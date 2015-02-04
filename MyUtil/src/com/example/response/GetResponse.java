package com.example.response;

import org.json.JSONException;

import com.example.netutil.NetInterface;
import com.example.rsp.BaseRsp;

public abstract class GetResponse extends BaseRespose{

	public GetResponse(String data, NetInterface in) {
		super(data, in);
		// TODO Auto-generated constructor stub
	}
	
	abstract public BaseRsp JsonToRsp() throws JSONException ;

}
