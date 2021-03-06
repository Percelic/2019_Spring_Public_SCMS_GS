package com.example.spring03.User_controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring03.details.dto.DetailsDTO;
import com.example.spring03.details.service.DetailsService;

@Controller
@RequestMapping("/User_View/*")
public class User_DetailsController {
	
	private static final Logger logger = LoggerFactory.getLogger(User_DetailsController.class);
	
	@Inject
	DetailsService detailsService;
	
	@Inject 
	SessionLocaleResolver localeResolver; 
	@Inject 
	MessageSource messageSource;
	
	@RequestMapping("/User_ClusterCheck.do") 
	public String clusterCheck(Model model,HttpServletResponse response, HttpServletRequest request, Locale locale, ModelAndView mav, RedirectAttributes redirectAttr) {
		try {
			String userid = request.getSession().getAttribute("user_id").toString();
		 	String groupID = detailsService.User_Details(userid).getT_group_id();
		 	
			model.addAttribute("t_group_id",groupID);
			
			//return "redirect:/User_View/User_index";
			return "/User_View/User_ClusterCheck";
		}  catch (Exception e) {
			request.getSession().invalidate();
			
			return "/User_Auth/login";
		}

		//return "/User_View/User_index";
	}
	
//	@RequestMapping(value="/User_index", method=RequestMethod.GET)
//	public String index(Model model, HttpServletResponse response, Locale locale, HttpServletRequest request, ModelAndView mav, HttpSession session) {
//		logger.info("*******User_index GET");
//		String t_group_id = session.getAttribute("tGroupId").toString();
//		DetailsDTO dto = new DetailsDTO();
//		dto.setT_group_id(t_group_id);
//		
//		model.addAttribute("dto", detailsService.view(t_group_id));//?????????
//		model.addAttribute("weather", detailsService.group_weather(t_group_id));//????????????
//		model.addAttribute("sensor_date", detailsService.sensordate(t_group_id));//???????????????
//		model.addAttribute("t_alarm", detailsService.t_alarm(t_group_id));//?????? ??????
//		model.addAttribute("tracker", detailsService.tracker(t_group_id));//????????? ????????? ??????
//		model.addAttribute("total_data", detailsService.total_data(t_group_id));//?????? ??????
//		model.addAttribute("inverter_date", detailsService.inverter_date(t_group_id));//?????? ?????????
//		model.addAttribute("inverter_today", detailsService.inverter_today(t_group_id) ); //?????? ?????????
//		model.addAttribute("Cumulative_power_generation", detailsService.Cumulative_power_generation(t_group_id) ); //?????? ?????????
//		
//		model.addAttribute("GROUP_ADDR", detailsService.Addr(t_group_id) ); //?????? ??????
////		logger.info("?????? ?????? ?????????", detailsService.Addr(t_group_id));
//		
//		mav.addObject("group_id", detailsService.t_group_id(t_group_id));
////		logger.info("??????????", detailsService.t_group_id(t_group_id));
//
//		mav.addObject("trackerList", detailsService.trackerList(t_group_id));//????????? ????????? ??????
////		logger.info("????????? ????????? ???????????", detailsService.trackerList(t_group_id));
//		
//		
//		return "/User_View/User_index";
//	}
	
//	@RequestMapping(value="/User_index")
//	public String index(@RequestParam String t_group_id, Model model, HttpServletResponse response, Locale locale, HttpServletRequest request, ModelAndView mav) {
//		DetailsDTO dto = new DetailsDTO();
//		HttpSession session = request.getSession();
//		
//		dto.setT_group_id(t_group_id);
//		model.addAttribute("dto", detailsService.view(t_group_id));//?????????
//		model.addAttribute("weather", detailsService.group_weather(t_group_id));//????????????
//		model.addAttribute("sensor_date", detailsService.sensordate(t_group_id));//???????????????
//		model.addAttribute("t_alarm", translate_statusCode(detailsService.t_alarm(t_group_id)));//?????? ??????
//		model.addAttribute("tracker", detailsService.tracker(t_group_id));//????????? ????????? ??????
//		model.addAttribute("total_data", detailsService.total_data(t_group_id));//??????, ?????? ??????
//		model.addAttribute("inverter_date", detailsService.inverter_date(t_group_id));//?????? 
//		model.addAttribute("inverter_today", detailsService.inverter_today(t_group_id) ); //??? ?????????
//		model.addAttribute("Cumulative_power_generation", detailsService.Cumulative_power_generation(t_group_id) ); //??? ?????????
//		model.addAttribute("manageCluster", detailsService.User_Details(request.getSession().getAttribute("user_id").toString()));
//		
//		model.addAttribute("GROUP_ADDR", detailsService.Addr(t_group_id) ); //?????? ??????
//		
//		session.setAttribute("tGroupId", t_group_id);
//		session.setAttribute("lang", LocaleContextHolder.getLocale());
//
//		//mav.addObject("trackerList", detailsService.trackerList(t_group_id));//????????? ????????? ??????
//		logger.info("::*************::" + t_group_id);
//		
//		
//		return "/User_View/User_index";
//	}
	
