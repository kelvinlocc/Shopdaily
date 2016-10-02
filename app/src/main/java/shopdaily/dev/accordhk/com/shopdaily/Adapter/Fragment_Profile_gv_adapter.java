package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.shop_feed;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;

/**
 * Created by KelvinLo on 6/17/2016.
 */
public class Fragment_Profile_gv_adapter extends BaseAdapter {
    private Context mContext;
    ArrayList<shop_feed> my_feed_list;
    shop_feed feed;
    MyPreApp myPreApp;

    public Fragment_Profile_gv_adapter(Context c) {
        mContext = c;
        myPreApp = new MyPreApp();
        my_feed_list = myPreApp.getMyFeed_list();
    }

    public int getCount() {
        return my_feed_list.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public class Holder {
        TextView total_like;
        TextView total_comment;
        ImageView imageView;
    }

    Holder holder;

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        feed = my_feed_list.get(position);
        if (convertView == null) {
            holder = new Holder();

            view = inflater.inflate(R.layout.category_grid_single, null);
            holder.total_like = (TextView) view.findViewById(R.id.total_like);
            holder.total_comment = (TextView) view.findViewById(R.id.total_comment);
            holder.imageView = (ImageView) view.findViewById(R.id.grid_image);
            holder.total_like.setText(feed.total_count_like);
            holder.total_comment.setText(feed.total_count_comment);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {


//                        textView.setBackgroundResource(R.drawable.green_circle_bg);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            });
            if (feed.image_list.size()>0){
                holder.imageView.setImageBitmap(myPreApp.getBitmapFromURL(feed.image_list.get(0)));
            }
            else {
            holder.imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.not_found));}
        } else {
            view = (View) convertView;
        }

        return view;
    }

    private Integer[] mThumbIds = {

//            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.category_01, R.drawable.category_02, R.drawable.category_03,
            R.drawable.category_04, R.drawable.category_05, R.drawable.category_06,
            R.drawable.category_07, R.drawable.category_08, R.drawable.category_09
    };
}