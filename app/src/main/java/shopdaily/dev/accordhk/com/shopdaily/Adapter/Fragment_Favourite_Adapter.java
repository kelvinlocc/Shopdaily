package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/17/2016.
 */
public class Fragment_Favourite_Adapter extends BaseAdapter {
    private Context mContext;

    public Fragment_Favourite_Adapter(Context c) {
        mContext = c;
    }


    @Override
    public int getCount() {
        return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
//        if (convertView == null) {  // if it's not recycled, initialize some attributes
//            imageView = new ImageView(mContext);
////            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            imageView.setLayoutParams(new GridView.LayoutParams(230, 230));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//
        return convertView;
    }


}