package com.example.tanchian.myapplication.tabs_refragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanchian.myapplication.R;

public class History extends Fragment {

    private static final String ARG_EXAMPLE = "this_is_a_constant";
    private String example_data;

    public History(){

    }

    public static History newInstance (String example_argument){
        History historylist = new History();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argument);
        historylist.setArguments(args);
        return historylist;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        example_data = getArguments().getString(ARG_EXAMPLE);
        Log.i("Fragment created with ", example_data);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.history, container,false);
    }
}
