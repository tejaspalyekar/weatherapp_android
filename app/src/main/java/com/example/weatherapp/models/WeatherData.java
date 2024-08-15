package com.example.weatherapp.models;

public class WeatherData{
    String base;
    String visibility;
    String dt;
    String id;
    String location_name;
    int status;
    double temp;
    String main;

    public  WeatherData(String base, String visibility, String dt, String id, String location_name, int status, double temp, String main) {
        this.base = base;
        this.visibility = visibility;
        this.dt = dt;
        this.id = id;
        this.location_name = location_name;
        this.status = status;
        this.temp = temp;
        this.main = main;
    }



    public void setBase(String base) {
        this.base = base;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setCoords(double temp) {
        this.temp = temp;
    }

    public String getBase() {
        return base;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getId() {
        return id;
    }

    public String getDt() {
        return dt;
    }

    public String getLocation_name() {
        return location_name;
    }

    public double getTemp() {
        return temp;
    }

    public int getStatus() {
        return status;
    }

    public String getMain() {
        return main;
    }
}

