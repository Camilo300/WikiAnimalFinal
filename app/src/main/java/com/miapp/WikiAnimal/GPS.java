package com.miapp.WikiAnimal;

import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GPS extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_s);
        int estado = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if(estado == ConnectionResult.SUCCESS){
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        else{
            Dialog mens = GooglePlayServicesUtil.getErrorDialog(estado,(Activity) getApplicationContext(), 10);
            mens.show();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings USet = mMap.getUiSettings();
        USet.setZoomControlsEnabled(true);

        LatLng Alaska = new LatLng(64.2008413, -149.4936733);
        mMap.addMarker(new MarkerOptions().position(Alaska).title("Marcador del Lobo").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        float zoomnivel1 = 1;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Alaska, zoomnivel1));

        LatLng india = new LatLng(20.593684, 78.96288);
        mMap.addMarker(new MarkerOptions().position(india).title("Marcador del Tigre").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(india, zoomnivel1));

        LatLng atlantico = new LatLng(-14.597398, -28.673482);
        mMap.addMarker(new MarkerOptions().position(atlantico).title("Marcador del Delfin").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(atlantico, zoomnivel1));

        LatLng arabia = new LatLng( 23.885942, 45.079162);
        mMap.addMarker(new MarkerOptions().position(arabia).title("Marcador del Guepardo").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(arabia, zoomnivel1));

        LatLng pacifico = new LatLng(4.861101, -124.332278);
        mMap.addMarker(new MarkerOptions().position(pacifico).title("Marcador del Tiburon").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pacifico, zoomnivel1));

        LatLng sudafrica = new LatLng( 	-30.559482,22.937506);
        mMap.addMarker(new MarkerOptions().position(sudafrica).title("Marcador del Leon").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sudafrica, zoomnivel1));

        LatLng antartida = new LatLng(-75.250973, -0.071389);
        mMap.addMarker(new MarkerOptions().position(antartida).title("Marcador del Pinguino").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(antartida, zoomnivel1));

        LatLng africa = new LatLng(-4.936264, 33.988226);
        mMap.addMarker(new MarkerOptions().position(africa).title("Marcador del Elefante").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(africa, zoomnivel1));

    }
}