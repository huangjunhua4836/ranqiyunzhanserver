/**
 * 科室表
 */
layui.use('core', function(){
    var table = layui.table;
    var util = layui.util;
    var laydate = layui.laydate;
    var form = layui.form;
    var core = layui.core;
    var $ = layui.jquery;

    getMemberTable({});

    /**
     * 查询数据并组装到layui表格
     * @param where
     */
    function getMemberTable(where){
        //第一个实例
        table.render({
            elem: '#table'
            ,url: '/crmMember/findMemberBy' //数据接口
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
                ,{field: 'memberName', title: '科室名'}
                ,{field: 'parentId', title: '上级科室'}
                ,{field: 'organizationId', title: '所属机构'}
                ,{field: 'phone', title: '电话'}
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
                core.openIframeDialog('添加科室','/crmMember/memberInput?type=add',null,false,getMemberTable);
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
            layer.confirm('真的删除行么？该科室下的子科室也会被删除，相关业务数据会失效！', function(index){
                layer.close(index);
                //向服务端发送删除指令
                var resultData = core.ajax('/crmMember/deleteMember',false,'POST','memberId='+data.id+"&orgId="+data.organization_id);
                if(resultData!=false){
                    layer.msg('操作成功！',core.showtime);
                    getMemberTable({});
                }
            });
        } else if(obj.event === 'edit'){
            core.openIframeDialog('修改科室','/crmMember/memberInput?type=update&id='+data.id,null,false,getMemberTable);
        } else if(obj.event === 'detail'){
            core.openDialog('科室详情',$('#detail').html(),['500px','515px']);
            $('.layui-layer-content').find('input').eq(0).val(data.id);
            $('.layui-layer-content').find('input').eq(1).val(data.memberName);
            $('.layui-layer-content').find('input').eq(2).val(data.parent_member_name);
            $('.layui-layer-content').find('input').eq(3).val(data.phone);
            $('.layui-layer-content').find('input').eq(4).val(data.orgName);
            $('.layui-layer-content').find('input').eq(5).val(data.createUser);
            $('.layui-layer-content').find('input').eq(6).val(util.toDateString(data.createTime, "yyyy-MM-dd HH:mm:ss"));
            $('.layui-layer-content').find('input').eq(7).val(data.updateUser);
            $('.layui-layer-content').find('input').eq(8).val(util.toDateString(data.updateTime, "yyyy-MM-dd HH:mm:ss"));
        }
    });

    //下拉框监听事件
    form.on('select(selectOrgFilter)', function(data){
        var resultData = core.ajax('/crmMember/findMemberByOrg',false,'GET','orgId='+data.value);
        var optionHtml = "<option value=\"\">请选择</option>";
        if(!core.isEmpty(resultData)){
            for(var i = 0;i<resultData.length;i++){
                optionHtml += "<option value=\""+resultData[i].id+"\">"+resultData[i].memberName+"</option>";
            }
        }
        $('#parentId').html(optionHtml);
        form.render(); //更新全部
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
        getMemberTable(data.field);
        layer.msg('操作成功',core.showtime)
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
});