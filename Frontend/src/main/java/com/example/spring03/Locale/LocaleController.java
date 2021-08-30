package com.example.spring03.Locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LocaleController {


	@RequestMapping(value = "/changeLocale")
    public String changeLocale(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String locale) {
        HttpSession session = request.getSession();
        Locale lo = null;
        
        //step. 파라메터에 따라서 로케일 생성, 기본은 KOREAN 
        if (locale.matches("en")) {
                lo = Locale.ENGLISH;
        } else {
                lo = Locale.KOREAN;
        }

        // step. Locale을 새로 설정한다.          session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, lo);
        // step. 해당 컨트롤러에게 요청을 보낸 주소로 돌아간다.
        String redirectURL = "redirect:" + request.getHeader("referer");
        return redirectURL;
    }


}


