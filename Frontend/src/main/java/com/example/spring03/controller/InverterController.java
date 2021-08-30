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
import com.example.spring03.inverter.dto.inverterDTO;
import com.example.spring03.inverter.service.InverterService;


@Controller
@RequestMapping("/inverterBoard/*")
public class InverterController {
	
	
	@Inject
	InverterService inverterService;
	
	@Inject 
	SessionLocaleResolver localeResolver; 
	@Inject 
	MessageSource messageSource;
	
	Logger logger = LoggerFactory.getLogger(InverterController.class);
	
	//인버터 목록 뿌리기
	@RequestMapping("/Inverter_Board")
	public ModelAndView List(@RequestParam(value="nPage", required=false, defaultValue="1") int nPage)throws Exception {
		ModelAndView mav = new ModelAndView("tiles/inverter_board/inverter_board");
		PageDto pageDto = new PageDto();
		inverterDTO dto = new inverterDTO();
		
		int totalCnt = inverterService.inverterListCount(); 
		
		if(nPage == 0) {
			nPage = 1;
		}
		
		pageDto.setNowPage(nPage);
		pageDto.setTotalCount(totalCnt);
		
		CommPaging commPage = new CommPaging(pageDto, 10, 10, "inverterList");
		
		dto.setStartRow(commPage.getStartRow());
		dto.setEndRow(commPage.getEndRow());
		
		mav.addObject("nowPage", commPage.getNowPage());
		mav.addObject("pageTag", commPage.resultString());
		mav.addObject("list", inverterService.listInverter(dto));
		return mav;
	}
	//등록 페이지
	@RequestMapping("Inverter_insert")
	public ModelAndView inverter_insert(ModelAndView mav)throws Exception {
		mav.setViewName("tiles/inverter_board/inverter_insert");
		mav.addObject("inverter_group_choice", inverterService.inverter_group_choice());
			logger.info("무엇이 들어갔을까?", mav);
		return mav;
	}
	
	
	//인버터 등록
		@RequestMapping("/insert")
		public String insert(@ModelAttribute inverterDTO dto, HttpSession session)throws Exception {
			//로그인한 사용자의 아이디
			inverterService.insertInverter(dto);
			return "redirect:/inverterBoard/Inverter_Board";
		}
		//상세보기
		@RequestMapping("Inverter_view")
		public ModelAndView view(@RequestParam String inverter_idx, Model model) throws Exception {
			ModelAndView mav = new ModelAndView("tiles/inverter_board/inverter_view");
			model.addAttribute(inverter_idx);
			
			mav.addObject("dto", inverterService.viewInsert(inverter_idx));
			mav.addObject("idx", inverter_idx);
			return mav;
		}
		
		
		
		@RequestMapping("update")//수정
		public String update(@ModelAttribute inverterDTO dto, Model model) throws Exception {
			inverterService.updateInverter(dto);
			return "redirect:/inverterBoard/Inverter_Board";
		}
		
		@RequestMapping("delete")//삭제
		public String delete(@RequestParam("inverter_idx") int[] inverter_idx, Model model){
			for(int user : inverter_idx) {
				System.out.println("사용자 삭제 = "+ user);
				int delete = inverterService.deleteView(user);
			}
			return "redirect:/inverterBoard/Inverter_Board";//목록으로 이동
	
		}
		// 회원 확인
		@ResponseBody
		@RequestMapping(value= "/idCheck", method = RequestMethod.POST)
		public int postIdCheck(HttpServletRequest req) throws Exception {
		 logger.info("post idCheck");
		 
		 String inverter_idx = req.getParameter("inverter_id");
		 inverterDTO idCheck =  inverterService.idCheck(inverter_idx);
		 
		 int result = 0;
		 
		 if(idCheck != null) {
			 
			 logger.info("ID를 사용할 수 있습니다.");
		  result = 1;
		  
		 } 
		 
		 return result;
		}
	
		
}
