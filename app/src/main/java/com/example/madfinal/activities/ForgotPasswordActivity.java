package com.example.madfinal.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madfinal.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText usernameEt;
    private Button resetBtn;
    private TextView backToLoginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        usernameEt = findViewById(R.id.fp_username_et);
        resetBtn = findViewById(R.id.reset_btn);
        backToLoginText = findViewById(R.id.back_to_login_fp);

        resetBtn.setOnClickListener(v -> {
            String username = usernameEt.getText().toString().trim();
            if (TextUtils.isEmpty(username)) {
                Toast.makeText(this, "Please enter your username", Toast.LENGTH_SHORT).show();
            } else {
                // Simulation of reset link
                Toast.makeText(this, "Reset link sent to " + username, Toast.LENGTH_LONG).show();
                finish();
            }
        });

        backToLoginText.setOnClickListener(v -> finish());
    }
}