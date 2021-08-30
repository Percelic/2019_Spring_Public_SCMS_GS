package com.example.spring03.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.example.spring03.CommPaging;
import com.example.spring03.PageDto;
import com.example.spring03.User.dto.userDTO;
import com.example.spring03.tracker.dto.trackerDTO;
import com.example.spring03.tracker.service.trackerService;

@Controller
@RequestMapping("trackerBoard")
public class TrackerController {
	
	private static final Logger logger = LoggerFactory.getLogger(TrackerController.class);
	
	@Inject
	trackerService trackerService;
	
	@Inject 
	SessionLocaleResolver localeResolver; 
	@Inject 
	MessageSource messageSource;
	
	@RequestMapping("Tracker_Board")
	public ModelAndView List(@RequestParam(value="nPage", required=false, defaultValue="1") int nPage)throws Exception {
		ModelAndView mav = new ModelAndView("tiles/tracker_board/tracker_board");
		PageDto pageDto = new PageDto();
		trackerDTO dto = new trackerDTO();
		
		int totalCnt = trackerService.trackerListCount(); 
		
		if(nPage == 0) {
			nPage = 1;
		}
		
		pageDto.setNowPage(nPage);
		pageDto.setTotalCount(totalCnt);
		
		CommPaging commPage = new CommPaging(pageDto, 10, 10, "trackerList");
		
		dto.setStartRow(commPage.getStartRow());
		dto.setEndRow(commPage.getEndRow());
		
		mav.addObject("nowPage", commPage.getNowPage());
		mav.addObject("pageTag", commPage.resultString());
		mav.addObject("list", trackerService.listTracker(dto));
		return mav;
	}
	//트래커 등록 페이지입니다.
	@RequestMapping("Tracker_Insert")
	public ModelAndView insert(ModelAndView mav) throws Exception {
		System.out.println("*********************************Tracker_Insert************");
		mav.setViewName("tiles/tracker_board/tracker_insert");
		mav.addObject("tracker_group_choice",  trackerService.tracker_group_choice());

		return mav;
	}
	//군집 등록
		@RequestMapping("insert")
		public String insert(@ModelAttribute trackerDTO dto) throws Exception {
			//세션에서 사용자아이디를 가져옴
			trackerService.insertlistTracker(dto);
			return "redirect:/trackerBoard/Tracker_Board";
		}
	//상세보기
	@RequestMapping("Tracker_view")
	public String view(@RequestParam String tracker_idx, Model model) throws Exception{
		model.addAttribute("dto", trackerService.view(tracker_idx));
		model.addAttribute("Add_Inverter", trackerService.Add_Inverter());
		return "tiles/tracker_board/tracker_view";
	}
	///////////////////////////////////////////////////////////////////////////////////
	//체크박스 삭제
	@RequestMapping("/delete")
	public String delete(@RequestParam("tracker_idx") int[] tracker_idx, Model model) {
		System.out.println("*********************************delete************");
		//삭제할 사용자 ID마다 반복해서 사용자 삭제
		for(int user : tracker_idx) {
			System.out.println("사용자 삭제 = "+ user);
			int delete =  trackerService.deleteTracker(user);
		}
		return "redirect:/trackerBoard/Tracker_Board";//목록으로 이동
		}
	
	@RequestMapping("update")
	public String update(@ModelAttribute trackerDTO dto, Model model)throws Exception {
		System.out.println("dto:: " + dto.toString());
		trackerService.updatelistTracker(dto);
		return "redirect:/trackerBoard/Tracker_Board";
	}
	
	
	//군집 확인
	@ResponseBody
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST)
	public int postIdCheck(@ModelAttribute trackerDTO dto) throws Exception{
		logger.info("tracker_idCheck");
		logger.info("dto.tracker_id() : " + dto.getTracker_idx());
		logger.info("dto.getT_group_id() : " + dto.getT_group_id());
		int result = trackerService.Tracker_Idcheck(dto);
		
		return result;
	}
}
