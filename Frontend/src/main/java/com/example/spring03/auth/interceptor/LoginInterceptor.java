package com.example.spring03.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.spring03.auth.dto.authDTO;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	boolean masterFlag = false;
	
	 @Override
	    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
	            throws Exception {
	        
		 //세션 객체 생성
	        HttpSession session = req.getSession();
	        //세션에 ID가 없으면 null이면
	        if(session.getAttribute("user_id") == null) {
	        	//로그인 페이지 이동
	        	res.sendRedirect(req.getContextPath()+"/auth/admin_Login");
	        	//컨트롤러 실행하지 않는다.(오청페이지로 이동 하지 않는다.
	        	return false;
	        	// null이 아니면
	        	
	        }else {
	        	//컨트롤러 실행(요청페이지로 이동한다
	        	return true;
	        }
	 }

	 	//컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메서드
	 @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	            ModelAndView modelAndView) throws Exception {
	        super.postHandle(request, response, handler, modelAndView);
	    }
	 @Override
	 public void afterCompletion(HttpServletRequest request, 
	                             HttpServletResponse response, 
	                             Object handler, 
	                             Exception ex) throws Exception {
	    super.afterCompletion(request, response, handler, ex);
	 } 
	 
	 
	    
	    
	}
