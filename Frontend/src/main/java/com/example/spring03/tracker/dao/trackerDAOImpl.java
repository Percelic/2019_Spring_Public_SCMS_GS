package com.example.spring03.tracker.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring03.inverter.dto.inverterDTO;
import com.example.spring03.tracker.dto.trackerDTO;
@Repository
public class trackerDAOImpl implements trackerDAO{

	@Inject
	SqlSession sqlSession;
	
	
	@Override
	public int trackerListCount() throws Exception {
		return sqlSession.selectOne("tracker.trackerListCount");
	}
	@Override
	public List<trackerDTO> listTracker(trackerDTO dto)throws Exception {
		return sqlSession.selectList("tracker.select_190916", dto);
	}

	@Override
	public trackerDTO detailTracker(int tracker_idx)throws Exception {
		return null;
	}

	@Override
	public void updateTracker(trackerDTO dto)throws Exception {
		
		sqlSession.update("tracker.update_190916", dto);
	}

	@Override 
	public int deleteTracker(int tracker_idx){
		return sqlSession.delete("tracker.delete", tracker_idx);
		
	}

	@Override
	public void insertTracker(trackerDTO dto)throws Exception {
		sqlSession.insert("tracker.tracker_insert_190920", dto);
	}

	@Override
	public trackerDTO view(String tracker_idx) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("tracker.view_190916",tracker_idx);
	} 

	@Override
	public List<trackerDTO> tracker_group_choice() throws Exception {
		return sqlSession.selectList("tracker.tracker_group_choice");
	}

	@Override
	public int t_tracker(trackerDTO dto)throws Exception {
		return sqlSession.selectOne("tracker.t_tracker",dto);
	}

	@Override
	public List<trackerDTO> Add_Inverter() throws Exception {
		return sqlSession.selectList("tracker.Add_Inverter");
	}
	
	////////////////////일반 유저/////////////////////////////
	@Override
	public List<trackerDTO> User_TrackerListCount(String userID) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("tracker.User_TrackerListCount_191010",userID);
	}
	
	@Override
	public List<trackerDTO> User_tracker(String userID) throws Exception {
		return sqlSession.selectList("tracker.User_Tracker_List_191010",userID);
	}
	@Override
	public int Tracker_Idcheck(trackerDTO dto) throws Exception {
		return sqlSession.selectOne("tracker.Tracker_Idcheck",dto);
	}

}


	

	

