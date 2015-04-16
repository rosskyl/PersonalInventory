package com.example.br161.personalinventory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

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

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Log.d("test", "logged in");
        }//end if
        else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        }//end else
/*
        //sets up an anonymous user automatically
        ParseUser.enableAutomaticUser();
        ParseUser.getCurrentUser().increment("RunCount");
        ParseUser.getCurrentUser().saveInBackground();
*/



    }//end onCreate method
}//end MainActivity class
