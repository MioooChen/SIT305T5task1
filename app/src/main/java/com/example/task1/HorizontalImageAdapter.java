package com.example.task1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
public class HorizontalImageAdapter extends RecyclerView.Adapter<HorizontalImageAdapter.ImageViewHolder> {

    private List<NewsItem> newsItems;
    private MainActivity mainActivity;
    private Context context;

    public HorizontalImageAdapter(List<NewsItem> newsItems, MainActivity mainActivity, Context context) {
        this.newsItems = newsItems;
        this.mainActivity = mainActivity;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_horizontal, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        NewsItem newsItem = newsItems.get(position);
        Glide.with(context).load(newsItem.getImageUrl()).into(holder.imageView);

        holder.imageView.setOnClickListener((v) -> {
            mainActivity.showNewsDetailPage(newsItem);
        });
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewHorizontal);
        }
    }
}