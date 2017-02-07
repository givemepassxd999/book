package com.gg.givemepass.get_data_from_php;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mQueue;
    private final static String mUrl = "http://10.1.17.111:8888/server_send_data.php";
    private TextView msg;
    private Button mGetMsgButton;
    private StringRequest getRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msg = (TextView) findViewById(R.id.msg);
        mGetMsgButton = (Button) findViewById(R.id.get_msg);
        mQueue = Volley.newRequestQueue(this);
        getRequest = new StringRequest(mUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        msg.setText(s);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        msg.setText(volleyError.getMessage());
                    }
                });
        mGetMsgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQueue.add(getRequest);
            }
        });

    }
}
