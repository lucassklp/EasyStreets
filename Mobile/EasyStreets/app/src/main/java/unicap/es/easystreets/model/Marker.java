package unicap.es.easystreets.model;

public class Marker {

    public long id;
    public double latitude;
    public double longitude;
    public String description;
    public String title;
    public StreetFurniture streetFurniture;

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
}
