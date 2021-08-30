package com.DataSimulator;

import java.util.Random;

import com.Common.Utils;

/* ------------------------------------------------- */
/*				공동연구법인 - 태양광 모니터링 서버 개발 		 */
/*				데이터 수집서버 프로그램 개발 				 */
/*													 */
/*				센서 프로토콜의  Body 부분 기술.			 */
/*													 */
/* ------------------------------------------------- */

public class DataSimulator_Protocol_Sensor {
	
	Protocol_Header_ForString header;
	Protocol_Body_SensorData body;
	Protocol_Footer_ForString foot;

	public DataSimulator_Protocol_Sensor() {
		header = new Protocol_Header_ForString();
		body = new Protocol_Body_SensorData();
		foot = new Protocol_Footer_ForString();
	}
	
	public Protocol_Header_ForString GetHeader() {
		return header;
	}
	
	public Protocol_Body_SensorData GetBody() {
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
		header.setCMD("W");
		header.setSI_ID(_nSID);
		header.setTR_ID(_nTID);
		
 		body.setfAmbientTemp((_rand.nextFloat() * 100) %100.0f);
		body.setfModuleTemp((_rand.nextFloat() * 100) %100.0f);
		body.setfControllerTemp((_rand.nextFloat() * 100) %100.0f);
		body.setfInnerRltHumid((_rand.nextFloat() * 100) %100.0f);
		body.setnHorizontalIDT((_rand.nextInt(9999)));
		body.setnSlopeIDT((_rand.nextInt(9999)));
		body.setfWindSpeed((_rand.nextFloat() * 100) %100.0f);
		
		foot.setCS("00");
		foot.setETX("\r\n");
			
		_szStrBody = String.format("%.1f,%.1f,%.1f,%.1f,%d,%d,%.1f", body.getfAmbientTemp(), body.getfModuleTemp(), body.getfControllerTemp(), body.getfInnerRltHumid(), body.getnHorizontalIDT(), body.getnSlopeIDT(), body.getfWindSpeed());
		
		_szStr = String.format("%s,%s,%04d,%03d,%s,%s%s",
				header.getSTX(), header.getCMD(), header.getSI_ID(), header.getTR_ID(),
				_szStrBody,
				foot.getCS(), foot.getETX());
		
		foot.setCS(String.format("%02x", Utils.CalculateCS( Utils.byteToInteger(_szStr.substring(0,_szStr.length()-4).getBytes()))) );	// origin : 16 , _szStr.length()-5
		
		_szStr = String.format("%s,%s,%04d,%03d,%s,%s%s",
				header.getSTX(), header.getCMD(), header.getSI_ID(), header.getTR_ID(),
				_szStrBody,
				foot.getCS(), foot.getETX());
		
		DataSimulatorManager.logger.debug("[ Send Sensor data. length : " + _szStr.length() + " \t] " + _szStr);
		
		
		return _szStr;
	}
	
	public String GetGenerate(int _nSID,int _nTID) {
		
		String _szStr = "";
		String _szStrBody = "";
		Random _rand = new Random();
		
		
		header.setSTX("$$SC");
		header.setCMD("W");
		header.setSI_ID(_nSID);
		header.setTR_ID(_nTID);
		
 		body.setfAmbientTemp((_rand.nextFloat() * 100) %100.0f);
		body.setfModuleTemp(((_rand.nextFloat() * 100) + body.getfAmbientTemp()) % 100.0f);
		body.setfControllerTemp(((_rand.nextFloat() * 100) + body.getfAmbientTemp()) % 100.0f);
		body.setfInnerRltHumid((_rand.nextFloat() * 100) %100.0f);
		body.setnHorizontalIDT((_rand.nextInt(9999)));
		body.setnSlopeIDT((_rand.nextInt(9999)));
		body.setfWindSpeed((_rand.nextFloat() * 100) %100.0f);
		
		foot.setCS("00");
		foot.setETX("\r\n");
			
		_szStrBody = String.format("%.1f,%.1f,%.1f,%.1f,%d,%d,%.1f", body.getfAmbientTemp(), body.getfModuleTemp(), body.getfControllerTemp(), body.getfInnerRltHumid(), body.getnHorizontalIDT(), body.getnSlopeIDT(), body.getfWindSpeed());
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD()
				,_szStrBody
				, foot.getCS(), foot.getETX());
		
