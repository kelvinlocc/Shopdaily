package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import shopdaily.dev.accordhk.com.shopdaily.Activity.Post_feed_Activity;
import shopdaily.dev.accordhk.com.shopdaily.Activity.setting_Activity;
import shopdaily.dev.accordhk.com.shopdaily.Activity.Special_Activity;
import shopdaily.dev.accordhk.com.shopdaily.R;


/**
 * Created by iFocus on 27-10-2015.
 */
public class Fragment_Profile extends Fragment {
    String tag = "qa_fragment";
    boolean gate = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new Fragment_Profile_lV()).commit();


        FragmentTransaction transaction_SO = getChildFragmentManager().beginTransaction();
        transaction_SO.replace(R.id.fragment_container_SO, new Fragment_Profile_shop_owner()).commit();

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        final ImageButton btn_switch = (ImageButton) view.findViewById(R.id.btn_switch);

        btn_switch.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              try {


                                                  // color: http://www.nthelp.com/colorcodes.htm  ff+0x006600

                                                  if (gate) {
//                                                      btn_switch.setBackgroundResource(R.drawable.menu_profile);
                                                      btn_switch.setImageResource(R.drawable.menu_profile);
                                                      gate = !gate;
                                                      FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                                                      transaction.replace(R.id.fragment_container_SO, new Fragment_Profile_shop(), tag);
                                                      Log.i("check_", "fragment add");

                                                      transaction.commit();
                                                  } else {
                                                      btn_switch.setImageResource(R.drawable.menu_feed);
                                                      gate = !gate;
                                                      FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                                                      transaction.replace(R.id.fragment_container_SO, new Fragment_Profile_shop_owner(), tag);
                                                      Log.i("check_", "fragment add");

                                                      transaction.commit();
                                                  }


                                              } catch (Exception e) {
                                                  e.printStackTrace();
                                              }
                                          }


                                      }
        );


        final ImageButton btn_listview = (ImageButton) view.findViewById(R.id.btn_listview);
        final ImageButton btn_gridview = (ImageButton) view.findViewById(R.id.btn_gridview);
        btn_listview.setColorFilter(0xff00a99e, PorterDuff.Mode.MULTIPLY);

        btn_gridview.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                try {


                                                    // color: http://www.nthelp.com/colorcodes.htm  ff+0x006600
                                                    btn_gridview.setColorFilter(0xff00a99e, PorterDuff.Mode.MULTIPLY);
                                                    btn_listview.setColorFilter(0xff999999, PorterDuff.Mode.MULTIPLY);

                                                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                                                    transaction.replace(R.id.fragment_container, new Fragment_Profile_GV(), tag);
                                                    Log.i("check_", "fragment add");

                                                    transaction.commit();

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }


                                        }
        );
        btn_listview.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                try {

                                                    btn_listview.setColorFilter(0xff00a99e, PorterDuff.Mode.MULTIPLY);
                                                    btn_gridview.setColorFilter(0xff999999, PorterDuff.Mode.MULTIPLY);

                                                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                                                    transaction.replace(R.id.fragment_container, new Fragment_Profile_lV(), tag);

                                                    transaction.commit();


                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
        );




        final TextView post_feed = (TextView) view.findViewById(R.id.btn_post_feed);
        post_feed.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             try {
                                                 post_feed.setBackgroundResource(R.color.yellow);
                                                 Log.i("check_", "post feed button pressed");
                                                 Intent toMainActivity = new Intent(getActivity(), Post_feed_Activity.class);
                                                 startActivity(toMainActivity);
                                                 getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                                             } catch (Exception e) {
                                                 e.printStackTrace();
                                             }
                                         }
                                     }
        );

        final TextView special_action = (TextView) view.findViewById(R.id.btn_special);
        special_action.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  try {
                                                      special_action.setBackgroundResource(R.color.yellow);
                                                      Intent toMainActivity = new Intent(getActivity(), Special_Activity.class);
                                                      startActivity(toMainActivity);

                                                      getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


                                                  } catch (Exception e) {
                                                      e.printStackTrace();
                                                  }
                                              }
                                          }
        );


        final ImageButton setting_action = (ImageButton) view.findViewById(R.id.btn_setting);
        setting_action.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  try {
                                                      setting_action.setBackgroundResource(R.color.yellow);

                                                      Intent toNextActivity = new Intent(getActivity(), setting_Activity.class);
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
