$().ready(function (){
    if ($("#size").val() == "0")
    {
        $("#bottom").hide();
        $("#noGoods").show();
    }
});
//删除购物车
function del(id)
{
    $.ajax({
        cache : true,
        type : "POST",
        url : "/goods/delete",
        data : {
            id
        },
        async : false,
        error : function(request) {
            layer.alert("Connection error");
        },
        success : function(data) {
            window.location.reload()
        }
    });
}
//购买购物车中的物品
function buy(id)
{
    $.ajax({
        cache : true,
        type : "POST",
        url : "/goods/buy",
        data : {
            id
        },
        async : false,
        error : function(request) {
            layer.alert("Connection error");
        },
        success : function(data) {
            layer.msg("购买成功");
            setTimeout("window.location.reload()", 2000);
        }
    });
}
//购买购物车的全部物品
function buyAll()
{
    $.ajax({
        cache : true,
        type : "POST",
        url : "/goods/buyAll",
        data : { },
        async : false,
        error : function(request) {
            layer.alert("Connection error");
        },
        success : function(data) {
            window.location.reload()
        }
    });
}