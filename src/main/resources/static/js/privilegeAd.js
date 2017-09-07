var source;
var type;





function handResp(response) {
    if (0 == response.ret) {
        content = response.content;
        privilegeList = content.object;
    }

    if (4 > privilegeList.length) {
        for (var i = 0; i < privilegeList.length; i++) {
            privilegeList.push(privilegeList[i]);
            if (4 <= privilegeList.length) {
                break;
            }
        }
    }
    for (var i = 0; i < privilegeList.length; i++) {
        $('.slider').append('<div><div class="imgDiv"><a href="' + privilegeList[i].adUrl + '"><img src="' + privilegeList[i].adPic + '"></a></div></div>');
    }

}

$(document).ready(function () {
    $('div.type').animate({
        width: '80%'
    });

    source = GetQueryString("source");
    type = GetQueryString("type");


    $.ajax({
        type: "get",
        url: "api/privilege/getAdList?type=" + type + "&source=" + source + "&id=0",
        success: function (response) {
            handResp(response);



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
    });

});