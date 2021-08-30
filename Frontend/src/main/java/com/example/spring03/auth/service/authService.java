package com.example.spring03.auth.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.example.spring03.User.dto.userDTO;
import com.example.spring03.auth.dto.authDTO;

public interface authService {

	
	public String loginCheck(authDTO dto);
	public void logout(HttpSession session);
	//비밀번호 체크
	public Map<String, Object> pwChk(String user_id, String user_pw);
	//비밀번호 리셋
	public int pwReset(userDTO dto);
	//비밀번호 변경
	public boolean pwChange(userDTO dto);
}
