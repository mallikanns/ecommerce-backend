package com.mallika.ecom.services.customer.cart;

import com.mallika.ecom.dto.AddProductInCartDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
}
