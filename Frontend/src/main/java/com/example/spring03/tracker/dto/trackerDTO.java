package com.example.spring03.tracker.dto;

import java.util.Date;

public class trackerDTO {
	
	
	 private String tracker_idx;
	 private String tracker_id;
	 private String t_group_id;
	 private String tracker_name;
	 private String tracker_model;
	 private String tracker_capacity;
	 private String tracker_builder;
	 private String tracker_type;
	 private String tracker_manager;
	 private String tracker_loc_lat;
	 private String tracker_loc_lng;
	 private String tracker_owner;
	 private String tracker_phone;
	 private String tracker_username;
	 private String tracker_img_filename;
	 private String tracker_port;
	 private String history;
	 private Date tracker_reg_date;
	 private Date tracker_completion_date;
	 private Date tracker_install_date;
	 private Date tracker_upt_date;
	 ///////////////////////////////////
	 //군집명 
	 private String t_group_name;
	//////////////////////////////////////
	 //인버터 아이디 
	 private String inverter_idx;
	 private String inverter_id;
	 //////////////////////////////////////
	 
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
	public String getTracker_idx() {
		return tracker_idx;
	}
	public void setTracker_idx(String tracker_idx) {
		this.tracker_idx = tracker_idx;
	}
	public String getTracker_id() {
		return tracker_id;
	}
	public void setTracker_id(String tracker_id) {
		this.tracker_id = tracker_id;
	}
	public String getT_group_id() {
		return t_group_id;
	}
	public void setT_group_id(String t_group_id) {
		this.t_group_id = t_group_id;
	}
	public String getTracker_name() {
		return tracker_name;
	}
	public void setTracker_name(String tracker_name) {
		this.tracker_name = tracker_name;
	}
	public String getTracker_model() {
		return tracker_model;
	}
	public void setTracker_model(String tracker_model) {
		this.tracker_model = tracker_model;
	}
	public String getTracker_capacity() {
		return tracker_capacity;
	}
	public void setTracker_capacity(String tracker_capacity) {
		this.tracker_capacity = tracker_capacity;
	}
	public String getTracker_builder() {
		return tracker_builder;
	}
	public void setTracker_builder(String tracker_builder) {
		this.tracker_builder = tracker_builder;
	}
	public String getTracker_type() {
		return tracker_type;
	}
	public void setTracker_type(String tracker_type) {
		this.tracker_type = tracker_type;
	}
	public String getTracker_manager() {
		return tracker_manager;
	}
	public void setTracker_manager(String tracker_manager) {
		this.tracker_manager = tracker_manager;
	}
	public String getTracker_loc_lat() {
		return tracker_loc_lat;
	}
	public void setTracker_loc_lat(String tracker_loc_lat) {
		this.tracker_loc_lat = tracker_loc_lat;
	}
	public String getTracker_loc_lng() {
		return tracker_loc_lng;
	}
	public void setTracker_loc_lng(String tracker_loc_lng) {
		this.tracker_loc_lng = tracker_loc_lng;
	}
	public String getTracker_owner() {
		return tracker_owner;
	}
	public void setTracker_owner(String tracker_owner) {
		this.tracker_owner = tracker_owner;
	}
	public String getTracker_phone() {
		return tracker_phone;
	}
	public void setTracker_phone(String tracker_phone) {
		this.tracker_phone = tracker_phone;
	}
	public String getTracker_username() {
		return tracker_username;
	}
	public void setTracker_username(String tracker_username) {
		this.tracker_username = tracker_username;
	}
	public String getTracker_img_filename() {
		return tracker_img_filename;
	}
	public void setTracker_img_filename(String tracker_img_filename) {
		this.tracker_img_filename = tracker_img_filename;
	}
	public String getTracker_port() {
		return tracker_port;
	}
	public void setTracker_port(String tracker_port) {
		this.tracker_port = tracker_port;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public Date getTracker_reg_date() {
		return tracker_reg_date;
	}
	public void setTracker_reg_date(Date tracker_reg_date) {
		this.tracker_reg_date = tracker_reg_date;
	}
	public Date getTracker_completion_date() {
		return tracker_completion_date;
	}
	public void setTracker_completion_date(Date tracker_completion_date) {
		this.tracker_completion_date = tracker_completion_date;
	}
	public Date getTracker_install_date() {
		return tracker_install_date;
	}
	public void setTracker_install_date(Date tracker_install_date) {
		this.tracker_install_date = tracker_install_date;
	}
	public Date getTracker_upt_date() {
		return tracker_upt_date;
	}
	public void setTracker_upt_date(Date tracker_upt_date) {
		this.tracker_upt_date = tracker_upt_date;
	}
	public String getT_group_name() {
		return t_group_name;
	}
	public void setT_group_name(String t_group_name) {
		this.t_group_name = t_group_name;
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
	@Override
	public String toString() {
		return "trackerDTO [tracker_idx=" + tracker_idx + ", tracker_id=" + tracker_id + ", t_group_id=" + t_group_id
				+ ", tracker_name=" + tracker_name + ", tracker_model=" + tracker_model + ", tracker_capacity="
				+ tracker_capacity + ", tracker_builder=" + tracker_builder + ", tracker_type=" + tracker_type
				+ ", tracker_manager=" + tracker_manager + ", tracker_loc_lat=" + tracker_loc_lat + ", tracker_loc_lng="
				+ tracker_loc_lng + ", tracker_owner=" + tracker_owner + ", tracker_phone=" + tracker_phone
				+ ", tracker_username=" + tracker_username + ", tracker_img_filename=" + tracker_img_filename
				+ ", tracker_port=" + tracker_port + ", history=" + history + ", tracker_reg_date=" + tracker_reg_date
				+ ", tracker_completion_date=" + tracker_completion_date + ", tracker_install_date="
				+ tracker_install_date + ", tracker_upt_date=" + tracker_upt_date + ", t_group_name=" + t_group_name
				+ ", inverter_idx=" + inverter_idx + ", inverter_id=" + inverter_id + "]";
	}
	public trackerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
