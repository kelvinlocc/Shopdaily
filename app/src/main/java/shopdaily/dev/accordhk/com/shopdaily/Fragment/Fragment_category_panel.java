package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Map;

import shopdaily.dev.accordhk.com.shopdaily.Adapter.Fragment_Category_Adapter;
import shopdaily.dev.accordhk.com.shopdaily.Adapter.Fragment_Profile_gv_adapter;
import shopdaily.dev.accordhk.com.shopdaily.DataModel.GridViewDataModel;
import shopdaily.dev.accordhk.com.shopdaily.R;


/**
 * Created by iFocus on 27-10-2015.
 */
public class Fragment_category_panel extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_panel, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setAdapter(new Fragment_Category_Adapter(view.getContext())); // uses the view to get the context instead of getActivity().
        return view;



//        return inflater.inflate(R.layout.fragment_category_panel, container, false);

    }

    public void onStart() {
        super.onStart();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();

//        if (mMap != null) {
//            MainActivity.fragmentManager.beginTransaction()
//                    .remove(MainActivity.fragmentManager.findFragmentById(R.id.map)).commit();
//            mMap = null;
//        }
    }

}
