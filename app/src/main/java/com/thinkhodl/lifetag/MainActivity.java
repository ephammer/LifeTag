package com.thinkhodl.lifetag;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.nfc_view)
    ConstraintLayout mNFCLayout;

    @BindView(R.id.nfc_antena_imageview)
    ImageView mNFCAntenaImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        animNFC();
    }

    private void animNFC(){

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mNFCAntenaImageView, "alpha",  1f, 0);
        fadeOut.setDuration(2000);
        fadeOut.setRepeatMode(ValueAnimator.RESTART);
        fadeOut.setRepeatCount(Animation.INFINITE);
        fadeOut.start();

    }

}
