package com.DataCollector;

import java.io.Closeable;
import java.io.IOException;

import com.Common.CustomQueue;

public class DataParser implements Closeable {

	private CustomQueue<Integer[]> PopQueue;		// Packet ����
	private CustomQueue<Object> PushQueue;		// Protocol Class ����
	private CustomQueue<Object> TransQueue;
	
	private Thread thread_DataParser;
	private boolean bThreadisRun = true;
	
	static int _nCount = 0;
	
	public DataParser() {
	
		//executorService = Executors.newFixedThreadPool(3);	
	}
	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		try {
			bThreadisRun = false;
			PopQueue = null;
			PushQueue = null;
			TransQueue = null;
		
		} catch(Exception e) {
			DataCollectManager.logger.error("[ DataCollector ] Error occured while closing DataParser :: " + e.toString());
		}
	}
	
	public void DataParse_StartThread() {
		
		thread_DataParser = new Thread(new Runnable() {
			@Override
			public void run() {
				
					while(bThreadisRun) {
						try {
								
								// ** Suspect point
							// synchronized ?
							//synchronized (PopQueue) {
								if(!PopQueue.isEmpty()) {
									synchronized (PushQueue) {		// 19.04.26 13:55 PopQueue -> PushQueue 
										//DataCollectManager.logger.debug(String.format("[PARSE_SYN_START ] start DataParser PushQueue Synchronized block. " ));
										
										DataCollect_Protocol dcp = new DataCollect_Protocol(PopQueue.Dequeue());
										String _cmd = dcp.GetData().getClass().getName();
										
										if(_cmd.equals("com.DataCollector.DataCollect_Protocol_Shadow") || _cmd.equals("com.DataCollector.DataCollect_Protocol_Shadow_ADC")) {
											TransQueue.Enqueue(dcp);		// to message to Target Tracker
										}
										
										PushQueue.Enqueue(dcp);				// to insert DB
												
										//DataCollectManager.logger.debug(String.format("Protocolize Count : %d  | Protocol Queue .Amount : %d , .Front : %d , .Rear : %d " ,++_nCount, PopQueue.AmountOf() , PopQueue.IndexOfFront(), PopQueue.IndexOfRear()));
										
										
										//DataCollectManager.logger.debug(String.format("[PARSE_SYN_END ] end DataParser PushQueue Synchronized block. "));
										//System.out.println("# " + (nParserCount++) + " is " + dcp.GetData().getClass().getName());
									}
									 
									
									//dcp.debug_PrintStatus();
								}
							//}
								Thread.sleep(1);
						
						}	catch(Exception e) {
							DataCollectManager.logger.warn("[ DataCollector ] DataParser error occured ! : " + e.toString());
						}
					}
			}
		});
		
		thread_DataParser.start();
		
	}
	
	public void SetPopQueue(CustomQueue<Integer[]> _cq) {
		PopQueue = _cq;
	}
	
	public void SetPushQueue(CustomQueue<Object> _cqq) {
		PushQueue = _cqq;
	}
	
	public void SetTransQueue(CustomQueue<Object> _tsq) {
		TransQueue = _tsq;
	}
}
