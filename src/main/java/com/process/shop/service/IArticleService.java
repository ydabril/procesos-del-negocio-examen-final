package com.process.shop.service;

import com.process.shop.model.Article;

import java.util.List;

public interface IArticleService {
    Article createArticle (Article article);
    Article updateArticle (Article articleUpdated, Long id);
    Article getArticleById (Long id);
    List<Article> findAllArticles ();
    List<Article> deleteArticle(Long id);
}
