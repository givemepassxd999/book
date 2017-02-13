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

        public MyAdapter() {
            paths = new ArrayList<>();
            paths.add("http://192.168.56.1:7777/img/butterfly.png");
            paths.add("http://192.168.56.1:7777/img/car1.png");
            paths.add("http://192.168.56.1:7777/img/car2.png");
            paths.add("http://192.168.56.1:7777/img/car3.png");
            paths.add("http://192.168.56.1:7777/img/car4.png");
            paths.add("http://192.168.56.1:7777/img/cat.png");
            paths.add("http://192.168.56.1:7777/img/flower.png");
            paths.add("http://192.168.56.1:7777/img/hippo.png");
            paths.add("http://192.168.56.1:7777/img/monkey.png");
            paths.add("http://192.168.56.1:7777/img/mushroom.png");
            paths.add("http://192.168.56.1:7777/img/panda.png");
            paths.add("http://192.168.56.1:7777/img/rabbit.png");
            paths.add("http://192.168.56.1:7777/img/raccoon.png");
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
