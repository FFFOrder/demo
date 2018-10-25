$.validator.setDefaults({
    submitHandler: function() {
      register();
    }
});
$().ready(function() {
    //验证用户注册表单
    $("#signupForm").validate({
         rules:{
            username: "required",
            password: "required",
            confirmpassword:{
                required: true,
                equalTo: "#password"
            }
        },
        messages: {
            username: "请输入用户名",
            password: "请输入密码",
            confirmpassword: {
                required: "请再次输入密码",
                equalTo: "密码不一致"
            }
        }
    })
});
//用户注册
function register()
{
    $.ajax({
        cache: true,
        type: "POST",
        url: "/user/register",
        data: $('#signupForm').serialize(),
        async: false,
        success : function(data)
        {
            if(data == 0){
                layer.msg("注册成功");
                setTimeout("parent.location.href = '/'", 2000);
            }
            else
            {
                layer.msg("该用户已存在");
            }
        }
    });
}