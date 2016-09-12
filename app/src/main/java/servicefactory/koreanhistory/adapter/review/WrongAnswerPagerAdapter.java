package servicefactory.koreanhistory.adapter.review;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import servicefactory.koreanhistory.fragment.CategoryListFragment;
import servicefactory.koreanhistory.fragment.DateListFragment;

public class WrongAnswerPagerAdapter extends FragmentPagerAdapter {

    private int pagerCount = 2;

    Context ctx;

    public WrongAnswerPagerAdapter(FragmentManager fm, Context ctx) {
        super(fm);
        this.ctx = ctx;
    }

    @Override
    public Fragment getItem(int i) {

        if (i == 0) {
            return new DateListFragment();
        } else {
            return new CategoryListFragment();
        }
    }

    @Override
    public int getCount() {
        return pagerCount;
    }

}