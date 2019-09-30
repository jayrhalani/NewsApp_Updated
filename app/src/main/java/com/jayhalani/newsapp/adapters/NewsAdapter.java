package com.jayhalani.newsapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jayhalani.newsapp.R;
import com.jayhalani.newsapp.databinding.NewsItemBinding;
import com.jayhalani.newsapp.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CustomViewHolder> {

    private List<Article> articles;

    public NewsAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.news_item, parent, false);
        return new CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Article article = articles.get(position);
        if (article.getUrlToImage() == null) {
            Picasso.get().load(R.drawable.ic_launcher_background)
                    .fit().centerCrop()
                    .into(holder.newsItemBinding.listItemThumbnail);
        } else {
            Picasso.get().load(article.getUrlToImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .fit().centerCrop()
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.newsItemBinding.listItemThumbnail);
        }
        holder.newsItemBinding.listItemTitle.setText(article.getTitle());
        holder.newsItemBinding.listItemDescription.setText(article.getDescription());

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        NewsItemBinding newsItemBinding;

        CustomViewHolder(@NonNull NewsItemBinding newsItemBinding) {
            super(newsItemBinding.getRoot());
            this.newsItemBinding = newsItemBinding;
        }
    }
}
