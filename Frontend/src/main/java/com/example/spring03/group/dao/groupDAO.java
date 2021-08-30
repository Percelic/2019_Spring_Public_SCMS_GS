package com.example.spring03.group.dao;

import java.util.List;
import java.util.Map;

import com.example.spring03.group.dto.groupDTO;
import com.example.spring03.inverter.dto.inverterDTO;


public interface groupDAO {
	
	public int groupListCount() throws Exception;
	 public List<groupDTO> listGroup(groupDTO dto)throws Exception;//군집 목록
	 public groupDTO detailGroup(int t_group_id)throws Exception;//군집 뷰~
	 
	 public void updateGroup(groupDTO dto)throws Exception;//군집 수정.
	 
	 public int deleteView(int t_group_id)throws Exception;//군집 삭제
	 
	 public void insertGroup(groupDTO dto)throws Exception; //군집 등록
	 
	 public groupDTO groupview(String t_group_id)throws Exception; // 군집 상세보기
	 
	 public List<groupDTO> inverter_group_choice() throws Exception;//군집명 넣어주기
	 
	 public groupDTO idCheck(String t_group_id)throws Exception; // 군집 아이디 중복
	 
	 public int CheckDuplication(String t_group_id);
	 
	 
	 //아이디 중복 검사
	 public int selectgroupID(Map<String, Object>map)throws Exception; 
	 
	 
	 // 19 07 09 아이디 중복 검사 | 군집명 중복 검사
		public int t_group_id(String t_group_id)throws Exception;
		public int t_group_name(String t_group_name)throws Exception;
	 
	 /////////////////////////////////////////일반 유저/////////////////////////////////////
		public int User_GroupListCount(String userID) throws Exception;
	 public List<groupDTO> User_group(String userID)throws Exception;
	 
	 
	 
	 
	 
	 
	 
	
}
