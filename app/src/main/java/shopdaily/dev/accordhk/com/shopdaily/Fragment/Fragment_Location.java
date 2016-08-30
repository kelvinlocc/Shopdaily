package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;


public class Fragment_Location extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, OnMapReadyCallback {

    private static String TAG = "Fragment_Location";

    private double coordinate_X = 29.759386;
    private double coordinate_Y = -95.397082;

    double yeach_camera_shop_coordinate_X = 29.766128;
    double yeach_camera_shop_coordinate_Y = -95.396760;

    double burgeroom_coordinate_X = 29.760287;
    double burgeroom_coordinate_Y = -95.399637;

    double via_tokyo_coordinate_X = 29.757890;
    double via_tokyo_coordinate_Y = -95.399966;

    double little_vegas_coordinate_X = 29.760337;
    double little_vegas_coordinate_Y = -95.394815;
    double shop_coordination[][] = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};

    GoogleMap map;
    LatLng latlng;
    private LocationRequest lr;
    MapFragment mapFragment;
    ImageView iv;

    private static View view;


    private static GoogleMap myGoogleMap;
    private Marker myMarker;
    Marker mCurrLocation;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    double location[];
    MyPreApp myPreApp;
    CheckBox checkBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated ");

        myPreApp = new MyPreApp();


        final LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean isLocationServiceEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isLocationServiceEnabled) {
            Log.i(TAG, "onCreateView: locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER); is true");
        } else {
            if (!myPreApp.getGPSOption()) {
                showSettingsAlert("GPS");
            }
        }
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null) {
            Log.d(TAG, "device offline");
        } else {
            Log.d(TAG, "device online");
        }


        if (mGoogleApiClient == null) {
            Log.i(TAG, "mGoogleApiClient is null ");

            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }

        view = inflater.inflate(R.layout.fragment_location, container,
                false);

        if (container == null) {
            return null;
        }
        onMapReady(myGoogleMap);
        setUpMapIfNeeded();
        return view;
    }

    public void setUpMapIfNeeded() {
        Log.i(TAG, "setUpMapIfNeeded");

        // Do a null check to confirm that we have not already instantiated the map.
        if (myGoogleMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            myGoogleMap = ((SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map)).getMap();
        } else {
            Log.i(TAG, "myGoogleMap is null");
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i(TAG, "onMapReady");

        myGoogleMap = googleMap;

//        myGoogleMap.setMyLocationEnabled(true);

        buildGoogleApiClient();

        mGoogleApiClient.connect();
    }

    protected synchronized void buildGoogleApiClient() {
        Log.i(TAG, "buildGoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    public void onLocationChanged(Location location) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                new LatLng(location.getLatitude(), location.getLongitude()), 15);
        map.animateCamera(cameraUpdate);
    }

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {

    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Log.i(TAG, "onConnected");

        try {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
        } catch (SecurityException e) {

            return;
        }
        if (mLastLocation != null) {
            coordinate_X = mLastLocation.getLatitude();
            coordinate_Y = mLastLocation.getLongitude();
//            Log.i(TAG, "coordinate_X: " + coordinate_X + "coordinate_Y: " + coordinate_Y);
            LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            mCurrLocation = myGoogleMap.addMarker(markerOptions);
            moveMap(latLng);
            Log.i(TAG, "moveMap");


//            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
//            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        } else {
            Log.i(TAG, "mLastLocation is null");
            if (!myPreApp.getGPSOption()) {
                showSettingsAlert("GPS");
            }

        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    private void moveMap(LatLng place) {
        // 建立地圖攝影機的位置物件
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(place)
                        .zoom(15)
                        .build();

        // 使用動畫的效果移動地圖
        myGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void showSettingsAlert(String provider) {

        Log.i(TAG, "showSettingsAlert: ");
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.checkbox, null);
        checkBox = (CheckBox) view.findViewById(R.id.skip);


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                getActivity());

        alertDialog.setTitle(provider + " SETTINGS");
        alertDialog.setView(view);
        alertDialog
                .setMessage(provider + " is not enabled! Want to go to settings menu?");

        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (checkBox.isChecked()) {
                            Toast.makeText(getActivity(), "don't ask again!", Toast.LENGTH_SHORT).show();
                            myPreApp.setGPSOption(true);
                        }
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        getActivity().startActivity(intent);
                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (checkBox.isChecked()) {
                            Toast.makeText(getActivity(), "don't ask again!", Toast.LENGTH_SHORT).show();
                            myPreApp.setGPSOption(true);
                        }
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }


}
