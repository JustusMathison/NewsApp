package com.example.newsapp;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation; // 确保这个 import 存在
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton; // 新增 FAB import

import java.util.ArrayList;
import java.util.List;

public class NewsListFragment extends Fragment implements NewsAdapter.OnNewsClickListener {

    private NewsAdapter adapter;
    private OnNewsSelectedListener callback;
    private boolean isLargeScreen = false;
    private NewsDao newsDao;
    private AppPreferences appPreferences;
    private RecyclerView recyclerView;
    private EditText searchEditText;
    private ImageView clearSearchButton;
    private FloatingActionButton fabAddNews; // 新增 FAB 变量
    private static final String TAG = "NewsListFragment";
    private Observer<List<News>> currentObserver;
    private String currentSearchQuery = "";

    public interface OnNewsSelectedListener {
        void onNewsSelected(News news);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach called");
        try {
            callback = (OnNewsSelectedListener) context;
        } catch (ClassCastException e) {
            Log.w(TAG, context.toString() + " must implement OnNewsSelectedListener if in large screen mode");
        }
        appPreferences = new AppPreferences(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        if (getContext() != null) { // 确保 getContext() 不为 null
            newsDao = NewsRoomDatabase.getDatabase(requireContext().getApplicationContext()).newsDao();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called");
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated called");

        if (getActivity() != null) { // 确保 getActivity() 不为 null
            isLargeScreen = requireActivity().findViewById(R.id.news_detail_container) != null;
        }
        Log.d(TAG, "isLargeScreen: " + isLargeScreen);

        searchEditText = view.findViewById(R.id.search_edit_text);
        clearSearchButton = view.findViewById(R.id.clear_search_button);
        recyclerView = view.findViewById(R.id.recycler_news);
        fabAddNews = view.findViewById(R.id.fab_add_news); // 初始化 FAB

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new NewsAdapter(new ArrayList<>(), this, requireContext());
        recyclerView.setAdapter(adapter);

        setupSearchFunctionality();
        setupAddNewsButton(); // 设置 FAB 的点击事件

        loadNews("");
    }

    private void setupSearchFunctionality() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim();
                currentSearchQuery = query;
                Log.d(TAG, "Search query: " + query);
                loadNews(query);
                clearSearchButton.setVisibility(query.isEmpty() ? View.GONE : View.VISIBLE);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        clearSearchButton.setOnClickListener(v -> {
            searchEditText.setText("");
            // clearSearchButton.setVisibility(View.GONE); // 这行会在 onTextChanged 中自动处理
        });
    }

    private void setupAddNewsButton() {
        if (fabAddNews != null) {
            fabAddNews.setOnClickListener(v -> {
                // 使用 Navigation 组件导航到 AddNewsFragment
                // 确保 ID action_newsListFragment_to_addNewsFragment 在 nav_graph.xml 中已定义
                try {
                    Navigation.findNavController(v).navigate(R.id.action_newsListFragment_to_addNewsFragment);
                } catch (IllegalArgumentException e) {
                    Log.e(TAG, "Navigation to AddNewsFragment failed. Check action ID in nav_graph.xml.", e);
                    if (getContext() != null) { // 避免 Toast 因 context 为 null 而崩溃
                        Toast.makeText(getContext(), "无法打开添加新闻页面", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Log.e(TAG, "FloatingActionButton fab_add_news not found in layout.");
        }
    }


    private void loadNews(String searchQuery) {
        if (newsDao == null) { // 增加对 newsDao 的空检查
            Log.e(TAG, "NewsDao is null, cannot load news.");
            if (getContext() != null) {
                Toast.makeText(getContext(), "数据库错误，无法加载新闻列表", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        if (currentObserver != null) {
            // 尝试从所有可能的数据源移除观察者
            if (newsDao.getAllNews() != null) newsDao.getAllNews().removeObserver(currentObserver);
            if (newsDao.searchNews("") != null && !searchQuery.isEmpty()) newsDao.searchNews(searchQuery).removeObserver(currentObserver); // 稍微调整了这里的逻辑
        }

        currentObserver = newsListFromDb -> {
            Log.d(TAG, "News list observer onChanged, list size: " + (newsListFromDb != null ? newsListFromDb.size() : 0));
            Log.d(TAG, "Current search query for observer: " + currentSearchQuery); // 使用成员变量

            if (adapter != null) { // 确保 adapter 不为 null
                adapter.setNewsList(newsListFromDb);
            }


            int lastReadId = appPreferences.getLastReadNewsId();
            News newsToSelect = null;
            int positionToScroll = -1;

            if (newsListFromDb != null && !newsListFromDb.isEmpty()) {
                if (lastReadId != -1) {
                    for (int i = 0; i < newsListFromDb.size(); i++) {
                        if (newsListFromDb.get(i).getId() == lastReadId) {
                            newsToSelect = newsListFromDb.get(i);
                            positionToScroll = i;
                            break;
                        }
                    }
                }
                if (newsToSelect == null && isLargeScreen) { // 大屏幕模式下，如果没找到上次阅读的，默认选第一条
                    newsToSelect = newsListFromDb.get(0);
                    positionToScroll = 0;
                }
            }

            if (positionToScroll != -1 && recyclerView != null) {
                Log.d(TAG, "Scrolling to position: " + positionToScroll);
                recyclerView.scrollToPosition(positionToScroll);
            }

            if (isLargeScreen && newsToSelect != null) {
                if (callback != null) {
                    Log.d(TAG, "Large screen, selecting news: " + newsToSelect.getTitle());
                    callback.onNewsSelected(newsToSelect);
                    if (adapter != null) {
                        adapter.updateLastReadNewsId(newsToSelect.getId());
                    }
                } else {
                    Log.w(TAG, "Callback is null in large screen mode, cannot select news.");
                }
            }
        };

        if (searchQuery.isEmpty()) {
            if (newsDao.getAllNews() != null) newsDao.getAllNews().observe(getViewLifecycleOwner(), currentObserver);
        } else {
            if (newsDao.searchNews(searchQuery) != null) newsDao.searchNews("%" + searchQuery + "%").observe(getViewLifecycleOwner(), currentObserver);
        }
    }

    @Override
    public void onNewsClick(News news) {
        if (news == null) return;
        Log.d(TAG, "onNewsClick: " + news.getTitle());

        appPreferences.setLastReadNewsId(news.getId());
        if (adapter != null) {
            adapter.updateLastReadNewsId(news.getId());
        }

        if (isLargeScreen) {
            if (callback != null) {
                callback.onNewsSelected(news);
            } else {
                Log.w(TAG, "Callback is null in large screen mode during onNewsClick.");
            }
        } else {
            Bundle args = new Bundle();
            args.putInt("newsId", news.getId());
            try {
                // 确保 requireView() 在 Fragment 视图实际创建后调用
                if (getView() != null) {
                    Navigation.findNavController(requireView()).navigate(
                            R.id.action_newsListFragment_to_newsDetailFragment, args);
                } else {
                    Log.e(TAG, "View is null in onNewsClick, cannot navigate.");
                }
            } catch (Exception e) {
                Log.e(TAG, "Navigation error in onNewsClick", e);
            }
        }
    }
}