package com.DataCollector;

import java.io.Closeable;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.Common.CustomQueue;
import com.Common.Utils;

public class ValidationChecker implements Closeable {

	CustomQueue<Integer> vPopQueue;
	CustomQueue<Integer[]> vPushQueue;

	Validate_DataHandler dataHandler;

	public ValidationChecker() {

	}
	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		try {
			dataHandler.bThreadisRun = false;
			
			vPopQueue = null;
			vPushQueue = null;
			dataHandler = null;
		} catch(Exception e) {
			DataCollectManager.logger.error("[ DataCollector ] Error occured while closing ValidationChecker :: " + e.toString());
		}
	}

	public void Valid_StartPopHandler() {
		dataHandler = new Validate_DataHandler(vPopQueue, vPushQueue);
		dataHandler.start();
	}

	public void SetPopQueue(CustomQueue<Integer> _cq) {
		vPopQueue = _cq;
	}

	public void SetPushQueue(CustomQueue<Integer[]> _cq) {
		vPushQueue = _cq;
	}
}

class Validate_DataHandler extends Thread {

	CustomQueue<Integer> PopQueue;
	CustomQueue<Integer[]> PushQueue;
	boolean bThreadisRun = true;

	int nRunCount = 0;

	public Validate_DataHandler(CustomQueue<Integer> _cq, CustomQueue<Integer[]> _cqq) {
		PopQueue = _cq;
		PushQueue = _cqq;
	}

