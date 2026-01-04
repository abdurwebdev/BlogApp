package com.example.madfinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.madfinal.R;
import com.example.madfinal.models.BlogPost;
import com.example.madfinal.utils.ThemeManager;

public class BlogDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);

        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Blog Details");
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        TextView titleTv = findViewById(R.id.detail_title);
        TextView bodyTv = findViewById(R.id.detail_body);
        Button webViewBtn = findViewById(R.id.btn_open_webview);

        if (getIntent().hasExtra("blog_post")) {
            BlogPost blog = (BlogPost) getIntent().getSerializableExtra("blog_post");
            if (blog != null) {
                titleTv.setText(blog.getTitle());
                bodyTv.setText(blog.getBody());
            }
        }

        webViewBtn.setOnClickListener(v -> {
            Intent intent = new Intent(BlogDetailActivity.this, WebViewActivity.class);
            // Passing a dummy URL for demonstration as API doesn't provide real URLs
            intent.putExtra("url", "https://developer.android.com/"); 
            startActivity(intent);
        });
    }
}