package com.example.sql;

import java.sql.SQLException;

import android.content.Context;
import android.util.Log;

import com.example.rsp.BaseRsp;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class EntityManager<Bean,idClass> {
	private BaseHelper<Bean, idClass> mHelp;
	private Dao<Bean,idClass> mDao;
	

	public EntityManager(Context context,Class<Bean> mclass){
		mHelp = new BaseHelper<Bean,idClass>(context,mclass);
		OpenHelperManager.setHelper(mHelp);
		if(mHelp != null){
			mDao = mHelp.getBaseHelperDao();
		}	
	}
	/***
	 * 升级版本？？依葫芦画瓢，慎用，。。估计有bug
	 * @param oldVersion 旧版本
	 * @param newVersion 新版本
	 */
	public void update(int oldVersion,int newVersion){
		if(mHelp != null){
			mHelp.onUpgrade(mHelp.getWritableDatabase(), mHelp.getConnectionSource(), oldVersion, newVersion);
		}
	}
	
	public void close(){
		if(mHelp != null){
			mHelp.close();
			OpenHelperManager.releaseHelper();
			mHelp = null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public int insert(BaseRsp bean){
		try {
			return mDao.create((Bean)bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -404;
		}
	}
	@SuppressWarnings("unchecked")
	public Bean search(Object id){
		try {
			Bean m = mDao.queryForId((idClass)id);
			return m;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
