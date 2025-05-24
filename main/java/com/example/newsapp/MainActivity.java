package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
// import androidx.fragment.app.FragmentManager; // 如果未使用 findFragmentById，可以移除

import android.os.Bundle;
import android.util.Log; // 用于调试

public class MainActivity extends AppCompatActivity implements NewsListFragment.OnNewsSelectedListener {

    private boolean isLargeScreen;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isLargeScreen = findViewById(R.id.news_detail_container) != null;
        Log.d(TAG, "isLargeScreen: " + isLargeScreen);
    }

    @Override
    public void onNewsSelected(News news) {
        Log.d(TAG, "onNewsSelected: " + (news != null ? news.getTitle() : "null news"));
        if (isLargeScreen) {
            NewsDetailFragment detailFragment =
                    (NewsDetailFragment) getSupportFragmentManager().findFragmentById(R.id.news_detail_container);

            if (detailFragment != null) {
                Log.d(TAG, "DetailFragment found, displaying news.");
                detailFragment.displayNews(news);
            } else {
                Log.e(TAG, "DetailFragment is null, cannot display news. Ensure it's in the layout for large screens.");
                // 如果 detailFragment 是 null，你可能需要动态添加它，或者检查你的大屏幕布局文件
                // 例如，如果 news_detail_container 是一个 FragmentContainerView，且已通过 navGraph 指定了 fragment
                // 那么可能不需要手动 findFragmentById 和调用 displayNews，而是通过 Navigation 参数传递
                // 但基于你原有的代码，这里是更新已存在的 Fragment
            }
        } else {
            Log.d(TAG, "Small screen, navigation should handle detail view.");
            // 小屏幕模式下，NewsListFragment 会通过 Navigation 组件跳转，MainActivity 不直接处理
        }
    }
}