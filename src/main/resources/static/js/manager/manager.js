var goodstable = layui.table;    //数据表格
$().ready(function(){
    tableInit();
});
//初始化表格
function tableInit (){
    goodstable.render({
        elem: '#table'  //元素id
        ,method:'post'
        ,url:'/manager/list'
        ,page: true
        ,id: 'goodstable'
        ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,request:{
            pageName: 'page'
            ,limitName: 'limit'
        }
        ,cols: [[
            {field:'id', width:80, title: 'ID'}
            ,{field:'name', width:80, title: '名称'}
            ,{field:'price', width:80, title: '价格', sort: true}
            ,{field:'info', width:80, title: '信息'}
            ,{field:'number', title: '库存', width: '30%', minWidth: 100} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
            ,{field:'sold', title: '销量', sort: true}
            ,{toolbar: '#toolbar'}
        ]]
    });
}
function tableReload() {
    goodstable.reload('goodstable',{
        where: {
            keyword: $("#keyword").val()
        }
        ,page: {
            page: 1
        }
    });
}
//表格按钮事件
goodstable.on('tool(table)', function(obj){
    var data = obj.data;
    if(obj.event === 'detail'){
        layer.msg('ID：'+ data.id + ' 的查看操作');
    } else if(obj.event === 'del'){
        layer.confirm('真的删除行么', function(index){
            obj.del();
            layer.close(index);
        });
    } else if(obj.event === 'edit'){
        edit(data.id);
    } else if(obj.event === 'uploadImg'){
        uploadImg(data.id);
    }
});

//商品信息编辑页面弹出层
function edit(id)
{
    layer.open({
        type: 2,
        title: 'edit',
        maxmin: true,
        shadeClose: true,
        area: ['600px', '500px'],
        move: '.layui-layer-title',
        content: '/manager/edit/' + id
    });
}
//商品添加页面弹出层
function add(){
    layer.open({
        type: 2,
        title: 'add',
        maxmin: true,
        shadeClose: true,
        area: ['600px', '400px'],
        move: '.layui-layer-title',
        content: '/manager/add/'
    });
}
//商品图片上传弹出层
function uploadImg(id){
    layer.open({
        type: 2,
        title: 'add',
        maxmin: true,
        shadeClose: true,
        area: ['600px', '400px'],
        move: '.layui-layer-title',
        content: '/manager/uploadImg/' + id
    });
}
