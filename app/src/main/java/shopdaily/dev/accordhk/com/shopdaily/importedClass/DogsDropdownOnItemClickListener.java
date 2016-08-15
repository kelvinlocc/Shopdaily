package shopdaily.dev.accordhk.com.shopdaily.importedClass;

/**
 * Created by KelvinLo on 6/27/2016.
 */

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import shopdaily.dev.accordhk.com.shopdaily.Activity.MainActivity;
import shopdaily.dev.accordhk.com.shopdaily.Fragment.Fragment_Feed;

public class DogsDropdownOnItemClickListener implements OnItemClickListener {

    String TAG = "DogsDropdownOnItemClickListener.java";

    @Override
    public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {

        // get the context and main activity to access variables
        Context mContext = v.getContext();
//        MainActivity mainActivity = ((getClass()) mContext);

        // add some animation when a list item was clicked
        Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), android.R.anim.fade_in);
        fadeInAnimation.setDuration(10);
        v.startAnimation(fadeInAnimation);

        // dismiss the pop up
//        mContext.popupWindowDogs.dismiss();

        // get the text and set it as the button text
        String selectedItemText = ((TextView) v).getText().toString();
//        .buttonShowDropDown.setText(selectedItemText);

        // get the id
        String selectedItemTag = ((TextView) v).getTag().toString();
        Toast.makeText(mContext, "Dog ID is: " + selectedItemTag, Toast.LENGTH_SHORT).show();

    }

}