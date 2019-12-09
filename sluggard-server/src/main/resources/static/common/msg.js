function msg() {
}

msg.prototype = {
    toplayer: window.top.layer,   // 获取顶层窗口的layer对象
    topWin: window.top,           // 获取顶层窗口对象
    colseTime: 3000,              // 关闭弹出框的默认时间 1S
    width: '800px',               // 默认窗口的宽度
    height: '600px',              // 默认窗口的高度
    px: 'px',                     // 对话框宽高单位
    info: function (content, callback) {
        // 实际传入参数的长度
        var length = arguments.length;
        if (length === 0) {
            parent.layer.msg('操作成功!', {
                skin: 'layui-layer-lan'
                , maxWidth: 300
                , anim: 6
                , offset: 't'
                , icon: 6
                , time: 2000
            });
        } else if (length === 1) {
            parent.layer.msg(content, {
                skin: 'layui-layer-lan'
                , maxWidth: 300
                , anim: 6
                , offset: 't'
                , icon: 6
                , time: 2000
            });
        } else if (length >= 2) {
            parent.layer.msg(content, {
                skin: 'layui-layer-lan'
                , maxWidth: 300
                , anim: 6
                , offset: 't'
                , icon: 6
                , time: 2000
            }, function () {
                callback();
            });
        }
    },
    warn: function (content) {
        parent.layer.msg(content, {
            maxWidth: 300
            , anim: 6
            , offset: 't'
            , title: '警告'
            , icon: '&#xe702;'
            , time: 2000
        });
    },
    error: function (content) {
        parent.layer.msg(content, {
            maxWidth: 300
            , anim: 6
            , offset: 't'
            , title: '错误'
            , icon: 5
        });
    },
    confirm: function (fun, obj) {
        var length = arguments.length;
        layer.confirm("确认执行此操作?", {
            btn: ['确定', '取消'],
            icon: 3
        }, function (index) {
            if (length === 1) {
                fun();
            } else if (length >= 2) {
                fun(obj);
            }
            layer.close(index);
        });
    }
};

var msg = new msg();
