var curlPath = window.document.location.href;
var pathName = window.document.location.pathname;
var pos = curlPath.indexOf(pathName);
var ServerPath = curlPath.substring(0, pos);
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
projectName = projectName.substring(1, projectName.length);
var WEB_CONTEXT = ServerPath + "/" + projectName;

function getRestUrl(url) {
    url = url.indexOf("/") !== 0 ? url : url.substring(1);
    return WEB_CONTEXT + "/" + url;
}

function getRestResult(url, data) {
    var result = {};
    var length = arguments.length;
    if (length >= 2 && !isNullOrEmptyObject(data)) {
        url += "?";
        for (var field in data) {
            url = url + field + "=" + data[field] + "&";
        }
        url = url.substring(0, url.length - 1);
    }
    $.ajax({
        type: 'get',
        url: getRestUrl(url),
        cache: false,
        async: false,
        contentType: 'application/json',
        dataType: 'json',
        success: function (resp) {
            result = resp;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.status === 555) {
                //window.location.href = "/login.html";
            } else {
                msg.error("错误状态码:" + XMLHttpRequest.readyState);
            }
        }
    });
    return result;
}

function postRestResult(url, data) {
    var result = {};
    $.ajax({
        type: 'post',
        url: getRestUrl(url),
        data: JSON.stringify(data),
        async: false,
        contentType: 'application/json',
        dataType: 'json',
        success: function (resp) {
            result = resp;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (textStatus === 555) {
                //window.location.href = "/login.html";
            } else {
                msg.error("错误状态码:" + XMLHttpRequest.readyState);
            }
        }
    });
    return result;
}

function getRestData(url, data, fun) {
    var length = arguments.length;
    var result = getRestResult(url, data);
    if (result.success === true) {
        msg.info(result.message);
        if (length >= 3) {
            fun();
        }
    } else {
        msg.error(result.message);
    }
    return result;
}
