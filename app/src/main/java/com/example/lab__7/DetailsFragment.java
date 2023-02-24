package com.example.lab__7;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        // Find the TextViews in the layout and populate them with data
        TextView textView1 = view.findViewById(R.id.textView);
        TextView textView2 = view.findViewById(R.id.textView2);
        TextView textView3 = view.findViewById(R.id.textView3);
        Bundle bundle = getArguments();
        String text1 = bundle.getString("characterName");
        String text2 = bundle.getString("characterHeight");
        String text3 = bundle.getString("characterMass");
        textView1.setText("Name :" + text1);
        textView2.setText("Height :" + text2);
        textView3.setText("Mass :" + text3);

        return view;
    }
}

