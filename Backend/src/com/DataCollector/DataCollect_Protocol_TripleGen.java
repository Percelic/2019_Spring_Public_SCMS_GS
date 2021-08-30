package com.DataCollector;

import com.Protocol.Protocol_Foot;
import com.Protocol.Protocol_Header;

/* ------------------------------------------------- */
/*				공동연구법인 - 태양광 모니터링 서버 개발 		 */
/*				데이터 수집서버 프로그램 개발 				 */
/*													 */
/*				삼상 프로토콜의  Body 부분 기술.			 */
/*													 */
/* ------------------------------------------------- */

public class DataCollect_Protocol_TripleGen {
		Protocol_Header header;
		Protocol_Body_TripleData body;
		Protocol_Foot foot;
		
		public DataCollect_Protocol_TripleGen() {
			// TODO Auto-generated constructor stub
			header = new Protocol_Header();
			body = new Protocol_Body_TripleData();
			foot = new Protocol_Foot();
		}
		
		public Protocol_Header GetHeader() {
			return header;
		}
		
		public Protocol_Body_TripleData GetBody() {
			return body;
		}
		
		public Protocol_Foot GetFooter() {
			return foot;
		}
}

class Protocol_Body_TripleData {
	
	// ( 5 bytes )  
	/* CMD			 	1Bytes */		private short CMD;
	/* Source		 	1Bytes */		private short Source;
	/* Type			 	1Bytes */		private short Type;
	/* MultiCode	 	1Bytes */		private short MultiCode;
	/* ErrCode		 	1Bytes */		private short ErrCode;
	
	// ( 38 Bytes )
	/* PV_Volt		 	2Bytes */		private int nPV_V;
	/* PV_Amp		 	2Bytes */		private int nPV_A;
	/* PV_Out		 	4Bytes */		private Long lPV_Out;
	/* SysR_Volt		2Bytes */		private int nSysR_V;
	/* SysS_Volt	 	2Bytes */		private int nSysS_V;
	/* SysT_Volt	 	2Bytes */		private int nSysT_V;
	/* SysR_Amp		 	2Bytes */		private int nSysR_A;
	/* SysS_Amp		 	2Bytes */		private int nSysS_A;
	/* SysT_Amp		 	2Bytes */		private int nSysT_A;
	
	/* CurrentGen	 	4Bytes */		private Long lCurrentGen;
	/* PowerFact	 	2Bytes */		private int nPowerFact;
	/* Hz			 	2Bytes */		private int nHz;
	/* Accu.Gen		 	8Bytes */		private Long lAccuGen;
	/* FaultCode	 	2Bytes */		private int nFaultCode;
	
	
	public Protocol_Body_TripleData() {
	}
	
	public Protocol_Body_TripleData(int nInverterID, int nGroupID, int nPV_V, int nPV_A, Long lPV_Out, int nSysR_V, int nSysS_V, int nSysT_V, int nSysR_A, int nSysS_A, int nSysT_A
								, Long lCurrentGen, int nPowerFact, int nHz, Long lAccuGen, int nFaultCode) {
	
		this.nPV_V = nPV_V;
		this.nPV_A = nPV_A;
		this.lPV_Out = lPV_Out;
		this.nSysR_V = nSysR_V;
		this.nSysS_V = nSysS_V;
		this.nSysT_V = nSysT_V;
		this.nSysR_A = nSysR_A;
		this.nSysS_A = nSysS_A;
		this.nSysT_A = nSysT_A;
		this.lCurrentGen = lCurrentGen;
		this.nPowerFact = nPowerFact;
		this.nHz = nHz;
		this.lAccuGen = lAccuGen;
		this.nFaultCode = nFaultCode;
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

	public String GetSQLString() {
		String _sql = ""; 
		
		//_sql = "insert into Inverter_Data";
		
		return _sql;
	}
	
	public int getPV_V() {
		return nPV_V;
	}

	public void setPV_V(int nPV_V) {
		this.nPV_V = nPV_V;
	}

	public int getPV_A() {
		return nPV_A;
	}

	public void setPV_A(int nPV_A) {
		this.nPV_A = nPV_A;
	}

	public long getPV_Out() {
		return lPV_Out;
	}

	public void setPV_Out(Long lPV_Out) {
		this.lPV_Out = lPV_Out;
	}

	public int getSysR_V() {
		return nSysR_V;
	}

	public void setSysR_V(int nSysR_V) {
		this.nSysR_V = nSysR_V;
	}

	public int getSysS_V() {
		return nSysS_V;
	}

	public void setSysS_V(int nSysS_V) {
		this.nSysS_V = nSysS_V;
	}

	public int getSysT_V() {
		return nSysT_V;
	}

	public void setSysT_V(int nSysT_V) {
		this.nSysT_V = nSysT_V;
	}

	public int getSysR_A() {
		return nSysR_A;
	}

	public void setSysR_A(int nSysR_A) {
		this.nSysR_A = nSysR_A;
	}

	public int getSysS_A() {
		return nSysS_A;
	}

	public void setSysS_A(int nSysS_A) {
		this.nSysS_A = nSysS_A;
	}

	public int getSysT_A() {
		return nSysT_A;
	}

	public void setSysT_A(int nSysT_A) {
		this.nSysT_A = nSysT_A;
	}

	public long getCurrentGen() {
		return lCurrentGen;
	}

	public void setCurrentGen(Long lCurrentGen) {
		this.lCurrentGen = lCurrentGen;
	}

	public int getPF() {
		return nPowerFact;
	}

	public void setPF(int nPowerFact) {
		this.nPowerFact = nPowerFact;
	}

	public int getHz() {
		return nHz;
	}

	public void setHz(int nHz) {
		this.nHz = nHz;
	}

	public Long getAccuGen() {
		return lAccuGen;
	}

	public void setAccuGen(Long lAccuGen) {
		this.lAccuGen = lAccuGen;
	}

	public int getFaultCode() {
		return nFaultCode;
	}

	public void setFaultCode(int nFaultCode) {
		this.nFaultCode = nFaultCode;
	}
}
