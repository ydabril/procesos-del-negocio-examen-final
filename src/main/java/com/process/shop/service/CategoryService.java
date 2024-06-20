package com.process.shop.service;

import com.process.shop.model.Article;
import com.process.shop.model.Category;
import com.process.shop.repository.ArticleRepository;
import com.process.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService  implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category categoryUpdated, Long id) {
        Optional<Category> categoryBd = categoryRepository.findById(id);
        if(categoryBd.isEmpty()){
            return null;
        }
        return categoryRepository.save(categoryUpdated);
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            return null;
        }
        return category.get();
    }

    @Override
    public List<Category> findAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }
}
