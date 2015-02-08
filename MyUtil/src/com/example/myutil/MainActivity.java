package com.example.myutil;

import java.util.ArrayList;
import java.util.List;

import com.example.bean.TestAdapter;
import com.example.obverser.ObverserInterface;
import com.example.provider.WeathterProvider;
import com.example.requst.WeatherGetRequest;
import com.example.rsp.BaseRsp;
import com.example.rsp.TsetRsp;
import com.example.rsp.WeatherRsp;
import com.example.sql.EntityManager;
import com.example.viewutil.AsyncImageView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener,ObverserInterface{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button mButton = (Button)findViewById(R.id.search);
		mButton.setOnClickListener(this);
		Button mButton2 = (Button)findViewById(R.id.insert);
		mButton2.setOnClickListener(this);
		AsyncImageView img1 = (AsyncImageView)findViewById(R.id.imagetestone);
		AsyncImageView img2 = (AsyncImageView)findViewById(R.id.imagetesttwo);
		img1.loadingAsyncImage("http://b.hiphotos.baidu.com/image/pic/item/54fbb2fb43166d220e78d492452309f79052d2ad.jpg");
		img2.loadingAsyncImage("http://e.hiphotos.baidu.com/image/pic/item/55e736d12f2eb9381761bbf4d6628535e5dd6fac.jpg");
		ListView listView = (ListView)findViewById(R.id.listview);
		TestAdapter testAdapter = new TestAdapter(getApplicationContext());
		List<TsetRsp> list = new ArrayList<TsetRsp>();
			TsetRsp rsp = new TsetRsp();
			rsp.city = "http://b.hiphotos.baidu.com/image/pic/item/54fbb2fb43166d220e78d492452309f79052d2ad.jpg";
			rsp.temp = "桂华";
			list.add(rsp);
			TsetRsp rsp1 = new TsetRsp();
			rsp1.city = "http://a.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=10284cd567380cd7e64baaeb9174810c/63d9f2d3572c11df09ba0c46612762d0f703c268.jpg";
			rsp1.temp = "钊华";
			list.add(rsp1);
			TsetRsp rsp2 = new TsetRsp();
			rsp2.city = "http://www.qqjia.com/z/11/tu12071_11.jpg";
			rsp2.temp = "教练";
			list.add(rsp2);
			TsetRsp rsp3 = new TsetRsp();
			rsp3.city = "http://www.qq745.com/uploads/allimg/140831/1-140S1130633.jpg";
			rsp3.temp = "市长";
			list.add(rsp3);
			TsetRsp rsp4 = new TsetRsp();
			rsp4.city = "http://e.hiphotos.baidu.com/image/pic/item/55e736d12f2eb9381761bbf4d6628535e5dd6fac.jpg";
			rsp4.temp = "春华";
			list.add(rsp4);
			
		testAdapter.setData(list);
		listView.setAdapter(testAdapter);
		/*数据库测试代码
		TsetRsp tr = new TsetRsp();
		EntityManager<TsetRsp, String> mmManager = new EntityManager<TsetRsp, String>(getApplicationContext(), TsetRsp.class);
		tr.city = "广的州";
		tr.temp = "71度";
		ThreadUtil.executeDBinsert(mmManager, tr);
		TsetRsp tt = (TsetRsp) ThreadUtil.executeDBsearch(mmManager, "广的州");
		 ((TextView)findViewById(R.id.city)).setText(tt.city);
		 ((TextView)findViewById(R.id.temp)).setText(tt.temp);
		  WeatherBean bean = new WeatherBean();
		bean.cityId = 102;
		bean.city = "GUI";
		bean.temp = "10度";
		EntityManager<WeatherBean, Integer> mEntityManager = new EntityManager<WeatherBean, Integer>(getApplicationContext(),WeatherBean.class);
		//mEntityManager.insert(bean);
		WeatherBean nextBean = mEntityManager.search(102);
		
		 EntityManager<TestBean, String> mmManager = new EntityManager<TestBean, String>(getApplicationContext(), TestBean.class);
		// mmManager.update(1, 2);
			TestBean tb = new TestBean();
			tb.ID = "nicai";
			tb.test = "babi";
			mmManager.insert(tb);*/
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
		if(v.getId() == R.id.search){
		//	String cityNum = ((EditText)findViewById(R.id.where)).getText().toString();
			String cityNum = "";
			if(cityNum.equals("")){
				(Toast.makeText(getApplicationContext(), "╮(╯▽╰)╭",Toast.LENGTH_SHORT )).show();
			}else {
				new WeathterProvider(this).sendGetMsg(new WeatherGetRequest(cityNum));
				/*数据库测试代码
	 			EntityManager<WeatherBean, Integer> mEntityManager = new EntityManager<WeatherBean, Integer>(getApplicationContext(),WeatherBean.class);
				WeatherBean nextBean = mEntityManager.search(101);
				 ((TextView)findViewById(R.id.city)).setText(nextBean.city);
				 ((TextView)findViewById(R.id.temp)).setText(nextBean.temp);
				 EntityManager<TestBean, String> mmManager = new EntityManager<TestBean, String>(getApplicationContext(), TestBean.class);
				 TestBean nb = mmManager.search("nicai");
				 ((TextView)findViewById(R.id.wd)).setText(nb.ID);
				 ((TextView)findViewById(R.id.ws)).setText(nb.test);*/
			}
		}else if (v.getId() == R.id.insert) {
			TsetRsp tr = new TsetRsp();
			EntityManager<TsetRsp, String> mmManager = new EntityManager<TsetRsp, String>(getApplicationContext(), TsetRsp.class);
			tr.city =  ((TextView)findViewById(R.id.city)).getText().toString();
			tr.temp =  ((TextView)findViewById(R.id.temp)).getText().toString();;
			new WeathterProvider(this).sendInsert(mmManager,tr);
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
		(Toast.makeText(getApplicationContext(), "╮(╯▽╰)╭什么鬼",Toast.LENGTH_SHORT )).show();
	}

	@Override
	public void notify(List<BaseRsp> dataList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notify(Integer code) {
		// TODO Auto-generated method stub
		if(code != -404){
			(Toast.makeText(getApplicationContext(), "╮(╯▽╰)╭深深插入了",Toast.LENGTH_SHORT )).show();
		}else {
			(Toast.makeText(getApplicationContext(), "╮(╯▽╰)╭没插进去",Toast.LENGTH_SHORT )).show();
		}
	}

}
