package com.example.spring03.details.dao;

import java.util.List;


import com.example.spring03.details.dto.DetailsDTO;
import com.example.spring03.inverter.dto.inverterDTO;



public interface DetailsDAO {
	
	
	
	/*가로수 마을*/	
	public DetailsDTO A_dong(String t_group_id); //상세보기 + 발정량
	public DetailsDTO B_dong(String t_group_id); //상세보기 + 발정량
	public DetailsDTO C_dong(String t_group_id); //상세보기 + 발정량
	public DetailsDTO D_dong(String t_group_id); //상세보기 + 발정량
	public DetailsDTO E_dong(String t_group_id); //상세보기 + 발정량
	public DetailsDTO F_dong(String t_group_id); //상세보기 + 발정량
	/*g현재출력*/
	public DetailsDTO AA_dong(String t_group_id); //상세보기 + 발정량
	public DetailsDTO BB_dong(String t_group_id); //상세보기 + 발정량
	public DetailsDTO CC_dong(String t_group_id); //상세보기 + 발정량
	public DetailsDTO DD_dong(String t_group_id); //상세보기 + 발정량
	public DetailsDTO EE_dong(String t_group_id); //상세보기 + 발정량
	public DetailsDTO FF_dong(String t_group_id); //상세보기 + 발정량
	
	
	
	public DetailsDTO view (String t_group_id); //상세보기 + 발정량
	public DetailsDTO group_weather (String t_group_id);// 날씨 정보
	public DetailsDTO sensorDate (String t_group_id);// 센서데이터 정보
	public List<DetailsDTO> t_alarm (String t_group_id);// 고장 정보
	////////////////////////////////////////////////////////////////////////
	public DetailsDTO inverter_date(String t_group_id);// 전일출력  정보
	
	public DetailsDTO inverter_today(String t_group_id);// 현재 발전량  정보
	
	public DetailsDTO Cumulative_power_generation(String t_group_id);// 누적 발전량  정보

	///////////////////////////////////////////////////////////////////////////	
	
	public DetailsDTO inverter_avg (String t_group_id);// 발전량 퍼센트  정보
	
	
	public DetailsDTO Addr (String t_group_id);// 군집 테이블 : 주소 
	
	public List<DetailsDTO> tracker(String t_group_id);// 트래커  정보
	public List<DetailsDTO> trackerList(String t_group_id);
	public List<DetailsDTO> tracker_id();//군집명 넣어주기
	
	//////////////////////////////////////////////////////////
	public DetailsDTO total_data(String t_group_id); //총 누적과 현재 발전량 
	
	public DetailsDTO t_group_id(String t_group_id); //군집 아이디 누굴까요?
	
	///////////////////////////////////////////////////////////////
	public List<DetailsDTO> GoogleChart(); // 구글 차트 들어갑니다.
	
	
	
	public List<DetailsDTO> listDetails();
	//////////////////////////////////////////////////////////////////////
	//  User 정보 입력하기
	
	
	//User_id 검사
	public DetailsDTO User_Details(String user_id);
	
	//view 상세보기
	public DetailsDTO User_view(String t_group_id);

}
