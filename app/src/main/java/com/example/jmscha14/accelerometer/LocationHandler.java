package com.example.jmscha14.accelerometer;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
;

import java.util.Observable;

/**
 * Created by jmscha14 on 9/13/2016.
 */
public class LocationHandler extends Observable implements LocationListener {

    LocationManager lm;
    Location location;
    double longi;
    double lat;


    public LocationHandler(Activity act) {
        lm = (LocationManager) act.getSystemService(Context.LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            lm.requestLocationUpdates(lm.NETWORK_PROVIDER,1000,0,this);
            longi = location.getLatitude();
            lat = location.getLongitude();
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        longi = location.getLatitude();
        lat = location.getLongitude();

        double [] loca = {longi,lat};
        setChanged();
        notifyObservers(loca);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
