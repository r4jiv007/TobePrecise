package com.example.rajiv.precise.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.rajiv.precise.R;

/**
 * Created by rajiv on 15/2/16.
 */
public class AboutUsScreen extends FragmentActivity {


    private WebView mWebView;
    private ProgressBar mProgressBar;

    private final String URL="http://www.joyalukkas.com/about-us/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayUseLogoEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setLogo(R.mipmap.ic_launcher);
        getActionBar().setTitle(R.string.About_us);
        setContentView(R.layout.about_us_screen);
        initView();
    }

    private void initView(){
        mWebView = (WebView)findViewById(R.id.wvAboutUs);
        mProgressBar =(ProgressBar)findViewById(R.id.pbLoading);
        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100 && mProgressBar.getVisibility() == ProgressBar.GONE) {
                    mProgressBar.setVisibility(ProgressBar.VISIBLE);
                }
                mProgressBar.setProgress(progress);
                if (progress == 100) {
                    mProgressBar.setVisibility(ProgressBar.GONE);
                }
            }
        });

        mWebView.loadUrl(URL);
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
            case android.R.id.home:
                // ProjectsActivity is my 'home' activity
                startActivityAfterCleanup(MainScreen.class);
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


    private void startActivityAfterCleanup(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
