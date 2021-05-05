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
    int day2;
    Calendar mCal;



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        mCal = Calendar.getInstance();
        // year 변수에 저장된 값을 Year에, month변수에 저장된 값을 Month에, 1을 Day에 넣는다.
        mCal.set(Integer.parseInt(String.valueOf(year)), Integer.parseInt(String.valueOf(month)) - 1, 1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        int dayMax = mCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        day = position*7%42-dayNum+1;
        if(day<0)
            day=0;
        day2 = position*7-dayNum+1;
        int dm = (position*7+1-dayNum)/dayMax;
        System.out.println("dm :" + dm + "month : " + (month+dm));
        if (month == 12)
            year++;
        System.out.println(day + "dm :" +day2);
        return WeekCalendarFragment.newInstance(year, month+dm, day, day2);
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }

}
