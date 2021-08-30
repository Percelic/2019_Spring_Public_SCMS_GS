package com.example.spring03.User.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.example.spring03.User.dto.userDTO;
import com.example.spring03.tracker.dto.trackerDTO;

public interface userService {
	public int userListCount()throws Exception;
	public List<userDTO> listUser(userDTO dto)throws Exception;
	public userDTO viewUser(String user_id)throws Exception;
	public void updateUser(userDTO dto)throws Exception;
	
	public int deleteUser(String user_id)throws Exception;
	
	public void insertUser(userDTO dto)throws Exception;
	 public boolean checkPw(String user_id, String user_pw)throws Exception; 
	//아이디 중복검사 만듬.
	public userDTO getUser (userDTO uDTO)throws Exception;
	
	public List<trackerDTO> user_group_choice() throws Exception;// 군집리스트 넣어주기
	
	public userDTO user (userDTO dto) throws Exception;
	
	 public userDTO idCheck(String user_id)throws Exception; // 군집 아이디 중복
	 
	 public int user_id(String user_id)throws Exception; 
	public int User_idCheck(String user_id) throws Exception; 
	
	
	
	
	
}
 