package com.brady.imageloadmanage.module.imageload.adapter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.brady.imageloadmanage.module.imageload.adapter.config.PicassoConfigFactory;
import com.brady.imageloadmanage.module.imageload.adapter.holders.PicassoHolder;
import com.brady.imageloadmanage.module.imageload.adapter.watcher.WatchImageView;
import com.brady.imageloadmanage.module.imageload.adapter.watcher.WatchListener;
import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2016/7/4.
 */
public class PicassoAdapterBase extends BaseImageListAdapter {
    private Picasso mPicasso;

    public PicassoAdapterBase(Context context, WatchListener watchListener) {
        super(context, watchListener);
        if (mPicasso == null) {
            mPicasso = PicassoConfigFactory.getPicasso(context);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WatchImageView watchImageView = new WatchImageView(getContext());
        return new PicassoHolder(watchImageView, getWatchListener(), parent, getContext(), mPicasso);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PicassoHolder picassoHolder = (PicassoHolder) holder;
        picassoHolder.bind(getItem(position));
    }

    @Override
    public void clear() {
        for (int i = 0; i < getItemCount(); i++) {
            mPicasso.invalidate(getItem(i));
        }
    }
}
