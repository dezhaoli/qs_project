//自适应屏幕
			(function (doc, win) {
			    var docEl = doc.documentElement,
			        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
			        recalc = function () {
			            var clientWidth = docEl.clientWidth;
//			            if (!clientWidth) return;
//			            	docEl.style.fontSize =36 * (clientWidth / 360) + 'px'; 
			            if (clientWidth >= 750) {
							docEl.style.fontSize = '75px';
						} else {
							docEl.style.fontSize = 36 * (clientWidth / 360) + 'px';
						}	
			        };
			    if (!doc.addEventListener) return;
			    win.addEventListener(resizeEvt, recalc, false);
			    doc.addEventListener('DOMContentLoaded', recalc, false);
			})(document, window);