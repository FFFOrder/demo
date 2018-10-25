$().ready(function(){

});
//用户信息编辑弹出层
function editlayer(){
    layer.open({
        type: 2,
        title: 'login page',
        maxmin: true,
        shadeClose: true,
        area: ['600px', '400px'],
        move: '.layui-layer-title',
        content: '/user/edit'
    });
}