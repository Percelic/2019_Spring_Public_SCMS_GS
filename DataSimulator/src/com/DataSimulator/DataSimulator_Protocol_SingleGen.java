package com.DataSimulator;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.Common.Utils;

/* ------------------------------------------------- */
/*				������������ - �¾籤 ����͸� ���� ���� 		 */
/*				������ �������� ���α׷� ���� 				 */
/*													 */
/*				�ܻ� ����������  Body �κ� ���.			 */
/*													 */
/* ------------------------------------------------- */

public class DataSimulator_Protocol_SingleGen {
		private Protocol_Header header;
		private Protocol_Body_SingleData body;
		private Protocol_Footer foot;	
		
		public DataSimulator_Protocol_SingleGen() {
			// TODO Auto-generated constructor stub
			header = new Protocol_Header();
			body = new Protocol_Body_SingleData();
			foot = new Protocol_Footer();
		}
		
		public Protocol_Header GetHeader() {
			return header;
		}
		
		public Protocol_Body_SingleData GetBody() {
			return body;
		}
		
		public Protocol_Footer GetFooter() {
			return foot;
		}
		
		
		// 2. ���� ������ ����
		public byte[] GetRandomGenerate(int _nSID ,int _nTID) {
			ArrayList<Byte> _byteArr = new ArrayList<Byte>();
			Byte[] _btArr = {};
			
			Random _rand = new Random();			
			
			header.setSTX(Utils.byteToShort("$$SC".getBytes()));			
			header.setSI_ID(Utils.byteToShort(String.format("%04d",_nSID).getBytes()));
			header.setTR_ID(Utils.byteToShort(String.format("%03d",_nTID).getBytes()));
			header.setCMD("G".getBytes()[0]);
			
			body.setCMD((short)0x14);
			body.setSource((short)0x01);
			body.setType((short)0x01);
			body.setMultiCode((short)_nTID);
			body.setErrCode((short)0x00);
			
	 		body.setPV_V(_rand.nextInt(0xFFFF));
			body.setPV_A(_rand.nextInt(0xFFFF));
			body.setPV_Out(_rand.nextInt(0xFFFF));
			body.setSys_V(_rand.nextInt(0xFFFF));
			body.setSys_A(_rand.nextInt(0xFFFF));
			body.setCurrentGen(_rand.nextInt(0xFFFF));
			body.setPF(_rand.nextInt(0xFFFF));
			body.setHZ(_rand.nextInt(0xFFFF));
			body.setAccuGen(ThreadLocalRandom.current().nextLong(Long.parseUnsignedLong("FFFFFFFFFFFFFFF",16)));
			body.setFaultCode(_rand.nextInt(0xFFFF));
			
			foot.setCS(Utils.byteToShort("00".getBytes()));
			foot.setETX(Utils.byteToShort("\r\n".getBytes()));
			
			ArrayList<Short> arrList = new ArrayList<Short>();
			
			arrList.add(body.getCMD());
			arrList.add(body.getSource());
			arrList.add(body.getType());
			arrList.add(body.getMultiCode());
			arrList.add(body.getErrCode());		
			
			arrList.add((short) (body.getPV_V() >> 8));		arrList.add((short) (body.getPV_V() & 0xFF));
			arrList.add((short) (body.getPV_A() >> 8));		arrList.add((short) (body.getPV_A() & 0xFF));
			arrList.add((short) (body.getPV_Out() >> 8));	arrList.add((short) (body.getPV_Out() & 0xFF));
			arrList.add((short) (body.getSys_V() >> 8));	arrList.add((short) (body.getSys_V() & 0xFF));
			arrList.add((short) (body.getSys_A() >> 8));	arrList.add((short) (body.getSys_A() & 0xFF));
			arrList.add((short) (body.getCurrentGen() >> 8)); arrList.add((short) (body.getCurrentGen() & 0xFF));
			arrList.add((short) (body.getPF() >> 8));		arrList.add((short) (body.getPF() & 0xFF));
			arrList.add((short) (body.getHZ() >> 8));		arrList.add((short) (body.getHZ() & 0xFF));
			arrList.add((short) (body.getAccuGen() >> 56));	arrList.add((short) (((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFFFF",16)) >> 48)));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFF",16))  >> 40));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFF",16)) >> 32));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFF",16)) >> 24));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFF",16)) >> 16)); arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFF",16)) >> 8)); arrList.add((short) (body.getAccuGen() & 0xFF));
			arrList.add((short) (body.getFaultCode() >> 8));	arrList.add((short) (body.getFaultCode() & 0xFF));
			
			arrList.add((short)",".getBytes()[0]);
			
			// original CalcCS Position.
			
			
			for(short _s : header.getSTX()) {
				_byteArr.add((byte)_s);
			}
			
			_byteArr.add((byte)0x2C);
			for(short _s : header.getSI_ID()) {
				_byteArr.add((byte)_s);
			}
			_byteArr.add((byte)0x2C);
			for(short _s : header.getTR_ID()) {
				_byteArr.add(((byte)_s));
			}
			_byteArr.add((byte)0x2C);
			_byteArr.add((byte)header.getCMD());
			_byteArr.add((byte)0x2C);			
			for(short _s : arrList) {
				_byteArr.add((byte)_s );
			}
			
			Short[] _shCSArr = {};
			_shCSArr = _byteArr.toArray(_shCSArr);
			foot.setCS(Utils.byteToShort(String.format("%02X", Utils.CalculateCS( _shCSArr)).getBytes()) );
			
			for(short _s : foot.getCS()) {
				_byteArr.add((byte)_s );
			}
			for(short _s : foot.getETX()) {
				_byteArr.add((byte)_s);
			}		
			
			//System.out.println("length : " + _byteArr.size());
			_btArr  = _byteArr.toArray(_btArr);
			
			
			String _szSingleByte = "[";
			
			for( byte _b : Utils.ByteTobyte(_btArr)) {
				_szSingleByte += String.format(" %02X", Byte.toUnsignedInt(_b));
			}
			
			_szSingleByte += " ]";
			
			DataSimulatorManager.logger.debug("[ Send Singlegen data. length : " + _byteArr.size() + " \t] " + _szSingleByte);
			
			return Utils.ByteTobyte(_btArr);
		}
		
		// 1. ���� ������ ����
		public byte[] GetGenerate(int _nSID,int _nTID, int _nCurrentGen, Long _lAccuGen, int _nAlarmCode) {
			ArrayList<Short> _shCSArr = new ArrayList<Short>();
			Byte[] _btArr = {};		
			
			try {
			
			header.setSTX(Utils.byteToShort("$$SC".getBytes()));			
			header.setCMD("G".getBytes()[0]);
			header.setSI_ID(Utils.byteToShort(String.format("%04d",_nSID).getBytes()));
			header.setTR_ID(Utils.byteToShort(String.format("%03d",_nTID).getBytes()));
			
			body.setCMD((short)0x14);
			body.setSource((short)0x01);
			body.setType((short)0x01);
			body.setMultiCode((short)_nTID);
			body.setErrCode((short)0x00);
			
			body.setPV_V(_nCurrentGen / ThreadLocalRandom.current().nextInt(110,240));
			body.setPV_A(body.getPV_V() == 0 ? 0 : _nCurrentGen / body.getPV_V());
			body.setPV_Out(_nCurrentGen);
			body.setSys_V(_nCurrentGen / ThreadLocalRandom.current().nextInt(110,240));
			body.setSys_A(body.getSys_V() == 0 ? 0 : _nCurrentGen / body.getSys_V());
			body.setCurrentGen(_nCurrentGen);
			body.setPF(1000);
			body.setHZ(600);
			body.setAccuGen(_lAccuGen);
			body.setFaultCode(_nAlarmCode);
			
			foot.setCS(Utils.byteToShort("00".getBytes()));
			foot.setETX(Utils.byteToShort("\r\n".getBytes()));
			
			ArrayList<Short> arrList = new ArrayList<Short>();
			arrList.add(body.getCMD());
			arrList.add(body.getSource());
			arrList.add(body.getType());
			arrList.add(body.getMultiCode());
			arrList.add(body.getErrCode());		
			
			arrList.add((short) (body.getPV_V() >> 8));		arrList.add((short) (body.getPV_V() & 0xFF));
			arrList.add((short) (body.getPV_A() >> 8));		arrList.add((short) (body.getPV_A() & 0xFF));
			arrList.add((short) (body.getPV_Out() >> 8));	arrList.add((short) (body.getPV_Out() & 0xFF));
			arrList.add((short) (body.getSys_V() >> 8));	arrList.add((short) (body.getSys_V() & 0xFF));
			arrList.add((short) (body.getSys_A() >> 8));	arrList.add((short) (body.getSys_A() & 0xFF));
			arrList.add((short) (body.getCurrentGen() >> 8)); arrList.add((short) (body.getCurrentGen() & 0xFF));
			arrList.add((short) (body.getPF() >> 8));		arrList.add((short) (body.getPF() & 0xFF));
			arrList.add((short) (body.getHZ() >> 8));		arrList.add((short) (body.getHZ() & 0xFF));
			arrList.add((short) (body.getAccuGen() >> 56));	arrList.add((short) (((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFFFF",16)) >> 48)));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFF",16))  >> 40));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFF",16)) >> 32));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFF",16)) >> 24));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFF",16)) >> 16)); arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFF",16)) >> 8)); arrList.add((short) (body.getAccuGen() & 0xFF));
			arrList.add((short) (body.getFaultCode() >> 8));	arrList.add((short) (body.getFaultCode() & 0xFF));
			
			arrList.add((short)",".getBytes()[0]);
			
			// original CalcCS Position.
			
			
			for(short _s : header.getSTX()) {
				_shCSArr.add(_s);
			}
			_shCSArr.add((short) 0x2C);
			for(short _s : header.getSI_ID()) {
				_shCSArr.add(_s);
			}
			_shCSArr.add((short)0x2C);
			for(short _s : header.getTR_ID()) {
				_shCSArr.add(_s);
			}
			_shCSArr.add((short)0x2C);
			_shCSArr.add(header.getCMD());
			_shCSArr.add((short)0x2C);			
			for(short _s : arrList) {
				_shCSArr.add(_s );
			}
			
			foot.setCS(Utils.byteToShort(String.format("%02X", Utils.CalculateCS(_shCSArr.toArray())).getBytes()) );
			
			for(short _s : foot.getCS()) {
				_shCSArr.add(_s );
			}
			for(short _s : foot.getETX()) {
				_shCSArr.add(_s);
			}		
			
			//System.out.println("length : " + _shCSArr.size());
			_btArr = Utils.ArrObjToByte(_shCSArr.toArray());
			
			String _szSingleByte = "[";
			
			for( byte _b : Utils.ByteTobyte(_btArr)) {
				_szSingleByte += String.format(" %02X", Byte.toUnsignedInt(_b));
			}
			
			_szSingleByte += " ]";
			
			DataSimulatorManager.logger.debug("[ Send Singlegen data. length : " + _shCSArr.size() + " \t] " + _szSingleByte);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.print("");
			}
			
			return Utils.ByteTobyte(_btArr);
		}
		
		
		// 3. ���� ������ ����
		public byte[] GetGenerateSerial(int _nSID,int _nTID, int _nSerialCount) {
			ArrayList<Short> _shCSArr = new ArrayList<Short>();
			ArrayList<Byte> _byteArr = new ArrayList<Byte>();
			Byte[] _btArr = {};		
			
			try {
			
			header.setSTX(Utils.byteToShort("$$SC".getBytes()));			
			header.setCMD("G".getBytes()[0]);
			header.setSI_ID(Utils.byteToShort(String.format("%04d",_nSID).getBytes()));
			header.setTR_ID(Utils.byteToShort(String.format("%03d",_nTID).getBytes()));
			
			body.setCMD((short)0x14);
			body.setSource((short)0x01);
			body.setType((short)0x01);
			body.setMultiCode((short)_nTID);
			body.setErrCode((short)0x00);
			
			body.setPV_V(_nSerialCount);
			body.setPV_A(_nSerialCount);
			body.setPV_Out(_nSerialCount);
			body.setSys_V(_nSerialCount);
			body.setSys_A(_nSerialCount);
			body.setCurrentGen(_nSerialCount);
			body.setPF(_nSerialCount);
			body.setHZ(_nSerialCount);
			body.setAccuGen((long)_nSerialCount);
			body.setFaultCode(_nSerialCount);
			
			foot.setCS(Utils.byteToShort("00".getBytes()));
			foot.setETX(Utils.byteToShort("\r\n".getBytes()));
			
			ArrayList<Short> arrList = new ArrayList<Short>();			
			arrList.add(body.getCMD());
			arrList.add(body.getSource());
			arrList.add(body.getType());
			arrList.add(body.getMultiCode());
			arrList.add(body.getErrCode());		
			
			arrList.add((short) (body.getPV_V() >> 8));		arrList.add((short) (body.getPV_V() & 0xFF));
			arrList.add((short) (body.getPV_A() >> 8));		arrList.add((short) (body.getPV_A() & 0xFF));
			arrList.add((short) (body.getPV_Out() >> 8));	arrList.add((short) (body.getPV_Out() & 0xFF));
			arrList.add((short) (body.getSys_V() >> 8));	arrList.add((short) (body.getSys_V() & 0xFF));
			arrList.add((short) (body.getSys_A() >> 8));	arrList.add((short) (body.getSys_A() & 0xFF));
			arrList.add((short) (body.getCurrentGen() >> 8)); arrList.add((short) (body.getCurrentGen() & 0xFF));
			arrList.add((short) (body.getPF() >> 8));		arrList.add((short) (body.getPF() & 0xFF));
			arrList.add((short) (body.getHZ() >> 8));		arrList.add((short) (body.getHZ() & 0xFF));
			arrList.add((short) (body.getAccuGen() >> 56));	arrList.add((short) (((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFFFF",16)) >> 48)));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFF",16))  >> 40));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFF",16)) >> 32));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFF",16)) >> 24));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFF",16)) >> 16)); arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFF",16)) >> 8)); arrList.add((short) (body.getAccuGen() & 0xFF));
			arrList.add((short) (body.getFaultCode() >> 8));	arrList.add((short) (body.getFaultCode() & 0xFF));
			
			arrList.add((short)",".getBytes()[0]);
			
			// original CalcCS Position.
			
			for(short _s : header.getSTX()) {
				_shCSArr.add(_s);
			}
			_shCSArr.add((short) 0x2C);
			for(short _s : header.getSI_ID()) {
				_shCSArr.add(_s);
			}
			_shCSArr.add((short)0x2C);
			for(short _s : header.getTR_ID()) {
				_shCSArr.add(_s);
			}
			_shCSArr.add((short)0x2C);
			_shCSArr.add(header.getCMD());
			_shCSArr.add((short)0x2C);			
			for(short _s : arrList) {
				_shCSArr.add(_s );
			}
			
			foot.setCS(Utils.byteToShort(String.format("%02X", Utils.CalculateCS(_shCSArr.toArray())).getBytes()) );
			
			for(short _s : foot.getCS()) {
				_shCSArr.add(_s );
			}
			for(short _s : foot.getETX()) {
				_shCSArr.add(_s);
			}		
			
			//System.out.println("length : " + _shCSArr.size());
			_btArr = Utils.ArrObjToByte(_shCSArr.toArray());
			
			String _szSingleByte = "[";
			
			for( byte _b : Utils.ByteTobyte(_btArr)) {
				_szSingleByte += String.format(" %02X", Byte.toUnsignedInt(_b));
			}
			
			_szSingleByte += " ]";
			
			DataSimulatorManager.logger.debug("[ Send Singlegen data. length : " + _shCSArr.size() + " \t] " + _szSingleByte);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.print("");
			}
			
			return Utils.ByteTobyte(_btArr);
