package com.DataCollector;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.Common.CustomQueue;
import com.Common.SMTPSender;
import com.Common.SecretInfo;
import com.Common.Utils;

public class DataCollectDAO implements Closeable {

	private final String DB_IP = SecretInfo.DCOLLECT_IP;
	private final int DB_Port = SecretInfo.DCOLLECT_PORT;
	private final String DB_USER = SecretInfo.DCOLLECT_ID;
	private final String DB_PW = SecretInfo.DCOLLECT_PW;
	private final String DB_DBName = SecretInfo.DCOLLECT_DBNAME;
	private String DB_DBConnectStr = String.format("jdbc:mysql://%s:%d/%s?serverTimezone=UTC", DB_IP, DB_Port,
			DB_DBName);

	private Connection mysql;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Integer>> tGroupOfPrevError;

	private CustomQueue<Object> PopQueue; // Protocol Class �뜝�룞�삕�뜝�룞�삕
	private int nQueryCount = 0;
	private boolean bThreadIsRun = true;

	public DataCollectDAO() {
		try {
			tGroupOfPrevError = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Integer>>();

		} catch (Exception e) {
			DataCollectManager.logger.error("[ DataCollector ] Failed to Initialize DB Connector :: " + e.toString());
		}
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		try {
			tGroupOfPrevError.clear();
			tGroupOfPrevError = null;

			bThreadIsRun = false;
			PopQueue = null;

			if (!rs.isClosed()) {
				rs.close();
				rs = null;
			}

			if (!pstmt.isClosed()) {
				pstmt.close();
				pstmt = null;
			}

			if (!mysql.isClosed()) {
				mysql.close();
				mysql = null;
			}
		} catch (Exception e) {
			DataCollectManager.logger
					.error("[ DataCollector ] Error occured while closing DataCollectDAO :: " + e.toString());
		}
	}

	public boolean DB_Connect(String _szDBIP, String _szDBPort, String _szDBID, String _szDBPW, String _szDBName) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			if (_szDBIP != "" && _szDBPort != "" && _szDBID != "" && _szDBPW != "" && _szDBName != "") {
				try {
					DataCollectManager.logger.debug("[ DataCollector ] Set DB Connect as Custom Setting");
					DB_DBConnectStr = String.format("jdbc:mysql://%s:%d/%s?serverTimezone=UTC", _szDBIP,
							Integer.parseInt(_szDBPort), _szDBName);
				} catch (NumberFormatException nfe) {
					DataCollectManager.logger
							.warn("[ DataCollector ] DataCollectManager DB Connect Parsing Error occured ! : "
									+ nfe.toString() + "\n" + "Set initial value automatically");
					DB_DBConnectStr = String.format("jdbc:mysql://%s:%d/%s?serverTimezone=UTC", _szDBIP, 3306,
							_szDBName);
				}

				mysql = DriverManager.getConnection(DB_DBConnectStr, _szDBID, _szDBPW);

				return true;
			}

