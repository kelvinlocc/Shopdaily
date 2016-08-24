package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/22/2016.
 */
public class Fragment_Favourite_feed_adapter extends BaseAdapter implements AbsListView.OnScrollListener {
    String[] result;
    Context context;
    int[] imageId;

    int Counter = 0;
    private static LayoutInflater inflater = null;

    public Fragment_Favourite_feed_adapter(Context context2, String[] prgmNameList, int[] prgmImages) {//
        // TODO Auto-generated constructor stub
        result = prgmNameList;
        context = context2;//
        imageId = prgmImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
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
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Counter++;


        Log.i("check_", result[position] + " added!");
        Log.i("check_", " at position: " + position);
        Holder holder = new Holder();
        View rowView;
        if (position == 0 || position== 2) {
            rowView = inflater.inflate(R.layout.date, null);
            holder.date = (TextView) rowView.findViewById(R.id.txt_date);
            holder.date.setText(result[position]);
        }
        else
        {
            rowView = inflater.inflate(R.layout.fragment_favourite_feed_list, null);
            holder.tv = (TextView) rowView.findViewById(R.id.shop_name);
            holder.img = (ImageView) rowView.findViewById(R.id.user_icon);
            holder.tv.setText(result[position]);
            holder.img.setImageResource(imageId[position]);
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
                }
            });
        }
        return rowView;
    }

}