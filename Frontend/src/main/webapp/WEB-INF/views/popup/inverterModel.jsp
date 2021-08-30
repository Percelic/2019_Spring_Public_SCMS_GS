<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade check-modal" id="inverterModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">인버터 검색</h4>
			</div>
			<div class="modal-section clearfix">
				<div class="modal-body list-body">
					<form id="modalPagingForm">
						<input type="hidden" name="searchWord" value="">
						<input type="hidden" name="pageNo" value="">
						<input type="hidden" name="pageSize" value="">
					</form>
					<br/>
					<div class="form-inverter">
				 		<div class="table-responsive">
				 			<table class="modelList">
				 				<colgroup>
				 					<col width="36px">
				 					<col>
				 				</colgroup>
				 				<thead>
				 					<tr>
				 						<th></th>
				 						<th>인버터 ID</th>
				 					</tr>
				 				</thead>
				 				<tbody>
				 				</tbody>
				 			</table>
				 		</div>
				 		<div id="modalPaging" class="pageNum">
			 			</div>
					 </div>
				</div>
				<div class="modal-body select-body">
					<h2>선택된 인버터</h2>
			 		<div class="selectedCheck" >
			 			<form id="selectCheckForm">
							<ul class="selectUl">
							</ul>
			 			</form>
			 		</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" name="apply" data-formid="${param.formId}">추가</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>


<script src="${res}/popup/js/inverterModel.js"></script>

