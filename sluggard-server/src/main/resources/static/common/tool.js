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

// 判断是否为空对象
function isNullOrEmptyObject(obj) {
    return obj === null || (isObject(obj) && $.isEmptyObject(obj));
}

// 判断是否为对象
function isObject(obj) {
    return Object.prototype.toString.call(obj) === '[object Object]';
}

// 将字符串数组转化为复选框赋值对象
function strArrToCheck(strArr) {
    var obj = {};
    for (var index in strArr) {
        var str = strArr[index];
        if (str != null && str.length > 0) {
            obj[str] = true;
        }
    }
    return obj;
}

// 将复选框对象转化为字符串数组
function checkToStrArr(obj) {
    var strArr = [];
    if (isNullOrEmptyObject(obj)) {
        return strArr;
    }
    for (var field in obj) {
        if (obj[field] === "on") {
            strArr.push(field);
        }
    }
    return strArr;
}
