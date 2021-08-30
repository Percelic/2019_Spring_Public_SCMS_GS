package com.example.spring03.auth.dao;

import java.util.Date;
import java.util.Map;

import com.example.spring03.User.dto.userDTO;
import com.example.spring03.auth.dto.authDTO;

public interface authDAO {
	
	
	//회원강비
	public int insertUser(Map<String, String> map);
	
	
	public String loginCheck(authDTO dto); // 기존 로그인 현황
	
	//회원 상세 정보
	public Map<String, Object> selectUser(String user_id);
	
	//비밀번호 체크
	public int pwChk(Map<String, String> map);
	
	public userDTO pwChkLoginChk(userDTO dto);
	
	public int pwErrorUpdate(userDTO dto);
	
	public Date getDate();
	
	public int pwErrorReset(userDTO dto);
	
	public int pwReset(userDTO dto);
	
}