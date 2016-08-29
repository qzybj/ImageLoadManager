package com.brady.imageloadtest.module.imageload.adapter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.brady.imageloadtest.module.imageload.adapter.config.ImageLoaderFactory;
import com.brady.imageloadtest.module.imageload.adapter.holders.ImageLoaderHolder;
import com.brady.imageloadtest.module.imageload.adapter.watcher.WatchImageView;
import com.brady.imageloadtest.module.imageload.adapter.watcher.WatchListener;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2016/7/4.
 */
public class ImageLoaderAdapter extends BaseImageListAdapter {

    final private ImageLoader mImageLoader;

    public ImageLoaderAdapter(Context context, WatchListener watchListener) {
        super(context, watchListener);
        mImageLoader = ImageLoaderFactory.getImageLoader(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WatchImageView watchImageView = new WatchImageView(getContext());
        return new ImageLoaderHolder(watchImageView, getWatchListener(), parent, getContext(), mImageLoader);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageLoaderHolder imageLoaderHolder = (ImageLoaderHolder) holder;
        imageLoaderHolder.bind(getItem(position));
    }

    @Override
    public void clear() {
        mImageLoader.clearMemoryCache();
        mImageLoader.clearDiskCache();
    }

}
