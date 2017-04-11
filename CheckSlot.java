package com.example.tanchian.myapplication.tabs_refragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanchian.myapplication.R;

public class CheckSlot extends Fragment {

    private static final String ARG_EXAMPLE = "this_is_a_constant";
    private String example_data;

    public CheckSlot(){

    }

    public static CheckSlot newInstance (String example_argument){
        CheckSlot checkSlot = new CheckSlot();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argument);
        checkSlot.setArguments(args);
        return checkSlot;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        example_data = getArguments().getString(ARG_EXAMPLE);
        Log.i("Fragment created with ", example_data);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.slot_check, container,false);
    }
}
