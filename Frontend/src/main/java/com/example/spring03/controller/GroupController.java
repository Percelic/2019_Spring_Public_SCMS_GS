package com.example.spring03.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.CommPaging;
import com.example.spring03.PageDto;
import com.example.spring03.group.dto.groupDTO;
import com.example.spring03.group.service.groupService;

@Controller
@RequestMapping("/groupBoard/*")
public class GroupController {
	
	@Inject
	groupService groupService;
	
	
	Logger logger = LoggerFactory.getLogger(userBoardController.class);
	
	@RequestMapping("/Group_Board")
	public ModelAndView List(@RequestParam(value="nPage", required=false, defaultValue="1") int nPage)throws Exception {
		ModelAndView mav = new ModelAndView("tiles/group_board/group_board");
		PageDto pageDto = new PageDto();
		groupDTO dto = new groupDTO();
		
		int totalCnt = groupService.groupListCount(); 
		
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
		mav.addObject("list",groupService.listGroup(dto));
		return mav;
	}
	//군집 등록 페이지 
	@RequestMapping("/Group_Insert")
	public ModelAndView insert(ModelAndView mav)throws Exception {
		mav.setViewName("tiles/group_board/group_insert");
		mav.addObject("inverter_group_choice", groupService.inverter_group_choice());
		return mav;
	}
	
	//군집 등록
	@RequestMapping(value = "/groupBoard/Group_Insert.do")
	public String insert(@ModelAttribute groupDTO dto, HttpSession session, Model model)throws Exception {
		//세션에서 사용자아이디를 가져옴
		groupService.insertGroup(dto);
		logger.info("여기 무엇이 들어갈까?"+ dto);
		return "redirect:/groupBoard/Group_Board";
	}
	
	@RequestMapping("/groupBoard/Group_Update.do") // 수정용
	public String update(@ModelAttribute groupDTO dto, Model model)throws Exception {
		 groupService.updateGroup(dto);
		return "redirect:/groupBoard/Group_Board";
	}
	
	//군집 상세페이지
	@RequestMapping("Group_view")
	public String view(@RequestParam String t_group_id, Model modelMap)throws Exception {
		groupDTO groupDto = groupService.groupview(t_group_id);
		modelMap.addAttribute("dto", groupDto);
		return "tiles/group_board/group_view";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("t_group_id") int[] t_group_id, Model model, groupDTO dto)throws Exception {
		//삭제할 사용자 ID마다 반복해서 사용자 삭제
		int count =0;
		
		for(int user : t_group_id) {
			System.out.println("사용자 삭제 = "+ user);
			int delete = groupService.deleteGroup(user);
		}
		return "redirect:/groupBoard/Group_Board";//목록으로 이동
		}
	
	
	/*
	 * @Param T_group_id / T_group_name
	 * @Day : 19.07.09
	 * @Skill : ID 중복 / Name 중복
	 * */
		@RequestMapping(value = "/groupBoard/idCheck.do", method = RequestMethod.GET)
		@ResponseBody
		public int t_group_id(HttpServletRequest request) throws Exception {
			request.setCharacterEncoding("utf-8");
			String t_group_id = request.getParameter("t_group_id");
			
			return groupService.t_group_id(t_group_id);
		}
		
		
		@RequestMapping(value = "/groupBoard/t_group_name.do", method = RequestMethod.GET)
		@ResponseBody
		public int t_group_name(HttpServletRequest request) throws Exception{
			request.setCharacterEncoding("utf-8");
			String tGroupNm = request.getParameter("t_group_name");
			
//			@RequestParam("t_group_name") String t_group_name) 
			System.out.println("===================================d" + tGroupNm);
			return groupService.t_group_name(tGroupNm);
		}

	
	
	
	
}
