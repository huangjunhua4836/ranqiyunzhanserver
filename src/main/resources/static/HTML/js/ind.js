document.documentElement.style.fontSize = document.documentElement.clientWidth / 7.5 + "px";



var hUrl="http://rqyz.plf.yl-mall.cn";//接口域名
//var hUrl="http://dysm.yl-mall.cn";//接口域名
//var hUrl="http://bjgzh.yl-mall.cn";

var de=decodeURIComponent;//解码////
var en=encodeURIComponent; ///编码///


//网络请求
function call_net(astype, url,data, successCall, eCall) {
	$.ajax({
		type: astype,
		data: data,
		url:hUrl+url,
		success: function(msg){
			var msgs=typeof msg;
			if(msgs=="string"){
				successCall(JSON.parse(msg));
			}else{
				successCall(msg);
			}
			
		},
		fail:function(msg){
			if(typeof msg=="string"){
				eCall(JSON.parse(msg))
			}else{
				eCall(msg)
			}
			
		}
	})
}


//获取url中携带的参数
function getUrlParamURLys(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = de(window.location.search).substr(1).match(reg);  //匹配目标参数
    if (r!=null){
        return unescape(r[2]);
    }
    return false; //返回参数值
}





//拓展数组函数
Array.prototype.indexOf = function(val) {
for (var i = 0; i < this.length; i++) {
if (this[i] == val) return i;
}
return -1;
};

Array.prototype.remove = function(val) {
var index = this.indexOf(val);
if (index > -1) {
this.splice(index, 1);
}
};


//纯数字验证
function textNumFn(obj){
	var txtRes = /^[0-9]+$/;
	if(!txtRes.test($(obj).val())){
		$(obj).val("");
	}
}

//非0纯数字验证
function textNumFnB(obj){
	var txtRes = /^[1-9]+$/;
	if(!txtRes.test($(obj).val())){
		$(obj).val("");
	}
}

			

//手机号码验证
function checkPhone(ph){ 
	var phone = document.getElementById(ph).value;
	if(!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(phone))){ 
		//Yeffect.mAlert("手机号码有误，请重填");  
		return false; 
	}else{
		return true;	
	} 
}

//设备判断
function IsAndroid()
{
	var userAgentInfo = navigator.userAgent;
	var Agents = new Array("iPhone", "iPad", "iPod");
	//alert(userAgentInfo)
	var flag = true;
	for (var v = 0; v < Agents.length; v++) {
		if (userAgentInfo.indexOf(Agents[v]) > 0) { flag = false; break; }
	}
	return flag;
	
}


//邮箱验证
function checkMail(id){
　　var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
　　var obj = document.getElementById(id); //要验证的对象
　　if(obj.value === ""){ //输入不能为空
　　　　alert("输入不能为空!");
　　　　return false;
　　}else if(!reg.test(obj.value)){ //正则验证不通过，格式不对
　　　　alert("请输入正确的邮箱!");
　　　　return false;
　　}else{
　　　　return true;
　　}
}

