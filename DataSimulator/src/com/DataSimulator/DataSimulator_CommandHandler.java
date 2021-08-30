package com.DataSimulator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class DataSimulator_CommandHandler extends Thread {

	private HashMap<String, String> hshConfig = new HashMap<String, String>();
	private Map<Integer, DataSimulator_TrackerData> hshTrackerSet = new HashMap<Integer, DataSimulator_TrackerData>();
	private CustomQueue<DataSimulator_MessageData> messageQueue = new CustomQueue<DataSimulator_MessageData>(
			DataSimulator_MessageData.class, 64);
	private DataSimulator_DataGenerator dataGenerator;

	private Scanner scanner = new Scanner(System.in);

	public DataSimulator_CommandHandler() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unused")
	private void ChoiceSelection(String _szMsg, String _szKey) {

		String[] _szSplit = { "", };
		int _nParsed = 0;
		float _fParsed = 0;
		boolean _bCondition = false;

		do {

			try {
				_szSplit = null;
				_nParsed = 0;
				_bCondition = false;

				System.out.print(_szMsg);

				if (scanner.hasNext()) {

					// scanner ���ۿ� �ܷ��ϴ� ���� ����
					scanner.skip("[\\r\\n]+");

					hshConfig.put(_szKey, scanner.next());
				}

				_szSplit = hshConfig.get(_szKey).trim().split(",");

				try {
					switch (_szKey) {
						case "Destination":
							try {
								_nParsed = Integer.parseInt(_szSplit[1]);

								_bCondition = (_szSplit.length == 2 && _szSplit[0].split("\\.").length == 4
										&& Integer.parseInt(_szSplit[1]) >= 0 && Integer.parseInt(_szSplit[1]) < 65536);

								for (String _s : _szSplit[0].split("\\.")) {
									Integer _n = Integer.parseInt(_s);
									_bCondition &= (_n >= 0 && _n < 256);
								}
							} catch (Exception e) {
								continue;
							}

							break;

						case "SysMode":
							_nParsed = Integer.parseInt(hshConfig.get(_szKey));

							_bCondition = (_szSplit[0].equals("1") || _szSplit[0].equals("2"));
							break;

						case "TStartNumber":
							_nParsed = Integer.parseInt(hshConfig.get(_szKey));

							_bCondition = (Integer.parseInt(_szSplit[0]) >= 1 && Integer.parseInt(_szSplit[0]) <= 254);
							break;

						case "QOT":
							_nParsed = Integer.parseInt(hshConfig.get(_szKey));

							_bCondition = (Integer.parseInt(_szSplit[0]) >= 1 && Integer.parseInt(_szSplit[0]) <= 254);
							break;

						case "Period":
							_nParsed = Integer.parseInt(hshConfig.get(_szKey));

							_bCondition = (Integer.parseInt(_szSplit[0]) > 0);
							break;

						case "GroupID":
							_nParsed = Integer.parseInt(hshConfig.get(_szKey));

							_bCondition = (Integer.parseInt(_szSplit[0]) >= 0);
							break;

						case "TMode":
							_nParsed = Integer.parseInt(hshConfig.get(_szKey));

							_bCondition = (_szSplit[0].equals("1") || _szSplit[0].equals("2")
									|| _szSplit[0].equals("3"));
							break;

						case "PMode":
							_bCondition = (_szSplit[0].equals("0") || _szSplit[0].equals("1")
									|| _szSplit[0].equals("2"));
							break;

						case "SMode":
							_bCondition = (_szSplit[0].equals("1") || _szSplit[0].equals("2"));
							break;
							
						case "OutputMultiply":
							try {
							_nParsed = Integer.parseInt(hshConfig.get(_szKey));
							} catch(NumberFormatException nfe) {
								_nParsed = 0;
							}
							_bCondition = (_nParsed > 0) && (_nParsed <= 30);
							break;

						case "Alarmsender":
							try {
								_bCondition = (0 == Integer.parseInt(_szSplit[0]) || ((Integer
										.parseInt(_szSplit[0]) <= (int) (hshTrackerSet.keySet().toArray()[0])
												+ hshTrackerSet.size()
										&& (int) (hshTrackerSet.keySet().toArray()[0]) <= Integer
												.parseInt(_szSplit[0]))));
							} catch (Exception e) {
								_bCondition = false;
							}
							break;

						case "Fault_Operating":
							_bCondition = (_szSplit[0].equals("0") || _szSplit[0].equals("1"));
							break;

						case "Fault_PVolt":
							_bCondition = (_szSplit[0].equals("0") || _szSplit[0].equals("1")
									|| _szSplit[0].equals("2"));
							break;

						case "Fault_PVHC":
							_bCondition = (_szSplit[0].equals("0") || _szSplit[0].equals("1"));
							break;

						case "Fault_IGBT":
							_bCondition = (_szSplit[0].equals("0") || _szSplit[0].equals("1"));
							break;

						case "Fault_OT":
							_bCondition = (_szSplit[0].equals("0") || _szSplit[0].equals("1"));
							break;

						case "Fault_SysVolt":
							_bCondition = (_szSplit[0].equals("0") || _szSplit[0].equals("1")
									|| _szSplit[0].equals("2"));
							break;

						case "Fault_SysHC":
							_bCondition = (_szSplit[0].equals("0") || _szSplit[0].equals("1"));
							break;

						case "Fault_FQ":
							_bCondition = (_szSplit[0].equals("0") || _szSplit[0].equals("1")
									|| _szSplit[0].equals("2"));
							break;

						case "Fault_Down":
							_bCondition = (_szSplit[0].equals("0") || _szSplit[0].equals("1"));
							break;

						case "Fault_Leak":
							_bCondition = (_szSplit[0].equals("0") || _szSplit[0].equals("1"));
							break;

					}

				} catch (NumberFormatException nfe) {
				}

				if (!_bCondition) {
					System.out.println("형식이 맞지 않습니다. 다시 입력해  주세요.");
				}

			} catch (Exception e) {
				if (_szSplit[0].equals("-"))
					break;
				else
					System.out.println(". 형식이 맞지 않습니다. 다시 입력해  주세요.");
			}

		} while (!_bCondition);

	}

	public void SetHshConfig(HashMap<String, String> _hsh) {
		this.hshConfig = _hsh;
	}

	public void SetHshTrackerList(Map<Integer, DataSimulator_TrackerData> _hsh) {
		this.hshTrackerSet = _hsh;
	}

	public void SetMessageQueue(CustomQueue<DataSimulator_MessageData> _msgQueue) {
		this.messageQueue = _msgQueue;
	}

	private void ShowHelp() {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("  H E L P");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println(" help      	: show command list");
		System.out.println();
		System.out.println(" initcfg	: initialize configuration settings step by step");
		System.out.println(" modcfg 	: modify configuration");
		System.out.println(" default	: set configuration default setting");
		System.out.println();
		System.out.println(" viewcfg 	: view configuration settings");
		System.out.println(" list   	: show registered Tracker list");
		System.out.println();
		// System.out.println(" recent : show recently sent or received messages");
		// System.out.println(" -> recent [TID] : show recently sent or received
		// messages of TID");
		System.out.println(" start  	: start simulation");
		System.out.println(" stop   	: stop simulation");
		System.out.println(" alarm  	: make inverter data with alarm code and send");
		System.out.println("-----------------------------------------------------------------------------");
	}

	private void ShowCurrentSetting() {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("  C O N F I G    I N F O");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println(String.format("1. Server IP / Port\t\t[ %s ]", hshConfig.get("Destination")));
		System.out.println(String.format("2. Group ID  \t\t\t[ %s ]", hshConfig.get("GroupID")));
		System.out.println(String.format("3. Tracker Start Number\t\t[ %s ]", hshConfig.get("TStartNumber")));
		System.out.println(String.format("4. Number of Tracker \t\t[ %s ]", hshConfig.get("QOT")));
		System.out.println(String.format("5. Transfer Period \t\t[ %s ]", hshConfig.get("Period")));
		System.out.println(String.format("6. Tracker Data Mode\t\t[ %s ]", hshConfig.get("TMode")));
		System.out.println(String.format("7. Phase Mode \t\t\t[ %s ]", hshConfig.get("PMode")));
		System.out.println(String.format("8. Shadow Data Mode \t\t[ %s ]", hshConfig.get("SMode")));
		System.out.println(String.format("9. Ouput Multiply \t\t[ %s ]", hshConfig.get("OutputMultiply")));
	}

	private void ShowList() {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("  T R A C K E R    C O N N E C T I O N    L I S T ");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println(" [ TID ] |\t[ Tracker Address ]\t|\t[Phase]");
		System.out.println("-----------------------------------------------------------------------------");

		for (Integer _i : hshTrackerSet.keySet()) {
			System.out.println(String.format("    %d  |\t%s\t|\t%s ", _i,
					(hshTrackerSet.get(_i).GetSocketConnector().GetSocket() == null ? "null"
							: hshTrackerSet.get(_i).GetSocketConnector().GetSocket().getLocalSocketAddress()),
					hshTrackerSet.get(_i).GetPhase() == true ? "Single" : "Triple"));
		}
		System.out.println("-----------------------------------------------------------------------------");
	}

	private void ShowRecentMessage() {
		int _nCount = 0;

		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("  R E C E N T    M E S S A G E S    ( S E N D & R E C V )");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("\t[ Date. ]\t|\t\t[ Message ]");
		System.out.println("-----------------------------------------------------------------------------");

		for (DataSimulator_MessageData _md : messageQueue.GetQueueList()) {
			if (_md != null && _md.GetshMessage()[0] != 0) {
				System.out.print(String.format("[%d] [ SID : # | TID : %d]     ", _nCount++, _md.GetshTID()));

				for (short _sh : _md.GetshMessage()) {
					System.out.print(String.format("%c", _sh));
				}
			}
		}

		System.out.println("-----------------------------------------------------------------------------");
	}

	private void StartSimulation() {
		MakeTrackerSet();
		SetTimingTask();
	}

	private void StopSimulation() {
		boolean _bIsAllStop = false;

		dataGenerator.interrupt();

		for (int _nKey : hshTrackerSet.keySet()) {
			if (hshTrackerSet.get(_nKey) != null)
				if (hshTrackerSet.get(_nKey).GetSocketConnector() != null) {
					if (hshTrackerSet.get(_nKey).GetSocketConnector().GetMsgHandler() != null) {
						hshTrackerSet.get(_nKey).GetSocketConnector().GetMsgHandler().interrupt();
						if (hshTrackerSet.get(_nKey).GetSocketConnector().Socket_Close()) {
							hshTrackerSet.put(_nKey, null);

							_bIsAllStop |= true;

							System.out.println("hash[" + _nKey + "] socket closed");
						} else
							_bIsAllStop &= false;
					}
				}
		}

		if (_bIsAllStop)
			hshTrackerSet.clear();
	}

	private void SetDefaultConfig() {
		hshConfig.put("Destination", "127.0.0.1,5000");
		hshConfig.put("TStartNumber", "1");
		hshConfig.put("QOT", "20");
		hshConfig.put("Period", "15");
		hshConfig.put("GroupID", "1");
		hshConfig.put("TMode", "1");
		hshConfig.put("PMode", "0");
		hshConfig.put("SMode", "1");
		hshConfig.put("OutputMultiply", "1");

	}

	private void InitializeConfig() {
		try {
			ChoiceSelection(
					" * 서버 IP , Port 입력 [ ex. xxx.xxx.xxx.xxx,port ]  -- 현재 : " + hshConfig.get("Destination") + " : ",
					"Destination");
			ChoiceSelection(" * 군집 ID 입력 [ 0 ~ ]  -- 현재 : " + hshConfig.get("GroupID") + " : ", "GroupID");
			ChoiceSelection(" * 트래커 시작 번호 선택 [ 1 ~ 250 ]  -- 현재 : " + hshConfig.get("TStartNumber") + " : ",
					"TStartNumber");
			ChoiceSelection(" * 트래커 갯수 선택 [ 1 ~ 250 ]  -- 현재 : " + hshConfig.get("QOT") + " : ", "QOT");
			ChoiceSelection(" * 전송 주기 입력 [ minute 단위 ]  -- 현재 : " + hshConfig.get("Period") + " : ", "Period");
			ChoiceSelection(
					" * 트래커 동작 모드 선택 [ 1: 유사 데이터 발생 , 2: 랜덤 데이터 발생, 3: 순차 데이터 발생 ( 데이터 1부터 차례대로 .. )  ]  -- 현재 : "
							+ hshConfig.get("TMode") + " : ",
					"TMode");
			ChoiceSelection(" * 상 동작 모드 선택 [0: 랜덤 , 1: 단상만 , 2: 삼상만 ]  -- 현재 : " + hshConfig.get("PMode") + " : ",
					"PMode");
			ChoiceSelection(
					" * 음영 데이터 모드 선택 [ 1: 음영값 (R) , 2: 음영 ADC값 (S) ]  -- 현재 : " + hshConfig.get("SMode") + " : ",
					"SMode");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void ModifyConfig() {
		try {
			String __szCmd;
			do {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println(" M O D I F Y   C O N F I G ");
				System.out.println("-----------------------------------------------------------------------------");
				System.out
						.println(String.format("1. Server IP / Port Setting\t\t [ %s ]", hshConfig.get("Destination")));
				System.out.println(String.format("2. Group ID Setting\t\t\t [ %s ]", hshConfig.get("GroupID")));
				System.out.println(
						String.format("3. Tracker Start Number Setting\t\t [ %s ]", hshConfig.get("TStartNumber")));
				System.out.println(String.format("4. Number of Tracker Setting\t\t [ %s ]", hshConfig.get("QOT")));
				System.out.println(String.format("5. Transfer Period Setting\t\t [ %s ]", hshConfig.get("Period")));
				System.out.println(String.format("6. Tracker Data Mode Setting\t\t [ %s ]", hshConfig.get("TMode")));
				System.out.println(String.format("7. Phase Mode Setting\t\t\t [ %s ]", hshConfig.get("PMode")));
				System.out.println(String.format("8. Shadow Data Setting\t\t\t [ %s ]", hshConfig.get("SMode")));
				System.out.println(String.format("9. Output Multiply Value Setting\t [ %s ]",
						hshConfig.get("OutputMultiply")));
				System.out.println();
				System.out.println("0. Exit");
				System.out.println();
				System.out.print("> ");

				switch (__szCmd = scanner.next()) {
					case "1":
						ChoiceSelection(" * 서버 IP , Port 입력 [ ex. xxx.xxx.xxx.xxx,port ]  -- 현재 : "
								+ hshConfig.get("Destination") + " : ", "Destination");
						break;
					case "2":
						ChoiceSelection(" * 군집 ID 입력 [ 0 ~ ]  -- 현재 : " + hshConfig.get("GroupID") + " : ", "GroupID");
						break;
					case "3":
						ChoiceSelection(" * 트래커 시작 번호 선택 [ 1 ~ 250 ]  -- 현재 : " + hshConfig.get("TStartNumber") + " : ",
								"TStartNumber");
						break;
					case "4":
						ChoiceSelection(" * 트래커 갯수 선택 [ 1 ~ 250 ]  -- 현재 : " + hshConfig.get("QOT") + " : ", "QOT");
						break;
					case "5":
						ChoiceSelection(" * 전송 주기 입력 [ minute 단위 ]  -- 현재 : " + hshConfig.get("Period") + " : ",
								"Period");
						break;
					case "6":
						ChoiceSelection(
								" * 트래커 동작 모드 선택 [ 1: 유사 데이터 발생 , 2: 랜덤 데이터 발생 , 3: 순차 데이터 발생 ( 데이터 1부터 차례대로 .. ) ]  -- 현재 : "
										+ hshConfig.get("TMode") + " : ",
								"TMode");
						break;
					case "7":
						ChoiceSelection(
								" * 상 동작 모드 선택 [0: 랜덤 , 1: 단상만 , 2: 삼상만 ]  -- 현재 : " + hshConfig.get("PMode") + " : ",
								"PMode");
						break;
					case "8":
						ChoiceSelection(" * 음영 데이터 모드 선택 [ 1: 음영값 (R) , 2: 음영 ADC값 (S) ]  -- 현재 : "
								+ hshConfig.get("SMode") + " : ", "SMode");
						break;

					case "9":
						ChoiceSelection(" * 현재출력 배수 입력 [ kW 단위, 1 ~ 30 ] -- 현재 : " + hshConfig.get("OutputMultiply") + " : ",
								"OutputMultiply");
						break;

					case "0":
						break;
					default:
						System.out.println("Invalid Input!! Please type validate number.");
						continue;
				}
			} while (!__szCmd.equals("0"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void MakeTrackerSet() {
		try {
			if (hshTrackerSet.isEmpty()) {
				hshTrackerSet.clear();

				// Logging
				String _szLog = "\n\nStart Simulation Config ==============================\n";
				for (String _s : hshConfig.keySet()) {
					_szLog += String.format("%s : %s\n", _s, hshConfig.get(_s));
				}

				_szLog += "End of Simulation Config ==============================\n\n";

				DataSimulatorManager.logger.info(_szLog);
				// End of Logging

				String[] _szAddr = hshConfig.get("Destination").split(",");
				Integer _nQOT = Integer.parseInt(hshConfig.get("QOT"));
				Integer _nStartNumber = Integer.parseInt(hshConfig.get("TStartNumber"));
				Integer _nPMode = Integer.parseInt(hshConfig.get("PMode"));
				Integer _nSMode = Integer.parseInt(hshConfig.get("SMode"));
				Integer _nCapacityMultiply = Integer.parseInt(hshConfig.get("OutputMultiply"));

				for (int i = _nStartNumber; i < (_nStartNumber + _nQOT); i++) {
					hshTrackerSet.put(i, new DataSimulator_TrackerData(_szAddr[0], Integer.parseInt(_szAddr[1]),
							5000 + i, _nPMode, _nSMode, _nCapacityMultiply));
					sleep(10);
				}
			}

			else {
				System.out.println("Tracker task is running already !!");
			}

		} catch (Exception e) {
			System.out.println("[System]     Failed to make TrackerSet !");
			e.printStackTrace();
		}
	}

	private void SetTimingTask() {
		try {
			int _nDelay = 0;
			try {
				_nDelay = Integer.parseInt(hshConfig.get("Period"));
			} catch (NumberFormatException nfe) {
				System.out.println("warn. Period data parsing failed.. initialize period 15.");
				hshConfig.put("Period", "15");
				_nDelay = Integer.parseInt(hshConfig.get("Period"));

			} finally {
				dataGenerator = new DataSimulator_DataGenerator(Integer.parseInt(hshConfig.get("TMode")), _nDelay,
						Integer.parseInt(hshConfig.get("GroupID")), hshTrackerSet);
				dataGenerator.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void InitAlarmTypeSetting() {
		hshConfig.put("Fault_Operating", "0");
		hshConfig.put("Fault_PVolt", "0");
		hshConfig.put("Fault_PVHC", "0");
		hshConfig.put("Fault_IGBT", "0");
		hshConfig.put("Fault_OT", "0");
		hshConfig.put("Fault_SysVolt", "0");
		hshConfig.put("Fault_SysHC", "0");
		hshConfig.put("Fault_FQ", "0");
		hshConfig.put("Fault_Down", "0");
		hshConfig.put("Fault_Leak", "0");

		hshConfig.put("Alarmtype", "0");
	}

	private void SetAllAlarmSetting(boolean isHigh) {
		if (isHigh) {
			hshConfig.put("Fault_Operating", "1");
			hshConfig.put("Fault_PVolt", "1");
			hshConfig.put("Fault_PVHC", "1");
			hshConfig.put("Fault_IGBT", "1");
			hshConfig.put("Fault_OT", "1");
			hshConfig.put("Fault_SysVolt", "1");
			hshConfig.put("Fault_SysHC", "1");
			hshConfig.put("Fault_FQ", "1");
			hshConfig.put("Fault_Down", "1");
			hshConfig.put("Fault_Leak", "1");
		} else {
			hshConfig.put("Fault_Operating", "1");
			hshConfig.put("Fault_PVolt", "2");
			hshConfig.put("Fault_PVHC", "1");
			hshConfig.put("Fault_IGBT", "1");
			hshConfig.put("Fault_OT", "1");
			hshConfig.put("Fault_SysVolt", "2");
			hshConfig.put("Fault_SysHC", "1");
			hshConfig.put("Fault_FQ", "2");
			hshConfig.put("Fault_Down", "1");
			hshConfig.put("Fault_Leak", "1");
		}

		hshConfig.put("Alarmtype", String.format("%s", CalcAlarm()));
	}

	private int CalcAlarm() {
		int _nAlarm = 0;

		_nAlarm = Integer.parseInt(hshConfig.get("Fault_Operating")) * 0b1
				| (hshConfig.get("Fault_PVolt").equals("2") ? 0b100
						: hshConfig.get("Fault_PVolt").equals("1") ? 0b10 : 0)
				| Integer.parseInt(hshConfig.get("Fault_PVHC")) * 0b1000
				| Integer.parseInt(hshConfig.get("Fault_IGBT")) * 0b10000
				| Integer.parseInt(hshConfig.get("Fault_OT")) * 0b100000
				| (hshConfig.get("Fault_SysVolt").equals("2") ? 0b10000000
						: hshConfig.get("Fault_SysVolt").equals("1") ? 0b1000000 : 0)
				| Integer.parseInt(hshConfig.get("Fault_SysHC")) * 0b100000000
				| (hshConfig.get("Fault_FQ").equals("2") ? 0b10000000000
						: hshConfig.get("Fault_FQ").equals("1") ? 0b1000000000 : 0)
				| Integer.parseInt(hshConfig.get("Fault_Down")) * 0b100000000000
				| Integer.parseInt(hshConfig.get("Fault_Leak")) * 0b1000000000000;

		return _nAlarm;
	}

	private void SetAlarm() {
		try {

			if (!hshTrackerSet.isEmpty()) {
				System.out.println("DBG :: hshTrackerSet size is" + hshTrackerSet.size());
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println(" Alarming :: Select alarm sender");
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println(" [ TID ] |\t[ Tracker Address ]\t|\t[Phase]");
				System.out.println("-----------------------------------------------------------------------------");

				for (Integer _i : hshTrackerSet.keySet()) {
					System.out.println(String.format(" [ %d ]   \t%s\t|\t%s ", _i,
							(hshTrackerSet.get(_i).GetSocketConnector().GetSocket() == null ? "null"
									: hshTrackerSet.get(_i).GetSocketConnector().GetSocket().getLocalSocketAddress()
											.toString().replace("/", "")),
							hshTrackerSet.get(_i).GetPhase() == true ? "Single" : "Triple"));
				}
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println(" [ 0 ] \texit");
				System.out.println("-----------------------------------------------------------------------------");
				ChoiceSelection(" * 알람 전송할 대상 ID 선택 : ", "Alarmsender");

				if (!hshConfig.get("Alarmsender").equals("0")) {
					String __szCmd;
					do {
						try {
							System.out.println(
									"-----------------------------------------------------------------------------");
							System.out.println(" Alarming :: Select alarm type");
							System.out.println(
									"-----------------------------------------------------------------------------");
							System.out.println(" [1] 인버터 동작 유무 [" + hshConfig.get("Fault_Operating") + "] ");
							System.out.println(" [2] 태양전지 전압 [" + hshConfig.get("Fault_PVolt") + "]");
							System.out.println(" [3] 태양전지 과전류 [" + hshConfig.get("Fault_PVHC") + "]");
							System.out.println(" [4] 인버터 IGBT [" + hshConfig.get("Fault_IGBT") + "]");
							System.out.println(" [5] 인버터 과온 [" + hshConfig.get("Fault_OT") + "]");
							System.out.println(" [6] 계통 전압 [" + hshConfig.get("Fault_SysVolt") + "]");
							System.out.println(" [7] 계통 과전류 [" + hshConfig.get("Fault_SysHC") + "]");
							System.out.println(" [8] 계통 주파수 [" + hshConfig.get("Fault_FQ") + "]");
							System.out.println(" [9] 인버터 단독운전 (정전) [" + hshConfig.get("Fault_Down") + "]");
							System.out.println(" [10] 인버터 지락 (누전) [" + hshConfig.get("Fault_Leak") + "]");
							System.out.println(
									"-----------------------------------------------------------------------------");
							System.out.println(" [11] 알람 해제");
							System.out.println(" [12] 모든 알람 발생 (과전압)");
							System.out.println(" [13] 모든 알람 발생 (저전압)");
							System.out.println(
									"-----------------------------------------------------------------------------");
							System.out.println(" [0] : exit   , [send] : alarm send");
							System.out.println(
									"-----------------------------------------------------------------------------");
							hshConfig.put("Alarmtype", String.format("%s", CalcAlarm()));
							System.out.println(
									String.format(" 현재 알람 값 : %d (%s)", Integer.parseInt(hshConfig.get("Alarmtype")),
											Integer.toBinaryString(Integer.parseInt(hshConfig.get("Alarmtype")))));
						} catch (NumberFormatException nfe) {
							nfe.printStackTrace();
							InitAlarmTypeSetting();
						}

						switch (__szCmd = scanner.next()) {
							case "1":
								ChoiceSelection(" * 인버터 동작 유무 선택 ( Bit 0 )  [0 - 동작중 , 1 - 미작동] : ", "Fault_Operating");
								break;
							case "2":
								ChoiceSelection(" * 태양전지 전압 상태 전택 ( Bit 1~2 )  [0 - 정상 , 1 - 과전압, 2 - 저전압] : ",
										"Fault_PVolt");
								break;
							case "3":
								ChoiceSelection(" * 태양전지 과전류 여부 선택 ( Bit 3 )  [0 - 정상 , 1 - 과전류] : ", "Fault_PVHC");
								break;
							case "4":
								ChoiceSelection(" * 인버터 IGBT 에러 유무 선택 ( Bit 4 )  [0 - 정상, 1 - 에러] : ", "Fault_IGBT");
								break;
							case "5":
								ChoiceSelection(" * 인버터 과온 여부 선택 ( Bit 5 )  [0 - 정상, 1 - 과온] : ", "Fault_OT");
								break;
							case "6":
								ChoiceSelection(" * 인버터 계통 전압 상태 전택 ( Bit 6~7 )  [0 - 정상, 1 - 과전압, 2 - 저전압] : ",
										"Fault_SysVolt");
								break;
							case "7":
								ChoiceSelection(" * 인버터 과전류 여부 선택 ( Bit 8 )  [0 - 정상, 1 - 과온] : ", "Fault_SysHC");
								break;
							case "8":
								ChoiceSelection(" * 인버터 계통 주파수 선택 ( Bit 9~10 )  [0 - 정상, 1 - 고주파, 2 - 저주파] : ",
										"Fault_FQ");
								break;
							case "9":
								ChoiceSelection(" * 인버터 단독운전(정전) 여부  선택 ( Bit 11 )  [0 - 정상, 1 - 정전] : ", "Fault_Down");
								break;
							case "10":
								ChoiceSelection(" * 인버터 지락(누전) 여부  선택 ( Bit 12 )  [0 - 정상, 1 - 누전] : ", "Fault_Leak");
								break;
							case "11":
								InitAlarmTypeSetting();
								break;
							case "12":
								SetAllAlarmSetting(true);
								break;
							case "13":
								SetAllAlarmSetting(false);
								break;
							case "send":
								DataSimulator_TrackerData trData = hshTrackerSet
										.get(Integer.parseInt(hshConfig.get("Alarmsender")));
								byte[] _byteArr = {};

								if (trData.GetPhase().equals(true)) {
									trData.SetAlarmCode(Integer.parseInt(hshConfig.get("Alarmtype")));
									trData.GetSocketConnector().GetSocket().getOutputStream()
											.write((_byteArr = new DataSimulator_Protocol_SingleGen()
													.GetGenerateForAlarm(Integer.parseInt(hshConfig.get("GroupID")),
															Integer.parseInt(hshConfig.get("Alarmsender")),
															Integer.parseInt(hshConfig.get("Alarmtype")))));
								} else {
									trData.SetAlarmCode(Integer.parseInt(hshConfig.get("Alarmtype")));
									trData.GetSocketConnector().GetSocket().getOutputStream()
											.write((_byteArr = new DataSimulator_Protocol_TripleGen()
													.GetGenerateForAlarm(Integer.parseInt(hshConfig.get("GroupID")),
															Integer.parseInt(hshConfig.get("Alarmsender")),
															Integer.parseInt(hshConfig.get("Alarmtype")))));
								}
								break;
						}

					} while (!(__szCmd.equals("0") || __szCmd.equals("send")));
				}
			} else {
				System.out.println("please start task first !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void MethodTest(int _nCmd) {
		for (int _nKey : hshTrackerSet.keySet()) {
			try {
				// DataSimulator_Protocol_SingleGen _dps = new
				// DataSimulator_Protocol_SingleGen();
				byte[] _byteArr = {};

				switch (_nCmd) {
					// 1. single gen
					case 1:
						hshTrackerSet.get(_nKey).GetSocketConnector().GetSocket().getOutputStream().write(
								(_byteArr = new DataSimulator_Protocol_SingleGen().GetRandomGenerate(18, _nKey)));
						break;

					// 2 . triple gen
					case 2:
						hshTrackerSet.get(_nKey).GetSocketConnector().GetSocket().getOutputStream().write(
								(_byteArr = new DataSimulator_Protocol_TripleGen().GetRandomGenerate(18, _nKey)));
						break;

					case 3:
						hshTrackerSet.get(_nKey).GetSocketConnector().GetSocket().getOutputStream()
								.write(new DataSimulator_Protocol_Sensor().GetRandomGenerate(18, _nKey).getBytes());
						break;

					case 4:
						Random _rand = new Random();
						if (_rand.nextBoolean()) {
							hshTrackerSet.get(_nKey).GetSocketConnector().GetSocket().getOutputStream().write(
									(_byteArr = new DataSimulator_Protocol_SingleGen().GetRandomGenerate(18, _nKey)));
						} else {
							hshTrackerSet.get(_nKey).GetSocketConnector().GetSocket().getOutputStream().write(
									(_byteArr = new DataSimulator_Protocol_TripleGen().GetRandomGenerate(18, _nKey)));
						}

						hshTrackerSet.get(_nKey).GetSocketConnector().GetSocket().getOutputStream()
								.write(new DataSimulator_Protocol_Sensor().GetRandomGenerate(18, _nKey).getBytes());
						break;
				}
				System.out.print(" [ ");
				for (byte _b : _byteArr) {
					System.out.print(String.format("%d ", Byte.toUnsignedInt(_b)));
				}
				System.out.print(" ] ");
				System.out.println("length : " + _byteArr.length);

			} catch (Exception e) {
				System.out.println("Port " + (5000 + _nKey) + " failed TX");
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// super.run();
		System.out.println("[System]     DataSimulator_CommandHandler Thread starts running.");
		System.out.println("[System]     Type 'help' to see the commands.");

		while (true) {
			try {
				if (isInterrupted()) {
					break;
				}

				if (scanner.hasNext()) {

					switch (scanner.next().split(" ")[0].toLowerCase()) {
						case "help":
							ShowHelp();
							break;
						case "list":
							ShowList();
							break;
						case "recent":
							ShowRecentMessage();
							break;

						// ------------------------------------

						case "start":
							StartSimulation();
							break;
						case "stop":
							StopSimulation();
							break;

						// ------------------------------------

						case "default":
							SetDefaultConfig();
							break;
						case "initcfg":
							InitializeConfig();
							break;
						case "modcfg":
							ModifyConfig();
							break;
						case "viewcfg":
							ShowCurrentSetting();
							break;

						// ------------------------------------
						case "alarm":
							SetAlarm();
							break;

						// case "1":
						// MethodTest(1);
						// break;
						// case "2":
						// MethodTest(2);
						// break;
						// case "3":
						// MethodTest(3);
						// break;
						// case "4":
						// MethodTest(4);
						// break;

						default:
							break;
					}
					System.out.println();
					System.out.print("> ");
				} else {

				}

				sleep(5);
			} catch (Exception e) {
				System.out.println(e.toString());
			}

		}
		System.out.println("[System]     DataSimulator_CommandHandler Thread stops running.");
	}

}
