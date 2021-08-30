package com.Protocol;

public class Protocol_Header_ForString {
	private String STX;
	private int SI_ID;
	private int TR_ID;
	private String CMD;
	
	public Protocol_Header_ForString() {
		STX = "";
		SI_ID = 0;
		TR_ID = 0;
		CMD = "";
	}

	public String getSTX() {
		return STX;
	}

	public void setSTX(String sTX) {
		STX = sTX;
	}

	public String getCMD() {
		return CMD;
	}

	public void setCMD(String cMD) {
		CMD = cMD;
	}

	public int getSI_ID() {
		return SI_ID;
	}

	public void setSI_ID(int sI_ID) {
		SI_ID = sI_ID;
	}

	public int getTR_ID() {
		return TR_ID;
	}

	public void setTR_ID(int tR_ID) {
		TR_ID = tR_ID;
	}	
}
