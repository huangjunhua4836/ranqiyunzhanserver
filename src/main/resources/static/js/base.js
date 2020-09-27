layui.config({
    dir: '/static/tools/layui/' //layui.js 所在路径（注意，如果是script单独引入layui.js，无需设定该参数。），一般情况下可以无视
    ,version: false //一般用于更新模块缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
    ,debug: false //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
    ,base: '/static/js/custom/' //设定扩展的Layui模块的所在目录，一般用于外部模块扩展
}).extend({ //设定模块别名
    custom1: 'custom' //custom1是模块的名称，custom是模块所在的js文件位置，这个位置相对于base路径设置的
    ,custom2: 'custom' //custom2是模块的名称，custom是模块所在的js文件位置，这个位置相对于base路径设置的
    // ,custom2: 'js/custom/custom' //如果base不设置路径则此处应该是js文件全部路径
    ,core: 'core'
})