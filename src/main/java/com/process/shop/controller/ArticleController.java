package com.process.shop.controller;


import com.process.shop.model.Article;
import com.process.shop.service.ArticleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody @Valid Article article){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.createArticle(article));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable @Positive Long id){
        return ResponseEntity
                .ok()
                .body(articleService.getArticleById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@RequestBody  @Valid Article article, @PathVariable @Positive Long id){
        article.setId(id);
        return ResponseEntity
                .ok()
                .body(articleService.updateArticle(article, id));
    }
    @GetMapping
    public ResponseEntity<List<Article>> allArticles(){
        return ResponseEntity
                .ok()
                .body(articleService.findAllArticles());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Article>> deleteArticle(@PathVariable @Positive Long id) {
        return ResponseEntity
                .ok()
                .body(articleService.deleteArticle(id));
    }
}
