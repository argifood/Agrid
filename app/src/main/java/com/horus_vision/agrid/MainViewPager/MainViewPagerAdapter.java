package com.horus_vision.agrid.MainViewPager;

import android.content.Context;

import com.horus_vision.agrid.DaoHelper;
import com.horus_vision.agrid.main_fragment_view.MainFragmentView;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager fragmentManager;

    private List<MainFragmentView> fragments = new ArrayList<>();

    private DaoHelper.UISkeleton uiSkeleton;

    public MainViewPagerAdapter(FragmentManager fm, DaoHelper.UISkeleton uiSkeleton) {
        super(fm);
        fragmentManager = fm;
        this.uiSkeleton = uiSkeleton;
    }

    public void SetupFragments(Context ctx) {
        for (String category: uiSkeleton.uiCategories) {
            MainFragmentView mainFragmentView = new MainFragmentView();
            mainFragmentView.setUiSkeleton(uiSkeleton);
            mainFragmentView.setCategory(category);
            fragments.add(mainFragmentView);
        }
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
