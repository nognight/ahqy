$(document).ready(function () {

    getCurrentUser("myCoupon");
    var menu = GetQueryString("menu");

    addMenuEvent();
    $('[showDiv="' + menu + '"]').trigger("click");

    $('#question').click(function (e) {
        showExplain("权益中心", "这里是特权专区，所有有关各种特权产品均在这里可以看到！<br>视频特权：各种视频免流量观看<br>腾讯视频定向流量包、优酷视频定向流量包、PPTV视频定向流量包<br>更多视频产品持续更新中<br>音乐特权：音乐在线免流量听<br>QQ音乐、网易音乐、百度音乐、沃音乐<br>最懂你的音乐在线倾听无需耗费你的任何流量<br>游戏|直播特权：打游戏、看直播！流量通通不算钱<br>王者荣耀专属流量包|斗鱼直播定向流量包<br>离开WiFi，一样畅快游戏！<br>阅读特权：正版电子书畅快阅读<br>创客阅读|精品杂志|酷阅原创精选|生活管家<br>为你提供最IN的正版阅读体验，每天都要进步一点点！<br>最重要的是：订购成功将会有超值500M、1G等多款包月流量免费送！<br>简直比买一赠一更划算！", function () {
            console.info("权益中心");
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

        if ("privilegeRecord" == showDiv) {
            $.ajax({
                type: "get",
                url: "api/privilege/getUserList?type=0",
                success: function (response) {
                    if (0 == response.ret) {
                        content = response.content;
                        var userObj = new Array();
                        userObj = content.object;
                        for (var i = 0; i < userObj.length; i++) {
                            $.ajax({
                                type: "get",
                                async: false,
                                url: "api/privilege/getName?id=" + userObj[i].privilegeId,
                                success: function (response) {
                                    content = response.content;
                                    obj = content.object;
                                    itemFormat(obj.name, "已经拥有的权益", userObj[i].getTime, userObj[i].expireTime, userObj[i].status, i);
                                    if (0 == userObj[i].status) {
                                        addClickEvent(obj.type, obj.id, i);
                                    }
                                }
                            });

                        }
                    }
                    closeWait();
                }
            });
        } else if ("getPrivilegeRecord" == showDiv) {
            $.ajax({
                type: "get",
                url: "api/privilege/getUserList?type=4",
                success: function (response) {
                    if (0 == response.ret) {
                        content = response.content;
                        userObj = content.object;
                        for (var i = 0; i < userObj.length; i++) {
                            $.ajax({
                                type: "get",
                                async: false,
                                url: "api/privilege/getName?id=" + userObj[i].privilegeId,
                                success: function (response) {
                                    content = response.content;
                                    obj = content.object;
                                    itemFormat(obj.name, "订购产品后可激活获得的流量券", userObj[i].getTime, userObj[i].expireTime, userObj[i].status, i);
                                    if (0 == userObj[i].status) {
                                        addClickEvent(obj.type, obj.id, i);
                                    }

                                }
                            });

                        }
                    }
                    closeWait();
                }
            });
        } else if ("orderedPrivilegeRecord" == showDiv) {
            $.ajax({
                type: "get",
                url: "api/privilege/getUserList?type=1",
                success: function (response) {
                    if (0 == response.ret) {
                        content = response.content;
                        userObj = content.object;
                        for (var i = 0; i < userObj.length; i++) {
                            $.ajax({
                                type: "get",
                                async: false,
                                url: "api/privilege/getName?id=" + userObj[i].privilegeId,
                                success: function (response) {
                                    content = response.content;
                                    obj = content.object;
                                    itemFormat1(obj.name, "产品订购类权益", userObj[i].getTime, userObj[i].expireTime, userObj[i].status, i);
                                    if (0 == userObj[i].status) {
                                        addClickEvent(obj.type, obj.id, i);
                                    }

                                }
                            });

                        }
                    }
                    closeWait();
                }
            });
        } else if ("expiredPrivilegeRecord" == showDiv) {

            $.ajax({
                type: "get",
                url: "api/privilege/getUserList?type=1&status=-7",
                success: function (response) {
                    if (0 == response.ret) {
                        content = response.content;
                        userObj = content.object;
                        for (var i = 0; i < userObj.length; i++) {
                            $.ajax({
                                type: "get",
                                async: false,
                                url: "api/privilege/getName?id=" + userObj[i].privilegeId,
                                success: function (response) {
                                    content = response.content;
                                    obj = content.object;
                                    itemFormat(obj.name, "产品订购类权益", userObj[i].expireTime, userObj[i].status, i);
                                    if (0 == userObj[i].status) {
                                        addClickEvent(obj.type, obj.id, i);
                                    }

                                }
                            });

                        }
                    }
                    closeWait();
                }
            });
        } else if ("hotPrivilege" == showDiv) {
            closeWait();
            $("#record").append('<div class="item privilegeRecord" itemIndex="xx"><div class="itemInfo"><div class="itemName">' + "产品首月免费体验" + '</div><div class="itemMemo">' + "订购首月免费" + '</div><div class="itemDate">领取时间：无时间期限</div></div><div class="itemTag"><img src="images/privilgeUsed.png" alt=""></div></div>');
            $("#record").append('<div id="more">更多权益福利敬请期待！</div>');
        }

    });

}



function itemFormat(name, memo, getDate, endDate, status, i) {
    var tag;
    switch (status) {
        case -1:
            tag = "不可用";
            break;
        case 0:
            tag = "可使用";
            break;
        case 1:
            tag = "已经使用";
            break;
        case 2:
            tag = "使用失败";
            break;
        case 3:
            tag = "不可用";
            break;
        default:
            tag = "不可用";
    }
    $("#record").append('<div class="item privilegeRecord" itemIndex="' + i + '"><div class="itemInfo"><div class="itemName">' + name + '</div><div class="itemMemo">' + memo + '</div><div class="itemDate">领取时间：' + formatDate(getDate) + '-' + formatDate(endDate) + '</div></div><div class="itemTag"><img src="images/privilgeUsed.png" alt=""></div></div>');
}


function itemFormat1(name, memo, startDate, endDate, status, i) {
    var tag;
    switch (status) {
        case -1:
            tag = "不可用";
            break;
        case 0:
            tag = "可使用";
            break;
        case 1:
            tag = "已经订购";
            break;
        case 2:
            tag = "订购失败";
            break;
        case 3:
            tag = "不可用";
            break;
        default:
            tag = "不可用";
    }
    $("#record").append('<div class="item1 privilegeRecord" itemIndex="' + i + '"><div class="itemInfo"><div class="itemName">' + name + '</div><div class="itemMemo">' + memo + '</div><div class="itemDate">订购时间：' + formatDateTime(startDate)+ '</div></div><div class="itemTag1">' + tag + '</div></div>');
}




function addClickEvent(type, id, i) {
    if (3 == type || 4 == type) {
        $('[itemIndex="' + i + '"]').click(function (e) {
            location.href = "couponGet.html?privilegeId=" + id;
        });

    }
}


/**
 * 固有方法用于更新用户头
 * @param {*} user 
 */
function updatePageUser(user) {
    $("div.userPhone").append(user.name);
}