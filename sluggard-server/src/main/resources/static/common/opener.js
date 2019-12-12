function opener() {
}

opener.prototype = {
    add: function (url, fun, obj, t, w, h) {
        var length = arguments.length;
        var title = length >= 4 ? t : "新建";
        var width = length >= 5 ? w : '50%';
        var height = length >= 6 ? h : '50%';
        layer.open({
            type: 2,
            title: title,
            area: [width, height],
            skin: 'layui-layer-molv',
            resize: false,
            content: url + '?tm=' + new Date().getTime(),
            btn: ['提交', '取消'],
            yes: function (index, layero) {
                var validation = $(layero).find('iframe')[0].contentWindow.validationForm(obj);
                if (length >= 2 && (validation)) {
                    var fm = layer.getChildFrame('body', index).find('form')[0];
                    fun(formToObj($(fm)));
                    layer.close(index);
                }
            },
            success: function (layero, index) {
                if (length >= 3) {
                    $(layero).find('iframe')[0].contentWindow.initForm(obj);
                }
            }
        });
    },
    edit: function (url, fun, obj, t, w, h) {
        var length = arguments.length;
        var title = length >= 4 ? t : "编辑";
        var width = length >= 5 ? w : '50%';
        var height = length >= 6 ? h : '50%';
        layer.open({
            type: 2,
            title: title,
            area: [width, height],
            skin: 'layui-layer-molv',
            resize: false,
            content: url + "?tm=" + new Date().getTime(),
            btn: ['提交', '取消'],
            yes: function (index, layero) {
                var validation = $(layero).find('iframe')[0].contentWindow.validationForm(obj);
                if (length >= 2 && (validation)) {
                    var fm = layer.getChildFrame('body', index).find('form')[0];
                    fun(formToObj($(fm)));
                    layer.close(index);
                }
            },
            success: function (layero, index) {
                if (length >= 3) {
                    $(layero).find('iframe')[0].contentWindow.initEditForm(obj);
                }
            }
        });
    },
    scan: function (url, fun, obj, t, w, h) {
        var length = arguments.length;
        var title = length >= 4 ? t : "浏览";
        var width = length >= 5 ? w : '50%';
        var height = length >= 6 ? h : '50%';
        layer.open({
            type: 2,
            title: title,
            area: [width, height],
            skin: 'layui-layer-molv',
            resize: false,
            content: url + "?tm=" + new Date().getTime(),
            btn: ['取消'],
            success: function (layero, index) {
                if (length >= 3) {
                    $(layero).find('iframe')[0].contentWindow.initEditForm(obj);
                }
            }
        });
    }
};

var opener = new opener();
