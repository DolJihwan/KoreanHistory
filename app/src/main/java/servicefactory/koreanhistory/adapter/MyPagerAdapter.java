package servicefactory.koreanhistory.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import servicefactory.koreanhistory.model.Category;

/**
 * Created by leejonghyeog on 2016. 8. 24..
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS;
    private ArrayList<Category> categoryList = null;
    private Fragment fragmentAdapter;
    private ArrayList<Fragment> fragmentArr;
    private Context context;
    public MyPagerAdapter(FragmentManager fragmentManager, int num_items, ArrayList<Category> categoryList, Context context) {
        super(fragmentManager);

        this.NUM_ITEMS = num_items;
        this.categoryList = categoryList;
        this.context = context;
        Log.i("categorytest", categoryList.toString());


    }


    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        for (int i = 0; i < NUM_ITEMS; i++) {
            if (position == i) {
                Log.i("실험 : ",categoryList.get(i).getCategoryMinor());
                fragmentAdapter= new FragmentAdapter().newInstance(i, categoryList.get(i).getCategoryMinor(),context);

                return fragmentAdapter;
            }
        }

        return null;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return categoryList.get(position).getCategoryMinor();
    }
}