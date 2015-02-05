package com.example.sql;

import java.lang.reflect.ParameterizedType;

public class BaseExchangeClass<T> {

	  public Class<T> getTClass()
	    {
	        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	        return tClass;
	    }
	
}
