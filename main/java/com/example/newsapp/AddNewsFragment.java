package com.example.newsapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNewsFragment extends Fragment {

    private TextInputEditText editTextTitle;
    private TextInputEditText editTextSummary;
    private TextInputEditText editTextContent;
    private TextInputEditText editTextDate;
    private Button buttonSaveNews;

    private NewsDao newsDao;

    public AddNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContext() != null) {
            newsDao = NewsRoomDatabase.getDatabase(requireContext().getApplicationContext()).newsDao();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextTitle = view.findViewById(R.id.editText_news_title);
        editTextSummary = view.findViewById(R.id.editText_news_summary);
        editTextContent = view.findViewById(R.id.editText_news_content);
        editTextDate = view.findViewById(R.id.editText_news_date);
        buttonSaveNews = view.findViewById(R.id.button_save_news);

        // 自动填充当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        editTextDate.setText(sdf.format(new Date()));

        buttonSaveNews.setOnClickListener(v -> saveNews());
    }

    private void saveNews() {
        String title = editTextTitle.getText() != null ? editTextTitle.getText().toString().trim() : "";
        String summary = editTextSummary.getText() != null ? editTextSummary.getText().toString().trim() : "";
        String content = editTextContent.getText() != null ? editTextContent.getText().toString().trim() : "";
        String date = editTextDate.getText() != null ? editTextDate.getText().toString().trim() : "";

        if (TextUtils.isEmpty(title)) {
            editTextTitle.setError("标题不能为空");
            editTextTitle.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(content)) {
            editTextContent.setError("内容不能为空");
            editTextContent.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(date)) {
            editTextDate.setError("日期不能为空");
            editTextDate.requestFocus();
            return;
        }
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            editTextDate.setError("日期格式应为 yyyy-MM-dd");
            editTextDate.requestFocus();
            return;
        }

        // 使用 News(title, summary, content, date) 构造函数
        News newNews = new News(title, summary, content, date);

        if (newsDao != null) {
            NewsRoomDatabase.databaseWriteExecutor.execute(() -> {
                newsDao.insert(newNews);
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "新闻已保存", Toast.LENGTH_SHORT).show();
                        NavHostFragment.findNavController(AddNewsFragment.this).popBackStack();
                    });
                }
            });
        } else {
            if (getContext() != null) { // 添加 getContext() 判空
                Toast.makeText(getContext(), "数据库错误，无法保存新闻", Toast.LENGTH_SHORT).show();
            }
        }
    }
}