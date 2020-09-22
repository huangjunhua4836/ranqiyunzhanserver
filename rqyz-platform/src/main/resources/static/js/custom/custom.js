/**
 * 扩展第一个模块
 */
layui.define(['layer', 'laypage'],function(exports){
    var obj = {
        hello: function(str){
            alert('Hello '+ (str||'我是自定义模块1，我依赖layer、laypage'));
        }
    };
    exports('custom1',obj);//custom1为模块的名称
});


/**
 扩展第二个模块
 **/
layui.define(function(exports){ //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
    var obj = {
        hello: function(str){
            alert('Hello '+ (str||'我是自定义模块2，我啥都不依赖'));
        }
    };

    //输出test接口
    exports('custom2', obj);//custom2为模块的名称
});