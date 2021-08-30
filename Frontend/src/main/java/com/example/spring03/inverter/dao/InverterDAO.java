package com.example.spring03.inverter.dao;

import java.util.List;

import com.example.spring03.inverter.dto.inverterDTO;

public interface InverterDAO {
	
	public int inverterListCount() throws Exception;
	 public List<inverterDTO> listInverter(inverterDTO dto) throws Exception;//목록
	 public void insertInverter(inverterDTO dto) throws Exception;//저장
	 public inverterDTO viewInsert(String inverter_idx)throws Exception;//상세보기
	 
	 public void updateInverter(inverterDTO dto)throws Exception;//수정
	 
	 public int deleteView(int inverter_idx);//체크박스 삭제
	 
	 
	 public List<inverterDTO> inverter_group_choice() throws Exception;//군집명 넣어주기
	 
	 public inverterDTO idCheck(String inverter_idx);// 인버터 아이디 찾기
	 
	 public inverterDTO group_idCheck(String t_group_id) throws Exception;// 군집 아이디 중복확인
	 
	 public void deeleteInverter(inverterDTO dto) throws Exception; // 인버터 삭제 테스트
	 
	 /////////////////////////////////////////일반 유저/////////////////////////////////////
	 public int User_inverterListCount(String userID) throws Exception;
	 
	 public List<inverterDTO> User_Inverter(inverterDTO dto)throws Exception;
	 

	
}
