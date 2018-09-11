package com.example.viewpagerdemo;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	
	private ArrayList<Fragment> list = null;
	
	private ViewPager mViewPager;
	
	private ImageView iv_bottom_line;
	
	private Resources resources;
	
	private TextView zh;
	
	private TextView xw;
	
	private TextView yl;
	
	private TextView ty;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		
		initWidth();
		
		initViewPager();
		
	}


	private void initViewPager() {
		Fragment zh = MyFragment.newInstance(Contents.ZH);
		Fragment xw = MyFragment.newInstance(Contents.XW);
		Fragment yl = MyFragment.newInstance(Contents.YL);
		Fragment ty = MyFragment.newInstance(Contents.TY);
		
		
		list = new ArrayList<Fragment>();
		
		list.add(zh);
		list.add(xw);
		list.add(yl);
		list.add(ty);
		
		mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),list));
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(new MyViewPagerChangedListener());
		
		
	}
	
	private void initView(){
		mViewPager = (ViewPager) findViewById(R.id.myviewpager);
		iv_bottom_line = (ImageView) findViewById(R.id.iv_bottom_line);
		zh = (TextView) findViewById(R.id.zh);
		xw = (TextView) findViewById(R.id.xw);
		yl = (TextView) findViewById(R.id.yl);
		ty = (TextView) findViewById(R.id.ty);
		
		zh.setOnClickListener(new MyClickListener(0));
		xw.setOnClickListener(new MyClickListener(1));
		yl.setOnClickListener(new MyClickListener(2));
		ty.setOnClickListener(new MyClickListener(3));
		
	}
	
	private int first = 0;
	private int second = 0;
	private int third = 0;
	
	private void initWidth(){
		int lineWidth = iv_bottom_line.getLayoutParams().width;
		Log.d("lineWidth ", lineWidth + "");
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		
		resources = getResources();
		
		first = width /4;
		second = first * 2;
		third = first * 3;
		
	}
	
	private int currPosition = 0; 
	
	class MyViewPagerChangedListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int arg0) {
			Log.d("onchanged", "onchanged " + arg0);
			TranslateAnimation ta = null;
			switch (arg0) {
			case 0:
				
				if (currPosition == 1) {
					ta = new TranslateAnimation(first, 0, 0, 0);
				}
				if (currPosition == 2) {
					ta = new TranslateAnimation(second, 0, 0, 0);
				}
				if (currPosition == 3) {
					ta = new TranslateAnimation(third, 0, 0, 0);
				}
				
				break;
				
			case 1:
				
				if (currPosition == 0) {
					ta = new TranslateAnimation(0, first, 0, 0);
				}
				if (currPosition == 2) {
					ta = new TranslateAnimation(second, first, 0, 0);
				}
				if (currPosition == 3) {
					ta = new TranslateAnimation(third, first, 0, 0);
				}
				
				break;
				
			case 2:
				if (currPosition == 0) {
					ta = new TranslateAnimation(0, second, 0, 0);
				}
				if (currPosition == 1) {
					ta = new TranslateAnimation(first, second, 0, 0);
				}
				if (currPosition == 3) {
					ta = new TranslateAnimation(third, second, 0, 0);
				}
				break;
				
			case 3:
				if (currPosition == 0) {
					ta = new TranslateAnimation(0, third, 0, 0);
				}
				if (currPosition == 1) {
					ta = new TranslateAnimation(first, third, 0, 0);
				}
				if (currPosition == 2) {
					ta = new TranslateAnimation(second, third, 0, 0);
				}
				break;

				
			}
			
			currPosition = arg0;
			
			ta.setDuration(300);
			ta.setFillAfter(true);
			iv_bottom_line.startAnimation(ta);
		}
		
	}
	
	class MyClickListener implements OnClickListener{
		
		private int index =0;
		
		public MyClickListener (int i){
			index = i;
		}

		@Override
		public void onClick(View v) {
			mViewPager.setCurrentItem(index);
			
		}
		
	}

}
