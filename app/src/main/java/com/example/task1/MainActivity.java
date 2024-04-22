package com.example.task1;

import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewHorizontal;
    private RecyclerView recyclerViewRelatedNews;
    private GridView gridViewNews;
    // news list
    private List<NewsItem> newsItems;

    private RelativeLayout listLayout;
    private LinearLayout detailLayout;

    private TextView textViewTitleDetail;
    private TextView textViewContentDetail;

    private ImageView imageViewNewsDetail;

    private boolean isDetailPage = false;

    private NewsApiClient newsApiClient;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsApiClient = new NewsApiClient();

        this.context = this;
        showNewsListPage();
    }

    private void getTopNews() {
        new Thread(() -> {
            try {
                 List<NewsItem> newsItems = newsApiClient.fetchTopNews();
                 runOnUiThread(() -> {
                     HorizontalImageAdapter horizontalImageAdapter = new HorizontalImageAdapter(newsItems, this, this);
                     recyclerViewHorizontal.setAdapter(horizontalImageAdapter);
                 });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }





    private void getNewsList() {
        new Thread(() -> {
            try {
                List<NewsItem> newsItems = newsApiClient.fetchListNews();
                runOnUiThread(() -> {
                    NewsGridAdapter newsGridAdapter = new NewsGridAdapter(this, newsItems, this);
                    gridViewNews.setAdapter(newsGridAdapter);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void getRelatedNews() {
        new Thread(() -> {
            try {
                List<NewsItem> newsItems = newsApiClient.fetchRelatedNews();
                runOnUiThread(() -> {
                    RelatedNewsAdapter relatedNewsAdapter = new RelatedNewsAdapter(newsItems, this);
                    recyclerViewRelatedNews.setAdapter(relatedNewsAdapter);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    // 点击事件：显示新闻详情页
    public void showNewsDetailPage(NewsItem newsItem) {
        setContentView(R.layout.news_detail_layout);

        textViewTitleDetail = findViewById(R.id.textViewTitleDetail);
        textViewContentDetail = findViewById(R.id.textViewContentDetail);


        // 设置新闻详情页的内容
        textViewTitleDetail.setText(newsItem.getTitle());
        textViewContentDetail.setText(newsItem.getDescription());

        imageViewNewsDetail = findViewById(R.id.imageViewNewsDetail);
        Glide.with(context).load(newsItem.getImageUrl()).into(imageViewNewsDetail);


        recyclerViewRelatedNews = findViewById(R.id.recyclerViewRelatedNews);

        // 创建并设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewRelatedNews.setLayoutManager(layoutManager);

        getRelatedNews();

        isDetailPage = true;

    }

    // 返回新闻列表页
    public void showNewsListPage() {
        setContentView(R.layout.activity_main);

        // 初始化视图
        recyclerViewHorizontal = findViewById(R.id.recyclerViewHorizontal);
        gridViewNews = findViewById(R.id.gridViewNews);


        // 设置水平滚动图片列表的布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHorizontal.setLayoutManager(layoutManager);

        getTopNews();

        getNewsList();

        isDetailPage = false;
    }

    @Override
    public void onBackPressed() {
        if (isDetailPage) {
            showNewsListPage();
        } else {
            super.onBackPressed();
        }
    }
}

