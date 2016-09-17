/*
Names:  Lo Chi Chiu          Student ID: 2019 8281     Email address: ccload@ust.hk
Names:  Lee Wing Lam     Student ID: 20124034       Email address: wlleeac@ust.hk
Names:  Chan Kai Hong    Student ID: 20124591       Email address: khchanbc@ust.hk

 */
package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


import me.relex.circleindicator.CircleIndicator;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Shop_Response;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.shop_feed;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.shop_re;
import shopdaily.dev.accordhk.com.shopdaily.Activity.MapsActivity;
import shopdaily.dev.accordhk.com.shopdaily.DataModel.FeedDataModel;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Feed;

import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;


public class Fragment_Feed_Adapter extends BaseAdapter implements View.OnClickListener, AbsListView.OnScrollListener {
    static String TAG = "Fragment_Feed_Adapter ";
    Fragment_Feed my_feed;
    LayoutInflater inflater;
    //    FeedDataModel feedDataModel;
    View view;
    ViewHolder holder;
    MyPreApp myPreApp;

    private PopupWindow emji_window;
    Location thisUserCurrentLocation = new Location("new");
    Location shop_location = new Location("shop_location");
    boolean loading;
//    List<FeedDataModel> FeedList = null;

//    ArrayList<FeedDataModel> feedDataModelArrayList;

    public Fragment_Feed_Adapter(Fragment_Feed feed, List<FeedDataModel> data, int limit, Location userCurrentLocation) {
        my_feed = feed;
//        this.FeedList = data;
//        this.feedDataModelArrayList = new ArrayList<FeedDataModel>();
//        this.feedDataModelArrayList.addAll(data);
        inflater = (LayoutInflater) feed.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loading = false;
        thisUserCurrentLocation = userCurrentLocation;
        myPreApp = new MyPreApp();
        feed_list = myPreApp.getFeed_list();
        shop_list = myPreApp.getShop_list();
        Log.i(TAG, " thisUserCurrentLocation @ initial adapter" + userCurrentLocation.getLatitude() + "," + userCurrentLocation.getLongitude());

    }

