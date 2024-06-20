package com.process.shop.service;

import com.process.shop.exceptions.AlreadyExistsException;
import com.process.shop.exceptions.NotFoundException;
import com.process.shop.model.Article;
import com.process.shop.model.User;
import com.process.shop.model.enums.ErrorMessages;
import com.process.shop.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService implements IArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article createArticle(Article article) {
        Optional<Article> articleFindByCode = articleRepository.findByCode(article.getCode());
        if(articleFindByCode.isPresent()){
            throw new AlreadyExistsException(ErrorMessages.ARTICLE_CODE_EXISTS.getMessage());
        }
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(Article articleUpdated, Long id) {
        Optional<Article> articleBd = articleRepository.findById(id);
        if(articleBd.isEmpty()){
            throw new NotFoundException(ErrorMessages.ARTICLE_NOT_FOUND.getMessage());
        }
        return articleRepository.save(articleUpdated);
    }

    @Override
    public Article getArticleById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if(article.isEmpty()){
            throw new NotFoundException(ErrorMessages.ARTICLE_NOT_FOUND.getMessage());
        }
        return article.get();
    }

    @Override
    public List<Article> findAllArticles() {
        return (List<Article>) articleRepository.findAll();
    }

    public List<Article> deleteArticle(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if(article.isEmpty()) {
            throw new NotFoundException(ErrorMessages.ARTICLE_NOT_FOUND.getMessage());
        }
        articleRepository.deleteById(id);
        return (List<Article>) articleRepository.findAll();
    }
}
