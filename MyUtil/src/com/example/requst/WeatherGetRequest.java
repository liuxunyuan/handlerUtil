package com.example.requst;

public class WeatherGetRequest extends BaseGetRequest{
	
	public WeatherGetRequest(Object... param) {
		mUrl = "http://www.weather.com.cn/data/sk/" + (Integer)param[0] + ".html";
	}

}
