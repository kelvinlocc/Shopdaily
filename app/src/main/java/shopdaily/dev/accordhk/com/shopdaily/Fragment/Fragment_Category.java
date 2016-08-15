package shopdaily.dev.accordhk.com.shopdaily.Fragment;

import android.app.Activity;
import android.content.Intent;
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
import android.view.ViewStub;
import android.widget.GridView;

import java.util.ArrayList;

import shopdaily.dev.accordhk.com.shopdaily.Adapter.Fragment_Category_Adapter;
import shopdaily.dev.accordhk.com.shopdaily.R;


/**
 * Created by iFocus on 27-10-2015.
 */

public class Fragment_Category extends Fragment {
    private com.miguelcatalan.materialsearchview.MaterialSearchView searchView;


    static String TAG = "Fragment_Category ";
    String keyword = "null";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_category_front, container, false);


//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_category_panel, container, false);
//
//
//
        //toolbar:
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new Fragment_category_panel()).commit();


        searchView = (com.miguelcatalan.materialsearchview.MaterialSearchView) view.findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        searchView.setEllipsize(true);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new com.miguelcatalan.materialsearchview.MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                View view = getView();

                Bundle data = new Bundle();
                data.putBoolean("setDisable", true);
                data.putString("text", query);
                Fragment_Feed new_fragment_feed = new Fragment_Feed();
                new_fragment_feed.setArguments(data);
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new_fragment_feed).commit();

                Log.i(TAG, "onQueryTextSubmit query: " + query);
                keyword = query;
                Snackbar.make(view.findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
                        .show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
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

                return false;
            }
        });

        searchView.setOnSearchViewListener(new com.miguelcatalan.materialsearchview.MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                Log.i(TAG, "onSearchViewClosed");
                //Do some magic
                if (keyword != "null") {
                    Log.i(TAG, "keyword: " + keyword);
                    Bundle data = new Bundle();
                    data.putBoolean("setDisable", true);
                    data.putString("text", keyword);

                    Fragment_Feed new_fragment_feed = new Fragment_Feed();
                    new_fragment_feed.setArguments(data);

                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragment_container, new_fragment_feed).commit();
                } else {
                    Fragment_Category new_fragment = new Fragment_Category();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new_fragment).commit();
                }


            }
        });

        setHasOptionsMenu(true);

        return view;


//        return inflater.inflate(R.layout.fragment_category_panel, container, false);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);


        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == com.miguelcatalan.materialsearchview.MaterialSearchView.REQUEST_VOICE && resultCode == Activity.RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
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
//        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();

//        if (mMap != null) {
//            MainActivity.fragmentManager.beginTransaction()
//                    .remove(MainActivity.fragmentManager.findFragmentById(R.id.map)).commit();
//            mMap = null;
//        }
    }

}
