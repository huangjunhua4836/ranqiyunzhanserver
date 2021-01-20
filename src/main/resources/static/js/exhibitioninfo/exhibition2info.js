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
            ,url: '/platform/zsInfo/initTable' //数据接口
            ,where: where
            ,toolbar: '#titleToolbar' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter']
            ,title: '数据表'
            ,cols: [[ //表头
                {field:'id', title:'ID', width:55, fixed: 'left', unresize: true, sort: true}
                ,{field: 'registerphone', title: '注册手机号'}
                ,{field: 'name', title: '管理者姓名'}
                ,{field: 'enterprisename', title: '企业'}
                // ,{field: 'mailbox', title: '邮箱'}
                ,{field: 'boothno', title: '展位号'}
                // ,{field: 'fid', title: 'fid'}
                ,{field: 'sort', title: '排序',sort: true}
                ,{field: 'isrecommend', title: '推荐',sort: true,
                    templet: function(d){
                        if(d.isrecommend == true){
                            return '<b style="color: red;">推荐</b>';
                        }else {
                            return '';
                        }
                    }
                }
                ,{field: 'state', title: '审核状态',
                    templet: function(d){
                        if(d.state == '0'){
                            return '未认证';
                        }else if(d.state == '1'){
                            return '审核通过';
                        } if(d.state == '2'){
                            return '审核中';
                        } if(d.state == '3'){
                            return '审核失败';
                        }else{
                            return '错误状态';
                        }
                    }
                }
                ,{field: 'type', title: '注册方式',
                    templet: function(d){
                        if(d.type == '0'){
                            return '用户注册';
                        }else if(d.type == '1'){
                            return '后台创建';
                        }else{
                            return '未知';
                        }
                    }
                }
                ,{field: 'createtime', title: '创建时间',sort: true,
                    templet: function(d){
                        return d.createtime;
                        // return util.toDateString(d.createtime, "yyyy-MM-dd HH:mm:ss");
                    }
                }
                ,{fixed: 'right', title:'操作', toolbar: '#rowToolBar',width:280,unresize: true}
            ]]
            ,page: true //开启分页
        });
    }

    var exportcondit;
    //头工具栏事件
    table.on('toolbar(tableFilter)', function(obj){
        switch(obj.event){
            case 'add':
                core.openIframeDialog('添加','/platform/zsInfo/input?type=add',['100%', '90%'],false,initTable);
                break;
            case 'export':
                location.href = "/platform/zsInfo/export?str="+encodeURIComponent(JSON.stringify(exportcondit));
                layer.msg('操作成功！',core.showtime);
                break;
            case 'refreshCloudWindows':
                var resultData = core.ajax('/platform/zsInfo/refreshCloudWindows',false,'POST',null);
                if(resultData!=false){
                    layer.msg('操作成功！',core.showtime);
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
            layer.confirm('确定删除么？删除之后不可恢复！', function(index){
                layer.close(index);
                //向服务端发送删除指令
                var resultData = core.ajax('/platform/zsInfo/delete',false,'POST','id='+data.id);
                if(resultData!=false){
                    layer.msg('操作成功！',core.showtime);
                    initTable({});
                }
            });
        }else if(obj.event === 'shenhe'){
            if(data.state == '1'){
                layer.msg('已经认证过啦',core.showtime);
                return;
            }
            layer.confirm('确定审核么？', function(index){
                layer.close(index);
                core.openIframeDialog('审核','/platform/zsInfo/input?type=shenhe&id='+data.id,['60%', '50%'],false,initTable);
            });
        } else if(obj.event === 'edit'){
            core.openIframeDialog('修改','/platform/zsInfo/input?type=update&id='+data.id,['100%', '90%'],false,initTable);
        } else if(obj.event === 'isrecommend'){
            if(data.state != '1'){
                layer.msg('没通过审核不能推荐！',core.showtime);
                return;
            }
            core.openIframeDialog('推荐','/platform/zsInfo/isrecommend?id='+data.id,['50%', '40%'],false,initTable);
        } else if(obj.event === 'detail'){
            core.openDialog('详情',$('#detail').html(),['100%', '90%']);
            $('.layui-layer-content').find('input').eq(0).val(data.registerphone);
            $('.layui-layer-content').find('input').eq(1).val(data.mailbox);
            $('.layui-layer-content').find('input').eq(2).val(data.name);
            $('.layui-layer-content').find('input').eq(3).val(data.phone);
            $('.layui-layer-content').find('input').eq(4).val(data.idcard);
            $('.layui-layer-content').find('input').eq(5).val(data.tel);
            $('.layui-layer-content').find('input').eq(6).val(data.enterprisename);
            $('.layui-layer-content').find('input').eq(7).val(data.boothno);
            $('.layui-layer-content').find('input').eq(8).val(data.fid);
            $('.layui-layer-content').find('input').eq(9).val(data.halurl);
            $('.layui-layer-content').find('input').eq(10).val(util.toDateString(data.createtime, "yyyy-MM-dd HH:mm:ss"));
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
        exportcondit = data.field;
        initTable(data.field);
        layer.msg('操作成功',core.showtime)
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
});