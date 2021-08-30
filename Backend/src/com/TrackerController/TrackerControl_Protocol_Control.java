package com.TrackerController;

import com.Protocol.Protocol_Header_ForString;
import com.Protocol.Protocol_Foot_ForString;

public class TrackerControl_Protocol_Control {

	Protocol_Header_ForString header;
	Protocol_Body_ControlData body;
	Protocol_Foot_ForString footer;
	
	public TrackerControl_Protocol_Control() {
		header = new Protocol_Header_ForString();
		body = new Protocol_Body_ControlData();
		footer = new Protocol_Foot_ForString();
	}
	
	public Protocol_Header_ForString GetHeader() {
		return header;
	}
	
	public Protocol_Body_ControlData GetBody() {
		return body;
	}
	
	public Protocol_Foot_ForString GetFooter() {
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
	String IsModeChange;
	String Mode;
	String IsUDShift;
	String UDShift;
	String IsLRShift;
	String LRShift;

	public String getIsModeChange() {
		return IsModeChange;
	}

	public void setIsModeChange(String isModeChange) {
		IsModeChange = isModeChange;
	}

	public String getMode() {
		return Mode;
	}

	public void setMode(String mode) {
		Mode = mode;
	}
	
	public String getIsUDShift() {
		return IsUDShift;
	}

	public void setIsUDShift(String isUDShift) {
		IsUDShift = isUDShift;
	}

	public String getUDShift() {
		return UDShift;
	}
	
	public void setUDShift(String udshift) {
		UDShift = udshift;
	}
	
	public String getIsLRShift() {
		return IsLRShift;
	}

	public void setIsLRShift(String isLRShift) {
		IsLRShift = isLRShift;
	}
	
	public String getLRShift() {
		return LRShift;
	}
	
	public void setLRShift(String lrshift) {
		LRShift = lrshift;
	}
}
