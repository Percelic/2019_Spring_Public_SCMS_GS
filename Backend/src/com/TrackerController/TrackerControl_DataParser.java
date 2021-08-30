package com.TrackerController;

import com.Common.CustomQueue;

public class TrackerControl_DataParser {

	private CustomQueue<String> PopQueue;		// Packet ����
	private CustomQueue<Object> PushQueue;		// Protocol Class ����
	

	private Thread thread_DataParser;
	
	public TrackerControl_DataParser() {
	
		//executorService = Executors.newFixedThreadPool(3);	
	}
	
	public void DataParse_StartThread() {
		thread_DataParser = new Thread(new Runnable() {
			@Override
			public void run() {
				
					while(true) {
						try {
														
								if(!PopQueue.isEmpty()) {
									TrackerControl_Protocol tcp;
									synchronized (PopQueue) {
										tcp = new TrackerControl_Protocol(PopQueue.Dequeue());
										//System.out.println("# " + (nParserCount++) + " is " + dcp.GetData().getClass().getName());
									}
									 
									synchronized (PushQueue) {
										PushQueue.Enqueue(tcp);				
									}
									//dcp.debug_PrintStatus();
								}
						
								Thread.sleep(1);
						
						}	catch(Exception e) {
							TrackerControlManager.logger.warn("[ TrackerController ] DataParser error occured ! : " + e.toString());
						}
					}
			}
		});
		
		thread_DataParser.start();
		
	}
	private void Debug_PrintData(int[] packet) 
	{
		System.out.format("[ TrackerController ] [Debug_PrintData] ::");
		
		for(int i : packet) {
			System.out.format(" %02X", i);
		}
		System.out.println();
	}
	
	public void SetPopQueue(CustomQueue<String> _cq) {
		PopQueue = _cq;
	}
	
	public void SetPushQueue(CustomQueue<Object> _cqq) {
		PushQueue = _cqq;
	}
}
