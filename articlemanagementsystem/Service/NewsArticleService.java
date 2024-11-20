package com.example.articlemanagementsystem.Service;

import com.example.articlemanagementsystem.Model.NewsArticle;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Service

public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles=new ArrayList<>();


    public ArrayList<NewsArticle> getNewsArticle (){
        return newsArticles ;
    }


    public void addNewsArticle ( NewsArticle newsArticle){
        newsArticles.add(newsArticle);

    }


    public boolean updateNewsArticle (String id , NewsArticle newsArticle){
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticle.getId().equalsIgnoreCase(id)){
                newsArticles.set(i,newsArticle);
                return true;
            }
        }return false;
    }


public boolean deleteNewsArticle (String id){
    for (int i = 0; i < newsArticles.size(); i++) {
        if(newsArticles.get(i).getId().equalsIgnoreCase(id)){
            newsArticles.remove(i);
        return true;}
        }return false;
}


public int publish ( String id ){
    for (int i = 0; i < newsArticles.size() ; i++) {
        if(newsArticles.get(i).getId().equalsIgnoreCase(id)){
            if (newsArticles.get(i).isPublished()){
                return 1;}
            else {
            newsArticles.get(i).setPublished(true);
            return 2;}
        }
    }return 3;

}

public ArrayList<NewsArticle> getAllPublishNewsArticles (){
        ArrayList<NewsArticle> published =new ArrayList<>();
        for (NewsArticle n :newsArticles){
            if (n.isPublished()){
                published.add(n);
            }
        }return published;
}


public ArrayList<NewsArticle> byCategory (String category){
        ArrayList<NewsArticle> articleByCategory =new ArrayList<>();
        for (NewsArticle n :newsArticles){
            if(n.getCategory().equalsIgnoreCase(category)){
                articleByCategory.add(n);
            }
        }return articleByCategory;
}


}
