package com.thinkhodl.lifetag;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        mNFCLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProfileInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void animNFC(){

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mNFCAntenaImageView, "alpha",  1f, 0);
        fadeOut.setDuration(1500);
        fadeOut.setRepeatMode(ValueAnimator.REVERSE);
        fadeOut.setRepeatCount(Animation.INFINITE);
        fadeOut.start();

    }

}
