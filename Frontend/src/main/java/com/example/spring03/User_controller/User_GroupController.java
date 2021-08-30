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
import com.example.spring03.controller.userBoardController;
import com.example.spring03.details.service.DetailsService;
import com.example.spring03.group.dto.groupDTO;
import com.example.spring03.group.service.groupService;

@Controller
@RequestMapping("/User_groupBoard/*")
public class User_GroupController {
	
	@Inject
	groupService groupService;
	
	@Inject
	DetailsService detailsService;
	
	@Inject 
	SessionLocaleResolver localeResolver; 
	@Inject 
	MessageSource messageSource;
	
	Logger logger = LoggerFactory.getLogger(userBoardController.class);
	
	@RequestMapping("/Group_Board")
	public ModelAndView List(@RequestParam(value="nPage", required=false, defaultValue="1") int nPage, @RequestParam(required=false, value="lang",  defaultValue="ko") String lang,HttpServletRequest request)throws Exception {
		ModelAndView mav = new ModelAndView("tiles/User_groupBoard/Group_Board");
		PageDto pageDto = new PageDto();
		groupDTO dto = new groupDTO();
		
		String userID = request.getSession().getAttribute("user_id").toString();
		
		int totalCnt = groupService.User_GroupListCount(userID); 
		
		if(nPage == 0) {
			nPage = 1;
		}
		
		pageDto.setNowPage(nPage);
		pageDto.setTotalCount(totalCnt);
		
		CommPaging commPage = new CommPaging(pageDto, 10, 10, "groupList");
		
		dto.setStartRow(commPage.getStartRow());
		dto.setEndRow(commPage.getEndRow());
		
		mav.addObject("nowPage", commPage.getNowPage());
		mav.addObject("pageTag", commPage.resultString());
		
		//mav.setViewName("/User_groupBoard/Group_Board");
		mav.addObject("list",groupService.User_group(userID));
		mav.addObject("manageCluster",detailsService.User_Details(userID));
		
		mav.addObject("_lang",lang);
		
		//logger.info("여기 무엇이 들어갈까?"+ mav);
		return mav;
	}

}
