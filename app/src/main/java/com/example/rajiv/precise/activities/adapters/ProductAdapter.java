package com.example.rajiv.precise.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rajiv.precise.R;
import com.example.rajiv.precise.activities.utils.Product;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajiv on 15/2/16.
 */
public class ProductAdapter extends BaseAdapter {

    private List<Product> mProductList;
    private Context mContext;

    private LayoutInflater mLayoutInflator ;

    public ProductAdapter(Context context,List<Product> productList){

        this.mContext =context;
        this.mProductList=productList;
        mLayoutInflator= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (mProductList!=null)?mProductList.size():0;
    }

    @Override
    public Object getItem(int position) {
        return (mProductList!=null && mProductList.size()>position)?mProductList.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return (mProductList!=null && mProductList.size()>position)?mProductList.get(position).hashCode():-1;
    }

    public void swapData(ArrayList<Product> list){
        mProductList=list;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder=null;
        if(convertView==null){

            convertView = mLayoutInflator.inflate(R.layout.product_item, null);
           mHolder = new ViewHolder(convertView);
            convertView.setTag(mHolder);
        }else{
            mHolder=(ViewHolder)convertView.getTag();
        }
        Picasso
                .with(mContext)
                .load(mProductList.get(position).getmImageUrl())
                .into(mHolder.imageView);
        mHolder.title.setText(mProductList.get(position).getmTitle());

        return convertView;
    }

    private class ViewHolder{
        public TextView title;
        public ImageView imageView;

        public ViewHolder(View view){
            title =(TextView)view.findViewById(R.id.tvTitle);
            imageView=(ImageView)view.findViewById(R.id.ivProduct);
        }
    }
}
