package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import shopdaily.dev.accordhk.com.shopdaily.Fragment.fragment_shop_status;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.shop_status_pieChart.fragment_age;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.shop_status_pieChart.fragment_gender;
import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/8/2016.
 */


public class shopStatus_Activity extends AppCompatActivity {
    ProgressDialog dialog = null;
    int shop_status_month;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


        setContentView(R.layout.shop_status);

        shop_status_month = 4;
        // line chart:
        Bundle bundle = new Bundle();
        bundle.putInt("month", shop_status_month);

        fragment_shop_status new_fragment = new fragment_shop_status();
        new_fragment.setArguments(bundle);

        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new_fragment).commit();


        ImageButton btn_previous = (ImageButton) findViewById(R.id.btn_previous);
        btn_previous.setColorFilter(0xffd3d3d3, PorterDuff.Mode.MULTIPLY);
        ImageButton btn_next = (ImageButton) findViewById(R.id.btn_next);
        btn_next.setColorFilter(0xffd3d3d3, PorterDuff.Mode.MULTIPLY);

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Bundle bundle1 = new Bundle();
                shop_status_month--;
                bundle1.putInt("month", shop_status_month);

                fragment_shop_status new_fragment = new fragment_shop_status();
                new_fragment.setArguments(bundle1);
                transaction.replace(R.id.fragment_container, new_fragment).commit();

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Bundle bundle1 = new Bundle();
                shop_status_month++;
                bundle1.putInt("month", shop_status_month);

                fragment_shop_status new_fragment = new fragment_shop_status();
                new_fragment.setArguments(bundle1);
                transaction.replace(R.id.fragment_container, new_fragment).commit();

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
//        btn_previous


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<LineDataSet> getDataSet() {
        ArrayList<LineDataSet> dataSets = null;

        ArrayList<Entry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList<Entry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);

        LineDataSet barDataSet1 = new LineDataSet(valueSet1, "Brand 1");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        LineDataSet barDataSet2 = new LineDataSet(valueSet2, "Brand 2");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        return xAxis;
    }

}
