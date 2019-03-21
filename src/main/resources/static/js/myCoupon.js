$(document).ready(function () {
    getCurrentUser("myCoupon");
    var menu = GetQueryString("menu");
    addMenuEvent();
    $('[showDiv="' + menu + '"]').trigger("click");

    $('#question').click(function (e) {
        showExplain("卡券使用提示", "使用规则：<br>1、已领卡券：在领取区领取后在“已领卡券”中查看。已领取卡券需在订购相应产品后才能至“激活区”激活使用。<br>2、激活区：用户在订购相应的权益产品成功后在激活区，将免费领取的卡券激活使用。激活成功后，将以短信提示免费流量赠送到账。<br>3、已使用记录：可查看用户激活使用的记录<br>4、过期卡券：可查看用户已过期卡券。 <br> 5、以下情况<b>无法激活赠送流量券</b>。<br>&nbsp; (1).部分联通套餐<b>无法获赠流量</b><b>无法激活卡券流量</b>，例如：4G智慧沃家共享版和组合版套餐、部分沃行卡套餐、不限流量的套餐（无限流量王、冰激凌套餐等其他类似套餐）、上网卡、随心卡、日租卡、副卡等其他套餐暂不支持参加本次活动；<br>&nbsp; (2).如联通用户号码状态非正常状态，无法获赠流量，例如：欠费停机、预约转4G套餐、黑名单号码、非实名制等状态的用户；<br>&nbsp; (3).上述活动不支持联通2I2C套餐用户参加，例如腾讯王卡、蚂蚁宝卡等类似的联通与互联网公司合作的套餐。<br>&nbsp; (4)未能成功订购权益产品，将不能激活免费流量券。", function () {
            console.info("卡券使用提示");
            closePop();
        });

    });


});

function addMenuEvent() {

    $(".menu").click(function (e) {
        // e.preventDefault();
        var showDiv = $(this).attr("showDiv");
        console.log(showDiv);
        $(".menu").removeClass("active");
        $(this).addClass("active");
        $("#record").text('');


        /**
         * 点击刷新下面内容
         */
        showWait();

        if ("couponRecord" == showDiv) {

            $.ajax({
                type: "get",
                url: "api/coupon/getUserList?type=1",
                success: function (response) {
                    if (0 == response.ret) {
                        content = response.content;
                        var userObj = new Array();
                        userObj = content.object;
                        for (var i = 0; i < userObj.length; i++) {
                            $.ajax({
                                type: "get",
                                async: false,
                                url: "api/coupon/getName?id=" + userObj[i].couponId,
                                success: function (response) {
                                    content = response.content;
                                    obj = content.object;
                                    itemFormat(obj.name, "券内流量券，当月有效，点击可激活", userObj[i].startTime,userObj[i].expireTime, userObj[i].status, i);
                                    if (0 == userObj[i].status || 2 == userObj[i].status) {
                                        addClickEvent(obj.type, userObj[i].id, userObj[i].status, i);
                                    }
                                }
                            });

                        }
                    }

                    closeWait();

                }
            });
        } else if ("usedCouponRecord" == showDiv) {
            $.ajax({
                type: "get",
                url: "api/coupon/getUserList?type=1&status=1",
                success: function (response) {
                    if (0 == response.ret) {
                        content = response.content;
                        userObj = content.object;
                        for (var i = 0; i < userObj.length; i++) {
                            $.ajax({
                                type: "get",
                                async: false,
                                url: "api/coupon/getName?id=" + userObj[i].couponId,
                                success: function (response) {
                                    content = response.content;
                                    obj = content.object;
                                    itemFormat(obj.name, "券内流量当月有效", userObj[i].startTime,userObj[i].expireTime, userObj[i].status, i);
                                    if (0 == userObj[i].status || 2 == userObj[i].status) {
                                        addClickEvent(obj.type, userObj[i].id, userObj[i].status, i);
                                    }

                                }
                            });

                        }
                    }
                    closeWait();

                }
            });
        } else if ("inactiveCouponRecord" == showDiv) {
            $.ajax({
                type: "get",
                url: "api/coupon/getUserList?type=1&status=2",
                success: function (response) {
                    if (0 == response.ret) {
                        content = response.content;
                        userObj = content.object;
                        for (var i = 0; i < userObj.length; i++) {
                            $.ajax({
                                type: "get",
                                async: false,
                                url: "api/coupon/getName?id=" + userObj[i].couponId,
                                success: function (response) {
                                    content = response.content;
                                    obj = content.object;

                                    var memo = "";

                                    if(obj.name.indexOf("200M")!=-1){
                                        memo="在阅读类，音乐类订购成功后激活";
                                    }else if(obj.name.indexOf("500M")!=-1){
                                        memo="在视频类，游戏类订购成功后激活";
                                    }

                                    


                                    itemFormat(obj.name, memo, userObj[i].startTime,userObj[i].expireTime, userObj[i].status, i);
                                    if (0 == userObj[i].status || 2 == userObj[i].status) {
                                        addClickEvent(obj.type, userObj[i].id, userObj[i].status, i);
                                    }

                                }
                            });

                        }
                    }
                    closeWait();

                }
            });

        } else if ("expiredCouponRecord" == showDiv) {
            $.ajax({
                type: "get",
                url: "api/coupon/getUserList?type=1&status=-7",
                success: function (response) {
                    if (0 == response.ret) {
                        content = response.content;
                        userObj = content.object;
                        for (var i = 0; i < userObj.length; i++) {
                            $.ajax({
                                type: "get",
                                async: false,
                                url: "api/coupon/getName?id=" + userObj[i].couponId,
                                success: function (response) {
                                    content = response.content;
                                    obj = content.object;
                                    itemFormat(obj.name, "省内流量券，当月有效", userObj[i].startTime,userObj[i].expireTime, userObj[i].status, i);
                                    if (0 == userObj[i].status || 2 == userObj[i].status) {
                                        addClickEvent(obj.type, userObj[i].id, userObj[i].status, i);
                                    }

                                }
                            });

                        }
                    }
                    closeWait();

                }

            });

        }

    });

}


