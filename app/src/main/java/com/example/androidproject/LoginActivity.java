package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button buttonLogin;
    TextView textRegister, textForgotPassword;

    // Hardcoded credentials (you can later connect Firebase or DB)
    private final String correctUsername = "zohaib";
    private final String correctPassword = "12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Views
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textRegister = findViewById(R.id.textRegister);
        textForgotPassword = findViewById(R.id.textForgotPassword);

        // Login button logic
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else if (username.equals(correctUsername) && password.equals(correctPassword)) {
                    Toast.makeText(LoginActivity.this, "Login Successful 🎉", Toast.LENGTH_SHORT).show();

                    // Redirect to HomeActivity
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Username or Password ❌", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Register link
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Forgot password link
        textForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
