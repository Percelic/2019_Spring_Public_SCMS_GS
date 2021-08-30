package com.example.spring03.User.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.example.spring03.User.dao.userDAO;
import com.example.spring03.User.dto.userDTO;
import com.example.spring03.tracker.dto.trackerDTO;

@Service
public class userServiceImpl implements userService {
	
	@Inject
	userDAO userDao;
	
	@Inject
	userService userservice;
	
	@Override
	public int userListCount() throws Exception {
		return userDao.userListCount();
	}
	@Override //목록 뿌리기
	public List<userDTO> listUser(userDTO dto)throws Exception {
		return userDao.listUser(dto);
	}
	//게시판 상세보기 만들기.
	@Override
	public userDTO viewUser(String user_id)throws Exception {
		return userDao.viewUser(user_id);
	}
	
	//상세보기 + 등록
	@Override
	public void insertUser(userDTO dto)throws Exception {
		userDao.insertUser(dto);
		userDao.insertTgroupUser(dto);
	}
	
	//상세보기 + 수정
	@Override
	public void updateUser(userDTO dto)throws Exception {
		userDao.updateUser(dto);
		
		int rt = userDao.selectTgroupUser(dto);
		System.out.println("rt : " + rt);
		if(rt == 0) {
			userDao.insertTgroupUser(dto);
		} else {
			userDao.updateTgroupUser(dto);
		}
	}
	
	//상세보기 + 삭제
	@Override
	public int deleteUser(String user_id)throws Exception {
		return userDao.deleteUser(user_id);
	}
	// 판별하는 것.
	
	  @Override 
	  public boolean checkPw(String user_id, String user_pw)throws Exception { 
		  return userDao.checkPw(user_id, user_pw); 
	  }
	 
	@Override
	public userDTO getUser(userDTO uDTO)throws Exception {
		return userDao.getUser(uDTO);
	}
	@Override
	public List<trackerDTO> user_group_choice() throws Exception {
		return userDao.user_group_choice();
	}
	@Override
	public userDTO user(userDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public userDTO idCheck(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return userDao.idCheck(user_id);
	}
	@Override
	public int User_idCheck(String user_id)throws Exception {
		return userDao.User_idCheck(user_id);
	}
	 
	@Override
	public int user_id(String user_id) throws Exception {
		return userDao.user_id(user_id);
	}
}
