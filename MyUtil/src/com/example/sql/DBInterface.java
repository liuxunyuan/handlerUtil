package com.example.sql;

import java.util.List;

import com.example.rsp.BaseRsp;

public interface DBInterface {
	public void onQueryFailed(String error);
	public void onQuerySuccess(BaseRsp data);
	public void onQuerySuccess(List<BaseRsp> dataList);
	public void onQueryCode(Integer code);
}
