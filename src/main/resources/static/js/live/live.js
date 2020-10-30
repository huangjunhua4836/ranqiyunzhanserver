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
                {type: 'radio', fixed: 'left'}
                ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field: 'mainTitle', title: '主标题'}
                ,{field: 'subTitle', title: '副标题'}
                // ,{field: 'announcement', title: '主播公告'}
                ,{field: 'roomNum', title: '直播号'}
                ,{field: 'liveStartTime', title: '直播开始时间'}
                ,{field: 'liveDuration', title: '直播时长'}
                ,{field: 'liveStatus', title: '直播状态',
                    templet: function(d){
                        if(d.liveStatus == '0'){
                            return '即将开播';
                        }if(d.liveStatus == '1'){
                            return '直播中';
                        }if(d.liveStatus == '2'){
                            return '直播结束';
                        }else{
                            return '未知状态';
                        }
                    }
                }
                // ,{field: 'videoDownUrl', title: '下载地址'}
                // ,{field: 'playback', title: '回访地址'}
                ,{field: 'sort', title: '排序'}
                ,{field: 'createtime', title: '创建时间',sort: true,
                    templet: function(d){
                        return util.toDateString(d.createtime, "yyyy-MM-dd HH:mm:ss");
                    }
                }
                ,{fixed: 'right', title:'操作', toolbar: '#rowToolBar',width:230,unresize: true}
            ]]
            ,page: true //开启分页
        });
    }

    //头工具栏事件
    table.on('toolbar(tableFilter)', function(obj){
        switch(obj.event){
            case 'add':
                core.openIframeDialog('添加','/platform/live/input?type=add',['100%', '90%'],false,initTable);
                break;
            case 'stoplive':
                var checkStatus = table.checkStatus('table'); //table 即为基础参数 id 对应的值
                if(checkStatus.data.length == 0){
                    layer.msg('必须选择一个直播！',core.showtime);
                }else{
                    layer.confirm('真的要直播结束吗？', function(index){
                        layer.close(index);
                        //向服务端发送删除指令
                        var resultData = core.ajax('/platform/live/liveStop',false,'POST','id='+checkStatus.data[0].id);
                        if(resultData!=false){
                            layer.msg('操作成功！',core.showtime);
                            initTable({});
                        }
                    });
                }
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
            core.openIframeDialog('修改','/platform/live/input?type=update&id='+data.id,['100%', '90%'],false,initTable);
        } else if(obj.event === 'detail'){
            core.openDialog('详情',$('#detail').html(),['100%', '90%']);
            $('.layui-layer-content').find('input').eq(0).val(data.flowName);
            $('.layui-layer-content').find('input').eq(1).val(data.videoDownUrl);
            $('.layui-layer-content').find('input').eq(2).val(data.playback);
            $('.layui-layer-content').find('input').eq(3).val(data.pushFlowUrl);
            $('.layui-layer-content').find('input').eq(4).val(data.pullFlowUrl);
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