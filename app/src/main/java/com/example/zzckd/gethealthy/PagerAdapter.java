package com.example.zzckd.gethealthy;




import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.zzckd.gethealthy.fragment.FragmentDecorate;
import com.example.zzckd.gethealthy.fragment.FragmentMain;
import com.example.zzckd.gethealthy.fragment.FragmentRecord;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int pageCount;

    public PagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.pageCount = pageCount;

    }
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentDecorate fragmentDecorate= new FragmentDecorate();
                return fragmentDecorate;
            case 1:
                FragmentMain fragmentMain= new FragmentMain();
                return fragmentMain;
            case 2:
                FragmentRecord fragmentRecord= new FragmentRecord();
                return fragmentRecord;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return pageCount;
    }

}