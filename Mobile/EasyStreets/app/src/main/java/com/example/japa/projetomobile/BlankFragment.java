package com.example.japa.projetomobile;


import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.TokenWatcher;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class BlankFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private static final String TAG = "MapsActivity";

    private GoogleMap mMap;

    private LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);


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

        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            Criteria criteria = new Criteria();

            String provider = locationManager.getBestProvider(criteria, true);

            Toast.makeText(getActivity(), "Provider: " + provider, Toast.LENGTH_LONG);

            mMap = googleMap;

            mMap.setOnMapClickListener(this);

            mMap.getUiSettings().setZoomControlsEnabled(true);

            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);

        }catch (SecurityException ex   ){
            Log.e(TAG, "ERRO", ex);
        }

        // Add a marker in Sydney and move the camera
        LatLng unicap = new LatLng(-8.0549845,-34.8883952);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(unicap);
        markerOptions.title("Marker in Unicap");


        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(unicap));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(getContext(), "Coordenadas" + latLng, Toast.LENGTH_SHORT).show();



    }
}
