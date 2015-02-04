package com.example.obverser;

import com.example.rsp.BaseRsp;

public interface ObverserInterface {
	public void notify(BaseRsp rsp);
	public void notify(String  error);
}
