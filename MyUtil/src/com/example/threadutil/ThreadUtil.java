package com.example.threadutil;

import org.json.JSONException;

import android.os.Handler;
import android.os.Looper;

import com.example.netutil.HttpGetUtil;
import com.example.netutil.NetInterface;
import com.example.requst.BaseGetRequest;
import com.example.response.BaseRespose;
import com.example.rsp.BaseRsp;
import com.example.sql.DBInterface;
import com.example.sql.EntityManager;

final public class ThreadUtil {
	static private Handler mHandler = new Handler(Looper.getMainLooper());
	static private ThreadBaseToDo todo ;
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
		todo = new ThreadBaseToDo(be);
		todo.start();
	}
	
	static public void executeDBinsert(EntityManager<?, ?> entity,BaseRsp bean,DBInterface in){
		BaseExecute be = new BaseExecute(new ExecuteInterface() {
			
			@Override
			public void execute(Object... object) {
				// TODO Auto-generated method stub
				int code = ((EntityManager<?, ?>)object[0]).insert((BaseRsp)object[1]);
				((DBInterface)object[2]).onQueryCode(code);
			}
		}, entity,bean,in);
		todo = new ThreadBaseToDo(be);
		todo.start();
	}
	
	static public void executeDBsearch(EntityManager<?, ?> entity,Object id){
		 entity.search(id);
	}
	
	static public synchronized void backToUI(BaseExecute be){ 
			mHandler.post(be);
		
	}
	
	static public synchronized void executeLoadImage(BaseExecute be){
		todo = new ThreadBaseToDo(be);
		todo.start();
	}
}
