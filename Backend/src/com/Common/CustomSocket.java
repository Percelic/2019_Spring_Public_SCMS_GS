package com.Common;
import java.net.Socket;

public class CustomSocket {

	private Socket socket;
	private boolean isRun;				// is running
	private String szMode;				// set mode
	private boolean isControl;			// is controlled
	private int nUpShift;			
	private int nLeftShift;
	private int nGID;
	
	public CustomSocket(Socket _socket) {
		socket = _socket;
		szMode = "h";
		isRun = false;
		isControl = false;
		nUpShift = 0;
		nLeftShift = 0;
		nGID = 0;
		
	}
	
	public void setSocket(Socket _socket) {
		socket = _socket;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public void disposeSocket() {
		try {
		socket.close();
		socket = null;
		
		} catch(Exception e) {
			
		}
	}
	
	public void setIsRun(boolean _isRun) {
		isRun = _isRun;
	}
	
	public boolean getIsRun() {
		return isRun;
	}
	
	public void setIsControl(boolean _isControl) {
		isControl = _isControl;
	}
	
	public boolean getIsControl() {
		return isControl;
	}
	
	public void setUpShift(int _nUpShift) {
		nUpShift = _nUpShift;
	}
	
	public int getUpShift() {
		return nUpShift;
	}
	
	public void setLeftShift(int _nLeftShift) {
		nLeftShift = _nLeftShift;
	}
	
	public int getLeftShift() {
		return nLeftShift;
	}
	
	public void setMode(String _cMode) {
		szMode = _cMode;
	}
	
	public String getMode() {
		return szMode;
	}

	public int getnGID() {
		return nGID;
	}

	public void setnGID(int nGID) {
		this.nGID = nGID;
	}
}
