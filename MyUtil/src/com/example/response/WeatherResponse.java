package com.example.response;

import org.json.JSONException;

import com.example.netutil.NetInterface;
import com.example.rsp.BaseRsp;
import com.example.rsp.WeatherRsp;

public class WeatherResponse extends GetResponse{
	
	public WeatherResponse(String data,NetInterface in){
		super(data,in);
	}

	@Override
	public BaseRsp JsonToRsp() throws JSONException {
		// TODO Auto-generated method stub
		WeatherRsp rsp = new WeatherRsp();
		if(mJson == null){
			return null;
		}
	/*	rsp.city = resposeGetString("city");
		rsp.temp = resposeGetString("temp");
		rsp.wd = resposeGetString("WD");
		rsp.ws = resposeGetString("WS");*/
		rsp.city = mJson.getJSONObject("weatherinfo").getString("city");
		rsp.temp = mJson.getJSONObject("weatherinfo").getString("temp");
		rsp.wd = mJson.getJSONObject("weatherinfo").getString("WD");
		rsp.ws = mJson.getJSONObject("weatherinfo").getString("WS");
		return rsp;
	}

}
