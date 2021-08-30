package com.Protocol;

public class Protocol_Header {
	/* STX  		4 Bytes */ private short[] 	STX;
	/* SITE_ID  	4 Bytes */ private short[] 	SI_ID;
	/* TRACKER_ID   3 Bytes */ private short[] 	TR_ID;
	/* CMD  		1 Byte  */ private short 	CMD;
	
	public Protocol_Header() {
		STX = new short[4];
		SI_ID = new short[4];
		TR_ID = new short[3];
		CMD = 0x00;
	}
	
	public short[] getSTX() {
		return STX;
	}
	
	public void setSTX(short[] STX) {
		this.STX = STX;
	}
	
	public short getCMD() {
		return CMD;
	}
	
	public void setCMD(short CMD) {
		this.CMD = CMD;
	}
	
	public short[] getSI_ID() {
		return SI_ID;
	}
	
	public void setSI_ID(short[] SI_ID) {
		this.SI_ID = SI_ID;
	}
	
	public short[] getTR_ID() {
		return TR_ID;
	}
	
	public void setTR_ID(short[] TR_ID) {
		this.TR_ID = TR_ID;
	}
}
