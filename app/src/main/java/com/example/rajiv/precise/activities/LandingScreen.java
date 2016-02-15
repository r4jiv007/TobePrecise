package com.example.rajiv.precise.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rajiv.precise.R;
import com.example.rajiv.precise.activities.utils.Constants;
import com.example.rajiv.precise.activities.utils.Network;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rajiv on 15/2/16.
 */
public class LandingScreen extends FragmentActivity implements View.OnClickListener {

//    ArrayList<String> mLocations = new ArrayList<>();
    private Spinner spnLocation;
    private Button bGO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_screen_layout);
        initView();
    }

    private void initView() {
        spnLocation = (Spinner) findViewById(R.id.spnLocation);
        bGO = (Button) findViewById(R.id.bGo);
        bGO.setOnClickListener(this);
        initData();
    }

    private void initData(){

        try {
            if(!checkFileExists() ){
                Toast.makeText(this,"Config file does not exists !! ",Toast.LENGTH_LONG).show();
                return;


            }
            if(!Network.isOnline(getApplicationContext())){
                Toast.makeText(this,"Network is not connected ",Toast.LENGTH_LONG).show();

                return;
            }



            parseLocation();
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, Constants.mLocation);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnLocation.setAdapter(dataAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkFileExists() throws   IOException{
       return Arrays.asList(getResources().getAssets().list("")).contains("location.xml");
    }

    private void parseLocation() throws XmlPullParserException, IOException {

        AssetManager assetManager = getBaseContext().getAssets();

            InputStream stream = assetManager.open("location.xml");

        XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
        XmlPullParser myParser = xmlFactoryObject.newPullParser();

        String text = null;
        myParser.setInput(stream, null);
        int event = myParser.getEventType();
        Constants.mLocation.clear();
        while (event != XmlPullParser.END_DOCUMENT) {
            String name = myParser.getName();
            switch (event) {
                case XmlPullParser.START_TAG:
                    break;
                case XmlPullParser.TEXT:
                    text = myParser.getText();
                    break;
                case XmlPullParser.END_TAG:
                    if (name.equals("name")) {
                        Constants.mLocation.add(text);
                    }
                    break;
            }
            event = myParser.next();
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.bGo:
                showMainScreen();
                break;

        }
    }

    private  void showMainScreen(){
        Constants.mSelectedLocation=spnLocation.getSelectedItem().toString();
        Intent intent = new Intent(this,MainScreen.class);
        startActivity(intent);
    }

}
