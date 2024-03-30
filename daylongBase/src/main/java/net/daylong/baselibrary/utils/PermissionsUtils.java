package net.daylong.baselibrary.utils;


import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import net.daylong.baselibrary.utils.ui.act.BaseActivity;


public class PermissionsUtils {

    private static PermissionsUtils instance = null;

    //    private
    public static synchronized PermissionsUtils getInstance() {
        if (instance == null) {
            synchronized (PermissionsUtils.class) {
                instance = new PermissionsUtils();

            }
        }
        return instance;
    }



    public void requestPermissions(BaseActivity activity) {
        // 是否已经授权
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, 10001);
            } else {//没有则请求获取权限，示例权限是：存储权限和短信权限，需要其他权限请更改或者替换
                ActivityCompat.requestPermissions(activity,
                        new String[]{
                                Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        }, 10001);
            }
        } else {//如果已经获取到了权限则直接进行下一步操作

        }

    }

}
