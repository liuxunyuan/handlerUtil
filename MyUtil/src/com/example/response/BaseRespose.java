package com.example.response;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.netutil.NetInterface;
import com.example.rsp.BaseRsp;

public class BaseRespose {
	protected JSONObject mJson;
	protected NetInterface mInterface;
	protected String mData;
	public BaseRespose(String data,NetInterface in){
		mInterface = in;
		mData = data;
		try {
			mJson = new JSONObject(data);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String resposeGetString(String key) throws JSONException{
		if(mJson == null){
			return null;
		}
		return mJson.getString(key);
	}
	
	public JSONObject resposeGetObject(String key) throws JSONException{
		if(mJson == null){
			return null;
		}
		return mJson.getJSONObject(key);
	}
	
	public String resposeCode(String key) throws JSONException{
		if(mJson == null){
			return null;
		}
		return mJson.getString(key);
	}
	
	public NetInterface getInterface(){
		return mInterface;
	}
	
	public String getData(){
		return mData;
	}
	
	public JSONObject getJosn(){
		return mJson;
	}
	

}
