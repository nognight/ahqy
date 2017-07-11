var weCode;
var type;

$('.center').slick({
    centerMode: true,
    infinite: true,
    centerPadding: '0px',
    slidesToShow: 3,
    speed: 500,
    autoplay: true,
    responsive: [{
        breakpoint: 768,
        settings: {
            arrows: false,
            centerMode: true,
            centerPadding: '0px',
            slidesToShow: 3
        }
    }, {
        breakpoint: 480,
        settings: {
            arrows: false,
            centerMode: true,
            centerPadding: '0px',
            slidesToShow: 3
        }
    }]
});


$(document).ready(function () {
    $('div.type').animate({width:'100%'});
    weCode= GetQueryString("weCode");
     type= GetQueryString("type");
    $.ajax({
        type: "get",
        url: "api/login?type="+ type +"&weCode="+ weCode,
        success: function (response) {
            console.log(response);
        }
    });
});