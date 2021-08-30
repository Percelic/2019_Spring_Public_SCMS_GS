package com.DataSimulator;

public class DataSimulator_Protocol {
	
	private Object ProtocolData;
	
	public DataSimulator_Protocol(Integer[] _packet) 
	{	
//		if(_packet != null) {
//			switch(_packet.length) {
//				case DataSimulatorManager.SIZE_OF_PROTOCOL_SINGLE_GENERATION:		// 53
//					try {
//						//ProtocolData = new Protocol_SingleGen();
//						 DataSimulator_Protocol_SingleGen psg = new DataSimulator_Protocol_SingleGen();					 
//						 
//						 // HEADER ( 12 bytes + 4 bytes )
//						 /* 4 */		psg.GetHeader().setSTX(MakeArray(_packet[0].shortValue(), _packet[1].shortValue(), _packet[2].shortValue(), _packet[3].shortValue()));
//						 /* 4 */		psg.GetHeader().setSI_ID(MakeArray(_packet[5].shortValue(), _packet[6].shortValue(), _packet[7].shortValue(), _packet[8].shortValue()));
//						 /* 3 */		psg.GetHeader().setTR_ID(MakeArray(_packet[10].shortValue(), _packet[11].shortValue(), _packet[12].shortValue()));
//						 /* 1 */		psg.GetHeader().setCMD(_packet[14].shortValue());
//						 
//						 // BODY ( 32 bytes, <5 + 26> ) 
//						 
//						 // BODY - SUBHEAD ( 5 Bytes  ) 
//						 
//						 /* 1 */		psg.GetBody().setCMD(_packet[16].shortValue());
//						 /* 1 */		psg.GetBody().setSource(_packet[17].shortValue());
//						 /* 1 */		psg.GetBody().setType(_packet[18].shortValue());
//						 /* 1 */		psg.GetBody().setMultiCode(_packet[19].shortValue());
//						 /* 1 */		psg.GetBody().setErrCode(_packet[20].shortValue());
//						 
//						 // BODY - DATA ( 26 Bytes )
//						 
//						 /* 2 */		psg.GetBody().setPV_V((_packet[21] << 8) + _packet[22]);
//						 /* 2 */		psg.GetBody().setPV_A((_packet[23] << 8) + _packet[24]);
//						 /* 2 */		psg.GetBody().setPV_Out((_packet[25] << 8) + _packet[26]);
//						 /* 2 */		psg.GetBody().setSys_V((_packet[27] << 8) + _packet[28]);
//						 /* 2 */		psg.GetBody().setSys_A((_packet[29] << 8) + _packet[30]);
//						 /* 2 */		psg.GetBody().setCurrentGen((_packet[31] << 8) + _packet[32]);
//						 /* 2 */		psg.GetBody().setPF((_packet[33] << 8) + _packet[34]);
//						 /* 2 */		psg.GetBody().setHZ((_packet[35] << 8) + _packet[36]);
//						 /* 8 */		psg.GetBody().setAccuGen(Long.parseUnsignedLong(String.format("%02X%02X%02X%02X%02X%02X%02X%02X",_packet[37],_packet[38],_packet[39],_packet[40],_packet[41],_packet[42],_packet[43],_packet[44]),16));
//						 /* 2 */		psg.GetBody().setFaultCode((_packet[45] << 8) + _packet[46]);
//						 
//						 
//						 // FOOTER ( 4 bytes + 1 bytes)
//						 /* 2 */		psg.GetFooter().setCS(MakeArray(_packet[48].shortValue(), _packet[49].shortValue()));
//						 /* 2 */		psg.GetFooter().setETX(MakeArray(_packet[50].shortValue(), _packet[51].shortValue())); 
//						 
//						 ProtocolData = psg;
//						 
//					} catch(Exception e) {
//						//DataCollectManager.logger.error(" DataCollectProtocol Init. (SINGLE) error occured !  :  " + e.toString());
//					}
//					break;
//				
//				case DataSimulatorManager.SIZE_OF_PROTOCOL_TRIPLE_GENERATION:		// 65
//					try {
//						DataSimulator_Protocol_TripleGen psg = new DataSimulator_Protocol_TripleGen();
//						
//						// HEADER ( 12 bytes + 4 bytes )
//						 /* 4 */		psg.GetHeader().setSTX(MakeArray(_packet[0].shortValue(), _packet[1].shortValue(), _packet[2].shortValue(), _packet[3].shortValue()));
//						 /* 4 */		psg.GetHeader().setSI_ID(MakeArray(_packet[5].shortValue(), _packet[6].shortValue(), _packet[7].shortValue(), _packet[8].shortValue()));
//						 /* 3 */		psg.GetHeader().setTR_ID(MakeArray(_packet[10].shortValue(), _packet[11].shortValue(), _packet[12].shortValue()));
//						 /* 1 */		psg.GetHeader().setCMD(_packet[14].shortValue());
//						 
//						 
//						 // BODY ( 32 bytes, <5 + 26> + 1 bytes ) 
//						 
//						 // BODY - SUBHEAD ( 5 Bytes + 1 bytes ) 
//						 
//						 /* 1 */		psg.GetBody().setCMD(_packet[16].shortValue());
//						 /* 1 */		psg.GetBody().setSource(_packet[17].shortValue());
//						 /* 1 */		psg.GetBody().setType(_packet[18].shortValue());
//						 /* 1 */		psg.GetBody().setMultiCode(_packet[19].shortValue());
//						 /* 1 */		psg.GetBody().setErrCode(_packet[20].shortValue());
//						 
//						 // BODY - DATA ( 26 Bytes )
//						 
//						 /* 2 */		psg.GetBody().setPV_V((_packet[21] << 8) + _packet[22]);
//						 /* 2 */		psg.GetBody().setPV_A((_packet[23] << 8) + _packet[24]);
//						 /* 4 */		psg.GetBody().setPV_Out(Long.parseLong(String.format("%02X%02X%02X%02X",(_packet[25]),(_packet[26]),(_packet[27]), _packet[28])));
//						 
//						 /* 2 */		psg.GetBody().setSysR_V((_packet[29] << 8) + _packet[30]);
//						 /* 2 */		psg.GetBody().setSysS_V((_packet[31] << 8) + _packet[32]);
//						 /* 2 */		psg.GetBody().setSysT_V((_packet[33] << 8) + _packet[34]);
//						 /* 2 */		psg.GetBody().setSysR_A((_packet[35] << 8) + _packet[36]);
//						 /* 2 */		psg.GetBody().setSysS_A((_packet[37] << 8) + _packet[38]);
//						 /* 2 */		psg.GetBody().setSysT_A((_packet[39] << 8) + _packet[40]);
//						 
//						 /* 4 */		psg.GetBody().setCurrentGen(Long.parseLong(String.format("%02X%02X%02X%02X",(_packet[41]), (_packet[42]), (_packet[43]), (_packet[44]))));
//						 /* 2 */		psg.GetBody().setPF((_packet[45] << 8) + _packet[46]);
//						 /* 2 */		psg.GetBody().setHz((_packet[47] << 8) + _packet[48]);
//						 /* 8 */		psg.GetBody().setAccuGen(Long.parseUnsignedLong(String.format("%02X%02X%02X%02X%02X%02X%02X%02X",(_packet[49]),(_packet[50]),(_packet[51]),(_packet[52]),(_packet[53]),(_packet[54]),(_packet[55]),_packet[56]),16));
//						 /* 2 */		psg.GetBody().setFaultCode((_packet[57] << 8) + _packet[58]);
//						 
//						 
//						 // FOOTER ( 4 bytes + 1 bytes )
//						 /* 2 */		psg.GetFooter().setCS(MakeArray(_packet[60].shortValue(), _packet[61].shortValue()));
//						 /* 2 */		psg.GetFooter().setETX(MakeArray(_packet[62].shortValue(), _packet[63].shortValue())); 
//						
//						ProtocolData = psg;
//						
//					} catch(Exception e) {
//						//DataCollectManager.logger.error(" DataCollectProtocol Init. (TRIPLE) error occured !  :  " + e.toString());
//					}
//					break;
//					
//				case DataSimulatorManager.SIZE_OF_PROTOCOL_SENSOR:				// 51
//					try {
//						DataSimulator_Protocol_Sensor psd = new DataSimulator_Protocol_Sensor();
//						
//						String[] strPacketSplit = DataCollectUtil.ConvertString(_packet).split(",");
//					
//						psd.GetHeader().setSTX(strPacketSplit[0]);
//						psd.GetHeader().setCMD(strPacketSplit[1]);
//						
//						try { psd.GetHeader().setSI_ID(Integer.parseInt(strPacketSplit[2])); 		} catch(NumberFormatException nfe) 	{ psd.GetHeader().setSI_ID(0); 			 }
//						try { psd.GetHeader().setTR_ID(Integer.parseInt(strPacketSplit[3])); 		} catch(NumberFormatException nfe) 	{ psd.GetHeader().setTR_ID(0);			 }
//						try { psd.GetBody().setfAmbientTemp(Float.parseFloat(strPacketSplit[4])); 	} catch(NumberFormatException nfe) 	{ psd.GetBody().setfAmbientTemp(0.0f); 	 }
//						try { psd.GetBody().setfModuleTemp(Float.parseFloat(strPacketSplit[5])); 	} catch(NumberFormatException nfe) 	{ psd.GetBody().setfModuleTemp(0.0f); 	 }
//						try { psd.GetBody().setfInnerRltHumid(Float.parseFloat(strPacketSplit[6])); } catch(NumberFormatException nfe) 	{ psd.GetBody().setfInnerRltHumid(0.0f); } 
//						try { psd.GetBody().setnHorizontalIDT(Integer.parseInt(strPacketSplit[7])); } catch(NumberFormatException nfe)	{ psd.GetBody().setnHorizontalIDT(0); 	 }
//						try { psd.GetBody().setnSlopeIDT(Integer.parseInt(strPacketSplit[8]));		} catch(NumberFormatException nfe)	{ psd.GetBody().setnSlopeIDT(0); 		 }
//						try { psd.GetBody().setfWindSpeed(Float.parseFloat(strPacketSplit[9]));		} catch(NumberFormatException nfe)	{ psd.GetBody().setfWindSpeed(0);		 }
//						
//						psd.GetFooter().setCS(strPacketSplit[10].substring(0,4));
//						psd.GetFooter().setETX(strPacketSplit[10].substring(4));
//						
//						ProtocolData = psd;
//					} catch(Exception e) {
//						//DataCollectManager.logger.error(" DataCollectProtocol Init. (SENSOR) error occured !  :  " + e.toString());
//					}
//					break;
//					
//				case DataSimulatorManager.SIZE_OF_PROTOCOL_SHADOW:				// 
//					try {
//						DataSimulator_Protocol_Shadow psd = new DataSimulator_Protocol_Shadow();
//						
//						String[] strPacketSplit = DataCollectUtil.ConvertString(_packet).split(",");
//					
//						psd.GetHeader().setSTX(strPacketSplit[0]);
//						
//						try { psd.GetHeader().setSI_ID(Integer.parseInt(strPacketSplit[1])); 		} catch(NumberFormatException nfe) 	{ psd.GetHeader().setSI_ID(0); 			 }
//						try { psd.GetHeader().setTR_ID(Integer.parseInt(strPacketSplit[2])); 		} catch(NumberFormatException nfe) 	{ psd.GetHeader().setTR_ID(0);			 }
//						psd.GetHeader().setCMD(strPacketSplit[3]);
//						
//						try { psd.GetBody().setnWCDS1(Integer.parseInt(strPacketSplit[4])); 	} catch(NumberFormatException nfe) 	{ psd.GetBody().setnWCDS1(0); 	 }
//						try { psd.GetBody().setnWCDS2(Integer.parseInt(strPacketSplit[5])); 	} catch(NumberFormatException nfe) 	{ psd.GetBody().setnWCDS2(0); 	 }
//						try { psd.GetBody().setnWCDS3(Integer.parseInt(strPacketSplit[6])); } catch(NumberFormatException nfe) 		{ psd.GetBody().setnWCDS3(0);	 } 
//						try { psd.GetBody().setnECDS1(Integer.parseInt(strPacketSplit[7])); } catch(NumberFormatException nfe)		{ psd.GetBody().setnECDS1(0);	 }
//						try { psd.GetBody().setnECDS2(Integer.parseInt(strPacketSplit[8]));		} catch(NumberFormatException nfe)	{ psd.GetBody().setnECDS2(0); 	 }
//						try { psd.GetBody().setnECDS3(Integer.parseInt(strPacketSplit[9]));		} catch(NumberFormatException nfe)	{ psd.GetBody().setnECDS3(0);	 }
//						
//						psd.GetFooter().setCS(strPacketSplit[10].substring(0,4));
//						psd.GetFooter().setETX(strPacketSplit[10].substring(4));
//						
//						ProtocolData = psd;
//					} catch(Exception e) {
//						//DataCollectManager.logger.error(" DataCollectProtocol Init. (SENSOR) error occured !  :  " + e.toString());
//					}
//					break;
//					
//				default:
//					break;
//			}
//		}
	}
	
