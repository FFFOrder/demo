$.validator.setDefaults({
    submitHandler: function() {
      login();
    }
});
$().ready(function() {
    $("#signupForm").validate();
});
//用户登录
function login()
{
    $.ajax({
        cache: true,
        type: "POST",
        url: "/user/identification",
        data: $('#signupForm').serialize(),
        async: false,
        success : function(r)
        {
            if(r.code == 0){
                parent.location.href = '/';
            }
            else
            {
                layer.msg(r.msg);
            }
        }
    });
}