package com.example.touchstone.demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    RotateLoading rotateLoading;
    Button sign;
    EditText emailEditText, passwordEditText;

    String enteredEmail, enteredPassword;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        sign = (Button) findViewById(R.id.button3);
        emailEditText = (EditText) findViewById(R.id.Emailfill);
        passwordEditText = (EditText) findViewById(R.id.Passwordfill);
        rotateLoading = (RotateLoading)findViewById(R.id.rotateloading);




        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enteredEmail = emailEditText.getText().toString();
                enteredPassword = passwordEditText.getText().toString();

                SharedPreferences sharedPreferences=getSharedPreferences("signup",MODE_PRIVATE);
                String email = sharedPreferences.getString("email",null);
                String password = sharedPreferences.getString("password",null);

                if (enteredEmail.length() == 0) {

                    Toast.makeText(Login.this, "Enter the Username", Toast.LENGTH_SHORT).show();
                } else if (enteredPassword.length() == 0) {


                    Toast.makeText(Login.this, "Enter the Password", Toast.LENGTH_SHORT).show();
                } else {

                    if (email.equals(enteredEmail) && password.equals(enteredPassword)) {


                        Handler handler = new Handler();
                        rotateLoading.start();


                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                rotateLoading.stop();


                                SharedPreferences.Editor sharedPreferences1 = getSharedPreferences("login",MODE_PRIVATE).edit();
                                sharedPreferences1.putBoolean("value",true);
                                sharedPreferences1.commit();

                                Intent intent = new Intent(Login.this, Navigation.class);
                                startActivity(intent);
                                finish();

                            }
                        },10000);




                    } else {


                        Toast.makeText(Login.this, "Values does not match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
}
