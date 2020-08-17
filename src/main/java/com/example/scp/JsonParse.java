package com.example.scp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParse {
    public  String names;

    JsonParse(String names) {
        this.names = names;
    }

    public String classcode() {
        String classcode = null;
        try {
            //jsonパース
            JSONObject json = new JSONObject(names);
            classcode = json.getString("class");
        }catch(Exception e) {
            Log.e("status", e.getMessage());
        }
        return classcode;
    }

    public String parse(String week) {
        StringBuilder list = new StringBuilder();
        try {
            //jsonパース
            JSONObject json = new JSONObject(names);
            JSONArray monday = json.getJSONArray(week);
            for (int i = 0; i < monday.length(); i++) {
                JSONObject data = monday.getJSONObject(i);
                String name = data.getString("name");
                list.append(name).append("\n");
            }
        }catch(Exception e){
            Log.e("Hoge",e.getMessage());
        }
        return list.toString();
    }
}
