$.validator.setDefaults({
    submitHandler: function() {
      save();
    }
});
$().ready(function() {
    //表单验证
    $("#form").validate({
         rules:{
            phone:{
                required: true,
                digits: true
            },
            email:{
                required: true,
                email: true,
            }
        },
        messages: {
            phone: "请输入正确的手机号",
            email: "邮箱格式不正确",
        }
    })
});
//更新用户信息
function save()
{
    $.ajax({
        cache: true,
        type: "POST",
        url: "/user/update",
        data: $('#form').serialize(),
        async: false,
        success : function(data)
        {
            if(data == 0){
                layer.msg("已保存");
                setTimeout("parent.location.href = '/user'", 2000);
            }
            else
            {
                layer.msg("失败");
            }
        }
    });
}

