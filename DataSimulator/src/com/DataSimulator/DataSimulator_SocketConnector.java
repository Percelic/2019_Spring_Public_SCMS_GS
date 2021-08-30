package com.DataSimulator;

import java.io.BufferedInputStream;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class DataSimulator_SocketConnector {
	
	private Socket socket;
	private ThreadSocketMsgHandler msgHandler;
	
	public DataSimulator_SocketConnector(String _szServerIP, int _nServerPort, int _nLocalPort) 
	{
		try {
			try {				
				socket = new Socket();
				socket.setReuseAddress(true);
				socket.setSoLinger(true,0);
				socket.bind(new InetSocketAddress(0));
				socket.connect(new InetSocketAddress(_szServerIP, _nServerPort));
				
				DataSimulatorManager.logger.info("[ DataSimulator ] Port " + socket.getLocalPort() + " has connected to " + _szServerIP + ":" + _nLocalPort);
				msgHandler = new ThreadSocketMsgHandler(socket);
			} catch(BindException be) {
				if(socket != null) {
					if(msgHandler != null) msgHandler.interrupt();
					socket.setReuseAddress(true);
					socket.setSoLinger(true,0);
					//socket.bind(new InetSocketAddress(InetAddress.getLocalHost(),_nLocalPort));
					//socket.connect(new InetSocketAddress(_szServerIP, _nServerPort));
					
					//System.out.println("Port " + _nLocalPort + " is ready.");
					//msgHandler = new ThreadSocketMsgHandler(socket);
				}
				else {
					System.out.println("Failed to Create Socket Object ! : " + be.toString());
					DataSimulatorManager.logger.error("Port " + socket.getLocalPort() + " is failed.");
				}
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean Socket_Close() {
		
		try {
			try {
			DataSimulatorManager.logger.info("Closing " + socket.getLocalPort() + " Port..");
			socket.shutdownOutput();
			socket.shutdownInput();
			socket.close();
//			if(socket != null) {
//				socket = null;
//			}
			
			return true;//socket == null ? true : false;	
			} catch(SocketException se) {
				System.out.println("Socket close..");
//				if(socket != null) 
//					socket = null;
//				
				return true;//socket == null ? true : false;
			}
		} catch(Exception e) {
			System.out.println("Failed to Close Server Port :: " + e.toString());
			return false;
		}
		
	}
	
	public Socket GetSocket() {
		return socket;
	}
	
	public ThreadSocketMsgHandler GetMsgHandler() {
		return msgHandler;
	}
	
	
}

class ThreadSocketMsgHandler extends Thread {
	
	Socket clientSocket;
	int nSendDelay;
	
	public ThreadSocketMsgHandler(Socket _socket) {
		clientSocket = _socket;
		start();
	}
	
	@SuppressWarnings("unused")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ReadMethod();
	}
	
	private void ReadMethod() {
			
			try {			
				BufferedInputStream buffer = new BufferedInputStream(clientSocket.getInputStream());
				
				int ReadByte = 0;
				int _nAvailable = 0;
					while(true) {
						ReadByte = buffer.read();
						
						if(ReadByte == -1) {
							buffer.close();
							DataSimulatorManager.logger.info("[ DataSimulator ] Socket " + clientSocket.getRemoteSocketAddress().toString().replace("/", "") + " has disconnected !"); 
							clientSocket.close();
							clientSocket = null;
							break;
						}
						
						else if ((buffer.available()) > 0 ) {		
							ArrayList<Integer> _readBytes = null;
							
							if(buffer.available() < 100 && buffer.available() > 0) {
								
								ArrayList<Short> qReadData = new ArrayList<Short>();
								
								qReadData.add((short)ReadByte);
								while(buffer.available() > 0) {									
									qReadData.add((short)buffer.read());
									
									if(buffer.available() == 0) {
										DataSimulator_MessageData _msgData = new DataSimulator_MessageData();
										_msgData.SetshTID(((short)(clientSocket.getLocalPort())));
										_msgData.SetshMessage((qReadData.toArray(new Short[qReadData.size()])));
										DataSimulatorManager.messageQueue.Enqueue(_msgData);
										
										String _szPacket = "";
										_szPacket += String.format(String.format("[ Recv data. length : %d \t\t] [ ", qReadData.size()));
										for (short _s : _msgData.GetshMessage()) {
											_szPacket += String.format("%02X ", _s);
										}
										_szPacket += "]";
										
										DataSimulatorManager.logger.debug(_szPacket);
									}	
								}		
							}
							
							
							else {
								while(buffer.available() > 0) buffer.read();
							}
						}	
						Thread.sleep(1); 
					} 					
					
					
									
			}catch(Exception e) {
				System.out.println("Failed to Receive Message From Server :: " + e.toString());
			}
	}
			
			////
	
//	private void ReadMethod2() {
//		try {			
//			InputStream is = clientSocket.getInputStream();
//						
//			int ReadByte = 0;
//			
//				while(true) {		
//						ReadByte = is.read();
//						
//						if(ReadByte == -1) {
//							is.close();
//							System.out.println(clientSocket.getRemoteSocketAddress() + " has Disconnected !"); 
//							clientSocket.close();
//							clientSocket = null;
//							break;
//						}
//						else if ((is.available()) > 0) {
//							nQueue.Enqueue(ReadByte);
//								if(is.available() < 100 && is.available() > 0) {
//									while((is.available()) > 0) {
//										
//										nQueue.Enqueue(is.read());
//										
//										if(is.available() == 0) {
//											nQueue.Enqueue(null);
//											System.out.println();
//										}		
//									}
//								}
//						}
//					
//					Thread.sleep(1);
//				}
//					
//		}catch(Exception e) {
//			System.out.println("Failed to Receive Message From Server :: " + e.toString());
//		}
//	}
}
