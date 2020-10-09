package com.verardo.bootcamp.weather;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.*;

import androidx.core.app.ActivityCompat;

public class LocationServices implements LocationServicesInterface {
    private Context context;

    public LocationServices(Context context){
        this.context = context;
    }

    public Location getCurrentLocation() {
        LocationManager locMan = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }

        android.location.Location l = locMan.getLastKnownLocation(locMan.getBestProvider(new Criteria(), false));
        return new Location(l.getLatitude(),l.getLongitude());
    }
}