package com.TrackerController;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.Common.CustomQueue;
import com.Common.Utils;

public class TrackerControl_SocketConnector {
	
	
	private ServerSocket server;
	private Socket socket = new Socket();
	private CustomQueue<Short> PushQueue;
	private CustomQueue<short[]> PopQueue;
	
	private int Socket_port;
	
	private Thread ServerAccept_Thread;
	
	public TrackerControl_SocketConnector(int _port) 
	{
		Socket_port = _port;
	}
	
	public boolean Socket_Server_Open() 
	{
		try {
			server = new ServerSocket(Socket_port);
			TrackerControlManager.logger.info("[ TrackerController ] Tracker Control Server Port " + Socket_port + " Open.");
			
			TrackerControlManager.logger.info("[ TrackerController ] Socket Server Open finished Successfully..");
			
			return true;
		} catch(Exception e) {
			TrackerControlManager.logger.error("[ TrackerController ] Failed to Open Server Port :: " + e.toString());
			
			return false;
		}
	}
	
	@SuppressWarnings("unused")
	public void Socket_Server_Accept() {
		
		ServerAccept_Thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					while(true) {
						socket = server.accept();
									
						ThreadServerHandler handler = new ThreadServerHandler(socket, PushQueue);
						//handler.interrupt(); // closing ThreadServerHandler Thread
						TrackerControlManager.logger.info("[ TrackerController ] [WebSocket] " + socket.getRemoteSocketAddress().toString().replace("/", "") + " has connected");
						Thread.sleep(1);
					}
				} catch(Exception e) {
					TrackerControlManager.logger.error("[ TrackerController ] Failed to Accept From Server Port  :: " + e.toString());
				}
			}
		});
		
		ServerAccept_Thread.start();
	}
	
	public boolean Socket_Server_Close() {
		
		try {
			server.close();
			
			return server.isClosed();	
		} catch(Exception e) {
			TrackerControlManager.logger.error("[ TrackerController ] Failed to Close Server Port :: " + e.toString());
			return false;
		}		
	}
	
	public void Socket_Server_SetPushQueue(CustomQueue<Short> _nQueue) 
	{
		PushQueue = _nQueue;
	}	
	
	public void Socket_Server_SetPopQueue(CustomQueue<short[]> _nQueue) 
	{
		PopQueue = _nQueue;
	}	
}

class ThreadServerHandler extends Thread {
	
	Socket clientSocket;
	CustomQueue<Short> nQueue;
	
	public ThreadServerHandler(Socket _socket, CustomQueue<Short> _nQueue) {
		clientSocket = _socket;
		nQueue = _nQueue;
		start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {			
			InputStream is = clientSocket.getInputStream();
			String _szHandshake = "";
			int ReadByte = 0;
			byte[] _bytArr;
			clientSocket.setSoTimeout(5000);
			
			while(true) {		// Step of Verify Web Socket Protocol 
				
				_szHandshake += String.format("%c",ReadByte = is.read());				
				
				if(is.available() == 0) {
					
					//Debug 
					//System.out.println("_szHandshake :: " + _szHandshake);
					
					if(WSocket_Handshaker(_szHandshake)) {
						_szHandshake = "";
					}
					else {
						clientSocket.close();
						System.out.println("[ TrackerController ] Handshake failed.. client disconnected");
					}
					break;	
				} 
				else if(ReadByte == -1) {
					TrackerControlManager.logger.debug("[ TrackerController ] WebSocket Thread for " + clientSocket.getRemoteSocketAddress().toString().replace("/", "") + " has killed.");
					break;
				}
			}
			
			clientSocket.setSoTimeout(600000);
						
			while(true) {		
					if((ReadByte = is.read()) == -1) {
						TrackerControlManager.logger.debug("[ TrackerController ] WebSocket Thread for " + clientSocket.getRemoteSocketAddress().toString().replace("/", "") + " has killed.");
						break;
					}
					
					else if(is.available() > 0) {
						synchronized(nQueue) {	
						nQueue.Enqueue((short)ReadByte);
							while((is.available()) > 0) {
								
									//ReadByte = ReadByte & 0xFF;
									//System.out.println("# Socket Receive Byte :" + ReadByte);
									//synchronized(nQueue) {
								
									nQueue.Enqueue((short)is.read());
									
									if(is.available() == 0) {
										//nQueue.Enqueue((short)0x00);		// ETX�� Ȯ���� �����ϱ� ���� ETX ������ null�� �߰��Ѵ�.
										System.out.println();//System.out.println(clientSocket.getRemoteSocketAddress() + " has disconnected.");
									}
									
									if(this.isInterrupted()) break;
							}
						}
					}
					
					//System.out.println("aaa");
					Thread.sleep(1);
				}
		} catch(Exception e) {
			System.out.println("[ TrackerController ] Failed to Receive Message From Server :: " + e.toString());
			if(clientSocket != null) {
				try {
				clientSocket.close();
				} catch(Exception _e) {
					_e.printStackTrace();
				}
				finally {
				clientSocket = null;
				}
			}
		}
	}
	
	private boolean WSocket_Handshaker(String _pkt) {
		String recvMsg = "";
		String[] _strArr = _pkt.split("\r\n");
		
		try {
		HashMap<String , String> Hash = new HashMap<String, String>();
		
		for(String s : _strArr) {
			String[] _sa = s.split(":");
			
			if(_sa.length > 2) { 
				Hash.put(_sa[0], _sa[1] + _sa[2]);
			}
			else if(_sa.length == 2)
				Hash.put(_sa[0],_sa[1]);
		}
		
		//System.out.println(Hash.get("Sec-WebSocket-Key").trim() + " -> " + new String(Util.EncryptWSKey(Hash.get("Sec-WebSocket-Key").trim())));
		
		if(Hash.size() > 0 && !Hash.get("Sec-WebSocket-Key").equals(null)) {
			String sendMsg = "HTTP/1.1 101 Web Socket Protocol Handshake\r\n" +
					"Connection: Upgrade\r\n" + 
					
					"Sec-WebSocket-Accept: " + new String(Utils.EncryptWSKey(Hash.get("Sec-WebSocket-Key").trim())) + "\r\n" + 
					"Server: Tracker Control\r\n" +
					"Upgrade: websocket\r\n\r\n";
					//"Sec-WebSocket-Protocol: chat\r\n" + ;
			
			//System.out.println(sendMsg);
			//System.out.println("Bytes :[" + new String(sendMsg.getBytes()) + "]");
			
			clientSocket.getOutputStream().write(sendMsg.getBytes());
		}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
