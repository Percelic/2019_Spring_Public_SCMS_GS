package com.DataSimulator;

import java.util.Random;

import com.Common.Utils;

/* ------------------------------------------------- */
/*				공동연구법인 - 태양광 모니터링 서버 개발 		 */
/*				데이터 수집서버 프로그램 개발 				 */
/*													 */
/*						19-05-07					 */
/*				음영제어 ADC 프로토콜의  Body 부분 기술.		 */
/*													 */
/* ------------------------------------------------- */

public class DataSimulator_Protocol_Shadow_ADC {
	
	Protocol_Header_ForString header;
	Protocol_Body_ShadowADCData body;
	Protocol_Footer_ForString foot;

	public DataSimulator_Protocol_Shadow_ADC() {
		header = new Protocol_Header_ForString();
		body = new Protocol_Body_ShadowADCData();
		foot = new Protocol_Footer_ForString();
	}
	
	public Protocol_Header_ForString GetHeader() {
		return header;
	}
	
	public Protocol_Body_ShadowADCData GetBody() {
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
		header.setCMD("S");
		header.setSI_ID(_nSID);
		header.setTR_ID(_nTID);
		
 		body.setnADC1(_rand.nextInt(4096));
		body.setnADC2(_rand.nextInt(4096));
		body.setnADC3(_rand.nextInt(4096));
		body.setnADC4(_rand.nextInt(4096));
		body.setnADC5(_rand.nextInt(4096));
		body.setnADC6(_rand.nextInt(4096));
		body.setnADC7(_rand.nextInt(4096));
		body.setnADC8(_rand.nextInt(4096));
		
		foot.setCS("00");
		foot.setETX("\r\n");
			
		_szStrBody = String.format("%d,%d,%d,%d,%d,%d,%d,%d", body.getnADC1(), body.getnADC2(), body.getnADC3(), body.getnADC4(), body.getnADC5(), body.getnADC6(), body.getnADC7(),body.getnADC8());
		
		_szStr = String.format("%s,%s,%04d,%03d,%s,%s%s",
				header.getSTX(), header.getCMD(), header.getSI_ID(), header.getTR_ID(),
				_szStrBody,
				foot.getCS(), foot.getETX());
		
		foot.setCS(String.format("%02x", Utils.CalculateCS(Utils.byteToInteger(_szStr.substring(0,_szStr.length()-4).getBytes()))) ); // origin : 16 , _szStr.length()-5
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD(),
				_szStrBody,
				foot.getCS(), foot.getETX());
		
