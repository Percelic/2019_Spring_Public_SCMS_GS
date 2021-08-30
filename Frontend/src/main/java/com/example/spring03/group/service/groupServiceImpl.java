package com.example.spring03.group.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.spring03.group.dao.groupDAO;
import com.example.spring03.group.dto.groupDTO;

@Service
public class groupServiceImpl implements groupService {
	
	Logger logger = LoggerFactory.getLogger(groupServiceImpl.class);
	
	@Inject
	groupDAO GroupDao;
	
	@Override
	public int groupListCount() throws Exception {
		return GroupDao.groupListCount();
	}
	@Override
	public List<groupDTO> listGroup(groupDTO dto)throws Exception {
		return GroupDao.listGroup(dto);
	}

	@Override
	public groupDTO detailGroup(int t_group_id)throws Exception {
		return null;
	}

	@Override
	public void updateGroup(groupDTO dto)throws Exception {
	 	GroupDao.updateGroup(dto);
	}

	@Override
	public int deleteGroup(int t_group_id)throws Exception {
		return GroupDao.deleteView(t_group_id);
	}

	

	@Override
	public groupDTO groupview(String t_group_id)throws Exception {
		return GroupDao.groupview(t_group_id);
	}

	@Override
	public void insertGroup(groupDTO dto)throws Exception {
		GroupDao.insertGroup(dto);
	}


	@Override // 아이디 중복
	public groupDTO idCheck(String t_group_id)throws Exception {
		return GroupDao.idCheck(t_group_id);
	}

	@Override
	public List<groupDTO> inverter_group_choice() throws Exception {
		return GroupDao.inverter_group_choice();
	}
	//카운트

	@Override
	public int User_GroupListCount(String userID) throws Exception {
		// TODO Auto-generated method stub
		return GroupDao.User_GroupListCount(userID);
	}
	
	@Override
	public List<groupDTO> User_group(String userID) throws Exception {
		// TODO Auto-generated method stub
		return GroupDao.User_group(userID);
	}

	@Override 
	public int t_group_id(String t_group_id) throws Exception {
		return GroupDao.t_group_id(t_group_id);
	}

	@Override
	public int t_group_name(String t_group_name) throws Exception {
		return GroupDao.t_group_name(t_group_name);
	}






	

}