		foot.setCS(String.format("%02x", Utils.CalculateCS(Utils.byteToInteger(_szStr.substring(0,_szStr.length()-4).getBytes()))) );		// origin : 16 , _szStr.length()-5
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD()
				,_szStrBody
				,foot.getCS(), foot.getETX());
		
		DataSimulatorManager.logger.debug("[ Send Sensor data. length : " + _szStr.length() + " \t] " + _szStr);
		
		
		return _szStr;
	}
	
	public String GetGenerateSerial(int _nSID,int _nTID, int _nSerialCount) {
		
		String _szStr = "";
		String _szStrBody = "";		
		
		header.setSTX("$$SC");
		header.setCMD("W");
		header.setSI_ID(_nSID);
		header.setTR_ID(_nTID);
		
 		body.setfAmbientTemp(_nSerialCount);
		body.setfModuleTemp(_nSerialCount);
		body.setfControllerTemp(_nSerialCount);
		body.setfInnerRltHumid(_nSerialCount);
		body.setnHorizontalIDT(_nSerialCount);
		body.setnSlopeIDT(_nSerialCount);
		body.setfWindSpeed(_nSerialCount);
		
		foot.setCS("00");
		foot.setETX("\r\n");
			
		_szStrBody = String.format("%.1f,%.1f,%.1f,%.1f,%d,%d,%.1f", body.getfAmbientTemp(), body.getfModuleTemp(), body.getfControllerTemp(), body.getfInnerRltHumid(), body.getnHorizontalIDT(), body.getnSlopeIDT(), body.getfWindSpeed());
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD(),
				_szStrBody,
				foot.getCS(), foot.getETX());
		
		foot.setCS(String.format("%02x", Utils.CalculateCS(Utils.byteToInteger(_szStr.substring(0,_szStr.length()-4).getBytes()))) );	// origin : 16 , _szStr.length()-5
		
		_szStr = String.format("%s,%04d,%03d,%s,%s,%s%s",
				header.getSTX(), header.getSI_ID(), header.getTR_ID(), header.getCMD(),
				_szStrBody,
				foot.getCS(), foot.getETX());
		
		DataSimulatorManager.logger.debug("[ Send Sensor data. length : " + _szStr.length() + " \t] " + _szStr);
		
		
		return _szStr;
	}
	
}

class Protocol_Body_SensorData {
	private int nGroupID;
	private int nTrackerID;
	private float fAmbientTemp;
	private float fModuleTemp;
	private float fControllerTemp;
	private float fInnerRltHumid;
	private int nHorizontalIDT;
	private int nSlopeIDT;
	private float fWindSpeed;
	
	public Protocol_Body_SensorData() {
		
	}
	
	public Protocol_Body_SensorData(float fAmbientTemp, float fModuleTemp, float fControllerTemp ,float fInnerRltHumid, 
							 int nHorizontalIDT, int nSlopeIDT, float fWindSpeed) {
		this.fAmbientTemp = fAmbientTemp;
		this.fModuleTemp = fModuleTemp;
		this.fControllerTemp = fControllerTemp;
		this.fInnerRltHumid = fInnerRltHumid;
		this.nHorizontalIDT = nHorizontalIDT;
		this.nSlopeIDT = nSlopeIDT;
		this.fWindSpeed = fWindSpeed;
		
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
	public float getfAmbientTemp() {
		return fAmbientTemp;
	}
	public void setfAmbientTemp(float fAmbientTemp) {
		this.fAmbientTemp = fAmbientTemp;
	}
	
	
	public float getfModuleTemp() {
		return fModuleTemp;
	}
	public void setfModuleTemp(float fModuleTemp) {
		this.fModuleTemp = fModuleTemp;
	}
	
	public float getfControllerTemp() {
		return fControllerTemp;
	}
	public void setfControllerTemp(float fControllerTemp) {
		this.fControllerTemp = fControllerTemp;
	}
	
	public float getfInnerRltHumid() {
		return fInnerRltHumid;
	}

	public void setfInnerRltHumid(float fInnerRltHumid) {
		this.fInnerRltHumid = fInnerRltHumid;
	}
	
	public int getnHorizontalIDT() {
		return nHorizontalIDT;
	}
	public void setnHorizontalIDT(int nHorizontalIDT) {
		this.nHorizontalIDT = nHorizontalIDT;
	}
	public int getnSlopeIDT() {
		return nSlopeIDT;
	}
	public void setnSlopeIDT(int nSlopeIDT) {
		this.nSlopeIDT = nSlopeIDT;
	}
	public float getfWindSpeed() {
		return fWindSpeed;
	}
	public void setfWindSpeed(float fWindSpeed) {
		this.fWindSpeed = fWindSpeed;
	}
	
}
