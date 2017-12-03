package com.example.lathe.meow;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lathe on 12/3/2017.
 */

public class GetFeed extends AsyncTask<String, Void, String> {
    //        private ListManager listManager;
    private List<Dummy> asyncList = new ArrayList<Dummy>();

    @Override
    protected String doInBackground(String... URL) {
        String REQUEST_METHOD = "GET";
        int READ_TIMEOUT = 15000;
        int CONNECTION_TIMEOUT = 15000;
        InputStreamReader streamReader;
        BufferedReader bufferedReader;
        String result ="";
        try {

            java.net.URL url = new URL(URL[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setRequestMethod(REQUEST_METHOD);

            urlConnection.connect();

            try {
                streamReader = new InputStreamReader(urlConnection.getInputStream());
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                streamReader.close();

                return result;
            } catch (Exception exception) {
                Log.d("Thread Crash", "Connection terminated for some reason.");
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            //Close connections.
            return result;
        }

    }

    @Override
    protected void onPostExecute(String response) {


    }

}

