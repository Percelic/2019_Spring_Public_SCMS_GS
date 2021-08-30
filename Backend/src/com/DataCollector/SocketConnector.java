package com.DataCollector;

import java.net.ServerSocket;
import java.util.ArrayList;

import org.apache.commons.net.io.SocketInputStream;

import com.Common.CustomQueue;
import com.Common.CustomSocket;
import com.Common.Utils;

public class SocketConnector {
	
	private SocketManager socketManager;
	
	private ServerSocket server;
	private CustomSocket customSocket;
	private CustomQueue<Integer> PushQueue;
	
	private int Socket_port;
	private boolean bThreadisRun = true;
	
	private Thread ServerAccept_Thread;
	
	public SocketConnector(int _nPort) 
	{
		Socket_port = _nPort;
	}
	
	public boolean Socket_Server_Open() 
	{
		try {
			server = new ServerSocket(Socket_port);
			DataCollectManager.logger.info("[ DataCollector ] Data Collector Server Port " + Socket_port + " Open.");
			
			DataCollectManager.logger.info("[ DataCollector ] Socket Server Open finished Successfully..");
			
			return true;
		} catch(Exception e) {
			DataCollectManager.logger.error("[ DataCollector ] Failed to Open Server Port :: " + e.toString());
			
			return false;
		}
	}
	
	public void Socket_Server_Accept() {
		
		ServerAccept_Thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					while(bThreadisRun) {
						customSocket = new CustomSocket(server.accept());
						
						customSocket.getSocket().setReuseAddress(true);
						customSocket.getSocket().setSoLinger(true, 0);
						
						new ThreadServerHandler(socketManager ,customSocket, PushQueue);
						System.out.println("[ DataCollector ]  Server accept client ...");
						System.out.println("[ DataCollector ] "+ customSocket.getSocket().getLocalAddress() + " has Connected !");
						System.out.println("==============================================");
						DataCollectManager.logger.info("[ DataCollector ] " + customSocket.getSocket().getLocalAddress() + " has Connected !" );
					}
				} catch(Exception e) {
					DataCollectManager.logger.error("[ DataCollector ] Failed to Accept From Server Port  :: " + e.toString());
				}
			}
		});
		
		ServerAccept_Thread.start();
	}
	
	@SuppressWarnings("unused")
	public void Socket_Server_Accept(SocketManager _sm) {
		
		ServerAccept_Thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					while(bThreadisRun) {
						customSocket = new CustomSocket(server.accept());
						customSocket.getSocket().setReuseAddress(true);
						customSocket.getSocket().setSoLinger(true, 0);
						
						//_sm.SocketManager_SearchClosed();
									
						ThreadServerHandler handler = new ThreadServerHandler(socketManager ,customSocket, PushQueue);
						//handler.interrupt(); // closing ThreadServerHandler Thread
						
						DataCollectManager.logger.info("[ DataCollector ] " + customSocket.getSocket().getRemoteSocketAddress().toString().replace("/", "") + " has Connected !" );
						//System.out.println("[ DataCollector ] Connected Local : " + customSocket.getSocket().getLocalAddress() + ", Remote : " + customSocket.getSocket().getRemoteSocketAddress() );
						
						
						// legacy
						//_sm.SocketManager_PutClient(customSocket);
						
						Thread.sleep(1);
					}
				} catch(Exception e) {
					DataCollectManager.logger.error("[ DataCollector ] Failed to Accept From Server Port  :: " + e.toString());
				}
			}
		});
		
		ServerAccept_Thread.start();
	}
	
	public boolean Socket_Server_Close() {
		
		try {
			bThreadisRun = false;
			server.close();
			
			return server.isClosed();	
		} catch(Exception e) {
			DataCollectManager.logger.error("[ DataCollector ] Failed to Close Server Port :: " + e.toString());
			return false;
		}		
	}
	
	public void Socket_Server_SetPushQueue(CustomQueue<Integer> nQueue) 
	{
		PushQueue = nQueue;
	}	
	
	public void Socket_SetSocketManager(SocketManager _sm) {
		socketManager = _sm;
	}
}

class ThreadServerHandler extends Thread {
	
	CustomSocket clientSocket;
	CustomQueue<Integer> nQueue;
	SocketManager socketManager;
	
	
	public ThreadServerHandler(SocketManager _socketManager ,CustomSocket _socket, CustomQueue<Integer> _nQueue) {
	
		socketManager = _socketManager;
		
		clientSocket = _socket;
		nQueue = _nQueue;
		
		
		setPriority(MAX_PRIORITY);
		
		start();
		
		
		System.out.println("[ DataCollector ] # ThreadServerHandler begin");
	}
	
