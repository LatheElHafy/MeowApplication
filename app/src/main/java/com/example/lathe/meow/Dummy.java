package com.example.lathe.meow;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lathe on 12/1/2017.
 */

public class Dummy {

    private List<Dummy> dummyList = new ArrayList<Dummy>();
    private String title;
    private String image;
    private String time;
    private String desc;

public Dummy(){

}
    public Dummy(String title, String image, String time, String desc) {
        this.dummyList = new ArrayList<>();
        this.title = title;
        this.image = image;
        this.time = time;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Dummy> response (String response){
        if(dummyList != null){
            dummyList = new ArrayList<Dummy>();
        }

        if (response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i <= jsonArray.length()-1; i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String title =(String) jsonObject.get("title");
                String image = (String) jsonObject.get("image_url");
                String time = (String) jsonObject.getString("timestamp");
                String description = (String) jsonObject.getString("description");


                dummyList.add(new Dummy(title,image,time,description));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("EXCEPTION","========> Exception has been thrown");
        }
        return this.dummyList;
    }

}
