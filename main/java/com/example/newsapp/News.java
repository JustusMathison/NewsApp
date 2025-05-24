package com.example.newsapp;

import androidx.room.Entity;
import androidx.room.Ignore; // 确保导入
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "news_table")
public class News {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String title;

    private String summary; // summary 可以为空

    @NonNull
    private String content;

    @NonNull
    private String date;

    // Room 会选择这个无参构造函数
    public News() {
        this.title = "";
        this.content = "";
        this.date = "";
    }

    // 明确告诉 Room 忽略这个构造函数，
    // 因为我们希望 Room 主要通过无参构造函数和 setters/public fields 来工作
    @Ignore
    public News(int id, @NonNull String title, String summary, @NonNull String content, @NonNull String date) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.date = date;
    }

    // 这个构造函数主要用于您的 AddNewsFragment 来创建新的 News 实例
    // 继续保持 @Ignore
    @Ignore
    public News(@NonNull String title, String summary, @NonNull String content, @NonNull String date) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.date = date;
    }

    // Getters and Setters (确保所有字段都有 public getters 和 setters，如果字段本身不是 public 的话)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }
}