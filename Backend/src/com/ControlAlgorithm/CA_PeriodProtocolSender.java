package com.ControlAlgorithm;

import java.util.concurrent.ConcurrentHashMap;

import com.Common.CustomSocket;
import com.Common.Utils;

public class CA_PeriodProtocolSender {
	private ProtocolSender_PeriodThreadHandler periodSendHandler_idle;
	private ProtocolSender_PeriodThreadHandler periodSendHandler_control;
	
	
	public CA_PeriodProtocolSender() {
		
	}
	
	public void Sender_StartPeriodHandler() {
		
		periodSendHandler_idle = new ProtocolSender_PeriodThreadHandler(60000, 2);  // NOT CONTROLLED
		periodSendHandler_idle.start();
		
		// Web에서 알아서 제어..
		//periodSendHandler_control = new ProtocolSender_PeriodThreadHandler(5000, 1); // CONTROLLED
		//periodSendHandler_control.start();
	}	
}

class ProtocolSender_PeriodThreadHandler extends Thread {
	
	int nPeriod = 0;
	int nFlag = 0;
	
	public ProtocolSender_PeriodThreadHandler(int _nPeriod, int _nFlag) {
		// TODO Auto-generated constructor stub
		nPeriod = _nPeriod;
		nFlag = _nFlag;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub		
		
		switch(nFlag) {
		
		// When Controlled
		case 1: 
			while(true) {
				try {
					sleep(nPeriod);
					
					for(ConcurrentHashMap<Integer,CustomSocket> _hsh : CAManager.tGroupDataMap.values() ) {
						if(!_hsh.isEmpty()) {
							for(CustomSocket _tcs : _hsh.values()) {
								if( _tcs.getSocket() != null ) {
									// check if controlled
									if(_tcs.getIsControl()) {
										String _szStr = "";
										
										_szStr += String.format("$$SC,");
										_szStr += String.format("%04d,",Utils.getKeyFromValue(CAManager.tGroupInfoMap, Utils.getKeyFromValue(CAManager.tGroupDataMap , _hsh)) );
										_szStr += String.format("%03d,",Utils.getKeyFromValue(_hsh, _tcs));
										_szStr += String.format("c,");
										_szStr += String.format("0,");
										_szStr += String.format("%s,",_hsh.get(0).getMode());
										_szStr += String.format("%d,",_tcs.getUpShift());
										_szStr += String.format("%d,",_tcs.getLeftShift());
										_szStr += String.format("%02x\r\n", Utils.CalculateCS(Utils.byteToInteger(_szStr.getBytes())));
												
										_tcs.getSocket().getOutputStream().write(_szStr.getBytes());
																				
										String _szPacket = "";
										_szPacket += String.format("[ CAManager ] send to %s %d byte(s) : [", _tcs.getSocket().getRemoteSocketAddress().toString().split("/")[1], _szStr.length() );
										
										for(byte _b : _szStr.getBytes()) {
											_szPacket += String.format(" %02X",_b);
										}
										_szPacket += " ]";
										
										CAManager.logger.debug(_szPacket);
									}
								}
								
								else {
									if(Utils.getKeyFromValue(_hsh, _tcs) != 0) {
									_hsh.values().remove(_tcs);
									}
								}
							}
						}
					}
				} catch(Exception e) {
					CAManager.logger.error("ProtocolSender_PeriodThreadHandler(Control) error occured ! : " + e.toString());
				}
			}
			
		// When Not Controlled
		case 2:
			while(true) {
				try {
					sleep(nPeriod);
					
					for(ConcurrentHashMap<Integer,CustomSocket> _hsh : CAManager.tGroupDataMap.values()) {
						if(!_hsh.isEmpty()) {
							for(CustomSocket _tcs : _hsh.values()) {
								
								if( _tcs.getSocket() != null) {
									
									// check if not Controlled
									if(!_tcs.getIsControl()) {
										String _szStr = "";
										
										_szStr += String.format("$$SC,");
										_szStr += String.format("%04d,", _tcs.getnGID() /*Utils.getKeyFromValue(CAManager.tGroupInfoMap, Utils.getKeyFromValue(CAManager.tGroupDataMap , _hsh))*/ );
										_szStr += String.format("%03d,",Utils.getKeyFromValue(_hsh, _tcs));
										_szStr += String.format("c,");
										_szStr += String.format("0,");
										_szStr += String.format("%s,",_hsh.get(0).getMode());
										_szStr += String.format("%d,",_tcs.getUpShift());
										_szStr += String.format("%d,",_tcs.getLeftShift());
										
										_szStr += String.format("%02x\r\n", Utils.CalculateCS(Utils.byteToInteger(_szStr.getBytes())));
										
										_tcs.getSocket().getOutputStream().write(_szStr.getBytes());
										
										
										
										String _szPacket = "";
										
										_szPacket += String.format("[ CAManager \t] send to %s [%d] : [", _tcs.getSocket().getRemoteSocketAddress().toString().split("/")[1], _szStr.length() );
										
										for(byte _b : _szStr.getBytes()) {
											_szPacket += String.format(" %02X",_b);
										}
										_szPacket += " ]";
										
										CAManager.logger.debug(_szPacket);
										
									} 
								}
								
								else {
									if(Utils.getKeyFromValue(_hsh, _tcs) != 0) {
										_hsh.values().remove(_tcs);
									}
								}
							}
						}
					}
					
				} catch(Exception e) {
					CAManager.logger.error("ProtocolSender_PeriodThreadHandler error occured ! : " + e.toString());
				}
				
			}
		}
		
	}
}

