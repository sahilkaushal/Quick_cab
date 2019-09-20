package com.example.touchstone.demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    Button registerButton;
    CheckBox checkBox;
    EditText fristnameEditText, lastnameEditText, emailEditText, passwordEditText, confirmpasswordEditText;

    String enteredfristname, enteredlastname, enteredEmail, enteredPassword, enteredconfirmPassword;
    private String fristname, lastname, email, password, conirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        registerButton = (Button) findViewById(R.id.button4);
        emailEditText = (EditText) findViewById(R.id.emailsi);
        passwordEditText = (EditText) findViewById(R.id.passwordsi);
        confirmpasswordEditText = (EditText) findViewById(R.id.confirmpassword);
        fristnameEditText = (EditText) findViewById(R.id.fristname);
        lastnameEditText = (EditText) findViewById(R.id.lastname);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fristname = fristnameEditText.getText().toString();
                String lastname = lastnameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmpasswordEditText.getText().toString();

                if (fristname.isEmpty()) {

                    fristnameEditText.setError("Frist name field is empty");
                }

               else if (lastname.isEmpty()) {

                    lastnameEditText.setError("Last name field is empty");
                }

               else if (!checkBox.isChecked()) {
                    Toast.makeText(Signup.this, "Click on there", Toast.LENGTH_SHORT).show();
                    checkBox.setError("Click on there");

                }
               else if (email.isEmpty()) {

                    emailEditText.setError("Email is empty");
                }
               else if (password.isEmpty()) {
                    passwordEditText.setError("Fill your password");
                }
                else if (confirmPassword.isEmpty()) {
                    confirmpasswordEditText.setError("Password does not match");
                }

                else {

                    if (password.equals(confirmPassword)) {

                        //do something
                        Intent intent = new Intent(Signup.this, Login.class);
                        startActivity(intent);

                    } else if (!password.equals(confirmPassword)) {
                        Toast.makeText(Signup.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(Signup.this, "Fill the mandatory fields", Toast.LENGTH_SHORT).show();
                    }

                }


                //store the value in Sql lite
                SharedPreferences.Editor sharedPreferences = getSharedPreferences("signup", MODE_PRIVATE).edit();
                sharedPreferences.putString("email", email);
                sharedPreferences.putString("password", password);
                sharedPreferences.putString("fristname", fristname);
                sharedPreferences.putString("lastname", lastname);

                sharedPreferences.commit();

            }
        });


        checkBox = (CheckBox) findViewById(R.id.checkBox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (checkBox.isChecked()) {
                    checkBox.setChecked(true);
                }

            }
        });


    }
}