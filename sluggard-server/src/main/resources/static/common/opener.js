function opener() {
}

function getLayerOption(title, width, height, src, initObj, initType, reqMethod, reqUrl, call, getReqObj) {
    if (arguments.length !== 10) {
        msg.error("非法入参");
        return;
    }
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
            if (reqUrl !== null) {
                var result = {};
                var reqObj = {};
                if (getReqObj !== null) {
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
                    if (call !== null) {
                        call();
                    }
                } else {
                    msg.error(result.message);
                }
            }
        },
        success: function (layero, index) {
            if (initObj !== null && initType !== null) {
                $(layero).find('iframe')[0].contentWindow.initForm(initObj, initType);
            }
        }
    };
}

opener.prototype = {
    /**
     * 打开新建/编辑子窗体
     *
     * @param title 标题
     * @param width 宽度
     * @param height 高度
     * @param src 链接
     * @param initObj 初始化对象
     * @param initType 初始化类型：1（新增）、2（编辑）
     * @param reqMethod 请求方式
     * @param reqUrl 请求地址
     * @param call 请求成功回调函数
     * @param getReqObj 获取请求对象函数
     */
    open: function (title, width, height, src, initObj, initType, reqMethod, reqUrl, call, getReqObj) {
        if (arguments.length !== 10) {
            msg.error("非法入参");
            return;
        }
        if (initType !== 1 && initType !== 2) {
            msg.error("未知的初始化类型：" + initType);
            return;
        }
        layer.open(getLayerOption(title, width, height, src, initObj, initType, reqMethod, reqUrl, call, getReqObj));
    },
    scan: function (title, width, height, src, initObj) {
        if (arguments.length !== 5) {
            msg.error("非法入参");
            return;
        }
        layer.open({
            type: 2,
            title: title,
            area: [width, height],
            skin: 'layui-layer-molv',
            resize: false,
            content: src + "?tm=" + new Date().getTime(),
            btn: ['取消'],
            success: function (layero, index) {
                if (initObj !== null) {
                    $(layero).find('iframe')[0].contentWindow.initForm(initObj, 2);
                }
            }
        });
    }
};

var opener = new opener();
