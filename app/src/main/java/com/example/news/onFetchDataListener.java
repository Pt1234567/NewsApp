package com.example.news;

import java.util.List;
import com.example.news.model.newsHeadlines;

public interface onFetchDataListener <newsApiResponse>{
    void onFetchData(List<newsHeadlines> list, String message);
    void Error(String message);
}
