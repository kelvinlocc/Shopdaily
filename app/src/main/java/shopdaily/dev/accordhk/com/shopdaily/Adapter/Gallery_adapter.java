package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/24/2016.
 */
public class Gallery_adapter extends BaseAdapter {
    int counter=0;

    private Context mContext;

    public Gallery_adapter(Context c) {
        mContext = c;


    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {


            grid = inflater.inflate(R.layout.grid_single, null);
            final TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
            textView.setBackgroundResource(R.drawable.black_trans_circle_bg);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        counter++;
                        textView.setText(Integer.toString(counter));
                        textView.setBackgroundResource(R.drawable.green_circle_bg);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            });
            imageView.setImageResource(mThumbIds[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }

    // references to our images
    Integer[] mThumbIds = {
            R.drawable.category_01, R.drawable.category_02, R.drawable.category_03,
            R.drawable.category_04, R.drawable.category_05, R.drawable.category_06,
            R.drawable.category_07, R.drawable.category_08, R.drawable.category_09
    };

    String[] web = {
            "1",
            "2",
            "3",

            "4",
            "5",
            "6",

            "7",
            "8",
            "9"

    };
}