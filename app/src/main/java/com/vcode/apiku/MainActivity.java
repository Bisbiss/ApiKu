package com.vcode.apiku;

import android.content.Intent;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ListView;

import android.widget.Toast;



import com.android.volley.Request;

import com.android.volley.RequestQueue;

import com.android.volley.Response;

import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;

import com.android.volley.toolbox.Volley;



import org.json.JSONArray;

import org.json.JSONException;

import org.json.JSONObject;



import java.util.ArrayList;

import java.util.List;

public class MainActivity extends AppCompatActivity {
ListView list;
private List<TimItem> TimItemList;
private static final String JSON_URL ="https://raw.githubusercontent.com/openfootball/football.json/master/2015-16/en.1.clubs.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);
        TimItemList = new ArrayList<>();


        loadTim();
    }
        private void loadTim(){
            StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject obj = new JSONObject(response);
                                JSONArray playerArray = obj.getJSONArray("clubs");

                                for (int i = 0; i < playerArray.length(); i++) {

                                    JSONObject playerObject = playerArray.getJSONObject(i);


                                    TimItem TimItem = new TimItem(playerObject.getString("key"),
                                            playerObject.getString("name"),
                                            playerObject.getString("code"));

                                    TimItemList.add(TimItem);
                                }

                                ListViewAdapter adapter = new ListViewAdapter(TimItemList, getApplicationContext());

                                list.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

        }
