package com.example.spring03.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class UserDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException ade) 	throws IOException, ServletException {
	//req.setAttribute("errMsg", "관리자 전용 페이지입니다.");
	//req.getRequestDispatcher("/WEB-INF/views/User_auth/denied.jsp").forward(req, res);
		
	req.getRequestDispatcher("/WEB-INF/views/User_auth/Login.jsp").forward(req, res);
	}

}
