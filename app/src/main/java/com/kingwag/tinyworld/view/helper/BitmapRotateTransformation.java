package com.kingwag.tinyworld.view.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by StevenWang on 16/3/19.
 */
public class BitmapRotateTransformation extends BitmapTransformation {
    private float rotateAngle = 0f;

    public BitmapRotateTransformation(Context mContext, float rotateAngle) {
        super(mContext);
        this.rotateAngle = rotateAngle;
    }


    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Matrix matrix = new Matrix();
        matrix.postRotate(rotateAngle);
        Bitmap result = Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName() + rotateAngle;
    }
}
