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

    private TextView tvContinueToApp;

    private TextView tvLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContinueToApp = (TextView) findViewById(R.id.tv_continue_to_app);
        tvLogout = (TextView) findViewById(R.id.tv_logout);

        tvContinueToApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }//end onClick
        });//end tvContinueToApp.setOnClickListener

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();

                Context context = getApplicationContext();
                CharSequence text = "Successfully Logged Out";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                checkUser();
            }//end onClick
        });//tvLogout.setOnClickListener

        //needed for using parse
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, getString(R.string.application_ID), getString(R.string.client_key));

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

        checkUser();
/*
        //sets up an anonymous user automatically
        ParseUser.enableAutomaticUser();
        ParseUser.getCurrentUser().increment("RunCount");
        ParseUser.getCurrentUser().saveInBackground();
*/
    }//end onCreate method

    private void checkUser() {

        //check if user is already logged in
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(MainActivity.this, MainScreenActivity.class);
            startActivity(intent);
        }//end if
        else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }//end else
    }//end checkUser method
}//end MainActivity class
