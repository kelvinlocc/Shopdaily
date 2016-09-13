package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response_Data;
import shopdaily.dev.accordhk.com.shopdaily.Activity.profile_shop_owner_edit;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;


/**
 * Created by iFocus on 27-10-2015.
 */
public class Fragment_Profile_shop_owner extends Fragment {

    private static String TAG = "Fragment_Profile_shop_owner ";
    TextView nick_name, birthday;
    MyPreApp myPreApp;
    ImageView member_profile_image;
    Login_Response_Data login_response_data;

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
        login_response_data = myPreApp.getLoginResponse().data;

        ImageView member_gender = (ImageView) view.findViewById(R.id.member_gender);
        if (login_response_data.member_gender == "1"){
            member_gender.setImageDrawable(getResources().getDrawable(R.drawable.male));
        }
        else if (login_response_data.member_gender=="2"){
            member_gender.setImageDrawable(getResources().getDrawable(R.drawable.female));
        }

        member_profile_image = (ImageView) view.findViewById(R.id.member_profile_image);
        
        if (!Objects.equals(login_response_data.member_profile_image, "")){
            Log.i(TAG, "onCreateView: member_profile_image "+login_response_data.member_profile_image);
            member_profile_image.setImageBitmap(myPreApp.getBitmapFromURL(login_response_data.member_profile_image));
        }
        else {
            Log.i(TAG, "onCreateView: member_profile_image is null");
        }
        

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
    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        login_response_data = myPreApp.getLoginResponse().data;
        updateView();
        
        //Refresh your stuff here
    }
    
    public void updateView (){
        Log.i(TAG, "updateView: ");
        Log.i(TAG, "updateView: login_response_data.member_profile_image "+login_response_data.member_profile_image);
        if (!Objects.equals(login_response_data.member_profile_image, "")){
            Log.d(TAG, "onFinished: member image: "+login_response_data.member_profile_image);
            member_profile_image.setImageBitmap(myPreApp.getBitmapFromURL(login_response_data.member_profile_image));
        }
        if (login_response_data.member_nick_name==null) {
            nick_name.setText("new nick name...");
        } else {
            nick_name.setText(login_response_data.member_nick_name);
        }

        if (login_response_data.member_birthday==null) {
            birthday.setText("????-??-??");
        } else {
            birthday.setText(login_response_data.member_birthday);
        }


    }
}
