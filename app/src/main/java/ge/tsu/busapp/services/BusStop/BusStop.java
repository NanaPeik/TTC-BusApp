package ge.tsu.busapp.services.BusStop;

import com.google.gson.annotations.SerializedName;

public class BusStop {
    @SerializedName("Name")
    private String name;
    @SerializedName("StopId")
    private String stopId;
    @SerializedName("Type")
    private String type;
    @SerializedName("Sequence")
    private String sequence;
    @SerializedName("Froward")
    private String forward;
    @SerializedName("HasBoard")
    private String hasBoard;
    @SerializedName("Lat")
    private String lat;
    @SerializedName("Lon")
    private String lon;
    @SerializedName("Virtual")
    private boolean virtual;

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getHasBoard() {
        return hasBoard;
    }

    public void setHasBoard(String hasBoard) {
        this.hasBoard = hasBoard;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public boolean isVirtual() {
        return virtual;
    }

    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
