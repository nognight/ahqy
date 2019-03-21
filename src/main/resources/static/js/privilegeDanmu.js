var weCode;
var type;


/**
 * 固有方法用于更新用户头
 * @param {*} user 
 */
function updatePageUser(user) {
    if (user.netType == 3) {
        $("#youxi").remove();
    }
    startToSlide();
}


$(document).ready(function () {
    weCode = GetQueryString("weCode");
    type = GetQueryString("type");
    if (1 == type) {
        $.ajax({
            type: "get",
            url: "api/login?type=" + type + "&weCode=" + weCode,
            success: function (response) {
                console.log(response);
                if (0 == response.ret) {
                    getCurrentUser("privilegeHome");
                }
            }
        });
    } else {
        getCurrentUser("privilegeDanmu");
    }





    $('#getDiscount').click(function (e) {
        location.href = "couponComment.html?id=80";
    });
    $('#rx').click(function (e) {
        location.href = "privilegeType1.html?category=2&giftType=2";

    });
    $('#sym').click(function (e) {
        location.href = "firstMonthFree.html?category=5";

    });
    $('#grzx').click(function (e) {
        location.href = "orderedPrivilege.html?menu=getPrivilegeRecord";

    });
    $('#kqzx').click(function (e) {
        location.href = "myCoupon.html?menu=couponRecord";

    });

    $('#question').click(function (e) {
        showExplain("特权专区说明", "这里是特权专区，所有有关各种特权产品均在这里可以看到！<br>视频特权：各种视频免流量观看<br>腾讯视频定向流量包、优酷视频定向流量包、PPTV视频定向流量包<br>更多视频产品持续更新中<br>音乐特权：音乐在线免流量听<br>QQ音乐、网易音乐、百度音乐、沃音乐<br>最懂你的音乐在线倾听无需耗费你的任何流量<br>游戏|直播特权：打游戏、看直播！流量通通不算钱<br>王者荣耀专属流量包|斗鱼直播定向流量包<br>离开WiFi，一样畅快游戏！<br>阅读特权：正版电子书畅快阅读<br>创客阅读|精品杂志|酷阅原创精选|生活管家<br>为你提供最IN的正版阅读体验，每天都要进步一点点！<br>最重要的是：订购成功将会有超值500M、1G等多款包月流量免费送！<br>简直比买一赠一更划算！", function () {
            console.info("卡券使用提示");
            closePop();
        });

    });




    // text——弹幕文本内容。 
    // color——弹幕颜色。 position——弹幕位置 “0”为滚动 “1” 为顶部 “2”为底部 
    // size——弹幕文字大小。 “0”为小字 ”1”为大字
    // time——弹幕所出现的时间。 单位为”分秒“（及1/10秒，100毫秒）
    // isnew——当出现该属性时（属性值科委任意），会认为这是用户新发的弹幕，从而弹幕在显示的时候会有边框。
    //var a_danmu={ "text":"2333333" , "color":"green" ,"size":"1","position":"0","time":60 ,"isnew":" "}; 

    //dammuss对象每个属性的名称为弹幕所出现的时间点(分秒)，属性值为该时间点所出现的所有弹幕的danmu对象(除掉time属性的)所组成的数组。


    var danmuss = {
        1: [{
                "text": "看视频免流量啦",
                "color": "red",
                "size": "0",
                "position": "0",
                "time": 10
            },
            {
                "text": "打游戏不缺流量",
                "color": "blue",
                "size": "0",
                "position": "0",
                "time": 0
            }
        ],
        90: [{
            "text": "超实惠的产品",
            "color": "#FFFFFF",
            "size": "0",
            "position": "1"
        }, {
            "text": "666666",
            "color": "red",
            "size": "0",
            "position": "2",
            "time": 0
        }, {
            "text": "权益中心真实惠",
            "color": "green",
            "size": "0",
            "position": "0",
            "time": 20
        }],
        60: [{
            "text": "打游戏不缺流量",
            "color": "#FFFFFF",
            "size": "0",
            "position": "1"
        }, {
            "text": "看视频免流量啦",
            "color": "yellow",
            "size": "0",
            "position": "0",
            "time": 30
        }, {
            "text": "打游戏不缺流量",
            "color": "red",
            "size": "0",
            "position": "0",
            "time": 80
        }],
        30: [{
            "text": "权益中心真好",
            "color": "#FFFFFF",
            "size": "0",
            "position": "2"
        }, {
            "text": "超实惠的产品",
            "color": "red",
            "size": "0",
            "position": "0",
            "time": 70
        }]
    };
    $("#danmu").danmu({
        left: 0, //区域的起始位置x坐标
        top: 130, //区域的起始位置y坐标
        height: 70, //区域的高度 
        width: 100, //区域的宽度    修改部分参数单位为vw
        zindex: 100, //div的css样式zindex
        speed: 20000, //弹幕速度，飞过区域的毫秒数 
        sumtime: 900, //弹幕运行总时间
        danmuss: danmuss, //danmuss对象，运行时的弹幕内容 
        default_font_color: "#FFFFFF", //弹幕默认字体颜色 
        font_size_small: 16, //小号弹幕的字体大小,注意此属性值只能是整数
        font_size_big: 24, //大号弹幕的字体大小 
        opacity: "0.9", //弹幕默认透明度 
        top_botton_danmu_time: 6000 //顶端底端弹幕持续时间 
    });

    $('#danmu').danmu('danmu_start');



});

function startToSlide() {
    $('.center').slick({
        centerMode: true,
        infinite: true,
        centerPadding: '0px',
        slidesToShow: 3,
        speed: 300,
        arrows: false,
        autoplay: true,
        accessibility: false,
        autoplaySpeed: 1500,
        mobileFirst: true,
        swipeToSlide: true,
        dots: false,
        responsive: [{
            breakpoint: 768,
            settings: {
                centerMode: true,
                centerPadding: '0px',
                slidesToShow: 3
            }
        }, {
            breakpoint: 480,
            settings: {
                centerMode: true,
                centerPadding: '0px',
                slidesToShow: 3
            }
        }]
    });
}