package com.DataCollector;

import com.Protocol.Protocol_Body_ShadowADCData;
import com.Protocol.Protocol_Foot_ForString;
import com.Protocol.Protocol_Header_ForString;

/* ------------------------------------------------- */
/*				공동연구법인 - 태양광 모니터링 서버 개발 		 */
/*				데이터 수집서버 프로그램 개발 				 */
/*													 */
/*						19-05-07					 */
/*				음영제어 ADC 프로토콜의  Body 부분 기술.		 */
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
