package com.example.spring03.inverter.dto;

import java.util.Date;

public class inverterDTO {

	
 private String	inverter_idx; // 인버터 인덱스
 private String	inverter_id; // 인버터 아이디
 private String	inverter_group_name; // 발전소명
 private String	inverter_builder; // 시공사
 private String	inverter_model; // 모델명
 private String	inverter_owner; // 소유주
 private Float	inverter_capacity; // 용량
 private String	inverter_manager; // 담당자
 private String	inverter_phone; // 연락처
 private String	inverter_username; // 관리자
 
 private String history; // 작성자
 private Date inverter_reg_date; // 작성일자
 private Date inverter_install_date; // 작성일자
 private Date inverter_completion_date; // 작성일자
 /////////////////////////////////////
 //군집명 넣어 주기 
 private String t_group_name;
 private String t_group_id;
 private String userID;
 
 ////////////////////////////////////
 
 private int startRow;
 private int endRow;

 
public int getStartRow() {
	return startRow;
}
public void setStartRow(int startRow) {
	this.startRow = startRow;
}
public int getEndRow() {
	return endRow;
}
public void setEndRow(int endRow) {
	this.endRow = endRow;
}
public String getInverter_idx() {
	return inverter_idx;
}
public void setInverter_idx(String inverter_idx) {
	this.inverter_idx = inverter_idx;
}
public String getInverter_id() {
	return inverter_id;
}
public void setInverter_id(String inverter_id) {
	this.inverter_id = inverter_id;
}
public String getInverter_group_name() {
	return inverter_group_name;
}
public void setInverter_group_name(String inverter_group_name) {
	this.inverter_group_name = inverter_group_name;
}
public String getInverter_builder() {
	return inverter_builder;
}
public void setInverter_builder(String inverter_builder) {
	this.inverter_builder = inverter_builder;
}
public String getInverter_model() {
	return inverter_model;
}
public void setInverter_model(String inverter_model) {
	this.inverter_model = inverter_model;
}
public String getInverter_owner() {
	return inverter_owner;
}
public void setInverter_owner(String inverter_owner) {
	this.inverter_owner = inverter_owner;
}
public Float getInverter_capacity() {
	return inverter_capacity;
}
public void setInverter_capacity(Float inverter_capacity) {
	this.inverter_capacity = inverter_capacity;
}
public String getInverter_manager() {
	return inverter_manager;
}
public void setInverter_manager(String inverter_manager) {
	this.inverter_manager = inverter_manager;
}
public String getInverter_phone() {
	return inverter_phone;
}
public void setInverter_phone(String inverter_phone) {
	this.inverter_phone = inverter_phone;
}
public String getInverter_username() {
	return inverter_username;
}
public void setInverter_username(String inverter_username) {
	this.inverter_username = inverter_username;
}
public String getHistory() {
	return history;
}
public void setHistory(String history) {
	this.history = history;
}
public Date getInverter_reg_date() {
	return inverter_reg_date;
}
public void setInverter_reg_date(Date inverter_reg_date) {
	this.inverter_reg_date = inverter_reg_date;
}
public Date getInverter_install_date() {
	return inverter_install_date;
}
public void setInverter_install_date(Date inverter_install_date) {
	this.inverter_install_date = inverter_install_date;
}
public Date getInverter_completion_date() {
	return inverter_completion_date;
}
public void setInverter_completion_date(Date inverter_completion_date) {
	this.inverter_completion_date = inverter_completion_date;
}
public String getT_group_name() {
	return t_group_name;
}
public void setT_group_name(String t_group_name) {
	this.t_group_name = t_group_name;
}
public String getT_group_id() {
	return t_group_id;
}
public void setT_group_id(String t_group_id) {
	this.t_group_id = t_group_id;
}

public String getUserID() {
	return userID;
}
public void setUserID(String userID) {
	this.userID = userID;
}
@Override
public String toString() {
	return "inverterDTO [inverter_idx=" + inverter_idx + ", inverter_id=" + inverter_id + ", inverter_group_name="
			+ inverter_group_name + ", inverter_builder=" + inverter_builder + ", inverter_model=" + inverter_model
			+ ", inverter_owner=" + inverter_owner + ", inverter_capacity=" + inverter_capacity + ", inverter_manager="
			+ inverter_manager + ", inverter_phone=" + inverter_phone + ", inverter_username=" + inverter_username
			+ ", history=" + history + ", inverter_reg_date=" + inverter_reg_date + ", inverter_install_date="
			+ inverter_install_date + ", inverter_completion_date=" + inverter_completion_date + ", t_group_name="
			+ t_group_name + ", t_group_id=" + t_group_id + "]";
}
public inverterDTO() {
	super();
	// TODO Auto-generated constructor stub
}

 


 
 



 
}
