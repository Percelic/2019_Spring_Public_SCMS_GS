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
import com.example.spring03.tracker.dto.trackerDTO;
import com.example.spring03.tracker.service.trackerService;

@Controller
@RequestMapping("/User_trackerBoard/*")
public class User_TrackerController {
	
	private static final Logger logger = LoggerFactory.getLogger(User_TrackerController.class);
	
	@Inject
	trackerService trackerService;
	
	@Inject 
	SessionLocaleResolver localeResolver; 
	@Inject 
	MessageSource messageSource;
	
	@RequestMapping("Tracker_Board")
	public ModelAndView List(@RequestParam(value="nPage", required=false, defaultValue="1") int nPage, @RequestParam(required=false, value="lang",  defaultValue="ko") String lang , HttpServletRequest request)throws Exception {
		ModelAndView mav = new ModelAndView("tiles/User_trackerBoard/Tracker_Board");
		PageDto pageDto = new PageDto();
		trackerDTO dto = new trackerDTO();
		
		String userID = request.getSession().getAttribute("user_id").toString();
		
		int totalCnt = trackerService.User_TrackerListCount(userID); 
		
		if(nPage == 0) {
			nPage = 1;
		}
		
		pageDto.setNowPage(nPage);
		pageDto.setTotalCount(totalCnt);
		
		CommPaging commPage = new CommPaging(pageDto, 10, 10, "trackerList");
		
		//mav.setViewName("/User_trackerBoard/Tracker_Board");
		//dto.setUserID(userID);
		dto.setStartRow(commPage.getStartRow());
		dto.setEndRow(commPage.getEndRow());
		
		mav.addObject("nowPage", commPage.getNowPage());
		mav.addObject("pageTag", commPage.resultString());
		
		
		mav.addObject("list", trackerService.User_tracker(userID));
		mav.addObject("_lang", lang);
		//logger.info("트래커 정보"+ trackerService.User_tracker(userID));
		return mav;
	}
}
