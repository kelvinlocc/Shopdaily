package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response_Data;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.API;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;

/**
 * Created by KelvinLo on 6/8/2016.
 */


public class profile_shop_owner_edit extends AppCompatActivity {
    ProgressDialog dialog = null;
    public static final int PICK_IMAGE_REQUEST = 1;
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    static String TAG = "profile_shop_owner_edit";
    API myApi;
    String imgPath, fileName;
    Bitmap bitmap;
    String encodedString;
    MyPreApp myPreApp;
    String upLoadServerUri;
    Login_Response_Data login_response_data;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        myApi = new API(this);
        myPreApp = new MyPreApp();
        setContentView(R.layout.fragment_profile_shop_owner_edit);
        upLoadServerUri ="http://shopdailydev.accordhkcloudapi.com/api/member_profile_image_upload";
        login_response_data = myPreApp.getLoginResponse().data;

        Button upload_profile_pic = (Button) findViewById(R.id.upload_profile);
        upload_profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent selIntent = new Intent();
                    // Show only images, no videos or anything else
                    selIntent.setType("image/*");
                    selIntent.setAction(Intent.ACTION_GET_CONTENT);
                    // Always show the chooser (if there are multiple options available)
                    startActivityForResult(Intent.createChooser(selIntent, "Select Picture"), PICK_IMAGE_REQUEST);
                } else
                    Toast.makeText(profile_shop_owner_edit.this, "require permission ", Toast.LENGTH_SHORT).show();
            }
        });


        TextView btn_go_back = (TextView) findViewById(R.id.txt_go_back);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        final TextView change_password = (TextView) findViewById(R.id.change_password);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    change_password.setBackgroundResource(R.color.yellow);

                    Intent toMapsActivity = new Intent(profile_shop_owner_edit.this, ChangePasswordActivity.class);
                    startActivity(toMapsActivity);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        TextView Save = (TextView) findViewById(R.id.Save);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });


    }


    String uploadFilePath;

    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "requestCode: " + requestCode + "==resultCode: " + resultCode);

//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK && data != null) {
//            Log.d(LOGGER_TAG, "captured!!!");
//
//            Bundle extras = data.getExtras();
//            Map<String, Object> bitmapResult = mUtils.processBitmap(getActivity(), (Bitmap) extras.get("data"), glMaxTextureSize);
//            String error = mUtils.validateUploadImage(bitmapResult);
//
//            if(error.isEmpty())
////                uploadToServer((Bitmap) bitmapResult.get(Constants.PHOTO_BITMAP));
//                uploadToServer(bitmapResult.get(Constants.PHOTO_PATH).toString());
//            else
//                mUtils.getErrorDialog(error).show();
//            // =======================3  END  =======================
//        }

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Log.d(TAG, "selected!!!");
            Uri targetUri = data.getData();

            uploadFilePath = getRealPathFromURI(targetUri);
            Log.i(TAG, "onActivityResult: uploadFilePath " + uploadFilePath);

            Log.d(TAG, "Selected targetUri.getPath() " + targetUri.getPath());
            Log.i(TAG, "onActivityResult: targetUri " + targetUri);
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            // Get the cursor
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            // Move to first row
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            imgPath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imgView = (ImageView) findViewById(R.id.icon);
            // Set the Image in ImageView
            imgView.setImageBitmap(BitmapFactory
                    .decodeFile(imgPath));
            // Get the Image's file name
            String fileNameSegments[] = imgPath.split("/");
            fileName = fileNameSegments[fileNameSegments.length - 1];
            // Put file name in Async Http Post Param which will used in Php web app
            Log.i(TAG, "onActivityResult: fileName " + fileName);


