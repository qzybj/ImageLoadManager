package com.brady.library.interfaces.impl;

import android.graphics.drawable.Drawable;
import android.view.animation.Transformation;
import android.widget.ImageView;

import com.brady.library.LoadImageManager;
import com.brady.library.interfaces.IImgLoadListener;
import com.brady.library.interfaces.IRequestCreator;

import java.util.List;


public class RequestCreator implements IRequestCreator {
    public RequestCreator(LoadImageManager loadImageManager, Object object) {

    }

    @Override
    public IRequestCreator noDefaultImg() {
        return null;
    }

    @Override
    public IRequestCreator defaultImg(int placeholderResId) {
        return null;
    }

    @Override
    public IRequestCreator defaultImg(Drawable placeholderDrawable) {
        return null;
    }

    @Override
    public IRequestCreator error(int errorResId) {
        return null;
    }

    @Override
    public IRequestCreator error(Drawable errorDrawable) {
        return null;
    }

    @Override
    public IRequestCreator resize(int targetWidth, int targetHeight) {
        return null;
    }

    @Override
    public IRequestCreator centerCrop() {
        return null;
    }

    @Override
    public IRequestCreator centerInside() {
        return null;
    }

    @Override
    public IRequestCreator transform(Transformation transformation) {
        return null;
    }

    @Override
    public IRequestCreator transform(List<? extends Transformation> transformations) {
        return null;
    }

    @Override
    public void into(ImageView target) {

    }

    @Override
    public void into(ImageView target, IImgLoadListener listener) {

    }
}
