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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/8/2016.
 */


public class monthly_subscription extends AppCompatActivity {
    ProgressDialog dialog = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


        setContentView(R.layout.monthly_subscription);

        TextView statement01 = (TextView) findViewById(R.id.statement01);
        TextView statement02 = (TextView) findViewById(R.id.statement02);
        TextView statement03 = (TextView) findViewById(R.id.statement03);
        TextView statement04 = (TextView) findViewById(R.id.statement04);
        statement01.setText(getResources().getStringArray(R.array.monthly_subcription)[0]);
        statement02.setText(getResources().getStringArray(R.array.monthly_subcription)[1]);
        statement03.setText(getResources().getStringArray(R.array.monthly_subcription)[2]);
        statement04.setText(getResources().getStringArray(R.array.monthly_subcription)[3]);


        final TextView btn_go_back = (TextView) findViewById(R.id.txt_go_back);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

//                    btn_go_back.setBackgroundResource(R.color.yellow);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        final Button btn_subscribe = (Button) findViewById(R.id.btn_subscribe);

        btn_subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

//                    btn_subscribe.setBackgroundResource(R.color.yellow);
//                    Intent toNextActivity = new Intent(monthly_subscription.this, Gallery_Activity.class);
//                    startActivity(toNextActivity);
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });
//
//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//
//// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.category_list, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        spinner.setAdapter(adapter);


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
