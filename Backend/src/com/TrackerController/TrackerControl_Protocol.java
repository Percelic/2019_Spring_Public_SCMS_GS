package com.TrackerController;

public class TrackerControl_Protocol {
	
	private Object ProtocolData;
	
	public TrackerControl_Protocol(String _packet) 
	{	
		if(_packet != null) {
			
			String[] _szSplit = _packet.split(",");
			
			if(_szSplit.length >= 8) {
				try {					
					TrackerControl_Protocol_Control pcn = new TrackerControl_Protocol_Control();
					
					String[] strPacketSplit =_packet.split(",");		
					
					pcn.GetHeader().setSTX("$$SC");
					pcn.GetHeader().setCMD("c");
					try { pcn.GetHeader().setSI_ID(Integer.parseInt(strPacketSplit[0])); }  catch(NumberFormatException nfe) { pcn.GetHeader().setSI_ID(0); } 
					try { pcn.GetHeader().setTR_ID(Integer.parseInt(strPacketSplit[1])); }  catch(NumberFormatException nfe) { pcn.GetHeader().setTR_ID(0);	} 
					try { pcn.GetBody().setIsModeChange(strPacketSplit[2]);				 }  catch(Exception e)				 { pcn.GetBody().setIsModeChange(""); }
					try { pcn.GetBody().setMode(strPacketSplit[3]); 					 }	catch(Exception e)				 { pcn.GetBody().setMode(""); 	}
					try { pcn.GetBody().setIsUDShift(strPacketSplit[4]); 				 }  catch(Exception e)				 { pcn.GetBody().setIsUDShift("");  }
					try { pcn.GetBody().setUDShift(strPacketSplit[5]); 					 }	catch(Exception e) 				 { pcn.GetBody().setUDShift("");}
					try { pcn.GetBody().setIsLRShift(strPacketSplit[6]);				 } 	catch(Exception e)				 { pcn.GetBody().setIsLRShift("");	}
					try { pcn.GetBody().setLRShift(strPacketSplit[7]); 					 }	catch(Exception e)				 { pcn.GetBody().setLRShift("");}
					
					pcn.GetFooter().setCS(MakeCS(pcn.GetBody().getMode()));
					pcn.GetFooter().setETX("\r\n");
					
					
					ProtocolData = pcn;
				} catch(Exception e) {
					TrackerControlManager.logger.error("[ TrackerController ] TrackerControlProtocol Init. (CONTROL) error occured ! : " + e.toString());
				}
			}
		}
	}
	
	public short[] MakeArray(short... s) {
		
		short[] sa = {0,};

		
		sa = s;
//		for(int i = 0; i < a.length ; i++) {
//			sa[]
//		}
		
		return sa;
	}
	
	public synchronized void debug_PrintStatus() {
		
		switch(ProtocolData.getClass().getName()) {
			
		case "Protocol_Status":
			break;
		}
	}
	
	public Object GetData() {
		return ProtocolData;
	}
	
	public String MakeCS(String _szStr) {
		short _szResult = 0x00;
		
		for(short _s : _szStr.getBytes()) {
			_szResult ^= _s;
		}
		
		return String.format("%02X", _szResult);
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




