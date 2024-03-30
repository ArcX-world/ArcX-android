package net.daylong.baselibrary.app;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;


/**
 * @author
 * @Company 广州大龙网络科技有限公司
 * @Description
 * @Date 2020/1/3
 * @Version 1.0
 */
@GlideModule
public class GlideCache extends AppGlideModule {
    //设置缓存大小
    private int size = 1024 * 1024 * 100;

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        //判断SD卡是否挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //设置磁盘外部缓存
            builder.setDiskCache(new DiskLruCacheFactory(Constant.iMAGE_CACHE_PATH, size));
        }
    }
}