//			for(short _s : header.getSTX()) {
//				_byteArr.add((byte)_s);
//			}
//			_byteArr.add((byte)0x2C);
//			for(short _s : header.getSI_ID()) {
//				_byteArr.add((byte)_s);
//			}
//			_byteArr.add((byte)0x2C);
//			for(short _s : header.getTR_ID()) {
//				_byteArr.add(((byte)_s));
//			}
//			_byteArr.add((byte)0x2C);
//			_byteArr.add((byte)header.getCMD());
//			_byteArr.add((byte)0x2C);			
//			for(short _s : arrList) {
//				_byteArr.add((byte)_s );
//			}
//			
//			Short[] _shCSArr = {};
//			_shCSArr = _byteArr.toArray(_shCSArr);
//			foot.setCS(Utils.byteToShort(String.format("%02X", Utils.CalculateCS( _shCSArr)).getBytes()) );
//			
//			for(short _s : foot.getCS()) {
//				_byteArr.add((byte)_s );
//			}
//			for(short _s : foot.getETX()) {
//				_byteArr.add((byte)_s);
//			}		
//			
//			//System.out.println("length : " + _byteArr.size());
//			_btArr  = _byteArr.toArray(_btArr);
//			
//			
//			String _szSingleByte = "[";
//			
//			for( byte _b : Utils.ByteTobyte(_btArr)) {
//				_szSingleByte += String.format(" %02X", Byte.toUnsignedInt(_b));
//			}
//			
//			_szSingleByte += " ]";
//			
//			DataSimulatorManager.logger.debug("[ Send Singlegen data. length : " + _byteArr.size() + " \t] " + _szSingleByte);
//			} catch(Exception e) {
//				e.printStackTrace();
//				System.out.print("");
//			}
//			
//			return Utils.ByteTobyte(_btArr);
		}
		
		public byte[] GetGenerateForAlarm(int _nSID, int _nTID, int _nAlarmCode) {
			ArrayList<Short> _shCSArr = new ArrayList<Short>();
			ArrayList<Byte> _byteArr = new ArrayList<Byte>();
			Byte[] _btArr = {};		
			
			try {
			
			header.setSTX(Utils.byteToShort("$$SC".getBytes()));			
			header.setCMD("G".getBytes()[0]);
			header.setSI_ID(Utils.byteToShort(String.format("%04d",_nSID).getBytes()));
			header.setTR_ID(Utils.byteToShort(String.format("%03d",_nTID).getBytes()));
			
			body.setCMD((short)0x14);
			body.setSource((short)0x01);
			body.setType((short)0x01);
			body.setMultiCode((short)_nTID);
			body.setErrCode((short)0x00);
			
			body.setPV_V(0);
			body.setPV_A(0);
			body.setPV_Out(0);
			body.setSys_V(0);
			body.setSys_A(0);
			body.setCurrentGen(0);
			body.setPF(0);
			body.setHZ(0);
			body.setAccuGen((long)0);
			body.setFaultCode(_nAlarmCode);
			
			foot.setCS(Utils.byteToShort("00".getBytes()));
			foot.setETX(Utils.byteToShort("\r\n".getBytes()));
			
			ArrayList<Short> arrList = new ArrayList<Short>();			
			arrList.add(body.getCMD());
			arrList.add(body.getSource());
			arrList.add(body.getType());
			arrList.add(body.getMultiCode());
			arrList.add(body.getErrCode());		
			
			arrList.add((short) (body.getPV_V() >> 8));		arrList.add((short) (body.getPV_V() & 0xFF));
			arrList.add((short) (body.getPV_A() >> 8));		arrList.add((short) (body.getPV_A() & 0xFF));
			arrList.add((short) (body.getPV_Out() >> 8));	arrList.add((short) (body.getPV_Out() & 0xFF));
			arrList.add((short) (body.getSys_V() >> 8));	arrList.add((short) (body.getSys_V() & 0xFF));
			arrList.add((short) (body.getSys_A() >> 8));	arrList.add((short) (body.getSys_A() & 0xFF));
			arrList.add((short) (body.getCurrentGen() >> 8)); arrList.add((short) (body.getCurrentGen() & 0xFF));
			arrList.add((short) (body.getPF() >> 8));		arrList.add((short) (body.getPF() & 0xFF));
			arrList.add((short) (body.getHZ() >> 8));		arrList.add((short) (body.getHZ() & 0xFF));
			arrList.add((short) (body.getAccuGen() >> 56));	arrList.add((short) (((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFFFF",16)) >> 48)));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFF",16))  >> 40));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFF",16)) >> 32));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFF",16)) >> 24));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFF",16)) >> 16)); arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFF",16)) >> 8)); arrList.add((short) (body.getAccuGen() & 0xFF));
			arrList.add((short) (body.getFaultCode() >> 8));	arrList.add((short) (body.getFaultCode() & 0xFF));
			
			arrList.add((short)",".getBytes()[0]);
			
			// original CalcCS Position.
			
			
			for(short _s : header.getSTX()) {
				_shCSArr.add(_s);
			}
			_shCSArr.add((short) 0x2C);
			for(short _s : header.getSI_ID()) {
				_shCSArr.add(_s);
			}
			_shCSArr.add((short)0x2C);
			for(short _s : header.getTR_ID()) {
				_shCSArr.add(_s);
			}
			_shCSArr.add((short)0x2C);
			_shCSArr.add(header.getCMD());
			_shCSArr.add((short)0x2C);			
			for(short _s : arrList) {
				_shCSArr.add(_s );
			}
			
			foot.setCS(Utils.byteToShort(String.format("%02X", Utils.CalculateCS(_shCSArr.toArray())).getBytes()) );
			
			for(short _s : foot.getCS()) {
				_shCSArr.add(_s );
			}
			for(short _s : foot.getETX()) {
				_shCSArr.add(_s);
			}		
			
			//System.out.println("length : " + _shCSArr.size());
			_btArr = Utils.ArrObjToByte(_shCSArr.toArray());
			
			String _szSingleByte = "[";
			
			for( byte _b : Utils.ByteTobyte(_btArr)) {
				_szSingleByte += String.format(" %02X", Byte.toUnsignedInt(_b));
			}
			
			_szSingleByte += " ]";
			
			DataSimulatorManager.logger.debug("[ Send Singlegen data. length : " + _shCSArr.size() + " \t] " + _szSingleByte);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.print("");
			}
			
			return Utils.ByteTobyte(_btArr);
			
			
