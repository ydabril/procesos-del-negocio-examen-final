package com.process.shop.service;

import com.process.shop.model.Article;
import com.process.shop.model.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory (Category category);
    Category updateCategory (Category categoryUpdated, Long id);
    List<Category> findAllCategory ();
    Category getCategoryById (Long id);
}
