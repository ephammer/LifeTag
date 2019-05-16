package com.thinkhodl.lifetag;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditInfoActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_edit_info);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_profile,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save_profile) {
            Intent saveActivity = new Intent(EditInfoActivity.this,SaveProfileActivity.class);
            saveActivity.putExtra(Utils.PROFILE_INFO,saveProfile());
            startActivity(saveActivity);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    String saveProfile(){

        Profile profile = new Profile();

        profile.setFirstName(mFirstNameTextInputEditText.getText().toString());
        profile.setLastName(mLastNameTextInputEditText.getText().toString());
        profile.setAge(mAgeTextInputEditText.getText().toString());
        Log.v("saveProfile",mAgeTextInputEditText.getText().toString());
        profile.setBloodType(mBloodTypeTextInputEditText.getText().toString());
        profile.setAllergies(mKnownAllergiesTextInputEditText.getText().toString());
        profile.setIllnesses(mKnownIllnessesTextInputEditText.getText().toString());
        profile.setVaccines(mVaccinesTextInputEditText.getText().toString());
        profile.setIsDonor(mOrganDonorTextInputEditText.getText().toString());
        profile.setContactName(mEmergencyContactNameTextInputEditText.getText().toString());
        profile.setContactNumber(mEmergencyContactNumberTextInputEditText.getText().toString());
        Log.v("saveProfile",profile.encode());

        return profile.encode();
    }
}
