package com.epicodus.eventmanager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeScreenActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private Button mBtnBack;
    private Button mBtnNext;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotsLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mBtnBack = (Button) findViewById(R.id.btnBack);
        mBtnNext = (Button) findViewById(R.id.btnNext);


        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        //OnClickListeners
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

//        @Override
//        public void finishTutorial() {
//            // Your implementation
//        }﻿
    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[3];
        mDotsLayout.removeAllViews();
        for(int i = 0; i < mDots.length; i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            mDotsLayout.addView(mDots[i]);

        }

        if(mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            mCurrentPage = position;

            if(position == 0) {
                mBtnNext.setEnabled(true);
                mBtnBack.setEnabled(false);
                mBtnBack.setVisibility(View.INVISIBLE);

                mBtnNext.setText("Next");
                mBtnBack.setText("");

            } else if (position == mDots.length - 1) {
                mBtnNext.setEnabled(true);
                mBtnBack.setEnabled(true);
                mBtnBack.setVisibility(View.VISIBLE);

                mBtnNext.setText("Finish");
                mBtnBack.setText("Back");

            } else {

                mBtnNext.setEnabled(true);
                mBtnBack.setEnabled(true);
                mBtnBack.setVisibility(View.VISIBLE);

                mBtnNext.setText("Next");
                mBtnBack.setText("Back");
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
