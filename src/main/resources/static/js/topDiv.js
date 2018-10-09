$().ready(function() {
    var a = $("#login");
    var username = getUser();

    if(username != null && username != "")
    {
        a.attr("disabled", true);
        a.text(username);
    }
    else
    {
        $("#exit").hide();
        $("#shoppingcart").hide();
    }
});

function login()
{
    layer.open({
        type: 2,
        title: 'login page',
        maxmin: true,
        shadeClose: true,
        area: ['600px', '400px'],
        move: '.layui-layer-title',
        content: '/login'
    });
}

function getUser(){
    var r;
    $.ajax({
        cache : true,
        type : "POST",
        url : "/getUser",
        data : { },
        async : false,
        error : function(request) {
            layer.alert("Connection error");
            r = "";
        },
        success : function(username) {
            r = username;
        }
    });
    return r;
}

function exit(){
    $.ajax({
        cache : true,
        type : "POST",
        url : "/exit",
        data : { },
        async : false,
        error : function(request) {
            layer.alert("Connection error");
        },
        success : function() {
            layer.msg("操作成功");
            window.location.href="/";
        }
    });
}