package com.brady.imageloadtest.module.imageload.adapter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.brady.imageloadtest.module.imageload.adapter.holders.GlideHolder;
import com.brady.imageloadtest.module.imageload.adapter.watcher.WatchImageView;
import com.brady.imageloadtest.module.imageload.adapter.watcher.WatchListener;
import com.bumptech.glide.Glide;


/**
 * Created by Nevermore on 16/7/1.
 */
public class GlideAdapterBase extends BaseImageListAdapter {
    public GlideAdapterBase(Context context, WatchListener watchListener) {
        super(context, watchListener);
    }

    @Override
    public GlideHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final WatchImageView watchImageView = new WatchImageView(getContext());
        return new GlideHolder(watchImageView, getWatchListener(), parent, getContext());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GlideHolder glideHolder = (GlideHolder) holder;
        glideHolder.bind(getItem(position));
    }

    @Override
    public void clear() {
        Glide.get(getContext()).clearMemory();
        Glide.get(getContext()).clearDiskCache();
    }
}
