package com.DataCollector;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.Common.SecretInfo;


// please add commons-net-3.6.jar 

public class DataCollect_WeatherCollector extends Thread implements Closeable {
	
	FTPClient ftpClient;
	DataCollectDAO dataCollectDAO;
	
	int nPeriod = 1000 * 60;
	int result = -1;
	Date dtdlStart; 
	Date dtdlEnd;
	
	HashMap<Integer, HashMap<String, String>> WeatherData = new HashMap<Integer, HashMap<String,String>>();
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//super.run();
		
		WeatherCollector_ThreadWork();	
	}
	
	public DataCollect_WeatherCollector() {
		// TODO Auto-generated constructor stub
		
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("UTF-8");		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		try {
			interrupt();
			
			dataCollectDAO = null;
			ftpClient = null;
			
		} catch(Exception e) {
			DataCollectManager.logger.error("[ DataCollector ] Error occured while closing WeatherCollector :: " + e.toString());
		}
	}
	
	
	public void AreaCodeNameSetting(String[] _strArr) {
		
		_strArr[0] = "가평";
		_strArr[1] = "고양";
		_strArr[2] = "과천";
		_strArr[3] = "광명";
		_strArr[4] = "광주시";
		_strArr[5] = "구리";
		_strArr[6] = "군포";
		_strArr[7] = "김포";
		_strArr[8] = "남양주";
		_strArr[9] = "동두천";
		_strArr[10] = "부천";
		_strArr[11] = "서울";
		_strArr[12] = "성남";
		_strArr[13] = "수원";
		_strArr[14] = "시흥";
		_strArr[15] = "안산";
		_strArr[16] = "안성";
		_strArr[17] = "안양";
		_strArr[18] = "양주";
		_strArr[19] = "양평";
		_strArr[20] = "여주";
		_strArr[21] = "연천";
		_strArr[22] = "오산";
		_strArr[23] = "용인";
		_strArr[24] = "의왕";
		_strArr[25] = "의정부";
		_strArr[26] = "이천";
		_strArr[27] = "파주";
		_strArr[28] = "평택";
		_strArr[29] = "포천";
		_strArr[30] = "하남";
		_strArr[31] = "화성";
		_strArr[32] = "강화군";
		_strArr[33] = "옹진군";
		_strArr[34] = "인천";
		_strArr[35] = "울산";
		_strArr[36] = "울주";
		_strArr[37] = "광주";
		_strArr[38] = "대구";
		_strArr[39] = "달성";
		_strArr[40] = "대전";
		_strArr[41] = "부산";
		_strArr[42] = "기장군";
		_strArr[43] = "계룡";
		_strArr[44] = "공주";
		_strArr[45] = "금산";
		_strArr[46] = "논산";
		_strArr[47] = "당진";
		_strArr[48] = "보령";
		_strArr[49] = "부여";
		_strArr[50] = "서산";
		_strArr[51] = "서천";
		_strArr[52] = "아산";
		_strArr[53] = "예산";
		_strArr[54] = "천안";
		_strArr[55] = "청양";
		_strArr[56] = "태안";
		_strArr[57] = "홍성";
		_strArr[58] = "괴산";
		_strArr[59] = "단양";
		_strArr[60] = "보은";
		_strArr[61] = "영동";
		_strArr[62] = "옥천";
		_strArr[63] = "음성";
		_strArr[64] = "제천";
		_strArr[65] = "증평";
		_strArr[66] = "진천";
		_strArr[67] = "청원";
		_strArr[68] = "청주";
		_strArr[69] = "충주";
		_strArr[70] = "강진";
		_strArr[71] = "고흥";
		_strArr[72] = "곡성";
		_strArr[73] = "광양";
		_strArr[74] = "구례";
		_strArr[75] = "나주";
		_strArr[76] = "담양";
		_strArr[77] = "목포";
		_strArr[78] = "무안";
		_strArr[79] = "보성";
		_strArr[80] = "순천";
		_strArr[81] = "신안";
		_strArr[82] = "여수";
		_strArr[83] = "영광";
		_strArr[84] = "영암";
		_strArr[85] = "완도";
		_strArr[86] = "장성";
		_strArr[87] = "장흥";
		_strArr[88] = "진도";
		_strArr[89] = "함평";
		_strArr[90] = "해남";
		_strArr[91] = "화순";
		_strArr[92] = "고창";
		_strArr[93] = "군산";
		_strArr[94] = "김제";
		_strArr[95] = "남원";
		_strArr[96] = "무주";
		_strArr[97] = "부안";
		_strArr[98] = "순창";
		_strArr[99] = "완주";
		_strArr[100] = "익산";
		_strArr[101] = "임실";
		_strArr[102] = "장수";
		_strArr[103] = "전주";
		_strArr[104] = "정읍";
		_strArr[105] = "진안";
		_strArr[106] = "거제";
		_strArr[107] = "거창";
		_strArr[108] = "고성";
		_strArr[109] = "김해";
		_strArr[110] = "남해";
		_strArr[111] = "밀양";
		_strArr[112] = "사천";
		_strArr[113] = "산청";
		_strArr[114] = "양산";
		_strArr[115] = "의령";
		_strArr[116] = "진주";
		_strArr[117] = "창녕";
		_strArr[118] = "창원";
		_strArr[119] = "통녕";
		_strArr[120] = "하동";
		_strArr[121] = "함안";
		_strArr[122] = "함양";
		_strArr[123] = "합천";
		_strArr[124] = "경산";
		_strArr[125] = "경주";
		_strArr[126] = "고령";
		_strArr[127] = "구미";
		_strArr[128] = "군위";
		_strArr[129] = "김천";
		_strArr[130] = "문경";
		_strArr[131] = "봉화";
		_strArr[132] = "상주";
		_strArr[133] = "성주";
		_strArr[134] = "안동";
		_strArr[135] = "영덕";
		_strArr[136] = "영양";
		_strArr[137] = "영주";
		_strArr[138] = "영천";
		_strArr[139] = "예천";
		_strArr[140] = "울릉";
		_strArr[141] = "울진";
		_strArr[142] = "의성";
		_strArr[143] = "청도";
		_strArr[144] = "청송";
		_strArr[145] = "칠곡";
		_strArr[146] = "포항";
		_strArr[147] = "춘천";
		_strArr[148] = "강릉";
		_strArr[149] = "고성";
		_strArr[150] = "동해";
		_strArr[151] = "삼척";
		_strArr[152] = "속초";
		_strArr[153] = "양구";
		_strArr[154] = "양양";
		_strArr[155] = "영월";
		_strArr[156] = "원주";
		_strArr[157] = "인제";
		_strArr[158] = "정선";
		_strArr[159] = "철원";
		_strArr[160] = "태백";
		_strArr[161] = "평창";
		_strArr[162] = "홍천";
		_strArr[163] = "화천";
		_strArr[164] = "횡성";
		_strArr[165] = "세종시";
		_strArr[166] = "제주시";
		_strArr[167] = "서귀포시";
		
		DataCollectManager.logger.info("[ WeatherCollector ] AreaCodeNameSetting work done.");
	}
	
	private void WeatherCollector_ThreadWork() {
		DataCollectManager.logger.info("[ WeatherCollector ] Start WeatherCollector Thread working");
		DataCollectManager.logger.info("[ WeatherCollector ] First WeatherCollect will work within " + nPeriod/(1000*60) + " minute.");
		
		while(true) {			
			try {
				try {
				sleep(nPeriod);
				
				DataCollectManager.logger.info("[ WeatherCollector ] Start to gather data from FTP Server :: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dtdlStart = new Date()));
				
				
				
				if( WeatherCollector_ConnectFTP() ) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new java.util.Date());
					
					if((WeatherData = WeatherCollector_DownloadWData(SecretInfo.DCOLLECT_FILEPATH)) != null ) {						
						
						for(HashMap<String, String> _hsh : WeatherData.values()) {
							if(dataCollectDAO != null) {
								dataCollectDAO.DB_UpdateWeatherData(_hsh);
							}
							else {
								DataCollectManager.logger.error("[ WeatherCollector ] Failed insert Weather Data into DB Table. Please check dataCollecDAO member of WeatherCollector Instance has initialized properly.");
								break;
							}
						}
						
						nPeriod = 1000 * 60 * (60 - calendar.get(Calendar.MINUTE));
						//System.out.println("nPeriod is " +  nPeriod);
						
						WeatherCollector_DisconnectFTP();
						DataCollectManager.logger.info("[ WeatherCollector ] Finished data gathering successfully ! :: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dtdlEnd = new Date()) + " , Elapsed time :: " + new SimpleDateFormat("mm:ss.SSS").format(dtdlEnd.getTime() - dtdlStart.getTime()));
						System.out.println();
					}
					else {
						
						nPeriod = 1000 * 60 * (60 - calendar.get(Calendar.MINUTE));
						WeatherCollector_DisconnectFTP();
						DataCollectManager.logger.info("[ WeatherCollector ] Failed to Finished data gathering.. :: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dtdlEnd = new Date()) + " , Elapsed time :: " + new SimpleDateFormat("mm:ss.SSS").format(dtdlEnd.getTime() - dtdlStart.getTime()));
					}
				}
				} catch(InterruptedException ie) {
					DataCollectManager.logger.debug("[ WeatherCollector ] WeatherCollector has Stopped !");
					break;
				}
			} catch(Exception e) {
				DataCollectManager.logger.warn("Failed WeatherCollector_ThreadWork() Period work :: " + e.toString());
			}
			
			
		}
	}
	
	public void WeatherCollector_ConnectDBUseAsDAO(DataCollectDAO _dao) {
		dataCollectDAO = _dao;
	}
	
	private boolean WeatherCollector_ConnectFTP() {
		try {
			ftpClient.connect(SecretInfo.DCOLLECT_WEATHER_IP, SecretInfo.DCOLLECT_WEATHER_PORT);
			
			int resultCode = ftpClient.getReplyCode();
			if(FTPReply.isPositiveCompletion(resultCode) == false) {
				ftpClient.disconnect();
				
				DataCollectManager.logger.info("[ WeatherCollector ] Weather Server Connect.");
				return false;
			}
			
			else {
				ftpClient.setSoTimeout(5000);
				boolean isLogin = ftpClient.login(SecretInfo.DCOLLECT_WEATHER_ID, SecretInfo.DCOLLECT_WEATHER_PW);
				
				if(isLogin == false) {
					DataCollectManager.logger.warn("[ WeatherCollector ] Cannot login to Weather Server !");
					return false;
				}
				else {
					DataCollectManager.logger.info("[ WeatherCollector ] FTP login Successfully");
					ftpClient.enterLocalPassiveMode();					// ������ ���� ��ȸ, �ٿ�ε� ��ƾ ���� ����.
					
					return true;
				}
			}
		
		} catch(Exception e) {
			DataCollectManager.logger.warn("[ WeatherCollector ] Cannot connect to Weather Server !");
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean WeatherCollector_DisconnectFTP() {
		try {
		ftpClient.logout();		
		ftpClient.disconnect();
		
		return true;
		
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		finally {
			if(ftpClient != null && ftpClient.isConnected()) try { ftpClient.disconnect(); } catch(Exception e) {}
 		}
	}
	
	private synchronized HashMap<Integer, HashMap<String, String>> WeatherCollector_DownloadWData(String filePath) {
		HashMap<Integer, HashMap<String, String>> _WeatherData = new HashMap<Integer, HashMap<String,String>>();
		
		try {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.changeWorkingDirectory(filePath);
			
			FTPFile[] ftpFiles = ftpClient.listFiles("/point");
			
			//
			Collection<Integer> areaCodes = dataCollectDAO.DB_SelectWeather_AreaCode();
			
			if(ftpFiles != null) {
				for(FTPFile _f : ftpFiles) {
					
					int _nCode = 0;
					try {
					 _nCode = Integer.parseInt(_f.getName().split("\\.")[0]);
					} catch(NumberFormatException nfe) {
						break;
					}
					
					
					//
					if(areaCodes.contains(_nCode)) {
						System.out.println(_f.toString());
						
						ByteArrayOutputStream Bytearr_os = new ByteArrayOutputStream();					
						
						boolean isSuccess = ftpClient.retrieveFile(_f.getName(), Bytearr_os);
						
						if(isSuccess) {
							result = 1;
							System.out.println("[ WeatherCollector ] File download successfully.");
							
							System.out.println("[ WeatherCollector ] [Bytearr_os] :: " + Bytearr_os.toString("UTF-8").split("\n")[0]);
							
							String[] Data = Bytearr_os.toString("UTF-8").split("\n")[0].split(",");
							String locCode = _f.getName().split("\\.")[0];
							
							HashMap<String, String> _hsh = new HashMap<String, String>();
							_hsh.put("fileDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(_f.getTimestamp().getTime()));
							_hsh.put("locationCode",locCode);
							_hsh.put("Date", Data[0]);
							_hsh.put("WeatherCode",Data[1]);
							_hsh.put("Temp_Min", Data[2]);
							_hsh.put("Temp_Max", Data[3]);
							_hsh.put("PM10_Concent",Data[4]);
							_hsh.put("PM10_Grade",Data[5]);
							_hsh.put("PM25_Concent",Data[6]);
							_hsh.put("PM25_Grade",Data[7]);
							_hsh.put("O3_Concent",Data[8]);
							_hsh.put("O3_Grade",Data[9]);
							
							
							DataCollectManager.logger.debug(String.format("{ %s , %s , %s , %s , %s , %s , %s , %s , %s , %s , %s , %s } ", _hsh.get("fileDate"), _hsh.get("locationCode"),_hsh.get("Date"),_hsh.get("WeatherCode"),_hsh.get("Temp_Min"),_hsh.get("Temp_Max"),
									_hsh.get("PM10_Concent"),_hsh.get("PM10_Grade"),_hsh.get("PM25_Concent"),_hsh.get("PM25_Grade"),_hsh.get("O3_Concent"),_hsh.get("O3_Grade")));
							
							_WeatherData.put( Integer.parseInt(locCode),_hsh);				
							
							// when save as file
							//bos.close();
						}
						else {
							System.out.println("[ WeatherCollector ] failed to File download  !!!");
						}
					}
					
					else {
						System.out.println(_f.toString());
						
						ByteArrayOutputStream Bytearr_os = new ByteArrayOutputStream();					
						
						boolean isSuccess = ftpClient.retrieveFile(_f.getName(), Bytearr_os);
						
						if(isSuccess) {
							result = 1;
							System.out.println("[ WeatherCollector ] File download successfully");
							
							System.out.println("[ WeatherCollector ] [Bytearr_os] :: " + Bytearr_os.toString("UTF-8").split("\n")[0]);
							
							String[] Data = Bytearr_os.toString("UTF-8").split("\n")[0].split(",");
							String locCode = _f.getName().split("\\.")[0];
							
							HashMap<String, String> _hsh = new HashMap<String, String>();
							_hsh.put("fileDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(_f.getTimestamp().getTime()));
							_hsh.put("locationCode",locCode);
							_hsh.put("Date", Data[0]);
							_hsh.put("WeatherCode",Data[1]);
							_hsh.put("Temp_Min", Data[2]);
							_hsh.put("Temp_Max", Data[3]);
							_hsh.put("PM10_Concent",Data[4]);
							_hsh.put("PM10_Grade",Data[5]);
							_hsh.put("PM25_Concent",Data[6]);
							_hsh.put("PM25_Grade",Data[7]);
							_hsh.put("O3_Concent",Data[8]);
							_hsh.put("O3_Grade",Data[9]);
							
							dataCollectDAO.DB_InsertWeatherData(_hsh);			
							
							// when save as file
							//bos.close();
						}
						else {
							System.out.println("[ WeatherCollector ] failed to File download  !!!");
						}
					}
				}
				
				DataCollectManager.logger.info("[ WeatherCollector ] Weather FTP file listing done" );
			}
			
			return _WeatherData;
			
		} catch(Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
}
