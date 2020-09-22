/**
 * 加载自定义模块
 */
layui.use(['custom1','custom2'], function(){
    var custom1 = layui.custom1;
    var custom2 = layui.custom2;

    var layer = layui.layer;
    layer.msg("自定义custom1当中layer依赖模块启用成功！");

    custom1.hello("custom1");
    custom2.hello("custom2");
});