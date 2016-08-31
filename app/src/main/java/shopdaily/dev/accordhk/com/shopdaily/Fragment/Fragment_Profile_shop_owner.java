package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.w3c.dom.Text;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response_Data;
import shopdaily.dev.accordhk.com.shopdaily.Activity.profile_shop_owner_edit;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;
import shopdaily.dev.accordhk.com.shopdaily.Uility.RESTService;


/**
 * Created by iFocus on 27-10-2015.
 */
public class Fragment_Profile_shop_owner extends Fragment {

    private static String TAG = "Fragment_Profile_shop_owner ";
    TextView nick_name, birthday;
    MyPreApp myPreApp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile_shop_owner, container, false);
        myPreApp = new MyPreApp();
        Login_Response_Data login_response_data = myPreApp.getLoginData().data;

        nick_name = (TextView) view.findViewById(R.id.member_nick_name);
        nick_name.setText(login_response_data.member_nick_name);

        birthday = (TextView) view.findViewById(R.id.member_birthday);
        birthday.setText(login_response_data.member_birthday);


        Button btn_setting = (Button) view.findViewById(R.id.btn_edit);
        btn_setting.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               try {

                                                   Intent toNextActivity = new Intent(getActivity(), profile_shop_owner_edit.class);
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
