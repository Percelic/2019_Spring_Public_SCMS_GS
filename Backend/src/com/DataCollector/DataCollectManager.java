package com.DataCollector;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Common.CustomQueue;
import com.Common.CustomSocket;
import com.Common.Utils;

public class DataCollectManager implements Closeable {
		static Logger logger;
		static DataCollectDAO dataCollectDAO;
		static SocketManager socketManager;
		
		static CustomQueue<Integer> socketQueue;		// �������� ���� ������ ����        ( Byte ���� ���� )
		static CustomQueue<Integer[]> validQueue;		// Valid�� ó���� Packet ���� ( Packet ���� ���� )
		static CustomQueue<Object> protocolQueue;		// Packet ������ Protocol Objectȭ �Ͽ� ���� ( Protocol ���� ���� )
		static CustomQueue<Object> transferQueue;		// for communicate TrackerController Module.		
		
		static ConcurrentHashMap<Integer, String> tGroupInfoMap;
		static ConcurrentHashMap<String, ConcurrentHashMap<Integer , CustomSocket >> tGroupDataMap;
		
		static ConcurrentHashMap<Integer, String> tGroupModeMap;
		
		static String[] AreaCodeNameArr;
		
		static Collection<CustomSocket>	tConnectionPool;
		
		boolean bIsRun = true;
		
		static final int SIZE_OF_PROTOCOL_SINGLE_GENERATION = 52;	//51;
		static final int SIZE_OF_PROTOCOL_TRIPLE_GENERATION = 64;	//65;
		static final int SIZE_OF_PROTOCOL_CONTROL 			= 23; 	//23 ,Not Certified yet
		static final int SIZE_OF_PROTOCOL_SENSOR 			= 48;	//51 --> //48;
		static final int SIZE_OF_PROTOCOL_SHADOW			= 32;	// R Response : 34 maybe
		static final int SIZE_OF_PROTOCOL_SHADOW_ADC		= 62;	// S Response : 62 maybe
		
		static final int MINIMUM_OF_PROTOCOL = Utils.Minimum(SIZE_OF_PROTOCOL_SINGLE_GENERATION, SIZE_OF_PROTOCOL_TRIPLE_GENERATION, SIZE_OF_PROTOCOL_SENSOR, SIZE_OF_PROTOCOL_CONTROL, SIZE_OF_PROTOCOL_SHADOW, SIZE_OF_PROTOCOL_SHADOW_ADC);
		static final int MAXSIZE_OF_PROTOCOL = Utils.Maximum(SIZE_OF_PROTOCOL_SINGLE_GENERATION, SIZE_OF_PROTOCOL_TRIPLE_GENERATION, SIZE_OF_PROTOCOL_SENSOR, SIZE_OF_PROTOCOL_CONTROL, SIZE_OF_PROTOCOL_SHADOW, SIZE_OF_PROTOCOL_SHADOW_ADC);
		
		static final String PATH_OF_PROTOCOL_SINGLEGEN 		= DataCollect_Protocol_SingleGen.class.getName();
		static final String PATH_OF_PROTOCOL_TRIPLEGEN 		= DataCollect_Protocol_TripleGen.class.getName();
		static final String PATH_OF_PROTOCOL_SENSORDATA 	= DataCollect_Protocol_Sensor.class.getName();
		static final String PATH_OF_PROTOCOL_STATUS 		= DataCollect_Protocol_Status.class.getName();
		
		
		// MODE ALARM METHOD  :: 
		// 1 : Alarm when first Data send
		// 2 : Alarm when Faultcode occured and change into normal
		static final int MODE_ALARM_METHOD = 2;
		static final boolean CHECK_CS = true;
		static final int NUMBER_OF_AREACODE = 168;
		
		SocketConnector socketConn;
		ValidationChecker validChecker; 
		DataParser dataParser;
		DataCollect_WeatherCollector weatherCollector;
		
