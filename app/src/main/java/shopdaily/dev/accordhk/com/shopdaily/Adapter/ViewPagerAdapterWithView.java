package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.feed_comment_response;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.shop_feed;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.shop_re;
import shopdaily.dev.accordhk.com.shopdaily.Activity.Shop_details_feed;
import shopdaily.dev.accordhk.com.shopdaily.DataModel.FeedDataModel;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Feed;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.API;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;
import shopdaily.dev.accordhk.com.shopdaily.Uility.ViewPagerItem;

/**
 * Created by flashok on 9.8.14.
 */
public class ViewPagerAdapterWithView extends PagerAdapter {
    public static String TAG = "ViewPagerAdapterWView";
    private ArrayList<ViewPagerItem> pagerItems;
    private LayoutInflater inflater;
    private Context context;
    private Fragment_Feed myFeed;


    ArrayList<String> image;
    shop_re shop_data;
    shop_feed feed_data;
    Context myContext;

    public ViewPagerAdapterWithView(Fragment_Feed feed, shop_re data,shop_feed data_feed,Context context) {
        super();
        myFeed = feed;
        shop_data = data;
        feed_data  = data_feed;
        myContext = context;
        image = shop_data.imageArray;
        inflater = (LayoutInflater) feed.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    API myApi;
    MyPreApp myPreApp;
    List<Bitmap> imageArray;
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout layout = null;
        layout = (LinearLayout) inflater.inflate(R.layout.item_viewpager, null);
        ImageView product_photo = (ImageView) layout.findViewById(R.id.product_photo);

        myPreApp = new MyPreApp();


//        product_photo.setImageResource(feedDataModel.getPhoto_list()[position]);
//        Log.i(TAG,"feedDataModel.getPhoto_list()[position]: "+feedDataModel.getPhoto_list()[position]);
        Log.i(TAG, "instantiateItem: ");
        if (image.size() != 0) {
            for (int i = 0; i <image.size() ; i++) {
                Log.i(TAG, "instantiateItem: printf @ "+i+"," +image.get(i));
            }
            Log.i(TAG, "instantiateItem: @position: "+position + image.get(position));
            product_photo.setImageBitmap(myPreApp.getBitmapFromURL(image.get(position)));
        }
        else {
            product_photo.setImageResource(R.drawable.category_06);
        }

        LinearLayout main_viewPager = (LinearLayout) layout.findViewById(R.id.main_viewPager);
        main_viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextActivity = new Intent(myFeed.getActivity(), Shop_details_feed.class);
                NextActivity.putExtra("shop_id",shop_data.shop_id);
                NextActivity.putExtra("feed_id",feed_data.shop_feed_id);
                String api_key = "654321";
                String lang_id = "1";
                getFeedComment_list (myContext,api_key,lang_id,myPreApp.getLoginResponse().data.member_session,feed_data.shop_feed_id);
//                myFeed.startActivity(NextActivity);
            }
        });
        container.addView(layout);
        return layout;
    }
    Intent NextActivity;


    ArrayList<feed_comment_response> feed_comment_list;
    feed_comment_response comment;
    public void getFeedComment_list(Context context,final String api_key,final String lang_id,final String member_session,final String shop_feed_id) {
        Log.i(TAG, "getFeedComment_list: ");
        feed_comment_list = new ArrayList<>();
        comment = new feed_comment_response();
//        Log.i(TAG, "getFeedComment_list: CHECK INPUT:: "+api_key+","+lang_id+","+member_session+","+shop_feed_id);
        myApi = new API(context);
        myApi.getFeedComment(api_key, lang_id, member_session, shop_feed_id, new API.onAjaxFinishedListener() {
            @Override
            public void onFinished(String url, String json, AjaxStatus status) throws JSONException {
                Log.i(TAG, "onFinished: getFeedComment_listJson"+json);
                JSONObject jsonObject = new JSONObject(json);
                jsonObject = jsonObject.getJSONObject("data");
                JSONArray array = jsonObject.getJSONArray("comments");
                Log.i(TAG, "onFinished: array"+array);
                for (int i = 0; i <array.length() ; i++) {
                    Gson gson = new Gson();
                    comment = gson.fromJson(array.get(i).toString(),feed_comment_response.class);
                    Log.i(TAG, "onFinished: array.getString(i) @i "+i+","+array.getString(i));
                    Log.i(TAG, "onFinished: comment"+comment.shop_feed_comment);
                    feed_comment_list.add(comment);
                }
                myPreApp.setCommentList(feed_comment_list);
                Log.i(TAG, "onFinished:feed_comment_list "+feed_comment_list.get(1).shop_feed_comment);
                myFeed.startActivity(NextActivity);
            }
        });
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        if (image.isEmpty()){
            return 0;
        }
        return image.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view.equals(obj);
    }
}