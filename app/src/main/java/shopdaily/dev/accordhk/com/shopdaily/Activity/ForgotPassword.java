package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.API;

/**
 * Created by KelvinLo on 6/10/2016.
 */
public class ForgotPassword extends Activity{

    ProgressDialog dialog = null;
    API myApi;
    static String TAG = "ForgotPassword ";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.forgot_password);
        myApi = new API(this);

        final TextView Input_email = (TextView) findViewById(R.id.input_email);


        Button btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  try {
                                                      myApi.forgetPassword(Input_email.getText().toString().trim(), new API.onAjaxFinishedListener() {
                                                          @Override
                                                          public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
                                                              Log.i(TAG, "onFinished: json: "+json);
                                                              JSONObject JsonObject = new JSONObject(json);
                                                              String return_status = JsonObject.get("return_status").toString();
                                                              if (Integer.parseInt(return_status)==1){
                                                                  Intent toSignUpSuccess = new Intent(ForgotPassword.this, LoginPage.class);
                                                                  startActivity(toSignUpSuccess);
                                                                  finish();

                                                              }

                                                              String msn = JsonObject.get("display_message").toString();
                                                              Toast.makeText(ForgotPassword.this, " "+msn, Toast.LENGTH_SHORT).show();
                                                          }
                                                      });
                                                      dialog = ProgressDialog.show(ForgotPassword.this, "", "Email sent!", true);



                                                  } catch (Exception e) {
                                                      e.printStackTrace();
                                                  }
                                              }
                                          }
        );
    }
}
