package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.feed_comment_response;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;

/**
 * Created by KelvinLo on 6/22/2016.
 */
public class commentListView_adapter extends BaseAdapter implements AbsListView.OnScrollListener {

    Context context;


    ArrayList<feed_comment_response> comment_list;
    feed_comment_response comment_data;
    MyPreApp myPreApp;
    public static String TAG = "commentListView_adapter";

    private static LayoutInflater inflater = null;

    public commentListView_adapter(Context context2, ArrayList<feed_comment_response> list) {
        // TODO Auto-generated constructor stub
        comment_list = list;

//        myDataModel = dataModel;
        context = context2;//

        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
//        return myDataModel.getUser_name_list().length;
        return comment_list.size();
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

        ImageView imageView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        Holder holder = new Holder();
        View view;
        view = inflater.inflate(R.layout.feed_comment_list, null);
        comment_data = comment_list.get(position);
        myPreApp = new MyPreApp();


        TextView userName = (TextView) view.findViewById(R.id.user_name);
//        userName.setText(myDataModel.getUser_name_list()[position]);
        userName.setText(comment_data.member_nick_name);


        TextView userComment = (TextView) view.findViewById(R.id.comment);
        userComment.setText(comment_data.shop_feed_comment);
//

        TextView comment_timeline = (TextView) view.findViewById(R.id.timePast);
//        calcTimePast(comment_data.create_datetime);
        comment_timeline.setText(calcTimePast(comment_data.create_datetime));
//
        ImageView userIcon = (ImageView) view.findViewById(R.id.feed_image);
//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),userIconList.get(position));
//        bitmap = getCroppedBitmap(bitmap);
//
//        userIcon.setImageResource(R.drawable.react_1);
        Bitmap bitmap = getCroppedBitmap(myPreApp.getBitmapFromURL(comment_data.member_profile_image));
        userIcon.setImageBitmap(bitmap);

        return view;
    }

    public String calcTimePast(String dateTime) {
        Log.i(TAG, "calcTimePast: ");
        Log.i(TAG, "calcTimePast: dateTime" + dateTime);
        String wholeString = dateTime;
        String year;
        String month;
        String day;
        String hour;
        String minute;
        String second;

        year = wholeString.split("\\-")[0];
        month = wholeString.split("\\-")[1];
        day = wholeString.split("\\-")[2].split(" ")[0];

        hour = wholeString.split(" ")[1].split(":")[0];
        minute = wholeString.split(":")[1].split(":")[0];
        second = wholeString.split(":")[2];

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year_c = sdf.format(new Date());
        sdf = new SimpleDateFormat("MM");
        String month_c = sdf.format(new Date());
        sdf = new SimpleDateFormat("dd");
        String day_c = sdf.format(new Date());
        sdf = new SimpleDateFormat("HH");
        String hour_c = sdf.format(new Date());
        sdf = new SimpleDateFormat("mm");
        String minute_c = sdf.format(new Date());
        sdf = new SimpleDateFormat("ss");
        String second_c = sdf.format(new Date());

        Log.i(TAG, "calcTimePast: " + year + "," +
                month + "," +
                day + "," +
                hour + "," +
                minute + "," +
                second + ",");
        Log.i(TAG, "calcTimePast: current time: " + year_c + "," +
                month_c + "," +
                day_c + "," +
                hour_c + "," +
                minute_c + "," +
                second_c + ",");

//        if (year.equals(year_c)&&month.equals(month_c)){
//            if (day.equals(day_c)){
//
//            }else {
//                return Integer.toString(Integer.parseInt(day_c) - Integer.parseInt(day)) +"days";
//            }
//        }

        if (year.equals(year_c)) {

            if (month.equals(month_c)) {
                if (day.equals(day_c)) {
                    if (hour.equals(hour_c)) {
                        if (minute.equals(minute_c)) {
                            return "just now";
                        } else {
                            return Integer.toString(Integer.parseInt(minute_c) - Integer.parseInt(minute)) + "minutes";
                        }
                    } else {
                        return Integer.toString(Integer.parseInt(hour_c) - Integer.parseInt(hour)) + "hours";
                    }
                } else {
                    return Integer.toString(Integer.parseInt(day_c) - Integer.parseInt(day)) + "days";
                }
            } else {
                return Integer.toString(Integer.parseInt(month_c) - Integer.parseInt(month)) + "months";
            }
        } else {
            return Integer.toString(Integer.parseInt(year_c) - Integer.parseInt(year)) + "years";
        }

    }

    // crop the icon into circle:
    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
    }

}