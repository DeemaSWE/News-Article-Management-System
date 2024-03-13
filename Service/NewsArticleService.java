package com.example.newsarticlemanagementsystem.Service;

import com.example.newsarticlemanagementsystem.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();
    public ArrayList<NewsArticle> getNewsArticles(){
        return newsArticles;
    }

    public void addNewsArticle(NewsArticle newsArticle) {
        newsArticles.add(newsArticle);
    }

    public boolean updateNewsArticle(NewsArticle newsArticle, String id) {
        for(NewsArticle n : newsArticles){
            if(n.getId().equals(id)){
                newsArticles.set(newsArticles.indexOf(n), newsArticle);
                return true;
            }
        }
        return false;
    }

    public boolean deleteNewsArticle(String id) {
        for(NewsArticle n : newsArticles){
            if(n.getId().equals(id)){
                newsArticles.remove(n);
                return true;
            }
        }
        return false;
    }

    public boolean publishNewsArticle(String id) {
        for(NewsArticle n : newsArticles){
            if(n.getId().equals(id)){
                n.setPublished(true);
                return true;
            }
        }
        return false;
    }

    public ArrayList<NewsArticle> getAllPublishedArticles() {

        ArrayList<NewsArticle> list = new ArrayList<>();

        for(NewsArticle n : newsArticles){
            if(n.isPublished())
                list.add(n);
        }

        return list;
    }

    public ArrayList<NewsArticle> getByCategory(String category) {

        ArrayList<NewsArticle> list = new ArrayList<>();

        for(NewsArticle n : newsArticles){
            if(n.getCategory().equals(category))
                list.add(n);
        }

        return list;
    }
}
