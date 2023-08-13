package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.news.model.newsHeadlines;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    newsHeadlines headlines;

    TextView news_title,news_author,news_time,news_details,news_content;
    ImageView news_img;
    WebView webView;
    ProgressBar loader;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        webView=findViewById(R.id.webview);
        news_title=findViewById(R.id.newsTitle);
        news_author=findViewById(R.id.news_author);
        news_time=findViewById(R.id.news_time);
        news_details=findViewById(R.id.news_details);
        news_content=findViewById(R.id.news_content);

        news_img=findViewById(R.id.news_Img);

        headlines= (newsHeadlines) getIntent().getSerializableExtra("data");

        news_title.setText(headlines.getTitle());
        news_author.setText(headlines.getAuthor());
        news_time.setText(headlines.getPublishedAt());
        news_details.setText(headlines.getDescription());
        news_content.setText(headlines.getContent());
        webView.loadUrl(headlines.getUrl());

        loader = findViewById(R.id.webViewLoader);
        loader.setVisibility(View.VISIBLE);
        Picasso.get().load(headlines.getUrlToImage()).into(news_img);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(headlines.getUrl());
        webView.setWebViewClient(new WebViewClient());

        if (webView.isShown()){
            loader.setVisibility(View.INVISIBLE);
        }
    }
}