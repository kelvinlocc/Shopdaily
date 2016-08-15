package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/22/2016.
 */
public class feedProductPhotoListView_adapter extends BaseAdapter implements AbsListView.OnScrollListener{

    Context context;
    int [] Photo;
    private static LayoutInflater inflater=null;
    public feedProductPhotoListView_adapter(Context context2, int [] photo) {//
        // TODO Auto-generated constructor stub
        Photo = photo;
        context=context2;//

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Photo.length;
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
        holder.imageView.setImageResource(Photo[position]);
        return view;
    }

}