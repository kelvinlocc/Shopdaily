package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;

import shopdaily.dev.accordhk.com.shopdaily.Fragment.shop_status_pieChart.fragment_age;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.shop_status_pieChart.fragment_gender;
import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 7/12/2016.
 */
public class fragment_shop_status extends Fragment {

    String option = "fragment_gender";
    int shop_status_month=0;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_status_fragment, container, false);

        Bundle bundle = getArguments();
        if (bundle != null){
            shop_status_month = bundle.getInt("month");
        }


        TextView show_month = (TextView) view.findViewById(R.id.top_feed_02);
        show_month.setText(Integer.toString(shop_status_month) );


        LineChart chart = (LineChart) view.findViewById(R.id.chart);
        LineData data = new LineData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("My Chart");
        chart.animateXY(2000, 2000);
        chart.invalidate();

        //pie chart x2, apply fragment:
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_pieChart,new fragment_age()).commit();

        final TextView button_age = (TextView) view.findViewById(R.id.btn_age);
        final TextView button_gender = (TextView) view.findViewById(R.id.btn_gender);





        button_gender.setTextColor(Color.WHITE);
        if (button_age != null) {
            button_age.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  try {


                                                      button_age.setBackgroundResource(R.drawable.rec_text_left_pressed);
                                                      button_gender.setBackgroundResource(R.drawable.rec_text_right);
                                                      button_age.setTextColor(Color.WHITE);
                                                      button_gender.setTextColor(Color.parseColor("#999999"));
                                                      android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                      transaction.replace(R.id.fragment_container_pieChart,new fragment_age());
                                                      transaction.commit();


                                                  } catch (Exception e) {
                                                      e.printStackTrace();
                                                  }
                                              }
                                          }
            );
        }

        button_gender.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 try {

                                                     button_age.setBackgroundResource(R.drawable.rec_text_left);
                                                     button_gender.setBackgroundResource(R.drawable.rec_text_right_pressed);
                                                     button_gender.setTextColor(Color.WHITE);
                                                     button_age.setTextColor(Color.parseColor("#999999"));
                                                     android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                     transaction.replace(R.id.fragment_container_pieChart,new fragment_gender());
                                                     transaction.commit();


                                                 } catch (Exception e) {
                                                     e.printStackTrace();
                                                 }
                                             }


                                         }
        );


        return view;

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