	@RequestMapping(value="/User_index")
	public ModelAndView index(@RequestParam(required=false, value="lang",  defaultValue="ko") String lang, Model model, HttpServletResponse response, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("tiles/User_View/User_index");
		DetailsDTO dto = new DetailsDTO();
		HttpSession session = request.getSession();
		
		String t_group_id = detailsService.User_Details(request.getSession().getAttribute("user_id").toString()).getT_group_id();
		
		dto.setT_group_id(t_group_id);
		mav.addObject("dto", detailsService.view(t_group_id));//?????????
		mav.addObject("weather", detailsService.group_weather(t_group_id));//???????????? 
		mav.addObject("sensor_date", detailsService.sensordate(t_group_id));//???????????????
		mav.addObject("t_alarm", translate_statusCode(detailsService.t_alarm(t_group_id)));//?????? ??????
		mav.addObject("tracker", detailsService.tracker(t_group_id));//????????? ????????? ??????
		mav.addObject("total_data", detailsService.total_data(t_group_id));//??????, ?????? ??????
		mav.addObject("inverter_date", detailsService.inverter_date(t_group_id));//?????? 
		mav.addObject("inverter_today", detailsService.inverter_today(t_group_id) ); //??? ?????????
		mav.addObject("Cumulative_power_generation", detailsService.Cumulative_power_generation(t_group_id) ); //??? ?????????
		mav.addObject("manageCluster", detailsService.User_Details(request.getSession().getAttribute("user_id").toString()));
		
		mav.addObject("GROUP_ADDR", detailsService.Addr(t_group_id) ); //?????? ??????
		
		session.setAttribute("tGroupId", t_group_id);
		session.setAttribute("_lang", lang);

		//mav.addObject("trackerList", detailsService.trackerList(t_group_id));//????????? ????????? ??????
		logger.info("::*************::" + t_group_id);
		
		
		return mav;
	}
	
	public List<DetailsDTO> translate_statusCode(List<DetailsDTO> src) {
		List<DetailsDTO> _list_dto = src;
		
		for(DetailsDTO _dto : _list_dto) {
			
			String _szStatus = new StringBuffer(Integer.toBinaryString(Integer.parseInt(_dto.getAlarm_status()))).reverse().toString();
			String _szTrans = "";
			
			if(Integer.parseInt(_dto.getAlarm_status()) > 7613 || Integer.parseInt(_dto.getAlarm_status()) < 0) {
				_szTrans = "?????? ?????? ????????? ??????";
			}
			
			else {
				for(int i = 0 ; i < _szStatus.length() ; i++) {
	
					
					/* REMS Alarm */
					if( _szStatus.charAt(i) == '1') {
						
						switch(i) {
							case 0:
								_szTrans += "?????????  ????????? ,";
								break;
							case 1:
								_szTrans += "????????????  ????????? ,";
								break;
							case 2:
								_szTrans += "????????????  ????????? ,";
								break;
							case 3:
								_szTrans += "????????????  ????????? ,";
								break;
							case 4:
								_szTrans += "????????? IGBT ?????? ,";
								break;
							case 5:
								_szTrans += "????????? ?????? ,";
								break;
							case 6:
								_szTrans += "?????? ????????? ,";
								break;
							case 7:
								_szTrans += "?????? ????????? ,";
								break;
							case 8:
								_szTrans += "?????? ????????? ,";
								break;
							case 9:
								_szTrans += "?????? ???????????? ,";
								break;
							case 10:
								_szTrans += "?????? ???????????? ,";
								break;
							case 11:
								_szTrans += "????????????(?????? ??????) ,";
								break;
							case 12:
								_szTrans += "??????(?????? ??????) ,";
								break;
							default:
								break;
						}				
					}
				}
				if(_szTrans.equals("")) _szTrans = "?????????";
				else _szTrans = _szTrans.substring(0,_szTrans.length()-1); 
			}
			
			_dto.setAlarm_contents(_szTrans);
		}
		
		return _list_dto;
	}
	
}
