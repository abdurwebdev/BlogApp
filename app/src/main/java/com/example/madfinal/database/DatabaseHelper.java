package com.example.madfinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.madfinal.models.BlogPost;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BlogApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BLOGS = "blogs";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_BODY = "body";
    private static final String COLUMN_USER_ID = "user_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_BLOGS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_BODY + " TEXT, " +
                COLUMN_USER_ID + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BLOGS);
        onCreate(db);
    }

    public long addBlog(BlogPost blog) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, blog.getId()); // Using API ID if available, or generate one
        values.put(COLUMN_TITLE, blog.getTitle());
        values.put(COLUMN_BODY, blog.getBody());
        values.put(COLUMN_USER_ID, blog.getUserId());

        long result = db.insertWithOnConflict(TABLE_BLOGS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
        return result;
    }

    public int updateBlog(BlogPost blog) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, blog.getTitle());
        values.put(COLUMN_BODY, blog.getBody());

        int result = db.update(TABLE_BLOGS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(blog.getId())});
        db.close();
        return result;
    }

    public void deleteBlog(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BLOGS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<BlogPost> getAllBlogs() {
        List<BlogPost> blogList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_BLOGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BlogPost blog = new BlogPost();
                blog.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                blog.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
                blog.setBody(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BODY)));
                blog.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID)));
                blog.setLocallySaved(true);
                blogList.add(blog);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return blogList;
    }
}