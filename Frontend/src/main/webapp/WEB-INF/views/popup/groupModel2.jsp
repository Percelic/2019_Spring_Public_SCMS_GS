<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="groupModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
					<span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">군집 검색</h4>
			</div>
			<div class="modal-body">
				<div style="text-align:right" class="">
					군집명 또는 군집ID : <input type="text" name="searchWord" value="" /> 
					<button type="button" class="btn" name="searchButton">검색</button>
				</div>
			 	
			 	<label>군집명 (군집ID)</label>
				<input type="hidden" name="trackerGroupId">
				<input type="hidden" name="trackerGroupName">
				
				
				<div class="modal-list">
			 		<div class="" style="overflow:scroll; height: 360px;">
			 			<table id="groupList" style="width: 100%;">
			 				<tbody>
			 					<tr>
									<td data-group-id="">검색 결과가 없습니다.</td>
								</tr>
			 				</tbody>
			 			</table>
			 		</div>
				 </div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn" name="groupApply" data-formid="${param.formId}">적용</button>
				<button type="button" class="btn cancel" data-dismiss="modal">닫기</button>
			</div>
		</div> 
	</div>
</div>


<script src="${res}/popup/js/groupModel2.js"></script>
