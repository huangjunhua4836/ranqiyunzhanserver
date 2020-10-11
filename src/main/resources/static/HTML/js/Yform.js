// JavaScript Document
function yradio_for(){$("yradio input").each(function(index,element){var thisO=$(this);if(thisO.is(":checked")){thisO.parents("yradio").addClass("checked")}else{thisO.parents("yradio").removeClass("checked")}})};function ycheckbox_for(){$("ycheckbox input").each(function(index,element){var thisO=$(this);if(thisO.is(":checked")){thisO.parents("ycheckbox").addClass("checked")}else{thisO.parents("ycheckbox").removeClass("checked")}})};function selectrChagr(){$("yselect yval").each(function(index,element){var thisO=$(this);var ptshd=thisO.parents("yselect");var shitsds=thisO;ptshd.find("option").each(function(index,element){if($(this).val()==ptshd.find("select").val()){var thisfginds=shitsds.find("opct");var thisdsObj=thisfginds.length>0?thisfginds:shitsds;thisdsObj.text($(this).text());if(thisfginds&&index==0){var paletext=thisfginds.attr("text");var paleopct=thisfginds.attr("opcto");if(paletext){thisdsObj.text(paletext)}
thisfginds.css("opacity",paleopct)}else{thisfginds.css("opacity",1)}}})})};function IsPCform(){var userAgentInfo=navigator.userAgent;var Agents=new Array("Android","iPhone","SymbianOS","Windows Phone","iPad","iPod");var flag=true;for(var v=0;v<Agents.length;v++){if(userAgentInfo.indexOf(Agents[v])>0){flag=false;break}}
return flag};function creatEtg(tag){return document.createElement(tag);};
var Yform={init:function(){
		$("input.yradio").each(function(index,element){var thisO=$(this);var thisfindhtml=thisO.attr("data-html");thisO.removeAttr("data-html");var danuxna=creatEtg("yradio");danuxna=$(danuxna);thisO.after(danuxna);thisO.removeClass("yradio");if(thisO.attr("class")!=""){danuxna.attr("class",thisO.attr("class"))}
thisO.removeAttr("class");if(thisfindhtml){danuxna.append(thisfindhtml)}
danuxna.addClass("endclick");danuxna.append(thisO);});yradio_for();$("yradio.endclick input").click(function(e){if(e.stopPropagation){e.stopPropagation()}else{e.cancelBubble=true}}).change(yradio_for);$("yradio.endclick").click(function(){$(this).find("input").trigger("click");}).removeClass("endclick");
	////多选////
	$("input.ycheckbox").each(function(index,element){var thisO=$(this);var thisfindhtml=thisO.attr("data-html");thisO.removeAttr("data-html");var danuxna=creatEtg("ycheckbox");danuxna=$(danuxna);thisO.after(danuxna);thisO.removeClass("ycheckbox");if(thisO.attr("class")!=""){danuxna.attr("class",thisO.attr("class"))}
thisO.removeAttr("class");if(thisfindhtml){danuxna.append(thisfindhtml)}
$(danuxna).addClass("endclick");danuxna.append(thisO);});ycheckbox_for();$("ycheckbox.endclick input").click(function(e){if(e.stopPropagation){e.stopPropagation()}else{e.cancelBubble=true}}).change(ycheckbox_for);$("ycheckbox.endclick").click(function(){$(this).find("input").trigger("click");}).removeClass("endclick");
	/////下拉/////
	$("select.yselect").each(function(index,element){var thisO=$(this);var danuxna=creatEtg("yselect");danuxna=$(danuxna);thisO.after(danuxna);thisO.removeClass("yselect");if(thisO.attr("class")!=""){danuxna.attr("class",thisO.attr("class"))}
thisO.removeAttr("class");danuxna.addClass("endclick");danuxna.append(thisO);});$("yselect.endclick").each(function(index,element){var thisO=$(this);var danuxna=creatEtg("yval"),selectP=creatEtg("yselp");var yplaceholder=thisO.find("select").attr("yplaceholder");var opacitypld=thisO.find("select").attr("yplaceholder-opacity")||0.5;if(yplaceholder){var opct=creatEtg("opct");opct=$(opct);if(yplaceholder!=" "){opct.attr("text",yplaceholder)}
opct.attr("opcto",opacitypld);$(danuxna).append(opct)}
thisO.append(danuxna).append(selectP)});$("yselect.endclick yval").click(function(){var prentA=$(this).parents("yselect");if(prentA.hasClass("mobile")){return}
prentA.find("yselp").show();prentA.addClass("current-show");prentA.find("yselp").html("");prentA.find("option").each(function(index,element){var opchisd=creatEtg("yoption");prentA.find("yselp").append(opchisd);$(opchisd).text($(this).text());$(opchisd).click(function(){var pahsd=$(this).parents("yselect");var tetx=$(this).text();pahsd.find("option").each(function(index,element){if($(this).text()==tetx){pahsd.find("select").val($(this).val());pahsd.find("select").change()}});prentA.find("yselp").hide();prentA.removeClass("current-show")})})});$("yselect.endclick").removeClass("current-show").find("yselp").hide();selectrChagr();if(!IsPCform()){$("yselect.endclick").addClass("mobile")}
$("yselect.endclick select").change(selectrChagr);$("yselect.endclick").hover(function(){},function(){$(this).find("yoption").unbind("click").remove();$(this).find("yselp").hide();$(this).removeClass("current-show")}).removeClass("endclick");
}}
$(document).ready(function() {
jQuery.fn.outerHTML = function(s) {return (s) ? this.before(s).remove() : $("<Hill_man>").append(this.eq(0).clone()).html();}
Yform.init();
});

