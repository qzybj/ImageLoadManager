package com.brady.imageloadmanage;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.brady.imageloadmanage.common.ui.base.BaseActivity;
import com.brady.imageloadmanage.module.imageload.adapter.config.PicassoConfigFactory;
import com.brady.imageloadmanage.module.imageload.adapter.watcher.Drawables;
import com.brady.imageloadmanage.module.imageload.model.Data;
import com.brady.library.LoadImageManager;
import com.brady.library.interfaces.ILoadImage;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import butterknife.Bind;

import static com.brady.imageloadmanage.R.string.picasso;


public class TestActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    @Bind(R.id.tvShowInfo)
    TextView mTvShowInfo;

    private PicassoTestAdapter mAdapter;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test;
    }

    @Override
    public void initContentView(View view) {
        initView();
    }
    @Override
    public void initData(Bundle savedInstanceState) {
        loadData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initView() {
        Drawables.init(getResources());

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                mRefreshLayout.setRefreshing(false);
            }
        });
    }
    private void refresh() {


    }

    private void loadData() {
        if(mRecyclerView!=null){
            List list = new ArrayList();
            Collections.addAll(list, Data.URLS);
            mAdapter = new PicassoTestAdapter(R.layout.recycler_item,list);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.fab:

                break;
        }
    }

    public class PicassoTestAdapter extends BaseQuickAdapter<String> {
        Picasso mPicasso;
        public PicassoTestAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
            if (mPicasso == null) {
                mPicasso = PicassoConfigFactory.getPicasso(TestActivity.this);
            }
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, String s) {
            LoadImageManager.instance().get().loadImage(((ImageView)viewHolder.getView(R.id.iv_left)),s, ILoadImage.NONE);
            viewHolder.setText(R.id.tv_right,s);
        }
    }
}