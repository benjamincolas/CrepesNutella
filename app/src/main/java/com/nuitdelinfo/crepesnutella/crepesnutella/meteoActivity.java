package com.nuitdelinfo.crepesnutella.crepesnutella;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class meteoActivity extends AppCompatActivity {

    private TextView t1_temp,t5_humi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);


    t1_temp = (TextView)findViewById(R.id.txttemp);

    t5_humi = (TextView)findViewById(R.id.txthumi);

find_weather();
    }
    public  void find_weather(){
        String url ="https://api.openweathermap.org/data/2.5/weather?q=Chambray-les-Tours,FR&appid=c639440f001d3c68da469812d1382746&units=Imperial";
// String url ="https://api.openweathermap.org/data/2.5/weather?q=Chambray-les-Tours,%20FR&appid=c639440f001d3c68da469812d1382746&units=Imperial";
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                JSONObject main_object = response.getJSONObject("main");
                JSONArray array = response.getJSONArray("weather");
                JSONObject object =array.getJSONObject(0);
                String temp = String.valueOf(main_object.getDouble("temp"));
                String description = object.getString("description") ;
                String city = response.getString("name");
                String humi = String.valueOf(main_object.getDouble("humidity"));




                t5_humi.setText(humi);


                    double temp_int  = Double.parseDouble(temp);
                    double centi = (temp_int - 32 ) / 1.8000;
                    centi = Math.round(centi);
                    int i = (int )centi;
                    t1_temp.setText(String.valueOf(i));


            }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        RequestQueue queue =Volley.newRequestQueue(this);
        queue.add(jor);

    }
}



