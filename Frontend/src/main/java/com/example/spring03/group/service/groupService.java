package com.example.spring03.group.service;

import java.util.List;
import java.util.Map;

import com.example.spring03.group.dto.groupDTO;
import com.example.spring03.inverter.dto.inverterDTO;

public interface groupService {

	public int groupListCount() throws Exception;
	public List<groupDTO> listGroup(groupDTO dto)throws Exception;

	public groupDTO detailGroup(int t_group_id)throws Exception;

	public void updateGroup(groupDTO dto)throws Exception;// 군집 수정

	public int deleteGroup(int t_group_id)throws Exception;// 군집 삭제

	public void insertGroup(groupDTO dto)throws Exception; // 군집 등록

	public groupDTO groupview(String t_group_id)throws Exception; // 군집 상세보기

	public groupDTO idCheck(String t_group_id)throws Exception; // 군집 아이디 중복
	
	 public List<groupDTO> inverter_group_choice() throws Exception;//군집명 넣어주기
	 

		/*
		 * 19.07.09 아이디 중복검사 
		 * */
		public int t_group_id(String t_group_id)throws Exception;
		public int t_group_name(String t_group_name)throws Exception;
	 
	 
	 /////////////////////////////////////////일반 유저/////////////////////////////////////
		public int User_GroupListCount(String userID) throws Exception;
	 public List<groupDTO> User_group(String userID)throws Exception;
	 
		
		
		
		
	 
	 
	 
}
 