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
public class filter_item_list_Adapter extends BaseAdapter implements AbsListView.OnScrollListener {
    Context context;

    ViewHolder holder;
    int Counter = 0;
    String item_list [];
    ArrayList<String> shop_name;
    ArrayList<Integer> shop_icon;

    filter_item_list_Adapter myadpter;
    private static LayoutInflater inflater = null;

    public filter_item_list_Adapter(Context context2, String[]data ) {//
        item_list = data;
        context = context2;//
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 5;
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


        view = inflater.inflate(R.layout.filter_item_list, null);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(item_list[position]);

        return view;
    }


}