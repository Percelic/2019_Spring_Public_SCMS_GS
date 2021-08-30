package com.example.spring03.tracker.service;

import java.util.List;
import java.util.Map;

import com.example.spring03.tracker.dto.trackerDTO;

public interface trackerService {
	public int trackerListCount() throws Exception;
	public List<trackerDTO> listTracker(trackerDTO dto)throws Exception;// 게시판 목록
	
	public trackerDTO detaillistTracker(int tracker_idx)throws Exception;
	
	
	public void updatelistTracker(trackerDTO dto)throws Exception;
	

	public void insertlistTracker(trackerDTO dto)throws Exception;
	
	public trackerDTO view(String tracker_idx)throws Exception;
	
	public int deleteTracker(int tracker_idx) ;
	
	public List<trackerDTO> tracker_group_choice() throws Exception;
	public int Tracker_Idcheck(trackerDTO dto) throws Exception;//아이디 중복 체크
	
	public int t_tracker(trackerDTO dto) throws Exception;// 트래커 아이디 찾기
	
	public List<trackerDTO> Add_Inverter()throws Exception; // 인버터 추가 기능.
	
	 /////////////////////////////////////////일반 유저/////////////////////////////////////
	public int User_TrackerListCount(String userID) throws Exception;
	 public List<trackerDTO> User_tracker(String userID)throws Exception;
	
	
}
