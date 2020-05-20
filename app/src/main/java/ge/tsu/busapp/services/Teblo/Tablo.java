package ge.tsu.busapp.services.Teblo;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;

public class Tablo {
    String busNumber;
    String minute;

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getMinuter() {
        return minute;
    }

    public void setMinuter(String minute) {
        this.minute = minute;
    }
}
