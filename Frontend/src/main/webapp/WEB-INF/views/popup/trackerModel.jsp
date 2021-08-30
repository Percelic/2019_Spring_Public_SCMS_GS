<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="trackerModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
					<span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">트래커 검색</h4>
			</div>
			<div class="modal-body">
				<div style="text-align:right" class="">
					지역 : 
					<select class="selectBox" name="eqAreaCode">
						<option value="" selected="selected">전체</option>
						<option value="33" >강원도</option>
						<option value="31" >경기도</option>
						<option value="55" >경상남도</option>
						<option value="54" >경상북도</option>
						<option value="43" >충청북도</option>
						<option value="41" >충청남도</option>
						<option value="63" >전라북도</option>
						<option value="61" >전라남도</option>
						<option value="64" >제주도</option>
					</select>
				
					군집명 :
					<select class="selectBox" name="eqTrackerGroupId">
						<option value="" selected="selected">전체</option>
					</select>
					<br>
					트래커 ID : <input type="text" name="likeTrackerId" value="" /> 
					<button type="button" class="btn" name="searchButton">검색</button>
				</div>
			 	
			 	<label>트래커 ID</label>
				<input type="hidden" name="trackerId">
				<input type="hidden" name="trackerGroupId">
				
				<div class="modal-list">
			 		<div class="" style="overflow:scroll; height: 360px;">
			 			<table id="trackerList" style="width: 100%;">
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
				<button type="button" class="btn" name="trackerApply" data-formid="${param.formId}">적용</button>
				<button type="button" class="btn cancel" data-dismiss="modal">닫기</button>
			</div>
		</div> 
	</div>
</div>


<script src="${res}/popup/js/trackerModel.js"></script>
