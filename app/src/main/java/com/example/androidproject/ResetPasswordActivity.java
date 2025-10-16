package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText etNewPassword, etConfirmPassword;
    Button btnUpdatePassword;
    TextView tvBackToLoginFromReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnUpdatePassword = findViewById(R.id.btnUpdatePassword);
        tvBackToLoginFromReset = findViewById(R.id.tvBackToLoginFromReset);

        btnUpdatePassword.setOnClickListener(v -> {
            String newPass = etNewPassword.getText().toString().trim();
            String confirmPass = etConfirmPassword.getText().toString().trim();

            if (newPass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!newPass.equals(confirmPass)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvBackToLoginFromReset.setOnClickListener(v -> {
            Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
