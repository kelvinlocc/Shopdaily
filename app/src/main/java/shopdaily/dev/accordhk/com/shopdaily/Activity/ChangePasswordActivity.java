package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response_Data;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.API;
import shopdaily.dev.accordhk.com.shopdaily.Uility.AsteriskPassword;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;

/**
 * Created by KelvinLo on 6/8/2016.
 */


public class ChangePasswordActivity extends AppCompatActivity {
    ProgressDialog dialog = null;
    MyPreApp myPreApp;
    static String TAG = "ChangePasswordActivity";
    API myApi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.change_password);
        myPreApp = new MyPreApp();
        myApi = new API(this);

        final Login_Response_Data login_response_data = myPreApp.getLoginResponse().data;

        final EditText current_password = (EditText) findViewById(R.id.input_current_password);
        current_password.setTransformationMethod(new AsteriskPassword());
        final EditText new_password = (EditText) findViewById(R.id.input_n_password);
        new_password.setTransformationMethod(new AsteriskPassword());
        final EditText confirm_password = (EditText) findViewById(R.id.input_c_password);
        confirm_password.setTransformationMethod(new AsteriskPassword());


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

        TextView Save = (TextView) findViewById(R.id.Save);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!login_response_data.member_password.equals(current_password.getText().toString().trim())) {
                    Log.i(TAG, "onClick:member_password 2 "+login_response_data.member_password);
                    Log.i(TAG, "onClick: current Password "+current_password.getText().toString());
                    Toast.makeText(ChangePasswordActivity.this, "Current password is not correct!", Toast.LENGTH_SHORT).show();
                } else if (current_password.length() == 0 || new_password.length() == 0 || confirm_password.length() == 0) {
                    Toast.makeText(ChangePasswordActivity.this, "Password is empty, please enter all the information", Toast.LENGTH_SHORT).show();
                } else if (new_password.getText().toString().trim().equals(confirm_password.getText().toString().trim())  ) {
                    try {
                        myApi.changePassword(login_response_data.member_session,confirm_password.getText().toString().trim(), new API.onAjaxFinishedListener() {
                            @Override
                            public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
                                Log.i(TAG, "onFinished: json: "+json);
                                JSONObject JsonObject = new JSONObject(json);
                                String return_status = JsonObject.get("return_status").toString();
                                if (Integer.parseInt(return_status)==1){
                                    finish();

                                }

                                String msn = JsonObject.get("display_message").toString();
                                Toast.makeText(ChangePasswordActivity.this, " "+msn, Toast.LENGTH_SHORT).show();
                            }
                        });

                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


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
