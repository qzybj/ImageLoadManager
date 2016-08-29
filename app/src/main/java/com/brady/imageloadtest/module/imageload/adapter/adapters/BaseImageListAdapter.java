package com.brady.imageloadtest.module.imageload.adapter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.brady.imageloadtest.CApplication;
import com.brady.imageloadtest.module.imageload.adapter.watcher.WatchListener;
import com.brady.imageloadtest.module.imageload.model.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nevermore on 16/7/2.
 */
public abstract class BaseImageListAdapter extends RecyclerView.Adapter {
    private List<String> mDatas;
    private final WatchListener mWatchListener;

    public BaseImageListAdapter(Context context, WatchListener watchListener) {
        mWatchListener = watchListener;
        mDatas = new ArrayList<>();
    }

    protected void addUrl(String url) {
        mDatas.add(url);
    }

    public void setDatas() {
        Collections.addAll(mDatas, Data.URLS);
        List<String> copyDatas = new ArrayList<>(mDatas);
    }

    public void setRandomDatas() {
        mDatas.clear();
        Collections.addAll(mDatas, Data.URLS);
    }

    protected Context getContext() {
        return CApplication.instance();
    }

    protected WatchListener getWatchListener() {
        return mWatchListener;
    }

    public String getItem(final int position) {
        return mDatas.get(position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public abstract void clear();

}