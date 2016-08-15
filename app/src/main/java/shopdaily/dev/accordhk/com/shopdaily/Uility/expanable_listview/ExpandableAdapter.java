package shopdaily.dev.accordhk.com.shopdaily.Uility.expanable_listview;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import shopdaily.dev.accordhk.com.shopdaily.Adapter.filter_item_list_Adapter;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.expanable_listview.Item;

/**
 * Created by kyle.jablonski on 10/6/15.
 */
public class ExpandableAdapter extends BaseAdapter {


    List<Item> items;
    Context context;


    public class viewHolder {
        AppCompatTextView mTvTitle;
        AppCompatTextView mTvDescription;
        FrameLayout mFlWrapper;
        ImageView mIvArrow;
        ListView myListView;
        RelativeLayout relativeLayout;
    }

    public ExpandableAdapter(Context context, List<Item> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final viewHolder holder;


        if (convertView == null) {
            holder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
            holder.mFlWrapper = (FrameLayout) convertView.findViewById(R.id.fl_wrapper);
            holder.mTvTitle = (AppCompatTextView) convertView.findViewById(R.id.tv_title);
            holder.mIvArrow = (ImageView) convertView.findViewById(R.id.iv_arrow);
            holder.myListView = (ListView) convertView.findViewById(R.id.filter_item_list);
            holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.rl_title_wrapper);
            convertView.setTag(holder);
        } else {

            holder = (viewHolder) convertView.getTag();
        }

        // Update the View
        final Item item = items.get(position);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.isExpanded) {
                    holder.mFlWrapper.setVisibility(View.GONE);
                    holder.mIvArrow.setRotation(0f);
                    item.setIsExpanded(false);

                } else {
                    holder.mFlWrapper.setVisibility(View.VISIBLE);
                    holder.mIvArrow.setRotation(180f);
                    item.setIsExpanded(true);
                }

            }
        });
//        if(item.isExpanded){
//            holder.mFlWrapper.setVisibility(View.VISIBLE);
//            holder.mIvArrow.setRotation(180f);
//        }else{
        holder.mFlWrapper.setVisibility(View.GONE);
        holder.mIvArrow.setRotation(0f);
//        }

//        holder.mTvTitle.setText(item.title);
        holder.mTvTitle.setText(item.getFilter_item()[position]);
//        String [] strings = item.getFilter_itemByName(item.getFilter_item()[position]);
        String[] strings = item.getFilter_item_list();
        if (strings != null) {
            for (int i = 0; i < strings.length; i++) {
                Log.i("expandableAdapter", "string:: " + strings[i]);
            }
        }
        filter_item_list_Adapter adapter = new filter_item_list_Adapter(context, strings);

        holder.myListView.setAdapter(adapter);

        // return the view
        return convertView;
    }


}
