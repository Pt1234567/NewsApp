package com.example.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class customViewHolder extends RecyclerView.ViewHolder {

    TextView headline1,headline2;
    ImageView img_headline;
    CardView main_cardview;
    public customViewHolder(@NonNull View itemView) {
        super(itemView);

        headline1=itemView.findViewById(R.id.news_heading1);
        headline2=itemView.findViewById(R.id.news_heading2);
        img_headline=itemView.findViewById(R.id.headline_img);
        main_cardview=itemView.findViewById(R.id.main_container);
    }
}
