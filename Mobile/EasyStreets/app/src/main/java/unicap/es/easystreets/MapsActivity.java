package unicap.es.easystreets;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Logger;

import unicap.es.easystreets.model.Marker;
import unicap.es.easystreets.model.StreetFurniture;
import unicap.es.easystreets.rest.RestRequest;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, LocationListener  {

    private static final long LOCATION_REFRESH_TIME = 300;
    private static final float LOCATION_REFRESH_DISTANCE = 1000;

    private GoogleMap mMap;
    protected LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_princ, menu);
        return true;
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
        mMap.setOnMapClickListener(this);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        if ( ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED ) {
            try {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            }catch(SecurityException ex){
                Logger.getGlobal().warning(ex.getMessage());
            }

            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE, this);
        }

        Type type = new TypeToken<List<Marker>>(){}.getType();

        RestRequest<Object, List<Marker>> request = new RestRequest(Object.class, type);
        request.setUrl(RestRequest.BASE_URL + "marker");
        request.setMethod(Request.Method.GET);
        request.execute(this, result -> {
            for (Marker marker : result){
                mMap.addMarker(marker.getMarkerOptions(this));
            }
        }, err -> {
            Toast.makeText(this, "Erro ao adicionar o marker" + err.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onMapClick(LatLng latLng) {

        AlertDialog.Builder mAlertBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_marker, null);
        EditText etTitle = mView.findViewById(R.id.txtTitle);
        EditText etDescription = mView.findViewById(R.id.txtDescription);
        Spinner spinner = mView.findViewById(R.id.spinner);
        Button btn = mView.findViewById(R.id.btnAddMarker);

        mAlertBuilder.setView(mView);
        AlertDialog dialog = mAlertBuilder.create();

        ArrayAdapter<StreetFurniture> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, StreetFurniture.values());
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spinner.setAdapter(adapter);

        btn.setOnClickListener(v -> {
            Marker markr = new Marker();
            markr.setLatitude(latLng.latitude);
            markr.setLongitude(latLng.longitude);

            markr.setTitle(etTitle.getText().toString());
            markr.setDescription(etDescription.getText().toString());
          
            markr.setStreetFurniture((StreetFurniture)spinner.getSelectedItem());

            RestRequest<Marker, Object> request = new RestRequest(Marker.class, Object.class);
            request.setUrl(RestRequest.BASE_URL + "marker");
            request.setMethod(Request.Method.POST);

            request.execute(MapsActivity.this, markr, response -> {
                mMap.addMarker(markr.getMarkerOptions(this));
                Toast.makeText(MapsActivity.this, "Adicionado com sucesso" + latLng, Toast.LENGTH_SHORT).show();
            }, err -> {
                Toast.makeText(MapsActivity.this, "Erro ao adicionar o marker" + err.getMessage(), Toast.LENGTH_SHORT).show();
            });

            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 20));
        this.locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
