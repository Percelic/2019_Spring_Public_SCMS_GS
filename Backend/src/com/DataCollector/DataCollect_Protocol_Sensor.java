package com.DataCollector;

import com.Protocol.Protocol_Foot_ForString;
import com.Protocol.Protocol_Header_ForString;

/* ------------------------------------------------- */
/*				공동연구법인 - 태양광 모니터링 서버 개발 		 */
/*				데이터 수집서버 프로그램 개발 				 */
/*													 */
/*				센서 프로토콜의  Body 부분 기술.			 */
/*													 */
/* ------------------------------------------------- */

public class DataCollect_Protocol_Sensor {
	
	Protocol_Header_ForString header;
	Protocol_Body_SensorData body;
	Protocol_Foot_ForString foot;

	public DataCollect_Protocol_Sensor() {
		header = new Protocol_Header_ForString();
		body = new Protocol_Body_SensorData();
		foot = new Protocol_Foot_ForString();
	}
	
	public Protocol_Header_ForString GetHeader() {
		return header;
	}
	
	public Protocol_Body_SensorData GetBody() {
		return body;
	}
	
	public Protocol_Foot_ForString GetFooter() {
		return foot;
	}
	
}

class Protocol_Body_SensorData {
	private float fAmbientTemp;
	private float fModuleTemp;
	private float fControllerTemp;
	private float fInnerRltHumid;
	private int nHorizontalIDT;
	private int nSlopeIDT;
	private float fWindSpeed;
	
	public Protocol_Body_SensorData() {
		
	}
	
	public Protocol_Body_SensorData(float fAmbientTemp, float fModuleTemp, float fControllerTemp, float fInnerRltHumid,
							 int nHorizontalIDT, int nSlopeIDT, float fWindSpeed) {
		this.fAmbientTemp = fAmbientTemp;
		this.fModuleTemp = fModuleTemp;
		this.fControllerTemp = fControllerTemp;
		this.fInnerRltHumid = fInnerRltHumid;
		this.nHorizontalIDT = nHorizontalIDT;
		this.nSlopeIDT = nSlopeIDT;
		this.fWindSpeed = fWindSpeed;
		
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
