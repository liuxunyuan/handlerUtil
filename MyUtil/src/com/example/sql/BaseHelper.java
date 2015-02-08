package com.example.sql;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class BaseHelper<Bean,idClass> extends OrmLiteSqliteOpenHelper{
	
	final private static int DATABASE_VERSON = 1;
	private Dao<Bean,idClass> dao;
	private Class<Bean> mClass;
	public BaseHelper(Context context,Class<Bean> mclass){

		super(context, "Test", null, DATABASE_VERSON);
	//	tclass = new BaseExchangeClass<Bean>();
		mClass = mclass;
		try {
			TableUtils.createTableIfNotExists(connectionSource, mClass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		// TODO Auto-generated method stub

		       try {
				TableUtils.createTableIfNotExists(arg1,mClass);
			} catch (java.sql.SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		try {
			TableUtils.dropTable(arg1, mClass, true);
			onCreate(arg0, arg1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	@Override
	public void close(){
		super.close();
		dao = null;
	}
	
	public  Dao<Bean,idClass> getBaseHelperDao(){
		if(dao == null){
			try {
				dao =  getDao(mClass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dao;
	}
	
}
