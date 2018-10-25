$().ready(function(){
    page();
});
//初始化翻页
function page(){
    var max = parseInt($("#pageCount").val());
    var page = parseInt($("#page").val());
    var start = page;
    var end = page;
    var count = 0;
    while(count < 5 && (start > 1 || end < max)){
        if(start > 1 && count < 5){
            start--;
            count++;
        }
        if(end < max && count < 5){
            end++;
            count++;
        }
        console.log("count %d", count);
    }
    var url = "/?";
    if($("#search").val() != null && $("#search").val() != ""){
        url.append("search=" + $("#search").val() + "&");
    }
    for (var i = start; i <= end; i++){
        $("#pageCodes").append("<a href='" + url + "page=" + i + "'>" + i + "</a>")
    }
}