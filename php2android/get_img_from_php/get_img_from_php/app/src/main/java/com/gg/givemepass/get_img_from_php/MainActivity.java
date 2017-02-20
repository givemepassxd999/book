package com.gg.givemepass.get_img_from_php;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        adapter = new MyAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> paths;
        private String addr = "http://192.168.43.253:8888/img/";
        public MyAdapter() {
            paths = new ArrayList<>();
            paths.add(new StringBuilder(addr).append("butterfly.png").toString());
            paths.add(new StringBuilder(addr).append("car1.png").toString());
            paths.add(new StringBuilder(addr).append("car2.png").toString());
            paths.add(new StringBuilder(addr).append("car3.png").toString());
            paths.add(new StringBuilder(addr).append("car4.png").toString());
            paths.add(new StringBuilder(addr).append("cat.png").toString());
            paths.add(new StringBuilder(addr).append("flower.png").toString());
            paths.add(new StringBuilder(addr).append("hippo.png").toString());
            paths.add(new StringBuilder(addr).append("monkey.png").toString());
            paths.add(new StringBuilder(addr).append("mushroom.png").toString());
            paths.add(new StringBuilder(addr).append("monkey.png").toString());
            paths.add(new StringBuilder(addr).append("panda.png").toString());
            paths.add(new StringBuilder(addr).append("rabbit.png").toString());
            paths.add(new StringBuilder(addr).append("raccoon.png").toString());
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView img;
            public ViewHolder(View v) {
                super(v);
                img = (ImageView) v.findViewById(R.id.img);
            }
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            Glide.with(MainActivity.this)
                    .load(paths.get(position))
                    .error(R.drawable.ic_android_black_24dp)
                    .centerCrop()
                    .fitCenter()
                    .into(holder.img);
        }

        @Override
        public int getItemCount() {
            return paths.size();
        }
    }
}
