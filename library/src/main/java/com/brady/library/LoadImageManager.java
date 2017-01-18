package com.brady.library;

import android.app.Application;
import android.content.Context;
import com.brady.library.interfaces.ILoadImage;

/**
 * 图片加载统一控制类
 */
public class LoadImageManager{

    private static LoadImageManager instance=null;
    private ILoadImage loadImageInstance=null;
    private Context mContext;

    private LoadImageManager(Application con, ILoadImage loadImage) {
        mContext = con.getApplicationContext();
        loadImageInstance = loadImage;
    }

    public static void init(Application con, ILoadImage loadImage) {
        if (instance==null) {
            instance = new LoadImageManager(con,loadImage);
        }
    }

    public static ILoadImage instance() {
        if(instance==null){
            return null;
        }
        return instance.loadImageInstance;
    }
}