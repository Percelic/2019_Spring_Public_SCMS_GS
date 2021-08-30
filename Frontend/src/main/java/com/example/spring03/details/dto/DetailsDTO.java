package com.example.spring03.details.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class DetailsDTO {

	private String t_group_id;
	private String t_group_addr;
	private String t_group_addr2;
	private String t_group_loc_lat;
	private String t_group_loc_lng;
	private String t_group_name;
	private String t_group_manager;
	private String t_group_builder;
	private String t_group_owner;
	private String t_group_phone;
	private String t_group_email;
	private String t_group_capacity;
	private String t_group_weather;
	private MultipartFile t_group_img_filename;
	private String t_group_ip;
	private Date t_group_reg_date;
	private Date t_group_upt_date;
	private String tracker_cnt;
	private String inverter_cnt;
	private String history;
	private String snow_control_mode;
	private String sunset_control_mode;
	private String shadow_control_mode;
	private String wind_control_mode;
	private String inverter_data_output;
	private int param;
//////////////////////////////////////////////////
//날씨정보 

	private String weather_idx;
	private String area_code;
	private String area_name;
	private String weather_t_min;
	private String weather_t_max;
	private String weather_finedust_concentration;
	private String weather_ultrafinedust_concentration;
	private String weather_ozone_concentration;
///////////////////////////////////////////////////////
//센서정보
	private String sensor_data_module_temp;
	private String sensor_data_ambient_temp;
	private String Sensor_Data_Controller_Temp;
	private String Sensor_Data_Controller_Humid;
	private String sensor_data_horizontal_idt;
	private String sensor_data_slope_idt;
	private String sensor_data_wind_speed;
////////////////////////////////////////////////////////
//고장 정보
	private String alarm_type;
	private String alarm_grade;
	private String alarm_status;
	private String IS_Clear;
	private String alarm_upt_data;
	private String alarm_contents;
///////////////////////////////////////////////////////////
//발전량
	private String inverter_data_pv_v_avg;
	private String inverter_data_pv_i_sum;
	private String inverter_data_pv_output;
	private String inverter_data_system_r_v;
	private String inverter_data_system_s_v;
	private String inverter_data_system_t_v;
	private String inverter_data_system_r_i;
	private String inverter_data_system_s_i;
	private String inverter_data_system_t_i;
	private String inverter_data_pf;
	private String inverter_data_hz;
	private String inverter_data_accu_energy;
	private String inverter_data_trouble;
	private String inverter_data_date;
////////////////////////////////////////////////////////////////
//트래커
	private String tracker_id;
/////////////////////////////////////////////

	private String user_id;
	
	private String t_group_mode;

	
	public int getParam() {
		return param;
	}

	public void setParam(int param) {
		this.param = param;
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

	public String getT_group_weather() {
		return t_group_weather;
	}

	public void setT_group_weather(String t_group_weather) {
		this.t_group_weather = t_group_weather;
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

	public String getTracker_cnt() {
		return tracker_cnt;
	}

	public void setTracker_cnt(String tracker_cnt) {
		this.tracker_cnt = tracker_cnt;
	}

	public String getInverter_cnt() {
		return inverter_cnt;
	}

	public void setInverter_cnt(String inverter_cnt) {
		this.inverter_cnt = inverter_cnt;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getSnow_control_mode() {
		return snow_control_mode;
	}

	public void setSnow_control_mode(String snow_control_mode) {
		this.snow_control_mode = snow_control_mode;
	}

	public String getSunset_control_mode() {
		return sunset_control_mode;
	}

	public void setSunset_control_mode(String sunset_control_mode) {
		this.sunset_control_mode = sunset_control_mode;
	}

	public String getShadow_control_mode() {
		return shadow_control_mode;
	}

	public void setShadow_control_mode(String shadow_control_mode) {
		this.shadow_control_mode = shadow_control_mode;
	}

	public String getWind_control_mode() {
		return wind_control_mode;
	}

	public void setWind_control_mode(String wind_control_mode) {
		this.wind_control_mode = wind_control_mode;
	}

	public String getInverter_data_output() {
		return inverter_data_output;
	}

	public void setInverter_data_output(String inverter_data_output) {
		this.inverter_data_output = inverter_data_output;
	}

	public String getWeather_idx() {
		return weather_idx;
	}

	public void setWeather_idx(String weather_idx) {
		this.weather_idx = weather_idx;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getWeather_t_min() {
		return weather_t_min;
	}

	public void setWeather_t_min(String weather_t_min) {
		this.weather_t_min = weather_t_min;
	}

	public String getWeather_t_max() {
		return weather_t_max;
	}

	public void setWeather_t_max(String weather_t_max) {
		this.weather_t_max = weather_t_max;
	}

	public String getWeather_finedust_concentration() {
		return weather_finedust_concentration;
	}

	public void setWeather_finedust_concentration(String weather_finedust_concentration) {
		this.weather_finedust_concentration = weather_finedust_concentration;
	}

	public String getWeather_ultrafinedust_concentration() {
		return weather_ultrafinedust_concentration;
	}

	public void setWeather_ultrafinedust_concentration(String weather_ultrafinedust_concentration) {
		this.weather_ultrafinedust_concentration = weather_ultrafinedust_concentration;
	}

	public String getWeather_ozone_concentration() {
		return weather_ozone_concentration;
	}

	public void setWeather_ozone_concentration(String weather_ozone_concentration) {
		this.weather_ozone_concentration = weather_ozone_concentration;
	}

	public String getSensor_data_module_temp() {
		return sensor_data_module_temp;
	}

	public void setSensor_data_module_temp(String sensor_data_module_temp) {
		this.sensor_data_module_temp = sensor_data_module_temp;
	}

	public String getSensor_data_ambient_temp() {
		return sensor_data_ambient_temp;
	}

	public void setSensor_data_ambient_temp(String sensor_data_ambient_temp) {
		this.sensor_data_ambient_temp = sensor_data_ambient_temp;
	}

	public String getSensor_Data_Controller_Temp() {
		return Sensor_Data_Controller_Temp;
	}

	public void setSensor_Data_Controller_Temp(String sensor_Data_Controller_Temp) {
		Sensor_Data_Controller_Temp = sensor_Data_Controller_Temp;
	}

	public String getSensor_Data_Controller_Humid() {
		return Sensor_Data_Controller_Humid;
	}

	public void setSensor_Data_Controller_Humid(String sensor_Data_Controller_Humid) {
		Sensor_Data_Controller_Humid = sensor_Data_Controller_Humid;
	}

	public String getSensor_data_horizontal_idt() {
		return sensor_data_horizontal_idt;
	}

	public void setSensor_data_horizontal_idt(String sensor_data_horizontal_idt) {
		this.sensor_data_horizontal_idt = sensor_data_horizontal_idt;
	}

	public String getSensor_data_slope_idt() {
		return sensor_data_slope_idt;
	}

	public void setSensor_data_slope_idt(String sensor_data_slope_idt) {
		this.sensor_data_slope_idt = sensor_data_slope_idt;
	}

	public String getSensor_data_wind_speed() {
		return sensor_data_wind_speed;
	}

	public void setSensor_data_wind_speed(String sensor_data_wind_speed) {
		this.sensor_data_wind_speed = sensor_data_wind_speed;
	}

	public String getAlarm_type() {
		return alarm_type;
	}

	public void setAlarm_type(String alarm_type) {
		this.alarm_type = alarm_type;
	}

	public String getAlarm_grade() {
		return alarm_grade;
	}

	public void setAlarm_grade(String alarm_grade) {
		this.alarm_grade = alarm_grade;
	}

	public String getAlarm_status() {
		return alarm_status;
	}

	public void setAlarm_status(String alarm_status) {
		this.alarm_status = alarm_status;
	}
	

	public String getAlarm_contents() {
		return alarm_contents;
	}

	public void setAlarm_contents(String alarm_contents) {
		this.alarm_contents = alarm_contents;
	}

	public String getIS_Clear() {
		return IS_Clear;
	}

	public void setIS_Clear(String iS_Clear) {
		IS_Clear = iS_Clear;
	}

	public String getAlarm_upt_data() {
		return alarm_upt_data;
	}

	public void setAlarm_upt_data(String alarm_upt_data) {
		this.alarm_upt_data = alarm_upt_data;
	}

	public String getInverter_data_pv_v_avg() {
		return inverter_data_pv_v_avg;
	}

	public void setInverter_data_pv_v_avg(String inverter_data_pv_v_avg) {
		this.inverter_data_pv_v_avg = inverter_data_pv_v_avg;
	}

	public String getInverter_data_pv_i_sum() {
		return inverter_data_pv_i_sum;
	}

	public void setInverter_data_pv_i_sum(String inverter_data_pv_i_sum) {
		this.inverter_data_pv_i_sum = inverter_data_pv_i_sum;
	}

	public String getInverter_data_pv_output() {
		return inverter_data_pv_output;
	}

	public void setInverter_data_pv_output(String inverter_data_pv_output) {
		this.inverter_data_pv_output = inverter_data_pv_output;
	}

	public String getInverter_data_system_r_v() {
		return inverter_data_system_r_v;
	}

	public void setInverter_data_system_r_v(String inverter_data_system_r_v) {
		this.inverter_data_system_r_v = inverter_data_system_r_v;
	}

	public String getInverter_data_system_s_v() {
		return inverter_data_system_s_v;
	}

	public void setInverter_data_system_s_v(String inverter_data_system_s_v) {
		this.inverter_data_system_s_v = inverter_data_system_s_v;
	}

	public String getInverter_data_system_t_v() {
		return inverter_data_system_t_v;
	}

	public void setInverter_data_system_t_v(String inverter_data_system_t_v) {
		this.inverter_data_system_t_v = inverter_data_system_t_v;
	}

	public String getInverter_data_system_r_i() {
		return inverter_data_system_r_i;
	}

	public void setInverter_data_system_r_i(String inverter_data_system_r_i) {
		this.inverter_data_system_r_i = inverter_data_system_r_i;
	}

	public String getInverter_data_system_s_i() {
		return inverter_data_system_s_i;
	}

	public void setInverter_data_system_s_i(String inverter_data_system_s_i) {
		this.inverter_data_system_s_i = inverter_data_system_s_i;
	}

	public String getInverter_data_system_t_i() {
		return inverter_data_system_t_i;
	}

	public void setInverter_data_system_t_i(String inverter_data_system_t_i) {
		this.inverter_data_system_t_i = inverter_data_system_t_i;
	}

	public String getInverter_data_pf() {
		return inverter_data_pf;
	}

	public void setInverter_data_pf(String inverter_data_pf) {
		this.inverter_data_pf = inverter_data_pf;
	}

	public String getInverter_data_hz() {
		return inverter_data_hz;
	}

	public void setInverter_data_hz(String inverter_data_hz) {
		this.inverter_data_hz = inverter_data_hz;
	}

	public String getInverter_data_accu_energy() {
		return inverter_data_accu_energy;
	}

	public void setInverter_data_accu_energy(String inverter_data_accu_energy) {
		this.inverter_data_accu_energy = inverter_data_accu_energy;
	}

	public String getInverter_data_trouble() {
		return inverter_data_trouble;
	}

	public void setInverter_data_trouble(String inverter_data_trouble) {
		this.inverter_data_trouble = inverter_data_trouble;
	}

	public String getInverter_data_date() {
		return inverter_data_date;
	}

	public void setInverter_data_date(String inverter_data_date) {
		this.inverter_data_date = inverter_data_date;
	}

	public String getTracker_id() {
		return tracker_id;
	}

	public void setTracker_id(String tracker_id) {
		this.tracker_id = tracker_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getT_group_mode() {
		return t_group_mode;
	}

	public void setT_group_mode(String t_group_mode) {
		this.t_group_mode = t_group_mode;
	}

	@Override
	public String toString() {
		return "DetailsDTO [t_group_id=" + t_group_id + ", t_group_addr=" + t_group_addr + ", t_group_addr2="
				+ t_group_addr2 + ", t_group_loc_lat=" + t_group_loc_lat + ", t_group_loc_lng=" + t_group_loc_lng
				+ ", t_group_name=" + t_group_name + ", t_group_manager=" + t_group_manager + ", t_group_builder="
				+ t_group_builder + ", t_group_owner=" + t_group_owner + ", t_group_phone=" + t_group_phone
				+ ", t_group_email=" + t_group_email + ", t_group_capacity=" + t_group_capacity + ", t_group_weather="
				+ t_group_weather + ", t_group_img_filename=" + t_group_img_filename + ", t_group_ip=" + t_group_ip
				+ ", t_group_reg_date=" + t_group_reg_date + ", t_group_upt_date=" + t_group_upt_date + ", tracker_cnt="
				+ tracker_cnt + ", inverter_cnt=" + inverter_cnt + ", history=" + history + ", snow_control_mode="
				+ snow_control_mode + ", sunset_control_mode=" + sunset_control_mode + ", shadow_control_mode="
				+ shadow_control_mode + ", wind_control_mode=" + wind_control_mode + ", inverter_data_output="
				+ inverter_data_output + ", weather_idx=" + weather_idx + ", area_code=" + area_code + ", area_name="
				+ area_name + ", weather_t_min=" + weather_t_min + ", weather_t_max=" + weather_t_max
				+ ", weather_finedust_concentration=" + weather_finedust_concentration
				+ ", weather_ultrafinedust_concentration=" + weather_ultrafinedust_concentration
				+ ", weather_ozone_concentration=" + weather_ozone_concentration + ", sensor_data_module_temp="
				+ sensor_data_module_temp + ", sensor_data_ambient_temp=" + sensor_data_ambient_temp
				+ ", Sensor_Data_Controller_Temp=" + Sensor_Data_Controller_Temp + ", Sensor_Data_Controller_Humid="
				+ Sensor_Data_Controller_Humid + ", sensor_data_horizontal_idt=" + sensor_data_horizontal_idt
				+ ", sensor_data_slope_idt=" + sensor_data_slope_idt + ", sensor_data_wind_speed="
				+ sensor_data_wind_speed + ", alarm_type=" + alarm_type + ", alarm_grade=" + alarm_grade
				+ ", alarm_status=" + alarm_status + ", IS_Clear=" + IS_Clear + ", alarm_upt_data=" + alarm_upt_data
				+ ", inverter_data_pv_v_avg=" + inverter_data_pv_v_avg + ", inverter_data_pv_i_sum="
				+ inverter_data_pv_i_sum + ", inverter_data_pv_output=" + inverter_data_pv_output
				+ ", inverter_data_system_r_v=" + inverter_data_system_r_v + ", inverter_data_system_s_v="
				+ inverter_data_system_s_v + ", inverter_data_system_t_v=" + inverter_data_system_t_v
				+ ", inverter_data_system_r_i=" + inverter_data_system_r_i + ", inverter_data_system_s_i="
				+ inverter_data_system_s_i + ", inverter_data_system_t_i=" + inverter_data_system_t_i
				+ ", inverter_data_pf=" + inverter_data_pf + ", inverter_data_hz=" + inverter_data_hz
				+ ", inverter_data_accu_energy=" + inverter_data_accu_energy + ", inverter_data_trouble="
				+ inverter_data_trouble + ", inverter_data_date=" + inverter_data_date + ", tracker_id=" + tracker_id
				+ ", user_id=" + user_id + "]";
	}

	public DetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}