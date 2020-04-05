package com.sample.picasso.secret;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.sample.picasso.Helper.PublicConstants;
import com.sample.picasso.MainActivity;
import com.sample.picasso.R;

public class SettingsActivity extends AppCompatActivity {

    Switch settingsThemeManualModeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences appDataPref = getSharedPreferences(PublicConstants.appDataPref, Context.MODE_PRIVATE);
        final SharedPreferences.Editor appDataPrefEditor = appDataPref.edit();
        if(appDataPref.contains(PublicConstants.appDataPref_theme))
        {
            String theme = appDataPref.getString(PublicConstants.appDataPref_theme,PublicConstants.themeLight);
            if(theme.equals(PublicConstants.themeLight))
            {
                setTheme(R.style.AppTheme);
            }
            else
            {
                setTheme(R.style.DarkTheme);

            }
        }
        else
        {
            appDataPrefEditor.putString(PublicConstants.appDataPref_theme,PublicConstants.themeLight);
            appDataPrefEditor.commit();
            setTheme(R.style.AppTheme);

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");


        settingsThemeManualModeSwitch = findViewById(R.id.settingThemeManualModeSwitch);

        if(appDataPref.contains(PublicConstants.appDataPref_theme))
        {
            String theme = appDataPref.getString(PublicConstants.appDataPref_theme,PublicConstants.themeLight);
            if(theme.equals(PublicConstants.themeDark))
            {
                settingsThemeManualModeSwitch.setChecked(true);
            }
            else
            {
                settingsThemeManualModeSwitch.setChecked(false);
            }
        }
        else
        {
            appDataPrefEditor.putString(PublicConstants.appDataPref_theme,PublicConstants.themeLight);
            appDataPrefEditor.commit();
            setTheme(R.style.AppTheme);

        }

        settingsThemeManualModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    appDataPrefEditor.putString(PublicConstants.appDataPref_theme,PublicConstants.themeDark);
                    appDataPrefEditor.commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                }
                else
                {
                    appDataPrefEditor.putString(PublicConstants.appDataPref_theme,PublicConstants.themeLight);
                    appDataPrefEditor.commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuItemId = item.getItemId();
        if(menuItemId == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        finish();
    }
}
