package com.example.lathe.meow;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lathe on 12/3/2017.
 */

public class ListManager extends BaseAdapter {
    private Context context;
    List<Dummy> dummyList = new ArrayList<Dummy>();

    public ListManager(Context context) {
        this.context = context;
    }

    public void add(Dummy temp) {
        dummyList.add(temp);
    }

    public void addList(List<Dummy> dummyList) {
        this.dummyList = dummyList;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return dummyList.size();
    }

    @Override
    public Object getItem(int i) {
        return dummyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ImageView imageView;
        TextView titleText;
        TextView timeStampText;
        TextView descriptionText;

        if (view == null) {
            view = mInflater.inflate(R.layout.simple_row, null);
            imageView = (ImageView) view.findViewById(R.id.img);
            titleText = (TextView) view.findViewById(R.id.TITLE);
            timeStampText = (TextView) view.findViewById(R.id.TIME_STAMP);
            descriptionText = (TextView) view.findViewById(R.id.DESCRIPTON);

            Dummy temp = dummyList.get(i);

            titleText.setText(temp.getTitle());
            timeStampText.setText(temp.getTime());
            descriptionText.setText(temp.getDesc());

            //Downloading image from the URL provided.

            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Bitmap bitmap = null;
                InputStream iStream = null;
                URL url = new URL(temp.getImage());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(5000 /* milliseconds */);
                conn.setConnectTimeout(7000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                iStream = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(iStream);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            v = mInflater.inflate(R.layout.simple_row, viewGroup, false);
        }


        ///#use    return convertView;
        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 10;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }
}