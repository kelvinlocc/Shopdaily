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
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
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

import java.io.IOException;
import java.io.InputStream;
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
    ImageView imageView;
    String uploadFilePath;

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


        imageView = (ImageView) findViewById(R.id.icon);
        Log.i(TAG, "onCreate: image url "+login_response_data.member_profile_image);
        showImageTwo();
//        imgView.setImageDrawable(LoadImageFromWebOperations(login_response_data.member_profile_image));

        TextView nick_name = (TextView) findViewById(R.id.txt_nickName);
        nick_name.setText(login_response_data.member_nick_name);

        TextView birthday = (TextView) findViewById(R.id.birthday);
        birthday.setText(login_response_data.member_birthday);

        TextView email = (TextView) findViewById(R.id.email);
        email.setText(login_response_data.member_email);

        TextView gender = (TextView) findViewById(R.id.gender);
        if (login_response_data.member_gender =="1"){
            gender.setText("Male");
        }
        else {
            gender.setText("Female");
        }



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

    public void showImageTwo() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(login_response_data.member_profile_image);
            imageView.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }



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
            if (uploadFilePath != "") {
                int result = myPreApp.uploadImage("654321","1",login_response_data.member_session,uploadFilePath);
                if(result==1){
                    Toast.makeText(profile_shop_owner_edit.this, "file is uploaded", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(profile_shop_owner_edit.this, "file upload failed", Toast.LENGTH_SHORT).show();
                }

            }

        }
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





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
