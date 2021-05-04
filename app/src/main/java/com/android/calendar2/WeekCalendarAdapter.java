package com.android.calendar2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Calendar;

public class WeekCalendarAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS=100;
    public WeekCalendarAdapter(@NonNull WeekViewFragment fragmentActivity) {
        super(fragmentActivity);
    }

    int year = Calendar.getInstance().get(Calendar.YEAR);
    int month = Calendar.getInstance().get(Calendar.MONTH)+1;
    int day;
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (month == 12)
            year++;
        day = position*7;
        return WeekCalendarFragment.newInstance(year, month, day);
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }

}
