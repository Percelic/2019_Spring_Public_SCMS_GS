+function(){
	String.prototype.lpad = function(len, pad) {
		if(!pad){
			return this;
		}
		
		var str = ''+this;
		while(str.length < len){
			str = pad + str;
		}
		return str;
	}
	
	String.prototype.numberFormat = function() {
		var str = ''+this;
		return str.split( /(?=(?:\d{3})+(?:\.|$))/g ).join( "," );
	}
	
	Number.prototype.format = function () {
	    return this.toString().numberFormat();
	};
	
	Date.prototype.format = function(f) {
		if (!this.valueOf()) return " ";
		 
		// Thu Jun 30 2016 23:41:43 GMT+0900
		//var monthName = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec"];
		//var weekName = ["Sun", "Mon", "Tue", "Wen", "Thu", "Fri", "Sat"];
		var d = this;
		 
		return f.replace(/(yyyy|yy|MMM|MM|dd|EEE|E|hh|mm|ss|a\/p)/gi, function($1) {
		    switch ($1) {
		        case "yyyy": return d.getFullYear();
		        case "yy": return (''+(d.getFullYear() % 1000)).lpad(2, '0');
		        case "MM": return (''+(d.getMonth() + 1)).lpad(2, '0');
		        case "MMM":
		        	return ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec"][d.getMonth()];
		        case "dd": return (''+d.getDate()).lpad(2, '0');
		        case "E":
		        case "EEE":
		        	return ["Sun", "Mon", "Tue", "Wen", "Thu", "Fri", "Sat"][d.getDay()];
		        case "F": return d.getDay();
		        case "HH": return (''+d.getHours()).lpad(2, '0');
		        case "hh": return (''+((h = d.getHours() % 12) ? h : 12)).lpad(2, '0');
		        case "mm": return (''+d.getMinutes()).lpad(2, '0');
		        case "ss": return (''+d.getSeconds()).lpad(2, '0');
		        case "a/p": return d.getHours() < 12 ? "오전" : "오후";
		        default: return $1;
		    }
		});
	};
	
	Date.prototype.addMonth = function(n) {
		this.setMonth(this.getMonth() + n);
		return this;
	}
	Date.prototype.addDate = function(n) {
		this.setDate(this.getDate() + n);
		return this;
	}
	
	// Element.prototype
	//(function(){
		if (!Element.prototype.matches){
			Element.prototype.matches =
			Element.prototype.msMatchesSelector ||
			Element.prototype.webkitMatchesSelector;
		}
		if (!Element.prototype.closest){
			Element.prototype.closest = function(s) {
				var el = this;
				if (!document.documentElement.contains(el))
					return null;
				do {
					if (el.matches(s))
						return el;
					el = el.parentElement || el.parentNode;
				} while (el);
				return null;
			};
		}
	//}());

	if(window.$ && window.$.ajax){
		var oriAjax = $.ajax;
		$.ajax = function(){
			//_csrf: $('[name="_csrf"]').val(),
			if(arguments.length > 0 && (arguments[0].type==='post' || arguments[0].type==='POST')){
				if(!arguments[0].data._csrf){
					arguments[0].data._csrf = $('[name="_csrf"]').val();
				}
			}
			return oriAjax.apply($, arguments);
		}
	}
	
	jQuery.fn.sort = function()
	{
	    return this.pushStack([].sort.apply(this, arguments), []);
	};
	
	jQuery.fn.sortOptions = function(sortCallback)
	{
	    jQuery('option', this)
	        .sort(sortCallback)
	        .appendTo(this);
	    return this;
	};
	
	jQuery.fn.sortOptionsBy = function(getValueCallback)
	{
	    var sortCallback = function(x, y)
	    {
	    	var xValue = getValueCallback(x);
	    	var yValue = getValueCallback(y);
	    	return (xValue < yValue) ? -1 : (xValue > yValue) ? 1 : 0;
	    };
	};
	
	jQuery.fn.sortOptionsByText = function()
	{
	    return this.sortOptionsBy(function(opt)
	    {
	        return jQuery(opt).text().toUpperCase();
	    });
	};
	
	jQuery.fn.sortOptionsByValue = function()
	{
	    return this.sortOptionsBy(function(opt)
	    {
	        return jQuery(opt).val();
	    });
	};
	
	jQuery.fn.sortOptionsByParsedValue = function()
	{
	    return this.sortOptionsBy(function(opt)
	    {
	        return parseFloat(jQuery(opt).val());
	    });
	};
	
	// c3, d3 custom code
	(function(){
		if(!window.c3 || !window.d3){
			return;
		}
		
		var native = c3.generate.bind(c3);
		c3.generate = function(params){
			var chart;
			if(params.legend && params.legend.grmCustom){
				var legend = params.legend.grmCustom;
				if(typeof legend === 'boolean'){
					legend = {};
				}
				
				params.legend = {
					show: false
				}
				
				chart = native(params);
				
				if(!params.data.colors){
					return;
				}
				
				d3.select(chart.element)
				.insert('div', '.chart')
					.attr('class', 'c-legend-layer')
					.style(legend.layerStyle ? legend.layerStyle : {})
				.selectAll('span')
				.data( Object.keys(params.data.colors) )
				.enter()
				.append('div').attr('class', 'c-legend')
				.attr('data-id', function(id) {
					return id;
				})
				.html(function(id) {
					var $label = $('<label>');
					
					$label
					.append(
						$('<input type="checkbox" checked>')
					)
					.append(
						$('<div class="c-legend-dot">')
					)
					.append(id)
					;
					
					return $('<div>').append( $label ).html();
				})
				.each(function(id) {
					d3.select(this).select('.c-legend-dot')
					.style('background-color', chart.color(id));
				})
				.on('mouseover', function(id) {
					chart.focus(id);
				}).on('mouseout', function(id) {
					chart.revert();
				}).on('click', function(id) {
					// ie 에서 이상작동 케이스 있음
					//chart.toggle(id);
					
					if(this.querySelector('input').checked){
						chart.show(id);
					}else{
						chart.hide(id);
					}
				});
			}else{
				chart = native(params);
			}
			return chart;
		}

	}());
	
	// jquery date picker
	$(function(){
		var agent = navigator.userAgent.toLowerCase();
		// ie
		if ( (navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1) ) {
			$('input[type="date"]')
			//.attr('type', 'text')
			.datepicker({
				dateFormat: "yy-mm-dd",
				showButtonPanel: false,
				closeText: '닫기', // 닫기 버튼 텍스트 변경
				currentText: '오늘', // 오늘 텍스트 변경
				monthNames: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'],
				monthNamesShort: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'],
				dayNames: ['월요일','화요일','수요일','목요일','금요일','토요일','일요일'],
				dayNamesShort: ['월','화','수','목','금','토','일'],
				dayNamesMin: ['월','화','수','목','금','토','일'],
			});
		}
	});
}();