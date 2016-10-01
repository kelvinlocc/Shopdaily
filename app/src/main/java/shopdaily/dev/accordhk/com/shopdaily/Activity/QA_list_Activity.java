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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.QA_Response;
import shopdaily.dev.accordhk.com.shopdaily.Adapter.QA_List_adapter;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.API;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;

/**
 * Created by KelvinLo on 6/8/2016.
 */


public class QA_list_Activity extends AppCompatActivity {
    ProgressDialog dialog = null;
    MyPreApp myPreApp;
    ArrayList<QA_Response> qa_list;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


        setContentView(R.layout.qa_list);
        myPreApp = new MyPreApp();
        qa_list =  myPreApp.getQA_list();




        final TextView btn_go_back = (TextView) findViewById(R.id.txt_go_back);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    btn_go_back.setBackgroundResource(R.color.yellow);
                    Intent toNextActivity = new Intent(QA_list_Activity.this, setting_Activity.class);
                    startActivity(toNextActivity);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });
        String[] q = {"1","2","3","2","3","2","3"};
        String[] a = {"1","2","3","2","3","2","3"};
        QA_List_adapter adapter = new QA_List_adapter(this,qa_list);
        ListView QA_listView = (ListView) findViewById(R.id.QA_listView);
        QA_listView.setAdapter(adapter);






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
