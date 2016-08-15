package shopdaily.dev.accordhk.com.shopdaily.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_F;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Feed;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
//        mFragmentManager = fm;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }


    // replace a whole fragment in viewpager

//    private final class FirstPageListener implements
//            FirstPageFragmentListener {
//        public void onSwitchToNextFragment() {
//            mFragmentManager.beginTransaction().remove(mFragmentAtPos0)
//                    .commit();
//            if (mFragmentAtPos0 instanceof Fragment_Feed){
////                mFragmentAtPos0 = new Fragment_F(listener);
//            }else{ // Instance of NextFragment
////                mFragmentAtPos0 = new Fragment_Feed(listener);
//            }
//            notifyDataSetChanged();
//        }
//    }
//    private final FragmentManager mFragmentManager;
//    private Fragment mFragmentAtPos0;
//    FirstPageListener listener = new FirstPageListener();
//
    @Override
    public int getItemPosition(Object object)
    {
       return POSITION_NONE;
    }
}
