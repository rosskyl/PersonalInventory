package com.example.br161.personalinventory;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;


public class MainScreenActivity extends Activity {

    private String[] drawerListViewItems;

    private DrawerLayout drawerLayout;

    private ListView drawerListView;

    private ActionBarDrawerToggle drawerToggle;

    private MainScreenFragment mainScreenFragment;

    private boolean isDrawerOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        drawerListViewItems = getResources().getStringArray(R.array.drawer_items);

        drawerListView = (ListView) findViewById(R.id.left_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Set the adapter for the list view
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_listview_item, drawerListViewItems));

        // Setting item click listener
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int idx, long id) {
                updateFragment(idx);

                drawerLayout.closeDrawer(drawerListView);
            }//end onItemClick
        });//end drawerListView.setOnItemClickListener

        //create the fragment for the main screen
        mainScreenFragment = new MainScreenFragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, mainScreenFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();

        //Setup a listener for the navigation drawer for when it opens and closes
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                null, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                mainScreenFragment.updateDrawerInstructions(R.string.drawer_open);
                isDrawerOpen = false;
            }//end onDrawerClosed

            public void onDrawerOpened(View drawerView) {
                mainScreenFragment.updateDrawerInstructions(R.string.drawer_close);
                isDrawerOpen = true;
            }//end onDrawerOpened
        };//end drawerToggle = new ActionBarDrawerToggle
        drawerLayout.setDrawerListener(drawerToggle);

        updateFragment(0);
    }//end onCreate method

    private void updateFragment(int idx) {
        mainScreenFragment.updateFragment(idx);
    }//end updateFragment method

    public void updateDrawer() {
        Log.d("updateDrawer", "updated");
        if (isDrawerOpen)
            drawerLayout.closeDrawer(drawerListView);
        else
            drawerLayout.openDrawer(drawerListView);
    }//end updateDrawer method

}//end MainScreenActivity class