	@SuppressWarnings("unused")
	@Override
	public void run() {
		// TODO Auto-generated method stub

		int nStartIdx = 0;
		int nEndIdx = 0;
		int nPacketLength = 0;
		Integer tmpData = 0;
		

		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		
		while (bThreadisRun) {
			try {
				if (!PopQueue.isEmpty()) {
					
					if ((tmpData = PopQueue.Dequeue()) != null && tmpData == 0x24) // Catch STX
					{
						if (!PopQueue.isEmpty() && PopQueue.Dequeue() == 0x24) // Catch STX+1
						{
							if (!PopQueue.isEmpty() && PopQueue.Dequeue() == 'S') {
								if (!PopQueue.isEmpty() && PopQueue.Dequeue() == 'C') {

									// suspect point
									nStartIdx = (PopQueue.IndexOfFront()) >= 3 ? (PopQueue.IndexOfFront() - 3)
											: (PopQueue.SizeOf()) - (3 - PopQueue.IndexOfFront());

									// 1. original parsing method
									// Integer[] _pkt = PopQueue.Copy(nStartIdx, nStartIdx + 100
									// /*DataCollectManager.MAXSIZE_OF_PROTOCOL+1*/);

									// 2. 190619 changed method
									Integer[] _pkt = PopQueue.Copy(nStartIdx, nStartIdx + 15);

									Integer[] _pktSample = PopQueue.Copy(nStartIdx,
											nStartIdx + 16 /* DataCollectManager.MAXSIZE_OF_PROTOCOL+1 */);
									int _nPktSize = 0;

									switch (_pktSample[14]) {
									case (int)'G':
										_pktSample = PopQueue.Copy(nStartIdx + 16, nStartIdx + 20);

										if (_pktSample[2].equals(0x01))
											_pkt = PopQueue.Copy(nStartIdx,
													nStartIdx + DataCollectManager.SIZE_OF_PROTOCOL_SINGLE_GENERATION);
										else if (_pktSample[2].equals(0x02))
											_pkt = PopQueue.Copy(nStartIdx,
													nStartIdx + DataCollectManager.SIZE_OF_PROTOCOL_TRIPLE_GENERATION);
										break;

									default:
										_pktSample = PopQueue.Copy(nStartIdx,
												nStartIdx + (DataCollectManager.SIZE_OF_PROTOCOL_SENSOR + 20));

										for (int i = DataCollectManager.MINIMUM_OF_PROTOCOL; i < _pktSample.length; i++) {
											if (_pktSample[i - 1] == 0x0D && _pktSample[i] == 0x0A) {
												_nPktSize = i;
												break;
											}
										}

										_pkt = PopQueue.Copy(nStartIdx, nStartIdx + _nPktSize + 1);
										break;
									}

									//

									String _szPkt = "[ ";

									for (Integer i : _pkt) {
										_szPkt += String.format("%02X ", i);
									}

									_szPkt += " ]";

									// 19-05-24 commented.
									// DataCollectManager.logger.debug(String.format("[Sample Packet] IndexOfFront :
									// %d / nStartIdx : %d ( %d ~ %d ) / _pkt size : %d / contents : %s ",
									// PopQueue.IndexOfFront(), nStartIdx ,nStartIdx, (nStartIdx +
									// DataCollectManager.MAXSIZE_OF_PROTOCOL+1) % PopQueue.SizeOf() ,_pkt.length,
									// _szPkt));

									// 1. original Find ETX Method
									// for(int i = DataCollectManager.MINIMUM_OF_PROTOCOL; i < _pkt.length ; i++) {
									//// System.out.println("_pkt[" + (i-1) +"] :" + _pkt[i-1] + " , _pkt[" + i +"]
									// : " + _pkt[i]);
									// if(_pkt[i] == null) {
									// if((_pkt[i-1] != null && _pkt[i-2] != null) && _pkt[i-1] == 0x0a && _pkt[i-2]
									// == 0x0d) {
									// nEndIdx = nStartIdx + (i-1);
									// nPacketLength = (nEndIdx - nStartIdx) + 1;
									// break;
									// }
									// }
									// }
									// 1 end

									// 2. 190619 Find ETX Method

									DataCollectManager.logger.debug(String.format("[Sample Packet] IndexOfFront : %d / nStartIdx : %d ( %d ~ %d ) / _pkt size : %d / contents : %s ",
											 PopQueue.IndexOfFront(), nStartIdx ,nStartIdx, (nStartIdx +
											 DataCollectManager.MAXSIZE_OF_PROTOCOL+1) % PopQueue.SizeOf() ,_pkt.length,
											 _szPkt));
									
									if (_pkt[_pkt.length - 1] == 0x0a && _pkt[_pkt.length - 2] == 0x0d) {
										nEndIdx = nStartIdx + (_pkt.length - 1);
										nPacketLength = (nEndIdx - nStartIdx) + 1;

									}

									//

									try {

										
										// 190619 commented.
//										if (nPacketLength > DataCollectManager.MAXSIZE_OF_PROTOCOL + 10) // 19-05-02
//																											// added
//											DataCollectManager.logger.debug(
//													"[ DataCollector ] Packet Length over the max length of protocol ! : "
//															+ nPacketLength);
//										else {
											Integer[] _pkt1;

											if (ValidationCheck(_pkt1 = packetize1(PopQueue, nStartIdx, nEndIdx))) {
												synchronized (PushQueue) {
													PushQueue.Enqueue(_pkt1);
												}
											}
										//}
										// 19-05-24 commented.
										// DataCollectManager.logger.debug("Packet Queue Front : " +
										// PopQueue.IndexOfFront() + " | Packet Queue Rear : " + PopQueue.IndexOfRear()
										// + " | Amount of Queue : " + PopQueue.AmountOf());

									} catch (Exception e) {
										DataCollectManager.logger.warn(
												"[ DataCollector ] Validate_runner error occured ! : " + e.toString());
									}
								}
							}
						}
					}
				}
				Thread.sleep(1);
				
			} catch (Exception e) {
				DataCollectManager.logger
						.warn("[ DataCollector ] Validate_DataPopHandler error occured ! : " + e.toString());
				try {
					Thread.sleep(1);
				} catch (Exception _e) {

				}
			}
		}
		

		// super.run();
	}

	@SuppressWarnings("finally")
	public Integer[] packetize1(CustomQueue<Integer> q, int startIdx, int endIdx) {
		Integer[] _packet = new Integer[startIdx < endIdx ? endIdx - startIdx : (q.SizeOf() + endIdx) - startIdx];

		try {
			_packet = q.Copy(startIdx, endIdx + 1);

			String _szPacket = "";
			_szPacket += String
					.format(String.format("[ DataCollector::packetize ] received Data [%d] : [", _packet.length));
			
			if(_packet.length >= DataCollectManager.MAXSIZE_OF_PROTOCOL + 10) {
				for (Integer a : _packet) {
					_szPacket += String.format(" %02X", a);
				}
	
				_szPacket += " ]";
	
				DataCollectManager.logger.debug(_szPacket);
			}
		} catch (Exception e) {
			DataCollectManager.logger.warn("[ DataCollector ] Validate_packetize error occured ! : " + e.toString());
		} finally {
			return _packet;
		}
	}

