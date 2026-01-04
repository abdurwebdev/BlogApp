package com.example.madfinal.network;

import com.example.madfinal.models.BlogPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("posts")
    Call<List<BlogPost>> getPosts();
}