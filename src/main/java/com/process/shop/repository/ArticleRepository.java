package com.process.shop.repository;

import com.process.shop.model.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    Optional<Article> findByCode(String code);
}
