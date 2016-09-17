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
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import shopdaily.dev.accordhk.com.shopdaily.Adapter.Fragment_Category_Adapter;
import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/8/2016.
 */


public class Shop_details extends AppCompatActivity {
    ProgressDialog dialog = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }



        setContentView(R.layout.shop_details);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new Fragment_Category_Adapter(this)); // uses the view to get the context instead of getActivity().

        TextView btn_go_back = (TextView) findViewById(R.id.txt_go_back);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                } catch (Exception e){
                    e.printStackTrace();
                }

            }

        });

        ImageButton shop_location = (ImageButton)findViewById(R.id.btn_shop_location);
        shop_location.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick (View v){
                try{
                    dialog = ProgressDialog.show(Shop_details.this,"","forwarding to shop_re location!",true);
                    Intent toMapsActivity = new Intent(Shop_details.this, MapsActivity.class);
                    startActivity(toMapsActivity);
                    finish();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });





    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }




}
