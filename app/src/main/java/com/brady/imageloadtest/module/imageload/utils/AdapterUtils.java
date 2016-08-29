package com.brady.imageloadtest.module.imageload.utils;

import com.brady.imageloadtest.CApplication;
import com.brady.imageloadtest.module.imageload.adapter.config.ImageLoaderFactory;
import com.brady.imageloadtest.module.imageload.adapter.config.PicassoConfigFactory;
import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.picasso.PicassoTools;

/**
 * Created by ZhangYuanBo on 2016/8/29.
 */
public class AdapterUtils {

    public static void clearAllCache() {
        Fresco.shutDown();
        PicassoTools.clearCache(PicassoConfigFactory.getPicasso(CApplication.instance()));
        Glide.get(CApplication.instance()).clearMemory();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(CApplication.instance()).clearDiskCache();
            }
        }).start();
        ImageLoaderFactory.getImageLoader(CApplication.instance()).clearMemoryCache();
        ImageLoaderFactory.getImageLoader(CApplication.instance()).clearDiskCache();
    }
}
