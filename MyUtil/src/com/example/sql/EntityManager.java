package com.example.sql;

import java.sql.SQLException;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class EntityManager<Bean,idClass> {
	private BaseHelper<Bean, idClass> mHelp;
	private Dao<Bean,idClass> mDao;
	
	@SuppressWarnings("unchecked")
	public EntityManager(Context context){
		mHelp = new BaseHelper<Bean,idClass>(context);
		if(mHelp != null){
			mDao = mHelp.getBaseHelperDao();
		}	
	}
	
	public void close(){
		if(mHelp != null){
			mHelp.close();
			OpenHelperManager.releaseHelper();
			mHelp = null;
		}
	}
	
	public void insert(Bean bean){
		try {
			mDao.create(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
