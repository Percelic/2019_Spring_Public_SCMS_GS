package com.example.spring03.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/Web")
public class WebController {
	
	
	Logger logger = LoggerFactory.getLogger(WebController.class);
	
	

	
	@RequestMapping("/User_Insert") 
	public String user_insert(@RequestParam Map<String, Object> pMap, @CookieValue(value="user_id", required=false) String user_id) {
		return "/Web/production/User_Insert";
	}
	
	@RequestMapping("/Group_Insert") 
	public String Group_Insert() {
		return "/Web/production/Group_Insert";
	}
	@RequestMapping("/Inverter_insert") 
	public String Inverter_insert() {
		return "/Web/production/Inverter_insert";
	}
	@RequestMapping("/Tracker_Insert") 
	public String Track_insert() {
		return "/Web/production/Tracker_Insert";
	}

}
