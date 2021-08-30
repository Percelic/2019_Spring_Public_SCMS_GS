package com.example.spring03.tracker.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring03.tracker.dao.trackerDAO;
import com.example.spring03.tracker.dto.trackerDTO;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Service
public class trackerServiceImpl implements trackerService {

	@Inject
	trackerDAO trackerDao;
	

	@Override
	public int trackerListCount() throws Exception {
		return trackerDao.trackerListCount();
	}
	
	@Override
	public List<trackerDTO> listTracker(trackerDTO dto) throws Exception {
		return trackerDao.listTracker(dto);
	}

	@Override
	public trackerDTO detaillistTracker(int tracker_idx) throws Exception {
		return null;
	}

	@Override
	public void updatelistTracker(trackerDTO dto) throws Exception {
		trackerDao.updateTracker(dto);
	}

	

	@Override
	public void insertlistTracker(trackerDTO dto) throws Exception {
		trackerDao.insertTracker(dto);
	}

	@Override
	public trackerDTO view(String tracker_idx) throws Exception {
		return trackerDao.view(tracker_idx);
	}

	@Override //체크박스 삭제하기.
	public int deleteTracker(int tracker_idx){
		return  trackerDao.deleteTracker(tracker_idx);
	}

	@Override
	public List<trackerDTO> tracker_group_choice() throws Exception {
		return trackerDao.tracker_group_choice();
	}

	
	@Override
	public int Tracker_Idcheck(trackerDTO dto) throws Exception {
		return trackerDao.Tracker_Idcheck(dto);
	}


	@Override
	public int t_tracker(trackerDTO dto)throws Exception {
		return trackerDao.t_tracker(dto);
	}

	@Override
	public List<trackerDTO> Add_Inverter() throws Exception {
		return trackerDao.Add_Inverter();
	}

	@Override
	public int User_TrackerListCount(String userID) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<trackerDTO> User_tracker(String userID) throws Exception {
		return trackerDao.User_tracker(userID);
	}

}