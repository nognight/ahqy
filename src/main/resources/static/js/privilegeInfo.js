var clickCount = 0;
var id;

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

$(document).ready(function () {

    id = GetQueryString("id");

    $.ajax({
        type: "get",
        url: "api/privilege/getInfo?id=" + id,
        success: function (response) {

            if (0 == response.ret) {
                content = response.content;
                object = content.object;


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

                            }
                        }
                    });
                }

                var giftIds = new Array(); //定义一数组 
                giftIds = object.giftId.split("|"); //字符分割 
                if (2 == object.giftType) {

                    for (var i = 0; i < giftIds.length; i++) {

                        $.ajax({
                            type: "get",
                            url: "api/product/getInfo?id=" + giftIds[i],
                            success: function (response) {
                                if (0 == response.ret) {
                                    content = response.content;
                                    object = content.object;

                                    $('.giftTable').append(' <tr><td>' + object.name + '</td><td>' + object.retailPrice + '元/月</td></tr>');

                                }
                            }
                        });
                    }
                }


            }

            $(".privilegeName").text(object.name);


        }
    });

});