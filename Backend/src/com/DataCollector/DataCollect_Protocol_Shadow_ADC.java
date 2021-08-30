package com.DataCollector;

import com.Protocol.Protocol_Body_ShadowADCData;
import com.Protocol.Protocol_Foot_ForString;
import com.Protocol.Protocol_Header_ForString;

/* ------------------------------------------------- */
/*				������������ - �¾籤 ����͸� ���� ���� 		 */
/*				������ �������� ���α׷� ���� 				 */
/*													 */
/*						19-05-07					 */
/*				�������� ADC ����������  Body �κ� ���.		 */
/*													 */
/* ------------------------------------------------- */

public class DataCollect_Protocol_Shadow_ADC {
	
	Protocol_Header_ForString header;
	Protocol_Body_ShadowADCData body;
	Protocol_Foot_ForString foot;

	public DataCollect_Protocol_Shadow_ADC() {
		header = new Protocol_Header_ForString();
		body = new Protocol_Body_ShadowADCData();
		foot = new Protocol_Foot_ForString();
	}
	
	public Protocol_Header_ForString GetHeader() {
		return header;
	}
	
	public Protocol_Body_ShadowADCData GetBody() {
		return body;
	}
	
	public Protocol_Foot_ForString GetFooter() {
		return foot;
	}
	
}
