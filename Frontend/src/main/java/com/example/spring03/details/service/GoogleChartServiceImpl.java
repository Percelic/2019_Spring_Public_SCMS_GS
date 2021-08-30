package com.example.spring03.details.service;

import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.example.spring03.details.dto.DetailsDTO;


@Service
public class GoogleChartServiceImpl 
	implements GoogleChartService {

	@Inject //의존관계 주입(장바구니 서비스)
	DetailsService detailsService;
	// 	ArrayList => json 객체
	@Override
	public JSONObject getChartData() {
		List<DetailsDTO> items=detailsService.GoogleChart();
		//리턴할 최종 json 객체
		JSONObject data=new JSONObject();
		//컬럼을 정의할 json 객체
		JSONObject col1=new JSONObject();
		JSONObject col2=new JSONObject();
		JSONArray title=new JSONArray();
		
		col1.put("label", "군집 아이디");
		col1.put("type", "string");
		col2.put("label", "현재발전량");
		col2.put("type", "number");
		// json 배열에 json 객체 추가
		title.add(col1);
		title.add(col2);
		data.put("cols", title);
		
		//json의 rows 객체구성
		JSONArray body=new JSONArray();
		for(DetailsDTO dto : items) {
			JSONObject t_group_id =new JSONObject();
			t_group_id.put("v", dto.getT_group_id());
			JSONObject inverter_data_accu_energy=new JSONObject();
			inverter_data_accu_energy.put("v", dto.getInverter_data_accu_energy());
			JSONArray row=new JSONArray();
			row.add(t_group_id);
			row.add(inverter_data_accu_energy);
			JSONObject cell=new JSONObject();
			cell.put("c", row);
			body.add(cell);
		}
		data.put("rows", body);
		
		return data;
	}

}






