package com.example.spring03.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.spring03.auth.dto.authDTO;

public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

	
	//로그인이 성공한 경우에 실행할 코드
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
			throws IOException, ServletException {
		HttpSession session = req.getSession();
		
		//Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		authDTO dto = (authDTO)auth.getPrincipal(); 
		String msg = auth.getName() + "";
		String authName = auth.getAuthorities() + "";
		
		session.setAttribute("msg", msg);
		session.setAttribute("auth", authName);
		session.setAttribute("path", req.getContextPath());
		session.setAttribute("cp", req.getContextPath());
		session.setAttribute("tGroupId", dto.getT_group_id());
		session.setAttribute("vendors", req.getContextPath() + "/resources/vendors");
		session.setAttribute("build", req.getContextPath() + "/resources/build");
		session.setAttribute("res", req.getContextPath() + "/resources/res");
		session.setAttribute("lib", req.getContextPath() + "/resources/lib");
		
		if(authName.equals("[ROLE_ADMIN]")) {
			res.sendRedirect(req.getContextPath() + "/View/Details");
		} else {
			session.setAttribute("user_id",dto.getUser_id());
			res.sendRedirect(req.getContextPath() + "/User_View/User_ClusterCheck.do");
		}
		
	}
	
}
