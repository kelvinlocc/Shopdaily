package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response_Data;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.API;
import shopdaily.dev.accordhk.com.shopdaily.Uility.CustomPhotoGalleryActivity;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;

import static shopdaily.dev.accordhk.com.shopdaily.Activity.profile_shop_owner_edit.REQUEST_WRITE_EXTERNAL_STORAGE;

/**
 * Created by KelvinLo on 6/8/2016.
 */


public class Post_feed_Activity extends AppCompatActivity {
    ProgressDialog dialog = null;
    static String TAG = "Post_feed_Activity";
    EditText regular_price, sale_price, description, hastTag;
    Spinner spinner;
    API myApi;
    MyPreApp myPreApp;
    public static final int PICK_IMAGE_REQUEST = 12;
    ImageView photo1,photo2;
    private final int PICK_IMAGE_MULTIPLE =1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        myApi = new API(this);
        myPreApp = new MyPreApp();
        setContentView(R.layout.profile_post_feed);
        regular_price = (EditText) findViewById(R.id.txt_regular_price);
        sale_price = (EditText) findViewById(R.id.txt_sale_price);
        description = (EditText) findViewById(R.id.txt_description);
        hastTag = (EditText) findViewById(R.id.txt_hashTag);

        // test data:
        photo1 = (ImageView) findViewById(R.id.photo01);
        photo2 = (ImageView) findViewById(R.id.photo02);

        final TextView btn_go_back = (TextView) findViewById(R.id.txt_go_back);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    btn_go_back.setBackgroundResource(R.color.yellow);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final Button button_post = (Button) findViewById(R.id.btn_post);

        button_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    create_feed();

//                    Intent toNextActivity = new Intent(Post_feed_Activity.this, Gallery_Activity.class);
//                    startActivity(toNextActivity);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                    create_feed();
                    if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Log.i(TAG, "onClick:button_post ");
                        Intent intent = new Intent(Post_feed_Activity.this,CustomPhotoGalleryActivity.class);
                        startActivityForResult(intent,PICK_IMAGE_MULTIPLE);

                    } else
                        ActivityCompat.requestPermissions(getParent(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_WRITE_EXTERNAL_STORAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    ArrayList<String> imagesPathList;
    Bitmap yourbitmap;
    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "requestCode: " + requestCode + "==resultCode: " + resultCode);

        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == Activity.RESULT_OK && data != null) {
            Log.i(TAG, "onActivityResult: get data!");
            imagesPathList = new ArrayList<String>();
            String[] imagesPath = data.getStringExtra("data").split("\\|");

            for (int i=0;i<imagesPath.length;i++){
                Log.i(TAG, "onActivityResult: imagesPath@i "+i+","+imagesPath[i]);
                imagesPathList.add(imagesPath[i]);

                String api_key = "654321";
                String lang_id = "1";
                String sequence_id= Integer.toString(i);
                Login_Response_Data Login_data = myPreApp.getLoginResponse().data;
                Log.i(TAG, "onFinished: imagesPathList.get(0) "+imagesPathList.get(0));
                int result = myPreApp.uploadFeedImage(api_key,lang_id,Login_data.member_session,Login_data.shop_id,shop_feed_id,sequence_id,imagesPath[i]);
                if (result==1){
                    Log.i(TAG, "onActivityResult: upload ss");
                }
                else{
                    Log.i(TAG, "onActivityResult: error");
                }


                yourbitmap = BitmapFactory.decodeFile(imagesPath[i]);

                if (i==1){
                    photo2.setImageBitmap(yourbitmap);
                }
                else {
                    photo1.setImageBitmap(yourbitmap);
                }
            }


        }
    }


    public static final String PHOTO_PATH = "photoPath";
    public static final String PHOTO_BITMAP = "photoBitmap";
    private List<String> bitmaps;

    public Map<String, Object> processBitmap(Activity activity, Bitmap bitmap) {
        Map<String, Object> result = new HashMap<String, Object>();

        Log.d(TAG, "BITMAP SIZE: " + String.valueOf(bitmap.getWidth()) + "x" + String.valueOf(bitmap.getHeight()));

        Bitmap bMapScaled = bitmap;

//        if(scaleSize > -1) {
//            if(bitmap.getHeight() > scaleSize || bitmap.getWidth() > scaleSize) {
//                bMapScaled = getResizedBitmap(bitmap, scaleSize);
//            }
//        }

        String path = getImagePathFromURI(activity, Uri.parse(MediaStore.Images.Media.insertImage(activity.getContentResolver(), bMapScaled, "Title", null)));
        Log.d(TAG, "photofilepath: " + path);
        result.put(PHOTO_PATH, path);

        result.put(PHOTO_BITMAP, bMapScaled);
        bMapScaled.recycle();
        return result;
    }

    public String getImagePathFromURI(Activity activity, Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        Cursor cursor = activity.getContentResolver().query(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }



    String start_datetime, end_datetime, discount_rate, feed_category_id;
    String shop_feed_id;
    // create feed via api
    public void create_feed() {
        String api_key = "654321";
        String lang_id = "1";
        start_datetime = "2016-08-20 00:00:00";
        end_datetime = myPreApp.getCurrentTime();
        Log.i(TAG, "create_feed: end_datetime "+end_datetime);
        feed_category_id = spinner.getSelectedItem().toString();
        Log.i(TAG, "create_feed: feed_category_id " + feed_category_id);
        Login_Response_Data data = myPreApp.getLoginResponse().data;
        myApi.shopFeedCreate(api_key, lang_id, data.member_session, description.getText().toString().trim(), description.getText().toString().trim(), description.getText().toString().trim(), start_datetime, end_datetime, regular_price.getText().toString().trim(), sale_price.getText().toString().trim(), discount_rate, feed_category_id, hastTag.getText().toString().trim(), new API.onAjaxFinishedListener() {
            @Override
            public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
                Log.i(TAG, "onFinished: json: " + json);
                JSONObject jsonObject = new JSONObject(json);
                shop_feed_id = jsonObject.getString("shop_feed_id");

                Log.i(TAG, "onFinished: shop_feed_id "+shop_feed_id);


            }
        });
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
