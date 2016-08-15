package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import shopdaily.dev.accordhk.com.shopdaily.Adapter.commentListView_adapter;
import shopdaily.dev.accordhk.com.shopdaily.Adapter.feedProductPhotoListView_adapter;
import shopdaily.dev.accordhk.com.shopdaily.DataModel.FeedDataModel;
import shopdaily.dev.accordhk.com.shopdaily.DataModel.Gallery_DataModel;
import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/8/2016.
 */


public class Shop_details_feed_2 extends ActionBarActivity {
//    int counter =0;
    public static String TAG = "Shop_details_feed ";
    Gallery_DataModel model;
    ArrayList<Gallery_DataModel> mData;
    List<String> newUserNameArray;
    ArrayList<String> itemList;
    commentListView_adapter myAdapter;
    ProgressDialog dialog = null;
    EditText editText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        if (Build.VERSION.SDK_INT < 16) {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }
//
//
//        setContentView(R.layout.fragment_feed_shop_details);
//        Log.i(TAG,"test");
//
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        final FeedDataModel myFeedDataModel = (FeedDataModel) bundle.getSerializable("data");
//        Log.i("TAG", "myFeedDataModel.getShopName: " + myFeedDataModel.getShop_location());
//
//        userNameList= Arrays.asList(myFeedDataModel.getUser_name_list());
//
//
//        String[] items={"Apple","Banana","Clementine"};
//        itemList=new ArrayList<String>(Arrays.asList(items));
//
//        final TextView btn_go_back = (TextView) findViewById(R.id.txt_go_back);
//        TextView txt_distance = (TextView) findViewById(R.id.txt_distance);
//        txt_distance.setText(myFeedDataModel.getUser_shop_distance() + " km");
//
//        TextView shop_name = (TextView) findViewById(R.id.shop_name);
//        shop_name.setText(myFeedDataModel.getShopName());
//
//        TextView shop_location = (TextView) findViewById(R.id.shop_location);
//        shop_location.setText(myFeedDataModel.getShop_location());
//
//
//        TextView store_info = (TextView) findViewById(R.id.store_info);
//        store_info.setText(myFeedDataModel.getStore_info());
//        TextView hastTag = (TextView) findViewById(R.id.hashTag);
//        hastTag.setText(Arrays.toString(myFeedDataModel.getHashTag_list()));
//
//        TextView original_price = (TextView) findViewById(R.id.original_price);
//        original_price.setText(myFeedDataModel.getOriginal_price());
//
//
//        TextView discount_price = (TextView) findViewById(R.id.discount_price);
//        discount_price.setText(myFeedDataModel.getDiscount_price());
//
//        TextView category = (TextView) findViewById(R.id.product_category);
//        category.setText(myFeedDataModel.getProduct_category());
//
//        TextView react_01 = (TextView) findViewById(R.id.react_number_01);
//        react_01.setText(Integer.toString(myFeedDataModel.getReact_number_01()));
//        TextView react_02 = (TextView) findViewById(R.id.react_number_02);
//        react_02.setText(Integer.toString(myFeedDataModel.getReact_number_02()));
//        TextView react_03 = (TextView) findViewById(R.id.react_number_03);
//        react_03.setText(Integer.toString(myFeedDataModel.getReact_number_03()));
//        TextView txt_comment = (TextView) findViewById(R.id.txt_comment);
//        txt_comment.setText(Integer.toString(myFeedDataModel.getComment()));
//        TextView txt_share = (TextView) findViewById(R.id.txt_share);
//        txt_share.setText(Integer.toString(myFeedDataModel.getShare()));
//
//        TextView discount = (TextView) findViewById(R.id.discount);
//        discount.setText(myFeedDataModel.getDiscount() + "%");
//
//        ListView photoListView = (ListView) findViewById(R.id.product_photo_listView);
//        final feedProductPhotoListView_adapter myAdapter = new feedProductPhotoListView_adapter(this, myFeedDataModel.getPhoto_list());
//        photoListView.setAdapter(myAdapter);
//        setListViewHeightBasedOnChildren(photoListView);
//
//
//        ListView listV = (ListView) findViewById(R.id.listView_comment);
//
//
//
//        inputComment = (EditText) findViewById(R.id.input_comment);
//        TextView btn_send = (TextView) findViewById(R.id.btn_send);
//
//
//        comment_adapter = new commentListView_adapter(this, itemList);
//
//        listV.setAdapter(comment_adapter);
//
//        btn_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String newItem = inputComment.getText().toString();
//                // add new item to arraylist
//                itemList.add(newItem);
//                // notify listview of data changed
//                comment_adapter.notifyDataSetChanged();
//            }
//
//        });
//
//
//
//
//
//        TextView btn_react = (TextView) findViewById(R.id.btn_react);
//        TextView btn_comment = (TextView) findViewById(R.id.btn_comment);
//        TextView btn_share = (TextView) findViewById(R.id.btn_share);
//        ImageButton btn_location = (ImageButton) findViewById(R.id.btn_location);
//        ImageButton btn_favourite = (ImageButton) findViewById(R.id.btn_favourite);
//
//
//
//        assert btn_go_back != null;
//        btn_go_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//
//                    btn_go_back.setBackgroundResource(R.color.yellow);
//                    finish();
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//        });
        setContentView(R.layout.fragment_feed_shop_details);
        String[] items = {"Apple", "Banana", "Clementine"};
        itemList = new ArrayList<String>(Arrays.asList(items));
//        myAdapter = new commentListView_adapter(this,itemList);

        final ListView listV = (ListView) findViewById(R.id.listView_comment);
        listV.setAdapter(myAdapter);
        editText = (EditText) findViewById(R.id.input_comment);
        Button btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = editText.getText().toString();
                // add new item to arraylist

                itemList.add(newItem);
                for (int i =0;i<itemList.size();i++){
                    Log.i("check_","itemlist.get: "+itemList.get(i));
                }
                // notify listview of data changed
                myAdapter.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(listV);
            }

        });
        Log.i("check_","myAdapter.getCount(); "+myAdapter.getCount());
        setListViewHeightBasedOnChildren(listV);


    }


    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            Log.i(TAG,"listAdapter.getCount(): "+listAdapter.getCount());
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            Log.i(TAG,"listItem.getMeasuredHeight(): "+listItem.getMeasuredHeight());
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        if (listView.getDividerHeight() == 0){listView.setDividerHeight(1);}
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        Log.i(TAG,"totalHeight :"+totalHeight+" +"+(listView.getDividerHeight() * (listAdapter.getCount() - 1)));
        Log.i(TAG,"listView.getDividerHeight() : "+listView.getDividerHeight() );
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
