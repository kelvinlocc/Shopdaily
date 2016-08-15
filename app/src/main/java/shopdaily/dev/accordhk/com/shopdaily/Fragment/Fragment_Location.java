package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import shopdaily.dev.accordhk.com.shopdaily.Activity.Shop_details;
import shopdaily.dev.accordhk.com.shopdaily.R;


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
    double shop_coordination [][] = {{1,2},{3,4},{5,6},{7,8},{9,10}};

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated ");

//        location = getActivity().getResources().get(R.array.shop_list);

        LocationManager lm = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {
            showSettingsAlert("GPS");
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {showSettingsAlert("network");}


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
    public void onLocationChanged(Location l2) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                new LatLng(l2.getLatitude(), l2.getLongitude()), 15);
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
            showSettingsAlert("GPS");

        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

//    private void setUpMap() {
//
//        Log.i(TAG, "setUpMap");
//
//        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.location_1);
//        Bitmap b = bitmapdraw.getBitmap();
//        Bitmap location_1 = Bitmap.createScaledBitmap(b, b.getWidth() / 2, b.getHeight() / 2, false);
//
//        BitmapDrawable bitmapdraw_2 = (BitmapDrawable) getResources().getDrawable(R.drawable.location_2);
//        Bitmap b_2 = bitmapdraw_2.getBitmap();
//        Bitmap location_2 = Bitmap.createScaledBitmap(b_2, b_2.getWidth() / 2, b_2.getHeight() / 2, false);
//
//        myMarker = myGoogleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(yeach_camera_shop_coordinate_X, yeach_camera_shop_coordinate_Y))
//                .title("Yeach Camera Shop").icon(BitmapDescriptorFactory.fromBitmap(location_1)));
//
//        myGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick(Marker myMarker) {
//                Intent intent = new Intent(getActivity(), Shop_details.class);
//                startActivity(intent);
//            }
//        });
//
//        myGoogleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(burgeroom_coordinate_X, burgeroom_coordinate_Y))
//                .title("Burgeroom").icon(BitmapDescriptorFactory.fromBitmap(location_2)));
//
//        myGoogleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(via_tokyo_coordinate_X, via_tokyo_coordinate_Y))
//                .title("Via Tokyo").icon(BitmapDescriptorFactory.fromBitmap(location_1)));
//
//        myGoogleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(little_vegas_coordinate_X, little_vegas_coordinate_Y))
//                .title("Little Vegas").icon(BitmapDescriptorFactory.fromBitmap(location_2)));
//
//    }


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
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                getActivity());

        alertDialog.setTitle(provider + " SETTINGS");

        alertDialog
                .setMessage(provider + " is not enabled! Want to go to settings menu?");

        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        getActivity().startActivity(intent);
                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }

    /**** The mapfragment's id must be removed from the FragmentManager
     **** or else if the same it is passed on the next time then
     **** app will crash ****/
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        if (myGoogleMap != null) {
//            MainActivity.fragmentManager.beginTransaction()
//                    .remove(MainActivity.fragmentManager.findFragmentById(R.id.map)).commit();
//            myGoogleMap = null;
//        }
//    }

}
