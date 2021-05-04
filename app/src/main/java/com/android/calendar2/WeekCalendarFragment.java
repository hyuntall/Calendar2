package com.android.calendar2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.w3c.dom.Text;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.WINDOW_SERVICE;

public class WeekCalendarFragment extends Fragment {
    Calendar mCal; // 캘린더 선언
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    int year;
    int month;
    int day;
    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;
    private int mParam3;
    public WeekCalendarFragment() {
        // Required empty public constructor
    }

    public static WeekCalendarFragment newInstance(int year, int month, int day) {
        WeekCalendarFragment fragment = new WeekCalendarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        args.putInt(ARG_PARAM3, day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        day = mParam3;
        System.out.println(day);
        View rootView = inflater.inflate(R.layout.fragment_week_calendar, container, false);
        ArrayList<String> dayList = new ArrayList<String>();
        mCal = Calendar.getInstance();
        // year 변수에 저장된 값을 Year에, month변수에 저장된 값을 Month에, 1을 Day에 넣는다.
        mCal.set(Integer.parseInt(String.valueOf(mParam1)), Integer.parseInt(String.valueOf(mParam2)) - 1, 1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        int dayMax = mCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(dayNum>day) {
            for (int i = 1; i < dayNum; i++) {
                dayList.add("");
            }
            for (int i = 1; i < 8- dayList.size(); i++){ // 최대 일 수만큼 dayList에 요소를 추가한다.
                dayList.add(String.valueOf(i+day));
            }
        }
        else{
            for (int i = 1; i < 8; i++){ // 최대 일 수만큼 dayList에 요소를 추가한다.
                dayList.add(String.valueOf(i+day));
            }


        }

        //해당 달의 최대 일 수를 구하기 위해 .getActualMaximum(Calendar.DAY_OF_MONTH) 함수를 사용한다.

        ArrayAdapter<String> adapt
                = new ArrayAdapter<String>(
                getActivity(),
                R.layout.item_week,R.id.item_gridview2,
                dayList); // 기존에 simple_list_item_1 리소스를 사용하였으나 텍스트 정렬을 위해
        // item_month 레이아웃을 만들어 그 내부에 만든 item_gridview를 사용하였다.

        // id를 바탕으로 화면 레이아웃에 정의된 GridView 객체 로딩
        GridView gridview = rootView.findViewById(R.id.gridview2);
        // 어댑터를 GridView 객체에 연결
        gridview.setAdapter(adapt);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(position >= dayNum-1 && position < dayMax+dayNum-1) { //그리드뷰의 n번째 요소의 내용이 1일 이상일 때 메시지를 출력한다.
                    Toast.makeText(getActivity(),
                            mParam1 + "." + mParam2 + "." + (position -(dayNum-2)),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
    public void onWindowFocusChanged(boolean hasFocus) {

    }
}