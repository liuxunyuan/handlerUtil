package com.example.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "weather")
public class WeatherBean {

	@DatabaseField(id = true)
	public int cityId;
	
	@DatabaseField
	public String city;
	
	@DatabaseField
	public String temp;
	
}
