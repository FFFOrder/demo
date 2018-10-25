//预览选择的图片
function preview(){
    var reads = new FileReader();
    var f = $("#image").get(0).files[0];
    reads.readAsDataURL(f);
    reads.onload = function (e){
        $("#preview").get(0).src = this.result;
    }
}
//上传图片
function save(id) {
    var image = $("#image").get(0).files[0];
    var formData = new FormData();
    formData.append('image', image);
    formData.append('id', id);
    $.ajax({
        url: '/manager/uploadImg',
        type: 'POST',
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        data: formData,
        success: function (r)
        {
            if(r.code == 0){
                layer.msg("上传成功");
                $("#item-img").get(0).src = "/images/" + id + ".jpg?" + Math.random();
            }
        }
    });
}