package com.sample.picasso;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.picasso.Adapter.WidgetAdapter;
import com.sample.picasso.Helper.PublicConstants;
import com.sample.picasso.Model.Widget;
import com.sample.picasso.secret.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<Widget> widgetList = new ArrayList<>();
    RecyclerView mainRecyclerView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        SharedPreferences appDataPref = getSharedPreferences(PublicConstants.appDataPref,Context.MODE_PRIVATE);
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
        setContentView(R.layout.activity_main);


        widgetList.add(new Widget(0,"Actionbar"));
        widgetList.add(new Widget(2,"Alert Dialog"));
        widgetList.add(new Widget(0,"Animations"));
        widgetList.add(new Widget(1,"Appbar and Tab"));
        widgetList.add(new Widget(2,"Custom Dialog"));
        widgetList.add(new Widget(1,"External Libraries"));
        widgetList.add(new Widget(2,"Menu"));
        widgetList.add(new Widget(2,"Navigation Drawer"));
        widgetList.add(new Widget(1,"Snackbar"));
        widgetList.add(new Widget(1,"Toast"));

        mainRecyclerView = findViewById(R.id.mainRecyclerView);
        mainRecyclerView.setHasFixedSize(true);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setAdapter(new WidgetAdapter(this,widgetList));



        if(!appDataPref.contains(PublicConstants.appDataPref_isAdBannerShown))
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    showAdDialog();

                }
            },2000);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuItemId = item.getItemId();
        if(menuItemId == R.id.actionbarSettings)
        {
            startActivity(new Intent(this, SettingsActivity.class));
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish();
        }
        else if(menuItemId ==  R.id.actionbarDonate)
        {
            showAdDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAdDialog() {

        final Dialog contributionDialog =  new Dialog(this);
        contributionDialog.setCancelable(true);
        contributionDialog.setCanceledOnTouchOutside(true);
        contributionDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        contributionDialog.setContentView(R.layout.dialog_ad);
        contributionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        contributionDialog.getWindow().getAttributes().windowAnimations = R.style.BottomUpSlideDialogAnimation;

        Window window = contributionDialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setDimAmount(0.75f);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        contributionDialog.show();
        SharedPreferences appDataPref = getSharedPreferences(PublicConstants.appDataPref,Context.MODE_PRIVATE);
        SharedPreferences.Editor appDataPrefEditor = appDataPref.edit();
        appDataPrefEditor.putBoolean(PublicConstants.appDataPref_isAdBannerShown,true);
        appDataPrefEditor.commit();

    }

    /*
    info app icon
    Icons made by <a href="https://www.flaticon.com/authors/dinosoftlabs" title="DinosoftLabs">DinosoftLabs</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
     */
}
