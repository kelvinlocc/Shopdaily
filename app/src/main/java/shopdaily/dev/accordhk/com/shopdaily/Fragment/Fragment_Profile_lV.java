package shopdaily.dev.accordhk.com.shopdaily.Fragment;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import shopdaily.dev.accordhk.com.shopdaily.Adapter.Fragment_Favourite_feed_adapter;
import shopdaily.dev.accordhk.com.shopdaily.Adapter.Fragment_Profile_lv_adapter;
import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/22/2016.
 */
public class Fragment_Profile_lV extends ListFragment {

    private View view;
    private ListView list_calllog;
    private ArrayList<HashMap<String, String>> callLog;
    //    ArrayList prgmName;
    public static int [] prgmImages={
            R.drawable.category_01,
            R.drawable.category_02,
            R.drawable.category_03,
            R.drawable.category_04,};
    public static String [] prgmNameList={
            "Man",
            "You",
            "Siu Ming",
            "You",};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile_lv, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {



        Fragment_Profile_lv_adapter adapter =
                new Fragment_Profile_lv_adapter(getActivity(), prgmNameList,prgmImages);
//        list_calllog=(ListView)view.findViewById(R.id.list);
        list_calllog = getListView();
        list_calllog.setAdapter(adapter);
        list_calllog.setOnScrollListener(adapter);




    }




}









