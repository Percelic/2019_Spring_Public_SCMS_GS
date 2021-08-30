package com.TrackerController;

import com.Common.CustomQueue;

public class TrackerControl_ProtocolSender {
	private CustomQueue<Object> vPopQueue;
	private CustomQueue<Object> vPushQueue;
	private ProtocolSender_ThreadHandler sendHandler;
	private TrackerControlDAO DAO;
//	private ProtocolSender_PeriodThreadHandler periodSendHandler_idle;
//	private ProtocolSender_PeriodThreadHandler periodSendHandler_control;
	
	
	public TrackerControl_ProtocolSender(TrackerControlDAO _DAO) {
		DAO = _DAO;
	}
	
	public void Sender_StartPopHandler() {
		sendHandler = new ProtocolSender_ThreadHandler(vPopQueue,vPushQueue,DAO);
		sendHandler.start();
		
//		periodSendHandler_idle = new ProtocolSender_PeriodThreadHandler(60000, 1);
//		periodSendHandler_idle.start();
//		
//		periodSendHandler_control = new ProtocolSender_PeriodThreadHandler(5000, 2);
//		periodSendHandler_control.start();
	}
	
	
	public void SetPopQueue(CustomQueue<Object> _cq) 
	{
		vPopQueue = _cq;
	}	
	
	public void SetPushQueue(CustomQueue<Object> _cq) 
	{
		vPushQueue = _cq;
	}
}

class ProtocolSender_ThreadHandler extends Thread {
	
	CustomQueue<Object> PopQueue;
	CustomQueue<Object> PushQueue;
	TrackerControlDAO DAO;
	
	public ProtocolSender_ThreadHandler(CustomQueue<Object> _cq, CustomQueue<Object> _cqq, TrackerControlDAO _DAO) {
		// TODO Auto-generated constructor stub
		PopQueue = _cq;
		PushQueue = _cqq;
		DAO = _DAO;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true) {
			try {
				synchronized(PopQueue) {
					if(!PopQueue.isEmpty()) {
					
						
						TrackerControl_Protocol_Control _tcpc = ((TrackerControl_Protocol_Control)((TrackerControl_Protocol)PopQueue.Dequeue()).GetData());
						
						if(_tcpc != null) {
						
							int _nSI_ID = _tcpc.GetHeader().getSI_ID(); 
							int _nTR_ID = _tcpc.GetHeader().getTR_ID();
							
							
							if(!TrackerControlManager.tGroupDataMap.get(TrackerControlManager.tGroupInfoMap.get(_nSI_ID)).isEmpty()) {
								if(TrackerControlManager.tGroupDataMap.get(TrackerControlManager.tGroupInfoMap.get(_nSI_ID)).get(_nTR_ID) != null) {
									
									// Tracker #0 of each Groups --> for change each group's mode.
									
									if(_nTR_ID != 0) {
										if(_tcpc.GetBody().getIsModeChange().equals("1")) {
											if(!TrackerControlManager.tGroupDataMap.get(TrackerControlManager.tGroupInfoMap.get(_nSI_ID)).get(0).getMode().equals(_tcpc.GetBody().getMode())) {
												TrackerControlManager.tGroupDataMap.get( TrackerControlManager.tGroupInfoMap.get(_nSI_ID) )
												.get(0).setMode(_tcpc.GetBody().getMode());
												
												DAO.DB_UpdateGroupInfo_Control(_tcpc);
												
												TrackerControlManager.logger.debug(String.format("[ TrackerController ] Group [%d] Mode has changed into %s ", _nSI_ID ,_tcpc.GetBody().getMode()));
											}
										}
									
										TrackerControlManager.tGroupDataMap.get( TrackerControlManager.tGroupInfoMap.get(_nSI_ID) )
										.get(_nTR_ID).getSocket().getOutputStream().write(_tcpc.GetString().getBytes());
										
										// when not control
										if(  (_tcpc.GetBody().getIsLRShift().equals("1") && _tcpc.GetBody().getLRShift().equals("0") && _tcpc.GetBody().getIsUDShift().equals("1") && _tcpc.GetBody().getUDShift().equals("0")) ) {
											TrackerControlManager.tGroupDataMap.get( TrackerControlManager.tGroupInfoMap.get(_nSI_ID)).get(_nTR_ID).setIsControl(false);
											
											try { 
												if(_tcpc.GetBody().getIsUDShift().equals("1"))  
													TrackerControlManager.tGroupDataMap.get( TrackerControlManager.tGroupInfoMap.get(_nSI_ID)).get(_nTR_ID).setUpShift(0); }
											catch(NumberFormatException nfe) { TrackerControlManager.tGroupDataMap.get( TrackerControlManager.tGroupInfoMap.get(_nSI_ID)).get(_nTR_ID).setUpShift(0); }
											try { 
												if(_tcpc.GetBody().getIsLRShift().equals("1"))
													TrackerControlManager.tGroupDataMap.get( TrackerControlManager.tGroupInfoMap.get(_nSI_ID)).get(_nTR_ID).setLeftShift(0); }
											catch(NumberFormatException nfe) { TrackerControlManager.tGroupDataMap.get( TrackerControlManager.tGroupInfoMap.get(_nSI_ID)).get(_nTR_ID).setLeftShift(0); }
										
											TrackerControlManager.logger.debug("[ TrackerController ] GROUP ID : " + _nSI_ID + " , TRACKER ID : " + _nTR_ID + " is not controlled");
										}
										
										// when control
										else {
											
											if(_tcpc.GetBody().getIsLRShift().equals("1") || _tcpc.GetBody().getIsUDShift().equals("1")) {
											TrackerControlManager.logger.debug("[ TrackerController ] GROUP ID : " + _nSI_ID + " , TRACKER ID : " + _nTR_ID + " is controlled");
											TrackerControlManager.tGroupDataMap.get( TrackerControlManager.tGroupInfoMap.get(_nSI_ID)).get(_nTR_ID).setIsControl(true);
											
											
											try { 
												if(_tcpc.GetBody().getIsUDShift().equals("1"))  
													TrackerControlManager.tGroupDataMap.get( TrackerControlManager.tGroupInfoMap.get(_nSI_ID)).get(_nTR_ID).setUpShift(Integer.parseInt(_tcpc.GetBody().getUDShift())); }
											catch(NumberFormatException nfe) { TrackerControlManager.tGroupDataMap.get( TrackerControlManager.tGroupInfoMap.get(_nSI_ID)).get(_nTR_ID).setUpShift(0); }
											try { 
												if(_tcpc.GetBody().getIsLRShift().equals("1"))
													TrackerControlManager.tGroupDataMap.get( TrackerControlManager.tGroupInfoMap.get(_nSI_ID)).get(_nTR_ID).setLeftShift(Integer.parseInt(_tcpc.GetBody().getLRShift())); }
											catch(NumberFormatException nfe) { TrackerControlManager.tGroupDataMap.get( TrackerControlManager.tGroupInfoMap.get(_nSI_ID)).get(_nTR_ID).setLeftShift(0); }
											}
										}
									}
								}
							}
						}
					}
				}
				sleep(1);
			} catch(Exception e) {
				TrackerControlManager.logger.error("[ TrackerController ] ProtocolSender_ThreadHandler error occured ! : " + e.toString());
			}
		}
	}
}

