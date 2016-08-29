package com.brady.imageloadtest.module.imageload.adapter.holders;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.brady.imageloadtest.module.imageload.adapter.watcher.Drawables;
import com.brady.imageloadtest.module.imageload.adapter.watcher.WatchImageView;
import com.brady.imageloadtest.module.imageload.adapter.watcher.WatchListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by Nevermore on 16/7/3.
 */
public class GlideHolder extends BaseHolder<WatchImageView> {

    public GlideHolder(WatchImageView imageView, WatchListener watchListener, View parentView, Context context) {
        super(imageView, watchListener, parentView, context);
    }

    public final static String TAG = "GlideHolder";
    private RequestListener<String, GlideDrawable> mRequestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            Log.w(TAG, "onException: ", e);
            Log.d(TAG, "onException: " + model);
            Log.d(TAG, "onException: " + target.getRequest().isRunning());
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            return false;
        }
    };

    @Override
    protected void onBind(String url) {
        Glide.with(getContext())
                .load(url)
                .listener(mRequestListener)//配置监听器
                .placeholder(Drawables.sPlaceholderDrawable)
                .error(Drawables.sErrorDrawable)
                .centerCrop()
                .skipMemoryCache(true) //不使用内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE) //不使用硬盘缓存
                .into(mImageView);
    }
}

