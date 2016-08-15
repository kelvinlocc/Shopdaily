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

import shopdaily.dev.accordhk.com.shopdaily.R;
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
    private RESTService service;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sign_up);
        service = new RESTService(this);

        final EditText input_email = (EditText) findViewById(R.id.input_email);

        final EditText input_password = (EditText) findViewById(R.id.input_password);
        input_password.setTransformationMethod(new AsteriskPassword());

        final EditText input_confirm_password = (EditText) findViewById(R.id.input_confirm_password);
        input_confirm_password.setTransformationMethod(new AsteriskPassword());
        Log.i(TAG, "SignUp: ");
        mButton_signUp = (Button) findViewById(R.id.btn_sign_up);
        mButton_signUp.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  String email = input_email.getText().toString().trim();
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
                                                          service.register(email, password, new RESTService.onAjaxFinishedListener() {
                                                              @Override
                                                              public void onFinished(String url, String json, AjaxStatus status) {
                                                                  Log.i("check_", "onFinished json: " + json);
//                                                              Toast.makeText(RegisterActivity.this, json, Toast.LENGTH_LONG).show();

                                                                  if (json.matches("Successfully Registered")) {
                                                                      Log.i("check_", "Successfully Registered");

                                                                      Intent toLogin = new Intent(SignUp.this, SignUpSuccess.class);
                                                                      startActivity(toLogin);
                                                                      finish();
                                                                  } else {
                                                                      Toast.makeText(SignUp.this, json, Toast.LENGTH_LONG).show();
                                                                  }
                                                                  dialog.dismiss();
                                                              }
                                                          });
//                                                      Intent toSignUpSuccess = new Intent(SignUp.this, SignUpSuccess.class);
//                                                      startActivity(toSignUpSuccess);
//                                                      finish();

                                                      } catch (Exception e) {
                                                          e.printStackTrace();
                                                      }
                                                  }
                                              }
                                          }
        );


    }

    @Override
    public void onBackPressed() {
        Intent NextActivity = new Intent(SignUp.this, LoginPage.class);
        startActivity(NextActivity);
        return;
    }


}
