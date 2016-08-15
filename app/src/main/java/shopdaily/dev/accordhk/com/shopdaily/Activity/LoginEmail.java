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
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;

import java.security.PublicKey;

import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.AsteriskPassword;
import shopdaily.dev.accordhk.com.shopdaily.Uility.RESTService;


/**
 * Created by KelvinLo on 6/10/2016.
 */
public class LoginEmail extends Activity {

    private static String TAG = "LoginEmail";
    private ImageButton mLoginButton_facebook;
    private ImageButton mLoginButton_email;
    private Button mButton_login;
    private TextView mPasswordtxt;
    ProgressDialog dialog = null;
    private RESTService service;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_email);
        service = new RESTService(this);


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
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = input_email.getText().toString().trim();
                String password = input_password.getText().toString().trim();
                Log.i(TAG, "email: " + email + " password: " + password);
                input_email.setText("");
                input_password.setText("");

                try {
                    service.login(email, password, new RESTService.onAjaxFinishedListener() {
                        @Override
                        public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
                            Log.i("check_", "onFinished json: " + json);
//                            if (json.matches("Login Successfully")) {
                            if (true) {
                                Toast.makeText(LoginEmail.this, "Login Successfully! ", Toast.LENGTH_LONG).show();

                                Intent NextActivity = new Intent(LoginEmail.this, MainActivity.class);
                                startActivity(NextActivity);
                                finish();
                            } else {
                                Toast.makeText(LoginEmail.this, json, Toast.LENGTH_LONG).show();
                            }
                        }

                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


//        mPasswordtxt = (TextView) findViewById(R.id.txtPassword);
//        mPasswordtxt.setEnabled(true);
//        mPasswordtxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Intent toRegister = new Intent(LoginEmail.this, RegisterActivity.class);
////                startActivity(toRegister);
////                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
//                //finish();
//            }
//        });


//        mLoginButton_email.setEnabled(true);
//        mLoginButton_email.setClickable(true);


    }


    @Override
    public void onBackPressed() {
        Intent NextActivity = new Intent(LoginEmail.this, LoginPage.class);
        startActivity(NextActivity);
        return;
    }

}
