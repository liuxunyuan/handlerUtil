package com.example.sql;

import java.lang.reflect.ParameterizedType;
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
	private BaseExchangeClass<Bean> tclass;
	public BaseHelper(Context context){

		super(context, "Test", null, DATABASE_VERSON);
		tclass = new BaseExchangeClass<Bean>();
		
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		// TODO Auto-generated method stub

		       try {
				TableUtils.createTableIfNotExists(arg1,(Class<Bean>) ((ParameterizedType)tclass.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
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
			TableUtils.dropTable(arg1, (Class<Bean>) ((ParameterizedType)tclass.getClass().getGenericSuperclass()).getActualTypeArguments()[0], true);
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
				dao =  getDao((Class<Bean>) ((ParameterizedType)tclass.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dao;
	}
	
}
