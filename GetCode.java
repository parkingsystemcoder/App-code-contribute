package com.example.tanchian.myapplication.tabs_refragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanchian.myapplication.R;

public class GetCode extends Fragment {

    private static final String ARG_EXAMPLE = "this_is_a_constant";
    private String example_data;

    public GetCode(){

    }

    public static GetCode newInstance (String example_argument){
        GetCode getCode = new GetCode();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argument);
        getCode.setArguments(args);
        return getCode;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        example_data = getArguments().getString(ARG_EXAMPLE);
        Log.i("Fragment created with ", example_data);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.get_code, container,false);
    }
}
