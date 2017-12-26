package com.morening.hello.promotionview.model;

import android.graphics.drawable.Drawable;

/**
 * Created by morening on 2017/12/9.
 */

public class DataBean {

    private String title = null;
    private Drawable image = null;

    public DataBean(String title, Drawable image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
