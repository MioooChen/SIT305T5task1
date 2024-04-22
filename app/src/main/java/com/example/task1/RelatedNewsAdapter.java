package com.example.task1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.ViewHolder> {

    private List<NewsItem> relatedNewsList;
    private Context context;

    public RelatedNewsAdapter(List<NewsItem> relatedNewsList, Context context) {
        this.relatedNewsList = relatedNewsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_related_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsItem relatedNewsItem = relatedNewsList.get(position);
        holder.textViewTitle.setText(relatedNewsItem.getTitle());
        Glide.with(context).load(relatedNewsItem.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return relatedNewsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewContent;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewContent = itemView.findViewById(R.id.textViewContent);
            imageView = itemView.findViewById(R.id.imageViewNews);
        }
    }
}
