package com.example.spring03.controller;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Controller
public class indexController {
	
	private static final Logger logger = LoggerFactory.getLogger(indexController.class);

	
	@Autowired 
	private SessionLocaleResolver localeResolver; 
	
	@Autowired 
	private MessageSource messageSource; //message-context.xml 에 선언되어있는 bean id 값

	
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String index(Locale locale, HttpServletRequest request, Model model){
		model.addAttribute("siteCount", messageSource.getMessage("msg.test",null, locale));
		return "index";
	}
}