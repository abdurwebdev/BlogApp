package com.example.madfinal.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madfinal.R;
import com.example.madfinal.database.DatabaseHelper;
import com.example.madfinal.models.BlogPost;
import com.example.madfinal.utils.ThemeManager;

public class EditBlogActivity extends AppCompatActivity {

    private EditText etTitle, etBody;
    private Button btnSave;
    private DatabaseHelper dbHelper;
    private BlogPost currentBlog;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_blog);

        etTitle = findViewById(R.id.et_blog_title);
        etBody = findViewById(R.id.et_blog_body);
        btnSave = findViewById(R.id.btn_save_blog);
        dbHelper = new DatabaseHelper(this);

        if (getIntent().hasExtra("blog_post")) {
            currentBlog = (BlogPost) getIntent().getSerializableExtra("blog_post");
            if (currentBlog != null) {
                isEditMode = true;
                etTitle.setText(currentBlog.getTitle());
                etBody.setText(currentBlog.getBody());
            }
        }

        btnSave.setOnClickListener(v -> saveBlog());
    }

    private void saveBlog() {
        String title = etTitle.getText().toString().trim();
        String body = etBody.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(body)) {
            Toast.makeText(this, "Title and Body cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEditMode) {
            currentBlog.setTitle(title);
            currentBlog.setBody(body);
            dbHelper.updateBlog(currentBlog);
            Toast.makeText(this, "Blog Updated", Toast.LENGTH_SHORT).show();
        } else {
            BlogPost newBlog = new BlogPost();
            // Generate a random ID for local blogs to avoid collision with API IDs (typically 1-100)
            int newId = (int) (System.currentTimeMillis() % 100000) + 1000; 
            newBlog.setId(newId);
            newBlog.setTitle(title);
            newBlog.setBody(body);
            newBlog.setUserId(1); // Default user
            newBlog.setLocallySaved(true);
            
            dbHelper.addBlog(newBlog);
            Toast.makeText(this, "Blog Saved", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}