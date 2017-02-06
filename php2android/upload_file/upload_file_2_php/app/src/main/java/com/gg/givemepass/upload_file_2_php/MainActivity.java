package com.gg.givemepass.upload_file_2_php;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private Button upload;
    private TextView resMsg;
    private ExecutorService executorService;
    private FileUpload fileUpload;
    private static final String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/butterfly.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        upload = (Button) findViewById(R.id.upload);
        resMsg = (TextView) findViewById(R.id.msg);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        fileUpload.doFileUpload(filePath);
                    }
                });
            }
        });
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
