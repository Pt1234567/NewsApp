package com.example.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.model.source;
import com.example.news.model.newsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class customAdapter extends RecyclerView.Adapter<customViewHolder> {
    private Context context;
    private List<newsHeadlines> headlines;

    private  SelectListener listener;

    public customAdapter(Context context, List<newsHeadlines> headlines,SelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener=listener;
    }


    @NonNull
    @Override
    public customViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new customViewHolder(LayoutInflater.from(context).inflate(R.layout.headlines,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull customViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.headline1.setText(headlines.get(position).getTitle());
            holder.headline2.setText(headlines.get(position).getSource().getName());
            if(headlines.get(position).getUrlToImage()!=null){
                Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.img_headline);
            }
            holder.main_cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnNewsClicked(headlines.get(position));
                }
            });
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
