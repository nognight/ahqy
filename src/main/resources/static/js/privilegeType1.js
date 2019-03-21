var privilegeList;
var category;



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
    var bgId = parseInt(category) + 3;

    for (var i = 0; i < privilegeList.length; i++) {
        $('.slider').append('<div><div class="imgDiv"><a href="./privilegeInfo1.html?id=' + privilegeList[i].id + '"><div class="slickDiv slickDivBg' + bgId + '" pid=' + i + '>' + privilegeList[i].name + '</div></a></div></div>');
    }
}


function listenSlick() {
    //监听

    var pid = $('div.slick-current>div>a>div').attr('pid');
    console.info(pid);
    $('div.tip').html('<p>权益介绍：</p>' + privilegeList[pid].description);
}


$(document).ready(function () {



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




    // getCurrentUser();

    // category = GetQueryString("category");
    // giftType = GetQueryString("giftType");

    // $.ajax({
    //     type: "get",
    //     url: "api/privilege/getList?type=1&category=" + category + "&giftType=" + giftType,
    //     success: function (response) {
    //         handResp(response);


    //         $('.center').slick({
    //             centerMode: true,
    //             infinite: true,
    //             centerPadding: '0px',
    //             slidesToShow: 3,
    //             speed: 300,
    //             arrows: false,
    //             autoplay: true,
    //             accessibility: false,
    //             autoplaySpeed: 1500,
    //             mobileFirst: true,
    //             swipeToSlide: true,
    //             dots: false,
    //             responsive: [{
    //                 breakpoint: 768,
    //                 settings: {
    //                     centerMode: true,
    //                     centerPadding: '0px',
    //                     slidesToShow: 3
    //                 }
    //             }, {
    //                 breakpoint: 480,
    //                 settings: {
    //                     centerMode: true,
    //                     centerPadding: '0px',
    //                     slidesToShow: 3
    //                 }
    //             }]
    //         });





    //         var t2 = window.setInterval('listenSlick()', 500);

    //     }
    // });






});