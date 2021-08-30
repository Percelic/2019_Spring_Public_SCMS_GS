package com.example.spring03.controller;

import java.beans.PropertyEditorSupport;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.auth.dao.authDAO;
import com.example.spring03.auth.dto.authDTO;
import com.example.spring03.auth.service.authService;

@Controller
public class auchController {

	Logger logger = LoggerFactory.getLogger(auchController.class);

	@Inject
	BCryptPasswordEncoder passwordEncoder;

	@Inject
	authService authService;

	@Inject
	private authDAO authDao;

	@RequestMapping("/auth/Login")
	public String login() {

		return "/auth/Login";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
	    binder.registerCustomEditor(MultipartFile.class, new PropertyEditorSupport() {
	        public void setAsText(String text) {
	            logger.debug("initBinder MultipartFile.class: {}; set null;", text);
	            setValue(null);
	        }
	    });
	}

	// 권한이 없는 사용자에게 안내 페이지 출력
	@RequestMapping("/auth/denied")
	public String denied(Model model, Authentication auth, HttpServletRequest req) {
		AccessDeniedException ade = (AccessDeniedException) req.getAttribute(WebAttributes.ACCESS_DENIED_403);
		model.addAttribute("errMsg", ade);
		return "auth/denied";
	}

	@RequestMapping("/auth/logout.do")
	public String admin_logout(HttpSession session) {
		session.invalidate();// 세션 초기화
		return "redirect:/User_auth/Login";
		/*
		 * authService.logout(session); mav.setViewName("auth/login1");
		 * mav.addObject("message","logout"); return mav;
		 */
	}
	@RequestMapping("/auth")
	public String listUser(Model model) {
		return "auth/main";
	}
	
	@ResponseBody
	@RequestMapping("/pwChk")
	public Map<String, Object> pwChk(HttpServletRequest req) {
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		PrivateKey key = (PrivateKey) req.getSession().getAttribute("__rsaPrivateKey__");
		
		String user_id = RSADecryption(key, req.getParameter("user_id"));
		String user_pw = RSADecryption(key, req.getParameter("user_pw"));
		
		rtMap = authService.pwChk(user_id, user_pw);
		return rtMap;
	}
	
	
	private String RSADecryption(PrivateKey key, String encrypted) {
		
		if(key == null) {
			//ra.addFlashAttribute("resultMsg","비정상적인 접근입니다.");
			//return "redirect:/"; //"/User_auth/Login";
			throw new UsernameNotFoundException(encrypted);
		}
		
		Cipher cipher;
		byte[] decryptedBytes;
		String decrypted = "";
		
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, key);
			decryptedBytes = cipher.doFinal(hexToByteArray(encrypted));
			decrypted = new String(decryptedBytes, "UTF-8");
		} catch(Exception e) {
			throw new UsernameNotFoundException(encrypted);
		}
		
		return decrypted;
	}
	
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

}