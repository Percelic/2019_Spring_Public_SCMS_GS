<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="inverterModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
					<span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">인버터 검색</h4>
			</div>
			<div class="modal-body">
				<div style="text-align:right" class="">
					인버터 ID : <input type="text" name="inverterSearchWord" value="" /> 
					<button type="button" class="btn" name="inverterSearchButton">검색</button>
				</div>
			 	
			 	<label>인버터ID</label>
				<input type="hidden" name="inverterId">
				
				<div class="modal-list">
			 		<div class="" style="overflow:scroll; height: 360px;">
			 			<table id="inverterList" style="width: 100%;">
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
				<button type="button" class="btn" name="apply" data-formid="${param.formId}">적용</button>
				<button type="button" class="btn cancel" data-dismiss="modal">닫기</button>
			</div>
		</div> 
	</div>
</div>


<script src="${res}/popup/js/inverterModel.js"></script>
