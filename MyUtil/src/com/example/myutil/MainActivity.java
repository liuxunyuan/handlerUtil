package com.example.myutil;

import com.example.obverser.ObverserInterface;
import com.example.provider.BaseProvider;
import com.example.provider.WeathterProvider;
import com.example.requst.WeatherGetRequest;
import com.example.rsp.BaseRsp;
import com.example.rsp.WeatherRsp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener,ObverserInterface{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button mButton = (Button)findViewById(R.id.search);
		mButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String cityNum = ((EditText)findViewById(R.id.where)).getText().toString();
		if(cityNum.equals("")){
			(Toast.makeText(getApplicationContext(), "¨r(¨s¨Œ¨t)¨q",Toast.LENGTH_SHORT )).show();
		}else {
			WeatherGetRequest resquest = new WeatherGetRequest(cityNum);
			new WeathterProvider(this).sendGetMsg(resquest);
		}
		
	}

	@Override
	public void notify(BaseRsp rsp) {
		// TODO Auto-generated method stub
		 ((TextView)findViewById(R.id.city)).setText(((WeatherRsp)rsp).city);
		 ((TextView)findViewById(R.id.temp)).setText(((WeatherRsp)rsp).temp);
		 ((TextView)findViewById(R.id.wd)).setText(((WeatherRsp)rsp).wd);
		 ((TextView)findViewById(R.id.ws)).setText(((WeatherRsp)rsp).ws);
	}

	@Override
	public void notify(String error) {
		// TODO Auto-generated method stub
		(Toast.makeText(getApplicationContext(), "¨r(¨s¨Œ¨t)¨qÊ²Ã´¹í",Toast.LENGTH_SHORT )).show();
	}

}
