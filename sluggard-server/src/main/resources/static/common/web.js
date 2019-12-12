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
    if (length >= 2) {
        if (Object.prototype.toString.call(data) === '[object Object]' && $.isEmptyObject(data) === false) {
            url += "?";
            for (var field in data) {
                url = url + field + "=" + data[field] + "&";
            }
            url = url.substring(0, url.length - 1);
        }
    }
    $.ajax({
        type: 'get',
        url: getRestUrl(url),
        cache: false,
        async: false,
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

function getRest(url, data, fun) {
    var result = {};
    var length = arguments.length;
    if (length >= 2) {
        if (Object.prototype.toString.call(data) === '[object Object]' && $.isEmptyObject(data) === false) {
            url += "?";
            for (var field in data) {
                url = url + field + "=" + data[field] + "&";
            }
            url = url.substring(0, url.length - 1);
        }
    }
    $.ajax({
        type: 'get',
        url: getRestUrl(url),
        cache: false,
        async: false,
        dataType: 'json',
        success: function (resp) {
            if (resp.success === true) {
                msg.info(resp.message);
                result = resp.data;
                if (length >= 3) {
                    fun();
                }
            } else {
                msg.error(resp.message);
            }
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

function postRest(url, data, fun) {
    var result = {};
    var length = arguments.length;
    $.ajax({
        type: 'post',
        url: getRestUrl(url),
        data: JSON.stringify(data),
        contentType: 'application/json',
        dataType: 'json',
        success: function (resp) {
            if (resp.success === true) {
                msg.info(resp.message);
                result = resp.data;
                if (length >= 3) {
                    fun();
                }
            } else {
                msg.error(resp.message)
            }
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
