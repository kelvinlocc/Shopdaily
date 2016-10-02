package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response_Data;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.bookmark_feed_response;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.API;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;

/**
 * Created by KelvinLo on 6/22/2016.
 */
public class Fragment_Favourite_feed_adapter extends BaseAdapter implements AbsListView.OnScrollListener {

    Context context;
    ArrayList<bookmark_feed_response> bookmark_list;
    MyPreApp myPreApp;
    API myApi;
    static String TAG = "Favourite_feed_ap";

    int Counter = 0;
    private static LayoutInflater inflater = null;

    public Fragment_Favourite_feed_adapter(Context context2, ArrayList<bookmark_feed_response> data) {

        myPreApp = new MyPreApp();
        myApi = new API(context2);
        context = context2;//
        bookmark_list = data;

        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int setToday(){
        for (int i = 0; i < bookmark_list.size(); i++) {
            bookmark_feed_response data= bookmark_list.get(i);
            Log.i(TAG, "setToday: create_datetime "+data.create_datetime);
            if (Objects.equals(myPreApp.getTodayOrYesterday(data.create_datetime), "today")){
                Log.i(TAG, "setToday: ");
                return bookmark_list.size()+2;
            }

        }
        return bookmark_list.size()+1;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return setToday();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    public class Holder {
        TextView tv;
        ImageView img;
        TextView date;
        TextView shop_info;
        ImageButton remove_bookmark;
    }
    bookmark_feed_response data;
    boolean today,yesterday= true;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub



        Log.i("check_", " at position: " + position+"counter: "+Counter);
        Log.i(TAG, "getView: bookmark_list.size(): "+bookmark_list.size());
        Holder holder = new Holder();
        View rowView;
        data = bookmark_list.get(position-Counter);

        if (Objects.equals(myPreApp.getTodayOrYesterday(data.create_datetime), "today")&&today) {
            rowView = inflater.inflate(R.layout.favourite_feed_listview, null);
            holder.date = (TextView) rowView.findViewById(R.id.txt_date);
            holder.date.setText("Today");
            Log.i(TAG, "Today: ");
            Counter++;
            today=false;

        }
        else if(Objects.equals(myPreApp.getTodayOrYesterday(data.create_datetime), "Yesterday")&&yesterday){
            rowView = inflater.inflate(R.layout.favourite_feed_listview, null);
            holder.date = (TextView) rowView.findViewById(R.id.txt_date);
            holder.date.setText("yesterday");

           
            Log.i(TAG, "yesterday: ");
            Counter++;
            yesterday=false;
        }
        else {
            data = bookmark_list.get(position - Counter);
            rowView = inflater.inflate(R.layout.fragment_favourite_feed_list, null);
            holder.tv = (TextView) rowView.findViewById(R.id.shop_name);
            holder.img = (ImageView) rowView.findViewById(R.id.feed_image);
            holder.tv.setText(data.shopcol_shop_name);
            holder.img.setImageBitmap(myPreApp.getBitmapFromURL(data.myImages));
            holder.shop_info = (TextView) rowView.findViewById(R.id.store_info);
            holder.shop_info.setText(data.feed_detail+data.create_datetime);
            holder.remove_bookmark = (ImageButton) rowView.findViewById(R.id.removeBookmark);
            holder.remove_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: remove: "+bookmark_list.get(position-Counter));
                    bookmark_list.remove(position-Counter);
                    removeBookmark(bookmark_list.get(position-Counter).shop_feed_id,bookmark_list.get(position-Counter).shop_id);

                    Counter=0;
                    today=yesterday=true;
                    notifyDataSetChanged();
                    Log.i(TAG, "onClick: ");
                }
            });

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Toast.makeText(context, "You Clicked @position: " + position, Toast.LENGTH_LONG).show();
                }
            });
        }
        return rowView;
    }



    public void removeBookmark(String shop_feed_id,String shop_id){
        String api_key = "654321";
        String lang_id = "1";
        String bookmark_action = "2";
        Login_Response_Data data = myPreApp.getLoginResponse().data;
        myApi.ShopBookmark(api_key,lang_id,data.member_session,shop_id,shop_feed_id,bookmark_action, new API.onAjaxFinishedListener() {
            @Override
            public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
                Log.i(TAG, "onFinished: Json "+json);
            }
        });

    }

}