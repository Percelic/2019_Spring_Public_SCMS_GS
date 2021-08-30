package com.TrackerController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Common.CustomQueue;
import com.Common.SecretInfo;

public class TrackerControlDAO {

	private final String DB_IP = SecretInfo.DCOLLECT_IP;
	private final int DB_Port = SecretInfo.DCOLLECT_PORT;
	private final String DB_USER = SecretInfo.DCOLLECT_ID;
	private final String DB_PW = SecretInfo.DCOLLECT_PW;
	private final String DB_DBName = SecretInfo.DCOLLECT_DBNAME;
	private String DB_DBConnectStr = String.format("jdbc:mysql://%s:%d/%s?serverTimezone=UTC", DB_IP,DB_Port,DB_DBName);
	
	private Connection mysql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private CustomQueue<Object> PopQueue;			// Protocol Class �뜝�룞�삕�뜝�룞�삕
	private int nQueryCount = 0;
	
	public TrackerControlDAO() {
		try {
			
		} catch(Exception e) {
			TrackerControlManager.logger.error("[ TrackerController ] Failed to Initialize DB Connector :: " + e.toString());
		}
	}
	
	public void DB_Connect(String _szDBIP, String _szDBPort, String _szDBID, String _szDBPW ,String _szDBName) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			if(_szDBIP != "" && _szDBPort != "" && _szDBID != "" && _szDBPW != "" && _szDBName != "") {
				try {
					TrackerControlManager.logger.debug("[ TrackerController ] Set DB Connect as Custom Settings");	
				DB_DBConnectStr = String.format("jdbc:mysql://%s:%d/%s?serverTimezone=UTC", _szDBIP, Integer.parseInt(_szDBPort), _szDBName);
				} 
				catch(NumberFormatException nfe) {
					TrackerControlManager.logger.warn("[ TrackerController ] TrackerControlManager DB Connect Parsing Error occured ! : " + nfe.toString() + "\n"
							+ "Set initial value automatically");
					DB_DBConnectStr = String.format("jdbc:mysql://%s:%d/%s?serverTimezone=UTC", _szDBIP, 3306, _szDBName);
				}
				
				mysql = DriverManager.getConnection(DB_DBConnectStr, _szDBID, _szDBPW);
			}
			
			else {
				mysql = DriverManager.getConnection(DB_DBConnectStr,DB_USER,DB_PW);
			}
			
			} catch(Exception e) {
				TrackerControlManager.logger.error("[ TrackerController ] Failed to Connect Database :: " + e.toString());
		}
	}
	
	public void DB_Disconnect() {
		try {
			rs.close();
			pstmt.close();
			mysql.close(); 
		} catch(Exception e) {
			TrackerControlManager.logger.error("[ TrackerController ] Failed to disconnect Database :: " + e.toString());
		}
	}
	
	public void DB_StartThread() {
		Thread thd = new Thread(new Runnable() {
			@Override
			public void run() {
				DB_ThreadRun();
			}
		});
		
		thd.start();
	}
	
	private void DB_ThreadRun() 
	{
		while(true) {
			try {
				
				synchronized(PopQueue) {
					if(!PopQueue.isEmpty()) 
					{
						TrackerControlManager.logger.debug("[ TrackerController ] Protocol Queue Count : " + PopQueue.IndexOfRear());
					}
				}
				
				Thread.sleep(1);
			} catch(Exception e) {
				TrackerControlManager.logger.error("[ TrackerController ] Failed to Insert Data into Database :: " + e.toString());
			}
		}
	}
	
	public void DB_UpdateGroupInfo(TrackerControl_Protocol _protocol) {
				
		String _sql = "";
		PreparedStatement pstmt1;
		
		switch(_protocol.GetData().getClass().getName()) {
		case "com.TrackerController.TrackerControl_Protocol_Control":
			TrackerControl_Protocol_Control tpc = (TrackerControl_Protocol_Control)_protocol.GetData();
			
			_sql = "Update t_group set T_Group_Mode = '?' WHERE T_GROUP_ID = ? AND T_Group_Mode <> '?'";
			
			try {
			pstmt1 = mysql.prepareStatement(_sql);
			pstmt1.setString(1, tpc.GetBody().getMode());
			pstmt1.setInt(2, tpc.GetHeader().getSI_ID());
			pstmt1.setString(3, tpc.GetBody().getMode());
			
			
			DB_UpdateQuery(pstmt1);
			nQueryCount++;
			System.out.print("");
			} catch(Exception e) {
				TrackerControlManager.logger.warn("[ TrackerController ] Failed to Update Statement for t_group :: " + e.toString()
				 + "\r\n [ stmt : " + _sql + " ]");
			}
			break;
		default:
			break;
		}
		
		TrackerControlManager.logger.debug("[ TrackerController ] QueryCount : " + nQueryCount + "\n");
		
	}
	
	public void DB_UpdateGroupInfo_Control(TrackerControl_Protocol_Control _protocol) {
		
		String _sql = "";
		PreparedStatement pstmt1;
		

			TrackerControl_Protocol_Control tpc = _protocol;
			
			_sql = "Update t_group set T_Group_Mode = ? WHERE T_GROUP_ID = ? ";
			
			try {
			pstmt1 = mysql.prepareStatement(_sql);
			pstmt1.setString(1, tpc.GetBody().getMode());
			pstmt1.setInt(2, tpc.GetHeader().getSI_ID());
//			pstmt1.setString(3, tpc.GetBody().getMode());
			
			
			DB_UpdateQuery(pstmt1);
			nQueryCount++;
			System.out.print("");
			} catch(Exception e) {
				TrackerControlManager.logger.warn("[ TrackerController ] Failed to Update Statement for t_group :: " + e.toString()
				 + "\r\n [ stmt : " + _sql + " ]");
			}
		
		TrackerControlManager.logger.debug("[ TrackerController ] QueryCount : " + nQueryCount + "\n");
		
	}
	
	private synchronized void DB_InsertQuery(PreparedStatement _pstmt) {
		try {	
			TrackerControlManager.logger.debug("[ TrackerController ]" + _pstmt.toString() + " DB Insert Result : " + _pstmt.execute());
		} catch(SQLException se) {
			TrackerControlManager.logger.error("[ TrackerController ] DB_UpdateQuery error occured ! : " + se.toString());
		}
	}
	
	private synchronized void DB_UpdateQuery(PreparedStatement _pstmt) {
		
		try {
			_pstmt.executeUpdate();
		} catch(Exception e) {
			TrackerControlManager.logger.error("[ TrackerController ] DB_UpdateQuery error occured ! : " + e.toString());
		}
	}
	
	public void SetPopQueue(CustomQueue<Object> _cq) {
		PopQueue = _cq;
	}
}
