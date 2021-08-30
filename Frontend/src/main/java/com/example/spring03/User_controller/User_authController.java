package com.example.spring03.User_controller;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring03.User.dto.userDTO;
import com.example.spring03.User.service.userService;
import com.example.spring03.auth.dao.authDAO;
import com.example.spring03.auth.service.authService;
import com.example.spring03.controller.auchController;

@Controller
public class User_authController {

	Logger logger = LoggerFactory.getLogger(auchController.class);
	
	
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	@Inject
	authService authService;
	
	@Inject
	private authDAO authDao;
	
	@Inject
	private userService userService;  
	
	
	@RequestMapping("/User_auth/Login") //로그인 페이지
	public String user_Login(HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(1024);
		
		KeyPair keyPair = generator.generateKeyPair();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		
		HttpSession session = request.getSession();
		if(privateKey != null) {
			session.removeAttribute("__rsaPrivateKey__");
		}
		
		session.setAttribute("__rsaPrivateKey__", privateKey);
		
		RSAPublicKeySpec publicSpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		
		String publicKeyModulus = publicSpec.getModulus().toString(16);
		String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
		
		request.setAttribute("RSAModulus", publicKeyModulus);
		request.setAttribute("RSAExponent", publicKeyExponent);
		
		return "User_auth/Login";
	}
	@RequestMapping("/User_auth/join") // 회원가입
	public String user_join() {
		return "User_auth/join";
	}
	
	@RequestMapping("/")
	public String home()throws Exception {
		return "home";
	}
	
	@RequestMapping(value = "Check")
	@ResponseBody
	public String id() {
		
		return "";
	}
	
	/*
	 * //사용자 페이지 처리
	 * 
	 * @RequestMapping(value ="/userPage", method = RequestMethod.POST) public void
	 * userPOST(userDTO dto, HttpSession httpSession, Model model)throws Exception{
	 * 
	 * 
	 * userDTO user = userService.user(dto);
	 * 
	 * model.addAttribute("user", user.getT_group_id());
	 * 
	 * }
	 * 
	 * 
	 * @RequestMapping("/test") public String home1(Model model)throws Exception {
	 * return "home"; }
	 */
	
	
	
	private byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() % 2 != 0) {
            return new byte[] {};
        }
 
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            byte value = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
            bytes[(int) Math.floor(i / 2)] = value;
        }
        return bytes;
    }
	
	@RequestMapping("/User_auth/loginchk.do") 
	public void loginchk(@RequestParam String user_id, @RequestParam String user_pw, HttpSession session,RedirectAttributes ra, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("loooooogin checkkkk");
		//ModelAndView mav = new ModelAndView("forward:/User_auth/login_check.do"); 
		
		try {
			
//			PrivateKey key = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
//			
//			if(key == null) {
//				ra.addFlashAttribute("resultMsg","비정상적인 접근입니다.");
//				//return "redirect:/"; //"/User_auth/Login";	
//			}
//			session.removeAttribute("__rsaPrivateKey__"); 
//
//			Cipher cipher = Cipher.getInstance("RSA");
//			
//					
//			cipher.init(Cipher.DECRYPT_MODE, key);
//			byte[] decryptedBytes = cipher.doFinal(hexToByteArray(user_id));
//			String user_ID = new String(decryptedBytes, "UTF-8");
//			decryptedBytes = cipher.doFinal(hexToByteArray(user_pw));
//			String user_PW = new String(decryptedBytes, "UTF-8");
//			
//			
//			
//			
//			mav.addObject("user_id", user_ID);
//			mav.addObject("user_pw", user_PW);
//			
//			System.out.println("user_id : " + user_ID + " , user_pw : " + user_PW);

			/////
			//mav.addObject("user_id", user_id);
			//mav.addObject("user_pw",user_pw);
			request.setAttribute("user_id", user_id);
			request.setAttribute("user_pw", user_pw);
			
			request.getRequestDispatcher("/WEB-INF/views/User_auth/login_check.do").forward(request, response);
			
		} catch(Exception e) {
			ra.addFlashAttribute("resultMsg","비정상적인 접근입니다.");
			//return "redirect:/"; //"/User_auth/Login";
		}
	}
	

	
	//권한이 없는 사용자에게 안내 페이지 출력
	@RequestMapping("/User_auth/denied")
	public String denied(Model model, Authentication auth, 
			HttpServletRequest req) {
		
		AccessDeniedException ade = (AccessDeniedException)req.getAttribute(WebAttributes.ACCESS_DENIED_403);
		model.addAttribute("errMsg", ade);
		return "User_auth/denied";
	}

	@RequestMapping("/User_auth/join.do")
	public String join(Model model) {
		return "User_auth/join";
	}
	
	@ResponseBody
	@RequestMapping("/User_auth/pwReset")
	public Map<String, Object> pwChk(HttpServletRequest req) {
		Map<String, Object> rtMap = new HashMap<String, Object>();
		String user_id = req.getParameter("user_id");
		userDTO dto = new userDTO();
		dto.setUser_id(user_id);
		rtMap.put("result", authService.pwReset(dto));
		
		return rtMap;
	}
	
	@ResponseBody
	@RequestMapping("/User_auth/pwChange")
	public Map<String, Object> pwChange(@ModelAttribute userDTO dto) {
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		rtMap.put("result", authService.pwChange(dto));
		
		return rtMap;
	}
	
	//회원가입 처리 성공
		@RequestMapping("/User_auth/insert.do")
		public String insertUser(
				@RequestParam String user_id,
				@RequestParam String user_pw,
				@RequestParam String user_name,
				@RequestParam String authority) {
			
			Map<String, String> map = new HashMap<>();
			map.put("user_id", user_id);
			//비밀번호 암호화
			String encryptPassword = passwordEncoder.encode(user_pw);
			//맵으로 담아준다.
			map.put("user_pw",encryptPassword);
			map.put("user_name",user_name);
			map.put("authority",authority);
			authDao.insertUser(map);
			return "redirect:/";
		}
		
		@RequestMapping("/User_auth/logout.do")
		public String admin_logout(HttpSession session) {
		session.invalidate();//세션 초기화
		return "redirect:/";
		}

	
}
