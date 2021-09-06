package com.example.hackerton;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.util.FusedLocationSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private MapView mapView;


    private double lat, lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView=findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);


        mapView.getMapAsync(this);

        locationSource =
                new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) { // 권한 거부됨
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
    }



    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;

        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);


        CameraPosition cameraPosition = new CameraPosition(
                new LatLng(36.35062086030392, 127.3848401553239),  // 위치 지정
                15                         // 줌 레벨
        );
        naverMap.setCameraPosition(cameraPosition);

        InputStream is=getResources().openRawResource(R.raw.daejeon_taxi2);
        BufferedReader reader=new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line="";

        try{
            reader.readLine();
            while ((line=reader.readLine())!=null){

                String[] tokens=line.split(",");
                test t=new test();

                t.setColor(tokens[0]);
                t.setLat1(Double.parseDouble(tokens[1]));
                t.setLon1(Double.parseDouble(tokens[2]));
                t.setLat2(Double.parseDouble(tokens[3]));
                t.setLon2(Double.parseDouble(tokens[4]));

                PathOverlay path = new PathOverlay();
                path.setCoords(Arrays.asList(
                        new LatLng(t.getLat1(), t.getLon1()),
                        new LatLng(t.getLat2(), t.getLon2())
                ));

                if (t.getColor().equals("G")) {
                    path.setColor(Color.GREEN);
                    path.setOutlineColor(Color.GREEN);
                    path.setMap(naverMap);
                }else if (t.getColor().equals("Y")){
                    path.setColor(Color.YELLOW);
                    path.setOutlineColor(Color.YELLOW);
                    path.setMap(naverMap);
                }else{
                    path.setColor(Color.RED);
                    path.setOutlineColor(Color.RED);
                    path.setMap(naverMap);
                }

            }
        } catch (IOException e){
            Log.d("MyActivity","Error reading data file on line"+line,e);
            e.printStackTrace();
        }

        /*naverMap.addOnLocationChangeListener(new NaverMap.OnLocationChangeListener() {
            @Override
            public void onLocationChange(@NonNull Location location) {
                lat=location.getLatitude();
                lon=location.getLongitude();


                //Toast.makeText(getApplicationContext(),lat+","+lon,Toast.LENGTH_SHORT).show();

                PathOverlay path = new PathOverlay();
                path.setCoords(Arrays.asList(
                        new LatLng(36.35059493712793, 127.38479723998036),
                        new LatLng(lat,lon)
                ));
                path.setMap(naverMap);
            }
        });*/
    }

    public void funcMoveTraffic(View v){
        Intent it=new Intent(MainActivity.this,TrafficActivity.class);
        startActivity(it);
        onBackPressed();
    }


}