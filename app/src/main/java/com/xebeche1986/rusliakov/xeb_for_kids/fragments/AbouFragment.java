package com.xebeche1986.rusliakov.xeb_for_kids.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.xebeche1986.rusliakov.xeb_for_kids.R;

public class AbouFragment extends android.support.v4.app.DialogFragment{
    TextView tv_ok;

    public AbouFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about,container,false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        tv_ok = (TextView)view.findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return view;

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        int dialogWidth = ViewGroup.LayoutParams.MATCH_PARENT;
//        int dialogHeight = ViewGroup.LayoutParams.MATCH_PARENT;
//        getDialog().getWindow().setLayout(dialogWidth,dialogHeight);
//
//    }
}
