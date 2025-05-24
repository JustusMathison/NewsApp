package com.example.newsapp;

import android.content.Context; // <--- 添加这个 import
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat; // 用于颜色
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<News> newsList;
    private List<News> originalNewsList; // 保存原始列表用于搜索
    private OnNewsClickListener listener;
    private AppPreferences appPreferences;
    private int lastReadNewsId = -1;
    private Context context; // 保存 Context 用于获取颜色等资源

    public interface OnNewsClickListener {
        void onNewsClick(News news);
    }

    // 构造函数接收一个 Context 参数
    public NewsAdapter(List<News> newsList, OnNewsClickListener listener, Context context) {
        this.newsList = (newsList == null) ? new ArrayList<>() : new ArrayList<>(newsList);
        this.originalNewsList = new ArrayList<>(this.newsList);
        this.listener = listener;
        this.context = context; // 保存 context
        if (this.context != null) {
            this.appPreferences = new AppPreferences(this.context); // 使用保存的 context
            this.lastReadNewsId = this.appPreferences.getLastReadNewsId();
        }
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = (newsList == null) ? new ArrayList<>() : new ArrayList<>(newsList);
        this.originalNewsList = new ArrayList<>(this.newsList);
        if (appPreferences != null) {
            this.lastReadNewsId = appPreferences.getLastReadNewsId();
        }
        notifyDataSetChanged();
    }

    public void updateLastReadNewsId(int newsId) {
        this.lastReadNewsId = newsId;
        notifyDataSetChanged();
    }

    // 搜索功能（备用，主要使用数据库搜索）
    public void filter(String searchText) {
        newsList.clear();
        if (searchText.isEmpty()) {
            newsList.addAll(originalNewsList);
        } else {
            String query = searchText.toLowerCase();
            for (News news : originalNewsList) {
                if (news.getTitle().toLowerCase().contains(query) ||
                        news.getContent().toLowerCase().contains(query)) {
                    newsList.add(news);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        if (newsList == null || newsList.isEmpty()) {
            return;
        }
        News news = newsList.get(position);
        holder.titleTextView.setText(news.getTitle());
        holder.summaryTextView.setText(news.getSummary());
        holder.dateTextView.setText(news.getDate());

        if (news.getId() == lastReadNewsId) {
            // 使用 ContextCompat 来获取颜色，确保兼容性
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.highlight_color)); // 假设你在 colors.xml 定义了 highlight_color
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onNewsClick(news);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView summaryTextView;
        TextView dateTextView;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_news_title);
            summaryTextView = itemView.findViewById(R.id.text_news_summary);
            dateTextView = itemView.findViewById(R.id.text_news_date);
        }
    }
}