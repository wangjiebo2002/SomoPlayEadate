package com.somoplay.somotab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.Window;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainTabActivity extends FragmentActivity implements OnClickListener,
		OnPageChangeListener
{

	private ViewPager mViewPager;
	protected List<Fragment> mTabs = new ArrayList<Fragment>();
	private String[] mTitles = new String[]
	{ "First Fragment !", "Second Fragment !", "Third Fragment !",
			"Fourth Fragment !", "Five Fragment !" };
	private FragmentPagerAdapter mAdapter;

	protected List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_tab);
		setOverflowButtonAlways();
		getActionBar().setDisplayShowHomeEnabled(false);

		initView();
		initTabFrame();
		mViewPager.setAdapter(mAdapter);
		initEvent();
	}

	/**
	 * 初始化所有事件
	 */
	private void initEvent()
	{
		mViewPager.setOnPageChangeListener(this);
	}

	//default four fragments
	protected void initTabFragment()
	{
		for (String title : mTitles)
		{
			TabFragment tabFragment = new TabFragment();
			Bundle bundle = new Bundle();
			bundle.putString(TabFragment.TITLE, title);
			tabFragment.setArguments(bundle);
			mTabs.add(tabFragment);
		}
	}

	private void initTabFrame()
	{
		initTabFragment();

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public int getCount()
			{
				return mTabs.size();
			}

			@Override
			public Fragment getItem(int position)
			{
				return mTabs.get(position);
			}
		};
	}

	private void initView()
	{
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

		ChangeColorIconWithText one = (ChangeColorIconWithText) findViewById(R.id.id_indicator_one);
		mTabIndicators.add(one);
		ChangeColorIconWithText two = (ChangeColorIconWithText) findViewById(R.id.id_indicator_two);
		mTabIndicators.add(two);
		ChangeColorIconWithText three = (ChangeColorIconWithText) findViewById(R.id.id_indicator_three);
		mTabIndicators.add(three);
		ChangeColorIconWithText four = (ChangeColorIconWithText) findViewById(R.id.id_indicator_four);
		mTabIndicators.add(four);
		ChangeColorIconWithText five = (ChangeColorIconWithText) findViewById(R.id.id_indicator_five);
		mTabIndicators.add(five);

		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
//		four.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Log.d("MainTabAcitivity","Test");
//			}
//		});
        four.setOnClickListener(this);
		five.setOnClickListener(this);

		one.setIconAlpha(1.0f);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_main_tab, menu);
		return true;
	}

	private void setOverflowButtonAlways()
	{
		try
		{
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKey = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			menuKey.setAccessible(true);
			menuKey.setBoolean(config, false);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 设置menu显示icon
	 */
	@Override
	public boolean onMenuOpened(int featureId, Menu menu)
	{

		if (featureId == Window.FEATURE_ACTION_BAR && menu != null)
		{
			if (menu.getClass().getSimpleName().equals("MenuBuilder"))
			{
				try
				{
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}

		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public void onClick(View v)
	{
		clickTab(v);

	}

	/**
	 * 点击Tab按钮
	 * 
	 * @param v
	 */
	private void clickTab(View v)
	{
		resetOtherTabs();

        if(v.getId() == R.id.id_indicator_one){
            mTabIndicators.get(0).setIconAlpha(1.0f);
            mViewPager.setCurrentItem(0, false);
        }
        else if(v.getId() == R.id.id_indicator_two){
            mTabIndicators.get(1).setIconAlpha(1.0f);
            mViewPager.setCurrentItem(1, false);
        }
        else if(v.getId() == R.id.id_indicator_three){
            mTabIndicators.get(2).setIconAlpha(1.0f);
            mViewPager.setCurrentItem(2, false);
        }
        else if(v.getId() == R.id.id_indicator_four){
            mTabIndicators.get(3).setIconAlpha(1.0f);
            mViewPager.setCurrentItem(3, false);
        }
		else if(v.getId() == R.id.id_indicator_five){
			mTabIndicators.get(4).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(4, false);
		}
	}

	/**
	 * 重置其他的TabIndicator的颜色
	 */
	private void resetOtherTabs()
	{
		for (int i = 0; i < mTabIndicators.size(); i++)
		{
			mTabIndicators.get(i).setIconAlpha(0);
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels)
	{
		// Log.e("TAG", "position = " + position + " ,positionOffset =  "
		// + positionOffset);
		if (positionOffset > 0)
		{
			ChangeColorIconWithText left = mTabIndicators.get(position);
			ChangeColorIconWithText right = mTabIndicators.get(position + 1);
			left.setIconAlpha(1 - positionOffset);
			right.setIconAlpha(positionOffset);
		}

	}

	@Override
	public void onPageSelected(int position)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrollStateChanged(int state)
	{
		// TODO Auto-generated method stub

	}

}
