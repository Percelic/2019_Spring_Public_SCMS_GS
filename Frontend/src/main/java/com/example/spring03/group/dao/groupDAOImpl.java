package com.example.spring03.group.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.spring03.controller.InverterController;
import com.example.spring03.group.dto.groupDTO;
import com.example.spring03.inverter.dto.inverterDTO;

@Repository
public class groupDAOImpl implements groupDAO {
	
	Logger logger = LoggerFactory.getLogger(groupDAOImpl.class);
	
	@Inject
	SqlSession sqlSession;
	
	
	@Override
	public int groupListCount() throws Exception {
		return sqlSession.selectOne("group.groupListCount");
	}
	@Override
	public List<groupDTO> listGroup(groupDTO dto)throws Exception {
		return sqlSession.selectList("group.select", dto);
	}

	@Override
	public groupDTO detailGroup(int t_group_id)throws Exception {
		return null;
	}

	@Override
	public void updateGroup(groupDTO dto)throws Exception {
		sqlSession.update("group.update",dto);
	}

	



	@Override
	public groupDTO groupview(String t_group_id)throws Exception {
		return sqlSession.selectOne("group.group_view", t_group_id);
	}

	@Override
	public int deleteView(int t_group_id)throws Exception {
		return sqlSession.delete("group.delete", t_group_id);
	}

	@Override
	public void insertGroup(groupDTO dto)throws Exception {
		sqlSession.insert("group.insert",dto);
	}

	@Override
	public List<groupDTO> inverter_group_choice() throws Exception {
		return sqlSession.selectList("group.inverter_group_choice");
		
	}
	
	@Override //아이디 중복 체크
	public groupDTO idCheck(String t_group_id)throws Exception {
		return sqlSession.selectOne("group.IDCheck", t_group_id);
	}
	
	// 아이디 중복 체크
		public int CheckDuplication(String t_group_id) {
			
			int idCount = sqlSession.selectOne("group.checkDuplicationId", t_group_id.replace("=", ""));
			return idCount;
		}

		@SuppressWarnings("unchecked")
		public int selectgroupID(Map<String, Object> map) throws Exception {
			Map<String, Object> resultMap = (Map<String, Object>)sqlSession.selectOne("group.selectGroupID", map);
			int result = Integer.valueOf(String.valueOf(resultMap.get("RESULT")));
			
			return result;
		}

/////////일반 유저 ///////////////////////////////////
		
		@Override
		public int User_GroupListCount(String userID) throws Exception {
			// TODO Auto-generated method stub
			return sqlSession.selectOne("group.User_groupListCount_191010",userID);
		}
		
		@Override
		public List<groupDTO> User_group(String userID) throws Exception {
			// TODO Auto-generated method stub
			return sqlSession.selectList("group.User_group_191010",userID);
		}

		@Override
		public int t_group_id(String t_group_id) throws Exception {
			return sqlSession.selectOne("group.GroupIDcheck",t_group_id);
		}

		@Override
		public int t_group_name(String t_group_name) throws Exception {
			// TODO Auto-generated method stub
			return sqlSession.selectOne("group.GroupNamecheck",t_group_name);
		}







	

	

}
