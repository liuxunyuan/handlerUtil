package com.example.netutil;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import com.example.requst.BaseGetRequest;
import com.example.response.BaseRespose;

final public class HttpGetUtil{
	
	static public BaseRespose execute(BaseGetRequest url,final NetInterface in) throws JSONException{
		HttpGet httpGet = new HttpGet(url.getUrl());
		ResponseHandler<BaseRespose> responseHander = new ResponseHandler<BaseRespose>() {

			@Override
			public BaseRespose handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				// TODO Auto-generated method stub
				HttpEntity entity = response.getEntity();
				if(entity != null){
					return new BaseRespose(EntityUtils.toString(entity,HTTP.UTF_8),in);
				}
				return null;
				
			}
		};
		try {
			BaseRespose baseResponse = new DefaultHttpClient().execute(httpGet,responseHander);
			if(baseResponse != null){
				return baseResponse;
			}else {
				return null;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}

}
