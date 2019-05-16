package com.thinkhodl.lifetag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.ImageView;

import java.io.IOException;
import java.nio.charset.Charset;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaveProfileActivity extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    Intent nfcIntent;
    PendingIntent pi;

    @BindView(R.id.save_nfc_view)
    ConstraintLayout mNFCLayout;

    @BindView(R.id.nfc_antena_imageview)
    ImageView mNFCAntenaImageView;

    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_profile);
        ButterKnife.bind(this);

        animNFC();

        Intent intent = getIntent();
        message = intent.getStringExtra(Utils.PROFILE_INFO);
        Log.v("saveProfileNFC",message);
    }
    private void animNFC(){

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mNFCAntenaImageView, "alpha",  1f, 0);
        fadeOut.setDuration(1500);
        fadeOut.setRepeatMode(ValueAnimator.REVERSE);
        fadeOut.setRepeatCount(Animation.INFINITE);
        fadeOut.start();

        ObjectAnimator move = ObjectAnimator.ofFloat(mNFCAntenaImageView, "translationX", 100f);
        move.setDuration(1500);
        move.setRepeatMode(ValueAnimator.RESTART);
        move.setRepeatCount(Animation.INFINITE);
        move.start();

    }


    public void waitForSomething(){
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        nfcIntent = new Intent(this, SaveProfileActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        nfcIntent.putExtra("nfcMessage", message);
        pi = PendingIntent.getActivity(this, 0, nfcIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        nfcAdapter.enableForegroundDispatch(this, pi, new IntentFilter[] {tagDetected}, null);

    }


    private void writeToNfc(Ndef ndef, String message){

        Log.v("writeToNFC",message);

        if (ndef != null) {

            try {
                ndef.connect();
                NdefRecord mimeRecord = NdefRecord.createMime("text/plain", message.getBytes(Charset.forName("US-ASCII")));
                ndef.writeNdefMessage(new NdefMessage(mimeRecord));
                ndef.close();
                Intent intent = new Intent(SaveProfileActivity.this,ProfileInfoActivity.class);
                intent.putExtra(Utils.PROFILE_INFO,message);
                startActivity(intent);
                finish();

            } catch (IOException | FormatException e) {
                e.printStackTrace();

            }

        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        Ndef ndef = Ndef.get(tag);

        writeToNfc(ndef,message);

        Log.v("onNewIntent",message);

    }

    @Override
    protected void onResume() {
        super.onResume();
        waitForSomething();
    }


}
