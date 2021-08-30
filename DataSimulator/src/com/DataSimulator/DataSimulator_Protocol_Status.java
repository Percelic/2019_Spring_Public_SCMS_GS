package com.DataSimulator;

import java.util.Random;

import com.Common.Utils;

/* ------------------------------------------------- */
/*				������������ - �¾籤 ����͸� ���� ���� 		 */
/*				������ �������� ���α׷� ���� 				 */
/*													 */
/*						19-05-03					 */
/*				�������� ����������  Body �κ� ���.			 */
/*													 */
/* ------------------------------------------------- */

public class DataSimulator_Protocol_Status {
	
	Protocol_Header_ForString header;
	Protocol_Body_StatusData body;
	Protocol_Footer_ForString foot;

	public DataSimulator_Protocol_Status() {
		header = new Protocol_Header_ForString();
		body = new Protocol_Body_StatusData();
		foot = new Protocol_Footer_ForString();
	}
	
	public Protocol_Header_ForString GetHeader() {
		return header;
	}
	
	public Protocol_Body_StatusData GetBody() {
		return body;
	}
	
	public Protocol_Footer_ForString GetFooter() {
		return foot;
	}
	
	public String GetRandomGenerate(int _nSID ,int _nTID, int _nCount) {
		String _szStr = "";
		String _szStrBody = "";
		Random _rand = new Random();
		
		
		header.setSTX("$$SC");
		header.setCMD("X");
		header.setSI_ID(_nSID);
		header.setTR_ID(_nTID);
		
		body.setnCount(_nCount);
		body.setnX1(13000 + _rand.nextInt(10000));
		body.setnX2(0);
		body.setnX3(13000 + _rand.nextInt(10000));
		body.setnX4(0);
		body.setnX5(_nTID);
		body.setnX6(_nSID);
		body.setnX7(_rand.nextInt(24));
		body.setnX8(_rand.nextInt(60));
		body.setnX9(_rand.nextInt(60));
		
		foot.setCS("00");
		foot.setETX("\r\n");
			
		_szStrBody = String.format("%d,%d,%d,%d,%d,%d,%d,%d,%d,%d", body.getnCount() , body.getnX1(), body.getnX2(), body.getnX3(), body.getnX4(), body.getnX5(), body.getnX6(), body.getnX7(), body.getnX8(), body.getnX9());
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD(),	_szStrBody,	foot.getCS(), foot.getETX());
		
		foot.setCS(String.format("%02x", Utils.CalculateCS(Utils.byteToInteger(_szStr.substring(0,_szStr.length()-4).getBytes()))) );	// origin : 16 , _szStr.length()-5
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD(),
				_szStrBody,
				foot.getCS(), foot.getETX());
		
		DataSimulatorManager.logger.debug("[ Send Status data. length : " + _szStr.length() + " \t] " + _szStr);
		
		
		return _szStr;
	}
	
	public String GetGenerate(int _nSID,int _nTID, int _nCount) {
		
		String _szStr = "";
		String _szStrBody = "";
		Random _rand = new Random();
		
		
		header.setSTX("$$SC");
		header.setCMD("X");
		header.setSI_ID(_nSID);
		header.setTR_ID(_nTID);
		
		body.setnCount(_nCount);
		body.setnX1(13000 + _rand.nextInt(10000));
		body.setnX2(0);
		body.setnX3(13000 + _rand.nextInt(10000));
		body.setnX4(0);
		body.setnX5(_nTID);
		body.setnX6(_nSID);
		body.setnX7(_rand.nextInt(24));
		body.setnX8(_rand.nextInt(60));
		body.setnX9(_rand.nextInt(60));
		
		foot.setCS("00");
		foot.setETX("\r\n");
			
		_szStrBody = String.format("%d,%d,%d,%d,%d,%d,%d,%d,%d,%d", body.getnCount() , body.getnX1(), body.getnX2(), body.getnX3(), body.getnX4(), body.getnX5(), body.getnX6(), body.getnX7(), body.getnX8(), body.getnX9());
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD()
				,_szStrBody
				, foot.getCS(), foot.getETX());
		
		foot.setCS(String.format("%02x", Utils.CalculateCS(Utils.byteToInteger(_szStr.substring(0,_szStr.length()-4).getBytes()))) );	// origin : 16 , _szStr.length()-5
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD()
				,_szStrBody
				,foot.getCS(), foot.getETX());
		
		DataSimulatorManager.logger.debug("[ Send Status data. length : " + _szStr.length() + " \t] " + _szStr);
		
		
		return _szStr;
	}
	
	public String GetGenerateSerial(int _nSID,int _nTID, int _nSerialCount) {
		
		String _szStr = "";
		String _szStrBody = "";		
		
		header.setSTX("$$SC");
		header.setCMD("X");
		header.setSI_ID(_nSID);
		header.setTR_ID(_nTID);
		
		body.setnCount(_nSerialCount);
		body.setnX1(_nSerialCount);
		body.setnX2(0);
		body.setnX3(_nSerialCount);
		body.setnX4(0);
		body.setnX5(_nTID);
		body.setnX6(_nSID);
		body.setnX7(_nSerialCount);
		body.setnX8(_nSerialCount);
		body.setnX9(_nSerialCount);
		
		foot.setCS("00");
		foot.setETX("\r\n");
			
		_szStrBody = String.format("%d,%d,%d,%d,%d,%d,%d,%d,%d,%d", body.getnCount() ,body.getnX1(), body.getnX2(), body.getnX3(), body.getnX4(), body.getnX5(), body.getnX6(), body.getnX7(),  body.getnX8(), body.getnX9());
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD(),
				_szStrBody,
				foot.getCS(), foot.getETX());
		
		foot.setCS(String.format("%02x", Utils.CalculateCS(Utils.byteToInteger(_szStr.substring(0,_szStr.length()-4).getBytes()))) );	// origin : 16 , _szStr.length()-5
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD(),
				_szStrBody,
				foot.getCS(), foot.getETX());
		
		DataSimulatorManager.logger.debug("[ Send Shadow data. length : " + _szStr.length() + " \t] " + _szStr);
		
		
		return _szStr;
	}
	
}

