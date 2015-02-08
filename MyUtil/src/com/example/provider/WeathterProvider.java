package com.example.provider;

import java.util.List;

import org.json.JSONException;

import android.content.res.Resources.Theme;

import com.example.netutil.NetInterface;
import com.example.obverser.ObverserInterface;
import com.example.requst.BaseGetRequest;
import com.example.response.BaseRespose;
import com.example.response.WeatherResponse;
import com.example.rsp.BaseRsp;
import com.example.rsp.WeatherRsp;
import com.example.threadutil.ThreadUtil;

public class WeathterProvider extends BaseProvider{

	public WeathterProvider(ObverserInterface in) {
		super(in);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void onRequestFailed(String error) {
		// TODO Auto-generated method stub
		changeNotify(mErrorInterface, error);
	}

	@Override
	public void onRequestSuccess(BaseRespose response) {
		// TODO Auto-generated method stub
		WeatherRsp rsp;
		try {
			//rsp = (WeatherRsp)(((WeatherResponse) response).JsonToRsp());
			WeatherResponse wResponse = new WeatherResponse(response.getData(), response.getInterface());
			rsp = (WeatherRsp) wResponse.JsonToRsp();
			changeNotify(mRspInterface, rsp);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			onRequestFailed("ERROR");
		}
		
	}



	@Override
	public void onQueryFailed(String error) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onQuerySuccess(BaseRsp data) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onQuerySuccess(List<BaseRsp> dataList) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onQueryCode(Integer code) {
		// TODO Auto-generated method stub
		changerNotify(mCodenterface, code);
	}



}
