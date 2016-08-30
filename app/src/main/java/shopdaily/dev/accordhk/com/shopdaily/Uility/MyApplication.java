package shopdaily.dev.accordhk.com.shopdaily.Uility;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class MyApplication extends Application{

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


    private static MyApplication getInstance(){
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    public static SharedPreferences getSharedPreferences(){
        return getInstance().getSharedPreferences("shop daily database", getInstance().MODE_PRIVATE);
    }


}
