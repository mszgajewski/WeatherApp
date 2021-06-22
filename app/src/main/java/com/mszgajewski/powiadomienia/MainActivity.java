package com.mszgajewski.powiadomienia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btn_cityID, btn_getWeatherByID, getBtn_getWeatherByName;
    EditText et_dataInput;
    ListView lv_weatherReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cityID = findViewById(R.id.btn_getCityID);
        btn_getWeatherByID = findViewById(R.id.btn_getWeatherByCityID);
        getBtn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);

        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReport = findViewById(R.id.lv_weatherReport);

        btn_cityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url ="https://www.metaweather.com/api/location/search/?query=" + et_dataInput.getText().toString();

             JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                 @Override
                 public void onResponse(JSONArray response) {

                     String cityID ="";

                     try {
                         JSONObject cityInfo = response.getJSONObject(0);
                          cityID = cityInfo.getString("woeid");
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }

                     Toast.makeText(MainActivity.this, "City ID = " + cityID,Toast.LENGTH_SHORT).show();
                 }
             }, new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {
                     Toast.makeText(MainActivity.this, "City ID = ",Toast.LENGTH_SHORT).show();
                 }
             });
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);
   }
        });

        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Kliknąłeś 2", Toast.LENGTH_SHORT).show();
            }
        });

        getBtn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Wpisałeś " + et_dataInput.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
