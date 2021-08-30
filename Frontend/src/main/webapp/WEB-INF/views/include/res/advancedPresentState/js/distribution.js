$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="advancedPresentState"]').addClass("active");
	
	setTrackerBatch();
});

function setTrackerBatch(){
	$.ajax({
		type:"GET",
		url	: _cp + '/tracker/getTrackerDataList',
		success:function(data) {
			console.log(data);
			
			makeTrackerTable(data.length);
			makeTrackerRec(data);
			
		}, error:function(error) {
			
		}
	});
}

function makeTrackerRec(data){
	/*var lngList = data.slice(),
		latList = data.slice();
	
	var d_limit = 5;
	
	var sort = function(v1, v2, v3, v4){
		if(v1== v2){
			return v3.localeCompare(v4);
		}
		else if(v1 < v2) return -1;
		else return 1;
	}
	
	lngList.sort(function(t1, t2){
		t1.loc_lng = parseFloat( parseFloat(t1.loc_lng).toFixed(d_limit) );
		t2.loc_lng = parseFloat( parseFloat(t2.loc_lng).toFixed(d_limit) );
		
		return sort(
			t1.loc_lng, t2.loc_lng,
			t1.tracker_id, t2.tracker_id
		);
	});
	latList.sort(function(t1, t2){
		t1.loc_lat = parseFloat( parseFloat(t1.loc_lat).toFixed(d_limit) );
		t2.loc_lat = parseFloat( parseFloat(t2.loc_lat).toFixed(d_limit) );
		
		return sort(
			t1.loc_lat, t2.loc_lat,
			t1.tracker_id, t2.tracker_id
		);
	});
	
	var margin = 10;
	var padding = 20;
	var width = 60 + margin, height = 60 + margin;
	var idx = -1, temp;
	lngList.forEach(function(tracker){
		if(tracker.loc_lng != temp){
			idx++;
			temp = tracker.loc_lng;
		}
		tracker.x = idx * width + padding;
	});
	idx = -1;
	latList.forEach(function(tracker){
		if(tracker.loc_lat != temp){
			idx++;
			temp = tracker.loc_lat;
		}
		tracker.y = idx * height + padding;
	});
	
	$(".trackerRec").css({'width': lngList[lngList.length-1].x + width + margin, 'height': latList[latList.length-1].y + height + margin});
	
	data.forEach(function(tracker){
		$(".trackerRec").append(trackerInfoDiv(tracker));
	});*/
	
	batchTrackerTable(data);
}

function makeTrackerTable(size){
//	var x_size = y_size = 50;
	size = size || 20;
	var x_size = size;
	var y_size = size;
	
	var table = document.createElement('table');
	$(table).addClass('trackerTable');
	$(".trackerTableRec").append(table);
	$(table).css('width', '100%');
	
	
//	ondrop="onDrop(event)" ondragover="onDrag(event)"
	for(var i=0; i<y_size; i++){
		var tr = document.createElement('tr');
		for(var j=0; j<x_size; j++){
			
			var td = document.createElement('td');
			
			$(td).attr('ondrop', 'onDrop(event)');
			$(td).attr('ondragover', 'onDrag(event)');
			
			$(tr).append(td);
			
			/*var th = document.createElement('th');
			
			$(tr).attr('ondrop', 'onDrop(event)');
			$(tr).attr('ondragover', 'onDrag(event)');
			
			$(tr).append(th);*/
			
		}
		$(table).append(tr);
	}

	
	console.log($(table));
	
}

function batchTrackerTable(data){
	console.log("batchTrackerTable", data);
	
	var $table = $(".trackerRec table")[0];
	
	for(var i=0; i<data.length; i++){
		var tracker = data[i];
		var x_position = tracker.site_x;
		var y_position = tracker.site_y;
		var trackerDiv = trackerInfoDiv(tracker);
		
//		좌표 null 일경우 -1로 들어옴
		if(x_position < 0 || y_position < 0 ){
			insertTrackerToDefaultTable(trackerDiv, 0, 0);
			continue;
		};
		
		var tableTh = $table.rows[y_position].cells[x_position];
		$(tableTh).append(trackerDiv);
	}
	
	
	function insertTrackerToDefaultTable(trackerDiv, x_position, y_position){
		var tableTh = $table.rows[y_position].cells[x_position];
		if(!tableTh) {
			insertTrackerToDefaultTable(trackerDiv, 0, y_position+1);
			return;
		}
		
//		x,y 좌표가 -1일경우 순서대로 삽입
		if($(tableTh).find(".trackerBox").length > 0){
			var x_limit = $table.rows[y_position].cells.length;
			if(x_limit > x_position){
				insertTrackerToDefaultTable(trackerDiv, x_position + 1, y_position);
			}else{
				insertTrackerToDefaultTable(trackerDiv, x_position, y_position +1);
			}
			
			return;
		}
		
		$(tableTh).append(trackerDiv);
		
	}
}

function trackerInfoDiv(tracker){
	var r,g,b;
	
	var instant_energy = tracker.instant_energy || 0;
	
	var average = instant_energy / tracker.capacity;
	var aveEnergy = (instant_energy / tracker.capacity) * 100;
	
	var standard = 50;
	if(aveEnergy > standard){
		r = 255;
		g = 255 - (255 * ((aveEnergy-51)/standard));
		b = 0;
	}else{
		r = 255 * (average*2);
		g = 127 + (510 * average);
		b = 255 - r;
	}
	
	var color;
	if(aveEnergy <= 10){
		color = 'white';
	}else if ( aveEnergy >= 80){
		color = 'white';
	}
	
	r = Math.floor(r);
	g = Math.floor(g);
	b = Math.floor(b);
	tracker.energy = tracker.energy || 0;
	
	
	
	var htmlTag = 
			'<div class="trackerBox" style="left:'+tracker.x+'px; top:'+tracker.y+'px;" >'
		+	'<div class="energyLevel" style="background-color: rgb('+r+','+g+','+b+')">'
		+	'<p class="trackerId">'+tracker.tracker_id+'</p>'
		+	'<p class="accuEnergy" style="color:'+color+'">'+instant_energy+' kW</p>'
		+	'</div></div>';
	
	return htmlTag;
	
}