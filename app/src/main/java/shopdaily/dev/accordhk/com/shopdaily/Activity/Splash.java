package shopdaily.dev.accordhk.com.shopdaily.Activity;

/**
 * Created by KelvinLo on 6/8/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;

public class Splash extends Activity {
    private static boolean splashLoaded = false;
    Location userCurrentLocation = new Location("new");
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String TAG = "Splash ";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);



        /// for debug::
        userCurrentLocation.setLatitude(22.2848476);        /// for debug::
        userCurrentLocation.setLongitude(114.2144456);        /// for debug::

        MyPreApp myPreApp = new MyPreApp();
        myPreApp.setUserLocation(userCurrentLocation);
        Log.d(TAG, "onCreate: lat,long "+myPreApp.getUserLocation().getLatitude()+","+myPreApp.getUserLocation().getLongitude());

        if (!splashLoaded) {
            setContentView(R.layout.splashscreen_main);
            int secondsDelayed = 2;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent NextActivity = new Intent(Splash.this, LoginPage.class);
//                    NextActivity.putExtra("x",deviceKey.getLatitude());
                    startActivity(NextActivity);

//                    startActivity(new Intent(Splash.this, z_scroll_layout.class));
                    finish();
                }
            }, secondsDelayed * 1);

            splashLoaded = true;
        } else {
            Intent goToMainActivity = new Intent(Splash.this, MainActivity.class);
            goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(goToMainActivity);
            finish();
        }
    }
}