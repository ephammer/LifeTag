package com.thinkhodl.lifetag;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    Intent nfcIntent;
    PendingIntent pi;

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

        /*mNFCLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProfileInfoActivity.class);
                startActivity(intent);
            }
        });*/
    }

    private void animNFC(){

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mNFCAntenaImageView, "alpha",  1f, 0);
        fadeOut.setDuration(1500);
        fadeOut.setRepeatMode(ValueAnimator.REVERSE);
        fadeOut.setRepeatCount(Animation.INFINITE);
        fadeOut.start();

    }

    // When an NFC tag comes into range, call the main activity which handles writing the data to the tag
    private void readFromNFC(Ndef ndef) {

        if(ndef!=null) {
            try {
                ndef.connect();
                NdefMessage ndefMessage = ndef.getNdefMessage();
                String message = new String(ndefMessage.getRecords()[0].getPayload());

                ndef.close();

                Intent intent = new Intent(MainActivity.this, ProfileInfoActivity.class);
                intent.putExtra(Utils.PROFILE_INFO, message);
                startActivity(intent);


            } catch (IOException | FormatException e) {
                e.printStackTrace();

            }
        }
    }




    public void waitForSomething(){
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        nfcIntent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //        nfcIntent.putExtra("nfcMessage", "testsss");
        pi = PendingIntent.getActivity(this, 0, nfcIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        nfcAdapter.enableForegroundDispatch(this, pi, new IntentFilter[] {tagDetected}, null);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        Ndef ndef = Ndef.get(tag);

        readFromNFC(ndef);

    }

    @Override
    protected void onResume() {
        super.onResume();
        waitForSomething();
    }

}
