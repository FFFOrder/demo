//增加商品的选择数量，最大值为库存数量
function itemAdd()
{
    var goodsnumber = $("#goodsnumber");
    var number = $("#number");
    if (parseInt(goodsnumber.text()) < parseInt(number.text())){
        goodsnumber.text(parseInt(goodsnumber.text()) + 1);
        countPrice();
    }
}
//减少商品的选择数量，最小值为1
function itemReduce()
{
    var goodsnumber = $("#goodsnumber");
    if (parseInt(goodsnumber.text()) > 1)
    {
        goodsnumber.text(parseInt(goodsnumber.text()) - 1);
        countPrice();
    }
}
//计算并显示总价格
function countPrice(){
    var totalPrice = parseFloat($("#price").text()) * parseInt($("#goodsnumber").text());
    $("#totalPrice").text(totalPrice);
}
//添加到购物车
function add()
{
    var goodsnumber = parseInt($("#goodsnumber").text());
    var goodsid = document.getElementById("goodsid").value;
    if(parseInt(goodsnumber) < 1)
    {
        layer.msg("请选择数量")
    }
    else{
        $.ajax({
            cache : true,
            type : "POST",
            url : "/goods/addItem",
            data : {
                goodsid,
                goodsnumber
            },
            async : false,
            error : function(request) {
                layer.alert("Connection error");
            },
            success : function(data) {
                if (data.code == 0)     //code=0 添加成功
                {
                    layer.msg("成功添加购物车");
                    updateNumber();
                }
                else if (data.code == -1)   //code=-1 添加失败，库存不足
                {
                    layer.msg(data.msg);
                    updateNumber();
                }
                else    //添加失败，其他原因
                {
                    window.location.href="/user/login";
                }
            }
        });
    }
}
//更新商品详情页面的库存
function updateNumber(){
    var id = $("#goodsid").val();
    $.ajax({
        cache : true,
        type : "POST",
        url : "/goods/getItem",
        data : {
            id
        },
        async : false,
        error : function(request) {
            layer.alert("Connection error");
        },
        success : function(data) {
            $("#number").text(data.number);
        }
    });
}