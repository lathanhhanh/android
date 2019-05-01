package com.lathanhhanh.binhlieuapp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lathanhhanh.binhlieuapp.fragment.XeKhachFragment;

public class XeKhachAdapter extends FragmentStatePagerAdapter {
    public static  final int NUMBER_PAGERS = 6;
    public static String tabTitle[] = new String[] {"Tất Cả", "Hà Nội", "Bãi Cháy", "Móng Cái", "Uông Bí", "Thái Bình"};

    public XeKhachAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int posotion) {
        switch (posotion){
            case 0:
                return XeKhachFragment.newInstance("all");
            case 1:
                return XeKhachFragment.newInstance("Hà Nội");
            case 2:
                return XeKhachFragment.newInstance("Bãi Cháy");
            case 3:
                return XeKhachFragment.newInstance("Móng Cái");
            case 4:
                return XeKhachFragment.newInstance("Uông Bí");
            case 5:
                return XeKhachFragment.newInstance("Thái Bình");
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUMBER_PAGERS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
