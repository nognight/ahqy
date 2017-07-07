


var clickCount = 0;

function getAuth(node) {



    //获取验证码

    //http://112.122.11.231:9080/ahqy/api/loginCode?phoneNum=18655133003
    // $.ajax({
    //     url: "./api/loginCode?phoneNum=" + $("#phone").val(),
    //     type: "get",
    //     success: function (obj) {
    


    
    //     }
    // });


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
