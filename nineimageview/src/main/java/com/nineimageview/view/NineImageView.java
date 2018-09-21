package com.nineimageview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nineimageview.R;

import java.util.List;

/**
 * Created by Administrator on 2018/9/20.
 */

public class NineImageView extends ViewGroup {

    private int margin = 0;
    private float radius = 0;
    private float leftTopRadius = 0,rightTopRadius = 0,leftBottomRadius=0,rightBottomRadius=0;
    private float borderWidth = 0;
    private int borderColor = Color.BLACK;
    private boolean isOval = false;

    public NineImageView(Context context) {
        this(context, null);
    }

    public NineImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NineImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NineImageView);
        margin = (int) (array.getDimension(R.styleable.NineImageView_niv_margin, margin) / 2);
        radius = array.getDimension(R.styleable.NineImageView_niv_radius,radius);
        leftTopRadius = array.getDimension(R.styleable.NineImageView_niv_left_top_corner_radius,radius);
        rightTopRadius = array.getDimension(R.styleable.NineImageView_niv_right_top_corner_radius,radius);
        leftBottomRadius = array.getDimension(R.styleable.NineImageView_niv_left_bottom_corner_radius,radius);
        rightBottomRadius = array.getDimension(R.styleable.NineImageView_niv_right_bottom_corner_radius,radius);
        borderWidth = array.getDimension(R.styleable.NineImageView_niv_border_width,0);
        borderColor = array.getColor(R.styleable.NineImageView_niv_border_color,borderColor);
        isOval = array.getBoolean(R.styleable.NineImageView_niv_oval,isOval);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        width = getDisplayMetrics().widthPixels * 2 / 3;
        if (widthMode == MeasureSpec.EXACTLY)
            width = MeasureSpec.getSize(widthMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int count = getChildCount();
        if (count == 1 || count == 4 || count > 5)
            height = width - getPaddingLeft() - getPaddingRight() + getPaddingTop() + getPaddingBottom();

        if (count == 2)
            height = (int) ((float) (width - getPaddingLeft() - getPaddingRight()) / 2f + getPaddingTop() + getPaddingBottom());

        if (count == 3) {
            height = (int) (((float) (width - getPaddingLeft() - getPaddingRight()) * 2f / 3f) + getPaddingTop() + getPaddingBottom());
        }
        if (count == 5) {
            height = (int) (((float) (width - getPaddingLeft() - getPaddingRight()) * 2f / 3f) + ((float) (width - getPaddingLeft() - getPaddingRight()) / 2) + getPaddingTop() + getPaddingBottom());
        }

        if (heightMode == MeasureSpec.EXACTLY)
            height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        l = l + getPaddingLeft()-getLeft();
        t = t + getPaddingTop()-getTop();
        r = r - getPaddingRight()-getLeft();
        b = b - getPaddingBottom()-getTop();

        int count = getChildCount();
        if(count <= 0)
            return;
        switch (count) {
            case 1:
                onLayout1(l, t, r, b);
                break;
            case 2:
                onLayout2(l, t, r, b);
                break;
            case 3:
                onLayout3(l, t, r, b);
                break;
            case 4:
                onLayout4(l, t, r, b);
                break;
            case 5:
                onLayout5(l, t, r, b);
                break;
            case 6:
                onLayout6(l, t, r, b);
                break;
            case 7:
                onLayout7(l, t, r, b);
                break;
            case 8:
                onLayout8(l, t, r, b);
                break;
            default:
                onLayout9(l, t, r, b);
                break;
        }

    }


    private void onLayout1(int l, int t, int r, int b) {


        View view = getChildAt(0);
        MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
        params.width = r - l - margin - margin;
        params.height = b - t - margin;
        view.setLayoutParams(params);
        int l1 = l + margin;
        int t1 = t + margin;
        int r1 = r - margin;
        int b1 = b - margin;
        view.layout(l1, t1, r1, b1);
    }


    private void onLayout2(int l, int t, int r, int b) {
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();

        View v0 = getChildAt(0);
        MarginLayoutParams p0 = (MarginLayoutParams) v0.getLayoutParams();
        p0.width = (int) ((float) width / 2 - margin * 2);
        p0.height = height - margin * 2;
        v0.setLayoutParams(p0);
        int l0 = l + margin;
        int t0 = t + margin;
        int r0 = l0 + p0.width;
        int b0 = t0 + p0.height;
        v0.layout(l0, t0, r0, b0);

        View v1 = getChildAt(1);
        v1.setLayoutParams(p0);
        int l1 = r0 + margin * 2;
        int t1 = t0;
        int r1 = l1 + p0.width;
        int b1 = t1 + p0.height;
        v1.layout(l1, t1, r1, b1);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        addOnClick();
    }

    private void addOnClick() {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            final int position = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickChildListener != null)
                        onClickChildListener.onClickChildListener(v, position);
                }
            });
        }
    }


    private void onLayout3(int l, int t, int r, int b) {

        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();

        View v0 = getChildAt(0);
        MarginLayoutParams p0 = (MarginLayoutParams) v0.getLayoutParams();
        p0.width = (int) (((float) width) * 2f / 3f) - margin * 2;
        p0.height = height - margin * 2;
        v0.setLayoutParams(p0);
        int l0 = l + margin;
        int t0 = t + margin;
        int r0 = l0 + p0.width;
        int b0 = t0 + p0.height;
        v0.layout(l0, t0, r0, b0);

        View v1 = getChildAt(1);
        MarginLayoutParams p1 = (MarginLayoutParams) v1.getLayoutParams();
        p1.width = width - p0.width - margin * 4;
        p1.height = p0.height / 2 - margin;
        v1.setLayoutParams(p1);
        int l1 = r0 + margin * 2;
        int t1 = t + margin;
        int r1 = l1 + p1.width;
        int b1 = t1 + p1.height;
        v1.layout(l1, t1, r1, b1);

        View v2 = getChildAt(2);
        v2.setLayoutParams(p1);
        int l2 = v1.getLeft();
        int t2 = v1.getBottom() + margin * 2;
        int r2 = l2 + p1.width;
        int b2 = t2 + p1.height;
        v2.layout(l2, t2, r2, b2);
    }


    private void onLayout4(int l, int t, int r, int b) {
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();

        View v0 = getChildAt(0);
        MarginLayoutParams p0 = (MarginLayoutParams) v0.getLayoutParams();
        p0.width = (int) (((float) width) / 2 - margin * 2);
        p0.height = (int) (((float) height) / 2 - margin * 2);
        v0.setLayoutParams(p0);

        int l0 = l + margin;
        int t0 = t + margin;
        int r0 = l0 + p0.width;
        int b0 = t0 + p0.height;
        v0.layout(l0, t0, r0, b0);

        View v1 = getChildAt(1);
        v1.setLayoutParams(p0);
        int l1 = r0 + margin * 2;
        int t1 = t + margin;
        int r1 = l1 + p0.width;
        int b1 = t1 + p0.height;
        v1.layout(l1, t1, r1, b1);

        View v2 = getChildAt(2);
        v2.setLayoutParams(p0);
        int l2 = l + margin;
        int t2 = v1.getBottom() + margin * 2;
        int r2 = l2 + p0.width;
        int b2 = t2 + p0.height;
        v2.layout(l2, t2, r2, b2);

        View v3 = getChildAt(3);
        v3.setLayoutParams(p0);
        int l3 = v2.getRight() + margin * 2;
        int t3 = v1.getBottom() + margin * 2;
        int r3 = l3 + p0.width;
        int b3 = t3 + p0.height;
        v3.layout(l3, t3, r3, b3);
    }

    private void onLayout5(int l, int t, int r, int b) {
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();

        View v0 = getChildAt(0);
        MarginLayoutParams p0 = (MarginLayoutParams) v0.getLayoutParams();
        p0.width = (int) (((float) width) * 2f / 3f) - margin * 2;
        p0.height = (int) (((float) height) * 4f / 7f - margin * 2);
        v0.setLayoutParams(p0);
        int l0 = l + margin;
        int t0 = t + margin;
        int r0 = l0 + p0.width;
        int b0 = t0 + p0.height;
        v0.layout(l0, t0, r0, b0);

        View v1 = getChildAt(1);
        MarginLayoutParams p1 = (MarginLayoutParams) v1.getLayoutParams();
        p1.width = width - p0.width - margin * 4;
        p1.height = (int) (((float) p0.height / 2) - margin);
        v1.setLayoutParams(p1);
        int l1 = r0 + margin * 2;
        int t1 = t0;
        int r1 = l1 + p1.width;
        int b1 = t1 + p1.height;
        v1.layout(l1, t1, r1, b1);

        View v2 = getChildAt(2);
        v2.setLayoutParams(p1);
        int l2 = v0.getRight() + margin * 2;
        int t2 = b1 + margin * 2;
        int r2 = l2 + p1.width;
        int b2 = t2 + p1.height;
        v2.layout(l2, t2, r2, b2);

        View v3 = getChildAt(3);
        MarginLayoutParams p3 = (MarginLayoutParams) v3.getLayoutParams();
        p3.width = (int) (((float) width / 2f) - margin * 2);
        p3.height = height - p0.height - margin * 4;
        v3.setLayoutParams(p3);
        int l3 = l0;
        int t3 = b0 + margin * 2;
        int r3 = l3 + p3.width;
        int b3 = t3 + p3.height;
        v3.layout(l3, t3, r3, b3);

        View v4 = getChildAt(4);
        v4.setLayoutParams(p3);
        int l4 = r3 + margin * 2;
        int t4 = t3;
        int r4 = l4 + p3.width;
        int b4 = t4 + p3.height;
        v4.layout(l4, t4, r4, b4);
    }

    private void onLayout6(int l, int t, int r, int b) {
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();

        View v0 = getChildAt(0);
        MarginLayoutParams p0 = (MarginLayoutParams) v0.getLayoutParams();
        v0.setLayoutParams(p0);
        p0.width = (int) ((float) width * 2f / 3f - margin * 2);
        p0.height = (int) ((float) height * 2f / 3f - margin * 2);
        int l0 = l + margin;
        int t0 = t + margin;
        int r0 = l0 + p0.width;
        int b0 = t0 + p0.height;
        v0.layout(l0, t0, r0, b0);

        View v1 = getChildAt(1);
        MarginLayoutParams p1 = (MarginLayoutParams) v1.getLayoutParams();
        p1.width = width - p0.width - margin * 4;
        p1.height = (int) (((float) p0.height / 2) - margin);
        v1.setLayoutParams(p1);
        int l1 = r0 + margin * 2;
        int t1 = t0;
        int r1 = l1 + p1.width;
        int b1 = t1 + p1.height;
        v1.layout(l1, t1, r1, b1);

        View v2 = getChildAt(2);
        v2.setLayoutParams(p1);
        int l2 = v0.getRight() + margin * 2;
        int t2 = b1 + margin * 2;
        int r2 = l2 + p1.width;
        int b2 = t2 + p1.height;
        v2.layout(l2, t2, r2, b2);

        View v3 = getChildAt(3);
        v3.setLayoutParams(p1);
        int l3 = l0;
        int t3 = b0 + margin * 2;
        int r3 = l3 + p1.width;
        int b3 = t3 + p1.height;
        v3.layout(l3, t3, r3, b3);

        View v4 = getChildAt(4);
        v4.setLayoutParams(p1);
        int l4 = r3 + margin * 2;
        int t4 = t3;
        int r4 = l4 + p1.width;
        int b4 = t4 + p1.height;
        v4.layout(l4, t4, r4, b4);

        View v5 = getChildAt(5);
        v5.setLayoutParams(p1);
        int l5 = l1;
        int t5 = t3;
        int r5 = l5 + p1.width;
        int b5 = t5 + p1.height;
        v5.layout(l5, t5, r5, b5);
    }


    private void onLayout7(int l, int t, int r, int b) {
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();

        View v0 = getChildAt(0);
        MarginLayoutParams p0 = (MarginLayoutParams) v0.getLayoutParams();
        v0.setLayoutParams(p0);
        p0.width = (int) ((float) width / 3 - margin * 2);
        p0.height = (int) ((float) height / 3 - margin * 2);
        v0.setLayoutParams(p0);

        int l0 = l + margin;
        int t0 = t + margin;
        int r0 = l0 + p0.width;
        int b0 = t0 + p0.height;
        v0.layout(l0, t0, r0, b0);

        View v1 = getChildAt(1);
        v1.setLayoutParams(p0);
        int l1 = r0 + margin * 2;
        int t1 = t0;
        int r1 = l1 + p0.width;
        int b1 = t1 + p0.height;
        v1.layout(l1, t1, r1, b1);

        View v2 = getChildAt(2);
        v2.setLayoutParams(p0);
        int l2 = r1 + margin * 2;
        int t2 = t0;
        int r2 = l2 + p0.width;
        int b2 = t2 + p0.height;
        v2.layout(l2, t2, r2, b2);

        View v3 = getChildAt(3);
        v3.setLayoutParams(p0);
        int l3 = l0;
        int t3 = b0 + margin*2;
        int r3 = l3 + p0.width;
        int b3 = t3 + p0.height;
        v3.layout(l3, t3, r3, b3);

        View v4 = getChildAt(4);
        v4.setLayoutParams(p0);
        int l4 = l1;
        int t4 = t3;
        int r4 = l4 + p0.width;
        int b4 = t4 + p0.height;
        v4.layout(l4, t4, r4, b4);

        View v5 = getChildAt(5);
        v5.setLayoutParams(p0);
        int l5 = l2;
        int t5 = t4;
        int r5 = l5 + p0.width;
        int b5 = t5 + p0.height;
        v5.layout(l5, t5, r5, b5);

        View v6 = getChildAt(6);
        v6.setLayoutParams(p0);
        int l6 = l0;
        int t6 = b3 + margin * 2;
        int r6 = l6 + p0.width;
        int b6 = t6 + p0.height;
        v6.layout(l6, t6, r6, b6);
    }

    private void onLayout8(int l, int t, int r, int b) {
        onLayout7(l, t, r, b);
        View v6 = getChildAt(6);
        MarginLayoutParams p6 = (MarginLayoutParams) v6.getLayoutParams();
        View v7 = getChildAt(7);
        v7.setLayoutParams(p6);
        int l7 = v6.getRight() + margin * 2;
        int t7 = v6.getTop();
        int r7 = l7 + p6.width;
        int b7 = t7 + p6.height;
        v7.layout(l7, t7, r7, b7);
    }

    private void onLayout9(int l, int t, int r, int b) {
        onLayout8(l, t, r, b);
        View v7 = getChildAt(7);
        MarginLayoutParams p7 = (MarginLayoutParams) v7.getLayoutParams();
        View v8 = getChildAt(8);
        v7.setLayoutParams(p7);
        int l8 = v7.getRight() + margin * 2;
        int t8 = v7.getTop();
        int r8 = l8 + p7.width;
        int b8 = t8 + p7.height;
        v8.layout(l8, t8, r8, b8);

    }

    public void addImages(@NonNull List<String> images){
        removeAllViews();
        if(images != null && images.size()>0){
            for (String image : images){
                RadiusImageView imageView = getRadiusImageView();
                addView(imageView);
                Glide.with(getContext()).load(image).apply(new RequestOptions().error(R.drawable.error_icon)).into(imageView);
            }
        }
        requestLayout();
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    private DisplayMetrics getDisplayMetrics() {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        return dm;
    }

    public interface OnClickChildListener {
        void onClickChildListener(View view, int position);
    }

    private OnClickChildListener onClickChildListener;

    public void setOnClickImageListener(OnClickChildListener onClickChildListener) {
        this.onClickChildListener = onClickChildListener;
    }

    private RadiusImageView getRadiusImageView(){
        RadiusImageView imageView = new RadiusImageView(getContext());
        MarginLayoutParams params = new MarginLayoutParams(300, 300);
        imageView.setLayoutParams(params);
        imageView.setBorderColor(borderColor);
        imageView.setBorderWidthDP(px2dip(borderWidth));
        imageView.setCornerRadiiDP(px2dip(leftTopRadius),px2dip(rightTopRadius),px2dip(leftBottomRadius),px2dip(rightBottomRadius));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    public int px2dip(float px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

}
