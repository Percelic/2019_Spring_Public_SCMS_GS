package com.example.spring03.inverter.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring03.inverter.dao.InverterDAO;
import com.example.spring03.inverter.dto.inverterDTO;

@Service
public class InverterServiceImpl implements InverterService {

	@Inject
	InverterDAO inverterDao;
	
	
	@Override
	public int inverterListCount() throws Exception {
		return inverterDao.inverterListCount();
	}
	@Override // 인버터 목록
	public List<inverterDTO> listInverter(inverterDTO dto)throws Exception {
		return inverterDao.listInverter(dto);
	}
	
	@Override //인버터 등록할 것입니다.
	public void insertInverter(inverterDTO dto)throws Exception {
		inverterDao.insertInverter(dto);
	}

	@Override
	public inverterDTO viewInsert(String inverter_idx)throws Exception {
		return inverterDao.viewInsert(inverter_idx);
	}

	@Override
	public void updateInverter(inverterDTO dto)throws Exception {
		inverterDao.updateInverter(dto);
	}

	@Override
	public int deleteView(int inverter_idx) {
		return inverterDao.deleteView(inverter_idx);
	}

	@Override
	public List<inverterDTO> inverter_group_choice() throws Exception {
		return inverterDao.inverter_group_choice();
	}
		
	@Override // 아이디 중복체크
	public inverterDTO idCheck(String inverter_idx) {
		return inverterDao.idCheck(inverter_idx);
	}

	@Override
	public inverterDTO group_idCheck(String t_group_id) throws Exception {
		return inverterDao.group_idCheck(t_group_id);
	}

	@Override
	public void deleteInverter(inverterDTO dto) throws Exception {
		inverterDao.deeleteInverter(dto);
	}

	@Override
	public int User_InverterListCount(String userID) throws Exception {
		// TODO Auto-generated method stub
		return inverterDao.User_inverterListCount(userID);
	}
	
	@Override
	public List<inverterDTO> User_Inverter(inverterDTO dto) throws Exception {
//		return inverterDao.User_Inverter(userID);
		return inverterDao.User_Inverter(dto);
	}
 


	


	
}
