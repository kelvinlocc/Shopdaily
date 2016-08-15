package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/10/2016.
 */
public class ForgotPassword extends Activity{

    ProgressDialog dialog = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.forgot_password);
        Button btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  try {
                                                      dialog = ProgressDialog.show(ForgotPassword.this, "", "Email sent!", true);
                                                      Intent toSignUpSuccess = new Intent(ForgotPassword.this, LoginPage.class);
                                                      startActivity(toSignUpSuccess);
                                                      finish();

                                                  } catch (Exception e) {
                                                      e.printStackTrace();
                                                  }
                                              }
                                          }
        );




    }
}
