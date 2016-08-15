package shopdaily.dev.accordhk.com.shopdaily.Uility;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by KelvinLo on 7/5/2016.
 */
public class AppLocationService extends Service implements LocationListener {

    protected LocationManager locationManager;
    Location location;

    private static final long MIN_DISTANCE_FOR_UPDATE = 10;
    private static final long MIN_TIME_FOR_UPDATE = 1000 * 60 * 2;

    public AppLocationService(Context context) {
        locationManager = (LocationManager) context
                .getSystemService(LOCATION_SERVICE);
    }

    public Location getLocation(String provider) {
        try {
//            LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);//

            if (locationManager.isProviderEnabled(provider)) {
                Log.i("check_", "isProviderEnabled 2");
                try {
                    locationManager.requestLocationUpdates(provider,
                            MIN_TIME_FOR_UPDATE, MIN_DISTANCE_FOR_UPDATE, this);
                } catch (SecurityException e) {
                    Log.i("check_", "requestLocationUpdates fail");
                    Log.i("check_", "SecurityException:" + e);
                }
                if (locationManager != null) {
                    Log.i("check_", "locationManager got!");

                    location = locationManager.getLastKnownLocation(provider);

                    if (location != null) {
//                        Log.i("check_", "get location: x: " + location.getLatitude() + " y: " + location.getLongitude());
                    } else {
                        Log.i("check_", "location obj is null");
                    }

                    return location;
                } else {
                    Log.i("check_", "locationManager is null");
                }
            }
        } catch (SecurityException e) {
            Log.i("check_", "getLocation failed, SecurityException ");
        }
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        updateGPSCoordinates(location);

    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    double latitude;
    double longitude;

    public void updateGPSCoordinates(Location location) {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Log.i("check_", "latitude "+latitude);

        } else {
            Log.i("check_", "updateGPSCoordinates fail, location is null");
        }
    }

}
