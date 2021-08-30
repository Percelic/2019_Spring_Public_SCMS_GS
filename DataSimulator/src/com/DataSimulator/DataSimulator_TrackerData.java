package com.DataSimulator;

import java.util.Random;

public class DataSimulator_TrackerData {
	private DataSimulator_SocketConnector socketConnector;
	private DataSimulator_MessageData msgData;
	private Boolean bIsSinglePhase = true;
	private Boolean bIsShadowADC = false;
	private Long lAccuGen = 0L;
	private Integer nAlarmCode = 0;
	private Integer nCapacityMultiply = 0;

	public DataSimulator_TrackerData(String _szServerIP, int _nServerPort, int _nLocalPort) {
		// TODO Auto-generated constructor stub
		socketConnector = new DataSimulator_SocketConnector(_szServerIP, _nServerPort, _nLocalPort);
		msgData = new DataSimulator_MessageData();
		bIsSinglePhase = new Random().nextBoolean();
	}

	public DataSimulator_TrackerData(String _szServerIP, int _nServerPort, int _nLocalPort, int _nPhaseMode,
			int _nShadowADC, int _nCapacityMultiply) {
		socketConnector = new DataSimulator_SocketConnector(_szServerIP, _nServerPort, _nLocalPort);
		msgData = new DataSimulator_MessageData();
		bIsSinglePhase = _nPhaseMode == 0 ? new Random().nextBoolean()
				: _nPhaseMode == 1 ? true : _nPhaseMode == 2 ? false : new Random().nextBoolean();

		bIsShadowADC = _nShadowADC == 2 ? true : _nShadowADC == 1 ? false : true;

		nCapacityMultiply = _nCapacityMultiply;
	}

	public DataSimulator_SocketConnector GetSocketConnector() {
		return socketConnector;
	}

	public DataSimulator_MessageData GetMessageData() {
		return msgData;
	}

	public Boolean GetPhase() {
		return bIsSinglePhase;
	}

	public Boolean GetShadowADC() {
		return bIsShadowADC;
	}

	public Long GetAccuGen() {
		return lAccuGen;
	}

	public void SetAccuGen(Long _lAccuGen) {
		lAccuGen = _lAccuGen;
	}

	public Integer GetAlarmCode() {
		return nAlarmCode;
	}

	public void SetAlarmCode(Integer _nAlarmCode) {
		nAlarmCode = _nAlarmCode;
	}

	public Integer GetCapacityMultiply() {
		return nCapacityMultiply;
	}

	public void SetCapacityMultiply(Integer _nCapacityMultiply) {
		nCapacityMultiply = _nCapacityMultiply;
	}
}
