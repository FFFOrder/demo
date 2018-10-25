$().ready(function() {
    //根据用户登录状态显示界面
    changeView();
});
//用户登录页面弹出层
function loginlayer()
{
    layer.open({
        type: 2,
        maxmin: true,
        title: false,
        shadeClose: true,
        area: ['600px', '400px'],
        move: '.layui-layer-title',
        content: '/user/login'
    });
}
/*
 *根据用户登录状态改变界面
 *当用户为登录状态时，隐藏登录按钮，显示用户菜单
 */
function changeView(){
    $.ajax({
        cache : true,
        type : "POST",
        url : "/user/getUser",
        data : { },
        error : function(request) {
            layer.alert("Connection error");
            r = "";
        },
        success : function(username) {
            if(username != null && username != "")
            {
                $("#login").hide();
                $("#user").text(username);
            }
        }
    });
}
//退出登录状态
function exit(){
    $.ajax({
        cache : true,
        type : "POST",
        url : "/user/exit",
        data : { },
        async : false,
        error : function(request) {
            layer.alert("Connection error");
        },
        success : function() {
            window.location.href="/";
        }
    });
}