		DataSimulatorManager.logger.debug("[ Send ShadowADC data. length : " + _szStr.length() + " \t] " + _szStr);
		
		
		return _szStr;
	}
	
	public String GetGenerate(int _nSID,int _nTID) {
		
		String _szStr = "";
		String _szStrBody = "";
		Random _rand = new Random();
		
		
		header.setSTX("$$SC");
		header.setCMD("S");
		header.setSI_ID(_nSID);
		header.setTR_ID(_nTID);
		
		body.setnADC1(_rand.nextInt(4095));
		body.setnADC2(_rand.nextInt(4095));
		body.setnADC3(_rand.nextInt(4095));
		body.setnADC4(_rand.nextInt(4095));
		body.setnADC5(_rand.nextInt(4095));
		body.setnADC6(_rand.nextInt(4095));
		body.setnADC7(_rand.nextInt(4095));
		body.setnADC8(_rand.nextInt(4095));
		
		foot.setCS("00");
		foot.setETX("\r\n");
			
		_szStrBody = String.format("%d,%d,%d,%d,%d,%d,%d,%d", body.getnADC1(), body.getnADC2(), body.getnADC3(), body.getnADC4(), body.getnADC5(), body.getnADC6(), body.getnADC7(),body.getnADC8());
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD()
				,_szStrBody
				, foot.getCS(), foot.getETX());
		
		foot.setCS(String.format("%02x", Utils.CalculateCS(Utils.byteToInteger(_szStr.substring(0,_szStr.length()-4).getBytes()))) );	// origin : 16 , _szStr.length()-5
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD()
				,_szStrBody
				,foot.getCS(), foot.getETX());
		
		DataSimulatorManager.logger.debug("[ Send ShadowADC data. length : " + _szStr.length() + " \t] " + _szStr);
		
		
		return _szStr;
	}
	
	public String GetGenerateSerial(int _nSID,int _nTID, int _nSerialCount) {
		
		String _szStr = "";
		String _szStrBody = "";		
		
		header.setSTX("$$SC");
		header.setCMD("S");
		header.setSI_ID(_nSID);
		header.setTR_ID(_nTID);
		
		body.setnADC1(_nSerialCount);
		body.setnADC2(_nSerialCount);
		body.setnADC3(_nSerialCount);
		body.setnADC4(_nSerialCount);
		body.setnADC5(_nSerialCount);
		body.setnADC6(_nSerialCount);
		body.setnADC7(_nSerialCount);
		body.setnADC8(_nSerialCount);
		
		foot.setCS("00");
		foot.setETX("\r\n");
			
		_szStrBody = String.format("%d,%d,%d,%d,%d,%d,%d,%d", body.getnADC1(), body.getnADC2(), body.getnADC3(), body.getnADC4(), body.getnADC5(), body.getnADC6(), body.getnADC7(),body.getnADC8());
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD(),
				_szStrBody,
				foot.getCS(), foot.getETX());
		
		foot.setCS(String.format("%02x", Utils.CalculateCS(Utils.byteToInteger(_szStr.substring(0,_szStr.length()-4).getBytes()))) );	// origin : 16 , _szStr.length()-5
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD(),
				_szStrBody,
				foot.getCS(), foot.getETX());
		
		DataSimulatorManager.logger.debug("[ Send ShadowADC data. length : " + _szStr.length() + " \t] " + _szStr);
		
		
		return _szStr;
	}
	
}

class Protocol_Body_ShadowADCData {
	private int nGroupID;
	private int nTrackerID;
	private int nADC1;
	private int nADC2;
	private int nADC3;
	private int nADC4;
	private int nADC5;
	private int nADC6;
	private int nADC7;
	private int nADC8;
	
	public Protocol_Body_ShadowADCData() {
		
	}
	
	public Protocol_Body_ShadowADCData(int nADC1, int nADC2, int nADC3, int nADC4,
							 int nADC5, int nADC6, int nADC7, int nADC8) {
		this.nADC1 = nADC1;
		this.nADC2 = nADC2;
		this.nADC3 = nADC3;
		this.nADC4 = nADC4;
		this.nADC5 = nADC5;
		this.nADC6 = nADC6;
		this.nADC7 = nADC7;
		this.nADC8 = nADC8;
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

	public int getnADC1() {
		return nADC1;
	}

	public void setnADC1(int nADC1) {
		this.nADC1 = nADC1;
	}

	public int getnADC2() {
		return nADC2;
	}

	public void setnADC2(int nADC2) {
		this.nADC2 = nADC2;
	}

	public int getnADC3() {
		return nADC3;
	}

	public void setnADC3(int nADC3) {
		this.nADC3 = nADC3;
	}

	public int getnADC4() {
		return nADC4;
	}

	public void setnADC4(int nADC4) {
		this.nADC4 = nADC4;
	}

	public int getnADC5() {
		return nADC5;
	}

	public void setnADC5(int nADC5) {
		this.nADC5 = nADC5;
	}

	public int getnADC6() {
		return nADC6;
	}

	public void setnADC6(int nADC6) {
		this.nADC6 = nADC6;
	}

	public int getnADC7() {
		return nADC7;
	}

	public void setnADC7(int nADC7) {
		this.nADC7 = nADC7;
	}

	public int getnADC8() {
		return nADC8;
	}

	public void setnADC8(int nADC8) {
		this.nADC8 = nADC8;
	}
	
	
	
	
	
}
