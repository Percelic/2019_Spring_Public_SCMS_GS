package com.TrackerController;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Common.CustomQueue;
import com.Common.CustomSocket;


public class TrackerControlManager {
	static CustomQueue<Short> socketQueue;  		// for receive User request  --> vQ
	static CustomQueue<String> validQueue;		// for receive packetized User Req. --> pQ
	static CustomQueue<Object> protocolQueue;	// for receive instance of Protocol Data --> Socket Send
	static CustomQueue<Object> outputQueue;
	
	static TrackerControlDAO trackerControlDAO;
	static Logger logger;
	
	boolean bIsRun = true;
	
	static boolean bIsDebugMode = false;
	
	static final int SIZE_OF_PROTOCOL_CONTROL = 23; 			// Not Certified yet
	
	static final int MINIMUM_OF_PROTOCOL = 23;
	static final int MAXSIZE_OF_PROTOCOL = 23;
	
	static ConcurrentHashMap<Integer , String> 					 	tGroupInfoMap;
	static ConcurrentHashMap<String, ConcurrentHashMap<Integer, CustomSocket>>  tGroupDataMap;
	//static CustomQueue<> 
	
	
	TrackerControl_SocketConnector socketConn;
	TrackerControl_ValidationChecker validChecker;
	TrackerControl_DataParser dataParser;
	TrackerControl_ProtocolSender protocolSender;
	
	public TrackerControlManager(int _nPort, String _szDBIP, String _szDBPort, String _szDBID, String _szDBPW, String _szDBName) {
		System.out.println("[ TrackerController ] # TrackerControlManager Loaded.");
		System.setProperty("log4j.configurationFile",System.getProperty("user.dir") + "\\configuration.xml");		
		
		logger = LogManager.getRootLogger();
		trackerControlDAO = new TrackerControlDAO();
		trackerControlDAO.DB_Connect(_szDBIP, _szDBPort, _szDBID, _szDBPW, _szDBName);
		
		socketConn = new TrackerControl_SocketConnector(_nPort);
		validChecker = new TrackerControl_ValidationChecker();
		dataParser = new TrackerControl_DataParser();
		protocolSender = new TrackerControl_ProtocolSender(trackerControlDAO);
		
		
		socketQueue = new CustomQueue<Short>(Short.class, 1024 * 10);		// �� ����Ŭ �� ���� �޴� �������� ũ�⺸�� Ŀ�� ��.
		validQueue = new CustomQueue<String>(String.class, 128);
		protocolQueue = new CustomQueue<Object>(Object.class, 128);
		
		/* 1. Socket  */
		if(socketConn.Socket_Server_Open()) {
		socketConn.Socket_Server_SetPushQueue(socketQueue);
		socketConn.Socket_Server_Accept();
		
		/* 2. Get Socket Info From DCM */
		
		/* 3. Valid Checker */
		validChecker.SetPopQueue(socketQueue);
		validChecker.SetPushQueue(validQueue);
		validChecker.Valid_StartPopHandler();
		
		/* 4. DataParser */
		dataParser.SetPopQueue(validQueue);
		dataParser.SetPushQueue(protocolQueue);
		dataParser.DataParse_StartThread();
		
		/* 5. Protocol Sender */
		protocolSender.SetPopQueue(protocolQueue);
		protocolSender.Sender_StartPopHandler();
		
		bIsRun = true;
		}
		else { bIsRun = false; } 
		
	}
	
	public void TrackerControlManager_SetGroupInfo(ConcurrentHashMap<Integer , String > _tGroupInfoMap) {
		tGroupInfoMap = _tGroupInfoMap;
	}
	
	public void TrackerControlManager_SetGroupData(ConcurrentHashMap<String , ConcurrentHashMap<Integer, CustomSocket>>_tGroupDataMap) {
		tGroupDataMap = _tGroupDataMap;
	}
	
	public void TrackerControlManager_SetOutputQueue(CustomQueue<Object> _outputQueue) {
		outputQueue = _outputQueue;
	}
	
	public boolean GetIsRun() {
		return bIsRun;
	}
	
	// ��� 
	// 1. ����� ���� ���� 
	// 2. ���� ���� �޾ƿ���
	// 3. ��Ʈ�� ������ ����
	// 4. 
	
}
