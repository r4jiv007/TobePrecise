package com.example.rajiv.precise.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.rajiv.precise.R;
import com.example.rajiv.precise.activities.utils.Constants;

import org.w3c.dom.Text;

/**
 * Created by rajiv on 15/2/16.
 */
public class MainScreen extends BaseActivity {

    private TextView tvWelcome,tvLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setTitle(R.string.main);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayUseLogoEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setLogo(R.mipmap.ic_launcher);

        setContentView(R.layout.main_screen_layout);
        initView();
    }

    private void initView(){
        tvWelcome=(TextView)findViewById(R.id.tvWelcome);
        tvLocation=(TextView)findViewById(R.id.tvLocation);
        if(Constants.mSelectedLocation!=null){
            tvLocation.setText(Constants.mSelectedLocation);
        }
    }



    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_exit:
                exitApp();
                return true;
            case R.id.menu_products:
                showProductScreen();
                return true;
            case R.id.menu_aboutus:
                showAboutUs();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showProductScreen(){
        Intent intent = new Intent(this,ProductScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void showAboutUs(){
        Intent intent = new Intent(this,AboutUsScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
