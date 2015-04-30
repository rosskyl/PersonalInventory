package com.example.br161.personalinventory;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;


public class MainScreenActivity extends Activity {

    private String[] drawerListViewItems;

    private DrawerLayout drawerLayout;

    private ListView drawerListView;


    private ActionBarDrawerToggle drawerToggle;

    private int position = 0;

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

        // Setting item click listener to mDrawerList
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int idx, long id) {
                updateFragment(idx);

                drawerLayout.closeDrawer(drawerListView);
            }//end onItemClick
        });//end drawerListView.setOnItemClickListener

        final MainScreenFragment mainScreenFragment = new MainScreenFragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, mainScreenFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                null, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                mainScreenFragment.updateDrawerInstructions(R.string.drawer_open);
                isDrawerOpen = false;
                Log.d("drawer", "closed");
            }//end onDrawerClosed

            public void onDrawerOpened(View drawerView) {
                mainScreenFragment.updateDrawerInstructions(R.string.drawer_close);
                isDrawerOpen = true;
                Log.d("drawer", "opened");
            }//end onDrawerOpened
        };//end drawerToggle = new ActionBarDrawerToggle
        drawerLayout.setDrawerListener(drawerToggle);

        updateFragment(-1);
    }//end onCreate method

    private void updateFragment(int idx) {
        if (idx == -1) {

        }//end if
        else if (idx != position) {
            position = idx;
        }//end if
        //TODO actually make update fragment
    }//end updateFragment method

    public void updateDrawer() {
        if (isDrawerOpen)
            drawerLayout.closeDrawer(drawerListView);
        else
            drawerLayout.openDrawer(drawerListView);
    }//end updateDrawer method

}//end MainScreenActivity class
