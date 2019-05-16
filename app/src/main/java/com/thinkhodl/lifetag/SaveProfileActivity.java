package com.thinkhodl.lifetag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaveProfileActivity extends AppCompatActivity {

    @BindView(R.id.save_nfc_view)
    ConstraintLayout mNFCLayout;

    @BindView(R.id.nfc_antena_imageview)
    ImageView mNFCAntenaImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_profile);
        ButterKnife.bind(this);

        animNFC();
    }
    private void animNFC(){

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mNFCAntenaImageView, "alpha",  1f, 0);
        fadeOut.setDuration(1500);
        fadeOut.setRepeatMode(ValueAnimator.REVERSE);
        fadeOut.setRepeatCount(Animation.INFINITE);
        fadeOut.start();

    }
}
