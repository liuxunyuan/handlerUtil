package com.example.netutil;

import com.example.response.BaseRespose;

public interface NetInterface {
	public void onRequestFailed(String error);
	public void onRequestSuccess(BaseRespose rsp);
}
