package com.example.spring03.details.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Repository;

import com.example.spring03.details.dto.DetailsDTO;

@Repository
public class DetailsDAOImpl implements DetailsDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<DetailsDTO> listDetails() {
		
		return sqlSession.selectList("details.details_list_190916");
	}

	@Override // 상세보기
	public DetailsDTO view(String t_group_id) {
		
		return sqlSession.selectOne("details.view",t_group_id);
	}

	@Override // 날씨정보
	public DetailsDTO group_weather(String t_group_id) {
		
		return sqlSession.selectOne("details.group_weather", t_group_id);
	}
	//트래커 ID 리스트 넣어주기
	@Override
	public List<DetailsDTO> tracker_id() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("details.tracker_ID");
	}

	@Override // 센서 데이터
	public DetailsDTO sensorDate(String t_group_id) {
		
		return sqlSession.selectOne("details.sensordate_191022", t_group_id);
	}

	@Override // 알람정보
	public List<DetailsDTO> t_alarm(String t_group_id) {
		return sqlSession.selectList("details.t_alarm", t_group_id);
	}

	

	@Override //인버터 평균
	public DetailsDTO inverter_avg(String t_group_id) {
		
		return sqlSession.selectOne("details.inverter_avg", t_group_id);
	}
	@Override
	public List<DetailsDTO> tracker(String t_group_id) {
		return sqlSession.selectList("details.tracker",t_group_id);
	}

	@Override // 인버터 구글차트
	public List<DetailsDTO> GoogleChart() {
		return sqlSession.selectOne("details.googleChart");
	}
	
	@Override // 금일, 전일, 현재출력 발전량
	public DetailsDTO inverter_date(String t_group_id) {
		return sqlSession.selectOne("details.inverter_date", t_group_id);
	}
	@Override// 금일, 누적
	public DetailsDTO inverter_today(String t_group_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("details.inverter_today", t_group_id);
	}

	@Override
	public DetailsDTO total_data(String t_group_id) {
		return sqlSession.selectOne("details.total_data", t_group_id);
	}

	@Override
	public DetailsDTO t_group_id(String t_group_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("details.group_id", t_group_id);
	}

	@Override
	public DetailsDTO Cumulative_power_generation(String t_group_id) {
		return sqlSession.selectOne("details.Cumulative_power_generation_190927", t_group_id);
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////
	//유저 정보 넣기
	@Override
	public DetailsDTO User_view(String t_group_id) {
		
		return sqlSession.selectOne("details.User_View", t_group_id);
	}
	//일반 사용자 아이디
	@Override
	public DetailsDTO User_Details(String user_id) {
		return sqlSession.selectOne("details.User_Details", user_id);
	}
	//군집 테이블 - 주소
	@Override 
	public DetailsDTO Addr(String t_group_id) {
		return sqlSession.selectOne("details.Addr", t_group_id);
	}

	@Override
	public List<DetailsDTO> trackerList(String t_group_id) {
		return sqlSession.selectList("details.trackerList", t_group_id);
	}

	/*가로수 마을 누적 발전량*/
	@Override
	public DetailsDTO A_dong(String t_group_id) {
		return sqlSession.selectOne("details.A_dong", t_group_id);
	}

	@Override
	public DetailsDTO B_dong(String t_group_id) {
		return sqlSession.selectOne("details.B_dong", t_group_id);
	}

	@Override
	public DetailsDTO C_dong(String t_group_id) {
		return sqlSession.selectOne("details.C_dong", t_group_id);
	}

	@Override
	public DetailsDTO D_dong(String t_group_id) {
		return sqlSession.selectOne("details.D_dong", t_group_id);
	}

	@Override
	public DetailsDTO E_dong(String t_group_id) {
		return sqlSession.selectOne("details.E_dong", t_group_id);
	}

	@Override
	public DetailsDTO F_dong(String t_group_id) {
		return sqlSession.selectOne("details.F_dong", t_group_id);
	}
	@Override
	public DetailsDTO AA_dong(String t_group_id) {
		return sqlSession.selectOne("details.AA_dong", t_group_id);
	}
	
	@Override
	public DetailsDTO BB_dong(String t_group_id) {
		return sqlSession.selectOne("details.BB_dong", t_group_id);
	}
	
	@Override
	public DetailsDTO CC_dong(String t_group_id) {
		return sqlSession.selectOne("details.CC_dong", t_group_id);
	}
	
	@Override
	public DetailsDTO DD_dong(String t_group_id) {
		return sqlSession.selectOne("details.DD_dong", t_group_id);
	}
	
	@Override
	public DetailsDTO EE_dong(String t_group_id) {
		return sqlSession.selectOne("details.EE_dong", t_group_id);
	}
	
	@Override
	public DetailsDTO FF_dong(String t_group_id) {
		return sqlSession.selectOne("details.FF_dong", t_group_id);
	}

	
	

}