	public boolean ValidationCheck(Integer[] _packet) {

		synchronized (_packet) {

			String _szStr = "";
			String[] _szData;

			for (int i = 0; i < 4; i++) {
				_szStr += String.format("%c", _packet[i]);
			}

			if (!_szStr.equals("$$SC")) {
				return false;
			}

			// Data Check - SI_ID
			if (0 > Integer.parseInt(String.format("%c%c%c%c", _packet[5], _packet[6], _packet[7], _packet[8]))
					&& 9999 < Integer
							.parseInt(String.format("%c%c%c%c", _packet[5], _packet[6], _packet[7], _packet[8]))) {
				return false;
			}

			// Data Check - TR_ID
			if (0 > Integer.parseInt(String.format("%c%c%c", _packet[10], _packet[11], _packet[12]))
					&& 999 < Integer.parseInt(String.format("%c%c%c", _packet[10], _packet[11], _packet[12]))) {
				return false;
			}

			// CMD Check
			if (!(_packet[14] == 'G' || _packet[14] == 'W' || _packet[14] == 'R' || _packet[14] == 'S' || _packet[14] == 'X')) {
				return false;
			}

			switch (_packet[14]) {

			case (int)('G'):
				// STX Check
				if (_packet.length == DataCollectManager.SIZE_OF_PROTOCOL_SINGLE_GENERATION) {

					// Data Check - Header - COMMAND
					if (_packet[16] == 0x14) {

						// Data Check - Header - Energy Source
						if (_packet[17] == 0x01) { // 0x01 is Solar

							// Data Check - Header - Type
							if (_packet[18] == 0x01) {

								// Data Check - Header - MultiCode[19] --> Skip

								// Data Check - Header - ErrorCode[20] --> Skip

								if (_packet[47] == 0x2C) {
									// Data Check - Body - PV V 2 [21~22] --> Skip
									// Data Check - Body - PV A 2 [23~24] --> Skip
									// Data Check - Body - PV Out 2 [25~26] --> Skip
									// Data Check - Body - Sys V 2 [27~28] --> Skip
									// Data Check - Body - Sys A 2 [29~30] --> Skip
									// Data Check - Body - CurrGen 2 [31~32] --> Skip
									// Data Check - Body - PF 2 [33~34] --> Skip
									// Data Check - Body - HZ 2 [35~36] --> Skip
									// Data Check - Body - AccuGen 8 [37~44] --> Skip
									// Data Check - Body - FaultCode 2 [45~46] --> Skip

									// Comma [47]

									// Data Check - Footer - CS [48~49]
									if (DataCollectManager.CHECK_CS) {

										try {
											if (_packet[48] >= '0'
													&& Integer.parseInt(String.format("%C", _packet[48]), 16) <= 'F'
													&& _packet[49] >= '0'
													&& Integer.parseInt(String.format("%C", _packet[49]), 16) <= 'F') {

												if ((Integer.parseInt(String.format("%C%C", _packet[48], _packet[49]),
														16)) == Utils.CalculateCS(
																Arrays.copyOfRange(_packet, 0, _packet.length - 4))) {

													// Data Check - Footer - ETX [50~51]
													if (_packet[50] == 0x0d && _packet[51] == 0x0a)
														return true;
												}
											}
										} catch (NumberFormatException nfe) {
										}
									} else {
										if (_packet[50] == 0x0d && _packet[51] == 0x0a)
											return true;
									}
								}
							}
						}
					}
				}

				else if (_packet.length == DataCollectManager.SIZE_OF_PROTOCOL_TRIPLE_GENERATION) {
					// Data Check - Header - COMMAND
					if (_packet[16] == 0x14) {

						// Data Check - Header - Energy Source
						if (_packet[17] == 0x01) { // 0x01 is Solar

							// Data Check - Header - Type
							if (_packet[18] == 0x02) {

								// Data Check - Header - MultiCode[19] --> Skip

								// Data Check - Header - ErrorCode[20] --> Skip

								if (_packet[59] == 0x2C) {
									// Data Check - Body - PV V 2 [21~22] --> Skip
									// Data Check - Body - PV A 2 [23~24] --> Skip
									// Data Check - Body - PV Out 4 [25~28] --> Skip
									// Data Check - Body - Sys-R V 2 [29~30] --> Skip
									// Data Check - Body - Sys-S V 2 [31~32] --> Skip
									// Data Check - Body - Sys-T V 2 [33~34] --> Skip
									// Data Check - Body - Sys-R A 2 [35~36] --> Skip
									// Data Check - Body - Sys-S A 2 [37~38] --> Skip
									// Data Check - Body - Sys-T A 2 [39~40] --> Skip
									// Data Check - Body - CurrGen 4 [41~44] --> Skip
									// Data Check - Body - PF 2 [45~46] --> Skip
									// Data Check - Body - HZ 2 [47~48] --> Skip
									// Data Check - Body - AccuGen 8 [49~56] --> Skip
									// Data Check - Body - FaultCode 2 [57~58] --> Skip

									// Comma [59]

									// Data Check - Footer - CS [60~63]
									if (DataCollectManager.CHECK_CS) {

										try {
											if (_packet[60] >= '0'
													&& Integer.parseInt(String.format("%C", _packet[60]), 16) <= 'F'
													&& _packet[61] >= '0'
													&& Integer.parseInt(String.format("%C", _packet[61]), 16) <= 'F') {

												if ((Integer.parseInt(String.format("%C%C", _packet[60], _packet[61]),
														16)) == Utils.CalculateCS(
																Arrays.copyOfRange(_packet, 0, _packet.length - 4))) { // origin
																														// :
																														// 16
																														// ,
																														// _packet.length
																														// -
																														// 5

													// Data Check - Footer - ETX [62~63]
													if (_packet[62] == 0x0d && _packet[63] == 0x0a)
														return true;
												}
											}
										} catch (NumberFormatException nfe) {
										}

									} else {
										if (_packet[62] == 0x0d && _packet[63] == 0x0a)
											return true;
									}

								}
							}
						}
					}
				}

				break;

			case (int)('W'):
//					if(_packet.length == DataCollectManager.SIZE_OF_PROTOCOL_SENSOR) {
				String _str = "";

				for (int i = 0; i < _packet.length; i++) {
					_str += String.format("%c", _packet[i]);
				}

				_szData = _str.split(",");

//						for(String __s : _szData) {
//							System.out.print(__s + " ");
//						}

				try {

					if (_szData.length == 12) {

						if (Utils.isInRange(Integer.parseInt(_szData[1]), 9999, 0)) { // SI ID
							if (Utils.isInRange(Integer.parseInt(_szData[2]), 9999, 0)) { // TR ID
								if (_szData[3].equals("W")) {
									if (Utils.isInRange(Float.parseFloat(_szData[4]), 9999.99f, -40.0f)) { // AMBIENT
																											// TEMP
										if (Utils.isInRange(Float.parseFloat(_szData[5]), 9999.99f, -40.0f)) { // MODULE
																												// TEMP
											if (Utils.isInRange(Float.parseFloat(_szData[6]), 9999.99f, -40.0f)) { // CONTROLLER
																													// TEMP
												if (Utils.isInRange(Float.parseFloat(_szData[7]), 9999.99f, 0.0f)) { // HUMID
													if (Utils.isInRange(Integer.parseInt(_szData[8]), 9999, 0)) { // Horizontal
																													// IDT
														if (Utils.isInRange(Integer.parseInt(_szData[9]), 9999, 0)) { // Slope
																														// IDT
															if (Utils.isInRange(Float.parseFloat(_szData[10]), 9999.99f,
																	0.0f)) { // WindSpeed
																if (Utils.isInRange(Integer.parseInt(
																		_szData[11].substring(0, 2), 16), 0xFF, 0x00)) { // CS
																	if (Integer.parseInt(_szData[11].substring(0, 2),
																			16) == Utils.CalculateCS(
																					Utils.byteToInteger(_str
																							.substring(0,
																									_str.length() - 4)
																							.getBytes()))) {
																		if (_szData[11].substring(2).equals("\r\n")) {
																			return true;
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

				catch (NumberFormatException nfe) {
					return false;
				}
				break;

			case (int)('R'):
				_szStr = "";
				for (Integer _i : _packet) {
					_szStr += String.format("%c", _i);
				}
				_szData = _szStr.split(",");

				try {
					if (_szData.length == 11) {
						if (Utils.isInRange(Integer.parseInt(_szData[1]), 9999, 0)) { // SI ID
							if (Utils.isInRange(Integer.parseInt(_szData[2]), 9999, 0)) { // TR ID
								if (_szData[3].equals("R")) { // CMD
									if (Utils.isInRange(Integer.parseInt(_szData[4]), 1, 0)) { // WCDS1
										if (Utils.isInRange(Integer.parseInt(_szData[5]), 1, 0)) { // WCDS2
											if (Utils.isInRange(Integer.parseInt(_szData[6]), 1, 0)) { // WCDS3
												if (Utils.isInRange(Integer.parseInt(_szData[7]), 1, 0)) { // ECDS1
													if (Utils.isInRange(Integer.parseInt(_szData[8]), 1, 0)) { // ECDS2
														if (Utils.isInRange(Integer.parseInt(_szData[9]), 1, 0)) { // ECDS3
															if (Utils.isInRange(
																	Integer.parseInt(_szData[10].substring(0, 2), 16),
																	0xFF, 0x00)) { // CS
																if (Integer.parseInt(_szData[10].substring(0, 2),
																		16) == Utils
																				.CalculateCS(
																						Utils.byteToInteger(_szStr
																								.substring(0,
																										_szStr.length()
																												- 4)
																								.getBytes()))) {
																	if (_szData[10].substring(2).equals("\r\n")) {
																		return true;
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

				catch (NumberFormatException nfe) {
					return false;
				}
				break;
			case (int)('S'):
				_szStr = "";
				for (Integer _i : _packet) {
					_szStr += String.format("%c", _i);
				}
				_szData = _szStr.split(",");

				try {
					if (_szData.length == 13) {
						if (Utils.isInRange(Integer.parseInt(_szData[1]), 9999, 0)) { // SI ID
							if (Utils.isInRange(Integer.parseInt(_szData[2]), 9999, 0)) { // TR ID
								if (_szData[3].equals("S")) {
									if (Utils.isInRange(Integer.parseInt(_szData[4]), 4096, 0)) { // ADC 0
										if (Utils.isInRange(Integer.parseInt(_szData[5]), 4096, 0)) { // ADC 1
											if (Utils.isInRange(Integer.parseInt(_szData[6]), 4096, 0)) { // ADC 2
												if (Utils.isInRange(Integer.parseInt(_szData[7]), 4096, 0)) { // ADC 3
													if (Utils.isInRange(Integer.parseInt(_szData[8]), 4096, 0)) { // ADC
																													// 4
														if (Utils.isInRange(Integer.parseInt(_szData[9]), 4096, 0)) { // ADC
																														// 5
															if (Utils.isInRange(Integer.parseInt(_szData[10]), 4096,
																	0)) { // ADC 6
																if (Utils.isInRange(Integer.parseInt(_szData[11]), 4096,
																		0)) { // ADC 7
																	if (Utils.isInRange(
																			Integer.parseInt(
																					_szData[12].substring(0, 2), 16),
																			0xFF, 0x00)) { // CS
																		if (Integer.parseInt(
																				_szData[12].substring(0, 2),
																				16) == Utils.CalculateCS(
																						Utils.byteToInteger(_szStr
																								.substring(0,
																										_szStr.length()
																												- 4)
																								.getBytes()))) {
																			if (_szData[12].substring(2)
																					.equals("\r\n")) {
																				return true;
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

				catch (NumberFormatException nfe) {
					return false;
				}
				break;
				
			case (int)('X'):
				_szStr = "";
				for (Integer _i : _packet) {
					_szStr += String.format("%c", _i);
				}
				_szData = _szStr.split(",");

				try {
					if (_szData.length == 15) {
						if (Utils.isInRange(Integer.parseInt(_szData[1]), 9999, 0)) { // SI ID
							if (Utils.isInRange(Integer.parseInt(_szData[2]), 9999, 0)) { // TR ID
								if (_szData[3].equals("X")) {
									if (Utils.isInRange(Long.parseLong(_szData[4]), Long.MAX_VALUE , 0)) { // COUNT
										if (Utils.isInRange(Long.parseLong(_szData[5]), Long.MAX_VALUE, 0)) { // X1
											if (Utils.isInRange(Long.parseLong(_szData[6]), Long.MAX_VALUE, 0)) { // X2
												if (Utils.isInRange(Long.parseLong(_szData[7]), Long.MAX_VALUE, 0)) { // X3
													if (Utils.isInRange(Long.parseLong(_szData[8]), Long.MAX_VALUE, 0)) { // X4															// X5
														if (Utils.isInRange(Long.parseLong(_szData[9]), Long.MAX_VALUE, 0)) { // X5
															if (Utils.isInRange(Long.parseLong(_szData[10]), Long.MAX_VALUE, 0)) { // X6
																if (Utils.isInRange(Long.parseLong(_szData[11]), Long.MAX_VALUE, 0)) { // X7
																	if(Utils.isInRange(Long.parseLong(_szData[12]), Long.MAX_VALUE, 0)) {	// X8
																		if(Utils.isInRange(Long.parseLong(_szData[13]), Long.MAX_VALUE, 0)) {	// X9
																			if (Utils.isInRange(Integer.parseInt(_szData[14].substring(0, 2), 16), 0xFF, 0x00)) { // CS
																				if (Integer.parseInt(_szData[14].substring(0, 2), 16) == Utils.CalculateCS(Utils.byteToInteger(_szStr.substring(0, _szStr.length()- 4).getBytes()))) {
																					if (_szData[14].substring(2).equals("\r\n")) {
																						return true;
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

				catch (NumberFormatException nfe) {
					return false;
				}
				break;
			}

			return false;
		}
	}
}