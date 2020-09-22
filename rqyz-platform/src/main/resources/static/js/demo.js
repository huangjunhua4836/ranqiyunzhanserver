//一般直接写在一个js文件中,加载layer常用模块
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;
    alert(form);
    layer.msg('Hello World');
});