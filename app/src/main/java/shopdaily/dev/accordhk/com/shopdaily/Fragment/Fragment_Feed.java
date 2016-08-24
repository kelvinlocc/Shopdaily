package shopdaily.dev.accordhk.com.shopdaily.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

//import com.androidquery.callback.AjaxStatus;

import java.util.ArrayList;
import java.util.List;

import shopdaily.dev.accordhk.com.shopdaily.Adapter.Fragment_Feed_Adapter;
import shopdaily.dev.accordhk.com.shopdaily.DataModel.FeedDataModel;
import shopdaily.dev.accordhk.com.shopdaily.DataModel.filter_DataModel;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreferenceApplication;
import shopdaily.dev.accordhk.com.shopdaily.Uility.expanable_listview.ExpandableAdapter;
import shopdaily.dev.accordhk.com.shopdaily.Uility.expanable_listview.Item;


public class Fragment_Feed extends ListFragment {

    static String TAG = "Fragment_Feed ";
    private int ENTRY_LIMIT = 1;
    ListView list_view;
    Fragment_Feed_Adapter feed_list_adapter;

    ArrayList<FeedDataModel> feedDataModelArrayList;
    FeedDataModel feedDataModel;

    public String shop_name_list[];
    public String shop_location_list[];
    public String shop_full_location_list[];
    public String filter_Top_bar[];
    public String filter_price_list[];

    //getting data for filter
    public String filter_item[];
    public String filter_item_price[];
    public String filter_item_location[];
    public String filter_item_category[];
    //photo_list data from resource:
    public String feed_category[];
    public String store_info;
    public int comment;
    public int share;

    public int photo_list[];
    public int myPhoto_list[] = {R.drawable.category_01, R.drawable.category_02, R.drawable.category_03};


    public Integer user_icon_list[] = {R.drawable.category_01, R.drawable.category_02, R.drawable.category_03};

    public String user_name_list[];
    public String user_comment_list[];
    public Integer user_timeline_list[] = {10, 20, 30};
    public String hash_tag_list[];
    public int original_price;
    public int discount_price;
    public int discount;
    private MyPreferenceApplication myPreferenceApplication;
    public CheckBox checkBox;
    //fake data:
    //wan chai: 22.276039, 114.182555
    double shop_coordination[][] = {{22.276039, 114.182555}, {29.760287, -95.399637}, {29.757890, -95.399966}, {29.760337, -95.394815}};

    Location userCurrentLocation;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    ArrayList<filter_DataModel> filter_dataModel_arrayList;


//    static FirstPageFragmentListener firstPageListener;


