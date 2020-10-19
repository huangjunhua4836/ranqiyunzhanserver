/*event,isPC,mobileBanner,mAlert*/
jQuery.easing.jswing = jQuery.easing.swing;
jQuery.extend(jQuery.easing, {
	def: "easeOutQuad",
	swing: function(e, f, a, h, g) {
		return jQuery.easing[jQuery.easing.def](e, f, a, h, g)
	},
	easeInQuad: function(e, f, a, h, g) {
		return h * (f /= g) * f + a
	},
	easeOutQuad: function(e, f, a, h, g) {
		return -h * (f /= g) * (f - 2) + a
	},
	easeInOutQuad: function(e, f, a, h, g) {
		if((f /= g / 2) < 1) {
			return h / 2 * f * f + a
		}
		return -h / 2 * (--f * (f - 2) - 1) + a
	},
	easeInCubic: function(e, f, a, h, g) {
		return h * (f /= g) * f * f + a
	},
	easeOutCubic: function(e, f, a, h, g) {
		return h * ((f = f / g - 1) * f * f + 1) + a
	},
	easeInOutCubic: function(e, f, a, h, g) {
		if((f /= g / 2) < 1) {
			return h / 2 * f * f * f + a
		}
		return h / 2 * ((f -= 2) * f * f + 2) + a
	},
	easeInQuart: function(e, f, a, h, g) {
		return h * (f /= g) * f * f * f + a
	},
	easeOutQuart: function(e, f, a, h, g) {
		return -h * ((f = f / g - 1) * f * f * f - 1) + a
	},
	easeInOutQuart: function(e, f, a, h, g) {
		if((f /= g / 2) < 1) {
			return h / 2 * f * f * f * f + a
		}
		return -h / 2 * ((f -= 2) * f * f * f - 2) + a
	},
	easeInQuint: function(e, f, a, h, g) {
		return h * (f /= g) * f * f * f * f + a
	},
	easeOutQuint: function(e, f, a, h, g) {
		return h * ((f = f / g - 1) * f * f * f * f + 1) + a
	},
	easeInOutQuint: function(e, f, a, h, g) {
		if((f /= g / 2) < 1) {
			return h / 2 * f * f * f * f * f + a
		}
		return h / 2 * ((f -= 2) * f * f * f * f + 2) + a
	},
	easeInSine: function(e, f, a, h, g) {
		return -h * Math.cos(f / g * (Math.PI / 2)) + h + a
	},
	easeOutSine: function(e, f, a, h, g) {
		return h * Math.sin(f / g * (Math.PI / 2)) + a
	},
	easeInOutSine: function(e, f, a, h, g) {
		return -h / 2 * (Math.cos(Math.PI * f / g) - 1) + a
	},
	easeInExpo: function(e, f, a, h, g) {
		return f == 0 ? a : h * Math.pow(2, 10 * (f / g - 1)) + a
	},
	easeOutExpo: function(e, f, a, h, g) {
		return f == g ? a + h : h * (-Math.pow(2, -10 * f / g) + 1) + a
	},
	easeInOutExpo: function(e, f, a, h, g) {
		if(f == 0) {
			return a
		}
		if(f == g) {
			return a + h
		}
		if((f /= g / 2) < 1) {
			return h / 2 * Math.pow(2, 10 * (f - 1)) + a
		}
		return h / 2 * (-Math.pow(2, -10 * --f) + 2) + a
	},
	easeInCirc: function(e, f, a, h, g) {
		return -h * (Math.sqrt(1 - (f /= g) * f) - 1) + a
	},
	easeOutCirc: function(e, f, a, h, g) {
		return h * Math.sqrt(1 - (f = f / g - 1) * f) + a
	},
	easeInOutCirc: function(e, f, a, h, g) {
		if((f /= g / 2) < 1) {
			return -h / 2 * (Math.sqrt(1 - f * f) - 1) + a
		}
		return h / 2 * (Math.sqrt(1 - (f -= 2) * f) + 1) + a
	},
	easeInElastic: function(f, h, e, l, k) {
		var i = 1.70158;
		var j = 0;
		var g = l;
		if(h == 0) {
			return e
		}
		if((h /= k) == 1) {
			return e + l
		}
		if(!j) {
			j = k * .3
		}
		if(g < Math.abs(l)) {
			g = l;
			var i = j / 4
		} else {
			var i = j / (2 * Math.PI) * Math.asin(l / g)
		}
		return -(g * Math.pow(2, 10 * (h -= 1)) * Math.sin((h * k - i) * 2 * Math.PI / j)) + e
	},
	easeOutElastic: function(f, h, e, l, k) {
		var i = 1.70158;
		var j = 0;
		var g = l;
		if(h == 0) {
			return e
		}
		if((h /= k) == 1) {
			return e + l
		}
		if(!j) {
			j = k * .3
		}
		if(g < Math.abs(l)) {
			g = l;
			var i = j / 4
		} else {
			var i = j / (2 * Math.PI) * Math.asin(l / g)
		}
		return g * Math.pow(2, -10 * h) * Math.sin((h * k - i) * 2 * Math.PI / j) + l + e
	},
	easeInOutElastic: function(f, h, e, l, k) {
		var i = 1.70158;
		var j = 0;
		var g = l;
		if(h == 0) {
			return e
		}
		if((h /= k / 2) == 2) {
			return e + l
		}
		if(!j) {
			j = k * .3 * 1.5
		}
		if(g < Math.abs(l)) {
			g = l;
			var i = j / 4
		} else {
			var i = j / (2 * Math.PI) * Math.asin(l / g)
		}
		if(h < 1) {
			return -.5 * g * Math.pow(2, 10 * (h -= 1)) * Math.sin((h * k - i) * 2 * Math.PI / j) + e
		}
		return g * Math.pow(2, -10 * (h -= 1)) * Math.sin((h * k - i) * 2 * Math.PI / j) * .5 + l + e
	},
	easeInBack: function(e, f, a, i, h, g) {
		if(g == undefined) {
			g = 1.70158
		}
		return i * (f /= h) * f * ((g + 1) * f - g) + a
	},
	easeOutBack: function(e, f, a, i, h, g) {
		if(g == undefined) {
			g = 1.70158
		}
		return i * ((f = f / h - 1) * f * ((g + 1) * f + g) + 1) + a
	},
	easeInOutBack: function(e, f, a, i, h, g) {
		if(g == undefined) {
			g = 1.70158
		}
		if((f /= h / 2) < 1) {
			return i / 2 * f * f * (((g *= 1.525) + 1) * f - g) + a
		}
		return i / 2 * ((f -= 2) * f * (((g *= 1.525) + 1) * f + g) + 2) + a
	},
	easeInBounce: function(e, f, a, h, g) {
		return h - jQuery.easing.easeOutBounce(e, g - f, 0, h, g) + a
	},
	easeOutBounce: function(e, f, a, h, g) {
		if((f /= g) < 1 / 2.75) {
			return h * 7.5625 * f * f + a
		} else {
			if(f < 2 / 2.75) {
				return h * (7.5625 * (f -= 1.5 / 2.75) * f + .75) + a
			} else {
				if(f < 2.5 / 2.75) {
					return h * (7.5625 * (f -= 2.25 / 2.75) * f + .9375) + a
				} else {
					return h * (7.5625 * (f -= 2.625 / 2.75) * f + .984375) + a
				}
			}
		}
	},
	easeInOutBounce: function(e, f, a, h, g) {
		if(f < g / 2) {
			return jQuery.easing.easeInBounce(e, f * 2, 0, h, g) * .5 + a
		}
		return jQuery.easing.easeOutBounce(e, f * 2 - g, 0, h, g) * .5 + h * .5 + a
	}
});
var Yeffect = {
	event: {
		start: function(dom, fn) {
			dom = $(dom).eq(0);
			if(!this.move.touchs) {
				this.move.touchs = {}
			};
			if(!this.start.ISpc) {
				this.start.ISpc = Yeffect.isPC()
			};
			var thiso = this;
			if(this.start.ISpc) {
				thiso.move.touchs = null;
				dom.mousedown(function(e) {
					thiso.move.touchs = e;
					fn.call(this, e)
				})
			} else {
				dom[0].addEventListener("touchstart", function(e) {
					var touchs = e.touches;
					var thiskey = Object.keys(touchs);
					var minlen = thiskey.length - 2;
					minlen = Math.max(minlen, 0);
					var thistouch = touchs[thiskey[minlen]];
					for(var o in touchs) {
						if(!thiso.move.touchs[thistouch.identifier]) {
							var datsA = {};
							var datsM = {};
							for(var p in thistouch) {
								datsA[p] = thistouch[p];
								datsM[p] = thistouch[p];
							};
							var newdtas = {
								dataS: datsA,
								dataM: datsM
							};
							thiso.move.touchs[thistouch.identifier] = JSON.parse(JSON.stringify(newdtas))
						}
					};
					thistouch.preventDefault = function() {
						e.preventDefault()
					};
					fn.bind(this)(thistouch, touchs)
				})
			}
		},
		move: function(dom, fn) {
			if(!this.move.touchs) {
				this.start(dom, function() {})
			};
			dom = $(dom).eq(0);
			var thiso = this;
			if(this.start.ISpc) {
				dom.mousemove(function(e) {
					if(thiso.move.touchs) {
						e.mousedown = true;
						e.deltaX = e.pageX - thiso.move.touchs.pageX;
						e.deltaY = e.pageY - thiso.move.touchs.pageY;
					} else {
						e.deltaX = 0;
						e.deltaY = 0;
						e.mousedown = false;
					};
					fn.call(this, e)
				})
			} else {
				dom[0].addEventListener("touchmove", function(e) {
					var touchs = e.touches;
					for(var o in touchs) {
						var idter = touchs[o].identifier;
						if(idter != undefined) {
							if(touchs[o].pageX != thiso.move.touchs[idter].dataM.pageX || touchs[o].pageY != thiso.move.touchs[idter].dataM.pageY) {
								thiso.move.touchs[idter].dataM.pageX = touchs[o].pageX;
								thiso.move.touchs[idter].dataM.pageY = touchs[o].pageY;
								var thistouch = touchs[o];
								thistouch.preventDefault = function() {
									e.preventDefault()
								};
								thistouch.deltaX = thiso.move.touchs[idter].dataM.pageX - thiso.move.touchs[idter].dataS.pageX;
								thistouch.deltaY = thiso.move.touchs[idter].dataM.pageY - thiso.move.touchs[idter].dataS.pageY;
								thiso.move.touchs[idter].deltaX = thistouch.deltaX;
								thiso.move.touchs[idter].deltaY = thistouch.deltaY;
								fn.bind(this)(thistouch, touchs)
							}
						};
					}
				})
			}
		},
		end: function(dom, fn) {
			if(!this.move.touchs) {
				this.start(dom, function() {})
			};
			dom = $(dom).eq(0);
			var thiso = this;
			if(this.start.ISpc) {
				dom.mouseup(function(e) {
					if(thiso.move.touchs) {
						e.deltaX = e.pageX - thiso.move.touchs.pageX;
						e.deltaY = e.pageY - thiso.move.touchs.pageY;
					} else {
						e.deltaX = 0;
						e.deltaY = 0;
					};
					e.mousedown = false;
					thiso.move.touchs = null;
					fn.call(dom[0], e)
				})
			} else {
				dom[0].addEventListener("touchend", function(e) {
					var touchs = e.touches;
					var curkey = [];
					for(var o in touchs) {
						if(touchs[o].identifier != undefined) {
							curkey.push(touchs[o].identifier + "")
						}
					};
					for(var t in thiso.move.touchs) {
						if(curkey.indexOf(t) < 0) {
							var touchtT = thiso.move.touchs[t];
							if(!touchtT.deltaY && touchtT.deltaY != 0) {
								touchtT.deltaX = 0;
								touchtT.deltaY = 0;
							};
							fn.bind(this)(touchtT);
							delete thiso.move.touchs[t];
						}
					};
				})
			}
		}
	},
	isPC: function() {
		var userAgentInfo = navigator.userAgent;
		var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");
		var flag = true;
		for(var v = 0; v < Agents.length; v++) {
			if(userAgentInfo.indexOf(Agents[v]) > -1) {
				flag = false;
				break
			}
		}
		return flag
	},
	mobileBanner: function(obj1, obj2, num, btnL, btnR, css, time, aniTimer, easingstr) {
		num = num || "";
		btnL = btnL || "";
		btnR = btnR || "";
		css = css || "current";
		time = time || 5e3;
		aniTimer = aniTimer || 300;
		easingstr = easingstr || "linear";
		var obj1ul = $(obj1);
		var obj2li = $(obj2);
		var numdian = $(num);
		if(obj1ul.length < 1) return;
		if(obj2li.length < 2) return;
		var domObj = obj1ul.eq(0);
		obj1ul.css("width", "100%");
		obj2li.css({
			position: "absolute",
			top: 0,
			left: "100%",
			width: "100%"
		});
		var ulweidth = obj1ul.width();
		var liliength = obj2li.length;
		var curptins = 0;
		var setIntervalsAAA;
		var panstPotinX = 0;
		var starTime = 0;
		var isDragendeanli = true;
		obj2li.eq(curptins).css({
			left: 0
		});
		numdian.removeClass(css).eq(curptins).addClass(css);
		obj2li.removeClass(css).eq(curptins).addClass(css);
		numdian.click(function() {
			var curindex = numdian.index(this);
			if(curindex != curptins) {
				animatgundxz(curindex, true)
			}
		});
		$(btnL).click(function() {
			if(curptins - 1 < 0) {
				animatgundxz(liliength - 1, false)
			} else {
				animatgundxz(curptins - 1, false)
			}
		});
		$(btnR).click(function() {
			if(curptins + 1 > liliength - 1) {
				animatgundxz(0, true)
			} else {
				animatgundxz(curptins + 1, true)
			}
		});
		this.event.start(domObj, function(ev) {
			if(!isDragendeanli) return;
			panstPotinX = 0;
			starTime = new Date().getTime()
		});

		function animateEndfunction() {
			isDragendeanli = true
		};
		this.event.end(domObj, function(ev) {
			if(!isDragendeanli) {
				setTimeout(animateEndfunction, aniTimer);
				return;
			};
			isDragendeanli = false;
			var thstates = new Date().getTime();
			var chazhis = thstates - starTime;
			if(chazhis < 300) {
				if(panstPotinX < 40 && panstPotinX > 0) {
					obj2li.eq(curptins).stop().animate({
						left: 100 + "%"
					}, aniTimer, easingstr, animateEndfunction).css("z-index", 10);
					obj2li.eq(getPrveInt()).stop().animate({
						left: 0 + "%"
					}, aniTimer, easingstr, animateEndfunction).css("z-index", 5);
					if(liliength > 2) {
						obj2li.eq(getNextInt()).stop().animate({
							left: -100 + "%"
						}, aniTimer, easingstr, animateEndfunction).css("z-index", 0)
					};
					curptins--
				};
				if(panstPotinX > -40 && panstPotinX < 0) {
					obj2li.eq(curptins).stop().animate({
						left: -100 + "%"
					}, aniTimer, easingstr, animateEndfunction).css("z-index", 10);
					obj2li.eq(getNextInt()).stop().animate({
						left: 0 + "%"
					}, aniTimer, easingstr, animateEndfunction).css("z-index", 5);
					if(liliength > 2) {
						obj2li.eq(getPrveInt()).stop().animate({
							left: 100 + "%"
						}, aniTimer, easingstr, animateEndfunction).css("z-index", 0)
					};
					curptins++
				}
			} else {
				obj2li.eq(curptins).stop().animate({
					left: 0 + "%"
				}, aniTimer, easingstr, animateEndfunction).css("z-index", 10);
				if(panstPotinX > 0) {
					obj2li.eq(getPrveInt()).stop().animate({
						left: -100 + "%"
					}, aniTimer, easingstr, animateEndfunction).css("z-index", 5);
					if(liliength > 2) {
						obj2li.eq(getNextInt()).stop().animate({
							left: 100 + "%"
						}, aniTimer, easingstr, animateEndfunction).css("z-index", 0)
					}
				} else {
					obj2li.eq(getNextInt()).stop().animate({
						left: 100 + "%"
					}, aniTimer, easingstr, animateEndfunction).css("z-index", 5);
					if(liliength > 2) {
						obj2li.eq(getPrveInt()).stop().animate({
							left: -100 + "%"
						}, aniTimer, easingstr, animateEndfunction).css("z-index", 0)
					}
				};
			}
			if(panstPotinX > 40) {
				obj2li.eq(curptins).stop().animate({
					left: 100 + "%"
				}, aniTimer, easingstr, animateEndfunction).css("z-index", 10);
				obj2li.eq(getPrveInt()).stop().animate({
					left: 0 + "%"
				}, aniTimer, easingstr, animateEndfunction).css("z-index", 5);
				if(liliength > 2) {
					obj2li.eq(getNextInt()).stop().animate({
						left: -100 + "%"
					}, aniTimer, easingstr, animateEndfunction).css("z-index", 0)
				};
				curptins--
			};
			if(panstPotinX < -40) {
				obj2li.eq(curptins).stop().animate({
					left: -100 + "%"
				}, aniTimer, easingstr, animateEndfunction).css("z-index", 10);
				obj2li.eq(getNextInt()).stop().animate({
					left: 0 + "%"
				}, aniTimer, easingstr, animateEndfunction).css("z-index", 5);
				if(liliength > 2) {
					obj2li.eq(getPrveInt()).stop().animate({
						left: 100 + "%"
					}, aniTimer, easingstr, animateEndfunction).css("z-index", 0)
				};
				curptins++
			};
			setcurptinszhi();
			panstPotinX = 0
		});

		function setcurptinszhi() {
			if(curptins < 0) {
				curptins = liliength - 1
			};
			if(curptins > liliength - 1) {
				curptins = 0
			};
			numdian.removeClass(css).eq(curptins).addClass(css);
			obj2li.removeClass(css).eq(curptins).addClass(css)
		};
		this.event.move(domObj, function(ev) {
			if(Math.abs(ev.deltaY) > Math.abs(ev.deltaX) * 5) {
				return;
			} else {
				ev.preventDefault()
			};
			if(!isDragendeanli) return;
			clearInterval(setIntervalsAAA);
			setIntervalsAAA = setInterval(functionTimegd, time);
			var deltaXz = ev.deltaX;
			var baifenbuzh = deltaXz / ulweidth * 100;
			panstPotinX = baifenbuzh;
			obj2li.eq(curptins).stop().css({
				left: baifenbuzh + "%"
			});
			if(deltaXz > 0) {
				obj2li.eq(getPrveInt()).stop().css({
					left: baifenbuzh - 100 + "%"
				});
				if(liliength > 2) {
					obj2li.eq(getNextInt()).stop().css({
						left: baifenbuzh + 100 + "%"
					})
				}
			} else {
				obj2li.eq(getNextInt()).stop().css({
					left: baifenbuzh + 100 + "%"
				});
				if(liliength > 2) {
					obj2li.eq(getPrveInt()).stop().css({
						left: baifenbuzh - 100 + "%"
					})
				}
			};
		});

		function getPrveInt() {
			var PrveInt = curptins - 1;
			if(PrveInt < 0) {
				PrveInt = liliength - 1
			};
			return PrveInt
		};

		function getNextInt() {
			var NextInt = curptins + 1;
			if(NextInt > liliength - 1) {
				NextInt = 0
			};
			return NextInt
		};
		setIntervalsAAA = setInterval(functionTimegd, time);

		function functionTimegd() {
			if(curptins + 1 > liliength - 1) {
				animatgundxz(0, true)
			} else {
				animatgundxz(curptins + 1, true)
			}
		};

		function animatgundxz(cuet, drect) {
			clearInterval(setIntervalsAAA);
			setIntervalsAAA = setInterval(functionTimegd, time);
			obj2li.stop().css({
				left: "100%"
			});
			if(drect) {
				obj2li.eq(cuet).stop().css({
					left: "100%"
				});
				obj2li.eq(cuet).animate({
					left: 0
				}, aniTimer, easingstr, animateEndfunction);
				obj2li.eq(curptins).stop().css({
					left: "0%"
				});
				obj2li.eq(curptins).animate({
					left: "-100%"
				}, aniTimer, easingstr, animateEndfunction)
			} else {
				obj2li.eq(cuet).css({
					left: "-100%"
				}).animate({
					left: 0
				}, aniTimer, easingstr, animateEndfunction);
				obj2li.eq(curptins).css({
					left: "0%"
				}).animate({
					left: "100%"
				}, aniTimer, easingstr, animateEndfunction)
			};
			curptins = cuet;
			numdian.removeClass(css).eq(curptins).addClass(css);
			obj2li.removeClass(css).eq(curptins).addClass(css)
		};
		$(window).resize(function() {
			obj2li.stop().css({
				left: "100%"
			});
			obj2li.eq(curptins).stop().css({
				left: 0
			});
			ulweidth = obj1ul.width()
		})
	},
	mAlert: function(text, time, isscroll) {
		var htmls = $("html");
		var mAlertClass = $(".mAlertClass");
		if(mAlertClass.length > 0) mAlertClass.remove();
		time = time || 3e3;
		isscroll = isscroll || false;
		var divalert = $("<div class='mAlertClass' style='position:fixed; width:100%; height:100%; z-index:10000; left:0px; top:0px;'><div id='malertbox' style='font-size:13px; color:#fff; background:rgba(0,0,0,0.6); line-height:40px; text-align:center; overflow:hidden; left:50%; top:50%; margin-left:-150px; padding:0 30px; margin-top:-20px; position:absolute; border-radius:5px;'>" + text + "</div></div>");
		$("body").append(divalert);
		var mbox=document.getElementById("malertbox");
		mbox.style.marginLeft=(-divalert[0].children[0].offsetWidth*0.5)+"px";

		function removets() {
			divalert.remove();
			htmls.css({
				borderRight: 0,
				overflowY: "visible"
			})
		};
		if(!isscroll) {
			if(window["addEventListener"]) {
				divalert[0].addEventListener("touchstart", function(e) {
					e.preventDefault()
				})
			};
			var divapen = $("<div></div>");
			var divapenn = $("<div></div>");
			divapen.css({
				clear: "both",
				width: 100,
				height: 100,
				overflowY: "scroll",
				position: "absolute",
				top: 0,
				left: 0
			});
			divapen.append(divapenn);
			$("body").append(divapen);
			htmls.css({
				overflow: "hidden",
				borderRight: divapen.width() - divapenn.width() + "px solid #eee"
			});
			divapen.remove()
		};
		setTimeout(removets, time);
		return removets
	}
};