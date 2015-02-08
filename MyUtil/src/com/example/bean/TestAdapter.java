package com.example.bean;

import java.util.ArrayList;
import java.util.List;

import com.example.myutil.R;
import com.example.rsp.TsetRsp;
import com.example.viewutil.AsyncImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class TestAdapter extends BaseAdapter{
	
	private ArrayList<TsetRsp> mData;
	private Context mContext;
	public TestAdapter(Context context){
		mContext = context;
	}
	public void setData(List<TsetRsp> data){
		if(data != null)
		mData = (ArrayList<TsetRsp>) data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(mData != null){
			return mData.size();
		}else {
			return 0;
		}
		
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_text_layout, null);
			viewHolder.img = (AsyncImageView)convertView.findViewById(R.id.imgitem);
			viewHolder.text = (TextView)convertView.findViewById(R.id.textitem);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		TsetRsp itemRsp = mData.get(position);
		viewHolder.img.loadingAsyncImage(itemRsp.city);
		viewHolder.text.setText(itemRsp.temp);
		return convertView;
	}
	
	class ViewHolder{
		AsyncImageView img;
		TextView text;
	}

	
}