	public short[] MakeArray(short... s) {
		
		short[] sa = {0,};
		
		sa = s;
		
		return sa;
	}
	
	public synchronized void debug_PrintStatus() {
		
		switch(ProtocolData.getClass().getName()) {
		
		case "Protocol_SingleGen":
		
			System.out.println();
			System.out.println("===========================================");
			System.out.println("* Header");
			System.out.format("STX	 \t: ");
			
			for(short s : ((DataSimulator_Protocol_SingleGen)ProtocolData).GetHeader().getSTX()) 	{	System.out.format("%c ", s); }
			System.out.println();
			
			System.out.format("CMD	 \t: %c%n", ((DataSimulator_Protocol_SingleGen)ProtocolData).GetHeader().getCMD());
			System.out.format("SI_ID \t\t: ");
			for(short s : ((DataSimulator_Protocol_SingleGen)ProtocolData).GetHeader().getSI_ID()) {	System.out.format("%c ", s); }
			System.out.println();
			
			System.out.format("TR_ID \t\t: ");
			for(short s : ((DataSimulator_Protocol_SingleGen)ProtocolData).GetHeader().getTR_ID()) {	System.out.format("%c ", s); }
			System.out.println();
			
			DataSimulator_Protocol_SingleGen _psg = (DataSimulator_Protocol_SingleGen)ProtocolData;
			
			System.out.println("===========================================");
			System.out.println("* Body");
			System.out.format("CMD\t\t: %s%n", _psg.GetBody().getCMD());
			System.out.format("Source \t\t: %s%n", _psg.GetBody().getSource());
			System.out.format("Type \t\t: %s%n", _psg.GetBody().getType());
			System.out.format("multiCode \t: %s%n", _psg.GetBody().getMultiCode());
			System.out.format("ErrCode \t: %s%n", _psg.GetBody().getErrCode());
			System.out.println();
			
			System.out.println("* Body::Data");
			System.out.format("PV_V \t : %d%n", _psg.GetBody().getPV_V());
			System.out.format("PV_A \t : %d%n", _psg.GetBody().getPV_A());
			System.out.format("PV_Out \t : %d%n", _psg.GetBody().getPV_Out());
			System.out.format("Sys_V \t : %d%n", _psg.GetBody().getSys_V());
			System.out.format("Sys_A \t : %d%n", _psg.GetBody().getSys_A());
			System.out.format("CurrGen \t : %d%n", _psg.GetBody().getCurrentGen());
			System.out.format("PF \t : %d%n", _psg.GetBody().getPF());
			System.out.format("HZ \t : %d%n", _psg.GetBody().getHZ());
			System.out.format("AccuGen \t : %d%n", _psg.GetBody().getAccuGen());
			System.out.format("FaultCode \t : %d%n", _psg.GetBody().getFaultCode());
			System.out.println();
			
			System.out.println("===========================================");
			System.out.println("* Footer");
			System.out.format("CS \t : ");
			for(short s : _psg.GetFooter().getCS()) {	System.out.format("%s ", s); }
			System.out.println();
			System.out.format("ETX \t : ");
			for(short s : _psg.GetFooter().getETX()) {	System.out.format("%s ", s); }
			System.out.println();
			System.out.println();
			
			System.out.println("===========================================");
	
		break;
		
		case "Protocol_TripleGen":
			System.out.println();
			System.out.println("===========================================");
			System.out.println("* Header");
			System.out.format("STX	 \t: ");
			
			for(short s : ((DataSimulator_Protocol_TripleGen)ProtocolData).GetHeader().getSTX()) 	{	System.out.format("%c ", s); }
			System.out.println();
			
			System.out.format("CMD	 \t: %c%n", ((DataSimulator_Protocol_TripleGen)ProtocolData).GetHeader().getCMD());
			System.out.format("SI_ID \t\t: ");
			for(short s : ((DataSimulator_Protocol_TripleGen)ProtocolData).GetHeader().getSI_ID()) {	System.out.format("%c ", s); }
			System.out.println();
			
			System.out.format("TR_ID \t\t: ");
			for(short s : ((DataSimulator_Protocol_TripleGen)ProtocolData).GetHeader().getTR_ID()) {	System.out.format("%c ", s); }
			System.out.println();
			
			DataSimulator_Protocol_TripleGen _psg_tri = (DataSimulator_Protocol_TripleGen)ProtocolData;
			
			System.out.println("===========================================");
			System.out.println("* Body");
			System.out.format("CMD\t\t: %s%n", _psg_tri.GetBody().getCMD());
			System.out.format("Source \t\t: %s%n", _psg_tri.GetBody().getSource());
			System.out.format("Type \t\t: %s%n", _psg_tri.GetBody().getType());
			System.out.format("multiCode \t: %s%n", _psg_tri.GetBody().getMultiCode());
			System.out.format("ErrCode \t: %s%n", _psg_tri.GetBody().getErrCode());
			System.out.println();
			
			System.out.println("* Body::Data");
			System.out.format("PV_V \t : %d%n", _psg_tri.GetBody().getPV_V());
			System.out.format("PV_A \t : %d%n", _psg_tri.GetBody().getPV_A());
			System.out.format("PV_Out \t : %d%n", _psg_tri.GetBody().getPV_Out());
			System.out.format("SysR_V \t : %d%n", _psg_tri.GetBody().getSysR_V());
			System.out.format("SysS_V \t : %d%n", _psg_tri.GetBody().getSysS_V());
			System.out.format("SysT_V \t : %d%n", _psg_tri.GetBody().getSysT_V());
			System.out.format("SysR_A \t : %d%n", _psg_tri.GetBody().getSysR_A());
			System.out.format("SysS_A \t : %d%n", _psg_tri.GetBody().getSysS_A());
			System.out.format("SysT_A \t : %d%n", _psg_tri.GetBody().getSysT_A());
			System.out.format("CurrGen \t : %d%n", _psg_tri.GetBody().getCurrentGen());
			System.out.format("PF \t : %d%n", _psg_tri.GetBody().getPF());
			System.out.format("HZ \t : %d%n", _psg_tri.GetBody().getHz());
			System.out.format("AccuGen \t : %d 0x%02X %n", _psg_tri.GetBody().getAccuGen(), _psg_tri.GetBody().getAccuGen());
			System.out.format("FaultCode \t : %d%n", _psg_tri.GetBody().getFaultCode());
			System.out.println();
			
			System.out.println("===========================================");
			System.out.println("* Footer");
			System.out.format("CS \t : ");
			for(short s : _psg_tri.GetFooter().getCS()) {	System.out.format("%s ", s); }
			System.out.println();
			System.out.format("ETX \t : ");
			for(short s : _psg_tri.GetFooter().getETX()) {	System.out.format("%s ", s); }
			System.out.println();
			System.out.println();
			
			System.out.println("===========================================");
			
			
			break;
			
		case "Protocol_Sensor":
			break;
			
		case "Protocol_Status":
			break;
		}
	}
	
