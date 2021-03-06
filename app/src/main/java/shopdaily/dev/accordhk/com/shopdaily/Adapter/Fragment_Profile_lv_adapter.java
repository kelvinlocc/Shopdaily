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

import java.util.ArrayList;
import java.util.Objects;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.Login_Response_Data;
import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.myTimeline_response;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;

/**
 * Created by KelvinLo on 6/22/2016.
 */
public class Fragment_Profile_lv_adapter extends BaseAdapter implements AbsListView.OnScrollListener {
    String[] result;
    Context context;
    int[] imageId;

    MyPreApp myPreApp;
    ArrayList<myTimeline_response> timelineList;
    private static LayoutInflater inflater = null;

    public Fragment_Profile_lv_adapter(Context context2, String[] prgmNameList, int[] prgmImages) {//
        // TODO Auto-generated constructor stub
        result = prgmNameList;
        context = context2;//
        myPreApp = new MyPreApp();
        timelineList = myPreApp.getMyTimelineList();
        imageId = prgmImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return timelineList.size();
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
        TextView userName;
        ImageView img;
        TextView action;
        TextView timePast;
    }

    myTimeline_response timeline;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        timeline = timelineList.get(position);
        Login_Response_Data data = myPreApp.getLoginResponse().data;

        Log.i("check_", " at position: " + position);
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.fragment_profile_lv_entry, null);
        holder.userName = (TextView) rowView.findViewById(R.id.user_name);
        holder.img = (ImageView) rowView.findViewById(R.id.feed_image);
        holder.action = (TextView) rowView.findViewById(R.id.action);
        holder.timePast = (TextView) rowView.findViewById(R.id.time_past);


        if (Objects.equals(data.member_id, timeline.member_id)) {
            holder.userName.setText("you ");
            holder.action.setText(actionTake(timeline.action_type) + " your photo.");
        } else {
            holder.userName.setText(timeline.member_id);
            holder.action.setText(actionTake(timeline.action_type) + " this photo.");
        }

        holder.timePast.setText(myPreApp.calcTimePast(timeline.create_datetime)+" ago");

        holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

    public String actionTake(String data) {
        if (Objects.equals(data, "1")) {
            return "like";
        }
        if (Objects.equals(data, "2")) return "cool";

        if (Objects.equals(data, "3")) return "love";

        if (Objects.equals(data, "4")) return "unlike";

        if (Objects.equals(data, "5")) return "viewed";

        else return "shared";

    }

}