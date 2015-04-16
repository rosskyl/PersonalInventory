package com.example.br161.personalinventory;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends Activity {

    private EditText etUserName;

    private EditText etPassword;

    private EditText etPasswordConfirm;

    private EditText etEmail;

    private TextView tvSubmitLogin;

    private TextView tvSubmitNewUser;

    private TextView tvNewUser;

    private TextView tvContinueAnon;

    private ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText) findViewById(R.id.et_user_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        etPasswordConfirm = (EditText) findViewById(R.id.et_password_confirm);
        etEmail = (EditText) findViewById(R.id.et_email);
        tvSubmitLogin = (TextView) findViewById(R.id.tv_submit_login);
        tvSubmitNewUser = (TextView) findViewById(R.id.tv_submit_new_user);
        tvNewUser = (TextView) findViewById(R.id.tv_new_user);
        tvContinueAnon = (TextView) findViewById(R.id.tv_continue_anon);

        tvSubmitNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                String passwordConfirm = etPasswordConfirm.getText().toString();
                String email = etEmail.getText().toString();

                //check to make sure all fields are filled in
                if (userName == null || password == null || passwordConfirm == null || email == null) {
                    Context context = getApplicationContext();
                    CharSequence text = "Please enter user name and password";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }//end if
                else if ( !(password.equals(passwordConfirm))) {
                    Context context = getApplicationContext();
                    CharSequence text = "Passwords do not match";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }//end else if
                else {
                    user = new ParseUser();
                    user.setUsername(userName);
                    user.setPassword(password);

                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                ParseInventory parseInventory = new ParseInventory();
                                boolean test = parseInventory.putItem("asd fasdfasdfs ", "", 1);
                                Log.d("check", test + "");
                            }//end if
                            else {
                                Log.d("signup", e.getMessage().toString());
                            }//end else
                        }//end done
                    });//end user.signUpInBackground
                }//end else
            }//end onClick
        });//end tvSubmit.setOnClickListener

        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPasswordConfirm.setVisibility(View.VISIBLE);
                etEmail.setVisibility(View.VISIBLE);
                tvSubmitNewUser.setVisibility(View.VISIBLE);

                tvSubmitLogin.setVisibility(View.GONE);
            }//end onClick
        });//end
    }//end onCreate method
}//end LoginActivity class
