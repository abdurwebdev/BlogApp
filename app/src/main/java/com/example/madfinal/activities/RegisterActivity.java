package com.example.madfinal.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madfinal.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEt, passwordEt, confirmPasswordEt;
    private Button registerBtn;
    private TextView backToLoginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEt = findViewById(R.id.reg_username_et);
        passwordEt = findViewById(R.id.reg_password_et);
        confirmPasswordEt = findViewById(R.id.reg_confirm_password_et);
        registerBtn = findViewById(R.id.register_btn);
        backToLoginText = findViewById(R.id.back_to_login_text);

        registerBtn.setOnClickListener(v -> {
            String username = usernameEt.getText().toString().trim();
            String password = passwordEt.getText().toString().trim();
            String confirmPass = confirmPasswordEt.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPass)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                // Here you would typically save to DB or API
                Toast.makeText(this, "Registration Successful! Please Login.", Toast.LENGTH_SHORT).show();
                finish(); // Go back to login
            }
        });

        backToLoginText.setOnClickListener(v -> finish());
    }
}