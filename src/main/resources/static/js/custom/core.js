/**
 * layer核心模块预加载
 */
layui.define(['tree','layer','util','jquery','form','table','element','laydate','transfer','upload'], function(exports){
    var $ = layui.jquery;
    var obj = {
        showtime : {time:3000},//提示层显示时间
        ajax: function(url,async,type,data){
            var resultData = false;//初始化返回数据
            var index;
            $.ajax({
                url:url,
                async:async,
                type:type,
                // contentType:'application/json',
                data:data,
                dataType:'json',
                timeout:'5000',
                beforeSend:function(xhr){
                    index = layer.load(1); //风格1的加载
                },
                success:function(result,status,xhr){
                    if(result.code != 200){
                        layer.msg('接口访问成功，但是返回错误！状态：'+ status + '<br>错误信息：' + JSON.stringify(result),obj.showtime);
                        return;
                    }
                    resultData = result.data;
                },
                error:function (xhr,status,error) {
                    layer.msg('接口访问失败！状态：'+ status + '<br>错误信息：' + JSON.stringify(error),obj.showtime);
                },
                complete:function(xhr,status){
                    layer.close(index);
        }
            });
            return resultData;
        },
        isEmpty: function(str){
            if(str == undefined || str == '' || str == 'undefined'
                || str == null || str == 'null'){
                return true;
            }else{
                return false;
            }
        },
        openIframeDialog: function(title,url,area,isBtn2UseLastfun,lastfun){
            var lastfunFlag = true;//回调函数执行标志
            layer.open({
                type: 2
                ,title:title
                ,content:url
                ,area: area==null?['500px', '400px']:area
                ,btn: ['确定', '取消'] //可以无限个按钮
                ,closeBtn: 0 //关闭右上角的按钮
                ,yes: function(index, layero){
                    var button = layer.getChildFrame('button:last', index);
                    button.click();
                }
                ,btn2: function(index, layero){
                    //按钮【按钮二】的回调
                    //return false 开启该代码可禁止点击该按钮关闭
                    if(!isBtn2UseLastfun){//最后关闭弹出框是否使用回调函数,通过取消按钮设置
                        lastfunFlag = false;//取消回调函数执行
                    }
                }
                ,end : function(){
                    if(lastfunFlag && lastfun != null){//判断标志是否执行回调函数
                        lastfun();
                    }
                }
            });
        }
        ,openDialog: function(title,content,area){
            layer.open({
                type: 0
                ,title:title
                ,content:content
                ,area: area==null?['500px', '400px']:area
                ,btn: ['确定'] //可以无限个按钮
                ,closeBtn: 0 //关闭右上角的按钮
                ,yes: function(index, layero){
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }
            });
        }
    };
    exports('core',obj);//core为模块的名称
});