package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/8/2016.
 */


public class profile_shop_edit extends AppCompatActivity {
    ProgressDialog dialog = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


        setContentView(R.layout.fragment_profile_shop_edit);


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

        final TextView change_password = (TextView)findViewById(R.id.change_password);
        change_password.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick (View v){
                try{
//                    change_password.setBackgroundResource(R.color.yellow);

                    Intent toMapsActivity = new Intent(profile_shop_edit.this, ChangePasswordActivity.class);
                    startActivity(toMapsActivity);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });

        TextView Save = (TextView) findViewById(R.id.btn_save);
        Save.setOnClickListener(new View.OnClickListener() {
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
