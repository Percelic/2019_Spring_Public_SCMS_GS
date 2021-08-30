package com.DataSimulator;

import java.sql.Date;

public class DataSimulator_MessageData {

	private short shTID = 0;
	private Short[] shMessage = {0,};
	private Date dtMsgDate;
	private boolean bIsSend = false;
	
	
	public DataSimulator_MessageData() {
		// TODO Auto-generated constructor stub
		
	}

	public short GetshTID() {
		return shTID;
	}


	public void SetshTID(short shTID) {
		this.shTID = shTID;
	}


	public Short[] GetshMessage() {
		return shMessage;
	}


	public void SetshMessage(Short[] shMessage) {
		this.shMessage = shMessage;
	}


	public Date GetdtMsgDate() {
		return dtMsgDate;
	}


	public void SetdtMsgDate(Date dtMsgDate) {
		this.dtMsgDate = dtMsgDate;
	}


	public boolean IsbIsSend() {
		return bIsSend;
	}


	public void SetbIsSend(boolean bIsSend) {
		this.bIsSend = bIsSend;
	}
	
	
}
