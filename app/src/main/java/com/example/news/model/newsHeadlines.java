package com.example.news.model;

import java.io.Serializable;

import javax.xml.transform.Source;

public class newsHeadlines implements Serializable {


    public com.example.news.model.source getSource() {
        return source;
    }

    public void setSource(com.example.news.model.source source) {
        this.source = source;
    }

    source source;        //in order to understand this see get request in postman of api
    String author;
    String title;
    String description;
    String url;
    String urlToImage;
    String publishedAt;
    String content;



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
