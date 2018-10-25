$.validator.setDefaults({
    submitHandler: function() {
      login();
    }
});
$().ready(function() {
    $("#form").validate();
});
//管理员登录
function login()
{
    $.ajax({
        cache: true,
        type: "POST",
        url: "/manager/managerLogin",
        data: $('#form').serialize(),
        async: false,
        success : function(r)
        {
            if(r.code == 0){
                parent.location.href = '/manager';
            }
            else
            {
                layer.msg(r.msg);
            }
        }
    });
}