package com.wd.tech.project20181228.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ProductDetailsView extends ScrollView {
    public ProductDetailsView(Context context) {
        this(context,null);
    }

    public ProductDetailsView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProductDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollViewListener != null){
            scrollViewListener.onScrollChange(this,l,t,oldl,oldt);
        }
    }

    public interface ScrollViewListener{
        void onScrollChange(ProductDetailsView scrollView, int l, int t, int oldl, int oldt);
    }

    private ScrollViewListener scrollViewListener;

    public void setScrollViewListener(ScrollViewListener listener) {
        scrollViewListener = listener;
    }
}
