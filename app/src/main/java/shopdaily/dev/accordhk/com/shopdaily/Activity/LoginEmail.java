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
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Shop_Response;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.API;
import shopdaily.dev.accordhk.com.shopdaily.Uility.AsteriskPassword;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;


/**
 * Created by KelvinLo on 6/10/2016.
 */
public class LoginEmail extends Activity {

    private static String TAG = "LoginEmail";
    ProgressDialog dialog = null;

    private API api;
    MyPreApp myPreApp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_email);
        api = new API(this);
        myPreApp = new MyPreApp();

        TextView forget_password = (TextView) findViewById(R.id.txtPassword);

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog = ProgressDialog.show(LoginEmail.this, "", "redirecting...");
                    Intent toForgotPassword = new Intent(LoginEmail.this, ForgotPassword.class);
                    startActivity(toForgotPassword);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        final EditText input_email = (EditText) findViewById(R.id.input_email);


        final EditText input_password = (EditText) findViewById(R.id.input_password);
        input_password.setTransformationMethod(new AsteriskPassword());

        Button loginButton = (Button) findViewById(R.id.btn_login_email);
        input_email.setText("user@gmail.com");
        input_password.setText("123123");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = input_email.getText().toString().trim();
                String password = input_password.getText().toString().trim();
                Log.i(TAG, "email: " + email + " password: " + password);
                input_email.setText("");
                input_password.setText("");

                try {
                    api.login(email, password, new API.onAjaxFinishedListener() {
                        @Override
                        public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
                            Log.i("check_", "onFinished api.login json: " + json);
                            Gson gson = new Gson();
                            Login_Response login_response = gson.fromJson(json,Login_Response.class);

                            JSONObject jsonObject = new JSONObject(json);
                            jsonObject = jsonObject.getJSONObject("data");
                            jsonObject = jsonObject.getJSONObject("shop");
                            if (jsonObject!=null){
                                Shop_Response shop_response = gson.fromJson(jsonObject.toString() ,Shop_Response.class);
                                Log.i(TAG, "onFinished: shop_br_number "+shop_response.shop_br_number);
                                myPreApp.setShopResponse(shop_response);
                            }

                            //save data from api
                            myPreApp.setLoginResponse(login_response);
//                            Log.i(TAG, "onFinished:... "+myPreApp.getLoginResponse().data.shop.shop_id);

                            if (login_response.status == 1) {
                                Toast.makeText(LoginEmail.this, "Login Successfully! ", Toast.LENGTH_LONG).show();

                                Intent NextActivity = new Intent(LoginEmail.this, MainActivity.class);
                                startActivity(NextActivity);
                                finish();
                            } else {
//                                Log.i(TAG, "onFinished: "+"");
                                Toast.makeText(LoginEmail.this, "your email or password is not correct", Toast.LENGTH_LONG).show();
                            }
                        }

                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent NextActivity = new Intent(LoginEmail.this, LoginPage.class);
        startActivity(NextActivity);
        return;
    }

}
