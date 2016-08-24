package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.util.ArrayList;
import java.util.List;

import shopdaily.dev.accordhk.com.shopdaily.DataModel.FeedDataModel;
import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/22/2016.
 */
public class commentListView_adapter extends BaseAdapter implements AbsListView.OnScrollListener{

    Context context;
    FeedDataModel myDataModel;

    ArrayList<String> userNameList;
    ArrayList<Integer> userIconList;
    ArrayList<String> commentList;
    ArrayList<Integer> timeList;

    public static String TAG = "commentListView_adapter";

    private static LayoutInflater inflater=null;
    public commentListView_adapter(Context context2, ArrayList<String> usernameData,ArrayList<Integer> userIconData, ArrayList<String> commentData,ArrayList<Integer> timeData  ) {
        // TODO Auto-generated constructor stub
        userNameList = usernameData;
        userIconList = userIconData;
        commentList = commentData;
        timeList = timeData;

//        myDataModel = dataModel;
        context=context2;//

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
//        return myDataModel.getUser_name_list().length;
        return userNameList.size();
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

    public class Holder
    {

        ImageView imageView;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        Holder holder=new Holder();
        View view;
        view = inflater.inflate(R.layout.feed_comment_list, null);

        TextView userName = (TextView) view.findViewById(R.id.user_name);
//        userName.setText(myDataModel.getUser_name_list()[position]);
        userName.setText(userNameList.get(position));
        Log.i("check_","userNameList.get(position): "+userNameList.get(position));

        TextView userComment = (TextView) view.findViewById(R.id.comment);
        userComment.setText(commentList.get(position));
//

        TextView comment_timeline = (TextView) view.findViewById(R.id.timePast);
        comment_timeline.setText(Integer.toString(timeList.get(position))+ " min");
//
        ImageView userIcon = (ImageView) view.findViewById(R.id.user_icon);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),userIconList.get(position));
        bitmap = getCroppedBitmap(bitmap);
//
        userIcon.setImageBitmap(bitmap);

        return view;
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