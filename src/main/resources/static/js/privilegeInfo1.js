var clickCount = 0;
var id;
var productPrice = 0;
var discountPrice = 0;
var isNeedTip = 0;



function getAuth(node) {

    // 获取验证码

    $.ajax({
        url: "api/privilege/authCode?id=" + id,
        type: "get",
        success: function (obj) {

            console.info(obj);

        }
    });


    clickCount++;
    timer.init(node);
    console.log(clickCount);
}


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
            this.count = 60;
        }
    },
    init: function (node) {
        this.node = node;
        $("span.getAuth").off("click");
        this.start();
    }
};




$('.orderBtn').click(function (e) {

    if (null !== $('input.authCode').val()) {
        $.ajax({
            type: "get",
            url: "api/privilege/use?id=" + id + "&authCode=" + $('input.authCode').val(),
            success: function (response) {
                console.log(response);
                if (0 == response.ret) {

                    content = response.content;

                    if (10 == content.ret) {
                        $('.am-modal-bd').text('短信验证码错误');

                        $('#mymodal').modal();
                    } else {
                        $('.am-modal-bd').text('您已经成功获得特权，参与结果请以短信通知结果为准。');
                        $('.orderBtn').attr("disabled", "disabled");
                        $('.orderBtn').text("已经订购");

                        $('#mymodal').modal();
                    }

                }
            }
        });
    } else {
        $('.am-modal-bd').text('请输入验证码');

        $('#mymodal').modal();


    }

});





//点击折扣券
// $("div.discount>div:nth-child(2)>img").click(function (e) {
//     $(this).attr("src", "./images/alreadyGet.png");
//     $('div.price>b').text(productPrice);


// });



$(document).ready(function () {

    getCurrentUser();




    id = GetQueryString("id");
    if (undefined != GetQueryString("isNeedTip")) {
        isNeedTip = GetQueryString("isNeedTip");
    }


    $.ajax({
        type: "get",
        url: "api/privilege/getUserList?type=0",
        success: function (response) {
            if (0 == response.ret) {
                content = response.content;
                object = content.object;
                userPrivilegeList = object;

                var notGetCoupon = true;

                for (var i = 0; i < userPrivilegeList.length; i++) {

                    if (id == userPrivilegeList[i].privilegeId) {
                        console.info("已经订购");
                        $('.orderBtn').attr("disabled", "disabled");
                        $('.orderBtn').text("已经订购");
                        $('.am-modal-bd').html('已经订购本产品，<br><a href="./privilegeDanmu.html">点击订购其他产品</a>');
                        $('#mymodal').modal();
                        return;

                    } else if (85 == userPrivilegeList[i].privilegeId || 86 == userPrivilegeList[i].privilegeId || 87 == userPrivilegeList[i].privilegeId || 88 == userPrivilegeList[i].privilegeId) {
                        console.info("已领取激活券");
                        notGetCoupon = false;
                    }

                }
                if (0 == isNeedTip && localStorage.isNeedTip == undefined) {
                    if (notGetCoupon) {
                        console.info("未领取激活券");
                        $('.am-modal-bd').html('您还没有领取流量券，<br>推荐<a href="./couponComment.html">点击此处领券</a>后订购。<br>否则将无法使用流量券');
                        $('#mymodal').modal();
                        localStorage.isNeedTip = false;

                    } else {
                        $('.am-modal-bd').text('您已经领取过流量券，本次订购成功后可激活使用对应流量券,一次成功订购只可激活一张流量券，无该流量券则不可激活。');
                        $('#mymodal').modal();
                        localStorage.isNeedTip = false;
                    }
                }
            }

        }
    });




    $.ajax({
        type: "get",
        url: "api/privilege/getInfo?id=" + id,
        success: function (response) {

            if (0 == response.ret) {
                content = response.content;
                object = content.object;



                $('div.privilegeName').append(object.name);

                $('div.tip').append(object.description);





                var productIds = new Array(); //定义一数组 
                productIds = object.productIds.split("|"); //字符分割 
                for (var i = 0; i < productIds.length; i++) {

                    $.ajax({
                        type: "get",
                        url: "api/product/getInfo?id=" + productIds[i],
                        success: function (response) {
                            if (0 == response.ret) {
                                content = response.content;
                                object = content.object;

                                $('.orderTable').append(' <tr><td>' + object.name + '</td><td>' + object.retailPrice + '元/月</td></tr>');
                                $('.tip').append('<p>产品说明：</p>' + object.description);
                                productPrice = productPrice + object.retailPrice;

                            }
                        }
                    });
                }

                var interval = window.setInterval(function () {
                    $('div.price>b').text(productPrice + discountPrice);
                    $('div.discount>div:nth-child(1)>div:nth-child(2)>b').text(discountPrice);

                }, 300);
                var timeout = window.setTimeout(function () {
                    window.clearInterval(interval);
                }, 1000);



                $(".privilegeName").text(object.name);


                var giftIds = new Array(); //定义一数组 
                if (undefined == object.giftId || null == object.giftId || "" == object.giftId) {
                    $("div.discount").css('display', 'none');
                    $('.giftTable').css('display', 'none');
                    $('.giftTitle').css('display', 'none');



                    return;
                }
                giftIds = object.giftId.split("|"); //字符分割 
                if (3 == object.giftType) {

                    for (var i = 0; i < giftIds.length; i++) {

                        $.ajax({
                            type: "get",
                            url: "api/product/getInfo?id=" + giftIds[i],
                            success: function (response) {
                                if (0 == response.ret) {
                                    content = response.content;
                                    object = content.object;
                                    $('.giftTable').append(' <tr><td>' + object.name + '</td><td>' + object.retailPrice + '元</td></tr>');
                                    discountPrice = discountPrice + object.retailPrice;

                                }
                            }
                        });
                    }

                } else if (2 == object.giftType) {
                    $(".giftTable>tbody>tr>th:nth-child(1)").text("卡券");
                    $(".giftTable>tbody>tr>th:nth-child(2)").text("状态");

                    for (var i = 0; i < giftIds.length; i++) {

                        $.ajax({
                            type: "get",
                            url: "api/coupon/getName?id=" + giftIds[i],
                            success: function (response) {
                                if (0 == response.ret) {
                                    content = response.content;
                                    object = content.object;
                                    $('.giftTable').append(' <tr><td>' + object.name + '</td><td>订购后激活</td></tr>');
                                    discountPrice = discountPrice + object.retailPrice;

                                }
                            }
                        });
                    }
                } else if (1 == object.giftType) {
                    $(".giftTable>tbody>tr>th:nth-child(1)").text("卡券");
                    $(".giftTable>tbody>tr>th:nth-child(2)").text("状态");

                    for (var i = 0; i < giftIds.length; i++) {

                        $.ajax({
                            type: "get",
                            url: "api/coupon/getName?id=" + giftIds[i],
                            success: function (response) {
                                if (0 == response.ret) {
                                    content = response.content;
                                    object = content.object;
                                    $('.giftTable').append(' <tr><td>' + object.name + '</td><td>已激活</td></tr>');
                                    discountPrice = discountPrice + object.retailPrice;

                                }
                            }
                        });
                    }
                }

            }


        }
    });

});