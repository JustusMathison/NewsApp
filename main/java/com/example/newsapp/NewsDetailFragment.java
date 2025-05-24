package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
// import androidx.lifecycle.ViewModelProvider; // 如果使用ViewModel

public class NewsDetailFragment extends Fragment {

    private int newsId = -1;
    private TextView titleTextView;
    private TextView dateTextView;
    private TextView contentTextView;
    private NewsDao newsDao;
    // private News currentNews; // LiveData会处理数据

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            newsId = getArguments().getInt("newsId", -1);
        }
        newsDao = NewsRoomDatabase.getDatabase(requireContext().getApplicationContext()).newsDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleTextView = view.findViewById(R.id.text_news_title);
        dateTextView = view.findViewById(R.id.text_news_date);
        contentTextView = view.findViewById(R.id.text_news_content);

        if (newsId != -1) {
            newsDao.getNewsById(newsId).observe(getViewLifecycleOwner(), new Observer<News>() {
                @Override
                public void onChanged(News news) {
                    if (news != null) {
                        displayNews(news);
                    }
                }
            });
        }
    }

    public void displayNews(News news) {
        if (news != null) {
            // 确保视图已初始化
            if (titleTextView != null && dateTextView != null && contentTextView != null) {
                titleTextView.setText(news.getTitle());
                dateTextView.setText(news.getDate());
                contentTextView.setText(news.getContent());
            }
        }
    }
}