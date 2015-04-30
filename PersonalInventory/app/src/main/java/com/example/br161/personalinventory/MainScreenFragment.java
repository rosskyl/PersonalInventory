package com.example.br161.personalinventory;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainScreenFragment extends Fragment {

    private TextView tvDrawerInstruction;

    private ArrayList<Item> items;//TODO get items

    public MainScreenFragment() {
        // Required empty public constructor
    }//end MainScreenFragment method

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false);
    }//end onCreateView method

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvDrawerInstruction = (TextView) view.findViewById(R.id.tv_drawer_instruction);

        tvDrawerInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO actually handle click
            }//end onClick
        });//end tvDrawerInstruction.setOnClickListener
    }//end onViewCreated method

    public void updateFragment(int position) {
        //TODO actually switch fragments here
    }//end updateFragment method

    public void updateDrawerInstructions(int textID) {
        tvDrawerInstruction.setText(textID);
        //TODO make this stuff move over
    }//end updateDrawerInstructions method

}//end MainScreenFragment class
