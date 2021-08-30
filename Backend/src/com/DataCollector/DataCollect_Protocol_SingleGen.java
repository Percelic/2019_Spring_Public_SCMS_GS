package com.DataCollector;

import com.Protocol.Protocol_Foot;
import com.Protocol.Protocol_Header;

/* ------------------------------------------------- */
/*				공동연구법인 - 태양광 모니터링 서버 개발 		 */
/*				데이터 수집서버 프로그램 개발 				 */
/*													 */
/*				단상 프로토콜의  Body 부분 기술.			 */
/*													 */
/* ------------------------------------------------- */

public class DataCollect_Protocol_SingleGen {
		private Protocol_Header header;
		private Protocol_Body_SingleData body;
		private Protocol_Foot foot;	
		
		public DataCollect_Protocol_SingleGen() {
			// TODO Auto-generated constructor stub
			header = new Protocol_Header();
			body = new Protocol_Body_SingleData();
			foot = new Protocol_Foot();
		}
		
		public Protocol_Header GetHeader() {
			return header;
		}
		
		public Protocol_Body_SingleData GetBody() {
			return body;
		}
		
		public Protocol_Foot GetFooter() {
			return foot;
		}
}

class Protocol_Body_SingleData {
	
	/* CMD			 	1Bytes */		private short CMD;
	/* Source		 	1Bytes */		private short Source;
	/* Type			 	1Bytes */		private short Type;
	/* MultiCode	 	1Bytes */		private short MultiCode;
	/* ErrCode		 	1Bytes */		private short ErrCode;
	
	/* PV_Volt		 	2Bytes */		private int PV_V;
	/* PV_Amp		 	2Bytes */		private int PV_A;
	/* PV_Out		 	2Bytes */		private int PV_Out;
	/* System_Volt	 	2Bytes */		private int Sys_V;
	/* System_Amp	 	2Bytes */		private int Sys_A;
	/* Current Gen. 	2Bytes */		private int CurrentGen;
	/* PowerFactor 		2Bytes */		private int PF;
	/* HZ		 		2Bytes */		private int HZ;
	/* Accumulated Gen.	8Bytes */		private Long AccuGen;
	/* FaultCode		2Bytes */		private int FaultCode;
	
	
	public Protocol_Body_SingleData() 
	{
		//AccuGen = BigInteger.valueOf(0xFFFFFFFF);
		
	}


	public int getPV_V() {
		return PV_V;
	}


	public void setPV_V(int pV_V) {
		PV_V = pV_V;
	}


	public int getPV_A() {
		return PV_A;
	}


	public void setPV_A(int pV_A) {
		PV_A = pV_A;
	}


	public int getPV_Out() {
		return PV_Out;
	}


	public void setPV_Out(int pV_Out) {
		PV_Out = pV_Out;
	}


	public int getSys_V() {
		return Sys_V;
	}


	public void setSys_V(int sys_V) {
		Sys_V = sys_V;
	}


	public int getSys_A() {
		return Sys_A;
	}


	public void setSys_A(int sys_A) {
		Sys_A = sys_A;
	}


	public int getCurrentGen() {
		return CurrentGen;
	}


	public void setCurrentGen(int currentGen) {
		CurrentGen = currentGen;
	}


	public int getPF() {
		return PF;
	}


	public void setPF(int pF) {
		PF = pF;
	}


	public int getHZ() {
		return HZ;
	}


	public void setHZ(int hZ) {
		HZ = hZ;
	}


	public Long getAccuGen() {
		return AccuGen;
	}


	public void setAccuGen(Long accuGen) {
		AccuGen = accuGen;
	}


	public int getFaultCode() {
		return FaultCode;
	}


	public void setFaultCode(int faultCode) {
		FaultCode = faultCode;
	}


	public short getCMD() {
		return CMD;
	}


	public void setCMD(short cMD) {
		CMD = cMD;
	}


	public short getSource() {
		return Source;
	}


	public void setSource(short source) {
		Source = source;
	}


	public short getType() {
		return Type;
	}


	public void setType(short type) {
		Type = type;
	}


	public short getMultiCode() {
		return MultiCode;
	}


	public void setMultiCode(short multiCode) {
		MultiCode = multiCode;
	}


	public short getErrCode() {
		return ErrCode;
	}


	public void setErrCode(short errCode) {
		ErrCode = errCode;
	}
	
	
	
}

/*
class Protocol_Inverter_TripleData {
	Protocol_Data_Header header;
	Protocol_InverterData_Body body;
	Protocol_Data_Foot foot;
	
	public Protocol_Inverter_TripleData() {
		header = new Protocol_Data_Header();
		
		switch(header.getCMD()) {
		case 'G':		// Generation
			body = new Protocol_InverterData_Body();
			foot = new Protocol_Data_Foot();
			break;
			
		case 'W':		// Weather
			break;
			
		case 'S':		// Sensor
			body = new Protocol_InverterData_Body();
			foot = new Protocol_Data_Foot();
			break;
			
			default:
				break;
		}
		
		
	}
	
	public Protocol_Inverter_TripleData(int nInverterID, int nGroupID, int nPV_V, int nPV_A, Long lPV_Out, int nSysR_V, int nSysS_V, int nSysT_V, int nSysR_A, int nSysS_A, int nSysT_A
			, Long lCurrentGen, int nPowerFact, int nHz, Long lAccuGen, int nFaultCode) {

		header = new Protocol_Data_Header();
		body = new Protocol_InverterData_Body();
		foot = new Protocol_Data_Foot();
		
		this.body.setnInverterID(nInverterID);
		this.body.setnGroupID(nGroupID);
		this.body.setnPV_V(nPV_V);
		this.body.setnPV_A(nPV_A);
		this.body.setlPV_Out(lPV_Out);
		this.body.setnSysR_V(nSysR_V);
		this.body.setnSysS_V(nSysS_V);
		this.body.setnSysT_V(nSysT_V);
		this.body.setnSysR_A(nSysR_A);
		this.body.setnSysS_A(nSysS_A);
		this.body.setnSysT_A(nSysT_A);
		this.body.setlCurrentGen(lCurrentGen);
		this.body.setnPowerFact(nPowerFact);
		this.body.setnHz(nHz);
		this.body.setlAccuGen(lAccuGen);
		this.body.setnFaultCode(nFaultCode);
	}
	
	public Protocol_Data_Header getHeader() {
		return this.header;
	}
	
	public Protocol_InverterData_Body getBody() {
		return this.body;
	}
	
	public Protocol_Data_Foot getFoot() {
		return this.foot;
	}
}
*/