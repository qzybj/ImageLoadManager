package com.brady.imageloadmanage.module.aosp.imageloadimpl.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.brady.library.interfaces.ILoadImage;
import com.brady.library.interfaces.IImgLoadListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;
import java.io.File;


/**
 * 基于Picasso的ILoadImage实现
 */
public class PicassoHelper implements ILoadImage {
    private Context mContext;

    @Override
    public void build(Context con) {
        this.mContext = con;
    }

    public void loadImage( ImageView iv, Object imageUrl, int loadImgResId) {
        loadImage(mContext,iv,imageUrl,NONE,NONE,loadImgResId,NONE,false,null,null);
    }

    @Override
    public void loadImage( ImageView iv, Object imageUrl, int loadImgResId, int errImgResId, IImgLoadListener callback) {
        loadImage(mContext,iv,imageUrl,NONE,NONE,loadImgResId,errImgResId,false,null,callback);
    }

    @Override
    public void loadImage( ImageView iv, Object imageUrl, int width, int height, Object loadImgResId, Object errImgResId, IImgLoadListener callback) {
        loadImage(mContext,iv,imageUrl,width,height,loadImgResId,errImgResId,false,null,callback);
    }


    /**
     *  加载图片(Picasso)
     * @param con
     * @param iv
     * @param imageUrl       支持的格式：除正常的ImageUrl字符串外，load(R.drawable.icon); load("file:///android_asset/icon.png");load(new File(...))
     * @param width          指定的图片宽
     * @param height         指定的图片高
     * @param loadImgResId   默认加载的图片
     * @param errImgResId    加载错误时的图片
     * @param isTransform    是否加载动画
     * @param callback
     */
    public void loadImage(Context con, ImageView iv, Object imageUrl, int width, int height, Object loadImgResId, Object errImgResId, boolean isTransform, Transformation transformation, final IImgLoadListener callback) {
        if(con!=null&&iv!=null){
            RequestCreator requestCreator =null;
            if(imageUrl instanceof String){
                String imageStr = (String)imageUrl;
                if(imageStr.trim().length()>0){
                    requestCreator = Picasso.with(con).load(imageStr);
                }
            }else if(imageUrl instanceof Integer){
                int imageInt = (int)imageUrl;
                if(imageInt>0){
                    requestCreator = Picasso.with(con).load(imageInt);
                }
            }else if(imageUrl instanceof File){
                File file = (File)imageUrl;
                if(file.exists()){
                    requestCreator = Picasso.with(con).load(file);
                }
            }else if(imageUrl instanceof Uri){
                Uri uri = (Uri)imageUrl;
                requestCreator = Picasso.with(con).load(uri);
            }
            if(requestCreator!=null){
                //requestCreator.skipMemoryCache();
                requestCreator.config(Bitmap.Config.ARGB_8888);
                //requestCreator.fit();
                if(width!= ILoadImage.NONE&&height!= ILoadImage.NONE){
                    requestCreator.resize(width, height);
                }
                if(isTransform){
                    if(transformation!=null){
                        requestCreator.transform(transformation);
                    }else{
                        if(width!= ILoadImage.NONE&&height!= ILoadImage.NONE){
                            transformation = new ScaleTransformation(width, height);
                            requestCreator.transform(transformation);
                        }
                    }
                }
                if (callback != null) {
                    requestCreator.fetch(new Callback(){
                        @Override
                        public void onSuccess() {
                            callback.onSuccess();
                        }
                        @Override
                        public void onError() {
                            callback.onError();
                        }
                    });
                }
                if(loadImgResId instanceof Integer){
                    int loadID = (int)loadImgResId;
                    if (loadID!= ILoadImage.NONE&& loadID>0) {
                        requestCreator.placeholder(loadID);//占位
                    }
                }else if( loadImgResId instanceof Drawable){
                    requestCreator.placeholder((Drawable)loadImgResId);//占位
                }

                if( errImgResId instanceof Integer){
                    int errId = (int)errImgResId;
                    if (errId!= ILoadImage.NONE&&errId>0) {
                        requestCreator.error(errId);//错误
                    }
                }else if( errImgResId instanceof Drawable){
                    requestCreator.error((Drawable)errImgResId);
                }

                requestCreator.into(iv);
            }else{
                iv.setImageBitmap(null);
            }
        }
    }

    @Override
    public  boolean isSupportImgUrlType(Object imageUrl){
        if(imageUrl instanceof String){
            return true;
        }
        if(imageUrl instanceof Integer){
            return true;
        }
        if(imageUrl instanceof File){
            return true;
        }
        if(imageUrl instanceof Uri){
            return true;
        }
        return false;
    }
}
