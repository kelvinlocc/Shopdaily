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

import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/22/2016.
 */
public class QA_List_adapter extends BaseAdapter implements AbsListView.OnScrollListener {
    String[] Question;
    String[] Answer;
    Context context;
    int[] imageId;
    static String TAG = "QA_List_adapter ";

    int Counter = 0;
    private static LayoutInflater inflater = null;

    public QA_List_adapter(Context context2, String[] Q, String[] A) {//
        // TODO Auto-generated constructor stub
        Question = Q;
        Answer = A;
        context = context2;//
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Question.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    public class Holder {
        TextView question;
        TextView answer;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Counter++;


        Log.i("check_", " at position: " + position);
        Log.i(TAG, "getView: Question.length: "+Question.length);
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.qa_row, null);
        holder.question = (TextView) rowView.findViewById(R.id.question);
        holder.question.setText("my question @"+position);
        holder.answer = (TextView)rowView.findViewById(R.id.answer);



//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Toast.makeText(context, "You Clicked " + Question[position], Toast.LENGTH_LONG).show();
//            }
//        });

        return rowView;
    }

}