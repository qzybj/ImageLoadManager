package com.brady.imageloadmanage.common.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.brady.imageloadmanage.common.utils.LogUtils;
import com.brady.imageloadmanage.common.utils.ViewUtils;
import butterknife.ButterKnife;

/**
 * 框架 - Activity的base基类，只包含最基本的
 */
public abstract class BaseActivity extends AppCompatActivity {
	private Activity mBaseActivity;
	private View mRootView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBaseActivity= this;
		int layoutResId = getLayoutResId();
		if (layoutResId>0) {
			try {
				mRootView = ViewUtils.inflateView(getBaseActivity(),layoutResId);
			} catch (Exception e) {
				LogUtils.e(e);
			}
			if (mRootView != null) {
				setContentView(mRootView);
				ButterKnife.bind(this);
				initContentView(mRootView);
			}
		}
		initConstant(savedInstanceState);
		initData(savedInstanceState);
	}

	public Activity getBaseActivity(){
		return mBaseActivity;
	}

	protected abstract int getLayoutResId();
	public abstract void initContentView(View view);
	public void initConstant(Bundle savedInstanceState) {}
	public abstract void initData(Bundle savedInstanceState);

}