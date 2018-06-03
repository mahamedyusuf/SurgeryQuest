package com.group5.surgeryquest.roottaskcollectionviews;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.group5.surgeryquest.R;

/**
 * Created by aryik.bhattacharya on 4/9/18.
 */

public class FrameLayoutHolder {

    private FrameLayout frameLayout;
    private ImageView cardImage;
    private ImageView cardCheckImage;
    private ImageView cardLockImage;

    public FrameLayoutHolder(View view) {
        frameLayout = view.findViewById(R.id.frameLayout);
        cardImage = view.findViewById(R.id.cardImage);
        cardCheckImage = view.findViewById(R.id.cardCheckImage);
        cardLockImage = view.findViewById(R.id.cardLockImage);
    }

    public void lock() {
        cardImage.setAlpha(0.5f);
        cardCheckImage.setVisibility(View.INVISIBLE);
        cardLockImage.setVisibility(View.VISIBLE);
    }

    public void unlock() {
        cardImage.setAlpha(1.0f);
        cardCheckImage.setVisibility(View.INVISIBLE);
        cardLockImage.setVisibility(View.INVISIBLE);
    }

    public void check() {
        cardImage.setAlpha(0.5f);
        cardCheckImage.setVisibility(View.VISIBLE);
        cardLockImage.setVisibility(View.INVISIBLE);
    }

    public void setImageResource(int id) {
        cardImage.setImageResource(id);
    }
}
