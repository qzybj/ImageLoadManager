package com.brady.imageloadtest;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.brady.imageloadtest.common.ui.base.BaseActivity;
import com.brady.imageloadtest.module.imageload.adapter.adapters.FrescoAdapterBase;
import com.brady.imageloadtest.module.imageload.adapter.adapters.GlideAdapterBase;
import com.brady.imageloadtest.module.imageload.adapter.adapters.BaseImageListAdapter;
import com.brady.imageloadtest.module.imageload.adapter.adapters.ImageLoaderAdapter;
import com.brady.imageloadtest.module.imageload.adapter.adapters.PicassoAdapterBase;
import com.brady.imageloadtest.module.imageload.adapter.watcher.Drawables;
import com.brady.imageloadtest.module.imageload.adapter.watcher.WatchListener;
import com.brady.imageloadtest.module.imageload.utils.AdapterUtils;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    Snackbar mSnackbar;
    private static final String TAG = "MainActivity";
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    @Bind(R.id.tvShowInfo)
    TextView mTvShowInfo;

    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    private BaseImageListAdapter mAdapter;
    private WatchListener mWatchListener;
    private Handler mHandler;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            upTvShowInfo();
            scheduleNextShow();
        }
    };

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initContentView(View view) {
        mHandler = new Handler(getMainLooper());
        initView();
    }
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        scheduleNextShow();
    }

    @Override
    protected void onStop() {
        super.onStop();
        upTvShowInfo();
        cancelScheduleRunnable();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_clear:
                clearAllCache();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_fresco:
                loadData(new FrescoAdapterBase(MainActivity.this, mWatchListener));
                break;
            case R.id.nav_imageload:
                loadData(new ImageLoaderAdapter(this, mWatchListener));
                break;
            case R.id.nav_picasso:
                loadData(new PicassoAdapterBase(this, mWatchListener));
                break;
            case R.id.nav_glide:
                loadData(new GlideAdapterBase(this, mWatchListener));
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initView() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        Drawables.init(getResources());
        mWatchListener = new WatchListener();

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLoad();
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void scheduleNextShow() {
        mHandler.postDelayed(mRunnable, 1000);
    }

    private void cancelScheduleRunnable() {
        mHandler.removeCallbacks(mRunnable);
    }

    public final static long MB = 1024 * 1024;

    private void upTvShowInfo() {
        final Runtime runtime = Runtime.getRuntime();
        final float totalMemory = (runtime.totalMemory()) / MB;
        final float heapMemory = (runtime.totalMemory() - runtime.freeMemory()) / MB;
        String heapStr = String.format("已使用内存:%.1f M\t总共:%.1f M\n", heapMemory, totalMemory);
        String formatStr = "平均请求时间:%dms\t 总共加载次数:%d\t已完成次数:%d\t取消次数:%d";
        String result = String.format(formatStr, mWatchListener.getAverageRequestTime(),
                mWatchListener.getTotalRequests(), mWatchListener.getCompletedRequests(),
                mWatchListener.getCancelledRequests());

        mTvShowInfo.setText(heapStr + result);
    }
    private void refreshLoad() {
        clearMemoryCache();
        resetWatchListenerData();
    }

    private void loadData(BaseImageListAdapter adapter) {
        if(adapter!=null&&mRecyclerView!=null){
            mAdapter = adapter;
            adapter.setRandomDatas();
            mRecyclerView.setAdapter(adapter);
        }
    }
    private void clearAllCache() {
        if(mAdapter!=null){
            mAdapter.clear();
            mAdapter.notifyDataSetChanged();
        }
        AdapterUtils.clearAllCache();
    }

    private void clearMemoryCache() {
        if (mAdapter != null) {
            mAdapter.clear();
        }
    }
    private void resetWatchListenerData() {
        if (mWatchListener != null) {
            mWatchListener.resetData();
        }
    }
    @OnClick({R.id.fab})
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.fab:
                if(mSnackbar==null){
                    mSnackbar =  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null);
                }
                if(mSnackbar.isShown()){
                    mSnackbar.dismiss();
                }else{
                    mSnackbar.show();
                }
                break;
        }
    }
}