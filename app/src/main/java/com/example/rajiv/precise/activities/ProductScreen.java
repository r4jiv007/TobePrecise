package com.example.rajiv.precise.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rajiv.precise.R;
import com.example.rajiv.precise.activities.adapters.ProductAdapter;
import com.example.rajiv.precise.activities.utils.Constants;
import com.example.rajiv.precise.activities.utils.HandleXml;
import com.example.rajiv.precise.activities.utils.Network;
import com.example.rajiv.precise.activities.utils.Product;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rajiv on 15/2/16.
 */
public class ProductScreen extends BaseActivity {

    private ListView lvProducts;
    private TextView tvLocation;
    ArrayList<Product> mProductList = new ArrayList<>();
    ProductAdapter mAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayUseLogoEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setLogo(R.mipmap.ic_launcher);
        getActionBar().setTitle(R.string.Products);

        setContentView(R.layout.products_screen_layout);
        initView();
        new ProductLoader().execute();
    }

    private  void initView(){

        lvProducts =(ListView)findViewById(R.id.lvProducts);
        tvLocation=(TextView)findViewById(R.id.location);
        if(Constants.mSelectedLocation!=null){
            tvLocation.setText(Constants.mSelectedLocation);
        }

        mAdapter = new ProductAdapter(this,mProductList);
        lvProducts.setAdapter(mAdapter);
    }


    private class ProductLoader extends AsyncTask{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] params) {

            String productResult = null;
            try {
                productResult = Network.fetchProduct();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HandleXml parser = new HandleXml(productResult);
            try {
                mProductList = parser.getProductList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(mProductList!=null && mProductList.size()>0){
                mAdapter.swapData(mProductList);
            }
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_menu, menu);
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
            case R.id.menu_exit:
                exitApp();
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
