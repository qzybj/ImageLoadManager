package com.brady.imageloadmanage;

import android.app.Application;

import com.brady.imageloadmanage.module.aosp.imageloadimpl.picasso.PicassoHelper;
import com.brady.library.LoadImageManager;

public class CApplication extends Application {
    private static Application instance;
    public static Application instance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LoadImageManager.instance().init(this,new PicassoHelper());
    }
}
