package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import shopdaily.dev.accordhk.com.shopdaily.Activity.Shop_details_feed;
import shopdaily.dev.accordhk.com.shopdaily.Activity.Shop_details_feed_2;
import shopdaily.dev.accordhk.com.shopdaily.DataModel.FeedDataModel;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Feed;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.ViewPagerItem;

/**
 * Created by flashok on 9.8.14.
 */
public class ViewPagerAdapterWithView extends PagerAdapter {
    public static String TAG = "ViewPagerAdapterWithView ";
    private ArrayList<ViewPagerItem> pagerItems;
    private LayoutInflater inflater;
    private Context context;
    private Fragment_Feed myFeed;
    FeedDataModel feedDataModel;

    public ViewPagerAdapterWithView(Fragment_Feed feed, FeedDataModel data) {
        super();
        myFeed = feed;
        feedDataModel = data;
        inflater = (LayoutInflater) feed.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout layout = null;
        layout = (LinearLayout) inflater.inflate(R.layout.item_viewpager, null);
        ImageView product_photo = (ImageView) layout.findViewById(R.id.product_photo);
//        product_photo.setImageResource(feedDataModel.getPhoto_list()[position]);
        Log.i(TAG,"feedDataModel.getPhoto_list()[position]: "+feedDataModel.getPhoto_list()[position]);
        product_photo.setImageResource(feedDataModel.getPhoto_list()[position]);

//        feedDataModel.getMyPhoto_list().recycle();
        LinearLayout main_viewPager = (LinearLayout) layout.findViewById(R.id.main_viewPager);
        main_viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "feedDataModel: "+feedDataModel.getShopName());
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data_model",feedDataModel);

//                Intent NextActivity = new Intent(myFeed.getActivity(), Shop_details_feed.class);
                Intent NextActivity = new Intent(myFeed.getActivity(), Shop_details_feed.class);
                NextActivity.putExtra("data",feedDataModel);
//                NextActivity.putExtra("data",bundle);
                myFeed.startActivity(NextActivity);
            }
        });




        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view.equals(obj);
    }
}