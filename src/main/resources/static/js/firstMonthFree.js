$(document).ready(function () {
    category = GetQueryString("category");
    $.ajax({
        type: "get",
        url: "api/privilege/getList?type=1&category=" + category,
        sync: false,
        success: function (response) {
            if (0 == response.ret) {
                content = response.content;
                var object = new Array();
                object = content.object;
                for (var i = 0; i < object.length; i++) {

                    $('div.productList').append('<div class="product" privilegeId="' + object[i].id + '"><div class="left"><div class="name">' + object[i].name + '</div><div class="info">' + object[i].name + '</div></div><div class="right"></div></div>');

                }

                $('div.product').click(function (e) {
                    var id = $(this).attr('privilegeId');

                    location.href = "privilegeInfo1.html?id=" + id + "&isNeedTip=1";



                });



            }

        }
    });
});