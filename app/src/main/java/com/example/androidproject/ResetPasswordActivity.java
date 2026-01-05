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

public class ResetPasswordActivity extends AppCompatActivity {

    EditText etNewPassword, etConfirmPassword;
    Button btnUpdatePassword;
    TextView tvBackToLoginFromReset;
    private AppDatabase db;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        db = AppDatabase.getDatabase(getApplicationContext());
        userEmail = getIntent().getStringExtra("email");

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
                new Thread(() -> {
                    UserDao userDao = db.userDao();
                    User user = userDao.findByEmail(userEmail);
                    if (user != null) {
                        user.password = newPass;
                        userDao.update(user);
                        runOnUiThread(() -> {
                            Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        });
                    }
                }).start();
            }
        });

        tvBackToLoginFromReset.setOnClickListener(v -> {
            Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
