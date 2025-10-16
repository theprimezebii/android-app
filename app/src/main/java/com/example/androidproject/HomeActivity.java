package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

public class HomeActivity extends AppCompatActivity {

    TextView tvDevName, tvBio;
    ImageView imgProfile;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvDevName = findViewById(R.id.tvDevName);
        tvBio = findViewById(R.id.tvBio);
        imgProfile = findViewById(R.id.imgProfile);
        btnLogout = findViewById(R.id.btnLogout);

        // (Optional) Get username passed from LoginActivity
        String username = getIntent().getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            tvDevName.setText(username);
        }

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
