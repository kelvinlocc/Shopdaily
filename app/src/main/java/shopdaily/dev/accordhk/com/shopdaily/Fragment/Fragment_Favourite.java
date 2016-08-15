package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import shopdaily.dev.accordhk.com.shopdaily.Activity.SignUp;
import shopdaily.dev.accordhk.com.shopdaily.Activity.SignUpSuccess;
import shopdaily.dev.accordhk.com.shopdaily.Adapter.Fragment_Category_Adapter;
import shopdaily.dev.accordhk.com.shopdaily.Adapter.Fragment_Favourite_Adapter;
import shopdaily.dev.accordhk.com.shopdaily.R;


/**
 * Created by iFocus on 27-10-2015.
 */
public class Fragment_Favourite extends Fragment {
    ProgressDialog dialog = null;
    String tag = "qa_fragment";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // the fragment part:
        // Inflate the layout for this fragment
//        Fragment videoFragment = new Fragment_Profile_shop_owner();
//        Fragment videoFragment = new Fragment_Favourite_shop();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new Fragment_Favourite_shop()).commit();


        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
//        View view = inflater.inflate(R.layout.fragment_favourite_shop, container, false);

        final TextView btn_feed = (TextView) view.findViewById(R.id.btn_feed);
        final TextView btn_shop = (TextView) view.findViewById(R.id.btn_shop);
        btn_shop.setTextColor(Color.WHITE);
        btn_feed.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            try {


                                                btn_feed.setBackgroundResource(R.drawable.rec_text_left_pressed);
                                                btn_shop.setBackgroundResource(R.drawable.rec_text_right);
                                                btn_feed.setTextColor(Color.WHITE);
                                                btn_shop.setTextColor(Color.parseColor("#999999"));

//                Fragment videoFragment = new Fragment_Favourite_feed();
                                                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                                                transaction.replace(R.id.fragment_container, new Fragment_Favourite_feed(), tag);
//                transaction.addToBackStack(null);
                                                transaction.commit();


                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
        );

        btn_shop.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            try {

                                                btn_feed.setBackgroundResource(R.drawable.rec_text_left);
                                                btn_shop.setBackgroundResource(R.drawable.rec_text_right_pressed);
                                                btn_shop.setTextColor(Color.WHITE);
                                                btn_feed.setTextColor(Color.parseColor("#999999"));
//                    Fragment videoFragment = new Fragment_Favourite_shop();
                                                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                                                transaction.replace(R.id.fragment_container, new Fragment_Favourite_shop(), tag);
                                                Log.i("check_", "fragment add");
//                    transaction.addToBackStack(null);
                                                transaction.commit();

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }


                                    }
        );
        return view;

    }
// * important!
    //    Fragments can be added inside other fragments but then you will need to remove
// it from parent Fragment each time when onDestroyView() method of parent fragment
// is called. And again add it in Parent Fragment's OnCreateView() method.
//    @Override
//    public void onDestroyView() {
//        Log.i("check_","onDestroyView_mainf");
//        FragmentManager mFragmentMgr = getFragmentManager();
//        FragmentTransaction mTransaction = mFragmentMgr.beginTransaction();
//        Fragment childFragment = mFragmentMgr.findFragmentByTag("qa_fragment");
//        mTransaction.remove(childFragment);
//        mTransaction.commit();
//        super.onDestroyView();
//    }
}