    public void onListItemClick(ListView l, View v, int position, long id) {
//        firstPageListener.onSwitchToNextFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_feed, container, false);


        //get current user lcoation
        myPreferenceApplication = new MyPreferenceApplication();
        userCurrentLocation = myPreferenceApplication.getUserLocation(getContext());


        final LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean isLocationServiceEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isLocationServiceEnabled) {
            Log.i(TAG, "onCreateView: locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER); is true");
        } else {
            if (!myPreferenceApplication.getGPSOption(getContext())) {
                showSettingsAlert("Network");
            }
        }
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null) {
            Log.d(TAG, "device offline");
        } else {
            Log.d(TAG, "device online");
        }


        final FrameLayout PopUp_window = (FrameLayout) view.findViewById(R.id.popup_window);
        PopUp_window.setVisibility(View.INVISIBLE);

        LinearLayout FilterBar = (LinearLayout) view.findViewById(R.id.filterBar);
        LinearLayout feed_listView = (LinearLayout) view.findViewById(R.id.feed_listView);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) feed_listView.getLayoutParams();

        Bundle extras = getArguments();
        if (extras != null) {
            if (extras.getBoolean("setDisable")) {
                FilterBar.setVisibility(View.INVISIBLE);
                params.setMargins(0, 0, 0, 50);
            }
        }


        // fetching data:
        shop_name_list = getActivity().getResources().getStringArray(R.array.shop_list);
        shop_location_list = getActivity().getResources().getStringArray(R.array.shop_location_list);
        shop_full_location_list = getActivity().getResources().getStringArray(R.array.shop_full_location_list);
        filter_Top_bar = getActivity().getResources().getStringArray(R.array.filter_top_bar);
        //filter_price_list = getActivity().getResources().getStringArray(R.array.filter_price_list);

        //filter data:
        filter_item = getActivity().getResources().getStringArray(R.array.filter_item);
        for (int i = 0; i < filter_item.length; i++) {
            Log.i(TAG, "@filter_item: " + filter_item[i]);
        }
        filter_item_price = getActivity().getResources().getStringArray(R.array.filter_item_price);
        filter_item_location = getActivity().getResources().getStringArray(R.array.filter_item_location);
        filter_item_category = getActivity().getResources().getStringArray(R.array.filter_item_category);
        // for photo_list:
        photo_list = getActivity().getResources().getIntArray(R.array.photo_list);
        user_name_list = getActivity().getResources().getStringArray(R.array.user_name_list);
        user_comment_list = getActivity().getResources().getStringArray(R.array.user_comment_list);
        hash_tag_list = getActivity().getResources().getStringArray(R.array.hast_tag_list);


        original_price = getActivity().getResources().getInteger(R.integer.original_price);
        discount_price = getActivity().getResources().getInteger(R.integer.discount_price);
        discount = getActivity().getResources().getInteger(R.integer.discount);
        //<
        feed_category = getActivity().getResources().getStringArray(R.array.feed_category);
        store_info = getActivity().getResources().getString(R.string.store_info);
        comment = 10;
        share = 5;
        //>


        // initialize pop up window


        ToggleButton toggleButton = (ToggleButton) view.findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PopUp_window.setVisibility(View.INVISIBLE);
                } else {
                    PopUp_window.setVisibility(View.VISIBLE);
                }
            }
        });

        // popUP filter
        ListView listView_filter_item_list = (ListView) view.findViewById(R.id.listView_filter_item_list);
        ExpandableAdapter adapter = getAdapter();

        listView_filter_item_list.setAdapter(adapter);
        listView_filter_item_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExpandableAdapter adapter = (ExpandableAdapter) parent.getAdapter();

                Item item = (Item) adapter.getItem(position);
                if (item != null) {
                    if (item.isExpanded) {
                        item.isExpanded = false;

                    } else {
                        item.isExpanded = true;
                    }
                }

                adapter.notifyDataSetChanged();
            }
        });

        //  popUp filter

        return view;

    }

    private ExpandableAdapter getAdapter() {

        List<Item> items = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Item item = new Item();
            item.title = "Title  " + i;
            item.description = "Description for Title  " + i;
            item.isExpanded = false;
            item.setFilter_item(filter_item);
            Log.i(TAG, "@filter_item: " + filter_item[i]);
            item.setFilter_item_list(setFilter_itemByName(filter_item[i]));

            items.add(item);
        }

        return new ExpandableAdapter(getActivity(), items);
    }

    public String[] setFilter_itemByName(String value) {

        switch (value) {
            case "filter_item":
                return filter_item;

            case "Price":
                return filter_item_price;

            case "Location":
                return filter_item_location;

            case "Category":
                return filter_item_category;
        }
        Log.i("expandableAdapter", "return filter_item_price");
        return null;


    }


    public void onStart() {
        Log.i(TAG, "onStart");

        list_view = getListView();

        feedDataModelArrayList = new ArrayList<>();
        getURL();

        feed_list_adapter = new Fragment_Feed_Adapter(this, feedDataModelArrayList, ENTRY_LIMIT, userCurrentLocation);
        ///
        Bundle extras = getArguments();
        if (extras != null) {
            if (extras.getBoolean("setDisable")) {
                Log.i(TAG, "input text: " + extras.get("text"));
                feed_list_adapter.filter(extras.getString("text"));

            }
        }
        list_view.setAdapter(feed_list_adapter);
        list_view.setOnScrollListener(feed_list_adapter);

        super.onStart();


    }


    public void getURL() {
        Log.i(TAG, "getURL");
        for (int i = 0; i < 3; i++) {
            feedDataModel = new FeedDataModel();
            feedDataModel.setShopName(shop_name_list[i]);
            feedDataModel.setShop_location(shop_location_list[i]);
            feedDataModel.setShop_full_location(shop_full_location_list[i]);
            feedDataModel.setReact_number_01(i);
            feedDataModel.setReact_number_02(i);
            feedDataModel.setReact_number_03(i);
            Log.i(TAG, "setCoordinate: " + shop_coordination[i][0]);

            feedDataModel.setCoordinate(shop_coordination[i][0], shop_coordination[i][1]);
            Log.i(TAG, "feedDataModel.getShop_X_coordinate: " + feedDataModel.getShop_X_coordinate());
            Log.i(TAG, "feedDataModel.getShop_Y_coordinate: " + feedDataModel.getShop_Y_coordinate());

            // fetching data:
            //<
            feedDataModel.setPhoto_list(myPhoto_list);
            feedDataModel.setProduct_category(feed_category[i]);
            feedDataModel.setStore_info(store_info);
            feedDataModel.setComment(comment);
            feedDataModel.setShare(share);

            feedDataModel.setHashTag_list(hash_tag_list);
            feedDataModel.setOriginal_price("$" + Integer.toString(original_price));
            feedDataModel.setDiscount_price("$" + Integer.toString(discount_price));
            feedDataModel.setDiscount(Integer.toString(discount) + "%");
            Log.i("feedDataModel.get", "" + feedDataModel.getComment());
            //>
//            feedDataModel.setPhoto_list(photo_list);
            feedDataModel.setUser_icon_list(user_icon_list);
            feedDataModel.setUser_name_list(user_name_list);
            feedDataModel.setUser_comment_list(user_comment_list);
            feedDataModel.setUser_timeline_list(user_timeline_list);


            feedDataModelArrayList.add(i, feedDataModel);
        }
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    public void showSettingsAlert(String provider) {

        Log.i(TAG, "showSettingsAlert: ");
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.checkbox, null);
        checkBox = (CheckBox) view.findViewById(R.id.skip);


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                getActivity());

        alertDialog.setTitle(provider + " SETTINGS");
        alertDialog.setView(view);
        alertDialog
                .setMessage(provider + " is not enabled! Want to go to settings menu?");

        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (checkBox.isChecked()) {
                            Toast.makeText(getActivity(), "don't ask again!", Toast.LENGTH_SHORT).show();
                            myPreferenceApplication.setGPSOption(getContext(), true);
                        }
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        getActivity().startActivity(intent);
                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (checkBox.isChecked()) {
                            Toast.makeText(getActivity(), "don't ask again!", Toast.LENGTH_SHORT).show();
                            myPreferenceApplication.setGPSOption(getContext(), true);
                        }
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }


//    public void loadMore(final int offset, final int count) {
//        Log.i("NewsFeed_", "loadMore");
//        if (offset + count >= feedDataModelArrayList.size()) {
//            Toast.makeText(getActivity(), "All feeds are loaded!", Toast.LENGTH_LONG).show();
//            return;
//        } else {
//            Toast.makeText(getActivity(), "loading feed error", Toast.LENGTH_LONG).show();
//        }
//        if (count == ENTRY_LIMIT) {
//            feed_list_adapter.unlock();
//            return;
//        }
//        final int entry = offset + count;
////        params = new HashMap<String, Object>();
////        imageLocation = service.getBaseURL() + data.get(entry).getLocation();
////        service.getImage(imageLocation, params, new RESTService.onAjaxBitmapFinishedListener() {
////            public void onFinished(String url, Bitmap bm, AjaxStatus status) {
////                if (bm == null) {
////                    data.get(entry).setPic(notFoundImage);
////                } else {
////                    data.get(entry).setPic(bm);
////                }
//        feed_list_adapter.updateNoOfPost();
////                feed_list_adapter.notifyDataSetChanged();
////                loadMore(offset, count + 1);
////            }
////        });
////
//
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}

