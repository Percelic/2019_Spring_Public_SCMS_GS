package com.DataSimulator;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.Common.Utils;

/* ------------------------------------------------- */
/*				������������ - �¾籤 ����͸� ���� ���� 		 */
/*				������ �������� ���α׷� ���� 				 */
/*													 */
/*				��� ����������  Body �κ� ���.			 */
/*													 */
/* ------------------------------------------------- */

public class DataSimulator_Protocol_TripleGen {
		Protocol_Header header;
		Protocol_Body_TripleData body;
		Protocol_Footer foot;
		
		public DataSimulator_Protocol_TripleGen() {
			// TODO Auto-generated constructor stub
			header = new Protocol_Header();
			body = new Protocol_Body_TripleData();
			foot = new Protocol_Footer();
		}
		
		public Protocol_Header GetHeader() {
			return header;
		}
		
		public Protocol_Body_TripleData GetBody() {
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
			header.setCMD("G".getBytes()[0]);
			header.setSI_ID(Utils.byteToShort(String.format("%04d",_nSID).getBytes()));
			header.setTR_ID(Utils.byteToShort(String.format("%03d",_nTID).getBytes()));
			
			body.setCMD((short)0x14);
			body.setSource((short)0x01);
			body.setType((short)0x02);
			body.setMultiCode((short)_nTID);
			body.setErrCode((short)0x00);
			
	 		body.setPV_V(_rand.nextInt(0xFFFF));
			body.setPV_A(_rand.nextInt(0xFFFF));
			body.setPV_Out(ThreadLocalRandom.current().nextLong(Long.parseUnsignedLong("FFFFFFFF",16)));
			body.setSysR_V(_rand.nextInt(0xFFFF));
			body.setSysS_V(_rand.nextInt(0xFFFF));
			body.setSysT_V(_rand.nextInt(0xFFFF));
			body.setSysR_A(_rand.nextInt(0xFFFF));
			body.setSysS_A(_rand.nextInt(0xFFFF));
			body.setSysT_A(_rand.nextInt(0xFFFF));
			body.setCurrentGen(ThreadLocalRandom.current().nextLong(Long.parseUnsignedLong("FFFFFFFF",16)));
			body.setPF(_rand.nextInt(0xFFFF));
			body.setHz(_rand.nextInt(0xFFFF));
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
			arrList.add((short) (body.getPV_Out() >> 24));	arrList.add((short) ((body.getPV_Out() & Long.parseUnsignedLong("FFFFFF",16)) >> 16));	arrList.add((short)((body.getPV_Out() & 0xFFFF) >> 8));	arrList.add((short)(body.getPV_Out() & 0xFF));
			arrList.add((short) (body.getSysR_V() >> 8));	arrList.add((short) (body.getSysR_V() & 0xFF));
			arrList.add((short) (body.getSysS_V() >> 8));	arrList.add((short) (body.getSysS_V() & 0xFF));
			arrList.add((short) (body.getSysT_V() >> 8));	arrList.add((short) (body.getSysT_V() & 0xFF));
			arrList.add((short) (body.getSysR_A() >> 8));	arrList.add((short) (body.getSysR_A() & 0xFF));
			arrList.add((short) (body.getSysS_A() >> 8));	arrList.add((short) (body.getSysS_A() & 0xFF));
			arrList.add((short) (body.getSysT_A() >> 8));	arrList.add((short) (body.getSysT_A() & 0xFF));
			arrList.add((short) (body.getCurrentGen() >> 24));	arrList.add((short) ((body.getCurrentGen() & Long.parseUnsignedLong("FFFFFF",16)) >> 16));	arrList.add((short) ((body.getCurrentGen() & 0xFFFF) >> 8)); arrList.add((short) (body.getCurrentGen() & 0xFF));
			arrList.add((short) (body.getPF() >> 8));		arrList.add((short) (body.getPF() & 0xFF));
			arrList.add((short) (body.getHz() >> 8));		arrList.add((short) (body.getHz() & 0xFF));
			arrList.add((short) (body.getAccuGen() >> 56));	arrList.add((short) (((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFFFF",16)) >> 48)));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFF",16))  >> 40));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFF",16)) >> 32));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFF",16)) >> 24));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFF",16)) >> 16)); arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFF",16)) >> 8)); arrList.add((short) (body.getAccuGen() & 0xFF));
			arrList.add((short) (body.getFaultCode() >> 8));	arrList.add((short) (body.getFaultCode() & 0xFF));
			arrList.add((short)",".getBytes()[0]);
			
			Short[] _shCSArr = {};
			_shCSArr = arrList.toArray(_shCSArr);
			foot.setCS(Utils.byteToShort(String.format("%02X", Utils.CalculateCS( _shCSArr)).getBytes()) );
			
			
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
			for(short _s : arrList) {
				_byteArr.add((byte)_s );
			}
			
			//_byteArr.add((byte)0x2C);
			for(short _s : foot.getCS()) {
				_byteArr.add((byte)_s );
			}
			for(short _s : foot.getETX()) {
				_byteArr.add((byte)_s);
			}		
			
			//System.out.println("length : " + _byteArr.size());
			_btArr  = _byteArr.toArray(_btArr);
			
			String _szTripleByte = "[";
			
			for( byte _b :  Utils.ByteTobyte(_btArr)) {
				_szTripleByte += String.format(" %02X", Byte.toUnsignedInt(_b));
			}
			
			_szTripleByte += " ]";
			
			DataSimulatorManager.logger.debug("[ Send Triplegen data. length : " + _byteArr.size() + " \t] " + _szTripleByte);
			
			
			return Utils.ByteTobyte(_btArr);
		}
		
		
		// 1. ���� ������ ����
		public byte[] GetGenerate(int _nSID,int _nTID, Long _lCurrentGen, Long _lAccuGen, int _nAlarmCode) {
			ArrayList<Byte> _byteArr = new ArrayList<Byte>();
			Byte[] _btArr = {};		
			
			Integer _nSysCurrent = 0;
			
			try {
			
			header.setSTX(Utils.byteToShort("$$SC".getBytes()));			
			header.setCMD("G".getBytes()[0]);
			header.setSI_ID(Utils.byteToShort(String.format("%04d",_nSID).getBytes()));
			header.setTR_ID(Utils.byteToShort(String.format("%03d",_nTID).getBytes()));
			
			body.setCMD((short)0x14);
			body.setSource((short)0x01);
			body.setType((short)0x02);
			body.setMultiCode((short)_nTID);
			body.setErrCode((short)0x00);
			
			_nSysCurrent = (int) (( _lCurrentGen / 380 ) / (Math.sqrt(3)));		
			
	 		body.setPV_V(((int)(_lCurrentGen / ThreadLocalRandom.current().nextInt(110,240))));
			body.setPV_A(body.getPV_V() == 0 ? 0 : (int)(_lCurrentGen / body.getPV_V()));
			body.setPV_Out(_lCurrentGen);

			body.setSysR_V(380);
			body.setSysS_V(380);
			body.setSysT_V(380);
			body.setSysR_A(_nSysCurrent);
			body.setSysS_A(_nSysCurrent);
			body.setSysT_A(_nSysCurrent);
			body.setCurrentGen(_lCurrentGen);
			body.setPF(1000);
			body.setHz(600);
			body.setAccuGen(_lAccuGen);
			body.setFaultCode(_nAlarmCode);
			
			foot.setCS(Utils.byteToShort("00".getBytes()));
			foot.setETX(Utils.byteToShort("\r\n".getBytes()));
			
			ArrayList<Short> arrList = new ArrayList<Short>();
			
			
			// added CS Range.
			
			
			for( Short _s : header.getSTX()) {
				arrList.add(_s);
			}
			arrList.add((short)0x2C);
			for(Short _s : header.getSI_ID()) {
				arrList.add(_s);
			}
			arrList.add((short)0x2C);
			for(Short _s : header.getTR_ID()) {
				arrList.add(_s);
			}
			arrList.add((short)0x2C);
			arrList.add(header.getCMD());
			arrList.add((short)0x2C);
			//
			
			
			arrList.add(body.getCMD());
			arrList.add(body.getSource());
			arrList.add(body.getType());
			arrList.add(body.getMultiCode());
			arrList.add(body.getErrCode());		
			
			arrList.add((short) (body.getPV_V() >> 8));		arrList.add((short) (body.getPV_V() & 0xFF));
			arrList.add((short) (body.getPV_A() >> 8));		arrList.add((short) (body.getPV_A() & 0xFF));
			arrList.add((short) (body.getPV_Out() >> 24));	arrList.add((short) ((body.getPV_Out() & Long.parseUnsignedLong("FFFFFF",16)) >> 16));	arrList.add((short)((body.getPV_Out() & 0xFFFF) >> 8));	arrList.add((short)(body.getPV_Out() & 0xFF));
			arrList.add((short) (body.getSysR_V() >> 8));	arrList.add((short) (body.getSysR_V() & 0xFF));
			arrList.add((short) (body.getSysS_V() >> 8));	arrList.add((short) (body.getSysS_V() & 0xFF));
			arrList.add((short) (body.getSysT_V() >> 8));	arrList.add((short) (body.getSysT_V() & 0xFF));
			arrList.add((short) (body.getSysR_A() >> 8));	arrList.add((short) (body.getSysR_A() & 0xFF));
			arrList.add((short) (body.getSysS_A() >> 8));	arrList.add((short) (body.getSysS_A() & 0xFF));
			arrList.add((short) (body.getSysT_A() >> 8));	arrList.add((short) (body.getSysT_A() & 0xFF));
			arrList.add((short) (body.getCurrentGen() >> 24));	arrList.add((short) ((body.getCurrentGen() & Long.parseUnsignedLong("FFFFFF",16)) >> 16));	arrList.add((short) ((body.getCurrentGen() & 0xFFFF) >> 8)); arrList.add((short) (body.getCurrentGen() & 0xFF));
			arrList.add((short) (body.getPF() >> 8));		arrList.add((short) (body.getPF() & 0xFF));
			arrList.add((short) (body.getHz() >> 8));		arrList.add((short) (body.getHz() & 0xFF));
			arrList.add((short) (body.getAccuGen() >> 56));	arrList.add((short) (((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFFFF",16)) >> 48)));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFF",16))  >> 40));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFF",16)) >> 32));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFF",16)) >> 24));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFF",16)) >> 16)); arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFF",16)) >> 8)); arrList.add((short) (body.getAccuGen() & 0xFF));
			arrList.add((short) (body.getFaultCode() >> 8));	arrList.add((short) (body.getFaultCode() & 0xFF));
			
			arrList.add((short)",".getBytes()[0]);
			
			Short[] _shCSArr = {};
			_shCSArr = arrList.toArray(_shCSArr);
			foot.setCS(Utils.byteToShort(String.format("%02x", Utils.CalculateCS( _shCSArr)).getBytes()) );
			
			
			
			// comment. 19-06-03
			//
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
			//
			
			
			for(short _s : arrList) {
				_byteArr.add((byte)_s );
			}
			
			//_byteArr.add((byte)0x2C);
			for(short _s : foot.getCS()) {
				_byteArr.add((byte)_s );
			}
			for(short _s : foot.getETX()) {
				_byteArr.add((byte)_s);
			}		
			
			//System.out.println("length : " + _byteArr.size());
			_btArr  = _byteArr.toArray(_btArr);
			
			String _szTripleByte = "[";
			
			for( byte _b :  Utils.ByteTobyte(_btArr)) {
				_szTripleByte += String.format(" %02X", Byte.toUnsignedInt(_b));
			}
			
			_szTripleByte += " ]";
			
			DataSimulatorManager.logger.debug("[ Send Triplegen data. length : " + _byteArr.size() + " \t] " + _szTripleByte);
			} 
			catch(Exception e) {
				e.printStackTrace();
				System.out.print("");
			}
			
			return Utils.ByteTobyte(_btArr);
		}
		
		// 3. ��������
		public byte[] GetGenerateSerial(int _nSID,int _nTID, int _nSerialCount) {
			ArrayList<Byte> _byteArr = new ArrayList<Byte>();
			Byte[] _btArr = {};		
			
			try {
			
			header.setSTX(Utils.byteToShort("$$SC".getBytes()));			
			header.setCMD("G".getBytes()[0]);
			header.setSI_ID(Utils.byteToShort(String.format("%04d",_nSID).getBytes()));
			header.setTR_ID(Utils.byteToShort(String.format("%03d",_nTID).getBytes()));
			
			body.setCMD((short)0x14);
			body.setSource((short)0x01);
			body.setType((short)0x02);
			body.setMultiCode((short)_nTID);
			body.setErrCode((short)0x00);		
			
	 		body.setPV_V(_nSerialCount);
			body.setPV_A(_nSerialCount);
			body.setPV_Out((long)_nSerialCount);

			body.setSysR_V(_nSerialCount);
			body.setSysS_V(_nSerialCount);
			body.setSysT_V(_nSerialCount);
			body.setSysR_A(_nSerialCount);
			body.setSysS_A(_nSerialCount);
			body.setSysT_A(_nSerialCount);
			body.setCurrentGen((long)_nSerialCount);
			body.setPF(_nSerialCount);
			body.setHz(_nSerialCount);
			body.setAccuGen((long)_nSerialCount);
			body.setFaultCode(_nSerialCount);
			
			foot.setCS(Utils.byteToShort("00".getBytes()));
			foot.setETX(Utils.byteToShort("\r\n".getBytes()));
			
			ArrayList<Short> arrList = new ArrayList<Short>();
			//change CS included range. 
			// added CS Range.
			
			
			for( Short _s : header.getSTX()) {
				arrList.add(_s);
			}
			arrList.add((short)0x2C);
			for(Short _s : header.getSI_ID()) {
				arrList.add(_s);
			}
			arrList.add((short)0x2C);
			for(Short _s : header.getTR_ID()) {
				arrList.add(_s);
			}
			arrList.add((short)0x2C);
			arrList.add(header.getCMD());
			arrList.add((short)0x2C);
			//
			
			arrList.add(body.getCMD());
			arrList.add(body.getSource());
			arrList.add(body.getType());
			arrList.add(body.getMultiCode());
			arrList.add(body.getErrCode());		
			
			arrList.add((short) (body.getPV_V() >> 8));		arrList.add((short) (body.getPV_V() & 0xFF));
			arrList.add((short) (body.getPV_A() >> 8));		arrList.add((short) (body.getPV_A() & 0xFF));
			arrList.add((short) (body.getPV_Out() >> 24));	arrList.add((short) ((body.getPV_Out() & Long.parseUnsignedLong("FFFFFF",16)) >> 16));	arrList.add((short)((body.getPV_Out() & 0xFFFF) >> 8));	arrList.add((short)(body.getPV_Out() & 0xFF));
			arrList.add((short) (body.getSysR_V() >> 8));	arrList.add((short) (body.getSysR_V() & 0xFF));
			arrList.add((short) (body.getSysS_V() >> 8));	arrList.add((short) (body.getSysS_V() & 0xFF));
			arrList.add((short) (body.getSysT_V() >> 8));	arrList.add((short) (body.getSysT_V() & 0xFF));
			arrList.add((short) (body.getSysR_A() >> 8));	arrList.add((short) (body.getSysR_A() & 0xFF));
			arrList.add((short) (body.getSysS_A() >> 8));	arrList.add((short) (body.getSysS_A() & 0xFF));
			arrList.add((short) (body.getSysT_A() >> 8));	arrList.add((short) (body.getSysT_A() & 0xFF));
			arrList.add((short) (body.getCurrentGen() >> 24));	arrList.add((short) ((body.getCurrentGen() & Long.parseUnsignedLong("FFFFFF",16)) >> 16));	arrList.add((short) ((body.getCurrentGen() & 0xFFFF) >> 8)); arrList.add((short) (body.getCurrentGen() & 0xFF));
			arrList.add((short) (body.getPF() >> 8));		arrList.add((short) (body.getPF() & 0xFF));
			arrList.add((short) (body.getHz() >> 8));		arrList.add((short) (body.getHz() & 0xFF));
			arrList.add((short) (body.getAccuGen() >> 56));	arrList.add((short) (((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFFFF",16)) >> 48)));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFF",16))  >> 40));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFF",16)) >> 32));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFF",16)) >> 24));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFF",16)) >> 16)); arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFF",16)) >> 8)); arrList.add((short) (body.getAccuGen() & 0xFF));
			arrList.add((short) (body.getFaultCode() >> 8));	arrList.add((short) (body.getFaultCode() & 0xFF));
			
			arrList.add((short)",".getBytes()[0]);
			
			Short[] _shCSArr = {};
			_shCSArr = arrList.toArray(_shCSArr);
			foot.setCS(Utils.byteToShort(String.format("%02X", Utils.CalculateCS( _shCSArr)).getBytes()) );
			
			
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
			for(short _s : arrList) {
				_byteArr.add((byte)_s );
			}
			
			//_byteArr.add((byte)0x2C);
			for(short _s : foot.getCS()) {
				_byteArr.add((byte)_s );
			}
			for(short _s : foot.getETX()) {
				_byteArr.add((byte)_s);
			}		
			
			//System.out.println("length : " + _byteArr.size());
			_btArr  = _byteArr.toArray(_btArr);
			
			String _szTripleByte = "[";
			
			for( byte _b :  Utils.ByteTobyte(_btArr)) {
				_szTripleByte += String.format(" %02X", Byte.toUnsignedInt(_b));
			}
			
			_szTripleByte += " ]";
			
			DataSimulatorManager.logger.debug("[ Send Triplegen data. length : " + _byteArr.size() + " \t] " + _szTripleByte);
			} 
			catch(Exception e) {
				e.printStackTrace();
				System.out.print("");
			}
			
			return Utils.ByteTobyte(_btArr);
		}
		
		public byte[] GetGenerateForAlarm(int _nSID, int _nTID, int _nAlarmCode) {
			ArrayList<Byte> _byteArr = new ArrayList<Byte>();
			Byte[] _btArr = {};		
			
			try {
			
			header.setSTX(Utils.byteToShort("$$SC".getBytes()));			
			header.setCMD("G".getBytes()[0]);
			header.setSI_ID(Utils.byteToShort(String.format("%04d",_nSID).getBytes()));
			header.setTR_ID(Utils.byteToShort(String.format("%03d",_nTID).getBytes()));
			
			body.setCMD((short)0x14);
			body.setSource((short)0x01);
			body.setType((short)0x02);
			body.setMultiCode((short)_nTID);
			body.setErrCode((short)0x00);		
			
	 		body.setPV_V(0);
			body.setPV_A(0);
			body.setPV_Out((long)0);

			body.setSysR_V(0);
			body.setSysS_V(0);
			body.setSysT_V(0);
			body.setSysR_A(0);
			body.setSysS_A(0);
			body.setSysT_A(0);
			body.setCurrentGen((long)0);
			body.setPF(0);
			body.setHz(0);
			body.setAccuGen((long)0);
			body.setFaultCode(_nAlarmCode);
			
			foot.setCS(Utils.byteToShort("00".getBytes()));
			foot.setETX(Utils.byteToShort("\r\n".getBytes()));
			
			ArrayList<Short> arrList = new ArrayList<Short>();
			//change CS included range. 
			// added CS Range.
			

			for( Short _s : header.getSTX()) {
				arrList.add(_s);
			}
			arrList.add((short)0x2C);
			for(Short _s : header.getSI_ID()) {
				arrList.add(_s);
			}
			arrList.add((short)0x2C);
			for(Short _s : header.getTR_ID()) {
				arrList.add(_s);
			}
			arrList.add((short)0x2C);
			arrList.add(header.getCMD());
			arrList.add((short)0x2C);
			//
			
			arrList.add(body.getCMD());
			arrList.add(body.getSource());
			arrList.add(body.getType());
			arrList.add(body.getMultiCode());
			arrList.add(body.getErrCode());		
			
			arrList.add((short) (body.getPV_V() >> 8));		arrList.add((short) (body.getPV_V() & 0xFF));
			arrList.add((short) (body.getPV_A() >> 8));		arrList.add((short) (body.getPV_A() & 0xFF));
			arrList.add((short) (body.getPV_Out() >> 24));	arrList.add((short) ((body.getPV_Out() & Long.parseUnsignedLong("FFFFFF",16)) >> 16));	arrList.add((short)((body.getPV_Out() & 0xFFFF) >> 8));	arrList.add((short)(body.getPV_Out() & 0xFF));
			arrList.add((short) (body.getSysR_V() >> 8));	arrList.add((short) (body.getSysR_V() & 0xFF));
			arrList.add((short) (body.getSysS_V() >> 8));	arrList.add((short) (body.getSysS_V() & 0xFF));
			arrList.add((short) (body.getSysT_V() >> 8));	arrList.add((short) (body.getSysT_V() & 0xFF));
			arrList.add((short) (body.getSysR_A() >> 8));	arrList.add((short) (body.getSysR_A() & 0xFF));
			arrList.add((short) (body.getSysS_A() >> 8));	arrList.add((short) (body.getSysS_A() & 0xFF));
			arrList.add((short) (body.getSysT_A() >> 8));	arrList.add((short) (body.getSysT_A() & 0xFF));
			arrList.add((short) (body.getCurrentGen() >> 24));	arrList.add((short) ((body.getCurrentGen() & Long.parseUnsignedLong("FFFFFF",16)) >> 16));	arrList.add((short) ((body.getCurrentGen() & 0xFFFF) >> 8)); arrList.add((short) (body.getCurrentGen() & 0xFF));
			arrList.add((short) (body.getPF() >> 8));		arrList.add((short) (body.getPF() & 0xFF));
			arrList.add((short) (body.getHz() >> 8));		arrList.add((short) (body.getHz() & 0xFF));
			arrList.add((short) (body.getAccuGen() >> 56));	arrList.add((short) (((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFFFF",16)) >> 48)));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFFFF",16))  >> 40));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFFFF",16)) >> 32));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFFFF",16)) >> 24));	arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFFFF",16)) >> 16)); arrList.add((short) ((body.getAccuGen() & Long.parseUnsignedLong("FFFF",16)) >> 8)); arrList.add((short) (body.getAccuGen() & 0xFF));
			arrList.add((short) (body.getFaultCode() >> 8));	arrList.add((short) (body.getFaultCode() & 0xFF));
			
			arrList.add((short)",".getBytes()[0]);
			
			Short[] _shCSArr = {};
			_shCSArr = arrList.toArray(_shCSArr);
			foot.setCS(Utils.byteToShort(String.format("%02X", Utils.CalculateCS( _shCSArr)).getBytes()) );
			
			
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
			for(short _s : arrList) {
				_byteArr.add((byte)_s );
			}
			
			//_byteArr.add((byte)0x2C);
			for(short _s : foot.getCS()) {
				_byteArr.add((byte)_s );
			}
			for(short _s : foot.getETX()) {
				_byteArr.add((byte)_s);
			}		
			
			//System.out.println("length : " + _byteArr.size());
			_btArr  = _byteArr.toArray(_btArr);
			
			String _szTripleByte = "[";
			
			for( byte _b :  Utils.ByteTobyte(_btArr)) {
				_szTripleByte += String.format(" %02X", Byte.toUnsignedInt(_b));
			}
			
			_szTripleByte += " ]";
			
			DataSimulatorManager.logger.debug("[ Send Triplegen data. length : " + _byteArr.size() + " \t] " + _szTripleByte);
			} 
			catch(Exception e) {
				e.printStackTrace();
				System.out.print("");
			}
			
			return Utils.ByteTobyte(_btArr);
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