	private boolean Check_TRID(CustomSocket _socket ,Object[] _ReadBytes) {
		
		Object[] _ReadSplit = Utils.split(_ReadBytes , 0x2C);
		int _gID = 0;
		int _trID = 0;
		
		if(_ReadSplit.length > 3) {
		
			Integer[] _nReadSplit = Utils.ConvertArrayType(_ReadSplit[2], Integer.class);
			
			_trID = (100 * (_nReadSplit[0] - '0')) + (10 * (_nReadSplit[1] - '0')) + (_nReadSplit[2] - '0');
			
			
			// get Group ID
			_nReadSplit = Utils.ConvertArrayType(_ReadSplit[1], Integer.class);
			_gID = (1000 * (_nReadSplit[0] - '0')) + (100 * (_nReadSplit[1] - '0')) + (10 * (_nReadSplit[2] - '0')) + (_nReadSplit[3] - '0');
			
			
			return socketManager.SocketManager_PutClient(clientSocket, _gID , _trID);
		}
		
		else
			return false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ReadMethod();
	}
	
	private void ReadMethod() {
		try {			
			SocketInputStream buffer = new SocketInputStream(clientSocket.getSocket(),clientSocket.getSocket().getInputStream());
						
			boolean bIsIDChecked = false;
			int ReadByte = 0;
				
			clientSocket.getSocket().setSoTimeout(20 * 60 * 1000);		// 20 minutes
					while(true) {	
							//ReadByte = is.read();  // 19-04-30 change into bufferedinputstream
													
								ReadByte = buffer.read();
								
						
								if(ReadByte == -1) {
									//is.close();	// 19-04-30 change into bufferedinputstream
									buffer.close();
									
									System.out.println(clientSocket.getSocket().getRemoteSocketAddress() + " has Disconnected !"); 
									clientSocket.disposeSocket();
									break;
								}
								
								
								else if ((buffer.available()) > 0  /*(is.available()) > 0*/) {			// 19-04-30 , change inputstream into bufferedinputstream
									// synchronized ?								
									int _nAvailable = 0;
									ArrayList<Integer> _readBytes = null;
									
									synchronized(nQueue) {
									
											//DataCollectManager.logger.debug(String.format("[SOCK_SYN_START :: %s ] start SocketConnector Synchronized block. ", clientSocket.getRemoteSocketAddress()));
											nQueue.Enqueue(ReadByte);
											
											DataCollectManager.logger.debug(String.format("[ DataCollector ] %d byte(s) received from %s ", (_nAvailable = buffer.available()), clientSocket.getSocket().getRemoteSocketAddress().toString().split("/")[1] ));
										
											//if(is.available() < 120 && is.available() > 0) {	// 19-04-30 09:33 , is available < (120)
											if((buffer.available()) < 1000 && buffer.available() > 0) {	// 19-04-30 10:33, inputstream change into bufferedinputstream
												if(!bIsIDChecked) {
													_readBytes = new ArrayList<Integer>(); 
												}
												
												while((buffer.available()) > 0 /*(is.available()) > 0*/) {	// 19-04-30 11:49 , inputstream change into bufferedinputstream
													int _read = buffer.read();
													
													nQueue.Enqueue(_read /*is.read()*/);			// 19-04-30 11:49 , inputstream change into bufferedinputstream
													
													if(!bIsIDChecked)
													_readBytes.add(_read);
													
													if(buffer.available() == 0 /*is.available() == 0*/) {
														if(!bIsIDChecked)
															bIsIDChecked = Check_TRID(clientSocket, _readBytes.toArray());
														
														SocketProcess(_nAvailable);
														
													}		
												}
											}
									}
											//DataCollectManager.logger.debug(String.format("[SOCK_SYN_END :: %s ] end SocketConnector Synchronized block. ", clientSocket.getRemoteSocketAddress()));
								}
						
						Thread.sleep(1);
					}
		}catch(Exception e) {
			DataCollectManager.logger.error("[ DataCollector ] Failed to Receive Message From Server ::" + clientSocket.getSocket().getRemoteSocketAddress().toString() + " :: " + e.toString());
			clientSocket.disposeSocket();
		}
	}
	
	private synchronized void SocketProcess(int _nAvailable) {
		nQueue.Enqueue(null);
		
		if(_nAvailable == 50 || _nAvailable == 62 || _nAvailable == 44) {
			TargetPacketPrinter(_nAvailable);													
		}
	}
	
	private void TargetPacketPrinter(int _nAv) {
		try { 
		
			synchronized(nQueue) {
			
				int _nIdxStart = nQueue.IndexOfRear() >= 64 ? nQueue.IndexOfRear() - 64 : nQueue.SizeOf() - (64 - nQueue.IndexOfRear());
				int _nIdxEnd = nQueue.IndexOfRear();
				
				Integer[] _nTg = nQueue.Copy( _nIdxStart, _nIdxEnd);
				
					
				String _szTg = "[";
				
				for(Integer i : _nTg) {
					_szTg += String.format("%d ",i);
				}
				
				_szTg += "]";
				
				
				DataCollectManager.logger.debug(String.format("[ DataCollector ] catch available %d !!  :: %s",_nAv,_szTg));
			
			}
			} catch(Exception e) {
				DataCollectManager.logger.debug("[ DataCollector ] TargetPacketPrinter exception occured ! : " + e.toString());
			}

	}
}
