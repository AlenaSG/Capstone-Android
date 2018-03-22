package com.epicodus.eventmanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class QuoteFragment extends Fragment {
    TextView mTvSettings;
    //Button mBtnQuote;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quote, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvSettings = (TextView) view.findViewById(R.id.tvSettings);

        view.findViewById(R.id.btnQuote).setOnClickListener(new View.OnClickListener() {//crash here - check the Quote Activity
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You are inside fragment quote", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), QuoteActivity.class);
                startActivity(intent);
            }
        });
    }
}
