var userPrivilegeList;

$(document).ready(function () {
    $.ajax({
        type: "get",
        url: "api/privilege/getUserList?type=0",
        success: function (response) {
            if (0 == response.ret) {
                content = response.content;
                object = content.object;
                userPrivilegeList = object;


                handHtml(userPrivilegeList);



            }

        }
    });
});


function handHtml(userPrivilegeList) {
    for (var i = 0; i < userPrivilegeList.length; i++) {

       userPrivilege = userPrivilegeList[i];
        $.ajax({
            type: "get",
            url: "api/privilege/getInfo?id=" + userPrivilegeList[i].privilegeId,
            success: function (response) {
                if (0 == response.ret) {
                    content = response.content;
                    object = content.object;
                    object.name

                    $('body').append('<div class="equity-hasget">' +
                        '<div class="equity-fa">' +
                        '<div class="equity-card-left floatleft">' +
                        '<div class="equity-card-tip">' +
                        '<div class="equity-card-tip-child">' +
                        '<img src="images/equity-tip.png" width="100%" />' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '<div class="equity-card-middle floatleft">' +
                        '<div>' + object.name + '</div>' +
                        '<div>' + object.name + '</div>' +
                        '<div>' + userPrivilege.startTime + '</div>' +
                        '</div>' +
                        '<div class="equity-card-right floatright equity-card-hasget"></div>' +
                        '</div>' +
                        '</div>');

                }
            }
        });

    }

}