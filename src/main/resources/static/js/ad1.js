var weCode;
var type;
var currentPhoneNum;

function slickReady() {
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




$(document).ready(function () {
    $('div.type').animate({
        width: '80%'
    });


    $.ajax({
        type: "get",
        url: "http://112.122.11.231:9080/ahqy/api/privilege/getAdList?type=5",
        success: function (response) {
            if (0 == response.ret) {
                content = response.content;
                var adList = new Array();
                adList = content.object;
                for (var i = 0; i < adList.length; i++) {

                    $("div.slider").append('<div><div class="imgDiv"><a href="' + adList[i].adUrl + '"><img src="' + adList[i].adPic + '"></a></div></div>');

                }

                slickReady();
            }

        }
    });


});