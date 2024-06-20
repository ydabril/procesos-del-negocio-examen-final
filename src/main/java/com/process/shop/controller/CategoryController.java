package com.process.shop.controller;

import com.process.shop.model.Category;
import com.process.shop.model.User;
import com.process.shop.service.CategoryService;
import com.process.shop.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @PostMapping("user")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(user));
    }
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody @Valid Category category){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.createCategory(category));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody @Valid Category category, @PathVariable @Positive Long id){
        category.setId(id);
        return ResponseEntity
                .ok()
                .body(categoryService.updateCategory(category, id));
    }
    @GetMapping
    public ResponseEntity<List<Category>> allCategory(){
        return ResponseEntity
                .ok()
                .body(categoryService.findAllCategory());
    }
}