	public DataCollectManager(int _nPort, String _szDBIP, String _szDBPort, String _szDBID, String _szDBPW, String _szDBName) {
		System.out.println("[ DataCollector ] # DataCollectManager Loaded.");
		
		logger = LogManager.getRootLogger();
		dataCollectDAO = new DataCollectDAO();
		if(dataCollectDAO.DB_Connect(_szDBIP, _szDBPort, _szDBID, _szDBPW, _szDBName)) {
		
			AreaCodeNameArr = new String[NUMBER_OF_AREACODE];
			
			socketConn = new SocketConnector(_nPort);
			socketQueue = new CustomQueue<Integer>(Integer.class,1024 * 25);
			validQueue = new CustomQueue<Integer[]>(Integer[].class, 128);
			protocolQueue = new CustomQueue<Object>(Object.class, 128);
			transferQueue = new CustomQueue<Object>(Object.class, 128);
			
			validChecker = new ValidationChecker();
			
			dataParser = new DataParser();
			
			socketManager = new SocketManager(dataCollectDAO);
			weatherCollector = new DataCollect_WeatherCollector();
			
			tGroupInfoMap = new ConcurrentHashMap<Integer, String>();
			tGroupDataMap = new ConcurrentHashMap<String, ConcurrentHashMap<Integer, CustomSocket>>();
			tGroupModeMap = new ConcurrentHashMap<Integer, String>();
			
			socketManager.SocketManager_SetInfoMap(tGroupInfoMap);
			socketManager.SocketManager_SetDataMap(tGroupDataMap);
			socketManager.SocketManager_SetModeMap(tGroupModeMap);
			
			if(socketManager.SocketManager_InitData()) {
			
				/* 1. Socket  */
				if(socketConn.Socket_Server_Open()) {			
					socketConn.Socket_SetSocketManager(socketManager);
					socketConn.Socket_Server_SetPushQueue(socketQueue);
					socketConn.Socket_Server_Accept(socketManager);
					
					/* 2. Valid Check */
					validChecker.SetPopQueue(socketQueue);
					validChecker.SetPushQueue(validQueue);
					validChecker.Valid_StartPopHandler();
					
					/* 3. Data Parse */
					dataParser.SetPopQueue(validQueue);
					dataParser.SetPushQueue(protocolQueue);
					dataParser.SetTransQueue(transferQueue);
					dataParser.DataParse_StartThread();
					
					/* 4. DB Connector */
					dataCollectDAO.SetPopQueue(protocolQueue);
					dataCollectDAO.DB_StartThread();
					
					/* 5. Weather Collector */
					weatherCollector.WeatherCollector_ConnectDBUseAsDAO(dataCollectDAO);
					weatherCollector.AreaCodeNameSetting(AreaCodeNameArr);
					weatherCollector.start();
					
					bIsRun = true;
				}
				
				else { bIsRun = false; }
			}
		
		}
		
		else { bIsRun = false; }
	}
	
	@Override
	public void close() throws IOException {
	// TODO Auto-generated method stub
		try {
			
			dataCollectDAO.DB_Disconnect();
			dataCollectDAO.close();
			dataCollectDAO = null;
			
			AreaCodeNameArr = null;
			
			//
			socketConn.Socket_Server_Close();
			socketConn = null;
			
			
			socketQueue = null;
			validQueue = null;
			protocolQueue = null;
			transferQueue = null;
			
			validChecker.close();
			validChecker = null;
		
			dataParser.close();
			dataParser = null;
			
			socketManager.close();
			weatherCollector.close();
			
			tGroupInfoMap = null;
			tGroupDataMap = null;
			tGroupModeMap = null;
			
			} catch (Exception e) {
				DataCollectManager.logger.error("[ DataCollector ] Error occured while closing DataCollectManager :: " + e.toString());
			}
	}
	
	public ConcurrentHashMap<Integer, String> DataCollectManager_GetGroupInfo() {
		return tGroupInfoMap;
	}
	
	public ConcurrentHashMap<String, ConcurrentHashMap<Integer, CustomSocket>> DataCollectManager_GetGroupData() {
		return tGroupDataMap;
	}
	
	public CustomQueue<Object> DataCollectManager_GetTransferQueue() {
		return transferQueue;
	}
	
	public ConcurrentHashMap<Integer, String> DataCollectManager_GetGroupMode() {
		return tGroupModeMap;
	}
	
	public boolean GetIsRun() {
		return bIsRun;
	}
}
