package shopdaily.dev.accordhk.com.shopdaily.Fragment;


import android.os.Bundle;

import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response_Data;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.bookmark_feed_response;
import shopdaily.dev.accordhk.com.shopdaily.Adapter.Fragment_Favourite_feed_adapter;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.API;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;

/**
 * Created by KelvinLo on 6/22/2016.
 */
public class Fragment_Favourite_feed extends ListFragment {

    private View view;
    private ListView listView;
    private ArrayList<HashMap<String, String>> callLog;
    static String TAG = "Fragment_Favourite_feed";
    MyPreApp myPreApp;
    API myApi;
    //    ArrayList prgmName;
    public static int [] prgmImages={
            R.drawable.category_01,
            R.drawable.category_02,
            R.drawable.category_03,
            R.drawable.category_04,
            R.drawable.category_05,
            R.drawable.category_06,
            R.drawable.category_07
    };
    public static String [] prgmNameList={
            "Today",
            "Yeah Camera Shop_Response",
            "Yesterday",

            "My Dream Chair",
            "The best gift to your love",
            "Super Camera",
            "Cleaning is an essential"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favourite_feed, container, false);

    }

    ArrayList<bookmark_feed_response> bookmark_feed_res_list;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        myPreApp = new MyPreApp();
        myApi = new API(getActivity());

        getBookmark_fromServer();
    }


    public void getBookmark_fromServer (){
        bookmark_feed_res_list = new ArrayList<>();
        String api_key = "654321";
        String lang_id = "1";
        Login_Response_Data data = myPreApp.getLoginResponse().data;
        String shop_or_feed = "2";
        myApi.getBookmarkList(api_key,lang_id,data.member_session,shop_or_feed, new API.onAjaxFinishedListener() {
            @Override
            public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
                Log.i(TAG, "onFinished: Json:"+json);
                JSONObject jsonobject = new JSONObject(json);
                JSONArray jsonArray = jsonobject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    Log.i(TAG, "onFinished: jsonArray: "+jsonArray);
                    Gson gson = new Gson();
                    bookmark_feed_response bk = gson.fromJson(jsonArray.get(i).toString(),bookmark_feed_response.class);
                    JSONObject jObj = jsonArray.getJSONObject(i);
                    JSONArray jsonArray_02 = jObj.getJSONArray("images");
                    String temp = jsonArray_02.getString(0);
                    Log.i(TAG, "onFinished: temp"+temp);
                    bk.myImages = temp;
                    Log.i(TAG, "onFinished: images: "+bk.myImages);

//                    bk.images= array.getJSONArray("images").toString();
                    bookmark_feed_res_list.add(bk);
                }

                for (int i = 0; i < bookmark_feed_res_list.size(); i++) {
                    Log.i(TAG, "onFinished: shop_feed_bookmark_id @i"+i+","+bookmark_feed_res_list.get(i).shop_feed_bookmark_id);
                }
                startView();

            }
        });
    }
    public void startView(){
        Fragment_Favourite_feed_adapter adapter =
                new Fragment_Favourite_feed_adapter(getActivity(), bookmark_feed_res_list);
//        listView=(ListView)view.findViewById(R.id.list);
        listView = getListView();
        getListView().setDivider(null);
        getListView().setDividerHeight(0);

        listView.setAdapter(adapter);
        listView.setOnScrollListener(adapter);

    }




}









