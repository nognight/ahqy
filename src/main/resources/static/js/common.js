/**获得请求参数
 * 
 * @param {*} name 
 */
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

// 调用方法
// alert(GetQueryString("参数名1"));
// alert(GetQueryString("参数名2"));
// alert(GetQueryString("参数名3"));