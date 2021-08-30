$(document).ready(function(){
	
	// 페이징 ui
	$('.pageNum').on('click', 'li[data-pageno]', function(event){
		var $this = $(this);
		var pageNo = $this.data('pageno');
		var formid = $(event.delegateTarget).data('form-id');
		var form = $('#'+formid);
		form.find('input[name="pageNo"]').val(pageNo);
		form.submit();
	});
	
	// 페이지 Size ui
	$('.viewNum').on('change', '.selectBox', function(event){
		var $this = $(this);
		var pageSize = $this.val();
		var formid = $(event.delegateTarget).data('form-id');
		var form = $('#'+formid);
		form.find('input[name="pageSize"]').val(pageSize);
		form.submit();

	});

	
});




