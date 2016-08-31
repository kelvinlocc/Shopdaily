package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import shopdaily.dev.accordhk.com.shopdaily.Activity.MainActivity;
import shopdaily.dev.accordhk.com.shopdaily.Activity.Shop_details;
import shopdaily.dev.accordhk.com.shopdaily.R;


/**
 * Created by iFocus on 27-10-2015.
 */
public class Fragment_Location_2 extends Fragment {

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
    private static View view;

    private static GoogleMap mMap;
    private Marker myMarker;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        view = (RelativeLayout) inflater.inflate(R.layout.fragment_location, container, false);
        // Passing harcoded values for latitude & longitude. Please change as per your need. This is just used to drop a Marker on the Map


        setUpMapIfNeeded(); // For setting up the MapFragment

        return view;
    }

    /*****
     * Sets up the map if it is possible to do so
     *****/
    public void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map)).getMap();

//            mMap = ((SupportMapFragment) MainActivity.fragmentManager
//                    .findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
                setUpMap();
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the
     * camera.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap}
     * is not null.
     */
    private void setUpMap() {

        LatLng place = new LatLng(coordinate_X, coordinate_Y);
        moveMap(place);

        BitmapDrawable bitmapDrawable_01 = (BitmapDrawable) getResources().getDrawable(R.drawable.location_1);
        Bitmap original_bitmap_01 = bitmapDrawable_01.getBitmap();
        Bitmap resized_bitmap_location_01 = Bitmap.createScaledBitmap(original_bitmap_01, original_bitmap_01.getWidth() / 2, original_bitmap_01.getHeight() / 2, false);




        BitmapDrawable bitmapDrawable_02 = (BitmapDrawable) getResources().getDrawable(R.drawable.location_2);
        Bitmap original_bitmap_02 = bitmapDrawable_02.getBitmap();
        Bitmap resized_bitmap_location_02 = Bitmap.createScaledBitmap(original_bitmap_02, original_bitmap_02.getWidth() / 2, original_bitmap_02.getHeight() / 2, false);

        myMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(yeach_camera_shop_coordinate_X, yeach_camera_shop_coordinate_Y))
                .title("Yeach Camera Shop_Response").icon(BitmapDescriptorFactory.fromBitmap(resized_bitmap_location_01)));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker myMarker) {
                Intent intent = new Intent(getActivity(), Shop_details.class);
                startActivity(intent);
            }
        });

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(burgeroom_coordinate_X, burgeroom_coordinate_Y))
                .title("Burgeroom").icon(BitmapDescriptorFactory.fromBitmap(resized_bitmap_location_02)));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(via_tokyo_coordinate_X, via_tokyo_coordinate_Y))
                .title("Via Tokyo").icon(BitmapDescriptorFactory.fromBitmap(resized_bitmap_location_01)));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(little_vegas_coordinate_X, little_vegas_coordinate_Y))
                .title("Little Vegas").icon(BitmapDescriptorFactory.fromBitmap(resized_bitmap_location_02)));


        // recycle all bitmap or it may crash in some device
        if (original_bitmap_01!=null){
            original_bitmap_01.recycle();
            original_bitmap_01= null;
        }
        if (resized_bitmap_location_01!=null){
            resized_bitmap_location_01.recycle();
            resized_bitmap_location_01= null;
        }
        if (original_bitmap_02!=null){
            original_bitmap_02.recycle();
            original_bitmap_02= null;
        }
        if (resized_bitmap_location_02!=null){
            resized_bitmap_location_02.recycle();
            resized_bitmap_location_02= null;
        }


    }


    private void moveMap(LatLng place) {
        // 建立地圖攝影機的位置物件
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(place)
                        .zoom(15)
                        .build();

        // 使用動畫的效果移動地圖
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (mMap != null)
            setUpMap();

        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) MainActivity.fragmentManager
                    .findFragmentById(R.id.map)).getMap(); // getMap is deprecated
            // Check if we were successful in obtaining the map.
            if (mMap != null)
                setUpMap();
        }
    }

    /**** The mapfragment's id must be removed from the FragmentManager
     **** or else if the same it is passed on the next time then
     **** app will crash ****/
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        if (mMap != null) {
//            MainActivity.fragmentManager.beginTransaction()
//                    .remove(MainActivity.fragmentManager.findFragmentById(R.id.map)).commit();
//            mMap = null;
//        }
//    }

}
