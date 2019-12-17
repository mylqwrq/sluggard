function opener() {
}

function getLayerOption(title, width, height, src, initObj, initType, reqMethod, reqUrl, call, getReqObj) {
    var length = arguments.length;
    return {
        type: 2,
        title: title,
        area: [width, height],
        skin: 'layui-layer-molv',
        resize: false,
        content: src + '?tm=' + new Date().getTime(),
        btn: ['提交', '取消'],
        yes: function (index, layero) {
            var validation = $(layero).find('iframe')[0].contentWindow.validationForm();
            if (!validation) {
                return;
            }
            if (length >= 8) {
                var result = {};
                var reqObj = {};
                if (length >= 10) {
                    reqObj = getReqObj(layer, index, layero);
                } else {
                    if (layer.getChildFrame('body', index).find('form').length > 0) {
                        var form = layer.getChildFrame('body', index).find('form')[0];
                        reqObj = formToObj($(form));
                    }
                }
                if (reqMethod === 'get') {
                    result = getRestResult(reqUrl, reqObj);
                } else if (reqMethod === 'post') {
                    result = postRestResult(reqUrl, reqObj);
                } else if (reqMethod === 'exportByPost') {
                    exportByPost(reqUrl, reqObj);
                    return;
                } else {
                    return;
                }
                if (result.success === true) {
                    msg.info(result.message);
                    layer.close(index);
                    if (length >= 9) {
                        call();
                    }
                } else {
                    msg.error(result.message);
                }
            }
        },
        success: function (layero, index) {
            if (length >= 5) {
                $(layero).find('iframe')[0].contentWindow.initForm(initObj, initType);
            }
        }
    };
}

opener.prototype = {
    add: function (t, w, h, src, initObj, reqMethod, reqUrl, call, getReqObj) {
        var length = arguments.length;
        var title = length >= 1 ? t : "新建";
        var width = length >= 2 ? w : '50%';
        var height = length >= 3 ? h : '50%';
        layer.open(getLayerOption(title, width, height, src, initObj, 1, reqMethod, reqUrl, call, getReqObj));
    },
    edit: function (t, w, h, src, initObj, reqMethod, reqUrl, call, getReqObj) {
        var length = arguments.length;
        var title = length >= 1 ? t : "编辑";
        var width = length >= 2 ? w : '50%';
        var height = length >= 3 ? h : '50%';
        layer.open(getLayerOption(title, width, height, src, initObj, 2, reqMethod, reqUrl, call, getReqObj));
    },
    scan: function (t, w, h, src, initObj) {
        var length = arguments.length;
        var title = length >= 1 ? t : "浏览";
        var width = length >= 2 ? w : '50%';
        var height = length >= 3 ? h : '50%';
        layer.open({
            type: 2,
            title: title,
            area: [width, height],
            skin: 'layui-layer-molv',
            resize: false,
            content: src + "?tm=" + new Date().getTime(),
            btn: ['取消'],
            success: function (layero, index) {
                if (length >= 5) {
                    $(layero).find('iframe')[0].contentWindow.initForm(initObj, 2);
                }
            }
        });
    }
};

var opener = new opener();
