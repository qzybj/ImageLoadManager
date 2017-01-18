package com.brady.library;

import android.app.Application;
import android.content.Context;
import com.brady.library.interfaces.ILoadImage;
import com.brady.library.interfaces.IRequestCreator;
import com.brady.library.interfaces.impl.RequestCreator;

/**
 * 图片加载统一控制类
 */
public class LoadImageManager{

    private static LoadImageManager instance = null;
    private ILoadImage loadImageInstance = null;
    private Context mContext;

    private LoadImageManager() {}

    public void init(Application con, ILoadImage loadImage) {
        mContext = con.getApplicationContext();
        loadImageInstance = loadImage;
        if(loadImageInstance!=null){
            loadImageInstance.build(mContext);
        }
    }

    public static LoadImageManager instance() {
        if (instance == null) {
            synchronized (LoadImageManager.class) {
                if (instance == null) {
                    instance = new LoadImageManager();
                }
            }
        }
        return instance;
    }

    public ILoadImage get() {
        return loadImageInstance;
    }

    public IRequestCreator load(Object object) {
        return new RequestCreator(this, object);
    }

}