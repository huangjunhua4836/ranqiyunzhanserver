/**
 * 用户表
 */
layui.use('core', function(){
    var table = layui.table;
    var util = layui.util;
    var laydate = layui.laydate;
    var form = layui.form;
    var core = layui.core;
    var $ = layui.jquery;

    getUserTable({});

    /**
     * 查询数据并组装到layui表格
     * @param where
     */
    function getUserTable(where){
        //第一个实例
        table.render({
            elem: '#table'
            ,url: '/platform/crmUser/findUserBy' //数据接口
            ,where: where
            ,toolbar: '#titleToolbar' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter']
            ,title: '机构数据表'
            ,cols: [[ //表头
                {type: 'radio', fixed: 'left'}
                ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field: 'userCode', title: '用户名'}
                ,{field: 'nickname', title: '昵称'}
                ,{field: 'phone', title: '电话'}
                // ,{field: 'weight', title: '权重'}
                // ,{field: 'org_name', title: '所属机构'}
                // ,{field: 'member_name', title: '所属科室'}
                // ,{field: 'position_name', title: '岗位'}
                // ,{field: 'createUser', title: '创建者'}
                ,{field: 'createTime', title: '创建时间',sort: true,
                    templet: function(d){
                        return util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss");
                    }
                }
                ,{fixed: 'right', title:'操作', toolbar: '#rowToolBar',width:170,unresize: true}
            ]]
            ,page: true //开启分页
        });
    }

    //头工具栏事件
    table.on('toolbar(tableFilter)', function(obj){
        switch(obj.event){
            case 'add':
                core.openIframeDialog('添加用户','/platform/crmUser/userInput?type=add',['500px', '900px'],false,getUserTable);
                break;
            case 'restPassword':
                var checkStatus = table.checkStatus('table'); //table 即为基础参数 id 对应的值
                if(checkStatus.data.length == 0){
                    layer.msg('必须选择一个用户！',core.showtime);
                }else{
                    layer.confirm('真的要重置密码？重置密码为：123456', function(index){
                        layer.close(index);
                        //向服务端发送删除指令
                        var resultData = core.ajax('/platform/crmUser/restPassword',false,'POST','userId='+checkStatus.data[0].id);
                        if(resultData!=false){
                            layer.msg('操作成功！',core.showtime);
                            getUserTable({});
                        }
                    });
                }
                break;
            case 'auzCopy':
                var checkStatus = table.checkStatus('table'); //table 即为基础参数 id 对应的值
                if(checkStatus.data.length == 0){
                    layer.msg('必须选择一个用户！',core.showtime);
                }else{
                    core.openIframeDialog('授权复制','/manager/auzCopyInput?userId='+checkStatus.data[0].id,['550px', '900px'],false,null);
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
            layer.confirm('真的删除用户么？相关业务数据会失效！', function(index){
                layer.close(index);
                //向服务端发送删除指令
                var resultData = core.ajax('/platform/crmUser/deleteUser',false,'POST','userId='+data.id);
                if(resultData!=false){
                    layer.msg('操作成功！',core.showtime);
                    getUserTable({});
                }
            });
        } else if(obj.event === 'edit'){
            core.openIframeDialog('修改用户','/platform/crmUser/userInput?type=update&id='+data.id,['500px', '900px'],false,getUserTable);
        } else if(obj.event === 'detail'){
            core.openDialog('用户详情',$('#detail').html(),['500px','440px']);
            $('.layui-layer-content').find('input').eq(0).val(data.id);
            $('.layui-layer-content').find('input').eq(1).val(data.nickname);
            $('.layui-layer-content').find('input').eq(2).val(data.userCode);
            // $('.layui-layer-content').find('input').eq(3).val(data.orgName);
            // $('.layui-layer-content').find('input').eq(4).val(data.memberName);
            // $('.layui-layer-content').find('input').eq(5).val(data.weight);
            $('.layui-layer-content').find('input').eq(3).val(data.phone);
            // $('.layui-layer-content').find('input').eq(7).val(data.positionName);
            // $('.layui-layer-content').find('input').eq(8).val(data.createUser);
            $('.layui-layer-content').find('input').eq(4).val(util.toDateString(data.createTime, "yyyy-MM-dd HH:mm:ss"));
            // $('.layui-layer-content').find('input').eq(10).val(data.updateUser);
            $('.layui-layer-content').find('input').eq(5).val(util.toDateString(data.updateTime, "yyyy-MM-dd HH:mm:ss"));
        }else if(obj.event === 'auz'){
            core.openIframeDialog('用户授权','/manager/auz?userId='+data.id,['500px', '560px'],false,null);
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
        getUserTable(data.field);
        layer.msg('操作成功',core.showtime);
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
});