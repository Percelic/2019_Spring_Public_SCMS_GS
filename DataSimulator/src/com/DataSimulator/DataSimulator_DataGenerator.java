package com.DataSimulator;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class DataSimulator_DataGenerator extends Thread {

	final int MINUTE_MILLISECOND = 60000;
	int nTimer = 1;
	int nDelay = 900000;
	int nGroupID = 0;
	int nMode = 1;
	Map<Integer, DataSimulator_TrackerData> hshTrackerList = null;

	// 96 generation values for every 15 minutes
	int[] nGenerateList = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 3, 2, 4,
			6, 4, 5, 6, 7, 6, 8, 6, 7, 8, 6, 6, 7, 8, 9, 10, 8,

			// PM 1 ~
			9, 7, 6, 9, 7, 8, 8, 8, 7, 7, 8, 6, 9, 8, 7, 8, 9, 8, 8, 7, 6, 5, 4, 3, 3, 2, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public DataSimulator_DataGenerator(int _nMode, int _nDelayMinute, int _nGroupID,
			Map<Integer, DataSimulator_TrackerData> _hsh) {
		// TODO Auto-generated constructor stub
		nDelay = (_nDelayMinute * 60 * 1000);
		nGroupID = _nGroupID;
		hshTrackerList = _hsh;
		nMode = _nMode;

		for (int i = 0; i < nGenerateList.length; i++) {
			nGenerateList[i] *= 1000;
		}
	}

	private boolean DataSimulator_SetInterval(int _nTimer, int _nPeriod) {
		// _nTimer = _nTimer % ((Integer.MAX_VALUE/1000)*1000);

		return _nTimer % _nPeriod == 0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// super.run();

		switch (nMode) {

			case 1:
				int _nStatusCount = 0;

				while (true) {
					try {
						sleep(nDelay);

						// 상태 데이터 전송
						for (int _nKey : hshTrackerList.keySet()) {

							Socket _TRSocket = hshTrackerList.get(_nKey).GetSocketConnector().GetSocket();
							OutputStream _TROutputStream = hshTrackerList.get(_nKey).GetSocketConnector().GetSocket()
									.getOutputStream();

							if (_TRSocket.isConnected()) {
								_TROutputStream.write(new DataSimulator_Protocol_Status()
										.GetGenerate(nGroupID, _nKey, _nStatusCount).getBytes());
							}
						}

						// 발전, 센서, 음영 데이터 전송
						Calendar cal = Calendar.getInstance();
						Integer _nSeries = (cal.get(Calendar.HOUR_OF_DAY) * 4) + (cal.get(Calendar.MINUTE) / 15);

						for (int _nKey : hshTrackerList.keySet()) {
							Long _lCurrent = ((nGenerateList[_nSeries] / 10) == 0L) ? (long) (nGenerateList[_nSeries])
									: (long) (nGenerateList[_nSeries])
											+ ThreadLocalRandom.current().nextInt(1, nGenerateList[_nSeries] / 10);
							_lCurrent *= ((long)hshTrackerList.get(_nKey).GetCapacityMultiply());
							_lCurrent /= 30;
							hshTrackerList.get(_nKey).SetAccuGen(hshTrackerList.get(_nKey).GetAccuGen() + _lCurrent);

							if (hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().isConnected()) {
								if (hshTrackerList.get(_nKey).GetPhase())
									hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream()
											.write(new DataSimulator_Protocol_SingleGen().GetGenerate(nGroupID, _nKey,
													_lCurrent.intValue(), hshTrackerList.get(_nKey).GetAccuGen(),
													hshTrackerList.get(_nKey).GetAlarmCode()));
								else
									hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream()
											.write(new DataSimulator_Protocol_TripleGen().GetGenerate(nGroupID, _nKey,
													_lCurrent, hshTrackerList.get(_nKey).GetAccuGen(),
													hshTrackerList.get(_nKey).GetAlarmCode()));
								// sleep(50);

								hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream().write(
										new DataSimulator_Protocol_Sensor().GetGenerate(nGroupID, _nKey).getBytes());
								// sleep(50);

								if (hshTrackerList.get(_nKey).GetShadowADC())
									hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream()
											.write(new DataSimulator_Protocol_Shadow_ADC().GetGenerate(nGroupID, _nKey)
													.getBytes());
								else
									hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream()
											.write(new DataSimulator_Protocol_Shadow().GetGenerate(nGroupID, _nKey)
													.getBytes());
							}
						}

						if (isInterrupted()) {
							DataSimulatorManager.logger.info("DataGenerator thread working has stopped");
							break;
						}

					} catch (Exception e) {
						DataSimulatorManager.logger.error("DataGenerator thread working has failed..");
						e.printStackTrace();
						break;
					}
				}

				break;

			case 2:

				while (true) {
					try {
						sleep(nDelay);

						for (int _nKey : hshTrackerList.keySet()) {
							if (hshTrackerList.get(_nKey).GetPhase())
								hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream().write(
										new DataSimulator_Protocol_SingleGen().GetRandomGenerate(nGroupID, _nKey));
							else
								hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream().write(
										new DataSimulator_Protocol_TripleGen().GetRandomGenerate(nGroupID, _nKey));
							sleep(50);
							hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream().write(
									new DataSimulator_Protocol_Sensor().GetRandomGenerate(nGroupID, _nKey).getBytes());
							sleep(50);

							if (hshTrackerList.get(_nKey).GetShadowADC())
								hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream()
										.write(new DataSimulator_Protocol_Shadow_ADC()
												.GetRandomGenerate(nGroupID, _nKey).getBytes());
							else
								hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream()
										.write(new DataSimulator_Protocol_Shadow().GetRandomGenerate(nGroupID, _nKey)
												.getBytes());
						}

						nTimer++;

						if (isInterrupted()) {
							DataSimulatorManager.logger.info("DataGenerator thread working has stopped");
							break;
						}

					} catch (Exception e) {
						DataSimulatorManager.logger.error("DataGenerator thread working has failed..");
						e.printStackTrace();
						break;
					}
				}
				break;

			case 3: // Serial mode --> ��� �����͸� 1���� ������� ���� .. ������ ���� üũ
				Integer _nSerialCount = 0;

				while (true) {
					try {
						sleep(nDelay);

						_nSerialCount++;

						for (int _nKey : hshTrackerList.keySet()) {

							if (hshTrackerList.get(_nKey).GetPhase())
								hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream()
										.write(new DataSimulator_Protocol_SingleGen().GetGenerateSerial(nGroupID, _nKey,
												_nSerialCount));
							else
								hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream()
										.write(new DataSimulator_Protocol_TripleGen().GetGenerateSerial(nGroupID, _nKey,
												_nSerialCount));
							sleep(50);

							hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream()
									.write(new DataSimulator_Protocol_Sensor()
											.GetGenerateSerial(nGroupID, _nKey, _nSerialCount).getBytes());
							sleep(50);

							if (hshTrackerList.get(_nKey).GetShadowADC())
								hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream()
										.write(new DataSimulator_Protocol_Shadow_ADC()
												.GetRandomGenerate(nGroupID, _nKey).getBytes());
							else
								hshTrackerList.get(_nKey).GetSocketConnector().GetSocket().getOutputStream()
										.write(new DataSimulator_Protocol_Shadow().GetRandomGenerate(nGroupID, _nKey)
												.getBytes());
						}

						nTimer++;

						if (isInterrupted()) {
							DataSimulatorManager.logger.info("DataGenerator thread working has stopped");
							break;
						}

					} catch (Exception e) {
						DataSimulatorManager.logger.error("DataGenerator thread working has failed..");
						e.printStackTrace();
						break;
					}
				}

				break;

		}

	}

}
