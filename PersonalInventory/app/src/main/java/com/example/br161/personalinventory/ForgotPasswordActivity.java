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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;


public class ForgotPasswordActivity extends Activity {

    private EditText etEmail;

    private TextView tvSubmitForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail = (EditText) findViewById(R.id.et_email);
        tvSubmitForgotPassword = (TextView) findViewById(R.id.tv_submit_forgot_password);

        final Activity activity = this;

        tvSubmitForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                if (email.equals("")) {
                    Context context = getApplicationContext();
                    CharSequence text = "Enter an email";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }//end if
                else {
                    ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                Context context = getApplicationContext();
                                CharSequence text = "Email successfully sent";
                                int duration = Toast.LENGTH_LONG;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();

                                Intent intent = new Intent(activity, LoginActivity.class);
                                startActivity(intent);
                            }//end if
                            else {
                                if (e.getCode() == 205) {
                                    Context context = getApplicationContext();
                                    CharSequence text = "Invalid email";
                                    int duration = Toast.LENGTH_LONG;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }//end if
                                else {
                                    Log.d("passwordReset", e.getCode() + "");
                                }//end else
                            }//end else
                        }//end done
                    });//end ParseUser.requestPasswordResetInBackground
                }//end else
            }//end onClick
        });//tvSubmitForgotPassword.setOnClickListener
    }//end onCreate method
}//end ForgotPasswordActivity class