package com.epicodus.eventmanager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class RatingList extends ArrayAdapter<Detail>{
    private Activity context;
    private List<Detail> detailList;

    public RatingList(Activity context, List<Detail> detailList){
        super(context,R.layout.rating_list_layout, detailList);

        this.context = context;
        this.detailList = detailList;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);//need to delete this autogenerated statement.
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.rating_list_layout, null, true);

        TextView tvDetails = (TextView) listViewItem.findViewById(R.id.tvDetails);
        TextView tvRating = (TextView) listViewItem.findViewById(R.id.tvRating);

        Detail detail = detailList.get(position);

        tvDetails.setText(detail.getDetailText());
        tvRating.setText(String.valueOf(detail.getEventRating()));

        return listViewItem;

    }

}
