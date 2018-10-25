$().ready(function() {
    countWord();
    $("#form").validate({})
});
//自定义from提交行为
$.validator.setDefaults({
    submitHandler: function() {
      save();
    }
});
//更新商品信息
function save()
{
    var formData = new FormData();
    var image = $("#image-input").get(0).files[0];
    formData.append('image', image);
    formData.append('id', $("#id").val());
    formData.append('name', $("#name").val());
    formData.append('price', $("#price").val());
    formData.append('number', $("#number").val());
    formData.append('sold', $("#sold").val());
    formData.append('info', $("#info").val());
    $.ajax({
        type: "POST",
        url: "/manager/save",
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        data: formData,
        success : function(data)
        {
            if(data == 0){
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
                parent.layer.msg("已保存");
                parent.tableReload();
            }
            else
            {
                alert("失败");
            }
        }
    });
}
//统计商品描述字数
function countWord(){
    var count = $("#info").val().length;
    $("#countWord").text(count);
}
//图片预览
function preview(){
    var reads = new FileReader();
    var f = $("#image-input").get(0).files[0];
    reads.readAsDataURL(f);
    reads.onload = function (e){
        $("#image").get(0).src = this.result;
    }
}
//重置图片选择
function resetImg(){
    $("#image-input").val("");
    $("#image").get(0).src = "/images/" + $("#id").val() + ".jpg";
}
