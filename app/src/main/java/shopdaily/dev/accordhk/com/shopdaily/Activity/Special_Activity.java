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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.AsteriskPassword;

/**
 * Created by KelvinLo on 6/8/2016.
 */


public class Special_Activity extends AppCompatActivity {
    ProgressDialog dialog = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


        setContentView(R.layout.special);

        final LinearLayout layout_monthly_subscription = (LinearLayout) findViewById(R.id.layout_monthlySubscription);
        final LinearLayout layout_adHoc = (LinearLayout) findViewById(R.id.layout_adHoc);
        final LinearLayout layout_shopStatus = (LinearLayout) findViewById(R.id.layout_shopStatus);
        final LinearLayout layout_advertisementMonthly = (LinearLayout) findViewById(R.id.layout_advertismentMonthly);

        layout_monthly_subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NextActivity = new Intent(Special_Activity.this,monthly_subscription.class); // done
                startActivity(NextActivity);
            }
        });
        layout_adHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NextActivity = new Intent(Special_Activity.this,adHoc_Activity.class); // done
                startActivity(NextActivity);

            }
        });
        layout_shopStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NextActivity = new Intent(Special_Activity.this,shopStatus_Activity.class);
                startActivity(NextActivity);

            }
        });
        layout_advertisementMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NextActivity = new Intent(Special_Activity.this,advertisementMonthly.class); // done
                startActivity(NextActivity);

            }
        });



        final TextView btn_go_back = (TextView) findViewById(R.id.txt_go_back);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    btn_go_back.setBackgroundResource(R.color.yellow);
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
