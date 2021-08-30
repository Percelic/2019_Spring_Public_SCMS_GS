package com.example.spring03.group.dto;


import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class groupDTO {

	private String t_group_id;
	private String t_group_addr;
	private String t_group_addr2;
	private String t_group_loc_lat;
	private String t_group_loc_lng;
	private String t_group_name;
	private String t_group_username;
	private String t_group_manager;
	private String t_group_builder;
	private String t_group_owner; 
	private String t_group_phone;
	private String t_group_email;
	private String t_group_capacity;
	private MultipartFile t_group_img_filename;
	private String t_group_ip;
	private Date t_group_reg_date;
	private Date t_group_upt_date;
	private Date t_group_install_date;
	private int tracker_cnt;
	private int inverter_cnt;
	private String history;
	
	////////////////////////////////////////
	//인버터 테이블이 들어가는지 확인
	private String inverter_name;
	///////////////////////////
	// 트래커 아이디 들어가는지 확인
	private String tracker_id;
	
	
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
	public String getT_group_id() {
		return t_group_id;
	}
	public void setT_group_id(String t_group_id) {
		this.t_group_id = t_group_id;
	}
	public String getT_group_addr() {
		return t_group_addr;
	}
	public void setT_group_addr(String t_group_addr) {
		this.t_group_addr = t_group_addr;
	}
	public String getT_group_addr2() {
		return t_group_addr2;
	}
	public void setT_group_addr2(String t_group_addr2) {
		this.t_group_addr2 = t_group_addr2;
	}
	public String getT_group_loc_lat() {
		return t_group_loc_lat;
	}
	public void setT_group_loc_lat(String t_group_loc_lat) {
		this.t_group_loc_lat = t_group_loc_lat;
	}
	public String getT_group_loc_lng() {
		return t_group_loc_lng;
	}
	public void setT_group_loc_lng(String t_group_loc_lng) {
		this.t_group_loc_lng = t_group_loc_lng;
	}
	public String getT_group_name() {
		return t_group_name;
	}
	public void setT_group_name(String t_group_name) {
		this.t_group_name = t_group_name;
	}
	public String getT_group_username() {
		return t_group_username;
	}
	public void setT_group_username(String t_group_username) {
		this.t_group_username = t_group_username;
	}
	public String getT_group_manager() {
		return t_group_manager;
	}
	public void setT_group_manager(String t_group_manager) {
		this.t_group_manager = t_group_manager;
	}
	public String getT_group_builder() {
		return t_group_builder;
	}
	public void setT_group_builder(String t_group_builder) {
		this.t_group_builder = t_group_builder;
	}
	public String getT_group_owner() {
		return t_group_owner;
	}
	public void setT_group_owner(String t_group_owner) {
		this.t_group_owner = t_group_owner;
	}
	public String getT_group_phone() {
		return t_group_phone;
	}
	public void setT_group_phone(String t_group_phone) {
		this.t_group_phone = t_group_phone;
	}
	public String getT_group_email() {
		return t_group_email;
	}
	public void setT_group_email(String t_group_email) {
		this.t_group_email = t_group_email;
	}
	public String getT_group_capacity() {
		return t_group_capacity;
	}
	public void setT_group_capacity(String t_group_capacity) {
		this.t_group_capacity = t_group_capacity;
	}
	public MultipartFile getT_group_img_filename() {
		return t_group_img_filename;
	}
	public void setT_group_img_filename(MultipartFile t_group_img_filename) {
		this.t_group_img_filename = t_group_img_filename;
	}
	public String getT_group_ip() {
		return t_group_ip;
	}
	public void setT_group_ip(String t_group_ip) {
		this.t_group_ip = t_group_ip;
	}
	public Date getT_group_reg_date() {
		return t_group_reg_date;
	}
	public void setT_group_reg_date(Date t_group_reg_date) {
		this.t_group_reg_date = t_group_reg_date;
	}
	public Date getT_group_upt_date() {
		return t_group_upt_date;
	}
	public void setT_group_upt_date(Date t_group_upt_date) {
		this.t_group_upt_date = t_group_upt_date;
	}
	public Date getT_group_install_date() {
		return t_group_install_date;
	}
	public void setT_group_install_date(Date t_group_install_date) {
		this.t_group_install_date = t_group_install_date;
	}
	public int getTracker_cnt() {
		return tracker_cnt;
	}
	public void setTracker_cnt(int tracker_cnt) {
		this.tracker_cnt = tracker_cnt;
	}
	public int getInverter_cnt() {
		return inverter_cnt;
	}
	public void setInverter_cnt(int inverter_cnt) {
		this.inverter_cnt = inverter_cnt;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getInverter_name() {
		return inverter_name;
	}
	public void setInverter_name(String inverter_name) {
		this.inverter_name = inverter_name;
	}
	public String getTracker_id() {
		return tracker_id;
	}
	public void setTracker_id(String tracker_id) {
		this.tracker_id = tracker_id;
	}
	@Override
	public String toString() {
		return "groupDTO [t_group_id=" + t_group_id + ", t_group_addr=" + t_group_addr + ", t_group_addr2="
				+ t_group_addr2 + ", t_group_loc_lat=" + t_group_loc_lat + ", t_group_loc_lng=" + t_group_loc_lng
				+ ", t_group_name=" + t_group_name + ", t_group_username=" + t_group_username + ", t_group_manager="
				+ t_group_manager + ", t_group_builder=" + t_group_builder + ", t_group_owner=" + t_group_owner
				+ ", t_group_phone=" + t_group_phone + ", t_group_email=" + t_group_email + ", t_group_capacity="
				+ t_group_capacity + ", t_group_img_filename=" + t_group_img_filename + ", t_group_ip=" + t_group_ip
				+ ", t_group_reg_date=" + t_group_reg_date + ", t_group_upt_date=" + t_group_upt_date
				+ ", t_group_install_date=" + t_group_install_date + ", tracker_cnt=" + tracker_cnt + ", inverter_cnt="
				+ inverter_cnt + ", history=" + history + ", inverter_name=" + inverter_name + ", tracker_id="
				+ tracker_id + "]";
	}
	public groupDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	
	
}

