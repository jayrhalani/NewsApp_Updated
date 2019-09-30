package com.jayhalani.newsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jayhalani.newsapp.adapters.NewsAdapter;
import com.jayhalani.newsapp.databinding.ActivityMainBinding;
import com.jayhalani.newsapp.models.Article;
import com.jayhalani.newsapp.models.News;
import com.jayhalani.newsapp.network.GetDataService;
import com.jayhalani.newsapp.network.RetrofitClientInstance;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        GetDataService dataService =
                RetrofitClientInstance
                        .getRetrofitInstance()
                        .create(GetDataService.class);
        Call<News> call = dataService.getAllNews();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NotNull Call<News> call, @NotNull Response<News> response) {
                assert response.body() != null;
                List<Article> articles = response.body().getArticles();
                NewsAdapter adapter = new NewsAdapter(articles);
                RecyclerView.LayoutManager layoutManager =
                        new LinearLayoutManager(MainActivity.this);
                mBinding.rvMainList.setLayoutManager(layoutManager);
                mBinding.rvMainList.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NotNull Call<News> call, @NotNull Throwable t) {

            }
        });

    }
}
