package shopdaily.dev.accordhk.com.shopdaily.Uility;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import com.google.gson.Gson;

/**
 * Created by Owner on 8/24/2016.
 */
public class MyPreferenceApplication {
    public static String deviceKey = "device key";
    public static  String locationKey = "location key";
    public static String GPSOption = "GPS option";
    //    Type listOfObjects = new TypeToken<ArrayList<obs_form_DataModel>>() {
//    }.getType();



    public void setUserLocation(Context context, Location location) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(deviceKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonObject = gson.toJson(location);
        editor.putString(locationKey,jsonObject);
        editor.apply();
    }
    public Location getUserLocation(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(deviceKey,Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonObject = sharedPreferences.getString(locationKey,null);
        Location location = gson.fromJson(jsonObject,Location.class);
        return location;
    }

    public void setGPSOption (Context context,boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(deviceKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(GPSOption,value);
        editor.apply();
    }

    public boolean getGPSOption (Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(deviceKey,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(GPSOption,false);

    }


}
