package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import shopdaily.dev.accordhk.com.shopdaily.Adapter.Gallery_adapter;
import shopdaily.dev.accordhk.com.shopdaily.DataModel.Gallery_DataModel;
import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/8/2016.
 */


public class Gallery_Activity extends AppCompatActivity {
//    int counter =0;

    Gallery_DataModel model;
    ArrayList<Gallery_DataModel> mData;

    ProgressDialog dialog = null;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


        setContentView(R.layout.gallery);

        GridView gridview = (GridView) findViewById(R.id.gridview);

        gridview.setAdapter(new Gallery_adapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(Gallery_Activity.this, "" + position,Toast.LENGTH_SHORT).show();
            }
        });


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

        final TextView upload_action = (TextView) findViewById(R.id.txt_upload);
        upload_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(Gallery_Activity.this, "uploading...",Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        final TextView view_number_upload = (TextView) findViewById(R.id.txt_number_upload);
//        model =null;
//        model.setCounter(5);
//        Log.i("check_",Integer.toString(model.getCounter()) );

//        view_number_upload.setText(Integer.toString(model.getCounter()));



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
