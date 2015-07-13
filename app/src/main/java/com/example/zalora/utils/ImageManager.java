package com.example.zalora.utils;

import android.content.Context;
import android.widget.ImageView;

import com.androidquery.AQuery;

public class ImageManager {
    private AQuery aq;

    public ImageManager(Context context) {
        aq = new AQuery(context);
    }
    
    public void displayImage(String url, ImageView imageView,int width, int defaultImage) {
        aq = aq.recycle(imageView);
        aq.id(imageView).image(url, false, true, width, defaultImage, null, AQuery.FADE_IN_NETWORK, AQuery.RATIO_PRESERVE);
    }

}