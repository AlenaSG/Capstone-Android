package com.epicodus.eventmanager;

import android.content.Context;
import android.icu.text.RelativeDateTimeFormatter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by alenagolovina on 3/9/18.
 */

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    //Arrays
    public int[] slide_images = {
            R.drawable.eat_icon,
            R.drawable.sleep_icon,
            R.drawable.code_icon
    };

    public String[] slide_headings = {
            "EAT",
            "SLEEP",
            "CODE"
    };

    public String[] slide_descs = {
            "Lorem Ipsum, dolor, ibitus gumsumus. Lorem Ipsum, dolor, ibitus gumsumus. Lorem Ipsum, dolor, ibitus gumsumus.",
            "Lorem Ipsum, dolor, ibitus gumsumus. Lorem Ipsum, dolor, ibitus gumsumus. Lorem Ipsum, dolor, ibitus gumsumus.",
            "Lorem Ipsum, dolor, ibitus gumsumus. Lorem Ipsum, dolor, ibitus gumsumus. Lorem Ipsum, dolor, ibitus gumsumus."
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout, container, false);


        ImageView slideImageView = (ImageView) view.findViewById(R.id.imageViewSlide);
        TextView slideHeading = (TextView) view.findViewById(R.id.textViewHeading);
        TextView slideDescription = (TextView) view.findViewById(R.id.textViewContent);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
