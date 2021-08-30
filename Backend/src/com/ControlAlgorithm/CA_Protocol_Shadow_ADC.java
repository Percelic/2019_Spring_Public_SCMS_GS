package com.ControlAlgorithm;

import com.DataCollector.DataCollect_Protocol_Shadow_ADC;

/* ------------------------------------------------- */
/*				공동연구법인 - 태양광 모니터링 서버 개발 		 */
/*				데이터 수집서버 프로그램 개발 				 */
/*													 */
/*						19-05-07					 */
/*				음영제어 ADC 프로토콜의  Body 부분 기술.		 */
/*													 */
/* ------------------------------------------------- */

public class CA_Protocol_Shadow_ADC extends DataCollect_Protocol_Shadow_ADC {
	
	Protocol_Header_ForString header;
	Protocol_Body_ShadowADCData body;
	Protocol_Footer_ForString foot;

	public CA_Protocol_Shadow_ADC() {
		header = new Protocol_Header_ForString();
		body = new Protocol_Body_ShadowADCData();
		foot = new Protocol_Footer_ForString();
	}
	
//	public Protocol_Header_ForString GetHeader() {
//		return header;
//	}
//	
//	public Protocol_Body_ShadowADCData GetBody() {
//		return body;
//	}
//	
//	public Protocol_Footer_ForString GetFooter() {
//		return foot;
//	}
	
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
