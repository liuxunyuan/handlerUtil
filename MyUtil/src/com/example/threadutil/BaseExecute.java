package com.example.threadutil;

public class BaseExecute implements Runnable{
	private Object[] mOBj;
	private  ExecuteInterface mInterface;
	public BaseExecute(ExecuteInterface interfacese, Object... objects){
		mOBj = objects;
		mInterface = interfacese;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		mInterface.execute(mOBj);
	}

}
