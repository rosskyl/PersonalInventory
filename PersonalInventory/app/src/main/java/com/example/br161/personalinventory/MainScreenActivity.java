package com.example.br161.personalinventory;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainScreenActivity extends Activity {

    private RecyclerView recyclerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Item item = new Item("hello", "there", 5, "five");

        recyclerItems = (RecyclerView) findViewById(R.id.recycler_items);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerItems.setLayoutManager(layoutManager);

        recyclerItems.setAdapter(new ItemAdapter());
    }//end onCreate method
}//end MainScreenActivity class
