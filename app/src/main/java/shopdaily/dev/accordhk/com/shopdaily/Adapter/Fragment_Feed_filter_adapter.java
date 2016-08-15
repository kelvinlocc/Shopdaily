package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import shopdaily.dev.accordhk.com.shopdaily.DataModel.filter_DataModel;
import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/17/2016.
 */
public class Fragment_Feed_filter_adapter extends BaseAdapter implements View.OnClickListener, AbsListView.OnScrollListener {
    static String TAG = "Fragment_Feed_filter_adapter ";
    private Context mContext;
    private boolean gate = true;
    ArrayList<filter_DataModel> filter_dataModelArrayList;
    filter_DataModel mData;
    viewHolder holder;

    private ListView listView;


    public Fragment_Feed_filter_adapter(Context c, ArrayList<filter_DataModel> data) {
        filter_dataModelArrayList = data;
        mContext = c;
    }

    public int getCount() {
        return filter_dataModelArrayList.size();
//        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    public class viewHolder {
        TextView top_left_category;
        TextView top_right_category;
        LinearLayout layout_top;
        TextView item00;
        TextView item01;
        TextView item02;
        TextView item03;
        TextView item04;
        TextView item05;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);;
        View view;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        holder = new viewHolder();
//        if (convertView == null) {
        view = inflater.inflate(R.layout.fragment_feed_filter_single, null);

//        Fragment_Profile_lv_adapter adapter =
//                new Fragment_Profile_lv_adapter();
////        list_calllog=(ListView)view.findViewById(R.id.list);
//        listView =  widget.findViewById(R.id.listView);
//        listView.setAdapter(adapter);
//        listView.setOnScrollListener(adapter);

        holder.top_left_category = (TextView) view.findViewById(R.id.top_left_cateory);
        holder.top_right_category = (TextView) view.findViewById(R.id.top_right_category);
        holder.layout_top = (LinearLayout) view.findViewById(R.id.layout_top);
        holder.item00 = (TextView) view.findViewById(R.id.item00);
        holder.item01 = (TextView) view.findViewById(R.id.item01);
        holder.item02 = (TextView) view.findViewById(R.id.item02);
        holder.item03 = (TextView) view.findViewById(R.id.item03);
        holder.item04 = (TextView) view.findViewById(R.id.item04);
        holder.item05 = (TextView) view.findViewById(R.id.item05);

//        } else {
//            view = convertView;
//        }

        if (filter_dataModelArrayList.size() <= 0) {
            return view;
        } else {
            mData = filter_dataModelArrayList.get(position);

            if (mData.getTop_bar_category() != null) {
                Log.i("check_", "mData.getTop_bar_category(): " + mData.getTop_bar_category());
                holder.top_left_category.setText(mData.getTop_bar_category());
                holder.top_right_category.setText("Any "+mData.getTop_bar_category());
            }
            ViewGroup.LayoutParams params = holder.layout_top.getLayoutParams();
            // Changes the height and width to the specified *pixels*
            if (mData.getIsExpanded()) {
                params.height = 400;

            } else {
                params.height = 100;
            }
            holder.layout_top.setLayoutParams(params);
//            String[] list =
            holder.item00.setText("Any " + mData.getTop_bar_category());
            for (int i = 0; i < mData.getItemList().length; i++) {
                Log.i(TAG, "itemList: " + mData.getItemList()[i]);

            }
            holder.item01.setText(mData.getItemList()[0]);
            holder.item02.setText(mData.getItemList()[1]);
            holder.item03.setText(mData.getItemList()[2]);
            holder.item04.setText(mData.getItemList()[3]);
            holder.item05.setText(mData.getItemList()[4]);



//            holder.top_left_category.setOnClickListener(new OnItemClickListener(position));
            holder.layout_top.setOnClickListener(new OnItemClickListener(position));
        }


//        final LinearLayout top_layout = (LinearLayout) view.findViewById(R.id.top_layout);
//        final LinearLayout Layout_expand = (LinearLayout) view.findViewById(R.id.layout_expand);
//        final ImageView Img_arrow = (ImageView) view.findViewById(R.id.img_arrow);

//

//        ViewGroup.LayoutParams params = Layout_top.getLayoutParams();
//        params.height = params.height-300;
//        Layout_top.setLayoutParams(params);

//        Top_bar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//
//                    if (gate) {
//                        Log.i("check_", "top expand");
//                        Layout_expand.setVisibility(View.VISIBLE);
////                        Layout_top.setMinimumHeight(400);
//                        Img_arrow.setRotation(0f);
//
//                        ViewGroup.LayoutParams params = Layout_top.getLayoutParams();
//                        // Changes the height and width to the specified *pixels*
//                        //params.height = 400;
//                        params.height = params.height + 400;
//                        Layout_top.setLayoutParams(params);
//
//                        gate = !gate;
//                    } else {
//                        Log.i("check_", "top is restored");
//                        Layout_expand.setVisibility(View.INVISIBLE);
//
//                        Img_arrow.setRotation(180f);
//
//                        ViewGroup.LayoutParams params = Layout_top.getLayoutParams();
//                        // Changes the height and width to the specified *pixels*
//                        //params.height = 400;
//                        params.height = params.height - 400;
//                        Layout_top.setLayoutParams(params);
//                        gate = !gate;
//                    }
//
////                    bottom_ex.setVisibility(View.VISIBLE);
////                        textView.setBackgroundResource(R.drawable.green_circle_bg);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//        });


        return view;
    }


    private class OnItemClickListener extends FragmentActivity implements View.OnClickListener {


        private int mPosition;

        OnItemClickListener(int position) {
            mPosition = position;
            // correctly setted!
            Log.i(TAG, "OnItemClickListener R postion: " + position);
        }

        public void onClick(View v) {

            if (v.getId() == R.id.tv_react) {
                Log.i(TAG, "react clicked in feed_update: " + mPosition);
            }


            if (v.getId() == R.id.layout_top) {
                Log.i(TAG, "R.id.top_layout: clicked @" + mPosition);
                mData = filter_dataModelArrayList.get(mPosition);
                mData.setExpand(!mData.getIsExpanded());
                notifyDataSetChanged();


            }


        }
    }
}