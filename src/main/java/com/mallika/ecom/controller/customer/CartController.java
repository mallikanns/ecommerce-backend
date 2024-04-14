package com.mallika.ecom.controller.customer;

import com.mallika.ecom.dto.AddProductInCartDto;
import com.mallika.ecom.dto.OrderDto;
import com.mallika.ecom.exceptions.ValidationException;
import com.mallika.ecom.services.customer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto) {
        return cartService.addProductToCart(addProductInCartDto);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {
        OrderDto orderDto = cartService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/coupon/{userId}/{code}")
    public ResponseEntity<?> applyCoupon(@PathVariable Long userId, @PathVariable String code) {
        try {
            OrderDto orderDto = cartService.applyCoupon(userId, code);
            return ResponseEntity.ok(orderDto);
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
