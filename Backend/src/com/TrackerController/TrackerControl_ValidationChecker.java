package com.TrackerController;

import com.Common.CustomQueue;

public class TrackerControl_ValidationChecker {
	
	CustomQueue<Short> vPopQueue;
	CustomQueue<String> vPushQueue;
	
	Validate_DataHandler dataHandler;
	
	public TrackerControl_ValidationChecker() 
	{
		
	}
	
	public void Valid_StartPopHandler() {
		dataHandler = new Validate_DataHandler(vPopQueue,vPushQueue);
		dataHandler.start();
	}
	
	
	
	public void SetPopQueue(CustomQueue<Short> _cq) 
	{
		vPopQueue = _cq;
	}	
	
	public void SetPushQueue(CustomQueue<String> _cq) 
	{
		vPushQueue = _cq;
	}
}

class Validate_DataHandler extends Thread {

	final int VALIDATE_DATAHANDLE_THREADPOOL_SIZE = 3;
	
	CustomQueue<Short> PopQueue;
	CustomQueue<String> PushQueue;
	
	int nRunCount = 0;
	
	public Validate_DataHandler(CustomQueue<Short> _cq, CustomQueue<String> _cqq) {
		PopQueue = _cq;
		PushQueue = _cqq;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		int nStartIdx = 0;
		int nEndIdx = 0;
		int nPacketLength = 0;
		Short tmpData = 0;
		short btPayloadLength = 0;
		
		while(true) {
			try {
				synchronized(PopQueue) {
					if(!PopQueue.isEmpty()) {
							tmpData = PopQueue.Dequeue();
							if(tmpData != null && tmpData == 0x81) // Catch STX is 0x81 = 129
							{
								
								if(!PopQueue.isEmpty() && (btPayloadLength = PopQueue.Dequeue()) >= 0x81) 		// Catch STX+1 is upper than 0x81
								{
									
									btPayloadLength %= 128;		// payload length																	
									
									
									if(TrackerControlManager.bIsDebugMode) {
										System.out.print("[ TrackerController ] Front IDX : " + (PopQueue.IndexOfFront()));
										System.out.println(" | Rear IDX : " + PopQueue.IndexOfRear());	
									}
									
									nStartIdx = PopQueue.IndexOfFront() > 0 ?  PopQueue.IndexOfFront() - 1:PopQueue.SizeOf() - (PopQueue.IndexOfFront() - 1);
									
									nEndIdx = (nStartIdx + btPayloadLength+2+4);
									nPacketLength = (nEndIdx - nStartIdx) ;
								
									if(TrackerControlManager.bIsDebugMode) {
										System.out.println("[ TrackerController ] found Data Frame !!   StartIdx : " + nStartIdx + " , Queue[StartIdx] : " + PopQueue.IndexOf(nStartIdx) + " , nPacketLength : " + nPacketLength + " , EndIdx : " + (nEndIdx%PopQueue.SizeOf()) + " , Queue[EndIdx] : " + PopQueue.IndexOf((nEndIdx%PopQueue.SizeOf())) + "   		:::: ");
										System.out.println();
									}

								
								  try { 
									  String _pkt1 = "";
								  
									  if(ValidationCheck(_pkt1 = packetizeWithDequeue(PopQueue,nStartIdx,nEndIdx))) {
									  
										  PushQueue.Enqueue(_pkt1);
										  TrackerControlManager.logger.info("[ TrackerController ] data received : " + _pkt1);
									  
									  } 
									  System.out.println();
								  
								  } catch(Exception e) {
								  TrackerControlManager.logger.warn("[TrackerController] Validate_runner error occured ! : " + e.toString()); }
								 
								} 
							}						
						}
				}
				Thread.sleep(1);
				
			} catch(Exception e) {
				TrackerControlManager.logger.warn("[TrackerController] Validate_DataPopHandler error occured ! : " + e.toString());
				try {
				Thread.sleep(1);
				} catch(Exception _e) {
					
				}
			}
		}
		
		//super.run();
	}
	
	@SuppressWarnings("finally")
	public synchronized String packetizeWithDequeue(CustomQueue<Short> q, int startIdx, int endIdx) 
	{
		short[] _packet = new short[startIdx < endIdx ? endIdx - startIdx: (q.SizeOf() + endIdx) - startIdx ];
		short[] _maskKey = new short[4];
		short[] _payload = new short[_packet.length - 6];
		
		try {
			_packet[0] = (short)129;
			_packet[1] = (short)(_packet.length + 128);
		for(int i =2; i < _packet.length; i++) {
			_packet[i] =  q.Dequeue(); //(startIdx, endIdx+1);
		}
		
		// make maskKey array
		for(int i = 0; i < _maskKey.length; i++) {
			_maskKey[i] = _packet[2+i];
		}
		
		// make payload array
		for(int i = 0; i < _payload.length ; i++ ) {
			_payload[i] = _packet[6+i]; 
		}
		
		// show raw data
		
		
		
		String _strPayload = "";
		for(int i = 0; i < _payload.length ;i++ ) {
			_strPayload += String.format("%c", _payload[i] ^ _maskKey[i%_maskKey.length]);
		}
		
		if(TrackerControlManager.bIsDebugMode) {
			System.out.print("[raw Data] :: ");
			
			for(short a : _packet) {
				 System.out.print(String.format("%d ", a));
			}
			System.out.println();
			
			// unmask data
			System.out.print("[unmasked Data] :: ");
		
			System.out.println("PushQueue size : " + PushQueue.AmountOf() + " , payload : " + _strPayload);
			
			System.out.println();
		}
		
		return _strPayload;
		
		} catch(Exception e) {
			TrackerControlManager.logger.warn("[TrackerController] Validate_packetize error occured ! : " + e.toString());
			
			return "";
		}
	}
	
	public boolean ValidationCheck(String _packet) {
	
		
		
		return true;
	}
}