function itemFormat(name, memo, startDate,endDate, status, i) {
    var tag;
    switch (status) {
        case -1:
            tag = "不可用";
            break;
        case 0:
            tag = "可激活";
            break;
        case 1:
            tag = "已经使用";
            break;
        case 2:
            tag = "待订购";
            break;
        case 99:
            tag = "已过期";
            break;
        default:
            tag = "不可用";
    }
    $("#record").append('<div class="item couponRecord" itemIndex="' + i + '"><div class="itemInfo"><div class="itemName">' + name + '</div><div class="itemMemo">' + memo + '</div><div class="itemDate">有效期：' + formatDate(startDate)+'-'+formatDate(endDate)+ '</div></div><div class="itemTag">' + tag + '</div></div>');
}

function addClickEvent(type, id, status, i) {
    console.info("addClickEvent");
    if (1 == type) {

        if (2 == status) {
            $('[itemIndex="' + i + '"]').click(function (e) {
                showPop("订购产品", "点击确认即可前去订购产品，订购成功后请在激活区激活使用该卡券", function () {
                    window.location.href = "privilegeDanmu.html";
                });

            });
        } else if (0 == status) {
            $('[itemIndex="' + i + '"]').click(function (e) {
                showPop("使用卡券", "点击确认立即使用卡券。每张卡券只能使用一次，使用后可在已使用卡券中查找。", function () {
                    console.log("使用卡券使用卡券" + id);
                    showWait();
                    $.ajax({
                        type: "get",
                        url: "api/coupon/useUserCoupon?id=" + id,
                        success: function (response) {
                            console.log(response);
                            closeWait();
                            showPop("使用成功", "正在使用卡券，使用结果以短信通知为准。", function () {
                                window.location.href = "privilegeDanmu.html";
                            });


                        }
                    });
                });
            });
        }
    }
}

/**
 * 固有方法用于更新用户头
 * @param {*} user 
 */
function updatePageUser(user) {
    $("div.userPhone").append(user.name);
}