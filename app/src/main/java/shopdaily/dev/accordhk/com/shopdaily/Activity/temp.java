package shopdaily.dev.accordhk.com.shopdaily.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import shopdaily.dev.accordhk.com.shopdaily.Adapter.ViewPagerAdapter;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Category;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Profile_shop_owner;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Favourite;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Location;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Profile;
import shopdaily.dev.accordhk.com.shopdaily.R;

/**
 * Created by KelvinLo on 6/8/2016.
 */
public class temp extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.menu_feed,
            R.drawable.menu_category,
            R.drawable.menu_location,
            R.drawable.menu_faviourite,
            R.drawable.menu_profile   //done
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


//        setTitle("Shop_Response Daily");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);  //done

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Fragment_Profile_shop_owner(), "Feed"); ////testing////
        adapter.addFragment(new Fragment_Category(), "Category");
        adapter.addFragment(new Fragment_Location(), "Location");
        adapter.addFragment(new Fragment_Favourite(), "Favourite");
        adapter.addFragment(new Fragment_Profile(), "Profile");
        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
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
