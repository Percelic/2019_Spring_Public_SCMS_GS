function viewIndex(groupId) {
	var path = $("#path").val();
	
	$("#head_form").attr("action", path + "/View/index");
	$("#head_t_group_id").val(groupId);
	$("#head_form").submit();
}

function user_viewIndex() {
	var path = $("#path").val();
	
	$("#head_form").attr("action", path + "/User_View/User_index");
	$("#head_form").submit();
}

function userList(nPage) {
	var path = $("#path").val();
	$("#n_page").val(nPage);
	$("#head_form").attr("action", path + "/userBoard/User_Board");
	$("#head_form").submit();
}

function trackerList(nPage) {
	var path = $("#path").val();
	$("#n_page").val(nPage);
	$("#head_form").attr("action", path + "/trackerBoard/Tracker_Board");
	$("#head_form").submit();
}

function inverterList(nPage) {
	var path = $("#path").val();
	$("#n_page").val(nPage);
	$("#head_form").attr("action", path + "/inverterBoard/Inverter_Board");
	$("#head_form").submit();
}

function groupList(nPage) {
	var path = $("#path").val();
	$("#n_page").val(nPage);
	$("#head_form").attr("action", path + "/groupBoard/Group_Board");
	$("#head_form").submit();
}

function langBtn2(lang) {
	var path = $("#path").val();
	var groupId = $("#head_t_group_id").val();
	
	$("#head_lang").val(lang);
	$("#head_form").attr("action", path + "/View/Details");
	$("#head_form").submit();
	
}


function langBtn(lang) {
	var path = $("#path").val();
	var groupId = $("#head_t_group_id").val();
	
	$("#head_lang").val(lang);
	$("#head_form").attr("action", path + "/View/index");
	$("#head_form").submit();
	
}

function changeLang(lang) {
	var path= $("#path").val();
	
	$("#head_form").attr("action","");
	$("#head_lang").val(lang);
	$("#head_form").submit();
}

function user_GroupBoard() {
	var path = $("#path").val();
	//$("#n_page").val(1);
	$("#head_form").attr("action", path + "/User_groupBoard/Group_Board");
	$("#head_form").submit();
}

function user_TrackerBoard() {
	var path = $("#path").val();
	//$("#n_page").val(1);
	$("#head_form").attr("action", path + "/User_trackerBoard/Tracker_Board");
	$("#head_form").submit();
}

function user_InverterBoard() {
	var path = $("#path").val();
	//$("#n_page").val(1);
	$("#head_form").attr("action", path + "/User_inverterBoard/Inverter_Board");
	$("#head_form").submit();
}

