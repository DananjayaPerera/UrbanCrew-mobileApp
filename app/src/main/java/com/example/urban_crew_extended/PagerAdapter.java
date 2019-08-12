package com.example.urban_crew_extended;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int NoOfTabs;

    public PagerAdapter(FragmentManager fm, int NoOfTabs){

        super(fm);
        this.NoOfTabs = NoOfTabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                Hatchback hatchback = new Hatchback();
                return hatchback;

            case 1:
                Sedan sedan = new Sedan();
                return sedan;

            case 2:
                SUV suv = new SUV();
                return suv;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return NoOfTabs;
    }
}
