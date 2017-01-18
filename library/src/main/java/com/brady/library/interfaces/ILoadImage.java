package com.brady.library.interfaces;

import android.content.Context;
import android.widget.ImageView;

/**
 * 需要实现定制的图片加载
 */
public interface ILoadImage {

	/**Is null*/
    int NONE = -1;

	/**
	 * @param con
     */
	void build(Context con);

	/**
	 * @param iv
	 * @param imageUrl      支持的格式
	 * @param loadImgResId  默认加载的图片
	 */
    void loadImage(ImageView iv, Object imageUrl, int loadImgResId);

	/**
	 * @param iv
	 * @param imageUrl      支持的格式
	 * @param loadImgResId  默认加载的图片
	 * @param errImgResId   加载错误时的图片
	 * @param callback		加载图片结果回调
	 */
   void loadImage( ImageView iv, Object imageUrl, int loadImgResId, int errImgResId, ILoadImageCallback callback);

   /**
     * @param iv
     * @param imageUrl		
     * @param width			指定的图片宽
     * @param height		指定的图片高
     * @param loadImgResId	默认加载的图片
     * @param errImgResId   加载错误时的图片
     * @param callback		加载图片结果回调
     */
    void loadImage(ImageView iv, Object imageUrl, int width, int height, Object loadImgResId, Object errImgResId,ILoadImageCallback callback);

	
    /**
     * 加载图片是否支持该格式ImageUrl
     * @param imageUrl  支持的格式 sample：load(R.drawable.landing_screen); load("file:///android_asset/DvpvklR.png");load(new File(...))
     * @return
     */
    boolean isSupportImageUrlType(Object imageUrl);
}