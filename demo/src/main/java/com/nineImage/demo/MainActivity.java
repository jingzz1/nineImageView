package com.nineImage.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nineimageview.view.NineImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adapter = new ImagesAdapter();
        recyclerView.setAdapter(adapter);

        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i < 9 ; i++){
            list.add(i);
        }
        adapter.setNewData(list);

    }

    private static class ImagesAdapter extends BaseQuickAdapter<Integer,BaseViewHolder>{

        private List<String> images = new ArrayList<>();

        public ImagesAdapter() {
            super(R.layout.images_adapter_item);
            initImages();
        }

        @Override
        protected void convert(BaseViewHolder helper, Integer item) {
            int position = helper.getLayoutPosition();
            helper.setText(R.id.text,(position+1)+"张图排列");
            NineImageView imagesView = helper.getView(R.id.images);

            List<String> list = new ArrayList<>();
            for (int i = 0 ; i <= position; i++){
                list.add(images.get((position+i)%images.size()));
            }
            imagesView.addImages(list);
            imagesView.setOnClickImageListener(new NineImageView.OnClickChildListener() {
                @Override
                public void onClickChildListener(View view, int position) {
                    Toast.makeText(mContext, "点击了第"+(position+1)+"张图", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void initImages() {
            images.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3753580082,2836079710&fm=26&gp=0.jpg");
            images.add("http://img4.imgtn.bdimg.com/it/u=2233601401,1911978501&fm=26&gp=0.jpg");
            images.add("http://img4.imgtn.bdimg.com/it/u=785911902,692744506&fm=26&gp=0.jpg");
            images.add("http://img5.imgtn.bdimg.com/it/u=3306528693,2467068810&fm=26&gp=0.jpg");
            images.add("http://img5.imgtn.bdimg.com/it/u=1595200400,3373330705&fm=26&gp=0.jpg");
            images.add("http://img5.imgtn.bdimg.com/it/u=1612692695,1299881634&fm=26&gp=0.jpg");
            images.add("http://img3.imgtn.bdimg.com/it/u=1231306644,1215519357&fm=26&gp=0.jpg");
            images.add("http://img3.imgtn.bdimg.com/it/u=3661127165,2132368286&fm=26&gp=0.jpg");
            images.add("http://img0.imgtn.bdimg.com/it/u=2867043378,2694439312&fm=26&gp=0.jpg");
            images.add("http://img1.imgtn.bdimg.com/it/u=2912834953,2410691374&fm=26&gp=0.jpg");
        }
    }

}
