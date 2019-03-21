var privilegeId;
var type;
$(document).ready(function () {
    privilegeId = GetQueryString("privilegeId");
    $.ajax({
        type: "get",
        url: "api/privilege/getInfo?id=" + privilegeId,
        success: function (response) {
            content = response.content;
            obj = content.object;
            type = obj.type;
            var couponIds = new Array();
            couponIds = obj.couponIds.split("|");
            for (var i = 0; i < couponIds.length; i++) {
                $.ajax({
                    type: "get",
                    url: "api/coupon/getName?id=" + couponIds[i],
                    async: false,
                    success: function (response) {
                        if (0 != response.ret) {
                            return false;
                        }
                        content = response.content;
                        obj = content.object;
                        if (null == obj.offlineDate) {
                            obj.offlineDate = "2017-10-01 00:00:00";
                        }
                        itemFormat(obj.name, obj.name, obj.offlineDate, obj.id);

                    }
                });
                autoEvent(privilegeId, obj.id);
            }

        }
    });

});


function itemFormat(itemName, itemMemo, date, id) {
    var itemDate = formatDate(date);
    $("div.items").append('<div class="item" itemId="' + id + '"><div class="itemName">' + itemName + '</div><div class="itemMemo">' + itemMemo + '</div><div class="itemDate">' + itemDate + '</div></div>');
}

function autoEvent(privilegeId, id) {
    $.ajax({
        type: "get",
        url: "api/coupon/addUserCoupon?privilegeId=" + privilegeId + "&couponId=" + id + "&source=" + 0,
        success: function (response) {

            if (0 == response.ret) {
                obj = response.content;
                if (0 != obj.object) {
                    alert("领取失败");
                } else {
                    // var message = confirm("领取成功，是否立即使用？");
                    // if (message == true) {
                    //     useCoupon(type);
                    // } else {

                    // }
                }

            }


        }
    });
}

$('#use').click(function (e) { 
    useCoupon(3);
    
});
$('#explain').click(function (e) { 
   
    
});


/**
 * 
 * @param {*} type 
 */
function useCoupon(type) {
    if (3 == type) {
        location.href = "myCoupon.html?menu=couponRecord";
    } else if (4 == type) {
        location.href = "myCoupon.html?menu=inactiveCouponRecord";
    }

}