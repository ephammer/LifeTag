package com.thinkhodl.lifetag;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileInfoActivity extends AppCompatActivity {

    @BindView(R.id.contact_emergency_button)
    Button mEmergencyButton;

    @BindView(R.id.first_name_textView)
    TextInputEditText mFirstNameTextInputEditText;

    @BindView(R.id.last_name_textView)
    TextInputEditText mLastNameTextInputEditText;

    @BindView(R.id.age_textView)
    TextInputEditText mAgeTextInputEditText;

    @BindView(R.id.blood_type_textView)
    TextInputEditText mBloodTypeTextInputEditText;

    @BindView(R.id.known_allergies_textView)
    TextInputEditText mKnownAllergiesTextInputEditText;

    @BindView(R.id.known_illnesses_textView)
    TextInputEditText mKnownIllnessesTextInputEditText;

    @BindView(R.id.vaccines_textView)
    TextInputEditText mVaccinesTextInputEditText;

    @BindView(R.id.organ_donor_textView)
    TextInputEditText mOrganDonorTextInputEditText;

    @BindView(R.id.emergency_contact_name_textView)
    TextInputEditText mEmergencyContactNameTextInputEditText;

    @BindView(R.id.emergency_contact_number_textView)
    TextInputEditText mEmergencyContactNumberTextInputEditText;

    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        ButterKnife.bind(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();


        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        message = intent.getStringExtra(Utils.PROFILE_INFO);
        populateProfile(message);


        mEmergencyButton.setOnClickListener(v -> sendSMSMessage());

    }

    void populateProfile(String message){
        Profile profile = new Profile(message);

        mFirstNameTextInputEditText.setText(profile.getFirstName());
        mLastNameTextInputEditText.setText(profile.getLastName());
        mAgeTextInputEditText.setText(profile.getAge());
        mBloodTypeTextInputEditText.setText(profile.getBloodType());
        mKnownAllergiesTextInputEditText.setText(profile.getAllergies());
        mKnownIllnessesTextInputEditText.setText(profile.getIllnesses());
        mVaccinesTextInputEditText.setText(profile.getVaccines());
        mOrganDonorTextInputEditText.setText(profile.getIsDonor());
        mEmergencyContactNameTextInputEditText.setText(profile.getContactName());
        mEmergencyContactNumberTextInputEditText.setText(profile.getContactNumber());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_profile,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.edit_profile) {
            Intent editActivity = new Intent(ProfileInfoActivity.this,EditInfoActivity.class);
            editActivity.putExtra(Utils.PROFILE_INFO,message);
            startActivity(editActivity);
//            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    protected void sendSMSMessage() {

        Dexter.withActivity(ProfileInfoActivity.this)
                .withPermission(Manifest.permission.SEND_SMS)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(
                                mEmergencyContactNumberTextInputEditText.getText().toString(),
                                null,
                                "Hey an emergency occured",
                                null,
                                null);
                    }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();


    }
}
