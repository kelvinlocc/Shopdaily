package shopdaily.dev.accordhk.com.shopdaily.Activity;

/**
 * Created by KelvinLo on 6/8/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.SingleShotLocationProvider;

public class Splash extends Activity {
    private static boolean splashLoaded = false;
    Location userCurrentLocation = new Location("new");
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getUserCurrentLocation(this);

        /// for debug::
        userCurrentLocation.setLatitude(22.2848476);        /// for debug::
        userCurrentLocation.setLongitude(114.2144456);        /// for debug::


        if (!splashLoaded) {
            setContentView(R.layout.splashscreen_main);
            int secondsDelayed = 5;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Log.i("check_", " myUserCurrentLocation @Handler ");

//                    Log.i("check_", " myUserCurrentLocation @Handler:: " + userCurrentLocation.getLatitude() + "," + userCurrentLocation.getLongitude());
                    String user_location_x = String.valueOf(userCurrentLocation.getLatitude());
                    String user_location_y  = String.valueOf(userCurrentLocation.getLongitude());
//                    String x = "test";
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("user_location_x", user_location_x);
                    editor.putString("user_location_y", user_location_y);
                    editor.commit();
                    Intent NextActivity = new Intent(Splash.this, LoginPage.class);
//                    NextActivity.putExtra("x",userCurrentLocation.getLatitude());
                    startActivity(NextActivity);

//                    startActivity(new Intent(Splash.this, z_scroll_layout.class));
                    finish();
                }
            }, secondsDelayed * 1);

            splashLoaded = true;
        } else {

//            Log.i("check_", " myUserCurrentLocation @Handler " + userCurrentLocation.getLatitude() + "," + userCurrentLocation.getLongitude());

            Intent goToMainActivity = new Intent(Splash.this, MainActivity.class);
            goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(goToMainActivity);
            finish();
        }
    }


    public void getUserCurrentLocation(Context context) {
        Log.i("check_", "getUserCurrentLocation... ");

        // when you need location
        // if inside activity context = this;

        SingleShotLocationProvider.requestSingleUpdate(context,
                new SingleShotLocationProvider.LocationCallback() {
                    @Override
                    public void onNewLocationAvailable(SingleShotLocationProvider.GPSCoordinates location) {

                        if (location == null) {
                        } else {
                            userCurrentLocation = new Location("l");
                            userCurrentLocation.setLongitude(location.longitude);
                            userCurrentLocation.setLatitude(location.latitude);
                        }
                        Log.i("check_", " myUserCurrentLocation @Splash " + userCurrentLocation.getLatitude() + "," + userCurrentLocation.getLongitude());
                    }
                });
    }
}