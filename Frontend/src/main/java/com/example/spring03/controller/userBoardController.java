package com.example.spring03.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring03.CommPaging;
import com.example.spring03.PageDto;
import com.example.spring03.User.dto.userDTO;
import com.example.spring03.User.service.userService;
import com.example.spring03.auth.dao.authDAO;
import com.example.spring03.auth.dto.authDTO;
import com.example.spring03.service.UserSha256;
import com.example.spring03.tracker.service.trackerService;

@Controller
@RequestMapping("/userBoard/*")
public class userBoardController {
	
	@Inject
	trackerService trackerService;

	@Inject
	userService userservice;

	@Inject
	authDAO authDao; // 매퍼.xml
	
	@Inject
	BCryptPasswordEncoder passwordEncoder;

	@Inject
	MessageSource messageSource;

	Logger logger = LoggerFactory.getLogger(userBoardController.class);

	// 회원가입 처리

	@RequestMapping(value = "/userBoard/User_Insert.do") 
	public String insertUser(HttpSession session, Model model,@RequestParam String user_id, @RequestParam String user_name,
			@RequestParam String user_phone, @RequestParam String user_email, @RequestParam String user_addr,
			@RequestParam String user_addr_1, @RequestParam String authority, @RequestParam String t_group_id) throws Exception {

		Map<String, String> map = new HashMap<>();
		userDTO dto = new userDTO();
		String encryPassword = passwordEncoder.encode("1234");
		
		/*map.put("user_id", user_id);
		map.put("user_pw", encryPassword);
		map.put("user_name", user_name);
		map.put("user_phone", user_phone);
		map.put("user_email", user_email);
		map.put("user_addr", user_addr);
		map.put("user_addr_1", user_addr_1); 
		map.put("authority", authority);
		map.put("group_name", group_name);
		authDao.insertUser(map);*/
		
		dto.setUser_id(user_id);
		dto.setUser_pw(encryPassword);
		dto.setUser_name(user_name);
		dto.setUser_phone(user_phone);
		dto.setUser_email(user_email);
		dto.setUser_addr(user_addr);
		dto.setUser_addr_1(user_addr_1);
		dto.setAuthority(authority);
		dto.setT_group_id(t_group_id);
		
		logger.info("t_group_id : " + t_group_id);
		
		userservice.insertUser(dto);
		return "redirect:/userBoard/User_Board";  

	}  
	
		// 등록 페이지

	@RequestMapping("/userBoard/User_Insert")
	public ModelAndView userinsert(ModelAndView mav) throws Exception {
		mav.setViewName("tiles/user_board/user_insert");
		mav.addObject("user_group_choice", userservice.user_group_choice());

		return mav;
	}

	// 회원목록
	@RequestMapping("/userBoard/User_Board")
	public ModelAndView user(@RequestParam(value="nPage", required=false, defaultValue="1") int nPage) throws Exception {
		ModelAndView mav = new ModelAndView("tiles/user_board/user_board");
		PageDto pageDto = new PageDto();
		userDTO dto = new userDTO();
		
		int totalCnt = userservice.userListCount(); 
		
		if(nPage == 0) {
			nPage = 1;
		}
		
		pageDto.setNowPage(nPage);
		pageDto.setTotalCount(totalCnt);
		
		CommPaging commPage = new CommPaging(pageDto, 10, 10, "userList");
		
		dto.setStartRow(commPage.getStartRow());
		dto.setEndRow(commPage.getEndRow());
		
		mav.addObject("nowPage", commPage.getNowPage());
		mav.addObject("pageTag", commPage.resultString());
		mav.addObject("list", userservice.listUser(dto));
		return mav;
	}

	// 상세보기 수정
	@RequestMapping("/userBoard/User_view")
	public String view(@RequestParam String user_id, Model model) throws Exception {
		model.addAttribute("dto", userservice.viewUser(user_id));
		model.addAttribute("user_group_choice", userservice.user_group_choice());
		return "tiles/user_board/user_view";
	}

	@RequestMapping("/update.do")
	public String update(@ModelAttribute userDTO dto, Model model) throws Exception {
		System.out.println("********update.do***************************");
		userservice.updateUser(dto); 
		return "redirect:/userBoard/User_Board";// 목록으로 이동
		/*
		 * if(result) {//비밀번호가 맞으면 }else { userDTO dto2 =
		 * userservice.viewUser(dto.getUser_id());
		 * dto.setUser_reg_date(dto2.getUser_reg_date());//날짜가 지워지지 않도록;
		 * model.addAttribute("dto", dto); model.addAttribute("message",
		 * "비밀번호가 일치하지 않습니다"); return "userBoard/User_view";//수정 페이지로 되돌아가자. }
		 */
	}

	@RequestMapping("/userBoard/delete")
	public String delete(@RequestParam(value="user_id", required=false) String[] user_id, Model model) throws Exception {
		// 삭제할 사용자 ID마다 반복해서 사용자 삭제
		
		if(user_id != null) { 
			for (String user : user_id) {
				System.out.println("사용자 삭제 = " + user);
				int delete = userservice.deleteUser(user);
			}
		}
		return "redirect:/userBoard/User_Board";// 목록으로 이동
		
	}

	/*
	 * 아이디
	 */
	// 회원 확인
		@ResponseBody
		@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
		public int postIdCheck(HttpServletRequest req) throws Exception{
			
			logger.info("post idCheck");
			String user_id = req.getParameter("user_id");
			 userDTO idCheck =  userservice.idCheck(user_id);
			
			int result = 0;
			
			if(idCheck !=null) {
				logger.info("유저 아이디 실행");
				result =1;
			}
			
			return result;
		}
		
		//id 중복 체크 컨트롤러
		@RequestMapping(value = "/userBoard/idCheck.do", method = RequestMethod.GET)
		@ResponseBody
		public int User_id(@RequestParam("user_id") String user_id)throws Exception{
			return userservice.user_id(user_id);
		}
} // 끝
