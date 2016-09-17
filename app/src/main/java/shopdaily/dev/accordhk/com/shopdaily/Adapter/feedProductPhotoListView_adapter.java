package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.MyPreApp;

/**
 * Created by KelvinLo on 6/22/2016.
 */
public class feedProductPhotoListView_adapter extends BaseAdapter implements AbsListView.OnScrollListener{

    Context context;

    ArrayList<String> imageArray;
    MyPreApp myPreApp;
    private static LayoutInflater inflater=null;
    public feedProductPhotoListView_adapter(Context context2, ArrayList<String> image_data) {//
        // TODO Auto-generated constructor stub
        imageArray = image_data;
        context=context2;//
        myPreApp = new MyPreApp();

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imageArray.size();
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
        view = inflater.inflate(R.layout.feed_product_photo_entry, null);
        holder.imageView = (ImageView) view.findViewById(R.id.product_photo_entry);
//        holder.imageView.setImageResource(Photo[position]);
        holder.imageView.setImageBitmap(myPreApp.getBitmapFromURL(imageArray.get(position)));
        return view;
    }

}