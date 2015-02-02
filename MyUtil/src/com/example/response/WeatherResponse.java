package com.example.response;

import org.json.JSONException;

import com.example.rsp.BaseRsp;
import com.example.rsp.WeatherRsp;

public class WeatherResponse extends BaseRespose{
	
	public WeatherResponse(String data){
		super(data);
	}

	@Override
	protected BaseRsp JsonToRsp() throws JSONException {
		// TODO Auto-generated method stub
		WeatherRsp rsp = new WeatherRsp();
		rsp.city = resposeGetString("city");
		rsp.temp = resposeGetString("temp");
		rsp.wd = resposeGetString("WD");
		rsp.ws = resposeGetString("WS");
		return rsp;
	}

}
