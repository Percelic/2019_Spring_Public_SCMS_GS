package com.DataSimulator;

import java.util.Random;

import com.Common.Utils;

/* ------------------------------------------------- */
/*				공동연구법인 - 태양광 모니터링 서버 개발 		 */
/*				데이터 수집서버 프로그램 개발 				 */
/*													 */
/*						19-05-03					 */
/*				음영제어 프로토콜의  Body 부분 기술.			 */
/*													 */
/* ------------------------------------------------- */

public class DataSimulator_Protocol_Shadow {
	
	Protocol_Header_ForString header;
	Protocol_Body_ShadowData body;
	Protocol_Footer_ForString foot;

	public DataSimulator_Protocol_Shadow() {
		header = new Protocol_Header_ForString();
		body = new Protocol_Body_ShadowData();
		foot = new Protocol_Footer_ForString();
	}
	
	public Protocol_Header_ForString GetHeader() {
		return header;
	}
	
	public Protocol_Body_ShadowData GetBody() {
		return body;
	}
	
	public Protocol_Footer_ForString GetFooter() {
		return foot;
	}
	
	public String GetRandomGenerate(int _nSID ,int _nTID) {
		String _szStr = "";
		String _szStrBody = "";
		Random _rand = new Random();
		
		
		header.setSTX("$$SC");
		header.setCMD("R");
		header.setSI_ID(_nSID);
		header.setTR_ID(_nTID);
		
 		body.setnWCDS1(_rand.nextInt(2));
		body.setnWCDS2(_rand.nextInt(2));
		body.setnWCDS3(_rand.nextInt(2));
		body.setnECDS1(_rand.nextInt(2));
		body.setnECDS2(_rand.nextInt(2));
		body.setnECDS3(_rand.nextInt(2));
		
		foot.setCS("00");
		foot.setETX("\r\n");
			
		_szStrBody = String.format("%d,%d,%d,%d,%d,%d", body.getnWCDS1(), body.getnWCDS2(), body.getnWCDS3(), body.getnECDS1(), body.getnECDS2(), body.getnECDS3());
		
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
	
	public String GetGenerate(int _nSID,int _nTID) {
		
		String _szStr = "";
		String _szStrBody = "";
		Random _rand = new Random();
		
		
		header.setSTX("$$SC");
		header.setCMD("R");
		header.setSI_ID(_nSID);
		header.setTR_ID(_nTID);
		
		body.setnWCDS1(_rand.nextInt(2));
		body.setnWCDS2(_rand.nextInt(2));
		body.setnWCDS3(_rand.nextInt(2));
		body.setnECDS1(_rand.nextInt(2));
		body.setnECDS2(_rand.nextInt(2));
		body.setnECDS3(_rand.nextInt(2));
		
		foot.setCS("00");
		foot.setETX("\r\n");
			
		_szStrBody = String.format("%d,%d,%d,%d,%d,%d", body.getnWCDS1(), body.getnWCDS2(), body.getnWCDS3(), body.getnECDS1(), body.getnECDS2(), body.getnECDS3());
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD()
				,_szStrBody
				, foot.getCS(), foot.getETX());
		
		foot.setCS(String.format("%02x", Utils.CalculateCS(Utils.byteToInteger(_szStr.substring(0,_szStr.length()-4).getBytes()))) );	// origin : 16 , _szStr.length()-5
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD()
				,_szStrBody
				,foot.getCS(), foot.getETX());
		
		DataSimulatorManager.logger.debug("[ Send Shadow data. length : " + _szStr.length() + " \t] " + _szStr);
		
		
		return _szStr;
	}
	
	public String GetGenerateSerial(int _nSID,int _nTID, int _nSerialCount) {
		
		String _szStr = "";
		String _szStrBody = "";		
		
		header.setSTX("$$SC");
		header.setCMD("R");
		header.setSI_ID(_nSID);
		header.setTR_ID(_nTID);
		
		body.setnWCDS1(_nSerialCount % 2);
		body.setnWCDS2(_nSerialCount % 2);
		body.setnWCDS3(_nSerialCount % 2);
		body.setnECDS1(_nSerialCount % 2);
		body.setnECDS2(_nSerialCount % 2);
		body.setnECDS3(_nSerialCount % 2);
		
		foot.setCS("00");
		foot.setETX("\r\n");
			
		_szStrBody = String.format("%d,%d,%d,%d,%d,%d", body.getnWCDS1(), body.getnWCDS2(), body.getnWCDS3(), body.getnECDS1(), body.getnECDS2(), body.getnECDS3());
		
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

class Protocol_Body_ShadowData {
	private int nGroupID;
	private int nTrackerID;
	private int nWCDS1;
	private int nWCDS2;
	private int nWCDS3;
	private int nECDS1;
	private int nECDS2;
	private int nECDS3;
	
	public Protocol_Body_ShadowData() {
		
	}
	
	public Protocol_Body_ShadowData(int nWCDS1, int nWCDS2, int nWCDS3,
							 int nECDS1, int nECDS2, int nECDS3) {
		this.nWCDS1 = nWCDS1;
		this.nWCDS2 = nWCDS2;
		this.nWCDS3 = nWCDS3;
		this.nECDS1 = nECDS1;
		this.nECDS2 = nECDS2;
		this.nECDS3 = nECDS3;	
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

	public int getnWCDS1() {
		return nWCDS1;
	}

	public void setnWCDS1(int nWCDS1) {
		this.nWCDS1 = nWCDS1;
	}

	public int getnWCDS2() {
		return nWCDS2;
	}

	public void setnWCDS2(int nWCDS2) {
		this.nWCDS2 = nWCDS2;
	}

	public int getnWCDS3() {
		return nWCDS3;
	}

	public void setnWCDS3(int nWCDS3) {
		this.nWCDS3 = nWCDS3;
	}

	public int getnECDS1() {
		return nECDS1;
	}

	public void setnECDS1(int nECDS1) {
		this.nECDS1 = nECDS1;
	}

	public int getnECDS2() {
		return nECDS2;
	}

	public void setnECDS2(int nECDS2) {
		this.nECDS2 = nECDS2;
	}

	public int getnECDS3() {
		return nECDS3;
	}

	public void setnECDS3(int nECDS3) {
		this.nECDS3 = nECDS3;
	}
	
	
	
}
