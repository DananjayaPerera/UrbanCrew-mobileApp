package com.example.urban_crew_extended;

public class BookingProfile {

    public String UserLocation;
    public String PickUpLocation;
    public String Date;
    public String Time;

    public BookingProfile(String userLocation, String pickUpLocation, String date, String time) {
        UserLocation = userLocation;
        PickUpLocation = pickUpLocation;
        Date = date;
        Time = time;
    }
}
