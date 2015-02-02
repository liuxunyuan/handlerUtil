package com.example.response;

import org.json.JSONException;
import org.json.JSONObject;
import com.example.rsp.BaseRsp;

abstract public class BaseRespose {
	protected JSONObject mJson;
	public BaseRespose(String data){
		try {
			mJson = new JSONObject(data);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected String resposeGetString(String key) throws JSONException{
		if(mJson == null){
			return null;
		}
		return mJson.getString(key);
	}
	
	abstract protected BaseRsp JsonToRsp()throws JSONException;
}
