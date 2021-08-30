
$(document).ready(function(){
	var arr = projection.translate(),
	arr2 = projection.translate(),
	scale = projection.scale();
  
	arr[0] = (arr[0] * 0.6)-75;
	arr[1] = (arr[1] * 0.6)-10;
	scale = scale * 0.6;
	
	projection.translate(arr).scale(scale);
	states.selectAll("path").attr("d", path);
});

var width = 300,
    height = 420,
    initialScale = 5500,
    initialX = -11900,
    initialY = 4050,
    centered,
    labels;

var projection = d3.geo.mercator()
    .scale(initialScale)
    .translate([initialX, initialY]);

var path = d3.geo.path()
    .projection(projection);

var zoom = d3.behavior.zoom()
    .translate(projection.translate())
    .scale(projection.scale())
    .scaleExtent([height, 800 * height])
    .on("zoom", zoom);

var svg = d3.select("#container").append("svg")
    .attr("width", width)
    .attr("height", height)
    .attr('id', 'map');

var states = svg.append("g")
    .attr("id", "states")
    .call(zoom);

states.append("rect")
    .attr("class", "background")
    .attr("width", width)
    .attr("height", height);

d3.json(_cp + "/resources/res/auth/json/korea.json", function(json) {
  states.selectAll("path")
      .data(json.features)
      .enter().append("path")
      .attr("d", path)
      .attr("id", function(d) { return 'path-'+d.id; })
      .attr("data-area", function(d) { return d.id; })
      .on("click", click);
      
  labels = states.selectAll("text")
    .data(json.features)
    .enter().append("text")
      .attr("transform", labelsTransform)
      .attr("id", function(d) { return 'label-'+d.id; })
      .attr("data-area", function(d) { return d.id; })
      .attr('text-anchor', 'middle')
      .attr("dy", ".35em")
      .attr("class", "map_text")
      .on("click", click)
      .text(function(d) { return d.properties.Name + ' (0)'; });
  
  	var groupArr = new Array();
	$.ajax({
		type:"GET",
		url	: _cp + '/group/trackerGroupAreaCntList',
		data: {},
		async:false,
		success:function(data) {
			groupArr = data;
			console.log('# group area data :', data);
		}, error:function(error) {
			
		}
	});
	
	for(var i=0; i<groupArr.length; i++) {
		var areaCode = groupArr[i].area_code;
		var areaCnt = groupArr[i].cnt;
		var $mapLable = $('#label-'+ areaCode);
		
		//console.log('# g data :', groupArr[i], $mapLable[0]);
		
		if($mapLable.length === 0){
			console.warn('$mapLable was not found :', '#label-'+ areaCode);
			continue;
		}
		
		var htmlStr = $mapLable.text() || '';
		$mapLable.text(
			htmlStr
			.substring(
				0,
				htmlStr.length-4
			)
			+ " (" + areaCnt + ")"
		);
//		mapLable.html(mapLable.html().substring(0, mapLable.html().length-4) + " (" + areaCnt + ")");
	}

	var pagingForm = $('#pagingForm');
	var areaCode = pagingForm.find('input[name="areaCode"]').val();
	$('#path-' + areaCode).attr("class", 'active');
  
});

function click(d) {
  var x, y, k;

  if (d && centered !== d) {
    var centroid = path.centroid(d);
    x = centroid[0];
    y = centroid[1];
    k = 4;
    centered = d;
  } else {
    x = width / 2;
    y = height / 2;
    k = 1;
    centered = null;
  }

  states.selectAll("path")
      .classed("active", centered && function(d) { return d === centered; });

  states.transition()
      .duration(1000)
//      .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")scale(" + k + ")translate(" + -x + "," + -y + ")")
      .style("stroke-width", 1.5 / k + "px");
  
  statesSelect($(this));
}

function labelsTransform(d) {
  // 경기도가 서울특별시와 겹쳐서 예외 처리
  if (d.id == 8) {
    var arr = path.centroid(d);
    arr[1] += (d3.event && d3.event.scale) ? (d3.event.scale / height + 20) : (initialScale / height + 20);
    
    return "translate(" + arr + ")";
  } else {
    return "translate(" + path.centroid(d) + ")";
  }
}

function statesSelect($this){
	var areaCode = $this.data('area');

	var pagingForm = $('#pagingForm');
	pagingForm.find('input[name="areaCode"]').val(areaCode);
	pagingForm.find('input[name="username"]').val('');
	pagingForm.find('input[name="trackerGroupName"]').val('');
	pagingForm.submit();
}



$('.tableBody').on('click', 'a[data-sel]', function(){
	var $this = $(this);
	var trackerGroupId = $this.data('trackergroupid');
	var areaCode = $this.data('areacode');
	
	location.replace(_cp + '/monitoring/monitoring?trackerGroupId=' + trackerGroupId + '&areaCode=' + areaCode);

});