/**
 * 机构树
 */
layui.use('core', function(){
    var tree = layui.tree
        ,$ = layui.jquery
        ,util = layui.util
        ,core = layui.core;

    /**
     *重载tree实例
     */
    function reload(){
        tree.reload('orgTreeId',{
            data: core.ajax('/manager/initOrgTree',false,'GET','')
        });
        $('form').hide();
    }

    //菜单树展示
    tree.render({
        elem: '#menu'
        ,data: core.ajax('/manager/initOrgTree',false,'GET','')
        ,id: 'orgTreeId'
        ,isJump: true //是否允许点击节点时弹出新窗口跳转
        ,onlyIconControl: true
        ,click: function(obj){
            var elem = obj.elem;
            $('.layui-tree-main').removeAttr('style');
            elem.find('.layui-tree-main').eq(0).attr('style','background-color:#009f95');
            console.log(obj.data);
            var id = obj.data.id;  //获取当前点击的节点数据
            var resultData = core.ajax('/manager/findOrgOrMemberTreeById',false,'POST','id='+id+"&type="+obj.data.url);
            if(core.isEmpty(obj.data.url)){
                $('#org').show();
                $('#member').hide();
                $('#org').find('input').eq(0).val(resultData.id);
                $('#org').find('input').eq(1).val(resultData.org_name);
                $('#org').find('input').eq(2).val(resultData.org_address);
                $('#org').find('input').eq(3).val(resultData.org_phone);
                $('#org').find('input').eq(4).val(resultData.create_user);
                $('#org').find('input').eq(5).val(util.toDateString(resultData.gmt_create, "yyyy-MM-dd HH:mm:ss"));
                $('#org').find('input').eq(6).val(resultData.update_user);
                $('#org').find('input').eq(7).val(util.toDateString(resultData.gmt_modified, "yyyy-MM-dd HH:mm:ss"));
            }else{
                $('#org').hide();
                $('#member').show();
                $('#member').find('input').eq(0).val(resultData.id);
                $('#member').find('input').eq(1).val(resultData.member_name);
                $('#member').find('input').eq(2).val(resultData.org_name);
                $('#member').find('input').eq(3).val(resultData.parent_member_name);
                $('#member').find('input').eq(4).val(resultData.phone);
                $('#member').find('input').eq(5).val(resultData.create_user);
                $('#member').find('input').eq(6).val(util.toDateString(resultData.gmt_create, "yyyy-MM-dd HH:mm:ss"));
                $('#member').find('input').eq(7).val(resultData.update_user);
                $('#member').find('input').eq(8).val(util.toDateString(resultData.gmt_modified, "yyyy-MM-dd HH:mm:ss"));
            }
        }
    });

    //按钮事件
    util.event('lay-tree', {
        reloadTree: function(){
            //重载实例
            reload();
        }
    });
});