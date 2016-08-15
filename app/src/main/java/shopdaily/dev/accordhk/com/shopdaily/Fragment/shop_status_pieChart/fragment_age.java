package shopdaily.dev.accordhk.com.shopdaily.Fragment.shop_status_pieChart;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;

import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 7/12/2016.
 */
public class fragment_age extends Fragment {

    String option = "fragment_gender";

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gender, container, false);

        PieChart chart = (PieChart) view.findViewById(R.id.pieChart);
        chart = configureChart(chart);

        chart = setData(chart, option);
        chart.animateXY(1500, 1500);

        return view;

    }

    public PieChart configureChart(PieChart chart) {
//        chart.setHoleColor(Color.WHITE);
        chart.setHoleRadius(40f);
        chart.setHoleColorTransparent(true);
        chart.setDescription("");
        chart.setTransparentCircleRadius(5f);
//        chart.setDrawYValues(true);
        chart.setDrawCenterText(true);
        chart.setDrawHoleEnabled(true);
        chart.setRotationAngle(0);
//        chart.setDrawXValues(false);
        chart.setRotationEnabled(true);
        chart.setUsePercentValues(true);

//        chart.setu

        return chart;
    }

    private PieChart setData(PieChart chart, String option) {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(25, 0));
        entries.add(new Entry(75, 1));
        ArrayList<String> xVals = new ArrayList<String>();

            xVals.add("10-25");
            xVals.add("26-40");

        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setSliceSpace(0f);
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#ffe3f2"));
        colors.add(Color.parseColor("#e7f3ff"));

        pieDataSet.setColors(colors);
        PieData data = new PieData(xVals, pieDataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(15f);

        chart.setData(data);
        chart.highlightValues(null);
        chart.invalidate();
        return chart;
    }

}