	public Object GetData() {
		return ProtocolData;
	}
}





class Protocol_Data {
	
}

class Protocol_Header {
	/* STX  		4 Bytes */ private short[] 	STX;
	/* CMD  		1 Byte  */ private short 	CMD;
	/* SITE_ID  	4 Bytes */ private short[] 	SI_ID;
	/* TRACKER_ID   3 Bytes */ private short[] 	TR_ID;
	
	public Protocol_Header() {
		STX = new short[4];
		CMD = 0x00;
		SI_ID = new short[4];
		TR_ID = new short[3];
	}
	
	public short[] getSTX() {
		return STX;
	}
	
	public void setSTX(short[] STX) {
		this.STX = STX;
	}
	
	public short getCMD() {
		return CMD;
	}
	
	public void setCMD(short CMD) {
		this.CMD = CMD;
	}
	
	public short[] getSI_ID() {
		return SI_ID;
	}
	
	public void setSI_ID(short[] SI_ID) {
		this.SI_ID = SI_ID;
	}
	
	public short[] getTR_ID() {
		return TR_ID;
	}
	
	public void setTR_ID(short[] TR_ID) {
		this.TR_ID = TR_ID;
	}
}

class Protocol_Header_ForString {
	private String STX;
	private String CMD;
	private int SI_ID;
	private int TR_ID;
	
