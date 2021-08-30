package com.example.spring03.details.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring03.details.dao.DetailsDAO;
import com.example.spring03.details.dto.DetailsDTO;

@Service
public class DetailsServiceImpl implements DetailsService {
	
	@Inject
	DetailsDAO DetailsDao;
	
	

	@Override
	public DetailsDTO view(String t_group_id) {
		return DetailsDao.view(t_group_id);
	}

	@Override
	public List<DetailsDTO> listDetails() {
		return DetailsDao.listDetails();
	}

	@Override
	public DetailsDTO group_weather(String t_group_id) {
		
		return DetailsDao.group_weather(t_group_id);
	}

	@Override
	public DetailsDTO sensordate(String t_group_id) {
	
		return DetailsDao.sensorDate(t_group_id);
	}

	@Override
	public List<DetailsDTO> t_alarm(String t_group_id) {
		
		return DetailsDao.t_alarm(t_group_id);
	}

	@Override
	public DetailsDTO inverter_date(String t_group_id) {
		
		return DetailsDao.inverter_date(t_group_id);
	}

	@Override
	public DetailsDTO inverter_avg(String t_group_id) {
		
		return DetailsDao.inverter_avg(t_group_id);
	}
	@Override
	public List<DetailsDTO> tracker(String t_group_id) {
		
		return DetailsDao.tracker(t_group_id);
	}

	@Override //구글차트 들어가는지 확인
	public List<DetailsDTO> GoogleChart() {
		return DetailsDao.GoogleChart();
	}


	@Override // 누적 발전량
	public DetailsDTO inverter_today(String t_group_id) {
		return DetailsDao.inverter_today(t_group_id);
	}

	@Override //누적과 현재발전량을 나타내는 것 같습니다.
	public DetailsDTO total_data(String t_group_id) {
		// TODO Auto-generated method stub
		return DetailsDao.total_data(t_group_id);
	}

	@Override
	public DetailsDTO t_group_id(String t_group_id) {
		return DetailsDao.t_group_id(t_group_id);
	}

	@Override
	public DetailsDTO Cumulative_power_generation(String t_group_id) {
		return DetailsDao.Cumulative_power_generation(t_group_id);
	}

	
	//////////////////////////////////////////////////////////////////유저정보 넣기
	//유저 상세보기
	@Override
	public DetailsDTO User_view(String t_group_id) {
		return DetailsDao.User_view(t_group_id);
	}
	//일반 사용자 넣기
	@Override
	public DetailsDTO User_Details(String user_id) {
		return DetailsDao.User_Details(user_id);
	}

	@Override //군집 주소
	public DetailsDTO Addr(String t_group_id) {
		// TODO Auto-generated method stub
		return DetailsDao.Addr(t_group_id);
	}

	@Override
	public List<DetailsDTO> trackerList(String t_group_id) {
		return DetailsDao.trackerList(t_group_id);
	}

	/********************가로수 마을 누적 발전량*/
	@Override
	public DetailsDTO A_dong(String t_group_id) {
		return DetailsDao.A_dong(t_group_id);
	}

	@Override
	public DetailsDTO B_dong(String t_group_id) {
		// TODO Auto-generated method stub
		return DetailsDao.B_dong(t_group_id);
	}

	@Override
	public DetailsDTO C_dong(String t_group_id) {
		return DetailsDao.C_dong(t_group_id);
	}

	@Override
	public DetailsDTO D_dong(String t_group_id) {
		return DetailsDao.D_dong(t_group_id);
	}

	@Override
	public DetailsDTO E_dong(String t_group_id) {
		return DetailsDao.E_dong(t_group_id);
	}

	@Override
	public DetailsDTO F_dong(String t_group_id) {
		return DetailsDao.F_dong(t_group_id);
	}
	/********************가로수 마을 누적 발전량*/
	@Override
	public DetailsDTO AA_dong(String t_group_id) {
		return DetailsDao.AA_dong(t_group_id);
	}
	
	@Override
	public DetailsDTO BB_dong(String t_group_id) {
		// TODO Auto-generated method stub
		return DetailsDao.BB_dong(t_group_id);
	}
	
	@Override
	public DetailsDTO CC_dong(String t_group_id) {
		return DetailsDao.CC_dong(t_group_id);
	}
	
	@Override
	public DetailsDTO DD_dong(String t_group_id) {
		return DetailsDao.DD_dong(t_group_id);
	}
	
	@Override
	public DetailsDTO EE_dong(String t_group_id) {
		return DetailsDao.EE_dong(t_group_id);
	}
	
	@Override
	public DetailsDTO FF_dong(String t_group_id) {
		return DetailsDao.FF_dong(t_group_id);
	}

	@Override
	public List<DetailsDTO> tracker_id() {
		return DetailsDao.tracker_id();
	}


}
