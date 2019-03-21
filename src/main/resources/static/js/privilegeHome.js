var weCode;
var type;
var currentPhoneNum;

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


$(document).ready(function () {
    $('div.type').animate({
        width: '80%'
    });
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
        getCurrentUser("privilegeHome");
    }

});

function updatePageUser(user) {
    location.href="privilegeDanmu.html";
}