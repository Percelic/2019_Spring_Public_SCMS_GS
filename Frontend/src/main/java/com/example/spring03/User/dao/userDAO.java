package com.example.spring03.User.dao;

import java.util.List;
import java.util.Map;

import com.example.spring03.User.dto.userDTO;
import com.example.spring03.group.dto.groupDTO;
import com.example.spring03.tracker.dto.trackerDTO;

public interface userDAO {

	public int userListCount()throws Exception;
	public List<userDTO> listUser(userDTO dto)throws Exception;
	public userDTO viewUser(String user_id)throws Exception;
	public void updateUser(userDTO dto)throws Exception;
	public void updateTgroupUser(userDTO dto)throws Exception;
	public int deleteUser(String user_id)throws Exception;
	
	public void insertUser(userDTO dto)throws Exception;
	public void insertTgroupUser(userDTO dto)throws Exception;
	public boolean checkPw(String user_id, String user_pw)throws Exception; 
	 
	//아이디 중복검사
	public userDTO getUser (userDTO uDTO)throws Exception;
	
	public List<trackerDTO> user_group_choice()throws Exception;// 군집리스트 넣어주기
	
	
	public int user (Map<String, String> map) throws Exception;
	
	//회원가입
	public int insertUser(Map<String, String> map);
	
	
	public Map<String, Object> getUser(Map<String, Object> map);
	
	 public userDTO idCheck(String user_id)throws Exception; // 군집 아이디 중복
	 
	 
	 
	public int User_idCheck(String user_id) throws Exception;
	
	public int user_id(String user_id)throws Exception;
	public int selectTgroupUser(userDTO dto);
			 
		 
}
