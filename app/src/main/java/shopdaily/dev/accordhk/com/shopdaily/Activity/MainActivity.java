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
import android.widget.Toast;

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

        fragmentManager = getSupportFragmentManager();
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);



        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        if (tabLayout!=null) {
            tabLayout.setupWithViewPager(viewPager);
            setupTabIcons();
        }

    }


    private void setupTabIcons() {
        for (int i=0;i<tabLayout.getTabCount();i++){
            int resourceId = tabIcons[i];
            tabLayout.getTabAt(i).setIcon(resourceId);
        }
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

        return super.onOptionsItemSelected(item);
    }


}
