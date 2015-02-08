package com.example.rsp;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "aaaa")
public class TsetRsp extends BaseRsp{

	@DatabaseField(id = true)
	public String city;
	
	@DatabaseField
	public String temp;
}