class Protocol_Body_StatusData {
	private int nGroupID;
	private int nTrackerID;
	private int nCount;
	private int nX1;
	private int nX2;
	private int nX3;
	private int nX4;
	private int nX5;
	private int nX6;
	private int nX7;
	private int nX8;
	private int nX9;
	
	public Protocol_Body_StatusData() {
		
	}
	
	public Protocol_Body_StatusData(int nCount ,int nX1, int nX2, int nX3, int nX4, int nX5, int nX6, int nX7, int nX8, int nX9) {
		this.nCount = nCount;
		this.nX1 = nX1;
		this.nX2 = nX2;
		this.nX3 = nX3;
		this.nX4 = nX4;
		this.nX5 = nX5;
		this.nX6 = nX6;	
		this.nX7 = nX7;	
		this.nX8 = nX8;	
		this.nX9 = nX9;	
	}
	
	
	public int getnTrackerID() {
		return nTrackerID;
	}
	public void setnTrackerID(int nTrackerID) {
		this.nTrackerID = nTrackerID;
	}
	public int getnGroupID() {
		return nGroupID;
	}
	public void setnGroupID(int nGroupID) {
		this.nGroupID = nGroupID;
	}

	public int getnCount() {
		return nCount;
	}

	public void setnCount(int nCount) {
		this.nCount = nCount;
	}

	public int getnX1() {
		return nX1;
	}

	public void setnX1(int nX1) {
		this.nX1 = nX1;
	}

	public int getnX2() {
		return nX2;
	}

	public void setnX2(int nX2) {
		this.nX2 = nX2;
	}

	public int getnX3() {
		return nX3;
	}

	public void setnX3(int nX3) {
		this.nX3 = nX3;
	}

	public int getnX4() {
		return nX4;
	}

	public void setnX4(int nX4) {
		this.nX4 = nX4;
	}

	public int getnX5() {
		return nX5;
	}

	public void setnX5(int nX5) {
		this.nX5 = nX5;
	}

	public int getnX6() {
		return nX6;
	}

	public void setnX6(int nX6) {
		this.nX6 = nX6;
	}

	public int getnX7() {
		return nX7;
	}

	public void setnX7(int nX7) {
		this.nX7 = nX7;
	}

	public int getnX8() {
		return nX8;
	}

	public void setnX8(int nX8) {
		this.nX8 = nX8;
	}

	public int getnX9() {
		return nX9;
	}

	public void setnX9(int nX9) {
		this.nX9 = nX9;
	}
	
	
	
}