//			for(short _s : header.getSTX()) {
//				_byteArr.add((byte)_s);
//			}
//			_byteArr.add((byte)0x2C);
//			for(short _s : header.getSI_ID()) {
//				_byteArr.add((byte)_s);
//			}
//			_byteArr.add((byte)0x2C);
//			for(short _s : header.getTR_ID()) {
//				_byteArr.add(((byte)_s));
//			}
//			_byteArr.add((byte)0x2C);
//			_byteArr.add((byte)header.getCMD());
//			_byteArr.add((byte)0x2C);			
//			for(short _s : arrList) {
//				_byteArr.add((byte)_s );
//			}
//			
//			Short[] _shCSArr = {};
//			_shCSArr = _byteArr.toArray(_shCSArr);
//			foot.setCS(Utils.byteToShort(String.format("%02X", Utils.CalculateCS( _shCSArr)).getBytes()) );
//			
//			for(short _s : foot.getCS()) {
//				_byteArr.add((byte)_s );
//			}
//			for(short _s : foot.getETX()) {
//				_byteArr.add((byte)_s);
//			}		
//			
//			//System.out.println("length : " + _byteArr.size());
//			_btArr  = _byteArr.toArray(_btArr);
//			
//			
//			String _szSingleByte = "[";
//			
//			for( byte _b : Utils.ByteTobyte(_btArr)) {
//				_szSingleByte += String.format(" %02X", Byte.toUnsignedInt(_b));
//			}
//			
//			_szSingleByte += " ]";
//			
//			DataSimulatorManager.logger.debug("[ Send Singlegen data. length : " + _byteArr.size() + " \t] " + _szSingleByte);
//			} catch(Exception e) {
//				e.printStackTrace();
//				System.out.print("");
//			}
//			
//			return Utils.ByteTobyte(_btArr);
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