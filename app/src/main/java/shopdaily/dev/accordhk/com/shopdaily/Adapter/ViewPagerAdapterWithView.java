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

import java.util.ArrayList;
import java.util.List;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.shop_re;
import shopdaily.dev.accordhk.com.shopdaily.Activity.Shop_details_feed;
import shopdaily.dev.accordhk.com.shopdaily.DataModel.FeedDataModel;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Feed;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;
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

    ArrayList<String> image;
    shop_re shop_data;

    public ViewPagerAdapterWithView(Fragment_Feed feed, shop_re data) {
        super();
        myFeed = feed;
        shop_data = data;
        image = shop_data.imageArray;
        inflater = (LayoutInflater) feed.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
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
//            imageArray = new ArrayList<>();
            Log.i(TAG, "instantiateItem: @position: "+position + image.get(position));
//            for (int i = 0; i < image.size(); i++) {
//                imageArray.add(myPreApp.getBitmapFromURL(image.get(0)));
//            }
            product_photo.setImageBitmap(myPreApp.getBitmapFromURL(image.get(position)));
        }
        else {
            product_photo.setImageResource(R.drawable.category_06);
        }




//        product_photo.setImageResource(feedDataModel.getPhoto_list()[position]);

//        feedDataModel.getMyPhoto_list().recycle();
        LinearLayout main_viewPager = (LinearLayout) layout.findViewById(R.id.main_viewPager);
        main_viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i("TAG", "feedDataModel: "+feedDataModel.getShopName());
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data_model",feedDataModel);

//                Intent NextActivity = new Intent(myFeed.getActivity(), Shop_details_feed.class);
                Intent NextActivity = new Intent(myFeed.getActivity(), Shop_details_feed.class);
//                NextActivity.putExtra("data",sh);
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