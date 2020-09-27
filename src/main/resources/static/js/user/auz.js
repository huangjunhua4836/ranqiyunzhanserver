/**
 *权限树
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
        tree.reload('menuTreeId',{
            data: core.ajax('/manager/initMenuTree',false,'GET','')
        });
    }

    //菜单树展示
    tree.render({
        elem: '#menu'
        ,data: core.ajax('/manager/initMenuTree',false,'GET','')
        ,id: 'menuTreeId'
        ,isJump: true //是否允许点击节点时弹出新窗口跳转
        ,onlyIconControl: true
        ,showCheckbox: true
    });

    //按钮事件
    util.event('lay-tree', {
        reloadTree: function(){
            //重载实例
            reload();
        },
        submitButton:function(){
            //获得选中的节点
            var checkData = tree.getChecked('menuTreeId');
            if(core.isEmpty(checkData)){
                parent.layer.msg('至少选择一个权限！',core.showtime);
            }else{
                var resultData = core.ajax('/manager/auzUserOpear',false,'POST',"userId="+$('button').val()+"&checkedMenus="+JSON.stringify(checkData));
                if(resultData!=false){
                    parent.layer.msg('操作成功',core.showtime);
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                }
            }
        }
    });

    //赋值checkbox菜单树
    var checkedIds = $('button').attr('checkedIds');
    tree.setChecked('menuTreeId', JSON.parse(checkedIds));
});