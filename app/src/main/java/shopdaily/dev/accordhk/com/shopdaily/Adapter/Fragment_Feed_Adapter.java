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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


import me.relex.circleindicator.CircleIndicator;
import shopdaily.dev.accordhk.com.shopdaily.Activity.MapsActivity;
import shopdaily.dev.accordhk.com.shopdaily.DataModel.FeedDataModel;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Feed;

import shopdaily.dev.accordhk.com.shopdaily.R;


public class Fragment_Feed_Adapter extends BaseAdapter implements View.OnClickListener, AbsListView.OnScrollListener {
    static String TAG = "Fragment_Feed_Adapter ";
    Fragment_Feed my_feed;
    LayoutInflater inflater;
    FeedDataModel feedDataModel;
    View view;
    ViewHolder holder;
    private PopupWindow emji_window;
    Location thisUserCurrentLocation = new Location("new");
    Location shop_location = new Location("shop_location");
    boolean loading;
    List<FeedDataModel> FeedList = null;

    ArrayList<FeedDataModel> feedDataModelArrayList;

    public Fragment_Feed_Adapter(Fragment_Feed feed, List<FeedDataModel> data, int limit, Location userCurrentLocation) {
        my_feed = feed;
        this.FeedList = data;
        this.feedDataModelArrayList = new ArrayList<FeedDataModel>();
        this.feedDataModelArrayList.addAll(data);
        inflater = (LayoutInflater) feed.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loading = false;
        thisUserCurrentLocation = userCurrentLocation;

        Log.i(TAG, " thisUserCurrentLocation @ initial adapter" + userCurrentLocation.getLatitude() + "," + userCurrentLocation.getLongitude());

    }

