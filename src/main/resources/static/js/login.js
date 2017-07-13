var clickCount = 0;

function getAuth(node) {

    var comment_input = $("#phone");
    if (comment_input.val() == "" || (!/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/i.test(comment_input.val()))) {
        $("#mymodal>div>div.am-modal-bd").text("请输入格式正确的手机号码"); //设置提示内容
        $("#mymodal").modal('toggle'); //弹出提示框
        return false;
    }

    //获取验证码

    //http://112.122.11.231:9080/ahqy/api/loginCode?phoneNum=18655133003
    $.ajax({
        url: "./api/loginCode?phoneNum=" + $("#phone").val(),
        type: "get",
        success: function (obj) {

            ///api/login

        }
    });


    clickCount++;
    timer.init(node);
    console.log(clickCount);
}





function loginByAuthCode() {
    $.ajax({
        type: "get",
        url: "api/login?type=" + 2 + "&phoneNum=" + $("#phone").val() + "&authCode=" + $("#authCode").val(),
        success: function (response) {
            if (0 == response.ret) {
                content = response.content;
                if (0 == content.status) {
                    window.location.href = "privilegeHome.html";
                } else {
                    alert(验证码输入错误);
                }

            } else {
              
            }

        }
    });
}

$(".loginBtn").click(function (e) {
    loginByAuthCode();
});

$("span.getAuth").on("click", function () {
    getAuth(this);
});


var timer = {
    node: null,
    count: 30,
    start: function () {
        if (this.count > 0) {
            $("span.getAuth").addClass("unClick");
            this.node.innerHTML = this.count-- + "秒";
            var _this = this;
            setTimeout(function () {
                _this.start();
            }, 1000);
        } else {
            $("span.getAuth").removeClass("unClick");
            $("span.getAuth").on("click", function () {
                getAuth(this);
            });
            this.node.innerHTML = "重新获取";
            this.count = 30;
        }
    },
    init: function (node) {
        this.node = node;
        $("span.getAuth").off("click");
        this.start();
    }
};



$(".getAuthPic").click(function (e) {
    changeImg();

});

$(document).ready(function () {
    $('#doc-my-tabs').tabs();
});


// 刷新图片
function changeImg() {
    var imgSrc = $(".getAuthPic>img");
    var src = imgSrc.attr("src");
    imgSrc.attr("src", changeUrl(src));
}
//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
function changeUrl(url) {
    var timestamp = (new Date()).valueOf();
    var index = url.indexOf("timestamp", url);
    if (0 < index) {
        url = url.substring(0, index);
        url = url + "timestamp=" + timestamp;
    } else {
        url = url + "&timestamp=" + timestamp;

    }

    return url;
}