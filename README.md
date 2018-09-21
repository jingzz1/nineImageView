# nineImageView

自定义九宫格控件，支持设置图片边距，加边框，设置圆角图片<br><br>


```
    <com.nineimageview.view.NineImageView
        android:id="@+id/NineImageView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:niv_radius="6dp"
        app:niv_margin="4dp"
        app:niv_border_width="1dp"
        app:niv_border_color="@color/colorAccent"/>
```

```
        NineImageView imagesView = findViewById(R.id.NineImageView);
        List<String> images = new ArrayList<>();
        images.add("http://img4.imgtn.bdimg.com/it/u=2233601401,1911978501&fm=26&gp=0.jpg");
        images.add("http://img4.imgtn.bdimg.com/it/u=785911902,692744506&fm=26&gp=0.jpg");
        images.add("http://img5.imgtn.bdimg.com/it/u=3306528693,2467068810&fm=26&gp=0.jpg");
        images.add("http://img5.imgtn.bdimg.com/it/u=1595200400,3373330705&fm=26&gp=0.jpg");
        images.add("http://img5.imgtn.bdimg.com/it/u=1612692695,1299881634&fm=26&gp=0.jpg");
        images.add("http://img3.imgtn.bdimg.com/it/u=1231306644,1215519357&fm=26&gp=0.jpg");
        images.add("http://img3.imgtn.bdimg.com/it/u=3661127165,2132368286&fm=26&gp=0.jpg");
        images.add("http://img0.imgtn.bdimg.com/it/u=2867043378,2694439312&fm=26&gp=0.jpg");
        images.add("http://img1.imgtn.bdimg.com/it/u=2912834953,2410691374&fm=26&gp=0.jpg");
        imagesView.addImages(images);
```

```
        imagesView.setOnClickImageListener(new NineImageView.OnClickChildListener() {
                @Override
                public void onClickChildListener(View view, int position) {
                    Toast.makeText(mContext, "点击了第"+(position+1)+"张图", Toast.LENGTH_SHORT).show();
                }
            });
```



 <!--//图片间距-->
        <attr name="niv_margin" format="dimension"/>
        <!--圆角大小-->
        <attr name="niv_radius" format="dimension" />
        <attr name="niv_left_top_corner_radius" format="dimension" />
        <attr name="niv_right_top_corner_radius" format="dimension" />
        <attr name="niv_left_bottom_corner_radius" format="dimension" />
        <attr name="niv_right_bottom_corner_radius" format="dimension" />
        <!--描边大小-->
        <attr name="niv_border_width" format="dimension" />
        <!--描边颜色-->
        <attr name="niv_border_color" format="color" />
        <!--是否圆形图片-->
        <attr name="niv_oval" format="boolean" />






![图片](https://imgsa.baidu.com/forum/pic/item/c897aa0a19d8bc3efdddb4d48f8ba61ea9d345e9.jpg) 
![图片](https://imgsa.baidu.com/forum/pic/item/35998aec08fa513de9dfd1c0306d55fbb2fbd94a.jpg) 
![图片](https://imgsa.baidu.com/forum/pic/item/5215598da97739121563387ff5198618367ae27a.jpg) 
![图片](https://imgsa.baidu.com/forum/pic/item/0d611312b31bb05150610e363b7adab44aede07a.jpg) 
![图片](https://imgsa.baidu.com/forum/pic/item/0cc5d71f4134970a64a6a23198cad1c8a6865de9.jpg) 

