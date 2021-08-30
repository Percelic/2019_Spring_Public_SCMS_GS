package com.example.spring03.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.example.spring03.details.dto.DetailsDTO;
import com.example.spring03.details.service.DetailsService;

@Controller
public class GarosuController {
	
	
	private Logger logger = LoggerFactory.getLogger(DetailsController.class);
	
	@Inject
	DetailsService detailsService;
	
	@Inject 
	SessionLocaleResolver localeResolver; 
	@Inject 
	MessageSource messageSource;
	
	@RequestMapping("/Garosu/index") 
	public String index(@RequestParam String t_group_id, Model model, HttpServletResponse response, Locale locale, HttpServletRequest request, ModelAndView mav) {
		DetailsDTO dto = new DetailsDTO();
		dto.setT_group_id(t_group_id);
		model.addAttribute("dto", detailsService.view(t_group_id));//발전량 
		model.addAttribute("weather", detailsService.group_weather(t_group_id));//날씨정보
		model.addAttribute("sensor_date", detailsService.sensordate(t_group_id));//센서데이터
		model.addAttribute("t_alarm", detailsService.t_alarm(t_group_id));//고장 정보
		model.addAttribute("tracker", detailsService.tracker(t_group_id));//트래커 퍼센트 정보
		model.addAttribute("total_data", detailsService.total_data(t_group_id));//현재, 누적 발전
		model.addAttribute("inverter_date", detailsService.inverter_date(t_group_id));//금일 
		model.addAttribute("inverter_today", detailsService.inverter_today(t_group_id) ); //전 발전량
		model.addAttribute("Cumulative_power_generation", detailsService.Cumulative_power_generation(t_group_id) ); //전 발전량
		
		
		/******************************가로수 누적 발전량 데이터********************************/
		model.addAttribute("A_dong", detailsService.A_dong(t_group_id)); //전 발전량
		model.addAttribute("AA_dong", detailsService.AA_dong(t_group_id)); //전 발전량
		logger.info("AA의 값은?" + detailsService.AA_dong(t_group_id));
		
		model.addAttribute("B_dong", detailsService.B_dong(t_group_id)); //전 발전량
		model.addAttribute("BB_dong", detailsService.BB_dong(t_group_id)); //전 발전량

		model.addAttribute("C_dong", detailsService.C_dong(t_group_id)); //전 발전량
		model.addAttribute("CC_dong", detailsService.CC_dong(t_group_id)); //전 발전량
		logger.info("CC동.", detailsService.CC_dong(t_group_id));

		model.addAttribute("D_dong", detailsService.D_dong(t_group_id)); //전 발전량
		model.addAttribute("DD_dong", detailsService.DD_dong(t_group_id)); //전 발전량

		model.addAttribute("E_dong", detailsService.E_dong(t_group_id)); //전 발전량
		model.addAttribute("EE_dong", detailsService.EE_dong(t_group_id)); //전 발전량

		model.addAttribute("F_dong", detailsService.F_dong(t_group_id)); //전 발전량
		model.addAttribute("FF_dong", detailsService.FF_dong(t_group_id)); //전 발전량
		
		
		model.addAttribute("GROUP_ADDR", detailsService.Addr(t_group_id) ); //군집 주소
		logger.info("군집 주소 들어감", detailsService.Addr(t_group_id));
		
		mav.addObject("group_id", detailsService.t_group_id(t_group_id));
		logger.info("들어감?", detailsService.t_group_id(t_group_id));

		mav.addObject("trackerList", detailsService.trackerList(dto.getT_group_id()));//트래커 퍼센트 정보
		
		
		return "/Garosu/index"; 
	}

}
