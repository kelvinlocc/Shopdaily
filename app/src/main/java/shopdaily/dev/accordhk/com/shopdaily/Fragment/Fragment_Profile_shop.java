package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Shop_Response;
import shopdaily.dev.accordhk.com.shopdaily.Activity.profile_shop_edit;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;


/**
 * Created by iFocus on 27-10-2015.
 */
public class Fragment_Profile_shop extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    String TAG = "Fragment_Profile_shop";
    TextView shop_name,shop_address,shop_operation_hour;
    MyPreApp myPreapp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_shop,container,false);
        myPreapp = new MyPreApp();
        Shop_Response shop_data = myPreapp.getLoginResponse().data.shop;

        shop_name = (TextView) view.findViewById(R.id.shop_name);
        shop_name.setText(shop_data.shop_name_en);
        Log.i(TAG, "onCreateView: "+shop_data.shop_name_en);

        shop_address = (TextView) view.findViewById(R.id.shop_address);
        shop_address.setText(shop_data.shop_address_en);

        shop_operation_hour = (TextView) view.findViewById(R.id.shop_operation_hour);
        shop_operation_hour.setText(shop_data.shop_operation_hour_en);

        Button btn_setting = (Button) view.findViewById(R.id.btn_edit);
        btn_setting.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               try {

                                                   Intent toNextActivity = new Intent(getActivity(), profile_shop_edit.class);
                                                   startActivity(toNextActivity);
                                                   getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


                                               } catch (Exception e) {
                                                   e.printStackTrace();
                                               }
                                           }
                                       }
        );
        return view;


    }
}
