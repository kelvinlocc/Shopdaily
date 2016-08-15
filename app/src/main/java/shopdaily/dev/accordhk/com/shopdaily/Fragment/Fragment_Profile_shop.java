package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import shopdaily.dev.accordhk.com.shopdaily.Activity.profile_shop_edit;
import shopdaily.dev.accordhk.com.shopdaily.Activity.profile_shop_owner_edit;
import shopdaily.dev.accordhk.com.shopdaily.R;


/**
 * Created by iFocus on 27-10-2015.
 */
public class Fragment_Profile_shop extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_shop,container,false);
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
