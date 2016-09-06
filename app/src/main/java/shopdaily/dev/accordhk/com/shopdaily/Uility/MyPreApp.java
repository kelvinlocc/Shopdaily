package shopdaily.dev.accordhk.com.shopdaily.Uility;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

import com.google.gson.Gson;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Shop_Response;


/**
 * Created by Owner on 8/24/2016.
 */
public class MyPreApp {
    static String TAG = "MyPreApp";
    public static String deviceKey = "device key";
    public static  String locationKey = "location key";
    public static String GPSOption = "GPS option";
    private static String TestKey = "test key";

    private static String Login_data_key = "login data key";
    private static String Shop_Response_data_key = "shop response data key";


    public void baseSharedPreference (String key, String value){
        SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
        editor.putString(key,value);
        editor.apply();
        Log.i(TAG, "setTestResult: string= "+value);
    }

    public void setShopResponse (Shop_Response shopResponse){
        SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
        Gson gson =new Gson();
        String jsonObject = gson.toJson(shopResponse);
        editor.putString(Shop_Response_data_key,jsonObject);
        editor.apply();
    }

    public Shop_Response getShopResponse (){
        Gson gson = new Gson();
        String jsonObject = MyApplication.getSharedPreferences().getString(Shop_Response_data_key,null);
        return gson.fromJson(jsonObject,Shop_Response.class);
    }


    public void setLoginResponse(Login_Response login_response){
        SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
        Gson gson = new Gson();
        String jsonObject = gson.toJson(login_response);
        editor.putString(Login_data_key,jsonObject);
        editor.apply();
    }

    public Login_Response getLoginResponse(){
        Gson gson = new Gson();
        String jsonObject = MyApplication.getSharedPreferences().getString(Login_data_key,null);
        return  gson.fromJson(jsonObject,Login_Response.class);
    }

    public void setUserLocation( Location location) {
        SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
        Gson gson = new Gson();
        String jsonObject = gson.toJson(location);
        editor.putString(locationKey,jsonObject);
        editor.apply();
    }
    public Location getUserLocation(){
        Gson gson = new Gson();
        String jsonObject =  MyApplication.getSharedPreferences().getString(locationKey,null);
        return gson.fromJson(jsonObject,Location.class);
    }

    public void setGPSOption (boolean value){
        SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
        editor.putBoolean(GPSOption,value);
        editor.apply();
    }

    public boolean getGPSOption (){
        return MyApplication.getSharedPreferences().getBoolean(GPSOption,false);
    }


    public void setTestResult (String string){
        baseSharedPreference(TestKey,string);
        Log.i(TAG, "setTestResult: string= "+string);
    }
    public String getTestKey (){
        return MyApplication.getSharedPreferences().getString(TestKey,"error");
    }
}
