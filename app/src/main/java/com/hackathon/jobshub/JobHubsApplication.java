package com.hackathon.jobshub;

import android.app.Application;
import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by Nguyen on 11/21/2015.
 */
public class JobHubsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initPrefs();
    }

    private void initPrefs() {
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }
}
