package com.somoplay.somotab.SomoMe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yaolu on 15-09-14.
 */
public class TabFragmentMe extends Fragment {
    private String mTitle = "Default";

    public static final String TITLE = "title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        if (getArguments() != null)
        {
            mTitle = getArguments().getString(TITLE);
        }

        TextView tv = new TextView(getActivity());
        tv.setTextSize(20);
        tv.setBackgroundColor(Color.parseColor("#ffffffff"));
        tv.setText(mTitle);
        tv.setGravity(Gravity.CENTER);
        return tv;

    }
}
