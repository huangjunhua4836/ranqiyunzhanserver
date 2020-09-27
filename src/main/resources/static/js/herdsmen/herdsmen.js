/**
 * 牧户表
 */
layui.use('core', function(){
    var table = layui.table;
    var util = layui.util;
    var laydate = layui.laydate;
    var form = layui.form;
    var core = layui.core;
    var $ = layui.jquery;

    initTable({});

    /**
     * 查询数据并组装到layui表格
     * @param where
     */
    function initTable(where){
        //第一个实例
        table.render({
            elem: '#table'
            ,url: '/herdsmen/initTable' //数据接口
            ,where: where
            ,title: '机构数据表'
            ,cols: [[ //表头
                {field:'id', title:'编号', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field: 'name', title: '牧户姓名'}
                ,{field: 'phone', title: '牧户手机号'}
                ,{field: 'name', title: '牲畜类别'}
                ,{field: 'animalscount', title: '牧户牲畜数'}
            ]]
            ,page: true //开启分页
        });
    }

    //按条件检索机构数据
    form.on('submit(searchFrom)', function(data){
        initTable(data.field);
        layer.msg('操作成功',core.showtime)
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
});