/**
 * 菜单树
 */
layui.use('core', function(){
    var tree = layui.tree
        ,layer = layui.layer
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
        $('form').hide();
    }

    //菜单树展示
    tree.render({
        elem: '#menu'
        ,data: core.ajax('/manager/initMenuTree',false,'GET','')
        ,id: 'menuTreeId'
        ,isJump: true //是否允许点击节点时弹出新窗口跳转
        ,edit: ['add', 'update', 'del'] //操作节点的图标
        ,onlyIconControl: true
        ,click: function(obj){
            var elem = obj.elem;
            $('.layui-tree-main').removeAttr('style');
            elem.find('.layui-tree-main').eq(0).attr('style','background-color:#009f95');
            var id = obj.data.id;  //获取当前点击的节点数据
            var resultData = core.ajax('/manager/findMenuById',false,'POST','id='+id);
            $('form').show();
            $('form').find('input').eq(0).val(resultData.name);
            $('form').find('input').eq(1).val(resultData.url);
            $('form').find('input').eq(2).val(resultData.type);
            $('form').find('input').eq(3).val(resultData.ismust);
            $('form').find('input').eq(4).val(resultData.sort);
            $('form').find('input').eq(5).val(resultData.createUser);
            $('form').find('input').eq(6).val(util.toDateString(resultData.createTime, "yyyy-MM-dd HH:mm:ss"));
            $('form').find('input').eq(7).val(resultData.updateUser);
            $('form').find('input').eq(8).val(util.toDateString(resultData.updateTime, "yyyy-MM-dd HH:mm:ss"));
        }
        ,operate: function(obj){
            var type = obj.type; //得到操作类型：add、edit、del
            var data = obj.data; //得到当前节点的数据
            var elem = obj.elem; //得到当前节点元素
            var url = '/manager/menuInput?id='+data.id+'&title='+data.title+'&type='+type//这里content是一个普通的String
            //Ajax 操作
            if(type === 'add'){ //增加节点
                if(data.level>=4){
                    layer.msg('菜单层级最大为4！',core.showtime);
                    reload();
                    return;
                }
                core.openIframeDialog('添加菜单',url,null,true,reload);
            } else{
                if(core.isEmpty(data.parentId)){
                    layer.msg('顶级节点不能操作！',core.showtime);
                    reload();
                    return;
                }
                if(type === 'update'){ //修改节点
                    data.title = elem.find('.layui-tree-txt').html();
                    core.openIframeDialog('修改菜单',url,null,true,reload);
                } else if(type === 'del'){ //删除节点
                    var resultData = core.ajax('/manager/deleteMenu',false,'POST','id='+data.id);
                    if(resultData!=false){
                        layer.msg('操作成功！',core.showtime);
                    }
                    reload();
                }
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