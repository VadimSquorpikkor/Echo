package com.squorpikkor.echo_2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int[] TAB_TITLES = new int[]{R.string.gamma, R.string.high_dose, R.string.neutron, R.string.btdu};
    private final Context mContext;
    FragmentManager fm;
    Fragment[] fragments;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fm = fm;
        mContext = context;
        fragments = new Fragment[]{
                GammaParamFragment.newInstance(),
                HighParamFragment.newInstance(),
                NeutronParamFragment.newInstance(),
                BtduParamFragment.newInstance()
        };
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}