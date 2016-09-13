package shopdaily.dev.accordhk.com.shopdaily.Uility;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response_Data;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.QA_Response;
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
    private static String QA_list_key = "QA list key";
    Type listOfObjects = new TypeToken<ArrayList<QA_Response>>() {
    }.getType();

    public void baseSharedPreference (String key, String value){
        SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
        editor.putString(key,value);
        editor.apply();
        Log.i(TAG, "setTestResult: string= "+value);
    }
    public void setQA_list (ArrayList<QA_Response> arrayList){
        SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
        Gson gson= new Gson();
        String jsonObject= gson.toJson(arrayList);
        editor.putString(QA_list_key,jsonObject);
        editor.apply();

    }

    public ArrayList<QA_Response> getQA_list (){
        Gson gson = new Gson();
        String jsonObject = MyApplication.getSharedPreferences().getString(QA_list_key,null);
        return gson.fromJson(jsonObject,listOfObjects);
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
    API myApi;
    MyPreApp myPreApp;
    public void update_member_profile(final Context context){
        myApi = new API(context);
        myPreApp = new MyPreApp();

        Log.i(TAG, "update_member_profile: ");
        String api_key = "654321";
        String lang_id = "1";
        Login_Response loginResponse = myPreApp.getLoginResponse();
        Login_Response_Data login_response_data = loginResponse.data;

        Log.i(TAG, "update_member_profile: login_response_data.member_nick_name  "+login_response_data.member_nick_name);


        if (login_response_data.shop_id.isEmpty()) {
            Toast.makeText(context, "warning!!!, the shop id is empty", Toast.LENGTH_SHORT).show();
        }
        myApi.update_member_profile(api_key, lang_id, login_response_data.member_session, login_response_data.member_email, login_response_data.member_password, "15", login_response_data.member_nick_name, login_response_data.member_gender, login_response_data.member_birthday, login_response_data.push_flag, login_response_data.shop_id, login_response_data.push_key_gcm, login_response_data.push_token_string, login_response_data.mobile_type, new API.onAjaxFinishedListener() {
            @Override
            public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
                Log.i(TAG, "onFinished: json " + json);
                JSONObject jsonObject = new JSONObject(json);
                if (Integer.parseInt(jsonObject.getString("return_status"))==1){
//                    Toast.makeText(profile_shop_owner_edit.this, ""+json, Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "member profile is updated!", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(context, "fail to update the member profile", Toast.LENGTH_SHORT).show();
                }



            }
        });
//        myApi.update_member_profile();
    }




    String uploadFileName = "uploadFileName";
    String username = "username";
    String currentTime = "currentTime";
    int serverResponseCode = 0;
    String upLoadServerUri = "http://shopdailydev.accordhkcloudapi.com/api/member_profile_image_upload";
    
    public int uploadImage(String api_key,String lang_id,String member_session,  String sourceFileUri) {
        Log.i(TAG, "uploadImage: ");
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);
        if (!sourceFile.isFile()) {
            Log.e("uploadImage", "Source File not exist :"
                    + sourceFileUri + "" + uploadFileName);
            Log.i(TAG, "run: \"Source File not exist :\"\n" +
                    " + uploadFilePath + \"\" + uploadFileName");
        } else {
            try {
                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUri);
                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                //conn.setRequestProperty("Accept-Charset", "UFT-8");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//                conn.setRequestProperty("uploaded_file", username + "_" + uploadFileName);
                conn.setRequestProperty("api_key", api_key);
                conn.setRequestProperty("lang_id", lang_id);
                conn.setRequestProperty("member_session", member_session);
                dos = new DataOutputStream(conn.getOutputStream());
                Log.i(TAG, "uploadImage: update");
                //for other params:
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=api_key" + lineEnd); // name=mobile_no so you have to get PHP side using mobile_no
                dos.writeBytes(lineEnd);
                dos.writeBytes(api_key); // mobile_no is String variable
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=lang_id" + lineEnd); // name=mobile_no so you have to get PHP side using mobile_no
                dos.writeBytes(lineEnd);
                dos.writeBytes(lang_id); // mobile_no is String variable
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=member_session" + lineEnd); // name=mobile_no so you have to get PHP side using mobile_no
                dos.writeBytes(lineEnd);
                dos.writeBytes(member_session); // mobile_no is String variable
                dos.writeBytes(lineEnd);
//                dos.writeBytes("#!/usr/local/bin/php --" + lineEnd);
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=upload_file; filename=" + username + "_" + currentTime + "_" + uploadFileName + lineEnd);
                dos.writeBytes(lineEnd);
                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }
                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                Log.i(TAG, "uploadImage: ");
                String serverResponseMessage = conn.getResponseMessage();
                Log.i("uploadImage", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);
                //getting json result:
                Log.i(TAG, "uploadImage: ");
                StringBuilder result = new StringBuilder();
                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                Log.i(TAG, "uploadImage: result " + result);
                if (serverResponseCode == 200) {
                    Log.i(TAG, "run: File Upload Complete!");
//                    Toast.makeText(myPreApp.this, "File Upload Complete", Toast.LENGTH_SHORT).show();
                    return 1;
                }
                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();
            } catch (MalformedURLException ex) {
//                dialog.dismiss();
                ex.printStackTrace();
                Log.i(TAG, "run: MalformedURLException Exception : check script url.");
//                Toast.makeText(MyApplication.this, "MalformedURLException Exception : check script url.", Toast.LENGTH_SHORT).show();
                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
                return 0;
            } catch (Exception e) {
//                dialog.dismiss();
                e.printStackTrace();
                Log.i(TAG, "Got Exception : see logcat ");
//                Toast.makeText(MyApplication.this, "Got Exception : see logcat ", Toast.LENGTH_SHORT).show();
                return 0;
                //Log.e("Upload file to server Exception", "Exception : "      + e.getMessage(), e);
            }
//            dialog.dismiss();
        } // End else block
        return 0;
    }



    public Bitmap getBitmapFromURL(String URL_Path){
        Log.i(TAG, "getBitmapFromURL: ");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Bitmap bitmap=null;
        try{
            URL url = new URL(URL_Path);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            return bitmap;
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;


    }


}
