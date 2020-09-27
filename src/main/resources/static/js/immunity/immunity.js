/**
 * 免疫追溯表
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
            ,url: '/immunity/initTable' //数据接口
            ,where: where
            ,title: '机构数据表'
            ,cols: [[ //表头
                {field:'chipId', title:'芯片ID', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field: 'diseaseType', title: '接种病种'}
                ,{field: 'season', title: '季节'}
                ,{field: 'immuneTime', title: '免疫时间'}
                ,{field: 'equNo', title: '打针设备号'}
                ,{field: 'vaccineBatchNumber', title: '疫苗批号'}
                ,{field: 'manufacturer', title: '生产厂家'}
                ,{fixed: 'right', title:'操作', toolbar: '#rowToolBar',width:150,unresize: true}
            ]]
            ,page: true //开启分页
        });
    }

    //监听行工具事件
    table.on('tool(tableFilter)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            core.openIframeDialog('免疫追溯详情','/immunity/input?id='+data.id,['500px', '600px'],false,initTable);
        }
    });

    //开始时间
    laydate.render({
        elem: '#startTime' //指定元素
    });
    //结束时间
    laydate.render({
        elem: '#endTime' //指定元素
    });

    //按条件检索机构数据
    form.on('submit(searchFrom)', function(data){
        initTable(data.field);
        layer.msg('操作成功',core.showtime)
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
});