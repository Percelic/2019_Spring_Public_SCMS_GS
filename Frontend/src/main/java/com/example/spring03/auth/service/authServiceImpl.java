package com.example.spring03.auth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring03.User.dto.userDTO;
import com.example.spring03.auth.dao.authDAO;
import com.example.spring03.auth.dto.authDTO;

@Service
public class authServiceImpl implements authService {

	@Inject
	authDAO authDao;
	
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	/*
	 * @Override public void loginCheck(authDTO dto) {
	 * 
	 * String encode_password = LoginUtil.encryptPassword(dto.getUser_id(),
	 * dto.getUser_pw()); dto.setUser_pw(encode_password);
	 * 
	 * 
	 * }
	 */

	
	 @Override 
	 public String loginCheck(authDTO dto) {
	 
	 return authDao.loginCheck(dto);
	 
	 }
	
	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@Transactional
	@Override
	public Map<String, Object> pwChk(String user_id, String user_pw) {
		Map<String, Object> rtMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		userDTO dto = new userDTO();
		int pwErrorCnt = 0;
		String userPw = "";
		Date pwErrorDt;
		Date nPwErrorDt;
		Date userUpdateDt = null;
		String encryPassword = "";
		long sec = 0;
		boolean loginSuccess = true;

		rtMap.put("idYn", true);//id 존재여부 - true: 있음,  false: 없음
		rtMap.put("idStat", true);//계정상태 - true :활성화, false : 비활성화
		rtMap.put("pwCnt", 0);//비밀번호 틀린 횟수
		rtMap.put("userUpdateDt", false);//admin의 기본 비밀번호가 변경되었는지 확인을 위한 값
		
		map = authDao.selectUser(user_id);
		
		if(map != null) {
			userPw = (String)map.get("password");
			pwErrorCnt = (int)(map.get("pw_error_cnt"));
			pwErrorDt = (Date)(map.get("pw_error_dt"));
			nPwErrorDt = authDao.getDate();
			userUpdateDt = (Date)(map.get("user_upt_date"));
			encryPassword = passwordEncoder.encode(user_pw);
			
			dto.setUser_id(user_id);
			dto.setUser_pw(encryPassword);
			
			//3 min chk
			if(pwErrorDt != null) {
				long diff = pwErrorDt.getTime() - nPwErrorDt.getTime();
				sec = diff / 60000;
			}
			
			if(pwErrorCnt > 4 && sec < -3) {
				//비밀번호 초기화
				authDao.pwErrorReset(dto);
				pwErrorCnt = 0;
			}
			
			//기존 로직
			if(pwErrorCnt < 5) {
				if(passwordEncoder.matches(user_pw, userPw) == false) {
					pwErrorCnt++;
					dto.setPw_error_cnt(pwErrorCnt);
					authDao.pwErrorUpdate(dto);
					loginSuccess = false;
				}
				
			} else {
				//계정 잠긴상태
				rtMap.put("idStat", false);
				loginSuccess = false;
			}
		} else {
			rtMap.put("idYn", false);
			loginSuccess = false;
		}
		
		System.out.println("==============================" + loginSuccess);
		
		rtMap.put("pwCnt", pwErrorCnt);
		
		if(loginSuccess) {
			if(userUpdateDt == null) {
				rtMap.put("userUpdateDt", true);
			}
			authDao.pwErrorReset(dto);
		}
		
		rtMap.put("loginSuccess", loginSuccess);//로그인 성공 여부
		
		return rtMap;
	}

	@Override
	public int pwReset(userDTO dto) {
		String encryPassword = passwordEncoder.encode("1234");
		
		dto.setUser_pw(encryPassword);
		dto.setResetChk("reset");
		return authDao.pwReset(dto);
	}
	
	
	@Override
	public boolean pwChange(userDTO dto) {
		Map<String, Object> rtMap = new HashMap<String, Object>();
		boolean result = false;
		String inputPwd = dto.getUser_pw();
		String newInputPwd = dto.getUser_pw2();
		
		rtMap = authDao.selectUser(dto.getUser_id());
		
		if(rtMap != null) {
			result = passwordEncoder.matches(inputPwd, (String)rtMap.get("password"));
			if(result) {
				dto.setUser_pw(passwordEncoder.encode(newInputPwd));
				authDao.pwReset(dto);
			}
		}
		
		
		return result;
	}
}
