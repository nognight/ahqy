var user;


/**获得请求参数
 * 
 * @param {*} name 
 * // 调用方法 alert(GetQueryString("参数名1"));
 */
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

/**
 * 获得用户，无用户则传登陆回调方法
 * @param {*} backUrl 
 */
function getCurrentUser(backUrl) {
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
                    window.location.href = "login.html?backUrl=" + backUrl;
                }
                user = object;

                try {

                    if (updatePageUser && typeof (updatePageUser) == "function") {
                        updatePageUser(object);
                    } else {
                        alert("不存在的函数");
                    }
                } catch (e) {
                    console.info("updatePageUser is not exsit");
                }



                // $("body").append('<div class ="toolbar"><div>&nbsp;</div><div>' + currentPhoneNum + '</div><div><a href="myPrivilege.html">&nbsp;</a></div></div>');
                // $('.toolbar>div:nth-child(1)').click(function (e) {
                //     history.go(-1);
                // });
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
function formatDateTime(long) {

    var time = new Date(long);
    var year = time.getFullYear();
    var month = time.getMonth() + 1;
    var date = time.getDate();
    var hour = time.getHours();
    var minute = time.getMinutes();
    var second = time.getSeconds();
    if (10 > hour) {
        hour = "0" + hour;
    }
    if (10 > minute) {
        minute = "0" + minute;
    }
    if (10 > second) {
        second = "0" + second;
    }
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}

/**
 * 时间戳转时间
 * @param {*} long 
 */
function formatDate(long) {

    var time = new Date(long - 1);
    var year = time.getFullYear();
    var month = time.getMonth() + 1;
    var date = time.getDate();
    return year + "." + month + "." + date;
}











/**
 * 
 */
function showWait() {
    $(document.body).append('<div id="wait" style="position:fixed;height:100%;width:100%;top:0px;text-align:center; background:rgba(30,30,30,0.5)"><img src="images/wait.jpg" style="margin-top:80%"></div>');
}

/**
 * 
 */
function closeWait() {
    $("div#wait").remove();
}


var confirmPop;

function showPop(title, content, callback) {
    confirmPop = callback;
    $(document.body).append('<div id="pop" style="position:fixed;text-align: center;vertical-align:middle;height:100%;width:100%;top:0px;background:rgba(30,30,30,0.5);"><div style="position: fixed;left: 50%;top: 50%;padding:10px;border: solid 1px #ccc;background-color: #ccc;border-radius: 10px;width: 80%;transform: translate(-50%,-50%);"><div style="font-size:20px;font-weight:600;">' + title + '</div><div style="font-size:3.5vw;">' + content + '</div><div><button onclick="confirmPop()" type="" style=" border: solid 1px #ccc;border-radius: 5px;font-size: 18px;padding:5px 20px; background-color:#fcc226">确认</button><button onclick="closePop()" type="" style=" border: solid 1px #ccc;border-radius: 5px;font-size: 18px;padding:5px 20px; margin-left:60px;">取消</button></div></div></div>');
}

function closePop() {
    $("div#pop").remove();
}

function showExplain(title, content, callback) {
    confirmPop = callback;
    $(document.body).append('<div id="pop" style="position:fixed;text-align: center;vertical-align:middle;height:100%;width:100%;top:0px;background:rgba(30,30,30,0.5);"><div style="position: fixed;left: 50%;top: 50%;padding:10px;border: solid 1px #ccc;background-color: #ccc;border-radius: 10px;width: 80%;transform: translate(-50%,-50%);"><div style="font-size:20px;font-weight:600;">' + title + '</div><div style="font-size:3.5vw;text-align:left;">' + content + '</div><div><button onclick="confirmPop()" type="" style=" border: solid 1px #ccc;border-radius: 5px;font-size: 18px;padding:5px 20px; background-color:#fcc226">确认</button></div></div></div>');
}