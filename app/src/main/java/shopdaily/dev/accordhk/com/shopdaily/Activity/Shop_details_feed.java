package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.feed_comment_response;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.shop_feed;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.shop_re;
import shopdaily.dev.accordhk.com.shopdaily.Adapter.commentListView_adapter;
import shopdaily.dev.accordhk.com.shopdaily.Adapter.feedProductPhotoListView_adapter;
import shopdaily.dev.accordhk.com.shopdaily.DataModel.FeedDataModel;
import shopdaily.dev.accordhk.com.shopdaily.DataModel.Gallery_DataModel;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;

/**
 * Created by KelvinLo on 6/8/2016.
 */


public class Shop_details_feed extends ActionBarActivity {
    //    int counter =0;
    public static String TAG = "Shop_details_feed ";
    Gallery_DataModel model;
    ArrayList<Gallery_DataModel> mData;
    ArrayList<String> userNameList;
    ArrayList<Integer> timeList;
    ArrayList<Integer> userIconList;

    commentListView_adapter comment_adapter;
    ProgressDialog dialog = null;
    EditText inputComment;
    private PopupWindow emji_window;


    MyPreApp myPreApp;
    shop_re shop_data;
    shop_feed feed_data;
    ArrayList<feed_comment_response> comment_list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.fragment_feed_shop_details);
        myPreApp = new MyPreApp();
        Log.i(TAG, "onCreate: comment: "+myPreApp.getComment_list().get(0).shop_feed_comment);
        comment_list = myPreApp.getComment_list();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (! bundle.isEmpty()) {
            Log.i(TAG, "onCreate: shop id, feed id:"+bundle.getString("shop_id")+","+bundle.getString("feed_id"));
            shop_data = myPreApp.searchForShop(bundle.getString("shop_id"));
            feed_data = myPreApp.searchForShopFeed(bundle.getString("feed_id"));
        }
//        Log.i("TAG", "myFeedDataModel.getShopName: " + myFeedDataModel.getShop_location());

        final TextView btn_go_back = (TextView) findViewById(R.id.txt_go_back);

        // setText on some simple data:
        TextView txt_distance = (TextView) findViewById(R.id.txt_distance);
        txt_distance.setText("???" + " km"); //// TODO: 9/17/2016

        TextView shop_name = (TextView) findViewById(R.id.shop_name);
        shop_name.setText(shop_data.shop_name);

        TextView shop_location = (TextView) findViewById(R.id.shop_location);
        shop_location.setText(shop_data.shop_address); // // TODO: 9/17/2016


        TextView store_info = (TextView) findViewById(R.id.store_info);
        store_info.setText(feed_data.feed_detail);
        TextView hastTag = (TextView) findViewById(R.id.hashTag);
        hastTag.setText(feed_data.feed_hashtag);

        TextView original_price = (TextView) findViewById(R.id.original_price);
        original_price.setText(feed_data.original_price);


        TextView discount_price = (TextView) findViewById(R.id.discount_price);
        discount_price.setText(feed_data.special_price);

        TextView category = (TextView) findViewById(R.id.product_category);
        category.setText(feed_data.feed_category_list);

        TextView react_01 = (TextView) findViewById(R.id.react_number_01);
        react_01.setText(feed_data.total_count_like);
        TextView react_02 = (TextView) findViewById(R.id.react_number_02);
        react_02.setText(feed_data.total_count_cool);
        TextView react_03 = (TextView) findViewById(R.id.react_number_03);
        react_03.setText(feed_data.total_count_love);
        TextView txt_comment = (TextView) findViewById(R.id.txt_comment);
        txt_comment.setText(feed_data.total_count_comment);
        TextView txt_share = (TextView) findViewById(R.id.txt_share);
        txt_share.setText(feed_data.total_count_share);

        TextView discount = (TextView) findViewById(R.id.discount);
        discount.setText(feed_data.discount_rate);


        // photo list view:
        ListView photoListView = (ListView) findViewById(R.id.product_photo_listView);
        final feedProductPhotoListView_adapter myAdapter = new feedProductPhotoListView_adapter(this, shop_data.imageArray);
        photoListView.setAdapter(myAdapter);
        setListViewHeightBasedOnChildren(photoListView);


        // comment list view & add comment:
        // fetching data::