    public int getCount() {
        Log.i(TAG, " size: " + feed_list.size());
        return feed_list.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {

    }


    public static class ViewHolder {
        public static TextView Shop_name;
        public static ImageButton btn_share;
        public static ImageView Picture;
        public static ImageButton imagb_react;
        public static ImageButton imageButton_location;
        public static TextView tv_react;
        public static TextView react_number_01;
        public static TextView react_number_02;
        public static TextView react_number_03;
        public static TextView Btn_comment;
        public static TextView tv_shop_location;
        public static TextView tv_distance;
        public static ViewPager myPhotoViewpager;
        public static LinearLayout shop_detail_view;
        //
        public static TextView store_info;
        public static TextView hashTag;
        public static TextView txt_comment;
        public static TextView txt_share;
        public static TextView product_category;
        public static TextView originalPrice;
        public static TextView discountPrice;
        public static TextView discount;
        //

    }

    ArrayList<shop_feed> feed_list;
    shop_feed feed_data;
    ArrayList<shop_re> shop_list;
    shop_re shop_data;

    public View getView(final int position, View convertView, final ViewGroup parent) {
        view = convertView;
//        if (convertView == null) {
        Log.i(TAG, "feed: " + position + " created!");
        view = inflater.inflate(R.layout.fragment_feed_entry, parent, false);
        holder = new ViewHolder();


//        feedListTesting();
        //<
        holder.store_info = (TextView) view.findViewById(R.id.store_info);
        holder.hashTag = (TextView) view.findViewById(R.id.hashTag);
        holder.txt_comment = (TextView) view.findViewById(R.id.txt_comment);
        holder.txt_share = (TextView) view.findViewById(R.id.txt_share);
        holder.product_category = (TextView) view.findViewById(R.id.product_category);
        holder.originalPrice = (TextView) view.findViewById(R.id.original_price);
        holder.discountPrice = (TextView) view.findViewById(R.id.discount_price);
        holder.discount = (TextView) view.findViewById(R.id.discount);

        //>
        holder.myPhotoViewpager = (ViewPager) view.findViewById(R.id.myPhotoViewpager);
        holder.shop_detail_view = (LinearLayout) view.findViewById(R.id.shop_detail_view);

        holder.imageButton_location = (ImageButton) view.findViewById(R.id.imagebutton_location);
        holder.tv_shop_location = (TextView) view.findViewById(R.id.tv_shop_location);
        holder.Btn_comment = (TextView) view.findViewById(R.id.btn_comment);
        holder.Shop_name = (TextView) view.findViewById(R.id.shop_name);
        holder.tv_distance = (TextView) view.findViewById(R.id.tv_distance);
        holder.imagb_react = (ImageButton) view.findViewById(R.id.imgb_react);
        holder.tv_react = (TextView) view.findViewById(R.id.tv_react);

        holder.react_number_01 = (TextView) view.findViewById(R.id.react_number_01);
        holder.react_number_02 = (TextView) view.findViewById(R.id.react_number_02);
        holder.react_number_03 = (TextView) view.findViewById(R.id.react_number_03);


        holder.btn_share = (ImageButton) view.findViewById(R.id.button_share);
        holder.originalPrice = (TextView) view.findViewById(R.id.original_price);
        holder.originalPrice.setPaintFlags(ViewHolder.originalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        view.setTag(holder);
//        } else {
//            holder = (ViewHolder) view.getTag();
//        }

        if (feed_list.size() <= 0) {
            holder.Shop_name.setText("error, no data!");
            return (view);

        } else {
            Log.i(TAG, "checked::");
            Log.i(TAG, "getView: feed_list.size()"+feed_list.size());

            feed_data = feed_list.get(position);
//            if (shop_list.get(position).feed !=null)
//            if (shop_list.get(position).feed != null) {
//                Log.i(TAG, "getView: feed is not null @" + shop_list.get(position).feed.shop_feed_id);
//            } else {
//                Log.i(TAG, "getView:  feed is null ");
//            }
            shop_data = searchForShop(feed_data.shop_id);


//            holder.myPhotoViewpager.setAdapter(new SamplePagerAdapter());
            ViewPager viewPager = holder.myPhotoViewpager;
            ViewPagerAdapterWithView temp_myPhoto_view = new ViewPagerAdapterWithView(my_feed, shop_data);
            viewPager.setAdapter(temp_myPhoto_view);
            CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
//            indicator.drawable
            indicator.setViewPager(viewPager);


            holder.Shop_name.setText(shop_data.shop_name);
            holder.tv_shop_location.setText("unknown");
//            holder.tv_shop_location.setText(feedDataModel.getShop_full_location());
            holder.react_number_01.setText(feed_data.total_count_like);
            holder.react_number_02.setText(feed_data.total_count_cool);
            holder.react_number_03.setText(feed_data.total_count_love);
//                Log.i(TAG, "ouput xy: " + feedDataModel.getShop_X_coordinate() + "," + feedDataModel.getShop_Y_coordinate());

            // shop_re locaiton:
            shop_location.setLatitude(Double.parseDouble(shop_data.shop_location_x));
            shop_location.setLongitude(Double.parseDouble(shop_data.shop_location_y));


            if (thisUserCurrentLocation != null) {
                Log.i(TAG, " thisUserCurrentLocation" + thisUserCurrentLocation.getLatitude() + "," + thisUserCurrentLocation.getLongitude());
            } else {
                Log.i(TAG, " thisUserCurrentLocation is null");
            }
            int display_user_to_shop_distance = cal_user_to_shop_distance(thisUserCurrentLocation, shop_location);
//                feedDataModel.setUser_shop_distance(Integer.toString(display_user_to_shop_distance));


            holder.tv_distance.setText("???" + " km");

            holder.tv_react.setTextColor(Color.parseColor("#999999"));
            holder.imagb_react.setColorFilter(0xff999999, PorterDuff.Mode.MULTIPLY);

            //check if liked//// TODO: 9/17/2016
//                if (feedDataModel.isReact()) {
//                    holder.tv_react.setTextColor(Color.parseColor("#00a99e"));
//                    holder.imagb_react.setColorFilter(0xff00a99e, PorterDuff.Mode.MULTIPLY);
//
//                }

            //<
            holder.store_info.setText(feed_data.feed_detail);
            holder.hashTag.setText(feed_data.feed_hashtag);
            holder.txt_comment.setText(feed_data.total_count_comment);
            holder.txt_share.setText(feed_data.total_count_share);
            holder.product_category.setText(feed_data.feed_category_list); /// // TODO: 9/17/2016
            holder.originalPrice.setText(feed_data.original_price);
            holder.discountPrice.setText(feed_data.special_price);
            holder.discount.setText(feed_data.discount_rate);
            //>


            holder.tv_react.setOnClickListener(new OnItemClickListener(position));
            holder.tv_react.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    initiatePopupWindow(position);
                    return false;
                }
            });
            holder.react_number_01.setOnClickListener(new OnItemClickListener(position));
            holder.imageButton_location.setOnClickListener(new OnItemClickListener(position));
            holder.btn_share.setOnClickListener(new OnItemClickListener(position));
            holder.shop_detail_view.setOnClickListener(new OnItemClickListener(position));

        }
        return view;

    }
    public shop_re searchForShop (String shop_id){
        Log.i(TAG, "searchForShop: shopid: for "+shop_id);
        for (int i = 0; i < shop_list.size(); i++) {
            Log.i(TAG, "searchForShop: shopid: "+shop_list.get(i).shop_id);
            if (Objects.equals(shop_id, shop_list.get(i).shop_id)){
                Log.i(TAG, "searchForShop: match");
                return shop_list.get(i);
            }
        }

        Log.e(TAG, "searchForShop: not found!!!!");
        return null;
    }

    public void feedListTesting() {
        Log.i(TAG, "feedListTesting: ");
        if (myPreApp.getFeed_list() != null) {
            feed_list = myPreApp.getFeed_list();
            for (int i = 0; i < feed_list.size(); i++) {
                Log.i(TAG, "getView: feed " + feed_list.get(i).shop_feed_id);
                if (!feed_list.get(i).image_list.isEmpty()) {
                    Log.i(TAG, "getView: feed image " + feed_list.get(i).image_list.get(0));
                } else {
                    Log.i(TAG, "getView: feed image is empty");
                }
            }
        } else {
            Log.i(TAG, "getView: feed list is null");
        }
    }


    // Filter Class
    public void filter(String charText) {
        Log.i(TAG, "filter ");

        charText = charText.toLowerCase(Locale.getDefault());
        shop_list.clear();
        if (charText.length() == 0) {
            shop_list.addAll(myPreApp.getShop_list());
        } else {
            for (shop_re wp : shop_list) {
                if (wp.shop_name.toLowerCase(Locale.getDefault()).contains(charText)) {
                    Log.i(TAG, "shop_re add: " + wp.shop_name);
                    shop_list.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    private void initiatePopupWindow(final int mPosition) {
        try {
            LayoutInflater inflater = (LayoutInflater) my_feed.getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.screen_popup, (ViewGroup) my_feed.getActivity().findViewById(R.id.popup_element));

            emji_window = new PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

//            emji_window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


            emji_window.setFocusable(true);

            emji_window.setOutsideTouchable(true);

            emji_window.setTouchable(true);
            emji_window.setBackgroundDrawable(new BitmapDrawable());
            emji_window.setTouchInterceptor(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                        Log.i("Background", "Back Touched");
                        emji_window.dismiss();
                        return true;
                    }
                    return false;
                }
            });
            emji_window.showAtLocation(layout, Gravity.CENTER, 50, 50);
            emji_window.update();

            Log.i(TAG, "emji_window");
            emji_window.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    emji_window.dismiss();
                    emji_window.isOutsideTouchable();
                }
            });
            ImageButton ImgB_react1 = (ImageButton) layout.findViewById(R.id.imgb_1);
            ImgB_react1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    feedDataModel = FeedList.get(mPosition);
