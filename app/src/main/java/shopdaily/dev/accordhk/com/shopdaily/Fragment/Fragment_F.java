package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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
public class Fragment_F extends ListFragment {
    Fragment_Favourite_shop_adapter adapter;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_f,container,false);
        Fragment_Feed new_fragment = new Fragment_Feed();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_feed_front,new_fragment).commit();

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


//        adapter = new Fragment_Favourite_shop_adapter(getActivity(), shop_name, shop_icon_list, adapter);
//        adapter = new Fragment_Favourite_shop_adapter(getActivity(), shop_name, shop_icon, adapter);
//        shop_re=(ListView)view.findViewById(R.id.list);


    }


}








