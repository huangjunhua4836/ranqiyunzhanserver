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
            ,url: '/platform/stationinfo/initTable' //数据接口
            ,where: where
            ,toolbar: '#titleToolbar' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter']
            ,title: '机构数据表'
            ,cols: [[ //表头
                {field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field: 'notificationTitle', title: '通知标题'}
                ,{field: 'msgTitle', title: '消息标题'}
                ,{field: 'sendtype', title: '发送类型',
                    templet: function(d){
                        if(d.sendtype == '1'){
                            return '安卓';
                        }else if(d.sendtype == '2'){
                            return 'IOS';
                        }else if(d.sendtype == '3'){
                            return '所有';
                        }else{
                            return '未知';
                        }
                    }
                }
                ,{field: 'msgContent', title: '消息内容'}
                , {
                    field: 'issuccess', title: '发送状态',
                    templet: function (d) {
                        if (d.issuccess == 0) {
                            return d.issuccess = '失败';
                        } else if (d.issuccess == 1) {
                            return d.issuccess = '成功';
                        } else if (d.issuccess == 2) {
                            return d.issuccess = '未发送';
                        } else {
                            return d.issuccess = '未知状态';
                        }
                    }
                }
                // ,{field: 'registrationId', title: '设备标识'}
                // ,{field: 'bieming', title: '别名'}
                ,{field: 'isdel', title: '删除状态',
                    templet: function(d){
                        return d.isdel == false?'正常':'删除';
                    }
                }
                ,{field: 'createtime', title: '创建时间',sort: true,
                    templet: function(d){
                        return d.createtime;
                        // return util.toDateString(d.createtime, "yyyy-MM-dd HH:mm:ss");
                    }
                }
                ,{fixed: 'right', title:'操作', toolbar: '#rowToolBar',width:200,unresize: true}
            ]]
            ,page: true //开启分页
        });
    }

    //头工具栏事件
    table.on('toolbar(tableFilter)', function(obj){
        switch(obj.event){
            case 'add':
                core.openIframeDialog('添加','/platform/stationinfo/input?type=add',['100%', '90%'],false,initTable);
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
                var resultData = core.ajax('/platform/stationinfo/delete',false,'POST','id='+data.id);
                if(resultData!=false){
                    layer.msg('操作成功！',core.showtime);
                    initTable({});
                }
            });
        } else if(obj.event === 'edit'){
            core.openIframeDialog('修改','/platform/stationinfo/input?type=update&id='+data.id,['100%', '90%'],false,initTable);
        } else if(obj.event === 'detail'){
            layer.confirm('真的发送么？', function(index){
                layer.close(index);
                //loading层
                var loadindex = layer.load(1, {
                    shade: [0.8,'#000'] //0.1透明度的白色背景
                })
                var resultData = core.ajax('/platform/stationinfo/sendOut',true,'POST','id='+data.id);
                if(resultData!=false){
                    layer.close(loadindex);
                    layer.msg('操作成功！',core.showtime);
                    initTable({});
                }
            });
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