//            try {
//            myApi.
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            if (uploadFilePath != "") {
                uploadFile(uploadFilePath);
            }

        }
    }

    String uploadFileName = "uploadFileName";
    String username = "username";
    String currentTime = "currentTime";
    int serverResponseCode = 0;

    public int uploadFile(String sourceFileUri) {

        String fileName = sourceFileUri;

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

//            dialog.dismiss();

            Log.e("uploadFile", "Source File not exist :"
                    + uploadFilePath + "" + uploadFileName);

            this.runOnUiThread(new Runnable() {
                public void run() {
                    Log.i(TAG, "run: \"Source File not exist :\"\n" +
                            " + uploadFilePath + \"\" + uploadFileName");
                }
            });

            return 0;

        } else {
            try {
                if (android.os.Build.VERSION.SDK_INT > 9)
                {
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
                
                conn.setRequestProperty("api_key", "654321");
                conn.setRequestProperty("lang_id", "1");
                conn.setRequestProperty("member_session", login_response_data.member_session);


                dos = new DataOutputStream(conn.getOutputStream());
                Log.i(TAG, "uploadFile: update");
                //for other params:
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=api_key" + lineEnd); // name=mobile_no so you have to get PHP side using mobile_no
                dos.writeBytes(lineEnd);
                dos.writeBytes("654321"); // mobile_no is String variable
                dos.writeBytes(lineEnd);
                

                
                
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=lang_id" + lineEnd); // name=mobile_no so you have to get PHP side using mobile_no
                dos.writeBytes(lineEnd);
                dos.writeBytes("1"); // mobile_no is String variable
                dos.writeBytes(lineEnd);

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=member_session"+lineEnd); // name=mobile_no so you have to get PHP side using mobile_no
                dos.writeBytes(lineEnd);
                dos.writeBytes(login_response_data.member_session.toString().trim()); // mobile_no is String variable
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
                Log.i(TAG, "uploadFile: ");
                String serverResponseMessage = conn.getResponseMessage();


                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                //getting json result:
                Log.i(TAG, "uploadFile: ");
                Log.i(TAG, "uploadFile: session "+login_response_data.member_session);

                StringBuilder result = new StringBuilder();

                InputStream in = new BufferedInputStream(conn.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                Log.i(TAG, "uploadFile: result "+result);


                if (serverResponseCode == 200) {

                    this.runOnUiThread(new Runnable() {
                        public void run() {

                            //String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
                            //      + " http://www.androidexample.com/media/uploads/"
                            //    + uploadFileName;
                            //    + uploadFileName;

                            Log.i(TAG, "run: File Upload Complete!");
                            Toast.makeText(profile_shop_owner_edit.this, "File Upload Complete", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {

//                dialog.dismiss();
                ex.printStackTrace();

                this.runOnUiThread(new Runnable() {
                    public void run() {
                        Log.i(TAG, "run: MalformedURLException Exception : check script url.");
                        Toast.makeText(profile_shop_owner_edit.this, "MalformedURLException Exception : check script url.", Toast.LENGTH_SHORT).show();
                    }
                });

                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {

//                dialog.dismiss();
                e.printStackTrace();

                this.runOnUiThread(new Runnable() {
                    public void run() {
                        Log.i(TAG, "Got Exception : see logcat ");
                        Toast.makeText(profile_shop_owner_edit.this, "Got Exception : see logcat ", Toast.LENGTH_SHORT).show();
                        //

                    }
                });
                //Log.e("Upload file to server Exception", "Exception : "      + e.getMessage(), e);
            }
//            dialog.dismiss();
            return serverResponseCode;
        } // End else block
    }


    public String getRealPathFromURI(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = this.managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    //////////////////////////////////failed
    // When Upload button is clicked
    public void uploadImage() {
        Log.i(TAG, "uploadImage: ");
        // When Image is selected from Gallery
        if (imgPath != null && !imgPath.isEmpty()) {
            Toast.makeText(profile_shop_owner_edit.this, "Converting Image to Binary Data...", Toast.LENGTH_SHORT).show();
            // Convert image to String using Base64

            // When Image is not selected from Gallery
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "You must select image from gallery before you try to upload",
                    Toast.LENGTH_LONG).show();
        }
    }

    // encode image to string failed
//    // AsyncTask - To convert Image to String
//    public void encodeImagetoString() {
//        new AsyncTask<Void, Void, String>() {
//
//
//            protected void onPreExecute() {
//
//            };
//
//            @Override
//            protected String doInBackground(Void... params) {
//                Log.i(TAG, "doInBackground: ");
//                BitmapFactory.Options options = null;
//                options = new BitmapFactory.Options();
//                options.inSampleSize = 3;
//                bitmap = BitmapFactory.decodeFile(imgPath,
//                        options);
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                // Must compress the Image to reduce image size to make upload easy
//                bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
//                byte[] byte_arr = stream.toByteArray();
//                // Encode Image to String
//                encodedString = Base64.encodeToString(byte_arr, 0);
//                Log.i(TAG, "doInBackground: encodedString "+encodedString);
//                return "";
//            }
//
//            @Override
//            protected void onPostExecute(String msg) {
//                Log.i(TAG, "onPostExecute: ");
//                Toast.makeText(profile_shop_owner_edit.this, "calling upload", Toast.LENGTH_SHORT).show();
//                // Put converted Image string into Async Http Post param
////                params.put("image", encodedString);
//                // Trigger Image upload
//                triggerImageUpload();
//            }
//        }.execute(null, null, null);
//    }
//
//    public void triggerImageUpload() {
//        Log.i(TAG, "triggerImageUpload: ");
//        makeAjaxCall();
//    }
//    // Make Http call to upload Image to Php server
//    public void makeAjaxCall() {
//        Log.i(TAG, "makeAjaxCall: ");
//        Log.i(TAG, "Invoking Php");
//        Login_Response_Data login_response_data = myPreApp.getLoginResponse().data;
//        myApi.member_profile_image_upload(login_response_data.member_session,encodedString, new API.onAjaxFinishedListener() {
//            @Override
//            public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
//                Log.i(TAG, "onFinished: json: "+json);
//            }
//        });
//
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
