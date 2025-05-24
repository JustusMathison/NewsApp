package com.example.newsapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News news);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<News> newsList);

    @Query("SELECT * FROM news_table ORDER BY id DESC") // 假设ID越大越新，或者按日期
    LiveData<List<News>> getAllNews();

    @Query("SELECT * FROM news_table WHERE id = :newsId")
    LiveData<News> getNewsById(int newsId);

    @Query("DELETE FROM news_table")
    void deleteAllNews(); // 用于开发或特定场景

    // 添加搜索功能 - 搜索标题和内容
    @Query("SELECT * FROM news_table WHERE title LIKE '%' || :searchQuery || '%' OR content LIKE '%' || :searchQuery || '%' ORDER BY id DESC")
    LiveData<List<News>> searchNews(String searchQuery);

    // 添加只搜索标题的功能
    @Query("SELECT * FROM news_table WHERE title LIKE '%' || :searchQuery || '%' ORDER BY id DESC")
    LiveData<List<News>> searchNewsByTitle(String searchQuery);
}