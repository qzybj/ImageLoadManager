package com.brady.library.interfaces;

import android.graphics.drawable.Drawable;
import android.view.animation.Transformation;
import android.widget.ImageView;
import java.util.List;

public interface IRequestCreator {

    IRequestCreator noDefaultImg();

    IRequestCreator defaultImg(int placeholderResId);

    IRequestCreator defaultImg(Drawable placeholderDrawable);

    IRequestCreator error(int errorResId);

    IRequestCreator error(Drawable errorDrawable);

    IRequestCreator resize(int targetWidth, int targetHeight);

    IRequestCreator centerCrop() ;

    IRequestCreator centerInside();

    IRequestCreator transform(Transformation transformation);

    IRequestCreator transform(List<? extends Transformation> transformations);

    void into(ImageView target);

    void into(ImageView target,IImgLoadListener listener);

}