//        userNameList = new ArrayList<String>(Arrays.asList(myFeedDataModel.getUser_name_list()));
//        commentList = new ArrayList<String>(Arrays.asList(myFeedDataModel.getUser_comment_list()));
//        userIconList = new ArrayList<Integer>(Arrays.asList(myFeedDataModel.getUser_icon_list()));
//        timeList = new ArrayList<Integer>(Arrays.asList(myFeedDataModel.getUser_timeline_list()));
//        String[] items={"Apple","Banana","Clementine"};
//        itemList=new ArrayList<String>(userNameList);

        final ListView comment_LV = (ListView) findViewById(R.id.listView_comment);
        inputComment = (EditText) findViewById(R.id.input_comment);

        //adapter::
        comment_adapter = new commentListView_adapter(this, comment_list);
        comment_LV.setAdapter(comment_adapter);
        setListViewHeightBasedOnChildren(comment_LV);


        TextView btn_send = (TextView) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newComment = inputComment.getText().toString();
                // add new item to arraylist
                String currentUser = "Current User";
                Collections.reverse(userNameList);
                Collections.reverse(userIconList);
//                Collections.reverse(commentList);
                Collections.reverse(timeList);

                userNameList.add(currentUser);
                userIconList.add(R.drawable.react_1);
//                commentList.add(newComment);
                timeList.add(0);
                Collections.reverse(userNameList);
                Collections.reverse(userIconList);
//                Collections.reverse(commentList);
                Collections.reverse(timeList);

                // notify listview of data changed
                comment_adapter.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(comment_LV);
            }

        });


        TextView btn_react = (TextView) findViewById(R.id.btn_react);
        TextView btn_comment = (TextView) findViewById(R.id.btn_comment);
        TextView btn_share = (TextView) findViewById(R.id.btn_share);
        ImageButton btn_location = (ImageButton) findViewById(R.id.btn_location);
        ImageButton btn_favourite = (ImageButton) findViewById(R.id.btn_favourite);

//        Button button = (Button) findViewById(R.id.btn_react);
//        button.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                initiatePopupWindow();
//                return false;
//            }
//        });

        btn_react.setOnClickListener(new OnItemClickListener());
        btn_react.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                initiatePopupWindow();
                return false;
            }
        });

        btn_comment.setOnClickListener(new OnItemClickListener());
        btn_share.setOnClickListener(new OnItemClickListener());
        btn_location.setOnClickListener(new OnItemClickListener());
        btn_favourite.setOnClickListener(new OnItemClickListener());

        assert btn_go_back != null;
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    btn_go_back.setBackgroundResource(R.color.yellow);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });


    }

    private void initiatePopupWindow() {
        try {
            LayoutInflater inflater = (LayoutInflater) this.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.screen_popup, (ViewGroup) this.findViewById(R.id.popup_element));

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

//                    feedDataModel = FeedList.get(mPosition);
////
//                    feedDataModel.restoreReactNumber();
//
//
//                    feedDataModel.react(0);
//
//                    notifyDataSetChanged();
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
//
//                    notifyDataSetChanged();
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
//
//                    notifyDataSetChanged();
                    emji_window.dismiss();

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class OnItemClickListener extends Shop_details_feed implements View.OnClickListener {
        private int mPosition;


        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_location){
                Log.i(TAG,"location button clicked");
            }

            if (v.getId() == R.id.btn_favourite){
                Log.i(TAG,"favourite button clicked");
            }

            if (v.getId() == R.id.btn_react){
                Log.i(TAG,"react button clicked");
            }

            if (v.getId() == R.id.btn_share) {
                Log.i(TAG, " button_share is clicked");
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                Intent.createChooser(sendIntent, "Share via");
                startActivity(sendIntent);
                Log.i(TAG, "share button clicked");
            }

            if (v.getId() == R.id.btn_comment) {
                Log.i(TAG, "comment button clicked");
            }

        }
    }


    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            Log.i(TAG, "listAdapter.getCount(): " + listAdapter.getCount());
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            Log.i(TAG, "listItem.getMeasuredHeight(): " + listItem.getMeasuredHeight());
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        if (listView.getDividerHeight() == 0) {
            listView.setDividerHeight(1);
        }
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        Log.i(TAG, "totalHeight :" + totalHeight + " +" + (listView.getDividerHeight() * (listAdapter.getCount() - 1)));
        Log.i(TAG, "listView.getDividerHeight() : " + listView.getDividerHeight());
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
