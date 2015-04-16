package com.example.br161.personalinventory;

import android.app.Activity;
import android.content.Context;
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

    private EditText etUserName;

    private EditText etPassword;

    private TextView tvSubmit;

    private ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = (EditText) findViewById(R.id.et_user_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvSubmit = (TextView) findViewById(R.id.tv_submit);

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

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();

                if (userName == null || password == null) {
                    Context context = getApplicationContext();
                    CharSequence text = "Please enter user name and password";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    user = new ParseUser();
                    user.setUsername(userName);
                    user.setPassword(password);

                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                ParseInventory parseInventory = new ParseInventory();
                                boolean test = parseInventory.putItem("asd fasdfasdfs ", "", 1);
                                Log.d("check", test + "");
                            } else {
                                Log.d("signup", e.getMessage().toString());
                            }//end else
                        }//end done
                    });//end user.signUpInBackground
                }//end else
            }//end onClick
        });//end tvSubmit.setOnClickListener
/*
        //sets up an anonymous user automatically
        ParseUser.enableAutomaticUser();
        ParseUser.getCurrentUser().increment("RunCount");
        ParseUser.getCurrentUser().saveInBackground();
*/



    }//end onCreate method
}//end MainActivity class