//                    feedDataModel.restoreReactNumber();
//                    feedDataModel.react(0);

                    notifyDataSetChanged();
                    emji_window.dismiss();
                }
            });

            ImageButton ImgB_react2 = (ImageButton) layout.findViewById(R.id.imgb_2);
            ImgB_react2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    feedDataModel = FeedList.get(mPosition);
//
//                    feedDataModel.restoreReactNumber();
//
//                    feedDataModel.react(1);

                    notifyDataSetChanged();
                    emji_window.dismiss();

                }
            });

            ImageButton ImgB_react3 = (ImageButton) layout.findViewById(R.id.imgb_3);
            ImgB_react3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    feedDataModel = FeedList.get(mPosition);
//
//                    feedDataModel.restoreReactNumber();
//
//
//                    feedDataModel.react(2);

                    notifyDataSetChanged();
                    emji_window.dismiss();

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class OnItemClickListener extends FragmentActivity implements View.OnClickListener {


        private int mPosition;

        OnItemClickListener(int position) {
            mPosition = position;
            // correctly setted!
            Log.i(TAG, "OnItemClickListener R postion: " + position);
        }


        public void onClick(View v) {

            if (v.getId() == R.id.tv_react) {
//                feedDataModel = FeedList.get(mPosition);
//                feedDataModel.restoreReactNumber();
                Log.i(TAG, "react clicked in feed_update: " + mPosition);
                notifyDataSetChanged();
            }

            if (v.getId() == R.id.button_share) {
                Log.i(TAG, " button_share is clicked");
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                Intent.createChooser(sendIntent, "Share via");
                my_feed.startActivity(sendIntent);
            }

            if (v.getId() == R.id.imagebutton_location) {
//                feedDataModel = FeedList.get(mPosition);
                shop_data = shop_list.get(mPosition);

                Intent NextActivity = new Intent(my_feed.getActivity(), MapsActivity.class);
//                Log.i(TAG, "shop_re x:" + feedDataModel.getShop_X_coordinate());

                NextActivity.putExtra("shop_name", shop_data.shop_name);
                NextActivity.putExtra("shop_location", "unknown");
                NextActivity.putExtra("shop_full_location", "unknown full location");
                NextActivity.putExtra("shop_distance", "unknow distance");
                NextActivity.putExtra("shop_location_x", shop_data.shop_location_x);
                NextActivity.putExtra("shop_location_y", shop_data.shop_location_y);

                my_feed.startActivity(NextActivity);
            }

        }


    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Log.i(TAG, "onScrollStateChanged ");
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


    }

    public int cal_user_to_shop_distance(Location user, Location shop) {
        Log.i(TAG, " user" + user.getLatitude() + "," + user.getLongitude());
        Log.i(TAG, " shop_re" + shop.getLatitude() + "," + shop.getLongitude());

        int distance = (int) user.distanceTo(shop) / 1000;
        Log.i(TAG, "distance: " + distance);
        return distance;
    }


}


