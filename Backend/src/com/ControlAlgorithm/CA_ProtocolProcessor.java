package com.ControlAlgorithm;

import java.io.IOException;

import com.Common.CustomQueue;
import com.Common.CustomSocket;
import com.Common.Utils;
import com.DataCollector.DataCollect_Protocol;
import com.DataCollector.DataCollect_Protocol_Shadow;
import com.DataCollector.DataCollect_Protocol_Shadow_ADC;


///////////////////////////////////////////////////////////
//
// 알고리즘 제어에 따른 조건과 응답을 기술
//
//
///////////////////////////////////////////////////////////

public class CA_ProtocolProcessor extends Thread {
	CustomQueue<Object> PopQueue;
	
	public CA_ProtocolProcessor() {
		// TODO Auto-generated constructor stub
		PopQueue = new CustomQueue<Object>(Object.class, 128);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//super.run();
		try {
			while(true) {
				if(!PopQueue.isEmpty()) {
					Object _data = ((DataCollect_Protocol)PopQueue.Dequeue()).GetData();
					CustomSocket _targetSocket;
					CustomSocket _targetGroupSocket;
					String _szStr = "";
							
					switch(_data.getClass().getName()) {
					case "com.DataCollector.DataCollect_Protocol_Shadow":
						DataCollect_Protocol_Shadow _dps = (DataCollect_Protocol_Shadow)_data;
						
						if(CAManager.tGroupInfoMap.get(_dps.GetHeader().getSI_ID()) != null) {
							if(CAManager.tGroupDataMap.get(CAManager.tGroupInfoMap.get(_dps.GetHeader().getSI_ID())) != null) {
								_targetSocket = CAManager.tGroupDataMap.get(CAManager.tGroupInfoMap.get(_dps.GetHeader().getSI_ID())).get(_dps.GetHeader().getTR_ID());
								_targetGroupSocket = CAManager.tGroupDataMap.get(CAManager.tGroupInfoMap.get(_dps.GetHeader().getSI_ID())).get(0);
								_szStr += "$$SC,";
								_szStr += String.format("%04d,", _dps.GetHeader().getSI_ID());
								_szStr += String.format("%03d,", _dps.GetHeader().getTR_ID());
								_szStr += String.format("%s,", "c");
								
								
								if(_targetSocket != null) {
								
									// when Mode is Shadow Control 
									if(_targetGroupSocket.getMode().equals("H")) {
										int _nEastCDS = _dps.GetBody().getnECDS1() & _dps.GetBody().getnECDS2() & _dps.GetBody().getnECDS3();
										int _nWestCDS = _dps.GetBody().getnWCDS1() & _dps.GetBody().getnWCDS2() & _dps.GetBody().getnWCDS3();
										
										switch(_nEastCDS & _nWestCDS) {
										case 0:
											_szStr += String.format("%d,", 0);
											_szStr += String.format("%s,", _targetGroupSocket.getMode()); 
											_szStr += String.format("%d,", 0);
											_szStr += String.format("%d,", 0);
											break;
										case 1:
											// Protocol :: if Run/Stop Oper. is Auto / Non - Auto
											_szStr += String.format("%d,", 1);
											_szStr += String.format("%s,", _targetGroupSocket.getMode()); 
											_szStr += String.format("%d,", 0);
											_szStr += String.format("%d,", 0);
											break;
										}
										
										_szStr += String.format("%02x\r\n", Utils.CalculateCS( Utils.byteToInteger(_szStr.getBytes())));
										try {
											_targetSocket.getSocket().getOutputStream().write(_szStr.getBytes());
											} catch(IOException ie) {
												CAManager.logger.error("error occured while CA_ProtocolProcesor : " + ie.toString());
											}
									}
									
									// else
//									else {
//											_szStr += String.format("%d,", _targetSocket.getIsRun() == true ? 1 : 0);		// is manual or auto?
//											_szStr += String.format("%s,", _targetGroupSocket.getMode()); 
//											_szStr += String.format("%d,", 0);
//											_szStr += String.format("%d,", 0);
//										
//									}
								}
							}
						}
						break;
					case "com.DataCollector.DataCollect_Protocol_Shadow_ADC":
						DataCollect_Protocol_Shadow_ADC _dpsadc = (DataCollect_Protocol_Shadow_ADC)_data;
						
						
						if(CAManager.tGroupInfoMap.get(_dpsadc.GetHeader().getSI_ID()) != null) {
							if(CAManager.tGroupDataMap.get(CAManager.tGroupInfoMap.get(_dpsadc.GetHeader().getSI_ID())) != null) {
								_targetSocket = CAManager.tGroupDataMap.get(CAManager.tGroupInfoMap.get(_dpsadc.GetHeader().getSI_ID())).get(_dpsadc.GetHeader().getTR_ID());
								_targetGroupSocket = CAManager.tGroupDataMap.get(CAManager.tGroupInfoMap.get(_dpsadc.GetHeader().getSI_ID())).get(0);
								
								_szStr += "$$SC,";
								_szStr += String.format("%04d,", _dpsadc.GetHeader().getSI_ID());
								_szStr += String.format("%03d,", _dpsadc.GetHeader().getTR_ID());
								_szStr += String.format("%c,", _dpsadc.GetHeader().getCMD());
								
								
								if(_targetSocket != null) {
								
									// when Mode is Shadow Control 
									if(_targetSocket.getMode().equals("H")) {
										int _nStandard1 = 0;//_dpsadc.GetBody().getnADC1() & _dpsadc.GetBody().getnADC2() & _dpsadc.GetBody().getnADC3() & _dpsadc.GetBody().getnADC4();
										int _nStandard2 = 0;//_dpsadc.GetBody().getnADC5() & _dpsadc.GetBody().getnADC6() & _dpsadc.GetBody().getnADC7() & _dpsadc.GetBody().getnADC8();
										
										switch(_nStandard1) {
										case 0:
											_szStr += String.format("%d,", 0);
											_szStr += String.format("%s,", _targetGroupSocket.getMode()); 
											_szStr += String.format("%d,", 0);
											_szStr += String.format("%d,", 0);
											break;
										case 1:
											_szStr += String.format("%d,", 1);
											_szStr += String.format("%s,", _targetGroupSocket.getMode()); 
											_szStr += String.format("%d,", 0);
											_szStr += String.format("%d,", 0);
											break;
										}
										
										switch(_nStandard2) {
										case 0:
											_szStr += String.format("%d,", 0);
											_szStr += String.format("%s,", _targetGroupSocket.getMode()); 
											_szStr += String.format("%d,", 0);
											_szStr += String.format("%d,", 0);
											break;
										case 1:
											_szStr += String.format("%d,", 1);
											_szStr += String.format("%s,", _targetGroupSocket.getMode()); 
											_szStr += String.format("%d,", 0);
											_szStr += String.format("%d,", 0);
											break;
										}
										
										_szStr += String.format("%02x\r\n", Utils.CalculateCS( Utils.byteToInteger(_szStr.getBytes())));
										try {
											_targetSocket.getSocket().getOutputStream().write(_szStr.getBytes());
											} catch(IOException ie) {
												CAManager.logger.error("error occured while CA_ProtocolProcesor : " + ie.toString());
											}
									}
									
									// else
//									else {
//										_szStr += String.format("%d,", _targetSocket.getIsRun() == true ? 1 : 0);		// is manual or auto?
//										_szStr += String.format("%s,", _targetGroupSocket.getMode()); 
//										_szStr += String.format("%d,", 0);
//										_szStr += String.format("%d,", 0);
//										
//									}
									
									
									
								
								}
							}
							break;
						}
					}
					
				}
				
				sleep(1);
			}
		} catch(InterruptedException ie) {
			
		}
	}
	
	public void SetPopQueue(CustomQueue<Object> _cq) {
		PopQueue = _cq;
	}
}