//class ProtocolSender_PeriodThreadHandler extends Thread {
//	
//	int nPeriod = 0;
//	int nFlag = 0;
//	
//	public ProtocolSender_PeriodThreadHandler(int _nPeriod, int _nFlag) {
//		// TODO Auto-generated constructor stub
//		nPeriod = _nPeriod;
//		nFlag = _nFlag;
//	}
//	
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub		
//		
//		switch(nFlag) {
//		
//		// When Mode ON
//		case 1: 
//			while(true) {
//				try {
//					sleep(nPeriod);
//					
//					for(ConcurrentHashMap<Integer,CustomSocket> _hsh : TrackerControlManager.tGroupDataMap.values() ) {
//						if(!_hsh.isEmpty()) {
//							for(CustomSocket _tcs : _hsh.values()) {
//								if( _tcs.getSocket() != null ) {
//									
//									// check if controlled
//									if( _tcs.getIsControl()) {
//										_tcs.getSocket().getOutputStream().write("CTRLED".getBytes());
//										
//									}
//									
//								}
//								
//								else {
//									if(Utils.getKeyFromValue(_hsh, _tcs) != 0) {
//									_hsh.values().remove(_tcs);
//									}
//								}
//							}
//						}
//					}
//				} catch(Exception e) {
//					TrackerControlManager.logger.error("ProtocolSender_PeriodThreadHandler(Control) error occured ! : " + e.toString());
//				}
//			}
//			
//		// When Mode OFF
//		case 2:
//			while(true) {
//				try {
//					sleep(nPeriod);
//					
//					for(ConcurrentHashMap<Integer,CustomSocket> _hsh : TrackerControlManager.tGroupDataMap.values()) {
//						if(!_hsh.isEmpty()) {
//							for(CustomSocket _tcs : _hsh.values()) {
//								
//								if( _tcs.getSocket() != null) {
//									
//									// check if not Controlled
//									if(!_tcs.getIsControl()) {
//										_tcs.getSocket().getOutputStream().write("!CTRLED".getBytes());
//										
//									}
//									
//									// CA_agent(_tcs, 
//								}
//								
//								else {
//									if(Utils.getKeyFromValue(_hsh, _tcs) != 0) {
//										_hsh.values().remove(_tcs);
//									}
//								}
//							}
//						}
//					}
//					
//				} catch(Exception e) {
//					TrackerControlManager.logger.error("ProtocolSender_PeriodThreadHandler error occured ! : " + e.toString());
//				}
//				
//			}
//		}
//		
//	}
//}

