package com.android.freak.scandemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


/**
 * Created by lzj on 2017/9/9.
 */

public class PermissionUtils {


    // 判断权限集合
    public static boolean lacksPermissions(Context mContext, String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(mContext, permission)) {
                return true;
            }
        }
        return false;
    }

    // 判断是否缺少权限
    private static boolean lacksPermission(Context mContext, String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) ==
                PackageManager.PERMISSION_DENIED;
    }


    /**
     * Android 版本低于6.0时，或权限都满足时，返回true
     * 选择“拍照”时，需要的权限
     *
     * @return
     */
    public static boolean checkTakePhotoPermission(Context mContext) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            //6.0以上需要WRITE_EXTERNAL_STORAGE和CAMERA两个权限才能打开相机
            int cameraPermission = ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA);
            int writeExternalPermission = ActivityCompat.checkSelfPermission(mContext, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE);
            if (writeExternalPermission != PackageManager.PERMISSION_GRANTED || cameraPermission != PackageManager
                    .PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) mContext,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        IETConstant.TAKEPHOTO_PERMISSION_REQUESTCODE);

                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * Android 版本低于6.0时，或权限都满足时，返回true
     * 选择“从相册选择”时，需要的权限
     *
     * @return
     */
    public static boolean checkAlbumStroagePermission(Context mContext) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int writeExternalPermission = ActivityCompat.checkSelfPermission(mContext, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE);
            if (writeExternalPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) mContext,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        IETConstant.ALBUM_PERMISSION_REQUESTCODE);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * 定位所需权限都满足时，返回true
     *
     * @param mContext
     * @return
     */
    public static boolean checkLocationPermission(Context mContext, int requestCode) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) mContext,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    requestCode);
            return false;
        } else {
            return true;
        }

    }

    /**
     * 打开扫描二维码
     *
     * @param mContext
     * @return
     */
    public static boolean check2dCameraPermission(Context mContext) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            //6.0以上需要WRITE_EXTERNAL_STORAGE和CAMERA两个权限才能打开相机
            int cameraPermission = ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA);
            int writeExternalPermission = ActivityCompat.checkSelfPermission(mContext, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE);
            if (writeExternalPermission != PackageManager.PERMISSION_GRANTED || cameraPermission != PackageManager
                    .PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) mContext,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        0);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


}
