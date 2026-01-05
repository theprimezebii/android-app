package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import com.example.androidproject.db.AppDatabase;
import com.example.androidproject.db.User;
import com.example.androidproject.db.UserDao;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText etEmail;
    Button btnResetPassword;
    TextView tvBackToLogin;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        db = AppDatabase.getDatabase(getApplicationContext());

        etEmail = findViewById(R.id.etEmail);
        btnResetPassword = findViewById(R.id.btnResetPassword);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);

        btnResetPassword.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                UserDao userDao = db.userDao();
                User user = userDao.findByEmail(email);
                if (user != null) {
                    runOnUiThread(() -> {
                        Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(ForgotPasswordActivity.this, "User not found", Toast.LENGTH_SHORT).show());
                }
            }).start();
        });

        tvBackToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
