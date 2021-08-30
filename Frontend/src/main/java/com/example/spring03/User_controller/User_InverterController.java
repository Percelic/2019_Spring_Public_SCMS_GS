package com.example.spring03.User_controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.example.spring03.CommPaging;
import com.example.spring03.PageDto;
import com.example.spring03.inverter.dto.inverterDTO;
import com.example.spring03.inverter.service.InverterService;

@Controller
@RequestMapping("/User_inverterBoard/*")
public class User_InverterController {
	
	@Inject
	InverterService inverterService;
	
	@Inject 
	SessionLocaleResolver localeResolver; 
	@Inject 
	MessageSource messageSource;
	
	Logger logger = LoggerFactory.getLogger(User_InverterController.class);
	
	//인버터 목록 뿌리기
	@RequestMapping("/Inverter_Board")
	public ModelAndView List(@RequestParam(value="nPage", required=false, defaultValue="1") int nPage , @RequestParam(required=false, value="lang",  defaultValue="ko") String lang , HttpServletRequest request)throws Exception {
		ModelAndView mav = new ModelAndView("tiles/User_inverterBoard/Inverter_Board");
		PageDto pageDto = new PageDto();
		inverterDTO dto = new inverterDTO();
		
		String userID = request.getSession().getAttribute("user_id").toString();
		 
		int totalCnt = inverterService.User_InverterListCount(userID); 
		
		if(nPage == 0) {
			nPage = 1;
		}
		
		pageDto.setNowPage(nPage);
		pageDto.setTotalCount(totalCnt);
		
		CommPaging commPage = new CommPaging(pageDto, 10, 10, "inverterList");
		
		dto.setUserID(userID);
		dto.setStartRow(commPage.getStartRow());
		dto.setEndRow(commPage.getEndRow());
		
		mav.addObject("nowPage", commPage.getNowPage());
		mav.addObject("pageTag", commPage.resultString());
		
		mav.addObject("list", inverterService.User_Inverter(dto));
		mav.addObject("_lang",lang);
		//logger.info("인버터 정보"+ inverterService.User_Inverter(dto));
			
		return mav;
	}
}