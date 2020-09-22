/**
 *穿梭框
 */
layui.use('core', function(){
    var $ = layui.jquery
        ,util = layui.util
        ,core = layui.core
        ,transfer = layui.transfer;

    //显示搜索框
    transfer.render({
        elem: '#auzCopy'
        ,data: core.ajax('/manager/auzCopyUsers',false,'GET',"userId="+$("button[lay-button='submitButton']").val())
        ,title: ['选择用户', '目标用户']
        ,showSearch: true
        ,id: 'auzCopy'
    });

    //按钮事件
    util.event('lay-button', {
        submitButton:function(){
            //获得右侧数据
            var checkUserData = transfer.getData('auzCopy');
            if(core.isEmpty(checkUserData)){
                parent.layer.msg('右边至少有一个用户！',core.showtime);
            }else{
                var resultData = core.ajax('/manager/auzCopyOpear',false,'POST',"userId="+$("button[lay-button='submitButton']").val()+"&checkUserData="+JSON.stringify(checkUserData));
                if(resultData!=false){
                    parent.layer.msg('操作成功',core.showtime);
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                }
            }
        }
    });
});