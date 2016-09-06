package shopdaily.dev.accordhk.com.shopdaily.Uility;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response_Data;


public class MyApplication extends Application {

    public static final String DB_NAME = "MOTT";
    private List<Activity> activityList;
    String TAG = "MyApplication";
    private static MyApplication mInstance;



    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        mInstance = this;
        activityList = new ArrayList<>();
    }


    private static MyApplication getInstance() {
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    public static SharedPreferences getSharedPreferences() {
        return getInstance().getSharedPreferences("shop daily database", getInstance().MODE_PRIVATE);
    }



}
