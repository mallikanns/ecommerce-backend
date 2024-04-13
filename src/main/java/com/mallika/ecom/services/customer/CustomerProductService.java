package com.mallika.ecom.services.customer;

import com.mallika.ecom.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {
    List<ProductDto> searchProductByTitle(String title);

    List<ProductDto> getAllProducts();
}