	public Protocol_Header_ForString() {
		STX = "";
		CMD = "";
		SI_ID = 0;
		TR_ID = 0;
	}

	public String getSTX() {
		return STX;
	}

	public void setSTX(String sTX) {
		STX = sTX;
	}

	public String getCMD() {
		return CMD;
	}

	public void setCMD(String cMD) {
		CMD = cMD;
	}

	public int getSI_ID() {
		return SI_ID;
	}

	public void setSI_ID(int sI_ID) {
		SI_ID = sI_ID;
	}

	public int getTR_ID() {
		return TR_ID;
	}

	public void setTR_ID(int tR_ID) {
		TR_ID = tR_ID;
	}	
}

class Protocol_Footer {
	private short[] CS;
	private short[] ETX;
	
	public Protocol_Footer() {
		CS = new short[2];
		ETX = new short[2];
	}

	public short[] getCS() {
		return CS;
	}

	public void setCS(short[] cS) {
		CS = cS;
	}

	public short[] getETX() {
		return ETX;
	}

	public void setETX(short[] eTX) {
		ETX = eTX;
	}
}

class Protocol_Footer_ForString {
	private String CS;
	private String ETX;
	
	public Protocol_Footer_ForString() {
		CS = "";
		ETX = "";
	}

	public String getCS() {
		return CS;
	}

	public void setCS(String cS) {
		CS = cS;
	}

	public String getETX() {
		return ETX;
	}

	public void setETX(String eTX) {
		ETX = eTX;
	}
	
	
}




