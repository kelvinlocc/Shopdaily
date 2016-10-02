package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/22/2016.
 */
public class Fragment_Favourite_shop_adapter extends BaseAdapter implements AbsListView.OnScrollListener {
    Context context;

    ViewHolder holder;
    int Counter = 0;

    ArrayList<String> shop_name;
    ArrayList<Integer> shop_icon;

    Fragment_Favourite_shop_adapter myadpter;
    private static LayoutInflater inflater = null;

    public Fragment_Favourite_shop_adapter(Context context2, ArrayList shop_name_object, ArrayList shop_icon_object, Fragment_Favourite_shop_adapter adapter) {//
        myadpter = adapter;
        shop_name=shop_name_object;
        context = context2;//
        shop_icon = shop_icon_object;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return shop_name.size();
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

    public class ViewHolder {
        TextView Shop_Name;
        ImageView shop_icon;
        ImageView Favour;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Counter++;
        holder = new ViewHolder();
        View view;


        view = inflater.inflate(R.layout.fragment_favourite_shop_list, null);

//            Log.i("check_", Question[position] + " added!");
        holder.Shop_Name = (TextView) view.findViewById(R.id.shop_name);
        holder.shop_icon = (ImageView) view.findViewById(R.id.feed_image);
        holder.Favour = (ImageView) view.findViewById(R.id.imgB_favour);
        holder.Favour.setColorFilter(0xff999999, PorterDuff.Mode.MULTIPLY);

        holder.Favour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You unfavoured " + shop_name.get(position), Toast.LENGTH_LONG).show();
//                shop_name.set(position,"test");
                shop_name.remove(position);
                shop_icon.remove(position);
                notifyDataSetChanged();

            }
        });
        holder.Shop_Name.setText(shop_name.get(position));

        holder.shop_icon.setImageResource(shop_icon.get(position));

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//        holder.Favour.setColorFilter(0xff999999, PorterDuff.Mode.MULTIPLY);

//                holder.Favour.setColorFilter(0xff00a99e, PorterDuff.Mode.MULTIPLY);
//
//                Toast.makeText(context, "You Clicked " + Question[position], Toast.LENGTH_LONG).show();
//            }
//        });

        return view;
    }


}