    public int getCount() {

        Log.i(TAG, "feedlist.size: " + FeedList.size());
        return FeedList.size();

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

    public View getView(final int position, View convertView, final ViewGroup parent) {
        view = convertView;
//        if (convertView == null) {
        Log.i(TAG, "feed: " + position + " created!");
        view = inflater.inflate(R.layout.fragment_feed_entry, parent, false);
        holder = new ViewHolder();

        //<
        holder.store_info = (TextView) view.findViewById(R.id.store_info);
        holder.hashTag = (TextView) view.findViewById(R.id.hashTag);
        holder.txt_comment =(TextView) view.findViewById(R.id.txt_comment);
        holder.txt_share = (TextView) view.findViewById(R.id.txt_share);
        holder.product_category= (TextView) view.findViewById(R.id.product_category);
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

        if (FeedList.size() <= 0) {
            holder.Shop_name.setText("error, no data!");
            return (view);

        } else {
            Log.i(TAG, "changed_05");
            feedDataModel = null;
            feedDataModel = FeedList.get(position);

//            holder.myPhotoViewpager.setAdapter(new SamplePagerAdapter());
            ViewPager viewPager = holder.myPhotoViewpager;
            ViewPagerAdapterWithView temp_myPhoto_view = new ViewPagerAdapterWithView(my_feed,feedDataModel);
            viewPager.setAdapter(temp_myPhoto_view);
            CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
//            indicator.drawable
            indicator.setViewPager(viewPager);


            holder.Shop_name.setText(feedDataModel.getShopName());
            holder.tv_shop_location.setText(feedDataModel.getShop_location());
//            holder.tv_shop_location.setText(feedDataModel.getShop_full_location());
            holder.react_number_01.setText(Integer.toString(feedDataModel.getReact_number_01()));
            holder.react_number_02.setText(Integer.toString(feedDataModel.getReact_number_02()));
            holder.react_number_03.setText(Integer.toString(feedDataModel.getReact_number_03()));
            Log.i(TAG, "ouput xy: " + feedDataModel.getShop_X_coordinate() + "," + feedDataModel.getShop_Y_coordinate());

            // shop locaiton:
            shop_location.setLatitude(feedDataModel.getShop_X_coordinate());
            shop_location.setLongitude(feedDataModel.getShop_Y_coordinate());


            if (thisUserCurrentLocation != null) {
                Log.i(TAG, " thisUserCurrentLocation" + thisUserCurrentLocation.getLatitude() + "," + thisUserCurrentLocation.getLongitude());
            } else {
                Log.i(TAG, " thisUserCurrentLocation is null");
            }
            int display_user_to_shop_distance = cal_user_to_shop_distance(thisUserCurrentLocation, shop_location);
            feedDataModel.setUser_shop_distance(Integer.toString(display_user_to_shop_distance));


            holder.tv_distance.setText(String.valueOf(feedDataModel.getUser_shop_distance()) + " km");

            holder.tv_react.setTextColor(Color.parseColor("#999999"));
            holder.imagb_react.setColorFilter(0xff999999, PorterDuff.Mode.MULTIPLY);
            if (feedDataModel.isReact()) {
                holder.tv_react.setTextColor(Color.parseColor("#00a99e"));
                holder.imagb_react.setColorFilter(0xff00a99e, PorterDuff.Mode.MULTIPLY);

            }
            //<
            holder.store_info.setText(feedDataModel.getStore_info());
            holder.hashTag.setText(Arrays.toString(feedDataModel.getHashTag_list()) );
            holder.txt_comment.setText(Integer.toString(feedDataModel.getComment()));
            holder.txt_share.setText(Integer.toString(feedDataModel.getShare()));
            holder.product_category.setText(feedDataModel.getProduct_category());
            holder.originalPrice.setText(feedDataModel.getOriginal_price());
            holder.discountPrice.setText(feedDataModel.getDiscount_price());
            holder.discount.setText(feedDataModel.getDiscount());
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


    // Filter Class
    public void filter(String charText) {
        Log.i(TAG, "filter ");

        charText = charText.toLowerCase(Locale.getDefault());
        FeedList.clear();
        if (charText.length() == 0) {
            FeedList.addAll(feedDataModelArrayList);
        } else {
            for (FeedDataModel wp : feedDataModelArrayList) {
                if (wp.getShopName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    Log.i(TAG, "shop add: " + wp.getShopName());
                    FeedList.add(wp);
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

            Log.i(TAG,"emji_window");
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

                    feedDataModel = FeedList.get(mPosition);
//
                        feedDataModel.restoreReactNumber();


                        feedDataModel.react(0);

                    notifyDataSetChanged();
                    emji_window.dismiss();
                }
            });

            ImageButton ImgB_react2 = (ImageButton) layout.findViewById(R.id.imgb_2);
            ImgB_react2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    feedDataModel = FeedList.get(mPosition);

                    feedDataModel.restoreReactNumber();

                        feedDataModel.react(1);

                    notifyDataSetChanged();
                    emji_window.dismiss();

                }
            });

            ImageButton ImgB_react3 = (ImageButton) layout.findViewById(R.id.imgb_3);
            ImgB_react3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    feedDataModel = FeedList.get(mPosition);

                        feedDataModel.restoreReactNumber();


                        feedDataModel.react(2);

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
                feedDataModel = FeedList.get(mPosition);
                feedDataModel.restoreReactNumber();
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
                feedDataModel = FeedList.get(mPosition);

                Intent NextActivity = new Intent(my_feed.getActivity(), MapsActivity.class);
//                Log.i(TAG, "shop x:" + feedDataModel.getShop_X_coordinate());

                NextActivity.putExtra("shop_name", feedDataModel.getShopName());
                NextActivity.putExtra("shop_location", feedDataModel.getShop_location());
                NextActivity.putExtra("shop_full_location", feedDataModel.getShop_full_location());
                NextActivity.putExtra("shop_distance", feedDataModel.getUser_shop_distance());
                NextActivity.putExtra("shop_location_x", feedDataModel.getShop_X_coordinate());
                NextActivity.putExtra("shop_location_y", feedDataModel.getShop_Y_coordinate());

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
        Log.i(TAG, " shop" + shop.getLatitude() + "," + shop.getLongitude());

        int distance = (int) user.distanceTo(shop) / 1000;
        Log.i(TAG, "distance: " + distance);
        return distance;
    }


}


