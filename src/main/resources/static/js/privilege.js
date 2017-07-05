var userInfo;
var typeNum = 5;
var privilegeList = new Array();










function addDetailClick() {
    $(".detail-top").click(function () {
        var _index = $('.detail-top').index(this);



        if ($('.detail-top-tips ').eq(_index).hasClass('detail-top-updown')) {
            $('.detail-top-tips ').eq(_index).addClass('detail-top-updowndown');
            $('.detail-top-tips ').eq(_index).removeClass('detail-top-updown');

            $('.detail-contentfa').eq(_index).hide();
            $('.equity-detail-item-more').eq(_index).hide();
        } else {
            $('.detail-top-tips ').eq(_index).removeClass('detail-top-updowndown');
            $('.detail-top-tips ').eq(_index).addClass('detail-top-updown');

            $('.detail-contentfa').eq(_index).show();
            $('.equity-detail-item-more').eq(_index).show();
        }







    });
}

function makeHtml(name, onlineDate, offlineDate) {

    var onlineDate = new Date(onlineDate);
    onlineDate = onlineDate.toLocaleDateString();
    var offlineDate = new Date(offlineDate);
    offlineDate = offlineDate.toLocaleDateString();




    var htmlContent = '<div class="detail-content"><div class="detail-content-top ">' + name + '</div><div class="detail-content-middle"></div><div class="detail-content-bottom"><div class="floatleft">领取时间：<span class="exchangetime">' + onlineDate + '-' + offlineDate + '</span></div><div class="floatright getequity">领取</div></div></div>';

    return htmlContent;
}

function addDetail() {
    for (var i = 0; i < privilegeList.length; i++) {
        for (var j = 0; j < privilegeList[i].length; j++) {
            $(".detail-contentfa").eq(i).append(makeHtml(privilegeList[i][j].name, privilegeList[i][j].onlineDate, privilegeList[i][j].offlineDate));
            console.log(j);
        }
        console.log(i);
    }
}


var flag = 0;


function afterAjax() {
    if (5 == flag) {
        addDetail();
        addDetailClick();
    }
}


$(document).ready(function () {

    for (var i = 1; i <= typeNum; i++) {
        $.ajax({
            type: "get",
            url: "./api/privilege/getList?type=" + i,

            success: function (response) {
                object = response.content;
                 privilegeList.push (object.object);
           
                flag = flag + 1;
                afterAjax();
            }
        });

    }


});