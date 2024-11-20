package com.example.articlemanagementsystem.Controller;

import com.example.articlemanagementsystem.ApiResponse.ApiResponse;
import com.example.articlemanagementsystem.Model.NewsArticle;
import com.example.articlemanagementsystem.Service.NewsArticleService;
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
    public ResponseEntity getNewsArticle() {
        ArrayList<NewsArticle> newsArticles= newsArticleService.getNewsArticle();
        return ResponseEntity.status(200).body(newsArticles);
    }

    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        newsArticleService.addNewsArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("Article added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticle(@PathVariable String id, @RequestBody @Valid NewsArticle newsArticle, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdate = newsArticleService.updateNewsArticle(id, newsArticle);
        if (isUpdate) {
            return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("id not found"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNewsArticle(@PathVariable String id) {
        boolean isDeleted = newsArticleService.deleteNewsArticle(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("id not found"));

    }

    @PutMapping("/publish/{id}")
    public ResponseEntity publishNewsArticles(@PathVariable String id) {
        int result= newsArticleService.publish(id);
        if (result == 1) {
            return ResponseEntity.status(400).body("NewsArticles is already published");
        }
        else if (result == 2) {
            newsArticleService.publish(id);
            return ResponseEntity.status(200).body("published");
        }else
        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

    @GetMapping("/AllPublish")
    public ResponseEntity getAllPublishNewsArticles() {
        if (newsArticleService.getAllPublishNewsArticles() != null) {
            return ResponseEntity.status(200).body(newsArticleService.getAllPublishNewsArticles());
        }
        return ResponseEntity.status(400).body(new ApiResponse("no Publish NewsArticles "));
    }

@GetMapping("/byCategory/{category}")
    public ResponseEntity byCategory(@PathVariable String category) {
        if (newsArticleService.byCategory(category) != null) {
            return ResponseEntity.status(200).body(newsArticleService.byCategory(category));
        }
        return ResponseEntity.status(400).body(new ApiResponse("no Publish NewsArticles "));
    }


}