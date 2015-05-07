package com.example.br161.personalinventory;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainScreenFragment extends Fragment {

    private TextView tvDrawerInstruction;

    private ViewItemsFragment viewItemsFragment;

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
                ( (MainScreenActivity) getActivity()).updateDrawer();
            }//end onClick
        });//end tvDrawerInstruction.setOnClickListener

        //create the fragment for the items viewing
        viewItemsFragment = new ViewItemsFragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, viewItemsFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }//end onViewCreated method

    public void updateFragment(String option) {
        if (option.equalsIgnoreCase("logout")) {
            ParseUser.logOut();

            Context context = getActivity().getApplicationContext();
            CharSequence text = "Successfully Logged Out";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }//end if
        else if (option.equalsIgnoreCase("view inventory")) {
            viewItemsFragment = new ViewItemsFragment();
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, viewItemsFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }//end else if
        else if (option.equalsIgnoreCase("add item")) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, new AddItemFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }//end else if
    }//end updateFragment method

    public void updateDrawerInstructions(String text) {
        if (text.equals(getString(R.string.drawer_close))) {
            tvDrawerInstruction.setText(text);
            tvDrawerInstruction.setGravity(Gravity.RIGHT);
        }//end if
        else if (text.equals(getString(R.string.drawer_open))) {
            tvDrawerInstruction.setText(text);
            tvDrawerInstruction.setGravity(Gravity.LEFT);
        }//end if
    }//end updateDrawerInstructions method

}//end MainScreenFragment class
