package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import shopdaily.dev.accordhk.com.shopdaily.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static String TAG = "MapsActivity ";
    private GoogleMap mMap;

    String target_shop_name;
    String target_shop_location;
    String target_shop_full_location;
    String target_user_to_shop_distance;
    double target_shop_coordination_x = 0;
    double target_shop_coordination_y = -0;


    //
    ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "get extra: " + getIntent().getExtras().get("shop_location_x"));
        Log.i(TAG, "get extra: " + getIntent().getExtras().get("shop_location_y"));

        target_shop_name = getIntent().getExtras().getString("shop_name");
        target_shop_location = getIntent().getExtras().getString("shop_location");
        target_shop_full_location = getIntent().getExtras().getString("shop_full_location");
        target_user_to_shop_distance = String.valueOf(getIntent().getExtras().getInt("shop_distance"));
        target_shop_coordination_x = getIntent().getExtras().getDouble("shop_location_x");
        target_shop_coordination_y = getIntent().getExtras().getDouble("shop_location_y");

        setContentView(R.layout.activity_maps);
        TextView shop_name = (TextView) findViewById(R.id.shop_name);
        TextView shop_location = (TextView) findViewById(R.id.shop_location);
        TextView distance = (TextView) findViewById(R.id.distance);

        shop_name.setText(target_shop_name);
        shop_location.setText(target_shop_full_location);
        distance.setText(target_user_to_shop_distance + " km");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        TextView btn_go_back = (TextView) findViewById(R.id.txt_go_back);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog = ProgressDialog.show(MapsActivity.this, "", "back!", true);


                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng place = new LatLng(target_shop_coordination_x, target_shop_coordination_y);
        moveMap(place);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.location_1);
        Bitmap original_bitmap = bitmapDrawable.getBitmap();
        Bitmap resized_bitmap_location = Bitmap.createScaledBitmap(original_bitmap, original_bitmap.getWidth() / 2, original_bitmap.getHeight() / 2, false);

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(target_shop_coordination_x, target_shop_coordination_y))
                .title(target_shop_name).icon(BitmapDescriptorFactory.fromBitmap(resized_bitmap_location))).showInfoWindow();

        // recycle the bitmap:
        if (original_bitmap != null) {
            original_bitmap.recycle();
        }
        if (resized_bitmap_location != null) {
            resized_bitmap_location.recycle();
        }
    }

    private void setUpMap() {
        // 刪除原來預設的內容
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));

        // 建立位置的座標物件
//        LatLng place = new LatLng(25.033408, 121.564099);
//         移動地圖
//        moveMap(place);

    }


    // 移動地圖到參數指定的位置
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

}
