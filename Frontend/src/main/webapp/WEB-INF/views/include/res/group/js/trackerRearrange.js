$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="manage"]').addClass("active");
});

// GPS 좌표 매칭
$('.inputRec').on('click', 'button[data-tracker-gps-rearrange]', function(){
	var trackerGroupId = $(this).data('tracker-gps-rearrange');
	
	$.ajax({
		type:"GET",
		url	: _cp + '/group/trackerGpsRearrange',
		data: {trackerGroupId : trackerGroupId},
		success:function(data) {

		}, error:function(error) {
			
		}
	});
});