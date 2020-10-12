/**
 * 表
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
            ,url: '/platform/live/initTable' //数据接口
            ,where: where
            ,toolbar: '#titleToolbar' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter']
            ,title: '机构数据表'
            ,cols: [[ //表头
                {field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field: 'mainTitle', title: '主标题'}
                ,{field: 'subTitle', title: '副标题'}
                ,{field: 'roomNum', title: '直播号'}
                ,{field: 'flowName', title: '流名称'}
                ,{field: 'liveDuration', title: '直播时长'}
                ,{field: 'liveStartTime', title: '直播开始时间'}
                ,{field: 'liveEndtime', title: '直播结束时间'}
                ,{field: 'createtime', title: '创建时间',sort: true,
                    templet: function(d){
                        return util.toDateString(d.createtime, "yyyy-MM-dd HH:mm:ss");
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
                core.openIframeDialog('添加','/platform/live/input?type=add',['500px', '100%'],false,initTable);
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
                var resultData = core.ajax('/platform/live/delete',false,'POST','id='+data.id);
                if(resultData!=false){
                    layer.msg('操作成功！',core.showtime);
                    initTable({});
                }
            });
        } else if(obj.event === 'edit'){
            core.openIframeDialog('修改','/platform/live/input?type=update&id='+data.id,['500px', '100%'],false,initTable);
        } else if(obj.event === 'detail'){
            core.openDialog('详情',$('#detail').html(),['500px','480px']);
            $('.layui-layer-content').find('input').eq(0).val(data.id);
            $('.layui-layer-content').find('input').eq(1).val(data.mainTitle);
            $('.layui-layer-content').find('input').eq(2).val(data.subTitle);
            $('.layui-layer-content').find('input').eq(3).val(data.roomNum);
            $('.layui-layer-content').find('input').eq(4).val(data.flowName);
            $('.layui-layer-content').find('input').eq(5).val(data.liveDuration);
            $('.layui-layer-content').find('input').eq(6).val(data.livePassword);
            $('.layui-layer-content').find('input').eq(7).val(data.liveStartTime);
            $('.layui-layer-content').find('input').eq(8).val(data.liveEndtime);
            $('.layui-layer-content').find('input').eq(9).val(data.videoDownUrl);
            $('.layui-layer-content').find('input').eq(10).val(data.pushFlowUrl);
            $('.layui-layer-content').find('input').eq(11).val(data.pullFlowUrl);
            $('.layui-layer-content').find('input').eq(12).val(data.notice);
            $('.layui-layer-content').find('input').eq(13).val(data.playback);
            $('.layui-layer-content').find('input').eq(14).val(data.sort);
            $('.layui-layer-content').find('input').eq(15).val(data.announcement);
            $('.layui-layer-content').find('input').eq(16).val(data.createtime);
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