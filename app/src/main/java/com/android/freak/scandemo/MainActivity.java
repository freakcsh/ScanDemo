package com.android.freak.scandemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.freak.scandemo.zxing.ImageUtil;
import com.android.freak.scandemo.zxing.activity.CaptureActivity;
import com.android.freak.scandemo.zxing.activity.CodeUtils;

public class MainActivity extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫码
                if (PermissionUtils.check2dCameraPermission(MainActivity.this)) {
                    Intent intent = new Intent(MyApp.getMyApp(), CaptureActivity.class);
                    startActivityForResult(intent, IETConstant.REQUEST_CODE);
                }
            }
        });
    }


    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /**
         * 处理二维码扫描结果
         */
        if (requestCode == IETConstant.REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }

                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Log.e("TAG",result);

//                    Toast.makeText(getMContext(), getResources().getString(R.string.homePage_analysis_result) + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                    Toast.makeText(getMContext(), getResources().getString(R.string.homePage_getCode_error), Toast.LENGTH_LONG).show();
                }
            }
        }

        /**
         * 选择系统图片并解析
         */
        else if (requestCode == IETConstant.REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
//                            Toast.makeText(getMContext(), getResources().getString(R.string.homePage_analysis_result) + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
//                            Toast.makeText(getMContext(), getResources().getString(R.string.homePage_getCode_error), Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == IETConstant.REQUEST_CAMERA_PERM) {
//            Toast.makeText(getMContext(), getResources().getString(R.string.homePage_from_setting_come_back), Toast.LENGTH_SHORT).show();
        }
    }
}
