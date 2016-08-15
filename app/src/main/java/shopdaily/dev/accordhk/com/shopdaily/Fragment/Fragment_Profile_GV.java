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
public class Fragment_Profile_GV extends Fragment {
    private int COLUMN = 3;
    private int DEFAULT_ITEM_COUNT;
    ArrayList<GridViewDataModel> data;
    Spinner spinner;
    ArrayAdapter<CharSequence> sAdapter;





    DisplayMetrics displayMetrics;


    Map<String, Object> params;
    String userName;
    String imageLocation;

    int width, height;
    Boolean lock = false;

    Bitmap notFound;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_gv, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setAdapter(new Fragment_Profile_gv_adapter(view.getContext())); // uses the view to get the context instead of getActivity().
        return view;



//        return inflater.inflate(R.layout.fragment_category_panel, container, false);

    }

    public void onStart() {
        super.onStart();
//        spinner = (Spinner) getActivity().findViewById(R.id.spinner);
//        sAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.spinner, android.R.layout.simple_spinner_item);
//        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(sAdapter);
////        spinner.setOnItemSelectedListener(this);
//        GridView gridView = (GridView) getActivity().findViewById(R.id.gridView);
//        data = new ArrayList<>();
//        displayMetrics = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        width = displayMetrics.widthPixels/COLUMN;
//        height = displayMetrics.heightPixels;
//

//        gridView.setAdapter(adapter);
//        gridView.setOnScrollListener(adapter);
//
//        userName = getArguments().getString("currentUser");
//        notFound =  BitmapFactory.decodeResource(getActivity().getResources(),
//                R.drawable.not_found);


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
