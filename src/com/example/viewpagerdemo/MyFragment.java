package com.example.viewpagerdemo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ListView;

public class MyFragment extends Fragment{
	
	private String key = null;
	
	
	static MyFragment newInstance(String s){
		MyFragment myFragment = new MyFragment();
		Bundle bundle = new Bundle();
		bundle.putString("key", s);
		myFragment.setArguments(bundle);
		return myFragment;
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		Bundle bundle = getArguments();
		key = bundle != null? bundle.getString("key") : null;
		super.onCreate(savedInstanceState);
	}
	
	
	private ListView listView;
	
	private MyListViewAdapter adapter;
	
	private List<String> list ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = null; 
		Button button = null;
		if (key == null || key.equals(Contents.ZH)) {
			view = inflater.inflate(R.layout.lay1, container, false);
			button = (Button) view.findViewById(R.id.confirm);
			button.setOnClickListener(new MyClickListener(1));
		}
		
		if (key != null && key.equals(Contents.XW)) {
			view = inflater.inflate(R.layout.lay2,container,false);
			button = (Button) view.findViewById(R.id.confirm02);
			button.setOnClickListener(new MyClickListener(2));
		}
		
		if (key != null && key.equals(Contents.YL)) {
			view = inflater.inflate(R.layout.lay3,container,false);
		}
		
		if (key != null && key.equals(Contents.TY)) {
			view = inflater.inflate(R.layout.lay4,container,false);
			listView = (ListView) view.findViewById(R.id.mylistview);
			list = getdata();
			adapter = new MyListViewAdapter(getActivity(),list);
			listView.setAdapter(adapter);
			
			//listView滑动状态判断
			listView.setOnScrollListener(new OnScrollListener() {
				
				@Override
				public void onScrollStateChanged(AbsListView arg0, int arg1) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onScroll(AbsListView arg0, int firstItem, int visibleItemCount, int totalItemCount) {
					// TODO Auto-generated method stub
					
					//到达底部
					if (firstItem + visibleItemCount == totalItemCount) {
						list = getdata();
						adapter.notifyDataSetChanged();
					}
				}
			});
			
		}
		
		return view;
	}
	
	private List<String> getdata(){
		int size = 0;
		if (list != null) {
			size = list.size();
			
		}
		if (list == null) {
			list = new ArrayList<String>();
		}
		
		for (int i = 0; i < 20; i++) {
			list.add("item" + i + size);
		}
		return list;
	}
	
	class MyClickListener implements OnClickListener{

		int index;
		
		public MyClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			Log.d("onclick", "onclick " + index);
		}
		
	}

}
