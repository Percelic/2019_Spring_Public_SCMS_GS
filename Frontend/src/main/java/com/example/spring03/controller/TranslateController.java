package com.example.spring03.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.translate.service.TranslateService;

@Controller
@RequestMapping("/Translate/*")
public class TranslateController {

	
	
	@Inject
	private TranslateService tService;
	
	@RequestMapping("Trans")
	public String test() {
		return "Translate/Trans";
	}
	
	
	@RequestMapping("trans.do")
	public ModelAndView trans(@RequestParam(required=false) String text, String source, String target ) {
		ModelAndView mav = new ModelAndView();
		
		if(text!=null) {
		mav.addObject("result", tService.TranslateService(text, source, target));
		}
		mav.setViewName("Translate/Trans");
		return mav;
	}
}
