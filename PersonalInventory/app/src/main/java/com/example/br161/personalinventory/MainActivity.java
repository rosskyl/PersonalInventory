package com.example.br161.personalinventory;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseObject;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //needed for using parse
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "niksKHirkYlRWqK36F6VQhSqUQ7XIfF25sILpM7N", "I4kaV9SnJWnACD2Uc4JkXDTKD1GFPDxSmpKFGkpp");

        Inventory inventory = new Inventory();
        boolean test = inventory.putItem("asdfas ", "", 1);
        Log.d("check", test + "");
    }//end onCreate method
}//end MainActivity class
