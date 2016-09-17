package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Registration_Response_Data;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.API;
import shopdaily.dev.accordhk.com.shopdaily.Uility.AsteriskPassword;
import shopdaily.dev.accordhk.com.shopdaily.Uility.RESTService;

/**
 * Created by KelvinLo on 6/8/2016.
 */
public class SignUp extends Activity {
    public static String TAG = "SignUp";
    private ImageButton mLoginButton_facebook;
    private ImageButton mLoginButton_email;
    private Button mButton_signUp;
    ProgressDialog dialog = null;

    API api;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sign_up);
        api = new API(this);

        final EditText input_email = (EditText) findViewById(R.id.input_email);

        final EditText input_password = (EditText) findViewById(R.id.input_password);
        input_password.setTransformationMethod(new AsteriskPassword());

        final EditText input_confirm_password = (EditText) findViewById(R.id.input_confirm_password);
        input_confirm_password.setTransformationMethod(new AsteriskPassword());
        Log.i(TAG, "SignUp: ");
        //for testing:
        input_password.setText("123123");
        input_confirm_password.setText("123123");

        mButton_signUp = (Button) findViewById(R.id.btn_sign_up);
        mButton_signUp.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  final String email = input_email.getText().toString().trim();
                                                  String password = input_password.getText().toString().trim();

                                                  Log.i("check_", "check email:" + email + " password: " + password);
                                                  if (email.length() == 0) {
                                                      Toast.makeText(SignUp.this, "email is empty", Toast.LENGTH_LONG).show();
                                                  } else if (password.length() == 0) {
                                                      Toast.makeText(SignUp.this, "password is empty", Toast.LENGTH_LONG).show();
                                                  } else if (!password.matches(input_confirm_password.getText().toString().trim())) {
                                                      Toast.makeText(SignUp.this, "password is not match", Toast.LENGTH_LONG).show();
                                                  } else {
                                                      try {
                                                          dialog = ProgressDialog.show(SignUp.this, "", "checking...", true);
                                                          String facebook_id = "0";
                                                          api.member_registration(email, password, facebook_id, new API.onAjaxFinishedListener() {
                                                              @Override
                                                              public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
                                                                  Log.i(TAG, "onFinished: Json: " + json);

                                                                  Gson gson = new Gson();
                                                                  JSONObject jsonObject = new JSONObject(json);
                                                                  Log.i(TAG, "onFinished: jsonObject " + jsonObject);
                                                                  Log.i(TAG, "onFinished: return status " + jsonObject.get("return_status").toString());
                                                                  if (Integer.parseInt(jsonObject.get("return_status").toString()) == 1) {

                                                                      jsonObject = jsonObject.getJSONObject("data");

                                                                      Log.i(TAG, "onFinished: jsonObject " + jsonObject);

                                                                      Registration_Response_Data data = gson.fromJson(jsonObject.toString(), Registration_Response_Data.class);
                                                                      Log.i(TAG, "onFinished: " + data.member_email + "," + data.member_password);
                                                                      Toast.makeText(SignUp.this, "registration success!", Toast.LENGTH_SHORT).show();
                                                                      Default_shop_registration(data);

                                                                      dialog.dismiss();
                                                                      Intent NextActivity = new Intent(SignUp.this, LoginPage.class);
                                                                      startActivity(NextActivity);
                                                                      finish();
                                                                  } else {
                                                                      Toast.makeText(SignUp.this, "Registration error1", Toast.LENGTH_SHORT).show();

                                                                      dialog.dismiss();
                                                                  }

                                                              }
                                                          });
                                                      } catch (Exception e) {
                                                          e.printStackTrace();
                                                      }
                                                  }
                                              }
                                          }
        );


    }

    protected void Default_shop_registration(Registration_Response_Data data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());
        String shop_name_en = "shop_re en";
        String shop_name_tc = "shop_re tc";
        String shop_name_sc = "shop_re sc";
        String shop_contact_person = "owner 1";
        String shop_br_number = "brnumber"+currentDateandTime;
        String shop_address_en = "address en";
        String shop_address_tc = "address tc";
        String shop_address_sc = "address sc";
        String shop_location_x = "12.3457";
        String shop_location_y = "98.77";
        String shop_operation_hour_en = "9am to 5pm";
        String shop_operation_hour_tc = "9am to 5pm";
        String shop_operation_hour_sc = "9am to 5pm";
        String shop_district = "1";
        String shop_category_list = "1,2";
        String shop_hash_list = "#shop_re";
        api.shop_registration(data.member_session,shop_name_en,shop_name_tc,shop_name_sc,shop_contact_person,shop_br_number,shop_address_en,shop_address_tc,shop_address_sc,shop_location_x,shop_location_y,shop_operation_hour_en,shop_operation_hour_tc,shop_operation_hour_sc,shop_district,shop_category_list,shop_hash_list, new API.onAjaxFinishedListener() {
            @Override
            public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
                Log.i(TAG, "onFinished: api.shop_registration json "+json);
            }
        });
        Log.i(TAG, "Default_shop_registration: shop_br_number "+shop_br_number);

    }

    @Override
    public void onBackPressed() {
        Intent NextActivity = new Intent(SignUp.this, LoginPage.class);
        startActivity(NextActivity);
        return;
    }


}
