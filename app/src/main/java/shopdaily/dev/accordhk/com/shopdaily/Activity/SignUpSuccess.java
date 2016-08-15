package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/8/2016.
 */
public class SignUpSuccess extends Activity {
    private ImageButton mLoginButton_facebook;
    private ImageButton mLoginButton_email;
    private Button mButton_ok;
    ProgressDialog dialog = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sign_up_success);
        mButton_ok = (Button) findViewById(R.id.btn_ok);
        mButton_ok.setOnClickListener(new View.OnClickListener()
          {
              @Override
              public void onClick(View v){
                  try{
                      dialog = ProgressDialog.show(SignUpSuccess.this,"","going to Login Page...",true);
                      Intent toLoginPage = new Intent(SignUpSuccess.this,LoginPage.class);
                      startActivity(toLoginPage);
                    finish();
                  }catch (Exception e){
                      e.printStackTrace();
                  }
              }
          }
        );
//        mLoginButton_facebook = (ImageButton) findViewById(R.id.btn_login_fb);
//        mLoginButton_email = (ImageButton) findViewById(R.id.btn_login_email);
//        mButton_signUp = (Button) findViewById(R.id.btn_sign_up);
//
//
//        mLoginButton_email.setEnabled(true);
//        mLoginButton_email.setClickable(true);


    }




}
