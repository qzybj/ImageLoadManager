package com.brady.imageloadtest.module.imageload.adapter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.brady.imageloadtest.module.imageload.adapter.config.FrescoConfigFactory;
import com.brady.imageloadtest.module.imageload.adapter.holders.FrescoHolder;
import com.brady.imageloadtest.module.imageload.adapter.watcher.Drawables;
import com.brady.imageloadtest.module.imageload.adapter.watcher.WatchDraweeImage;
import com.brady.imageloadtest.module.imageload.adapter.watcher.WatchListener;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;

/**
 * Created by Administrator on 2016/7/4.
 */
public class FrescoAdapterBase extends BaseImageListAdapter {

    public FrescoAdapterBase(Context context, WatchListener watchListener) {
        super(context, watchListener);
        Fresco.initialize(context, FrescoConfigFactory.getImagePipelineConfig(context));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GenericDraweeHierarchy genericDraweeHierarchy = new GenericDraweeHierarchyBuilder(getContext().getResources())
                .setPlaceholderImage(Drawables.sPlaceholderDrawable)
                .setFailureImage(Drawables.sErrorDrawable)
//                .setProgressBarImage(new ProgressBarDrawable())
                .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
                .build();
        WatchDraweeImage watchDraweeImage = new WatchDraweeImage(getContext(), genericDraweeHierarchy);
        return new FrescoHolder(watchDraweeImage, getWatchListener(), parent, getContext());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FrescoHolder frescoHolder = (FrescoHolder) holder;
        frescoHolder.bind(getItem(position));
    }

    @Override
    public void clear() {
        Fresco.shutDown();
    }
}
