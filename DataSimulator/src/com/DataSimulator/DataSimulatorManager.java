package com.DataSimulator;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataSimulatorManager {
	
	public static final int SIZE_OF_PROTOCOL_SINGLE_GENERATION = 53;
	public static final int SIZE_OF_PROTOCOL_TRIPLE_GENERATION = 65;
	public static final int SIZE_OF_PROTOCOL_SENSOR 			= 51;
	public static final int SIZE_OF_PROTOCOL_SHADOW				= 34;	// R Response : 34 maybe
	public static final int SIZE_OF_PROTOCOL_SHADOW_ADC			= 62;	// S Response : 62 maybe
	
	public static Logger logger;
	public static boolean bShowCS = true;
	
	private HashMap<String,String> hshConfig = new HashMap<String, String>();
	
	private Map<Integer,DataSimulator_TrackerData> hshTrackerSet 	= new HashMap<Integer,DataSimulator_TrackerData>();
	protected static CustomQueue<DataSimulator_MessageData> messageQueue 	= new CustomQueue<DataSimulator_MessageData>(DataSimulator_MessageData.class,64);
	private DataSimulator_CommandHandler cmdHandler;
	
	public DataSimulatorManager() {
		// TODO Auto-generated constructor stub
		
		try {
			//System.setProperty("log4j.configurationFile",System.getProperty("user.dir") + "\\configuration.xml");
			
			logger = LogManager.getRootLogger();
			
			// Default Settings
			hshConfig.put("Destination", "127.0.0.1,5000");
			hshConfig.put("TStartNumber","1");
			hshConfig.put("QOT","20");
			hshConfig.put("Period", "15");
			hshConfig.put("GroupID","1");
			hshConfig.put("TMode","1");
			hshConfig.put("PMode","0");
			hshConfig.put("SMode","1");
			hshConfig.put("OutputMultiply","3");
			
			hshConfig.put("Fault_Operating","0");
			hshConfig.put("Fault_PVolt","0"); 
			hshConfig.put("Fault_PVHC","0");
			hshConfig.put("Fault_IGBT","0");
			hshConfig.put("Fault_OT","0");
			hshConfig.put("Fault_SysVolt","0");
			hshConfig.put("Fault_SysHC","0");
			hshConfig.put("Fault_FQ","0"); 
			hshConfig.put("Fault_Down","0"); 
			hshConfig.put("Fault_Leak","0");
			
			hshConfig.put("Alarmtype","0");
			
			cmdHandler = new DataSimulator_CommandHandler();
			cmdHandler.SetHshConfig(hshConfig);
			cmdHandler.SetHshTrackerList(hshTrackerSet);
			cmdHandler.SetMessageQueue(messageQueue);
			cmdHandler.start();			
		
		} catch(Exception e) {
			logger.error("DataSimulatorManager error occured ! :: " + e.toString());
		}
		// **** �ʱ� ���� �� �Է� 
		//  			- ���� IP, Port ( ���� �� �⺻ ��Ʈ ���� )
		//				- ������ Ʈ��Ŀ �� ( ���� �� �⺻ Ʈ��Ŀ �� ���� )
		//				- ���� �ֱ� ( ���� �� �⺻ ���� �ֱ� ���� )
		
		//				- ��Ʈ�ѷ����� ��û�� ���� ������ �ָ� ������ �� �ְ�..
		
		// 1. Ʈ��Ŀ ������ ���� 
		
		// 2. ���� ���� �� ���� ���� 
	
		// 3. Cyclic run
		// 				- ������ ������� ���.
		//				- Send Protocol Data from DS_Protocol
		//				- 

		
		System.out.println("[System]     DataSimulatorManager load complete.");
	}
	
	public CustomQueue<DataSimulator_MessageData> GetMsgQueue() {
		return messageQueue;
	}
}
