package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import javax.sql.DataSource;

import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.search_HashTag.SuggestionSimpleCursorAdapter;
import shopdaily.dev.accordhk.com.shopdaily.Uility.search_HashTag.SuggestionsDatabase;


/**
 * Created by iFocus on 27-10-2015.
 */

public class Fragment_Category_i extends Fragment  {
    private SuggestionsDatabase database;


    static String TAG = "Fragment_Category ";
    String keyword = "null";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_category_front_i, container, false);


//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_category_panel, container, false);
//
//
//
        //toolbar:
        database = new SuggestionsDatabase(getActivity());



        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new Fragment_category_panel()).commit();

        final SearchView mySearchView =  (SearchView) view.findViewById(R.id.mySearchView);

        final TextView btn_reset = (TextView) view.findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_reset.getText() == "Reset") {
                    Fragment_category_panel new_fragment = new Fragment_category_panel();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragment_container, new_fragment).commit();
                    btn_reset.setText("Search");
                }
            }
        });



        mySearchView.setQueryHint("Search feed/Shops/Hashtag/keyword...");


        mySearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if (btn_reset.getText() == "Reset") {
                    Fragment_category_panel new_fragment = new Fragment_category_panel();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragment_container, new_fragment).commit();
                    btn_reset.setText("Search");
                }
                return false;
            }
        });

        mySearchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                SQLiteCursor cursor = (SQLiteCursor) mySearchView.getSuggestionsAdapter().getItem(position);
                int indexColumnSuggestion = cursor.getColumnIndex( SuggestionsDatabase.FIELD_SUGGESTION);

                mySearchView.setQuery(cursor.getString(indexColumnSuggestion), false);
                return true;
            }
        });

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                long result = database.insertSuggestion(query);
                return result != -1;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                btn_reset.setText("Reset");

                // show the possible list:
                if (keyword != "null") {
                    newText = keyword;
                }
                Log.i(TAG, "keyword: " + keyword);
                Bundle data = new Bundle();
                data.putBoolean("setDisable", true);
                data.putString("text", newText);

                Fragment_Feed new_fragment = new Fragment_Feed();
                new_fragment.setArguments(data);
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new_fragment).commit();
                Log.i(TAG, "onQueryTextChange query: " + newText);



                // show suggestion:
                Cursor cursor = database.getSuggestions(newText);
                if(cursor.getCount() != 0)
                {
                    String[] columns = new String[] {SuggestionsDatabase.FIELD_SUGGESTION };
                    int[] columnTextId = new int[] { android.R.id.text1};

                    SuggestionSimpleCursorAdapter simple = new SuggestionSimpleCursorAdapter(getActivity().getBaseContext(),
                            android.R.layout.simple_list_item_1, cursor,
                            columns , columnTextId
                            , 0);

                    mySearchView.setSuggestionsAdapter(simple);
                    return true;
                }
                else
                {
                    return false;
                }
            }

            }
        );





        setHasOptionsMenu(true);

        return view;


//        return inflater.inflate(R.layout.fragment_category_panel, container, false);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);

        super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == com.miguelcatalan.materialsearchview.MaterialSearchView.REQUEST_VOICE && resultCode == Activity.RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {

                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void onStart() {
        super.onStart();
    }


    // needed to destroy the activity!
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


}
