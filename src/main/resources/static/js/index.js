$().ready(function() {
    var a = $("#login");
    if(a.text() == "")
    {
        a.text("登录");
        $("#exit").hide();
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