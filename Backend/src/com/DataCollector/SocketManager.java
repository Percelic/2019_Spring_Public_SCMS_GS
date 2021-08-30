package com.DataCollector;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.Common.CustomSocket;

// To manage Tracker of Groups 
//
//           HASHMAP        HASHMAP      HASHMAP
// [ GROUP ID ] : [ GROUP IP ] : [ TRACKER ID , SOCKET ]
//  <Integer>   |   <String>   |   	<Integer>  |<Socket>

// |<-------------------------->						|
// |             <------------------------------------->|
// |                            <---------------------->|
public class SocketManager implements Closeable {
	
	ConcurrentHashMap<Integer, String> tGroupInfoMap;
	ConcurrentHashMap<String, ConcurrentHashMap<Integer, CustomSocket>> tGroupDataMap;
	ConcurrentHashMap<Integer, String> tGroupModeMap;
	
	DataCollectDAO DAO;
	Thread periodAction;
	
	Date refreshMode_Date;
	Date period_RefreshMode_Date;
	
	public SocketManager(DataCollectDAO _DAO) {		
		
		DAO = _DAO;
		
		period_RefreshMode_Date = new Date();
		periodAction = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					try {
						if((period_RefreshMode_Date.getTime() - new Date().getTime()) > (5 * 60 * 1000))
							SocketManager_RefreshGroupMode();
						
						Thread.sleep(59000);
					} catch(Exception e) {
						DataCollectManager.logger.warn("[ DataCollector ] SocketManager_periodAction failed.. : " + e.toString());
					}
				}
			}
		});
	}
	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		try {
		periodAction.join(500);
		refreshMode_Date = null;
		period_RefreshMode_Date = null;
		DAO = null;
		
		} catch(InterruptedException ie) {
			DataCollectManager.logger.error("[ DataCollector ] Error occured while closing SocketManager :: " + ie.toString());
		}
	}
	
	public boolean SocketManager_InitData() {
		// Init Each Hash
		try {
			
			if(DAO != null) {
				tGroupInfoMap.putAll(DAO.DB_SelectGroupInfo(false));
				//tGroupDataMap = new HashMap<String, HashMap<Integer, Socket>>();
				
				for(String s : tGroupInfoMap.values()) 
				{
					ConcurrentHashMap<Integer, CustomSocket> _tTData = new ConcurrentHashMap<Integer, CustomSocket>();
					tGroupDataMap.put(s, _tTData);
				}
				
				
				// to add Tracker #0 each group 
				for(ConcurrentHashMap<Integer, CustomSocket> _t : tGroupDataMap.values()) {
					_t.put(0, new CustomSocket(null));
				}
				
				refreshMode_Date = new Date();
				tGroupModeMap.putAll(DAO.DB_SelectGroupMode());
			}
			
			return true;
		} catch(Exception e) {
			DataCollectManager.logger.error("[ DataCollector ] SocketManager_InitData() failed.. : " + e.toString());
			DataCollectManager.logger.error("[ DataCollector ] must each row of group table have IP and mode value and call SocketManager_SetInfoMap , SetDataMap before InitData Method");
			
			return false;
		}
	}
	
	public boolean SocketManager_PutClient(CustomSocket _client  ,int _gID , int _trID) {
		
		try {
			String _szHostIP = _client.getSocket().getRemoteSocketAddress().toString().replace("/", "").split(":")[0];
			
			SocketManager_RefreshGroupID(_szHostIP);
			SocketManager_RefreshGroupMode();
			//SocketManager_Broadcasting(4);
			
		for(Map.Entry<String, ConcurrentHashMap<Integer, CustomSocket>> t: tGroupDataMap.entrySet()){
			// 190620 commented.
			// original Method 1
//			if ( t.getKey().equals(_szHostIP) && Utils.getKeyFromValue(tGroupInfoMap , t.getKey()).equals(_gID) ) {	
//				if(SocketManager_CheckValidate(_client.getSocket())) {
//					if(tGroupDataMap.get(t.getKey()) != null) {
//						tGroupDataMap.get(t.getKey()).put(_trID,_client);
//						
//						DataCollectManager.logger.debug(String.format("[ DataCollector ] SocketManager_PutClient :: [ %s ] socket has registered as GROUP : %d , TRACKER : %d", _client.getSocket().getRemoteSocketAddress(), Utils.getKeyFromValue(tGroupInfoMap, t.getKey()) ,_trID  ));				
//						return true;
//					}
//					
//					else {
//						DataCollectManager.logger.debug(String.format("[ DataCollector ] SocketManager_PutClient :: [ %s ] socket has failed to register as GROUP : %d , TRACKER : %d", _client.getSocket().getRemoteSocketAddress(), Utils.getKeyFromValue(tGroupInfoMap, t.getKey()) ,_trID  ));				
//						return false;
//					}
//					//tGroupDataMap.get(t.getKey()).put(_client.getSocket().getPort()%5000, _client);
//				}
//			}
			
			//else {
				//System.out.println("found Incorrect IP try to join GROUP [" + _gID + "] let them out :: " + _szHostIP);
				//_client.disposeSocket();
			//}
			
			
			// Changed method 2 :: 190620
			
			if(t.getKey().equals(_szHostIP) && SocketManager_CheckValidate(_client.getSocket())) {
				if(tGroupDataMap.get(t.getKey()) != null) {
					tGroupDataMap.get(t.getKey()).put(_trID,_client);
					
					DataCollectManager.logger.debug(String.format("[ DataCollector ] SocketManager_PutClient :: [ %s ] socket has registered as GROUP : %d , TRACKER : %d", _client.getSocket().getRemoteSocketAddress(), _gID ,_trID  ));
					_client.setnGID(_gID);
					return true;
				}
				
				else {
					DataCollectManager.logger.debug(String.format("[ DataCollector ] SocketManager_PutClient :: [ %s ] socket has failed to register as GROUP : %d , TRACKER : %d", _client.getSocket().getRemoteSocketAddress(), _gID ,_trID  ));
					
					return false;
				}
				//tGroupDataMap.get(t.getKey()).put(_client.getSocket().getPort()%5000, _client);
			}
			
			//
		}
		
		return false;	
		//_client.close();
		//_client = null;
		//SocketManager_Debug_GetHashList();
		} catch(Exception e) {
			System.out.println("PutClient Error occured ! : " + e.toString());
			return false;
		}
	}
	
	public void SocketManager_SearchClosed() { // not completed..
		for(String _szKey : tGroupDataMap.keySet()){
			for(CustomSocket _csocket : tGroupDataMap.get(_szKey).values()) {
				System.out.println(_csocket.getSocket().isBound() + ", " + _csocket.getSocket().isClosed() + "," + _csocket.getSocket().isConnected());
				
				if(_csocket.getSocket().isClosed()) {
					try {
					System.out.println( _csocket.getSocket().getRemoteSocketAddress() + " has closed ! " );
					_csocket.getSocket().close();
					_csocket.setSocket(null);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void SocketManager_Debug_GetHashList() {
		System.out.println("======================================");
		System.out.println(" tGroupInfoMap Size is " + tGroupInfoMap.size());
		for(Map.Entry<Integer, String> t : tGroupInfoMap.entrySet()) {
			System.out.println(t.getKey() + " : " + t.getValue());
		}
		System.out.println("--------------------------------------");
		System.out.println(" tGroupDataMap Size is " + tGroupDataMap.size());
		for(Map.Entry<String, ConcurrentHashMap<Integer, CustomSocket>> t : tGroupDataMap.entrySet()) {
			System.out.println(t.getKey() + " : " + t.getValue());
		}
		System.out.println("======================================");
	}
	
	private boolean SocketManager_CheckValidate(Socket _client) {
	
		/* Validate Checking - Basic Arguments */
		// 5001 <= Port <= 5250  		, true
		// else 				 		, false
		int _pt = _client.getPort();
		
		if( 1 <= _pt && _pt <= 65535) 
			return true;
		else {
			
			byte[] _bt = new String("PORT RANGE ERROR").getBytes();
			
			try {
			_client.getOutputStream().write(_bt);
			} catch(Exception e) {
				System.out.println("[ DataCollector ] SocketManager_CheckValidate() error occured ! : " + e.toString());
			}
			return false;
		}
			
	}
	
	private void SocketManager_RefreshGroupID(String __szHostIP) {
		
		// when new Group IP has joined..
		if(!tGroupInfoMap.values().contains(__szHostIP)) {
		// 		when do collecting successfully
			System.out.println("is new Group IP! Refresh ID-IP List!!");
			
			HashMap<Integer, String> _tInfoSet = DAO.DB_SelectGroupInfo(true);
			
			// do Add New Group IP into tGroupInfoMap, tGroupDataMap Data
				// tGroupInfoMap.
				// tGroupDataMap.
			
			// check element repeated
			
			
			// 1. save to tGroupInfoMap if not repeated
			_tInfoSet.keySet().forEach(key -> 										// compare tInfoSet Values and tGroupDataMap Keys
				tGroupInfoMap.computeIfAbsent( key , k -> _tInfoSet.get(key) ));	// if absent, put HashMap<String, <HashMap<Integer, Socket>> in the tGroupDataMap 
			
			// 2. save to tGroupDataMap if not repeated 
			_tInfoSet.values().forEach(value ->
				tGroupDataMap.computeIfAbsent(value, k -> new ConcurrentHashMap<Integer, CustomSocket>() ));
		
			// 3. each #0 tracker to tGroupDataMap if absent
			for(ConcurrentHashMap<Integer , CustomSocket> _t : tGroupDataMap.values()) {
				if(!_t.keySet().contains(0)) {
					_t.put(0, new CustomSocket(null));
				}
			}
			
			//_tInfoSet.values().forEach(value -> 
			//	tGroupDataMap.computeIfAbsent(value , null ));
			
		// 		--> else
		}
		
		else {
			System.out.println("[ DataCollector ] Registered IP has joined. :: " + __szHostIP);
		}	
	}
	
	private synchronized void SocketManager_RefreshGroupMode() {	
		// 19.05.17 added.
		
		if(new Date().getTime() - refreshMode_Date.getTime() >= ( 10 * 1000l ) ) {
			
			refreshMode_Date = new Date();
			
			tGroupModeMap.clear();
			
			tGroupModeMap = DAO.DB_SelectGroupMode();
			
			System.out.println("[ DataCollector ] the delay is over !! let refresh mode list..");
		}
		
		else {
			//System.out.println("[ DataCollector ] not yet over the delay !!");
		}	
	}
	
	
	
//	private void SocketManager_RefreshConnection() {		// NOT COMPLETED .. 60%
//		
//		System.out.println("SocketManager_RefreshConnection() Begin ===========================>");
//		
//		tGroupDataMap.values().forEach(						// get HashMap < Integer, Socket > as v
//				v -> 
//				v.keySet().forEach(k -> {
//					System.out.println("[" + k + "] : " + v.get(k) + " --> " + v.get(k).isBound());
////					if(!v.get(k).isConnected()) { 
////						try {
////							v.get(k).close();
////							
////						} catch(Exception e) { 
////							System.out.println("RefreshConnection() error occured ! : " + e.toString()); 
////						} 
////					} 
//				} )
//		);
//		System.out.println("SocketManager_RefreshConnection() End ============================>");
//		
//	}
	
	private void SocketManager_Broadcasting(int _nGroupID) {
		if(tGroupInfoMap.containsKey(_nGroupID)) {
			
			for(CustomSocket _csock : tGroupDataMap.get(tGroupInfoMap.get(_nGroupID)).values() ) {
				try {
				_csock.getSocket().getOutputStream().write(String.format("Alive Check!").getBytes());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void SocketManager_SetInfoMap(ConcurrentHashMap<Integer , String> _tgi) {
		 tGroupInfoMap = _tgi;
	}
	
	public void SocketManager_SetDataMap(ConcurrentHashMap<String , ConcurrentHashMap<Integer , CustomSocket>> _tgd) {
		tGroupDataMap = _tgd;
	}
	
	public void SocketManager_SetModeMap(ConcurrentHashMap<Integer, String> _tgm) {
		tGroupModeMap = _tgm;
	}
}

class PeriodAction extends Thread {
	
	 int nDelay = 0;
	
	public PeriodAction() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//super.run();
		while(true) {
			try {
				sleep(nDelay);
				
				
				
				sleep(1);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}
