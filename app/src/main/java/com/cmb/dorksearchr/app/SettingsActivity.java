package com.cmb.dorksearchr.app;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by cmb on 3/5/14.
 */
public class SettingsActivity extends PreferenceActivity {

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.user_settings);

    }
}
