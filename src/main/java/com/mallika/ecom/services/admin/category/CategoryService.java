package com.mallika.ecom.services.admin.category;

import com.mallika.ecom.dto.CategoryDto;
import com.mallika.ecom.entity.Category;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);
}
