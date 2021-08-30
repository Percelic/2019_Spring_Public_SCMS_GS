package com.example.spring03.auth.dao;

import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring03.User.dto.userDTO;
import com.example.spring03.auth.dto.authDTO;


@Repository
public class authDAOImpl  implements authDAO{

	@Inject
	SqlSession sqlSession;

	@Override
	public String loginCheck(authDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("auth.login_check", dto);
	}

	//로그인 
	@Override
	public Map<String, Object> selectUser(String user_id) {
		return sqlSession.selectOne("auth.selectUser",user_id); 
	}


	@Override
	public int insertUser(Map<String, String> map) {
		return sqlSession.insert("auth.insertUser", map);
	}
	
	@Override
	public int pwChk(Map<String, String> map) {
		return sqlSession.insert("auth.insertUser", map);
	}

	@Override
	public userDTO pwChkLoginChk(userDTO dto) {
		return sqlSession.selectOne("auth.pwChkLoginChk", dto);
	}
	
	@Override
	public int pwErrorUpdate(userDTO dto) {
		return sqlSession.update("auth.pwErrorUpdate", dto);
	}

	@Override
	public Date getDate() {
		return sqlSession.selectOne("auth.getDate");
	}

	@Override
	public int pwErrorReset(userDTO dto) {
		return sqlSession.update("auth.pwErrorReset", dto);
	}
	
	@Override
	public int pwReset(userDTO dto) {
		return sqlSession.update("auth.pwReset", dto);
	}

}
