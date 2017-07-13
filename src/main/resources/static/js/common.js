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




function getCurrentUser() {
    $.ajax({
        type: "get",
        url: "api/user/getInfo",
        success: function (response) {
            console.log(response);
            if (0 == response.ret) {
                content = response.content;
                object = content.object;
                if (null == object) {
                    //跳登陆
                    window.location.href = "login.html";
                }

                currentPhoneNum = object.phoneNum;

                $("body").append('<div class ="toolbar"><div>&nbsp;</div><div>' + currentPhoneNum + '</div><div><a href="myPrivilege.html">&nbsp;</a></div></div>');
                $('.toolbar>div:nth-child(1)').click(function (e) {
                    history.go(-1);
                });
            }
        }
    });
}



//滚动到顶部监听
$(window).scroll(function () {


    //  $(window).scroll(function() {

    // if ($(document).scrollTop()<=0){
    //   $('.toolbar').css('display', 'block');
    // }else{
    //     $('.toolbar').css('display', 'none');
    // }

    //     if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
    //       alert("滚动条已经到达底部为" + $(document).scrollTop());
    //     }
    //   });

    // var top1 = $('.foot').offset().top;
    // console.log(top1);
    // var gun = $(document).scrollTop();
    // console.log(gun);
    // var top = top1 - gun;
    //  console.log(top);
    // if (top <= 0) {
    //     $('.toolbar').css('display', 'none');
    // } else {
    //     $('.toolbar').css('display', 'block');
    // }

    // if (70 >= gun) {
    //     $('.toolbar').css('display', 'none');
    // } else {
    //     $('.toolbar').css('display', 'block');
    // }

    if ($(document).scrollTop() + 10 >= $(document).height() - $(window).height()) {
        $('.toolbar').css('display', 'block');
    } else {
        $('.toolbar').css('display', 'none');
    }

});


/**
 * 时间戳转时间
 * @param {*} long 
 */
function formatDate(long) {

    var time = new Date(long);
    var year = time.getFullYear();
    var month = time.getMonth() + 1;
    var date = time.getDate();
    var hour = time.getHours();
    var minute = time.getMinutes();
    var second = time.getSeconds();
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}