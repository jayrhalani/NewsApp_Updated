package com.jayhalani.newsapp.network;

import com.jayhalani.newsapp.models.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("everything?q=india&from=2019-08-30&sortBy=publishedAt&apiKey=bbc55a739a6e477eafd82f5b7f37c26b")
    Call<News> getAllNews();
}
