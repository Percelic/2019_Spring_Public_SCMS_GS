$(document).ready(function(){
	$('.remote-start-group').hide();
	$('.remote-manual-group').hide();
	
	setAlramTable();
});

// 제어시작
$('.remote-banner').on('click', '.remote-start-btn', function(){
	$('.remote-start-group').show();
	$('.remote-stop-group').hide();
	
// 제어중지	
}).on('click', '.remote-stop-btn', function(){
	$('.remote-start-group').hide();
	$('.remote-stop-group').show();
	
// 자동 모드	
}).on('click', '.auto_btn', function(){
	$('.remote-manual-group').hide();
	$('.remote-auto-group').show();
	
// 수동 모드	
}).on('click', '.manual_btn', function(){
	$('.remote-manual-group').show();
	$('.remote-auto-group').hide();
});


$('#alarmTableForm').on('click', '.selectBtn', function(){
	setAlramTable();
});


$('.alarmListPageing').on('click', 'li[data-pageno]', function( event){
	var $this = $(this);
	var pageNo = $this.data('pageno');
	var alarmTableForm = $('#alarmTableForm');
	alarmTableForm.find('input[name="pageNo"]').val(pageNo);

	setAlramTable();

});


function setAlramTable(){
	var trackerSelForm = $('#trackerSelForm');
	var trackerGroupId = trackerSelForm.find('select[name="trackerGroupId"]').val();

	var alarmTableForm = $('#alarmTableForm');
	var alarmGrade = alarmTableForm.find('select[name="alarmGrade"]').val();
	var trackerId = $('#trackerSelForm').find('select[name="trackerId"]').val();
	var pageNo = alarmTableForm.find('input[name="pageNo"]').val();
	var pageSize = alarmTableForm.find('input[name="pageSize"]').val();
	var startDescNo = 0;
	
	$.ajax({
		type:"GET",
		url	:_cp + "/groupPresentState/alarmList/pageing",
		data: {trackerGroupId : trackerGroupId, alarmGrade : alarmGrade, pageNo : pageNo, pageSize : pageSize, trackerId : trackerId},
		async:false,
		success:function(data){
			startDescNo = data.startDescNo;
			setPageForm(data);
			
		}, error:function(error){
			alert("검색에 실패 하였습니다.");
		}
	});
	
	
	
	$.ajax({
		type:"GET",
		url	:_cp + "/groupPresentState/alarmList",
		data: {trackerGroupId : trackerGroupId, alarmGrade : alarmGrade, pageNo : pageNo, pageSize : pageSize, trackerId : trackerId},
		success:function(list){
			
			var length = list.length;
			var $list = $(".alarmList");
			var $rowSample = "<tr style='text-align: center;'>" + 
								"<td colspan='5'>검색 결과가 없습니다.</td>" +
							 "</tr>";
			
			
			$list.find('tbody').empty();
			if(length < 1){
				
				$list.find('tbody').append($rowSample);
			}
			
			for(var i=0; i<length; i++){
				var item = list[i];
				var $rowSample = "<tr>" +
									"<td>"+ (startDescNo - i) + "</td>" +
									"<td>"+ item.tracker_id + "</td>" +
									"<td>"+ item.alarm_type + "</td>" +
									"<td>"+ item.alarm_grade + "</td>" +
									"<td>"+ item.upt_date + "</td>" +
								 "</tr>";				
				
				
				$list.find('tbody').append($rowSample);
			}
			
		}, error:function(error){
			alert("검색에 실패 하였습니다.");
		}
	});
}


function setPageForm(paging){
	var divPaging = $('.alarmListPageing');
	var html = "";
	
	html += "<ul class=''>";
	for (var i=paging.sheetStartPageNo; i<=paging.sheetEndPageNo; i++){
   			
		if(i == paging.pageNo){
			html += "<li class='pageNumBtn active' data-pageno='" + i + "'>";
		} else {
			html += "<li class='pageNumBtn' data-pageno='" + i + "'>";
		}
		
		html += "<a href='#' >" + i + "</a>"
		html += "</li>"
		
	}
	html += "</ul>";
	
	divPaging.empty();
	divPaging.append(html);

}
