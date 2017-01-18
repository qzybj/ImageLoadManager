package com.brady.imageloadmanage;

import android.app.Application;

public class CApplication extends Application {
    private static Application instance;
    public static Application instance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
