package com.example.threadutil;

import org.json.JSONException;

import android.os.Handler;
import android.os.Looper;

import com.example.netutil.HttpGetUtil;
import com.example.netutil.NetInterface;
import com.example.requst.BaseGetRequest;
import com.example.response.BaseRespose;

final public class ThreadUtil {
	static private Handler mHandler = new Handler(Looper.getMainLooper());
	static public void executeHttpGet(BaseGetRequest request,NetInterface in){
		BaseExecute be = new BaseExecute(new ExecuteInterface() {
			@Override
			public void execute(Object... object) {
				// TODO Auto-generated method stub
				try {
					BaseRespose response = HttpGetUtil.execute((BaseGetRequest)object[0], (NetInterface)object[1]);
				//	if((response.resposeCode("Code").equals("OK"))){
					if(response !=null &&response.getJosn() != null){					
						response.getInterface().onRequestSuccess(response);
					}else {
							((NetInterface)object[1]).onRequestFailed("ERROR");
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					((NetInterface)object[1]).onRequestFailed("ERROR");
				}
			}
		}, request,in);
		ThreadBaseToDo todo = new ThreadBaseToDo(be);
		todo.start();
	}
	
	static public void backToUI(BaseExecute be){
		mHandler.post(be);
	}
}
