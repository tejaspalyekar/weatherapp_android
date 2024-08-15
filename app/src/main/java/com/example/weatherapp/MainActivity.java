package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.models.WeatherData;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView climate_temp;
    TextView climate_state;
    String api_key = "908ee85c110f2216ce21db52fcc7f214";
    String url ="https://api.openweathermap.org/data/2.5/weather?lat=19.2856&lon=72.8691&appid="+api_key;
    TextView location_data;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        climate_temp = findViewById(R.id.temprature);
        climate_state = findViewById(R.id.climateState);
        location_data = findViewById(R.id.curr_location);
        System.out.println(url);
        fetchWeather();
    }

    private void fetchWeather() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        System.out.println("weather data: " + response);

                        try {
                            // Parse the JSON response
                            JSONObject object = new JSONObject(response);

                            // Extract relevant data from JSON
                            String base = object.getString("base");
                            int visibility = object.getInt("visibility");
                            long dt = object.getLong("dt");
                            int id = object.getInt("id");
                            String locationName = object.getString("name");
                            int status = object.getInt("cod");
                            String main = object.getJSONArray("weather").getJSONObject(0).getString("main");
                            int temp = (int) (object.getJSONObject("main").getDouble("temp") - 273);


                            WeatherData weatherData = new WeatherData(
                                    base,
                                    String.valueOf(visibility),
                                    String.valueOf(dt),
                                    String.valueOf(id),
                                    locationName,
                                    status,
                                    temp,
                                    main
                            );


                            System.out.println("Parsed WeatherData: true" +weatherData.getBase());
                                            //formula for conversion C = K - 273.15

                            climate_temp.setText(String.valueOf(temp)+"°C");
                            climate_state.setText(main);
                            location_data.setText(locationName);
                        } catch (JSONException e) {
                            System.out.println("Error while parsing JSON object: " + e);
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error while fetching weather data: " + error);
            }
        });

        queue.add(stringRequest);
    }

}