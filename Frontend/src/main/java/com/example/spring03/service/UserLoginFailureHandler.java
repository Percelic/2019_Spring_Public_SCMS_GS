package com.example.spring03.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

//로그인 실패 했을 떄의 처리
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		HttpSession session = request.getSession();
		// request 영역에 변수 저장
		request.setAttribute("errMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		// forward
		request.getRequestDispatcher("/WEB-INF/views/User_auth/Login.jsp").forward(request, response);
	}

	/*
	 * @Override public void onAuthenticationFailure(HttpServletRequest request
	 * ,HttpServletResponse response ,AuthenticationException exception) throws
	 * IOException ,ServletException {
	 * 
	 * HttpSession session = request.getSession();
	 * logger.info("LoginFailureHandler AuthenticationFailureHandler");
	 * session.setAttribute("SS_LOGIN_SF", "F");
	 * 
	 * ObjectMapper om = new ObjectMapper();
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>(); map.put("result",
	 * false); map.put("message", exception.getMessage());
	 * 
	 * String jsonString = om.writeValueAsString(map);
	 * 
	 * OutputStream out = response.getOutputStream();
	 * out.write(jsonString.getBytes()); }
	 */
}
