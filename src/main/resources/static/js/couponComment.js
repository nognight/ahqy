var clickCount = 0;
var id;
var productPrice = 0;
var discountPrice = 0;




$('.orderBtn').click(function (e) {
    if (1 == user.payType) {
        id = 85;
    } else {
        if (1 == user.netType) {
            id = 86;

        } else if (2 == user.netType) {
            id = 87;
        } else if (3 == user.netType) {
            id = 88;
        }
    }


    $.ajax({
        type: "get",
        url: "api/privilege/addUserPrivilege?id=" + id,
        success: function (response) {
            console.log(response);
            if (0 == response.ret) {

                content = response.content;

                if (0 == content.ret) {
                    $.ajax({
                        type: "get",
                        url: "api/coupon/addUserCoupons?privilegeId=" + id + "&source=" + 0,
                        success: function (response) {
                            if (0 == response.ret) {
                                content = response.content;
                                if (0 == content.ret) {
                                    $('.orderBtn').attr("disabled", "disabled");
                                    $('.orderBtn').text("成功领取该权益");

                                    showPop("领取成功", "您的流量券已经放到您的卡券中心，是否立即查看？", function () {
                                        useCoupon(4);
                                    });
                                }
                            }

                        }
                    });
                } else if (10 == content.ret) {
                    // $('.am-modal-bd').text('短信验证码错误');

                    // $('#mymodal').modal();
                    $('.orderBtn').attr("disabled", "disabled");
                    $('.orderBtn').text("领取失败");
                } else {
                    // $('.am-modal-bd').text('您已经成功获得特权，参与结果请以短信通知结果为准。');
                    $('.orderBtn').attr("disabled", "disabled");
                    $('.orderBtn').text("请勿重复领取该权益");
                    // $('#mymodal').modal();
                    // location.href = "couponGetAuto.html?privilegeId=" + id;
                    // location.href = "myCoupon.html?menu=couponRecord";
                    // var message = confirm("您的流量券已经放到您的卡券中心，是否立即查看？");
                    // if (message == true) {
                    //     useCoupon(4);
                    // } else {

                    // }

                    showPop("请勿重复领取该权益", "您的流量券已经放到您的卡券中心，是否立即查看？", function () {
                        useCoupon(4);
                    });

                }

            }
        }
    });

});





$(document).ready(function () {

    getCurrentUser("couponComment");

    id = GetQueryString("id");

    $.ajax({
        type: "get",
        url: "api/privilege/getUserList?type=0",
        success: function (response) {
            if (0 == response.ret) {
                content = response.content;
                object = content.object;
                userPrivilegeList = object;
                for (var i = 0; i < userPrivilegeList.length; i++) {

                    if (id == userPrivilegeList[i].privilegeId) {
                        console.info("已经领取该权益");
                        $('.orderBtn').attr("disabled", "disabled");
                        $('.orderBtn').text("已经领取该权益");
                        alert('已经领取该权益');
                        setTimeout(function () {
                            // location.href = "couponGet.html?privilegeId=" + id;
                            location.href = "myCoupon.html?menu=couponRecord";
                        }, 1500);

                    }
                }
            }
        }
    });
});


/**
 * 
 * @param {*} type 
 */
function useCoupon(type) {
    if (3 == type) {
        location.href = "myCoupon.html?menu=couponRecord";
    } else if (4 == type) {
        location.href = "myCoupon.html?menu=inactiveCouponRecord";
    }

}


function updatePageUser(user) {
    if (3 == user.netType) {
        console.info("netType = 3");
        $('.orderBtn').attr("disabled", "disabled");
        $.ajax({
            type: "get",
            url: "api/user/getOrdered?type=2",
            success: function (response) {
                // {"ret":-1,"content":{"time":1508381380497,"object":["智慧沃家共享版-4G成员套内产品"]}}

                if (0 == response.ret) {
                    content = response.content;
                    objList = new Array();
                    objList = content.object;
                    strList = new Array("智慧沃家", "蚂蚁", "腾讯大王", "腾讯小王", "上网卡", "国际卡", "冰淇淋", "流量无限王");
                    notAllow = false;
                    for (var i = 0; i < objList.length; i++) {
                        for (var j = 0; j < strList.length; j++) {
                            if (-1 != objList[i].indexOf(strList[j])) {
                                notAllow = true;
                                break;
                            }
                        }
                        if (true == notAllow) {
                            break;
                        }
                    }
                    if (true == notAllow) {
                        $('.orderBtn').text("您的套餐暂不可领取");
                    } else {
                        $('.orderBtn').removeAttr("disabled");
                    }

                } else {
                    $('.orderBtn').text("请稍后再领取");
                }

            }
        });

    }
}