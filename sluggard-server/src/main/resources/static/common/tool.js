// 封装Form表单数据为对象
function formToObj(form) {
    var arr = form.serializeArray();
    var obj = {};
    $.each(arr, function (index) {
        if (obj[this['name']]) {
            //如果name存在属性重复拼接value值
            obj[this['name']] = obj[this['name']] + ',' + this['value'];
        } else {
            obj[this['name']] = this['value'];
        }
    });
    return obj;
}

// 过滤指定对象中的指定字符
function filter(obj, pattern) {
    $(obj).val($(obj).val().replace(pattern, ''));
}