			else {
				mysql = DriverManager.getConnection(DB_DBConnectStr, DB_USER, DB_PW);

				return true;

			}

		} catch (Exception e) {
			DataCollectManager.logger.error("[ DataCollector ] Failed to Connect Database :: " + e.toString());
			return false;
		}
	}

	public void DB_Disconnect() {
		try {
			rs.close();
			pstmt.close();
			mysql.close();
		} catch (Exception e) {
			DataCollectManager.logger.error("[ DataCollector ] Failed to disconnect Database :: " + e.toString());
		}
	}

	public void DB_StartThread() {
		Thread thd = new Thread(new Runnable() {
			@Override
			public void run() {
				DB_ThreadRun();
			}
		});

		thd.start();
	}

	private void DB_ThreadRun() {
		while (bThreadIsRun) {
			try {

				// synchronized(PopQueue) {
				if (!PopQueue.isEmpty()) {
					// 19-05-24 commented.
					// DataCollectManager.logger.debug("Protocol Queue Count : " +
					// PopQueue.IndexOfRear());

					DB_InsertGeneration((DataCollect_Protocol) PopQueue.Dequeue());
				}
				// }

				Thread.sleep(1);
			} catch (Exception e) {
				DataCollectManager.logger
						.error("[ DataCollector ] Failed to Insert Data into Database :: " + e.toString());
			}
		}
	}

	///////////////////////////////////////////
	/// 1. SELECT Statements ///
	///////////////////////////////////////////

	public HashMap<Integer, String> DB_SelectGroupInfo(boolean isSelectRecent) {
		HashMap<Integer, String> _h = new HashMap<Integer, String>();
		String _sql = "";

		try {
			_sql = isSelectRecent ? "select T_Group_ID, T_Group_IP from T_GROUP ORDER BY T_Group_Upt_Date DESC LIMIT 5"
					: "select T_Group_ID, T_Group_IP from T_GROUP ORDER BY T_Group_ID ASC";

			pstmt = mysql.prepareStatement(_sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				_h.put(rs.getInt(1), rs.getString(2));
			}
			System.out.println("[ DataCollector ] DB_SelectGroupInfo work done!");
			return _h;

		} catch (Exception e) {
			DataCollectManager.logger.error("Failed to DBSelectGroupInfo ! : " + e.toString());
			return null;
		}
	}

	public Collection<Integer> DB_SelectWeather_AreaCode() {
		String _sql = "";
		ResultSet _rs;
		Collection<Integer> _res = new ArrayList<Integer>();

		_sql = "select distinct Area_code from group_weather";

		try {
			_rs = mysql.createStatement().executeQuery(_sql);

			while (_rs.next()) {
				_res.add(_rs.getInt(1));
			}

			return _res;

		} catch (Exception e) {
			DataCollectManager.logger.error("[ DataCollector ] Failed to Select WAreaCode ! : " + e.toString());

			return null;
		}
	}

	public ConcurrentHashMap<Integer, String> DB_SelectGroupMode() {
		ConcurrentHashMap<Integer, String> _h = new ConcurrentHashMap<Integer, String>();
		String _sql = "";

		try {
			_sql = "SELECT T_Group_ID, T_Group_Mode from T_GROUP ORDER BY T_Group_ID ASC";

			pstmt = mysql.prepareStatement(_sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				_h.put(rs.getInt(1), rs.getString(2));
			}
			System.out.println("[ DataCollector ] DB_SelectGroupMode work done!");
			return _h;

		} catch (Exception e) {
			DataCollectManager.logger.error("Failed to DBSelectGroupInfo ! : " + e.toString());
			return null;
		}
	}

	public ArrayList<String> DB_SelectGroupEmail(int _SIID) {
		String _sql = "";

		ArrayList<String> _emailList = new ArrayList<String>();
		try {
			_sql = String.format(
					"SELECT user_email from user WHERE user_id in (SELECT user_id FROM user_t_g WHERE t_group_id = ?)");

			pstmt = mysql.prepareStatement(_sql);
			pstmt.setInt(1, _SIID);

			rs = pstmt.executeQuery();

			// System.out.println("DB_SelectGroupMail work done!");

			while (rs.next()) {
				_emailList.add(rs.getString(1));
			}

		} catch (Exception e) {
			DataCollectManager.logger.error("Failed to DB_SelectGroupEmail ! : " + e.toString());
		}

		return _emailList;
	}

	///////////////////////////////////////////
	/// 2. INSERT Statements ///
	///////////////////////////////////////////

	public void DB_InsertWeatherData(HashMap<String, String> _hsh) {
		String _sql = "";
		PreparedStatement _pstmt;

		_sql = "Insert into group_Weather(Weather_Date, weatherCode , area_name, area_Code, Weather_T_min, Weather_T_max, Weather_Finedust_concentration, Weather_Finedust_grade, Weather_Ultrafinedust_concentration, Weather_Ultrafinedust_grade, Weather_ozone_concentration, Weather_ozone_grade) Values(?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			_pstmt = mysql.prepareStatement(_sql);

			// original code
			// try { _pstmt.setString(1, _hsh.get("fileDate")); } catch(SQLException se) {
			// _pstmt.setString(1, _hsh.get("fileDate")); }
			// try { _pstmt.setInt(2, Integer.parseInt(_hsh.get("WeatherCode"))); }
			// catch(NumberFormatException nfe) { _pstmt.setInt(2, 0); }
			// try { _pstmt.setString(3, DataCollectManager.AreaCodeNameArr[
			// Integer.parseInt(_hsh.get("locationCode")) ]); } catch(NumberFormatException
			// nfe) { _pstmt.setString(3, ""); }
			// try { _pstmt.setInt(4, Integer.parseInt(_hsh.get("locationCode"))); }
			// catch(SQLException se) { _pstmt.setInt(4, 999); }
			// try { _pstmt.setInt(5, Integer.parseInt(_hsh.get("Temp_Min"))); }
			// catch(NumberFormatException nfe) { _pstmt.setInt(5, 0); }
			// try { _pstmt.setInt(6, Integer.parseInt(_hsh.get("Temp_Max"))); }
			// catch(NumberFormatException nfe) { _pstmt.setInt(6, 0); }
			// try { _pstmt.setInt(7, Integer.parseInt(_hsh.get("PM10_Concent"))); }
			// catch(NumberFormatException nfe) { _pstmt.setInt(7, 0); }
			// try { _pstmt.setString(8, _hsh.get("PM10_Grade")); } catch(SQLException se) {
			// _pstmt.setString(8, ""); }
			// try { _pstmt.setInt(9, Integer.parseInt(_hsh.get("PM25_Concent"))); }
			// catch(NumberFormatException nfe) { _pstmt.setInt(9, 0); }
			// try { _pstmt.setString(10, _hsh.get("PM25_Grade")); } catch(SQLException se)
			// { _pstmt.setString(10, ""); }
			// try { _pstmt.setFloat(11, Float.parseFloat(_hsh.get("O3_Concent"))); }
			// catch(NumberFormatException nfe) { _pstmt.setFloat(11, 0.000f); }
			// try { _pstmt.setString(12, _hsh.get("O3_Grade")); } catch(SQLException se) {
			// _pstmt.setString(12, ""); }

			// NULL care , 190621
			try {
				if (!_hsh.get("fileDate").equals("-"))
					_pstmt.setString(1, _hsh.get("fileDate"));
				else
					_pstmt.setNull(1, Types.VARCHAR);
			} catch (SQLException se) {
				_pstmt.setString(1, _hsh.get("fileDate"));
			}
			try {
				if (!_hsh.get("WeatherCode").equals("-"))
					_pstmt.setInt(2, Integer.parseInt(_hsh.get("WeatherCode")));
				else
					_pstmt.setNull(2, Types.INTEGER);
			} catch (NumberFormatException nfe) {
				_pstmt.setInt(2, 0);
			}
			try {
				_pstmt.setString(3, DataCollectManager.AreaCodeNameArr[Integer.parseInt(_hsh.get("locationCode"))]);
			} catch (NumberFormatException nfe) {
				_pstmt.setString(3, "");
			}
			try {
				if (!_hsh.get("locationCode").equals("-"))
					_pstmt.setInt(4, Integer.parseInt(_hsh.get("locationCode")));
				else
					_pstmt.setNull(4, Types.INTEGER);
			} catch (SQLException se) {
				_pstmt.setInt(4, 999);
			}
			try {
				if (!_hsh.get("Temp_Min").equals("-"))
					_pstmt.setInt(5, Integer.parseInt(_hsh.get("Temp_Min")));
				else
					_pstmt.setNull(5, Types.INTEGER);
			} catch (NumberFormatException nfe) {
				_pstmt.setInt(5, 0);
			}
			try {
				if (!_hsh.get("Temp_Max").equals("-"))
					_pstmt.setInt(6, Integer.parseInt(_hsh.get("Temp_Max")));
				else
					_pstmt.setNull(6, Types.INTEGER);
			} catch (NumberFormatException nfe) {
				_pstmt.setInt(6, 0);
			}
			try {
				if (!_hsh.get("PM10_Concent").equals("-"))
					_pstmt.setInt(7, Integer.parseInt(_hsh.get("PM10_Concent")));
				else
					_pstmt.setNull(7, Types.INTEGER);
			} catch (NumberFormatException nfe) {
				_pstmt.setInt(7, 0);
			}
			try {
				if (!_hsh.get("PM10_Grade").equals("-"))
					_pstmt.setString(8, _hsh.get("PM10_Grade"));
				else
					_pstmt.setNull(8, Types.VARCHAR);
			} catch (SQLException se) {
				_pstmt.setString(8, "");
			}
			try {
				if (!_hsh.get("PM25_Concent").equals("-"))
					_pstmt.setInt(9, Integer.parseInt(_hsh.get("PM25_Concent")));
				else
					_pstmt.setNull(9, Types.INTEGER);
			} catch (NumberFormatException nfe) {
				_pstmt.setInt(9, 0);
			}
			try {
				if (!_hsh.get("PM25_Grade").equals("-"))
					_pstmt.setString(10, _hsh.get("PM25_Grade"));
				else
					_pstmt.setNull(10, Types.VARCHAR);
			} catch (SQLException se) {
				_pstmt.setString(10, "");
			}
			try {
				if (!_hsh.get("O3_Concent").equals("-"))
					_pstmt.setFloat(11, Float.parseFloat(_hsh.get("O3_Concent")));
				else
					_pstmt.setNull(11, Types.FLOAT);
			} catch (NumberFormatException nfe) {
				_pstmt.setFloat(11, 0.000f);
			}
			try {
				if (!_hsh.get("O3_Grade").equals("-"))
					_pstmt.setString(12, _hsh.get("O3_Grade"));
				else
					_pstmt.setNull(12, Types.VARCHAR);
			} catch (SQLException se) {
				_pstmt.setString(12, "");
			}
			//

			DB_InsertQuery(_pstmt);
			// 1순위 : Date
			// 2순위 : File Date
			// 3순위 : System Time

		} catch (Exception e) {
			DataCollectManager.logger.error("[ DataCollector ] Failed to insert WeatherData !! : " + e.toString());
		}
	}

	public void DB_UpdateWeatherData(HashMap<String, String> _hsh) {
		String _sql = "";
		PreparedStatement _pstmt;

		_sql = "Update group_Weather set Weather_Date = ?, weatherCode = ?, Weather_T_min = ?, Weather_T_max = ?, Weather_Finedust_concentration = ?, Weather_Finedust_grade = ?, Weather_Ultrafinedust_concentration = ?, Weather_Ultrafinedust_grade = ?, Weather_ozone_concentration = ?, Weather_ozone_grade = ? where Area_code = ?";
		try {
			_pstmt = mysql.prepareStatement(_sql);
			try {
				if (!_hsh.get("fileDate").equals("-"))
					_pstmt.setString(1, _hsh.get("fileDate"));
				else
					_pstmt.setNull(1, Types.VARCHAR);
			} catch (SQLException se) {
				_pstmt.setString(1, _hsh.get("fileDate"));
			}
			try {
				if (!_hsh.get("WeatherCode").equals("-"))
					_pstmt.setInt(2, Integer.parseInt(_hsh.get("WeatherCode")));
				else
					_pstmt.setNull(2, Types.INTEGER);
			} catch (NumberFormatException nfe) {
				_pstmt.setInt(2, 0);
			}
			try {
				if (!_hsh.get("Temp_Min").equals("-"))
					_pstmt.setInt(3, Integer.parseInt(_hsh.get("Temp_Min")));
				else
					_pstmt.setNull(3, Types.INTEGER);
			} catch (NumberFormatException nfe) {
				_pstmt.setInt(3, 0);
			}
			try {
				if (!_hsh.get("Temp_Max").equals("-"))
					_pstmt.setInt(4, Integer.parseInt(_hsh.get("Temp_Max")));
				else
					_pstmt.setNull(4, Types.INTEGER);
			} catch (NumberFormatException nfe) {
				_pstmt.setInt(4, 0);
			}
			try {
				if (!_hsh.get("PM10_Concent").equals("-"))
					_pstmt.setInt(5, Integer.parseInt(_hsh.get("PM10_Concent")));
				else
					_pstmt.setNull(5, Types.INTEGER);
			} catch (NumberFormatException nfe) {
				_pstmt.setInt(5, 0);
			}
			try {
				if (!_hsh.get("PM10_Grade").equals("-"))
					_pstmt.setString(6, _hsh.get("PM10_Grade"));
				else
					_pstmt.setNull(6, Types.VARCHAR);
			} catch (SQLException se) {
				_pstmt.setString(6, "");
			}
			try {
				if (!_hsh.get("PM25_Concent").equals("-"))
					_pstmt.setInt(7, Integer.parseInt(_hsh.get("PM25_Concent")));
				else
					_pstmt.setNull(7, Types.INTEGER);
			} catch (NumberFormatException nfe) {
				_pstmt.setInt(7, 0);
			}
			try {
				if (!_hsh.get("PM25_Grade").equals("-"))
					_pstmt.setString(8, _hsh.get("PM25_Grade"));
				else
					_pstmt.setNull(8, Types.VARCHAR);
			} catch (SQLException se) {
				_pstmt.setString(8, "");
			}
			try {
				if (!_hsh.get("O3_Concent").equals("-"))
					_pstmt.setFloat(9, Float.parseFloat(_hsh.get("O3_Concent")));
				else
					_pstmt.setNull(9, Types.FLOAT);
			} catch (NumberFormatException nfe) {
				_pstmt.setFloat(9, 0.000f);
			}
			try {
				if (!_hsh.get("O3_Grade").equals("-"))
					_pstmt.setString(10, _hsh.get("O3_Grade"));
				else
					_pstmt.setNull(10, Types.VARCHAR);
			} catch (SQLException se) {
				_pstmt.setString(10, "");
			}
			try {
				if (!_hsh.get("locationCode").equals("-"))
					_pstmt.setInt(11, Integer.parseInt(_hsh.get("locationCode")));
				else
					_pstmt.setNull(11, Types.INTEGER);
			} catch (SQLException se) {
				_pstmt.setInt(11, 999);
			}

			DB_UpdateQuery(_pstmt);
			// 1순위 : Date
			// 2순위 : File Date
			// 3순위 : System Time

		} catch (Exception e) {
			DataCollectManager.logger.error("[ DataCollector ] Failed to insert WeatherData !! : " + e.toString());
		}
	}

	public void DB_InsertGeneration(DataCollect_Protocol _protocol) {

		String _sql = "";
		PreparedStatement _pstmt1;

		switch (_protocol.GetData().getClass().getName()) {
			case "com.DataCollector.DataCollect_Protocol_SingleGen":
				DataCollect_Protocol_SingleGen psg = (DataCollect_Protocol_SingleGen) _protocol.GetData();

				_sql = "Insert into Inverter_Data(T_Group_ID,Inverter_Data_ID, Inverter_Data_PV_V_AVG, Inverter_Data_PV_I_SUM, Inverter_Data_PV_Output, Inverter_Data_System_R_V, Inverter_Data_System_R_I, Inverter_Data_Output, Inverter_Data_PF, Inverter_Data_HZ, Inverter_Data_Accu_Energy, Inverter_Data_Trouble) Values(?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				try {
					_pstmt1 = mysql.prepareStatement(_sql);
					_pstmt1.setInt(1,
							((1000 * (psg.GetHeader().getSI_ID()[0] - '0'))
									+ ((100 * (psg.GetHeader().getSI_ID()[1] - '0'))
											+ (10 * (psg.GetHeader().getSI_ID()[2] - '0'))
											+ (psg.GetHeader().getSI_ID()[3] - '0'))));
					_pstmt1.setInt(2, ((100 * (psg.GetHeader().getTR_ID()[0] - '0'))
							+ (10 * (psg.GetHeader().getTR_ID()[1] - '0')) + (psg.GetHeader().getTR_ID()[2] - '0')));
					_pstmt1.setFloat(3, psg.GetBody().getPV_V());
					_pstmt1.setFloat(4, psg.GetBody().getPV_A());
					_pstmt1.setFloat(5, psg.GetBody().getPV_Out());
					_pstmt1.setFloat(6, psg.GetBody().getSys_V());
					_pstmt1.setFloat(7, psg.GetBody().getSys_A());
					_pstmt1.setFloat(8, psg.GetBody().getCurrentGen());
					_pstmt1.setFloat(9, psg.GetBody().getPF());
					_pstmt1.setFloat(10, psg.GetBody().getHZ());
					_pstmt1.setFloat(11, psg.GetBody().getAccuGen());
					_pstmt1.setInt(12, psg.GetBody().getFaultCode());

					if (DB_InsertQuery(_pstmt1)) {

						ProcessAlarm(_protocol);
						nQueryCount++;
					}

				} catch (Exception e) {
					DataCollectManager.logger
							.warn("[ DataCollector ] Failed to Create Insert Statement for Single Generation :: "
									+ e.toString() + "\r\n [ stmt : " + _sql + " ]");
				}
				break;
			case "com.DataCollector.DataCollect_Protocol_TripleGen":

				DataCollect_Protocol_TripleGen ptg = (DataCollect_Protocol_TripleGen) _protocol.GetData();

				_sql = "Insert into Inverter_Data(T_Group_ID, Inverter_Data_ID , Inverter_Data_PV_V_AVG, Inverter_Data_PV_I_SUM, Inverter_Data_PV_Output, Inverter_Data_System_R_V, Inverter_Data_System_S_V, Inverter_Data_System_T_V, Inverter_Data_System_R_I, Inverter_Data_System_S_I, Inverter_Data_System_T_I, Inverter_Data_Output, Inverter_Data_PF, Inverter_Data_HZ, Inverter_Data_Accu_Energy, Inverter_Data_Trouble) Values(?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				try {
					_pstmt1 = mysql.prepareStatement(_sql);
					_pstmt1.setInt(1, ((ptg.GetHeader().getSI_ID()[0] - '0') * 1000)
							+ ((ptg.GetHeader().getSI_ID()[1] - '0') * 100)
							+ ((ptg.GetHeader().getSI_ID()[2] - '0') * 10) + (ptg.GetHeader().getSI_ID()[3] - '0'));
					_pstmt1.setInt(2, (100 * (ptg.GetHeader().getTR_ID()[0] - '0'))
							+ (10 * (ptg.GetHeader().getTR_ID()[1] - '0')) + (ptg.GetHeader().getTR_ID()[2] - '0'));
					_pstmt1.setFloat(3, ptg.GetBody().getPV_V());
					_pstmt1.setFloat(4, ptg.GetBody().getPV_A());
					_pstmt1.setLong(5, ptg.GetBody().getPV_Out());
					_pstmt1.setFloat(6, ptg.GetBody().getSysR_V());
					_pstmt1.setFloat(7, ptg.GetBody().getSysS_V());
					_pstmt1.setFloat(8, ptg.GetBody().getSysT_V());
					_pstmt1.setFloat(9, ptg.GetBody().getSysR_A());
					_pstmt1.setFloat(10, ptg.GetBody().getSysS_A());
					_pstmt1.setFloat(11, ptg.GetBody().getSysT_A());
					_pstmt1.setLong(12, ptg.GetBody().getCurrentGen());
					_pstmt1.setFloat(13, ptg.GetBody().getPF());
					_pstmt1.setFloat(14, ptg.GetBody().getHz());
					_pstmt1.setLong(15, ptg.GetBody().getAccuGen());
					_pstmt1.setInt(16, ptg.GetBody().getFaultCode());

					if (DB_InsertQuery(_pstmt1)) {
						ProcessAlarm(_protocol);
						nQueryCount++;
					}

				} catch (Exception e) {
					DataCollectManager.logger
							.warn("[ DataCollector ] Failed to Create Insert Statement for Triple Generation :: "
									+ e.toString() + "\r\n [ stmt : " + _sql + " ]");
				}

				break;

			case "com.DataCollector.DataCollect_Protocol_Sensor":
				DataCollect_Protocol_Sensor psn = (DataCollect_Protocol_Sensor) _protocol.GetData();

				_sql = "Insert into sensor_data(Tracker_ID, T_Group_ID, Sensor_Data_Module_TEMP, Sensor_Data_Ambient_TEMP, Sensor_Data_Controller_TEMP, Sensor_Data_Controller_Humid , Sensor_Data_Horizontal_IDT, Sensor_Data_Slope_IDT, Sensor_Data_Wind_SPEED) Values( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				try {
					_pstmt1 = mysql.prepareStatement(_sql);
					_pstmt1.setInt(1, psn.GetHeader().getTR_ID());
					_pstmt1.setInt(2, psn.GetHeader().getSI_ID());

					if (Utils.isInRange(psn.GetBody().getfModuleTemp(), 199.9f, -40.0f))
						_pstmt1.setFloat(3, psn.GetBody().getfModuleTemp());
					else {
						DataCollectManager.logger.warn(String.format(
								"[ DataCollector ] [ Group : %d | TID : %d ] Got Invalidate ModuleTemp !! :: %.1f -> change into NULL",
								psn.GetHeader().getSI_ID(), psn.GetHeader().getTR_ID(),
								psn.GetBody().getfModuleTemp()));
						_pstmt1.setNull(3, Types.FLOAT);
					}

					if (Utils.isInRange(psn.GetBody().getfAmbientTemp(), 199.9f, -40.0f))
						_pstmt1.setFloat(4, psn.GetBody().getfAmbientTemp());
					else {
						DataCollectManager.logger.warn(String.format(
								"[ DataCollector ] [ Group : %d | TID : %d ] Got Invalidate AmbientTemp !! :: %.1f -> change into NULL",
								psn.GetHeader().getSI_ID(), psn.GetHeader().getTR_ID(),
								psn.GetBody().getfAmbientTemp()));
						_pstmt1.setNull(4, Types.FLOAT);
					}

					if (Utils.isInRange(psn.GetBody().getfControllerTemp(), 199.9f, -40.0f))
						_pstmt1.setFloat(5, psn.GetBody().getfControllerTemp());
					else {
						DataCollectManager.logger.warn(String.format(
								"[ DataCollector ] [ Group : %d | TID : %d ] Got Invalidate ControllerTemp !! :: %.1f -> change into NULL",
								psn.GetHeader().getSI_ID(), psn.GetHeader().getTR_ID(),
								psn.GetBody().getfModuleTemp()));
						_pstmt1.setNull(5, Types.FLOAT);
					}

					if (Utils.isInRange(psn.GetBody().getfInnerRltHumid(), 100.00f, 0.00f))
						_pstmt1.setFloat(6, psn.GetBody().getfInnerRltHumid());
					else {
						DataCollectManager.logger.warn(String.format(
								"[ DataCollector ] [ Group : %d | TID : %d ] Got Invalidate InnerRltHumid !! :: %.1f -> change into NULL",
								psn.GetHeader().getSI_ID(), psn.GetHeader().getTR_ID(),
								psn.GetBody().getfInnerRltHumid()));
						_pstmt1.setNull(6, Types.FLOAT);
					}

					if (Utils.isInRange(psn.GetBody().getnHorizontalIDT(), 2000, 0))
						_pstmt1.setInt(7, psn.GetBody().getnHorizontalIDT());
					else {
						DataCollectManager.logger.warn(String.format(
								"[ DataCollector ] [ Group : %d | TID : %d ] Got Invalidate HorizontalIDT !! :: %d -> change into NULL",
								psn.GetHeader().getSI_ID(), psn.GetHeader().getTR_ID(),
								psn.GetBody().getnHorizontalIDT()));
						_pstmt1.setNull(7, Types.INTEGER);
					}

					if (Utils.isInRange(psn.GetBody().getnSlopeIDT(), 2000, 0))
						_pstmt1.setInt(8, psn.GetBody().getnSlopeIDT());
					else {
						DataCollectManager.logger.warn(String.format(
								"[ DataCollector ] [ Group : %d | TID : %d ] Got Invalidate SlopeIDT !! :: %d -> change into NULL",
								psn.GetHeader().getSI_ID(), psn.GetHeader().getTR_ID(), psn.GetBody().getnSlopeIDT()));
						_pstmt1.setNull(8, Types.INTEGER);
					}

					if (Utils.isInRange(psn.GetBody().getfWindSpeed(), 40.0f, 0.0f))
						_pstmt1.setFloat(9, psn.GetBody().getfWindSpeed());
					else {
						DataCollectManager.logger.warn(String.format(
								"[ DataCollector ] [ Group : %d | TID : %d ] Got Invalidate WindSpeed !! :: %.1f -> change into NULL",
								psn.GetHeader().getSI_ID(), psn.GetHeader().getTR_ID(), psn.GetBody().getfWindSpeed()));
						_pstmt1.setNull(9, Types.FLOAT);
					}

					DB_InsertQuery(_pstmt1);
					nQueryCount++;
				} catch (Exception e) {
					DataCollectManager.logger
							.warn("[ DataCollector ] Failed to Create Insert Statement for Sensor Generation :: "
									+ e.toString() + "\r\n [ stmt : " + _sql + " ]");
				}
				break;

			case "com.DataCollector.DataCollect_Protocol_Shadow":
				DataCollect_Protocol_Shadow psd = (DataCollect_Protocol_Shadow) _protocol.GetData();

				_sql = "Insert into shadow_data(Tracker_ID, T_Group_ID, Shadow_Data_WCDS1, Shadow_Data_WCDS2, Shadow_Data_WCDS3, Shadow_Data_ECDS1, Shadow_Data_ECDS2, Shadow_Data_ECDS3) Values( ?, ?, ?, ?, ?, ?, ?, ?)";

				try {
					_pstmt1 = mysql.prepareStatement(_sql);
					_pstmt1.setInt(1, psd.GetHeader().getTR_ID());
					_pstmt1.setInt(2, psd.GetHeader().getSI_ID());
					_pstmt1.setInt(3, psd.GetBody().getnWCDS1());
					_pstmt1.setInt(4, psd.GetBody().getnWCDS2());
					_pstmt1.setInt(5, psd.GetBody().getnWCDS3());
					_pstmt1.setInt(6, psd.GetBody().getnECDS1());
					_pstmt1.setInt(7, psd.GetBody().getnECDS2());
					_pstmt1.setInt(8, psd.GetBody().getnECDS3());

					DB_InsertQuery(_pstmt1);
					nQueryCount++;
				} catch (Exception e) {
					DataCollectManager.logger
							.warn("[ DataCollector ] Failed to Create Insert Statement for Shadow Generation :: "
									+ e.toString() + "\r\n [ stmt : " + _sql + " ]");
				}
				break;
			case "com.DataCollector.DataCollect_Protocol_Shadow_ADC":
				DataCollect_Protocol_Shadow_ADC psdcd = (DataCollect_Protocol_Shadow_ADC) _protocol.GetData();

				_sql = "Insert into shadow_CDS_data(Tracker_ID, T_Group_ID, ShadowCDS_Data_ADC1, ShadowCDS_Data_ADC2, ShadowCDS_Data_ADC3, ShadowCDS_Data_ADC4, ShadowCDS_Data_ADC5, ShadowCDS_Data_ADC6, ShadowCDS_Data_ADC7, ShadowCDS_Data_ADC8) Values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				try {
					_pstmt1 = mysql.prepareStatement(_sql);
					_pstmt1.setInt(1, psdcd.GetHeader().getTR_ID());
					_pstmt1.setInt(2, psdcd.GetHeader().getSI_ID());
					_pstmt1.setInt(3, psdcd.GetBody().getnADC1());
					_pstmt1.setInt(4, psdcd.GetBody().getnADC2());
					_pstmt1.setInt(5, psdcd.GetBody().getnADC3());
					_pstmt1.setInt(6, psdcd.GetBody().getnADC4());
					_pstmt1.setInt(7, psdcd.GetBody().getnADC5());
					_pstmt1.setInt(8, psdcd.GetBody().getnADC6());
					_pstmt1.setInt(9, psdcd.GetBody().getnADC7());
					_pstmt1.setInt(10, psdcd.GetBody().getnADC8());

					DB_InsertQuery(_pstmt1);
					nQueryCount++;
				} catch (Exception e) {
					DataCollectManager.logger
							.warn("[ DataCollector ] Failed to Create Insert Statement for Shadow Generation :: "
									+ e.toString() + "\r\n [ stmt : " + _sql + " ]");
				}
				break;

			case "com.DataCollector.DataCollect_Protocol_Status":
				DataCollect_Protocol_Status ps = (DataCollect_Protocol_Status) _protocol.GetData();

				_sql = "Insert into t_status(t_Group_ID, tracker_ID, cnt, x1, x2, x3, x4, x5, x6, x7, x8, x9) Values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				try {
					_pstmt1 = mysql.prepareStatement(_sql);
					_pstmt1.setInt(1, ps.GetHeader().getSI_ID());
					_pstmt1.setInt(2, ps.GetHeader().getTR_ID());
					_pstmt1.setLong(3, ps.GetBody().getlCount());
					_pstmt1.setLong(4, ps.GetBody().getlX1());
					_pstmt1.setLong(5, ps.GetBody().getlX2());
					_pstmt1.setLong(6, ps.GetBody().getlX3());
					_pstmt1.setLong(7, ps.GetBody().getlX4());
					_pstmt1.setLong(8, ps.GetBody().getlX5());
					_pstmt1.setLong(9, ps.GetBody().getlX6());
					_pstmt1.setLong(10, ps.GetBody().getlX7());
					_pstmt1.setLong(11, ps.GetBody().getlX8());
					_pstmt1.setLong(12, ps.GetBody().getlX9());

					DB_InsertQuery(_pstmt1);
					nQueryCount++;
				} catch (Exception e) {
					DataCollectManager.logger
							.warn("[ DataCollector ] Failed to Create Insert Statement for X_Status :: " + e.toString()
									+ "\r\n [ stmt : " + _sql + " ]");
				}
				break;
			default:
				break;
		}

		// 19-05-24 commented.
		// DataCollectManager.logger.debug("QueryCount : " + nQueryCount + "\n");

	}

	private void DB_InsertAlarm(DataCollect_Protocol _protocol) {
		String _sql = "";
		PreparedStatement _pstmt1;

		String _subject = "";
		String _body = "";

		int _SIID = 0;
		int _TRID = 0;

		_sql = "Insert into t_alarm(T_Group_ID, Tracker_ID , Alarm_Type, Alarm_Grade, Alarm_Status) Values( ?, ?, ?, ?, ?)";

		try {
			_pstmt1 = mysql.prepareStatement(_sql);

			switch (_protocol.GetData().getClass().getName()) {

				case "com.DataCollector.DataCollect_Protocol_SingleGen":
					DataCollect_Protocol_SingleGen _psg = (DataCollect_Protocol_SingleGen) _protocol.GetData();
					_SIID = (1000 * (_psg.GetHeader().getSI_ID()[0] - '0'))
							+ ((100 * (_psg.GetHeader().getSI_ID()[1] - '0'))
									+ (10 * (_psg.GetHeader().getSI_ID()[2] - '0'))
									+ (_psg.GetHeader().getSI_ID()[3] - '0'));
					_TRID = (100 * (_psg.GetHeader().getTR_ID()[0] - '0'))
							+ (10 * (_psg.GetHeader().getTR_ID()[1] - '0')) + (_psg.GetHeader().getTR_ID()[2] - '0');

					_pstmt1.setInt(1, _SIID);
					_pstmt1.setInt(2, _TRID); // 191008 수정
					_pstmt1.setString(3, "알람");
					_pstmt1.setString(4, "0");
					_pstmt1.setString(5, ((Integer) _psg.GetBody().getFaultCode()).toString());

					if (_psg.GetBody().getFaultCode() != 0) {
						_subject = String.format("성창 모니터링시스템(SCMS) - %d 번 발전소 - %d 번 트래커 알람 발생 알림", _SIID, _TRID);
						_body = String.format("성창 모니터링시스템(SCMS) - %d 번 발전소 - %d 번 트래커에서 알람이 발생하였습니다.", _SIID, _TRID);
					}

					else {
						_subject = String.format("성창 모니터링시스템(SCMS) - %d 번 발전소 - %d 번 트래커 알람 해제 알림", _SIID, _TRID);
						_body = String.format("성창 모니터링시스템(SCMS) - %d 번 발전소 - %d 번 트래커가 정상화 되었습니다.", _SIID, _TRID);
					}

					// to Debug
					// new SMTPSender(SecretInfo.SMTP_ID, SecretInfo.SMTP_PW, "#@naver.com",
					// _subject, _body);

					// to Release

					DataCollectManager.logger.info(
							String.format("[ Instant Message ] :: GID - %d , TID - %d , SINGLE PHASE , AlarmCode - %d ",
									_SIID, _TRID, _psg.GetBody().getFaultCode()));
					for (String _email : DB_SelectGroupEmail(_SIID)) {
						try {
							new SMTPSender(SecretInfo.SMTP_ID, SecretInfo.SMTP_PW, _email, _subject, _body);
						} catch (Exception e) {
							DataCollectManager.logger
									.warn("[Email Send] :: Failed to Send E-mail.. please check E-mail address..");
						}

					}
					break;
				case "com.DataCollector.DataCollect_Protocol_TripleGen":
					DataCollect_Protocol_TripleGen _ptg = (DataCollect_Protocol_TripleGen) _protocol.GetData();
					_SIID = (1000 * (_ptg.GetHeader().getSI_ID()[0] - '0'))
							+ ((100 * (_ptg.GetHeader().getSI_ID()[1] - '0'))
									+ (10 * (_ptg.GetHeader().getSI_ID()[2] - '0'))
									+ (_ptg.GetHeader().getSI_ID()[3] - '0'));
					_TRID = (100 * (_ptg.GetHeader().getTR_ID()[0] - '0'))
							+ (10 * (_ptg.GetHeader().getTR_ID()[1] - '0')) + (_ptg.GetHeader().getTR_ID()[2] - '0');

					_pstmt1.setInt(1, _SIID);
					_pstmt1.setInt(2, _TRID);
					_pstmt1.setString(3, "알람");
					_pstmt1.setString(4, "0");
					_pstmt1.setString(5, ((Integer) _ptg.GetBody().getFaultCode()).toString());

					if (_ptg.GetBody().getFaultCode() != 0) {
						_subject = String.format("성창 모니터링시스템(SCMS) - %d 번 발전소 - %d 번 트래커 알람 발생 알림", _SIID, _TRID);
						_body = String.format("성창 모니터링시스템(SCMS) - %d 번 발전소 - %d 번 트래커에서 알람이 발생하였습니다.", _SIID, _TRID);
					}

					else {
						_subject = String.format("성창 모니터링시스템(SCMS) - %d 번 발전소 - %d 번 트래커 알람 해제 알림", _SIID, _TRID);
						_body = String.format("성창 모니터링시스템(SCMS) - %d 번 발전소 - %d 번 트래커가 정상화 되었습니다.", _SIID, _TRID);
					}
					// to Debug
					// new SMTPSender(SecretInfo.SMTP_ID, SecretInfo.SMTP_PW, "#@naver.com",
					// _subject, _body);

					// to Release

					DataCollectManager.logger.info(
							String.format("[ Instant Message ] :: GID - %d , TID - %d , TRIPLE PHASE , AlarmCode - %d ",
									_SIID, _TRID, _ptg.GetBody().getFaultCode()));
					for (String _email : DB_SelectGroupEmail(_SIID)) {

						try {
							new SMTPSender(SecretInfo.SMTP_ID, SecretInfo.SMTP_PW, _email, _subject, _body);
						} catch (Exception e) {
							DataCollectManager.logger.warn("[Email Send] :: Failed to send E-mail.. please ");
						}
					}
					break;
			}

			// _pstmt1.setInt(1, );

			DB_InsertQuery(_pstmt1);
		} catch (Exception e) {
			DataCollectManager.logger.warn("[ DataCollector ] Failed to Insert Statement for Alarm :: " + e.toString()
					+ "\r\n [ stmt : " + _sql + " ]");
		}
	}

	private synchronized boolean DB_InsertQuery(PreparedStatement _pstmt) {

		try {
			DataCollectManager.logger.debug("[ DataCollector ]" + _pstmt.toString().split(":")[1]
					+ " -> DB Insert Result : " + _pstmt.execute());
			return true;
		} catch (SQLException se) {
			DataCollectManager.logger.error("[ DataCollector ] DB_InsertQuery error occured ! : " + se.toString());
			return false;
		}
	}

	private synchronized void DB_UpdateQuery(PreparedStatement _pstmt) {

		try {
			_pstmt.executeUpdate();
		} catch (Exception e) {
			DataCollectManager.logger.error("[ DataCollector ] DB_UpdateQuery error occured ! : " + e.toString());
		}
	}

	public void SetPopQueue(CustomQueue<Object> _cq) {
		PopQueue = _cq;
	}

	private synchronized void ProcessAlarm(DataCollect_Protocol _protocol) {

		int _SIID = 0;
		int _TRID = 0;

		switch (_protocol.GetData().getClass().getName()) {

			case "com.DataCollector.DataCollect_Protocol_SingleGen":
				DataCollect_Protocol_SingleGen _psg = (DataCollect_Protocol_SingleGen) _protocol.GetData();

				_SIID = (1000 * (_psg.GetHeader().getSI_ID()[0] - '0'))
						+ ((100 * (_psg.GetHeader().getSI_ID()[1] - '0'))
								+ (10 * (_psg.GetHeader().getSI_ID()[2] - '0'))
								+ (_psg.GetHeader().getSI_ID()[3] - '0'));
				_TRID = (100 * (_psg.GetHeader().getTR_ID()[0] - '0')) + (10 * (_psg.GetHeader().getTR_ID()[1] - '0'))
						+ (_psg.GetHeader().getTR_ID()[2] - '0');

				// if(_psg.GetBody().getFaultCode() != 0) {

				// when cannot found target Group
				if (!tGroupOfPrevError.keySet().contains(_SIID)) {
					tGroupOfPrevError.put(_SIID, new ConcurrentHashMap<Integer, Integer>());

					if (DataCollectManager.MODE_ALARM_METHOD == 1) {
						// 1. alarm when first connected
						tGroupOfPrevError.get(_SIID).put(_TRID, _psg.GetBody().getFaultCode());
						DB_InsertAlarm(_protocol);
					}

					else if (DataCollectManager.MODE_ALARM_METHOD == 2) {
						// 2. alarm when error occured and change into normal
						tGroupOfPrevError.get(_SIID).put(_TRID, 0);
					}
				} else {
					// when cannot found target tracker
					if (!tGroupOfPrevError.get(_SIID).keySet().contains(_TRID)) {

						if (DataCollectManager.MODE_ALARM_METHOD == 1) {
							// 1. alarm when first connected
							tGroupOfPrevError.get(_SIID).put(_TRID, _psg.GetBody().getFaultCode());
							DB_InsertAlarm(_protocol);
						}

						else if (DataCollectManager.MODE_ALARM_METHOD == 2) {
							// 2. alarm when error occured and normaled
							tGroupOfPrevError.get(_SIID).put(_TRID, 0);
						}
					}

					else {
						// when previous error and current error has not matched
						if (!tGroupOfPrevError.get(_SIID).get(_TRID).equals(_psg.GetBody().getFaultCode())) {
							try {
								DB_InsertAlarm(_protocol);

								tGroupOfPrevError.get(_SIID).put(_TRID, _psg.GetBody().getFaultCode());
							} catch (Exception e) {
								DataCollectManager.logger.error("[] ... ");
							}
						}
					}
				}
				// }
				break;
			case "com.DataCollector.DataCollect_Protocol_TripleGen":
				DataCollect_Protocol_TripleGen _ptg = (DataCollect_Protocol_TripleGen) _protocol.GetData();

				_SIID = (1000 * (_ptg.GetHeader().getSI_ID()[0] - '0'))
						+ ((100 * (_ptg.GetHeader().getSI_ID()[1] - '0'))
								+ (10 * (_ptg.GetHeader().getSI_ID()[2] - '0'))
								+ (_ptg.GetHeader().getSI_ID()[3] - '0'));
				_TRID = (100 * (_ptg.GetHeader().getTR_ID()[0] - '0')) + (10 * (_ptg.GetHeader().getTR_ID()[1] - '0'))
						+ (_ptg.GetHeader().getTR_ID()[2] - '0');

				// if(_ptg.GetBody().getFaultCode() != 0) {

				// when cannot found target Group
				if (!tGroupOfPrevError.keySet().contains(_SIID)) {
					tGroupOfPrevError.put(_SIID, new ConcurrentHashMap<Integer, Integer>());

					if (DataCollectManager.MODE_ALARM_METHOD == 1) {
						// 1. alarm when first connected
						tGroupOfPrevError.get(_SIID).put(_TRID, _ptg.GetBody().getFaultCode());
						DB_InsertAlarm(_protocol);
					}

					else if (DataCollectManager.MODE_ALARM_METHOD == 2) {
						// 2. alarm when error occured and change into normal
						tGroupOfPrevError.get(_SIID).put(_TRID, 0);
					}
				}

				else {
					// when cannot found target tracker
					if (!tGroupOfPrevError.get(_SIID).keySet().contains(_TRID)) {

						if (DataCollectManager.MODE_ALARM_METHOD == 1) {
							// 1. alarm when first connected
							tGroupOfPrevError.get(_SIID).put(_TRID, _ptg.GetBody().getFaultCode());
							DB_InsertAlarm(_protocol);
						}

						else if (DataCollectManager.MODE_ALARM_METHOD == 2) {
							// 2. alarm when error occured and normaled
							tGroupOfPrevError.get(_SIID).put(_TRID, 0);
						}
					}

					else {
						// when previous error and current error has not matched
						if (!tGroupOfPrevError.get(_SIID).get(_TRID).equals(_ptg.GetBody().getFaultCode())) {
							try {
								DB_InsertAlarm(_protocol);

								tGroupOfPrevError.get(_SIID).put(_TRID, _ptg.GetBody().getFaultCode());
							} catch (Exception e) {
								DataCollectManager.logger.error("[ DataCollector ] error occured ! : " + e.toString());
							}
						}
					}
				}
				// }
				break;
		}
	}
}
