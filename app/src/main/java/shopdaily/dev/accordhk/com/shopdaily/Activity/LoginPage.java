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
public class LoginPage extends Activity {
    private ImageButton mLoginButton_facebook;
    private ImageButton mLoginButton_email;
    private Button mButton_signUp;
    ProgressDialog dialog = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_page);



        mLoginButton_facebook = (ImageButton) findViewById(R.id.btn_login_fb);
        mLoginButton_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    dialog = ProgressDialog.show(LoginPage.this,"","connect to your facebook account...",true);
                    Intent NextActivity = new Intent(LoginPage.this, MainActivity.class);
//                    NextActivity.putExtra("x","test");

                    startActivity(NextActivity);
                    finish();

                } catch (Exception e){
                    e.printStackTrace();
                }

            }

        });


        mLoginButton_email = (ImageButton) findViewById(R.id.btn_login_email);
        mLoginButton_email.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {

                                                      try {
                                                          dialog = ProgressDialog.show(LoginPage.this, "", "LoginPage with email...", true);


                                                          Intent toLoginEmail = new Intent(LoginPage.this, LoginEmail.class);

                                                          startActivity(toLoginEmail);
                                                          finish();
                                                          dialog.dismiss();
                                                      } catch (Exception e) {
                                                          e.printStackTrace();
                                                      }
                                                  }
                                              }


        );

        mButton_signUp = (Button) findViewById(R.id.btn_sign_up);
        mButton_signUp.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {

                                                  try {
                                                      dialog = ProgressDialog.show(LoginPage.this, "", "Sign Up...", true);


                                                      Intent toSignUp = new Intent(LoginPage.this, SignUp.class);

                                                      startActivity(toSignUp);
                                                      finish();
                                                      dialog.dismiss();
                                                  } catch (Exception e) {
                                                      e.printStackTrace();
                                                  }
                                              }
                                          }


        );


    }



}
