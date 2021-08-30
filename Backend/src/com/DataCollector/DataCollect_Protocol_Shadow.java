package com.DataCollector;

import com.Protocol.Protocol_Foot_ForString;

/* ------------------------------------------------- */
/*				공동연구법인 - 태양광 모니터링 서버 개발 		 */
/*				데이터 수집서버 프로그램 개발 				 */
/*													 */
/*						19-05-03					 */
/*				음영제어 프로토콜의  Body 부분 기술.			 */
/*													 */
/* ------------------------------------------------- */

public class DataCollect_Protocol_Shadow {
	
	com.Protocol.Protocol_Header_ForString header;
	com.Protocol.Protocol_Body_ShadowData body;
	Protocol_Foot_ForString foot;

	public DataCollect_Protocol_Shadow() {
		header = new com.Protocol.Protocol_Header_ForString();
		body = new com.Protocol.Protocol_Body_ShadowData();
		foot = new Protocol_Foot_ForString();
	}
	
	public com.Protocol.Protocol_Header_ForString GetHeader() {
		return header;
	}
	
	public com.Protocol.Protocol_Body_ShadowData GetBody() {
		return body;
	}
	
	public Protocol_Foot_ForString GetFooter() {
		return foot;
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
