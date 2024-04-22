package com.example.task1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsGridAdapter extends BaseAdapter {

    private Context context;
    private List<NewsItem> newsList;
    private MainActivity mainActivity;

    public NewsGridAdapter(Context context, List<NewsItem> newsList, MainActivity mainActivity) {
        this.context = context;
        this.newsList = newsList;
        this.mainActivity = mainActivity;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_news_grid, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.imageViewNews);
            holder.textViewTitle = convertView.findViewById(R.id.textViewTitle);
            holder.textViewContent = convertView.findViewById(R.id.textViewContent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 获取当前位置的新闻项
        final NewsItem newsItem = newsList.get(position);

        // 设置新闻项的数据
        Glide.with(context).load(newsItem.getImageUrl()).into(holder.imageView);

        holder.textViewTitle.setText(newsItem.getTitle());
        holder.textViewContent.setText(newsItem.getDescription());

        // 设置点击事件监听器
        convertView.setOnClickListener(v -> {
            // 点击事件：显示新闻详情页
            if (mainActivity != null) {
                mainActivity.showNewsDetailPage(newsItem);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textViewTitle;

        TextView textViewContent;
    }
}
