/**
 * 岗位表
 */
layui.use('core', function(){
    var table = layui.table;
    var util = layui.util;
    var laydate = layui.laydate;
    var form = layui.form;
    var core = layui.core;
    var $ = layui.jquery;

    getPositionTable({});

    /**
     * 查询数据并组装到layui表格
     * @param where
     */
    function getPositionTable(where){
        //第一个实例
        table.render({
            elem: '#table'
            ,url: '/crmPosition/findPositionBy' //数据接口
            ,where: where
            ,toolbar: '#titleToolbar' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '导入'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-upload'
            },'print']
            ,title: '机构数据表'
            ,cols: [[ //表头
                {field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field: 'name', title: '岗位名'}
                ,{field: 'createUser', title: '创建者'}
                ,{field: 'createTime', title: '创建时间',sort: true,
                    templet: function(d){
                        return util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss");
                    }
                }
                ,{fixed: 'right', title:'操作', toolbar: '#rowToolBar',width:150,unresize: true}
            ]]
            ,page: true //开启分页
        });
    }

    //头工具栏事件
    table.on('toolbar(tableFilter)', function(obj){
        switch(obj.event){
            case 'add':
                core.openIframeDialog('添加岗位','/crmPosition/positionInput?type=add',['500px', '200px'],false,getPositionTable);
                break;
            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        };
    });

    //监听行工具事件
    table.on('tool(tableFilter)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('真的删除岗位么？绑定该岗位的用户丢失岗位！', function(index){
                layer.close(index);
                //向服务端发送删除指令
                var resultData = core.ajax('/crmPosition/deletePosition',false,'POST','id='+data.id);
                if(resultData!=false){
                    layer.msg('操作成功！',core.showtime);
                    getPositionTable({});
                }
            });
        } else if(obj.event === 'edit'){
            core.openIframeDialog('修改岗位','/crmPosition/positionInput?type=update&id='+data.id,['500px', '200px'],false,getPositionTable);
        } else if(obj.event === 'detail'){
            core.openDialog('岗位详情',$('#detail').html(),['500px','480px']);
            $('.layui-layer-content').find('input').eq(0).val(data.id);
            $('.layui-layer-content').find('input').eq(1).val(data.name);
            $('.layui-layer-content').find('input').eq(2).val(data.createUser);
            $('.layui-layer-content').find('input').eq(3).val(util.toDateString(data.createTime, "yyyy-MM-dd HH:mm:ss"));
            $('.layui-layer-content').find('input').eq(4).val(data.updateUser);
            $('.layui-layer-content').find('input').eq(5).val(util.toDateString(data.updateTime, "yyyy-MM-dd HH:mm:ss"));
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
        getPositionTable(data.field);
        layer.msg('操作成功',core.showtime)
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
});