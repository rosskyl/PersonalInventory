package com.example.br161.personalinventory;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.SaveCallback;
/**
 * Created by Kyle on 5/7/2015.
 */
public class App extends Application {

    private App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        //needed for using parse
        ParseObject.registerSubclass(Item.class);
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
    }//end onCreate method

    public App getInstance() {
        return instance;
    }//end getInstance method
}//end App class
