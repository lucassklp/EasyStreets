package unicap.es.easystreets.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import unicap.es.easystreets.R;

public class Marker {

    private long id;
    private double latitude;
    private double longitude;
    private String description;

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

    public StreetFurniture getStreetFurniture() {
        return streetFurniture;
    }

    public void setStreetFurniture(StreetFurniture streetFurniture) {
        this.streetFurniture = streetFurniture;
    }

    public MarkerOptions getMarkerOptions(Context ctx){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(this.getLatitude(), this.getLongitude()));
        markerOptions.title(this.getDescription());
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons(ctx, this.streetFurniture.getResource(), 200, 200)));
        return markerOptions;
    }

    public Bitmap resizeMapIcons(Context ctx, int res, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(ctx.getResources(), res);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }


}
