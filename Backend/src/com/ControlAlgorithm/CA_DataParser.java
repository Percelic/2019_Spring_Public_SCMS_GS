package com.ControlAlgorithm;

import com.Common.CustomQueue;
import com.DataCollector.DataCollect_Protocol;

public class CA_DataParser {

	private CustomQueue<Object> PopQueue;		// 음영값 R , S 프로토콜 
	private CustomQueue<Object> PushQueue;
	

	private Thread thread_DataParser;
	
	public CA_DataParser() {
		
	}
	
	public void CA_DataParse_StartThread() {
		thread_DataParser = new Thread(new Runnable() {
			@Override
			public void run() {
				
					while(true) {
						try {
														
								if(!PopQueue.isEmpty()) {
									synchronized (PopQueue) {
										PushQueue.Enqueue(new CA_Protocol((PopQueue.Dequeue())));
										//System.out.println("# " + (nParserCount++) + " is " + dcp.GetData().getClass().getName());
									}
								}
						
								Thread.sleep(1);
						
						}	catch(Exception e) {
							CAManager.logger.warn("DataParser error occured ! : " + e.toString());
						}
					}
			}
		});
		
		thread_DataParser.start();
		
	}
	
	public void SetPopQueue(CustomQueue<Object> _cq) {
		PopQueue = _cq;
	}
	
	public void SetPushQueue(CustomQueue<Object> _cqq) {
		PushQueue = _cqq;
	}
}
