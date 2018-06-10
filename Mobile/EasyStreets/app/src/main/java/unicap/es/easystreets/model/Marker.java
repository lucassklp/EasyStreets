package unicap.es.easystreets.model;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import unicap.es.easystreets.R;

public class Marker {

    private long id;
    private double latitude;
    private double longitude;
    private String description;
    private String title;
    private StreetFurniture streetFurniture;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public StreetFurniture getStreetFurniture() {
        return streetFurniture;
    }

    public void setStreetFurniture(StreetFurniture streetFurniture) {
        this.streetFurniture = streetFurniture;
    }

    public MarkerOptions getMarkerOptions(){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(this.getLatitude(), this.getLongitude()));
        markerOptions.title(this.getTitle());
        markerOptions.icon(BitmapDescriptorFactory.fromResource(this.streetFurniture.getResource()));
        return markerOptions;
    }
}
