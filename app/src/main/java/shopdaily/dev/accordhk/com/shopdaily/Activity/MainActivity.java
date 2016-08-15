package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import shopdaily.dev.accordhk.com.shopdaily.Adapter.ViewPagerAdapter;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Category_i;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_F;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Favourite;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Location;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Category;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Profile;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Feed;
import shopdaily.dev.accordhk.com.shopdaily.R;
import shopdaily.dev.accordhk.com.shopdaily.Uility.SingleShotLocationProvider;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.menu_feed,
            R.drawable.menu_category,
            R.drawable.menu_location,
            R.drawable.menu_faviourite,
            R.drawable.menu_profile   //done
    };
    public static FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_main);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        setTitle("Shop Daily");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);



        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        if (tabLayout!=null) {
            tabLayout.setupWithViewPager(viewPager);

            setupTabIcons();
        }

//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                tab.getIcon().setColorFilter(Color.parseColor("#00a99e"), PorterDuff.Mode.SRC_IN);
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                tab.getIcon().setColorFilter(Color.parseColor("#999999"), PorterDuff.Mode.SRC_IN);
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//        Log.i("check_","onCreateView"+getIntent().getExtras().get("x"));

    }


    private void setupTabIcons() {
        for (int i=0;i<tabLayout.getTabCount();i++){
            int resourceId = tabIcons[i];
            tabLayout.getTabAt(i).setIcon(resourceId);
        }
        /*
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);  //done
*/
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        String [] strings = getResources().getStringArray(R.array.tab_layout_menu);

        adapter.addFragment(new Fragment_Feed(), strings[0]); ////testing////
        adapter.addFragment(new Fragment_Category_i(), strings[1]);
        adapter.addFragment(new Fragment_Location(), strings[2]);
        adapter.addFragment(new Fragment_Favourite(), strings[3]);
        adapter.addFragment(new Fragment_Profile(), strings[4]);



        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

}
