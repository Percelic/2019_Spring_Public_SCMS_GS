package com.Protocol;

public class Protocol_Foot {
	private short[] CS;
	private short[] ETX;
	
	public Protocol_Foot() {
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
