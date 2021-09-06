package com.example.hackerton;

public class test {
    private String co;
    private double lat1;
    private double lat2;
    private double lon1;
    private double lon2;

    public void setColor(String co){
        this.co=co;
    }

    public String getColor(){
        return co;
    }

    public double getLat1(){
        return lat1;
    }

    public double getLat2(){
        return lat2;
    }

    public void setLat1(double lat1){
        this.lat1=lat1;
    }

    public void setLat2(double lat2){
        this.lat2=lat2;
    }

    public double getLon1(){
        return lon1;
    }

    public double getLon2(){
        return lon2;
    }

    public void setLon1(double lon1){
        this.lon1=lon1;
    }

    public void setLon2(double lon2){
        this.lon2=lon2;
    }


    /*@Override
    public String toString() {
        return "test{" +
                "lat1=" + lat1 +
                ", lon1=" + lon1 +
                ", lat2=" + lat2 +
                ", lon2=" + lon2 +
                '}';
    }*/
}
