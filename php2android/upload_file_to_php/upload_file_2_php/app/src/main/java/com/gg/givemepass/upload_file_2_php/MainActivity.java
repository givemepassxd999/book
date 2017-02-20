package com.gg.givemepass.upload_file_2_php;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private Button upload;
    private TextView resMsg;
    private ExecutorService executorService;
    private FileUpload fileUpload;
    private static final int REQUEST_EXTERNAL_STORAGE = 100;
    private static final String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/butterfly.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        upload = (Button) findViewById(R.id.upload);
        resMsg = (TextView) findViewById(R.id.msg);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permission = ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    //未取得權限，向使用者要求允許權限
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_EXTERNAL_STORAGE
                    );
                } else{
                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            File f = new File(filePath);
                            if(f.exists()) {
                                fileUpload.doFileUpload(filePath);
                            } else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        resMsg.setText("file is not exist");
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            File f = new File(filePath);
                            if(f.exists()) {
                                fileUpload.doFileUpload(filePath);
                            } else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        resMsg.setText("file is not exist");
                                    }
                                });
                            }
                        }
                    });
                } else {
                    finish();
                }
            return;

        }
    }

    private void initData() {
        executorService = Executors.newSingleThreadExecutor();
        fileUpload = new FileUpload();
        fileUpload.setOnFileUploadListener(new FileUpload.OnFileUploadListener() {
            @Override
            public void onFileUploadSuccess(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resMsg.setText(msg);
                    }
                });

            }

            @Override
            public void onFileUploadFail(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resMsg.setText(msg);
                    }
                });
            }
        });
    }
}
