package shopdaily.dev.accordhk.com.shopdaily.Fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import shopdaily.dev.accordhk.com.shopdaily.Adapter.Fragment_Favourite_feed_adapter;
import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/22/2016.
 */
public class Fragment_Favourite_feed extends ListFragment {

    private View view;
    private ListView list_calllog;
    private ArrayList<HashMap<String, String>> callLog;
    //    ArrayList prgmName;
    public static int [] prgmImages={
            R.drawable.category_01,
            R.drawable.category_02,
            R.drawable.category_03,
            R.drawable.category_04,
            R.drawable.category_05,
            R.drawable.category_06,
            R.drawable.category_07
    };
    public static String [] prgmNameList={
            "Today",
            "Yeah Camera Shop",
            "Yesterday",

            "My Dream Chair",
            "The best gift to your love",
            "Super Camera",
            "Cleaning is an essential"};

    public static String [] timebar={"Today","Yesterday"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favourite_feed, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {



        Fragment_Favourite_feed_adapter adapter1 =
                new Fragment_Favourite_feed_adapter(getActivity(), prgmNameList,prgmImages);
//        list_calllog=(ListView)view.findViewById(R.id.list);
        list_calllog = getListView();
        getListView().setDivider(null);
        getListView().setDividerHeight(0);

        list_calllog.setAdapter(adapter1);
        list_calllog.setOnScrollListener(adapter1);




    }




}









