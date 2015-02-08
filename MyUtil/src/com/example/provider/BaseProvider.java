package com.example.provider;

import java.util.List;

import android.app.Activity;
import android.content.Context;

import com.example.netutil.NetInterface;
import com.example.obverser.ObverserInterface;
import com.example.requst.BaseGetRequest;
import com.example.rsp.BaseRsp;
import com.example.sql.DBInterface;
import com.example.sql.EntityManager;
import com.example.threadutil.BaseExecute;
import com.example.threadutil.ExecuteInterface;
import com.example.threadutil.ThreadUtil;

abstract public class BaseProvider implements NetInterface, DBInterface {
	protected ObverserInterface mInterface;
	
	protected ExecuteInterface mRspInterface = new ExecuteInterface() {

		@Override
		public void execute(Object... object) {
			// TODO Auto-generated method stub
			((ObverserInterface) object[0]).notify((BaseRsp) object[1]);
		}
	};
	protected ExecuteInterface mErrorInterface = new ExecuteInterface() {

		@Override
		public void execute(Object... object) {
			// TODO Auto-generated method stub
			((ObverserInterface) object[0]).notify((String) object[1]);
		}
	};
	
	protected ExecuteInterface mListRspInterface = new ExecuteInterface() {
		
		@SuppressWarnings("unchecked")
		@Override
		public void execute(Object... object) {
			// TODO Auto-generated method stub
			((ObverserInterface) object[0]).notify((List<BaseRsp>) object[1]);
		}
	};
	
	protected ExecuteInterface mCodenterface = new ExecuteInterface() {

		@Override
		public void execute(Object... object) {
			// TODO Auto-generated method stub
			((ObverserInterface) object[0]).notify((Integer) object[1]);
		}
	};

	public BaseProvider(ObverserInterface in) {
		mInterface = in;
	}

	public void sendGetMsg(BaseGetRequest request) {
		ThreadUtil.executeHttpGet(request, this);
	}
	
	public void sendInsert(EntityManager<?, ?> entity, BaseRsp bean){
		ThreadUtil.executeDBinsert(entity, bean, this);
	}

	protected void changeNotify(ExecuteInterface in, String error) {
		ThreadUtil.backToUI(new BaseExecute(in, mInterface, error));
	}

	protected void changeNotify(ExecuteInterface in, BaseRsp rsp) {
		ThreadUtil.backToUI(new BaseExecute(in, mInterface, rsp));
	}
	
	protected void changeNotify(ExecuteInterface in, List<BaseRsp> rsp) {
		ThreadUtil.backToUI(new BaseExecute(in, mInterface, rsp));
	}
	
	protected void changerNotify(ExecuteInterface in, Integer code){
		ThreadUtil.backToUI(new BaseExecute(in, mInterface, code));
	}
	
	
}
