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


});