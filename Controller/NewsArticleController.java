package com.example.newsarticlemanagementsystem.Controller;

import com.example.newsarticlemanagementsystem.Api.ApiResponse;
import com.example.newsarticlemanagementsystem.Model.NewsArticle;
import com.example.newsarticlemanagementsystem.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/article")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @GetMapping("/get")
    public ResponseEntity getNewsArticles(){
        return ResponseEntity.status(200).body(newsArticleService.getNewsArticles());
    }

    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        newsArticleService.addNewsArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("News article added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticle(@PathVariable String id ,@RequestBody @Valid NewsArticle newsArticle, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        boolean isUpdated = newsArticleService.updateNewsArticle(newsArticle, id);

        if(isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("News article updated"));

        return ResponseEntity.status(400).body(new ApiResponse("News article not found"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNewsArticle(@PathVariable String id){

        boolean isDeleted = newsArticleService.deleteNewsArticle(id);

        if(isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("News article deleted"));

        return ResponseEntity.status(400).body(new ApiResponse("News article not found"));

    }

    @PutMapping("/publish/{id}")
    public ResponseEntity publishNewsArticle(@PathVariable String id){

        boolean isPublished = newsArticleService.publishNewsArticle(id);

        if(isPublished)
            return ResponseEntity.status(200).body(new ApiResponse("News article published successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("News article not found"));

    }


    @GetMapping("/get/publishedArticles")
    public ResponseEntity getAllPublishedArticles(){

        ArrayList<NewsArticle> list = newsArticleService.getAllPublishedArticles();

        if(list.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("News article not found"));

        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/get/{category}")
    public ResponseEntity getByCategory(@PathVariable String category){

        ArrayList<NewsArticle> list = newsArticleService.getByCategory(category);

        if(list.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("News article not found"));

        return ResponseEntity.status(200).body(list);
    }
}
