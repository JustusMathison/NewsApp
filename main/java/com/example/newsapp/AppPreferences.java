package com.example.newsapp;

import android.content.Context; // <--- 确保这个 import 存在
import android.content.SharedPreferences;

public class AppPreferences {

    private static final String PREF_NAME = "NewsAppPrefs";
    private static final String KEY_LAST_READ_NEWS_ID = "last_read_news_id";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    // 构造函数接收 android.content.Context
    public AppPreferences(Context context) { // Context 类型在这里是正确的
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLastReadNewsId(int newsId) {
        editor.putInt(KEY_LAST_READ_NEWS_ID, newsId);
        editor.apply();
    }

    public int getLastReadNewsId() {
        return sharedPreferences.getInt(KEY_LAST_READ_NEWS_ID, -1);
    }
}