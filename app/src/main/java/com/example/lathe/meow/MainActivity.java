package com.example.lathe.meow;

import android.app.Activity;
import android.database.DataSetObserver;
import android.graphics.ColorSpace;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Dummy> dummies = new ArrayList<Dummy>();
    private static final String API_ADDRESS = "https://chex-triplebyte.herokuapp.com/api/cats?page=0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dummy dummy = new Dummy();
        listView = (ListView) findViewById(R.id.list_view);


        try {
            String temp = new GetFeed().execute(API_ADDRESS).get();
            List<Dummy> dummyList = dummy.response(temp);
//            listAdapter = new ArrayAdapter<Dummy>(this,R.id.list_view,dummyList);
            ListManager listManager = new ListManager(this);
            listManager.addList(dummyList);


            listView.setAdapter(listManager);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}


