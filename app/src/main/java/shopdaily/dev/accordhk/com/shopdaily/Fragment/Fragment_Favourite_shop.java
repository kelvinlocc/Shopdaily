package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import shopdaily.dev.accordhk.com.shopdaily.Adapter.Fragment_Favourite_shop_adapter;
import shopdaily.dev.accordhk.com.shopdaily.R;


/**
 * Created by iFocus on 27-10-2015.
 */
public class Fragment_Favourite_shop extends ListFragment {
    Fragment_Favourite_shop_adapter adapter;
    private View view;

    ArrayList<String> shop_name = new ArrayList<String>();
    ArrayList<Integer> shop_icon = new ArrayList<Integer>();

    private ListView shop_list;
    private ArrayList<HashMap<String, String>> callLog;
    //    ArrayList prgmName;


    public static int[] shop_icon_list = {
            R.drawable.react_1,
            R.drawable.react_2,
            R.drawable.react_3,
            R.drawable.react_1};
    public static String[] shop_name_list = {
            "Yeah Camera Shop",
            "Burgeroom",
            "Via TOkyo",
            "Little Vegas"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        for (int i = 0; i < shop_name_list.length; i++) {
            shop_name.add(shop_name_list[i]);
            Log.i("check_ffs", "shop_name add: " + shop_name_list[i]);
        }

        for (int i = 0; i < shop_icon_list.length; i++) {
            shop_icon.add(shop_icon_list[i]);
            Log.i("check_ffs", "shop_icon add: " + shop_icon_list[i]);

        }

        return inflater.inflate(R.layout.fragment_favourite_shop, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


//        adapter = new Fragment_Favourite_shop_adapter(getActivity(), shop_name, shop_icon_list, adapter);
        adapter = new Fragment_Favourite_shop_adapter(getActivity(), shop_name, shop_icon, adapter);
//        shop_list=(ListView)view.findViewById(R.id.list);

        shop_list = getListView();
        shop_list.setAdapter(adapter);
        shop_list.setOnScrollListener(adapter);
    }


}








