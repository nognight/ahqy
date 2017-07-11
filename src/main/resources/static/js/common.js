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




function getCurrentUser(){
        $.ajax({
        type: "get",
        url: "api/user/getInfo",
        success: function (response) {
            console.log(response);
            if(0 == response.ret){
                 content = response.content;
                object = content.object;
                currentPhoneNum = object.phoneNum;

                $("body").append('<div class ="toolbar"><div>&lt; </div><div>'+currentPhoneNum+'</div><div><a href="myPrivilege.html">查看信息</a></div></div>');

            }
        }
    });
}