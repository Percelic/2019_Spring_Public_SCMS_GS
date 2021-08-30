package com.ControlAlgorithm;

/* ------------------------------------------------- */
/*				������������ - �¾籤 ����͸� ���� ���� 		 */
/*				������ �������� ���α׷� ���� 				 */
/*													 */
/*						19-05-03					 */
/*				�������� ����������  Body �κ� ���.			 */
/*													 */
/* ------------------------------------------------- */

public class CA_Protocol_Shadow {
	
	Protocol_Header_ForString header;
	Protocol_Body_ShadowData body;
	Protocol_Footer_ForString foot;

	public CA_Protocol_Shadow() {
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
