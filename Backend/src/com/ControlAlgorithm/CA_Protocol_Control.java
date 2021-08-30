package com.ControlAlgorithm;

public class CA_Protocol_Control {

	Protocol_Header_ForString header;
	Protocol_Body_ControlData body;
	Protocol_Footer_ForString footer;
	
	public CA_Protocol_Control() {
		header = new Protocol_Header_ForString();
		body = new Protocol_Body_ControlData();
		footer = new Protocol_Footer_ForString();
	}
	
	public Protocol_Header_ForString GetHeader() {
		return header;
	}
	
	public Protocol_Body_ControlData GetBody() {
		return body;
	}
	
	public Protocol_Footer_ForString GetFooter() {
		return footer;
	}
	
	public String GetString() {
		String _szStr = "";
		
		_szStr += header.getSTX() + ",";
		_szStr += header.getSI_ID() + ",";
		_szStr += header.getTR_ID() + ",";
		_szStr += header.getCMD() + ",";
		_szStr += body.getMode() + ",";
		_szStr += body.getUDShift() + ",";
		_szStr += body.getLRShift() + ",";
		_szStr += footer.getCS();
		_szStr += footer.getETX();
		
		return _szStr;
	}
	
}

class Protocol_Body_ControlData {
	String Mode;
	String UDShift;
	String LRShift;

	public String getMode() {
		return Mode;
	}

	public void setMode(String mode) {
		Mode = mode;
	}
	
	public String getUDShift() {
		return UDShift;
	}
	
	public void setUDShift(String udshift) {
		UDShift = udshift;
	}
	
	public String getLRShift() {
		return LRShift;
	}
	
	public void setLRShift(String lrshift) {
		LRShift = lrshift;
	}
}
