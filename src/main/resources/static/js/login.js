$(document).ready(function () {
    alert("asdfj");
    validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
	    alert("asdfj");
		login();
	}
});

function login()
{
    alert("login");
    $.ajax({
        cache: true,
        type: "POST",
        url: "identification",
        data: $('#signupForm').serialize(),
        async: false,
        success : function(r)
        {
            if(r == 1){
                parent.location.href = '/index';
            }
        }
    });
}