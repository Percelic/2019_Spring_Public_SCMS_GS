package com.example.spring03.controller;


import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.example.spring03.details.dto.DetailsDTO;
import com.example.spring03.details.service.DetailsService;

@Controller
@RequestMapping("/View/*")
public class DetailsController {

	private Logger logger = LoggerFactory.getLogger(DetailsController.class);
	
	@Inject
	DetailsService detailsService;
	
	@Inject 
	SessionLocaleResolver localeResolver; 
	@Inject 
	MessageSource messageSource;
	
	@RequestMapping(value="/index")
	public String index(@RequestParam String t_group_id, Model model, HttpServletResponse response, Locale locale, HttpServletRequest request, ModelAndView mav) {
		DetailsDTO dto = new DetailsDTO();
//		PageDto pageDto = new PageDto();
		HttpSession session = request.getSession();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
//		pageDto.setNowPage(1);
//		pageDto.setTotalCount(totalCnt);
		
		dto.setT_group_id(t_group_id);
		model.addAttribute("dto", detailsService.view(t_group_id));//발전량
		model.addAttribute("weather", detailsService.group_weather(t_group_id));//날씨정보
		model.addAttribute("sensor_date", detailsService.sensordate(t_group_id));//센서데이터
		model.addAttribute("t_alarm", translate_statusCode(detailsService.t_alarm(t_group_id)) );//고장 정보
		model.addAttribute("tracker", detailsService.tracker(t_group_id));//트래커 퍼센트 정보
		model.addAttribute("total_data", detailsService.total_data(t_group_id));//현재 출력
		model.addAttribute("inverter_date", detailsService.inverter_date(t_group_id));//전일 발전량
		model.addAttribute("inverter_today", detailsService.inverter_today(t_group_id) ); //금일 발전량
		model.addAttribute("Cumulative_power_generation", detailsService.Cumulative_power_generation(t_group_id) ); //누적 발전량
		
		model.addAttribute("GROUP_ADDR", detailsService.Addr(t_group_id) ); //군집 주소
		logger.info("군집 주소 들어감", detailsService.Addr(t_group_id));
		
		mav.addObject("group_id", detailsService.t_group_id(t_group_id));
		logger.info("들어감?", detailsService.t_group_id(t_group_id));

		model.addAttribute("trackerList", detailsService.trackerList(t_group_id));//트래커 퍼센트 정보
		session.setAttribute("tGroupId", t_group_id);
		session.setAttribute("lang", LocaleContextHolder.getLocale());
		logger.info("::*************::" + t_group_id);
		
		
//		CommPaging commPage = new CommPaging(pageDto, 10, 10, "groupList");
//		
//		dto.setStartRow(commPage.getStartRow());
//		dto.setEndRow(commPage.getEndRow());
//		
//		mav.addObject("nowPage", commPage.getNowPage());
//		mav.addObject("pageTag", commPage.resultString());
//		mav.addObject("list",groupService.listGroup(dto));
		
		return "tiles/view/index";
	}
	
	@RequestMapping(value= "/Tracker_id.do")
	@ResponseBody
	public ModelAndView Tracker_ID (ModelAndView mav) {
		List<DetailsDTO> list = detailsService.tracker_id();
		
		return mav;
	}
	

	
	@RequestMapping("/Details")
	public ModelAndView List(HttpServletRequest request,Locale locale) {
		ModelAndView mav = new ModelAndView("tiles/view/detail");	
		HttpSession session = request.getSession();
		
		session.setAttribute("lang", LocaleContextHolder.getLocale());
		 mav.addObject("list", detailsService.listDetails());
		 return mav;
	}
	
	
	public List<DetailsDTO> translate_statusCode(List<DetailsDTO> src) {
		List<DetailsDTO> _list_dto = src;
		
		for(DetailsDTO _dto : _list_dto) {
			
			String _szStatus = new StringBuffer(Integer.toBinaryString(Integer.parseInt(_dto.getAlarm_status()))).reverse().toString();
			String _szTrans = "";
			
			if(Integer.parseInt(_dto.getAlarm_status()) > 7613 || Integer.parseInt(_dto.getAlarm_status()) < 0) {
				_szTrans = "알람 코드 입력값 초과";
			}
			
			else {
				for(int i = 0 ; i < _szStatus.length() ; i++) {
	
					
					/* REMS Alarm */
					if( _szStatus.charAt(i) == '1') {
						
						switch(i) {
							case 0:
								_szTrans += "인버터  미작동 ,";
								break;
							case 1:
								_szTrans += "태양전지  과전압 ,";
								break;
							case 2:
								_szTrans += "태양전지  저전압 ,";
								break;
							case 3:
								_szTrans += "태양전지  과전류 ,";
								break;
							case 4:
								_szTrans += "인버터 IGBT 에러 ,";
								break;
							case 5:
								_szTrans += "인버터 과온 ,";
								break;
							case 6:
								_szTrans += "계통 과전압 ,";
								break;
							case 7:
								_szTrans += "계통 저전압 ,";
								break;
							case 8:
								_szTrans += "계통 과전류 ,";
								break;
							case 9:
								_szTrans += "계통 과주파수 ,";
								break;
							case 10:
								_szTrans += "계통 저주파수 ,";
								break;
							case 11:
								_szTrans += "단독운전(정전 발생) ,";
								break;
							case 12:
								_szTrans += "지락(누전 발생) ,";
								break;
							default:
								break;
						}				
					}
				}
				if(_szTrans.equals("")) _szTrans = "정상화";
				else _szTrans = _szTrans.substring(0,_szTrans.length()-1); 
			}
			
			_dto.setAlarm_contents(_szTrans);
		}
		
		return _list_dto;
	}
	
}
