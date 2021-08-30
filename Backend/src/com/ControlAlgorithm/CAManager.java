package com.ControlAlgorithm;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Common.CustomQueue;
import com.Common.CustomSocket;
import com.Common.Utils;


//////////////
//
//	1. use ControlAlgorithm Module as add-on; not Core Module.
//  2. Main function of CA is.. 
//		First  ) Collect R,S protocol from DC. 
//		Second ) Load group info from DB periodically ( .. about 15 min ).
//		Third  ) Compare a socket that sent R,S protocol info and loaded group info. ( Especially, group ID and Alg.Mode of Group. )
//		Fourth ) Make each tracker of Group proper work that depends on Alg.Mode of groups.
//
//////////////


public class CAManager {
	
	static Logger logger;
	
	static final int SIZE_OF_PROTOCOL_SHADOW			= 32;	// R Response : 34 maybe
	static final int SIZE_OF_PROTOCOL_SHADOW_ADC		= 62;	// S Response : 62 maybe
	
	static final int MINIMUM_OF_PROTOCOL = Utils.Minimum( SIZE_OF_PROTOCOL_SHADOW, SIZE_OF_PROTOCOL_SHADOW_ADC);
	static final int MAXSIZE_OF_PROTOCOL = Utils.Maximum( SIZE_OF_PROTOCOL_SHADOW, SIZE_OF_PROTOCOL_SHADOW_ADC);

	static final boolean CHECK_CS = true;
	
	static CustomQueue<Object> 													transferQueue;
	static ConcurrentHashMap<Integer, String> 									tGroupMode;
	static ConcurrentHashMap<Integer , String> 					 				tGroupInfoMap;
	static ConcurrentHashMap<String, ConcurrentHashMap<Integer, CustomSocket>>  tGroupDataMap;
	
	//static CA_DataParser dataParser;
	static CA_PeriodProtocolSender periodProtocolSender;
	static CA_ProtocolProcessor protocolProcessor;
	
	
	public CAManager() {
		// TODO Auto-generated constructor stub
		
		System.setProperty("log4j.configurationFile",System.getProperty("user.dir") + "/configuration.xml");		
		
		logger = LogManager.getRootLogger();
		
		transferQueue = new CustomQueue<Object>(Object.class, 128);
		
		tGroupMode = new ConcurrentHashMap<Integer, String>();
		tGroupInfoMap = new ConcurrentHashMap<Integer, String>();
		tGroupDataMap = new ConcurrentHashMap<String, ConcurrentHashMap<Integer,CustomSocket>>();
		
		//dataParser = new CA_DataParser();
		//dataParser.CA_DataParse_StartThread();
		
		periodProtocolSender = new CA_PeriodProtocolSender();
		periodProtocolSender.Sender_StartPeriodHandler();
		
		protocolProcessor = new CA_ProtocolProcessor();
		//protocolProcessor.SetGroupInfoMap(tGroupInfoMap);
		//protocolProcessor.SetGroupDataMap(tGroupDataMap);
		
		protocolProcessor.start();
		
	}

	public void CAManager_SetTransferQueue(CustomQueue<Object> _tq) {
		transferQueue = _tq;
		//dataParser.SetPopQueue(transferQueue);
		//dataParser.SetPushQueue(processQueue);
		
		protocolProcessor.SetPopQueue(transferQueue);
	}
	
	public void CAManager_SetTModeData(ConcurrentHashMap<Integer, String> _tGroupMode) {
		tGroupMode = _tGroupMode;
	}
	
	public void CAManager_SetGroupInfo(ConcurrentHashMap<Integer , String> _tGroupInfoMap) {
		tGroupInfoMap = _tGroupInfoMap;
	}
	
	public void CAManager_SetGroupData(ConcurrentHashMap<String , ConcurrentHashMap<Integer, CustomSocket>> _tGroupDataMap) {
		tGroupDataMap = _tGroupDataMap;
	}
	
}
