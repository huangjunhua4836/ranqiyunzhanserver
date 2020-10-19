/**
 * 虚拟展厅表
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
            ,url: '/platform/hall/initTable' //数据接口
            ,where: where
            ,toolbar: '#titleToolbar' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter']
            ,title: '机构数据表'
            ,cols: [[ //表头
                {field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field: 'exhibitorname', title: '企业名称'}
                ,{field: 'boothno', title: '展位号'}
                ,{field: 'views', title: '浏览量'}
                ,{field: 'recommend', title: '是否推荐',
                    templet: function(d){
                        return d.recommend == 1 ? '推荐' : '不推荐';
                    }
                }
                ,{field: 'sort', title: '排序'}
                ,{field: 'createtime', title: '创建时间',sort: true,
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
                core.openIframeDialog('添加虚拟展厅','/platform/hall/input?type=add',['100%', '90%'],false,initTable);
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
            layer.confirm('真的删除么？', function(index){
                layer.close(index);
                //向服务端发送删除指令
                var resultData = core.ajax('/platform/hall/delete',false,'POST','id='+data.id);
                if(resultData!=false){
                    layer.msg('操作成功！',core.showtime);
                    initTable({});
                }
            });
        } else if(obj.event === 'edit'){
            core.openIframeDialog('修改虚拟展厅','/platform/hall/input?type=update&id='+data.id,['100%', '90%'],false,initTable);
        } else if(obj.event === 'detail'){
            core.openDialog('虚拟展厅详情',$('#detail').html(),['100%', '90%']);
            $('.layui-layer-content').find('input').eq(0).val(data.exhibitorname);
            $('.layui-layer-content').find('input').eq(1).val(data.boothno);
            $('.layui-layer-content').find('input').eq(2).val(data.hallurl);
            $('.layui-layer-content').find('input').eq(3).val(data.views);
            $('.layui-layer-content').find('input').eq(4).val(data.recommend);
            $('.layui-layer-content').find('input').eq(5).val(data.sort);
            $('.layui-layer-content').find('input').eq(6).val(data.createUser);
            $('.layui-layer-content').find('input').eq(7).val(util.toDateString(data.createTime, "yyyy-MM-dd HH:mm:ss"));
            $('.layui-layer-content').find('input').eq(8).val(data.updateUser);
            $('.layui-layer-content').find('input').eq(9).val(util.toDateString(data.updateTime, "yyyy-MM-dd HH:mm:ss"));
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