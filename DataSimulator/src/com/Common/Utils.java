package com.Common;


import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Utils {
	
	public Utils() {
		
	}
	
	private static int[] CRCTable = 
		{	0x0000, 0xC0C1, 0xC181, 0x0140, 0xC301, 0x03C0, 0x0280, 0xC241,
			0xC601, 0xC6C0, 0x0780, 0xC741, 0x0500, 0xC5C1, 0xC481, 0x0440,
			0xCC01, 0x0CC0, 0x0D80, 0xCD41, 0x0F00, 0xCFC1, 0xCE81, 0x0E40,
			0x0A00, 0xCAC1, 0xDB81, 0x0B40, 0xC901, 0x09C0, 0x0880, 0xC841,
			0xD801, 0x18C0, 0x1980, 0xD941, 0x1B00, 0xDBC1, 0xDA81, 0x1A40,
			
			0x1E00, 0xDEC1, 0xDF81, 0x1F40, 0xDD01, 0x1DC0, 0x1C80, 0xDC41,
			0x1400, 0xD4C1, 0xD581, 0x1540, 0xD701, 0x17C0, 0x1680, 0xD641,
			0xD201, 0x12C0, 0x1380, 0xD341, 0x1100, 0xD1C1, 0xD081, 0x1040,
			0xF001, 0x30C0, 0x3180, 0xF141, 0x3300, 0xF3C1, 0xF281, 0x3240,
			0x3600, 0xF6C1, 0xF781, 0x3740, 0xF501, 0x35C0, 0x3480, 0xF441,
			
			0x3C00, 0xFCC1, 0xFD81, 0x3D40, 0xFF01, 0x3FC0, 0xE380, 0xFE41,
			0xFA01, 0x3AC0, 0x3B80, 0xFB41, 0x3900, 0xF9C1, 0xF881, 0x3840,
			0x2800, 0xE8C1, 0xE981, 0x2940, 0xEB01, 0x2BC0, 0x2A80, 0xEA41,
			0xEE01, 0x2EC0, 0x2F80, 0xEF41, 0xED00, 0xEDC1, 0xEC81, 0xEC40,
			0xE401, 0x24C0, 0x2580, 0xE541, 0x2700, 0xE7C1, 0xE681, 0x2640,
			
			0x2200, 0xE2C1, 0xE381, 0x2340, 0xE101, 0x21C0, 0x2080, 0xE041,
			0xA001, 0x60C0, 0x6180, 0xA141, 0x6300, 0xA3C1, 0xA281, 0x6240,
			0x6600, 0xA6C1, 0xA781, 0x6740, 0xA501, 0x65C0, 0x6480, 0xA441,
			0x6C00, 0xACC1, 0xAD81, 0x6D40, 0xAF01, 0x6FC0, 0x6E80, 0xAE41,
			0xAA01, 0x6AC0, 0x6B80, 0xAB41, 0x6900, 0xA9C1, 0xA881, 0x9840,
			
			0x7800, 0xB8C1, 0xB981, 0x7940, 0xBB01, 0x7BC0, 0x7A80, 0xBA41,
			0xBE01, 0x7EC0, 0x7F80, 0xBF41, 0x7D00, 0xBDC1, 0xBC81, 0x7C40,
			0xB401, 0x74C0, 0x7580, 0xB541, 0x7700, 0xB7C1, 0xB681, 0x7640,
			0x7200, 0xB2C1, 0xB381, 0x7340, 0xB101, 0x71C0, 0x7080, 0xB041,
			0x5000, 0x90C1, 0x9181, 0x5140, 0x9301, 0x53C0, 0x5280, 0x9241,
			
			0x9601, 0x56C0, 0x5780, 0x9741, 0x5500, 0x95C1, 0x9481, 0x5440,
			0x9C01, 0x5CC0, 0x5D80, 0x9D41, 0x5F00, 0x9FC1, 0x9E81, 0x5E40,
			0x5A00, 0x9AC1, 0x9B81, 0x5B40, 0x9901, 0x59C0, 0x5880, 0x9841,
			0x8801, 0x48C0, 0x4980, 0x8941, 0x4B00, 0x8BC1, 0x8A81, 0x4A40,
			0x4E00, 0x8EC1, 0x8F81, 0x4F40, 0x8D01, 0x4DC0, 0x8C80, 0x8C41,
			
			0x4400, 0x84C1, 0x8581, 0x4540, 0x8701, 0x47C0, 0x4580, 0x8641,
			0x8201, 0x42C0, 0x4380, 0x8341, 0x4100, 0x81C1, 0x8081, 0x4040
		};
		
		public static boolean CRC_Check(int[] packet) {		
			int now = 0;
			int prev = 0xFFFF;
			int high;
			int low;
			
			for(int i = 0; i < packet.length - 2; i++) // 2 : ��Ŷ���� ���� ���� CRC �� ( High , Low )
			{
				 now = packet[i] ^ prev;
				 
				 System.out.println("[" + i + "] now practice 1 is" + now);
				
				 prev = now = CRCTable[(now & 0xFF)] ^ (now >> 8);
				 
				 System.out.println("[" + i + "] prev practice 1 is" + prev);
				 System.out.println("[" + i + "] now practice 2 is" + now);
			}
			
			high = (now & 0xFF);
			low = (now >> 8);
			
			 System.out.format("Packet CRC H: %02X   L: %02X%n",packet[packet.length-2],packet[packet.length-1]);
			 System.out.format("CRC_Check  H: %02X   L: %02X%n",high,low);
		
			 return (packet[packet.length-2] == high ? 
				 		packet[packet.length-1] == low ? true:false 
				 				: false);  
		}
		
	@SuppressWarnings({ "unchecked", "unused" })
	public static <T> Object[] split(T[] _target, T regex) {
		/* Not Completed */
		
	int regexCnt = 0;
		
		for(T t : _target) 
		{
		 if(_target.equals(t)) regexCnt++;
		}
		
		Collection<T[]> t =  new ArrayList<T[]>(); //Array.newInstance(Array.newInstance(regex.getClass().getClass(), regexCnt+1).getClass(), );
		Collection<T> splt = new ArrayList<T>();
		
		for(int i = 0 ; i < _target.length; i++) 
		{	
			if(_target[i].equals(regex)) 
			{
				
				splt.remove(regex);
				t.add((T[])splt.toArray());
				
				splt.clear();		
			}
			
			splt.add(_target[i]);
		}
		
		t.add((T[])splt.toArray());
		
		return t.toArray();
	}
	
	@SuppressWarnings({ "unchecked", "null" })
	public static <T,E> T[] ConvertArrayType(E[] _src, Class<T> _type) 
	{
		/* Not Completed */
		
		//ArrayList<T> e = new ArrayList<T>();
		//Array.newInstance(_type, 10);
		//T[] t = Array.newInstance(_dest.getClass() , Array.getLength(_src));
		try {
			T[] t = (T[])Array.newInstance(Object.class , _src.length);
			short[] _sh = (short[])Array.newInstance(short.class, _src.length);
		
		
			for(int i = 0; i < Array.getLength(_src); i++) 
			{	
				t[i] =  (T)_src[i];
			}
			return t;
		} catch(Exception _e) {
			System.out.println(_e.toString());
			return null;
		}
		
		
	}
	
	public static Integer[] byteToInteger(byte[] _target) {
		
		Integer[] e = (Integer[])Array.newInstance(Integer.class , _target.length); 
		
		for(int i = 0; i < _target.length ; i++) {
			e[i] = (int)_target[i];
		}
		
		return e;
	}
	
	public static short[] byteToShort(byte[] _target) {
	short[] e = (short[])Array.newInstance(short.class, _target.length);

	for(int i = 0; i < _target.length ; i++) {
		e[i] = _target[i];
	}
	
	return e;
	}

	public static byte[] ByteTobyte(Byte[] _target) {
	byte[] e = (byte[])Array.newInstance(byte.class, _target.length);
	
	for(int i = 0; i < _target.length; i++) {
		e[i] = _target[i];
	}
	
	return e;
	}
	
	public static byte[] ShortTobyte(Short[] _target) {
		byte[] e = (byte[])Array.newInstance(byte.class, _target.length);
		
		for(int i = 0; i < _target.length; i++) {
			e[i] = (byte)(short)_target[i];
		}
		
		return e;
	}
	
	public static Byte[] ArrObjToByte(Object[] _target) {
		Byte[] e = (Byte[])Array.newInstance(Byte.class, _target.length);
		
		for(int i = 0; i < _target.length; i++) {
			e[i] = (byte)(short)_target[i];
		}
		
		return e;
	}
	
	public static <T> String ConvertString(T[] _target) 
	{
		String str = new String();
		
		for(T t : _target) 
		{
			str += String.format("%c", t);
		}
		
		return str;
	}
	
	public static int CalculateCS(Integer[] _packet) {
		int _nCS = 0;
			
		for(Integer _i : _packet) {
			_nCS ^= _i;
		}
		
		return _nCS;
	}
	
	public static int CalculateCS(short[] _packet) {
		int _nCS = 0;
		
		
		//Object[] _nArr = split(_packet, 0x2C);
		
		
		for(Short _i : _packet) {
			_nCS ^= _i;
		}
		
		return _nCS;
	}
	
	public static int CalculateCS(Short[] _packet) {
		int _nCS = 0;
		
		
		//Object[] _nArr = split(_packet, 0x2C);
		
		
		for(Short _i : _packet) {
			_nCS ^= _i;
		}
		
		return _nCS;
	}
	
	public static int CalculateCS(Object[] _packet) {
		int _nCS = 0;
			
		for(Object _i : _packet) {
			_nCS ^= (short)_i;
		}
		
		return _nCS;
	}
	
	public static int Minimum(int... n) {

		int min = n[0];
		
		for(int i : n) 
		{
			min = (min > i) ? i:min;
		}
		
		return min;
	}
	
	public static int Maximum(int... n) {

		int max = n[0];
		
		for(int i : n) 
		{
			max = (max > i) ? max : i;
		}
		
		return max;
	}
	
	public static <E> boolean isInRange(float _value,float _max, float _min) {
		
		if(_value >= _min && _value <= _max)
			return true;
		else 
		return false;
	}
	
	public static <K,V> K getKeyFromValue(ConcurrentHashMap<K, V> _hashmap, V _value) {
		for(Map.Entry<K, V> _e : _hashmap.entrySet()) {
			if(_e.getValue().equals(_value))
				return _e.getKey();
		}
		
		return null;
	}
	
	public static byte[] EncryptWSKey(String _key) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update((_key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes());
			
			byte byteData[] = md.digest();		// -- sha1
			
//			for(byte _b : byteData) {
//				System.out.print(Integer.toString((_b & 0xFF) + 0x100, 16).substring(1)); 
//			}
			System.out.println();
			
			byte byteData2[] = Base64.getEncoder().encode(byteData);
			
				//System.out.print(new String(byteData2)); 
				return byteData2; 
			} catch(Exception e) {
				e.printStackTrace();
				
				return null;
			}
	}
}
