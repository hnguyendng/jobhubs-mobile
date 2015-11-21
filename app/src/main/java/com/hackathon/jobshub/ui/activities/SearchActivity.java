package com.hackathon.jobshub.ui.activities;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.hackathon.jobshub.R;
import com.hackathon.jobshub.utils.GoogleUtils;
import com.hackathon.jobshub.utils.LogUtils;
import com.hackathon.jobshub.utils.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = SearchActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.etJobsTitle)
    AutoCompleteTextView completeTextView;

    @Bind(R.id.etLocation)
    AutoCompleteTextView completeLocation;

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        String[] titles = {
                "Java", "Android", "iOS", "C", "C#", "C++", "Objective C"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        completeTextView.setAdapter(adapter);

        completeLocation.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.provinces)));

        if (GoogleUtils.checkPlayServices(this)) {
            LogUtils.d(TAG, "onCreate", "buildGoogleApiClient");
            buildGoogleApiClient();
        } else {
            LogUtils.d(TAG, "onCreate", "play services is false");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @OnClick(R.id.btnFindJobs)
    public void onFindJobsClick(View v) {
        startActivity(new Intent(SearchActivity.this, JobsActivity.class));
    }

    @OnClick(R.id.btnGPS)
    public void onGPSClick(View v) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            LogUtils.d(TAG, "onConnected", mLastLocation.getLatitude() + " " + mLastLocation.getLongitude());
            //mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            //mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        } else {
            LogUtils.d(TAG, "onConnected", "mLastLocation is null");

            Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            Toast.makeText(this, "Please enable location services", Toast.LENGTH_SHORT).show();
            startActivity(gpsOptionsIntent);
        }
        //String input = "39 Bùi Thị Xuân, Bến Thành"; // my UTF-16 string
        //LogUtils.d(TAG, "ascii.toString()", StringUtils.deAccent(input));
    }

    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    @Override
    public void onConnected(Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
