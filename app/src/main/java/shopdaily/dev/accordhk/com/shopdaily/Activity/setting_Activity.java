package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/8/2016.
 */


public class setting_Activity extends AppCompatActivity {
    ProgressDialog dialog = null;
    static String TAG = "Setting_Activity ";
    Locale myLocale;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


        setContentView(R.layout.setting);


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


        final TextView contact_us_action = (TextView) findViewById(R.id.txt_contact_us);
        contact_us_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

//                    contact_us_action.setBackgroundResource(R.color.yellow);
                    Intent toNextActivity = new Intent(setting_Activity.this, Contact_Us_Activity.class);
                    startActivity(toNextActivity);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        final TextView Q_and_A = (TextView) findViewById(R.id.Q_and_A);
        Q_and_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent toNextActivity = new Intent(setting_Activity.this, QA_list_Activity.class);
                    startActivity(toNextActivity);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });


        final TextView logout_action = (TextView) findViewById(R.id.txt_logout);
        logout_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    btn_go_back.setBackgroundResource(R.color.yellow);
                    Intent toNextActivity = new Intent(setting_Activity.this, LoginPage.class);
                    startActivity(toNextActivity);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        Spinner spinner_language = (Spinner) findViewById(R.id.spinner_language);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_list, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_language.setAdapter(adapter);
        spinner_language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 1) {

                    Toast.makeText(parent.getContext(),
                            "You have selected English", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("en");
                } else if (pos == 2) {

                    Toast.makeText(parent.getContext(),
                            "You have selected Chinese", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("zh");
                } else if (pos == 3) {

                    Toast.makeText(parent.getContext(),
                            "You have selected English", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("en");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner Spinner_pushOrNot = (Spinner) findViewById(R.id.spinner_pushOrNot);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_2 = ArrayAdapter.createFromResource(this,
                R.array.Push_option, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        Spinner_pushOrNot.setAdapter(adapter_2);


    }

    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Splash.class);
        Log.i(TAG, "refresh to splash.class: " + Splash.class);
        startActivity(refresh);
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
