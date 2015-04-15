package com.example.br161.personalinventory;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //needed for using parse
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "niksKHirkYlRWqK36F6VQhSqUQ7XIfF25sILpM7N", "I4kaV9SnJWnACD2Uc4JkXDTKD1GFPDxSmpKFGkpp");

        //needed for push notifications
        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }//end else
            }//end done
        });//end ParsePush.subscribeInBackground

        ParseInventory parseInventory = new ParseInventory();
        boolean test = parseInventory.putItem("asdfas ", "", 1);
        Log.d("check", test + "");
    }//end onCreate method
}//end MainActivity class
