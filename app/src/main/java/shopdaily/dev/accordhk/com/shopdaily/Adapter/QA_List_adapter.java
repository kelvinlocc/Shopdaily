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

import java.util.ArrayList;

import shopdaily.dev.accordhk.com.shopdaily.API_DataModel.QA_Response;
import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/22/2016.
 */
public class QA_List_adapter extends BaseAdapter implements AbsListView.OnScrollListener {

    Context context;
    static String TAG = "QA_List_adapter ";
    ArrayList<QA_Response> qa_list;

    private static LayoutInflater inflater = null;

    public QA_List_adapter(Context context2, ArrayList<QA_Response> list) {//
        // TODO Auto-generated constructor stub


        context = context2;//
        qa_list = list;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return qa_list.size();
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

        Log.i(TAG, "getView: ");
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.qa_row, null);
        holder.question = (TextView) rowView.findViewById(R.id.question);
        holder.question.setText(qa_list.get(position).question_detail);
        holder.answer = (TextView) rowView.findViewById(R.id.answer);
        holder.answer.setText(qa_list.get(position).answer_detail);

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