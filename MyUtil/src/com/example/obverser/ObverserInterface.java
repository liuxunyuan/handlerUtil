package com.example.obverser;

import java.util.List;

import com.example.rsp.BaseRsp;

public interface ObverserInterface {
	public void notify(BaseRsp rsp);
	public void notify(Integer code);
	public void notify(String  error);
	public void notify(List<BaseRsp>  dataList);
}
