package com.mallika.ecom.controller.admin;

import com.mallika.ecom.dto.CategoryDto;
import com.mallika.ecom.entity.Category;
import com.mallika.ecom.services.admin.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminCategoryController {
//    private final CategoryService categoryService;
    @Autowired
    private  CategoryService categoryService;

    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

}
