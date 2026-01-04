package com.example.madfinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madfinal.R;
import com.example.madfinal.adapters.BlogAdapter;
import com.example.madfinal.database.DatabaseHelper;
import com.example.madfinal.models.BlogPost;
import com.example.madfinal.network.ApiClient;
import com.example.madfinal.network.ApiService;
import com.example.madfinal.utils.ThemeManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BlogAdapter adapter;
    private List<BlogPost> blogList;
    private DatabaseHelper dbHelper;
    private ProgressBar progressBar;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Apply theme before super.onCreate and setContentView
        ThemeManager.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.blog_recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        FloatingActionButton fab = findViewById(R.id.fab_add_blog);

        dbHelper = new DatabaseHelper(this);
        blogList = new ArrayList<>();
        
        adapter = new BlogAdapter(this, blogList, new BlogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BlogPost blog) {
                // Open detail view
                Intent intent = new Intent(HomeActivity.this, BlogDetailActivity.class);
                intent.putExtra("blog_post", blog);
                startActivity(intent);
            }

            @Override
            public void onEditClick(BlogPost blog) {
                // Open edit view
                Intent intent = new Intent(HomeActivity.this, EditBlogActivity.class);
                intent.putExtra("blog_post", blog);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(BlogPost blog) {
                dbHelper.deleteBlog(blog.getId());
                loadBlogsFromDb(); // Refresh list
                Toast.makeText(HomeActivity.this, "Blog deleted", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, EditBlogActivity.class));
        });

        apiService = ApiClient.getClient().create(ApiService.class);

        // Load data strategy: Try to load from API, if fail or empty, load from DB. 
        // Also always load from DB initially to show cached data.
        loadBlogsFromDb();
        fetchBlogsFromApi();
    }

    private void loadBlogsFromDb() {
        List<BlogPost> localBlogs = dbHelper.getAllBlogs();
        if (!localBlogs.isEmpty()) {
            blogList.clear();
            blogList.addAll(localBlogs);
            adapter.notifyDataSetChanged();
        }
    }

    private void fetchBlogsFromApi() {
        progressBar.setVisibility(View.VISIBLE);
        Call<List<BlogPost>> call = apiService.getPosts();
        call.enqueue(new Callback<List<BlogPost>>() {
            @Override
            public void onResponse(Call<List<BlogPost>> call, Response<List<BlogPost>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<BlogPost> apiBlogs = response.body();
                    // For demo, we might just take the first 20 to avoid overwhelming the DB
                    for (int i = 0; i < Math.min(apiBlogs.size(), 20); i++) {
                        dbHelper.addBlog(apiBlogs.get(i));
                    }
                    loadBlogsFromDb(); // Reload from DB to reflect changes
                }
            }

            @Override
            public void onFailure(Call<List<BlogPost>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(HomeActivity.this, "Network error. Loading offline data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        loadBlogsFromDb(); // Refresh if returning from add/edit
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.action_logout) {
            SharedPreferences prefs = getSharedPreferences("BlogAppPrefs", MODE_PRIVATE);
            prefs.edit().clear().apply();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        } else if (id == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        } else if (id == R.id.theme_light) {
            ThemeManager.saveTheme(this, ThemeManager.THEME_LIGHT);
            recreate();
            return true;
        } else if (id == R.id.theme_dark) {
            ThemeManager.saveTheme(this, ThemeManager.THEME_DARK);
            recreate();
            return true;
        } else if (id == R.id.theme_sepia) {
            ThemeManager.saveTheme(this, ThemeManager.THEME_SEPIA);
            recreate();
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
}