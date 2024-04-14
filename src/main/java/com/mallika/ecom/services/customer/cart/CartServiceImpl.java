package com.mallika.ecom.services.customer.cart;


import com.mallika.ecom.dto.AddProductInCartDto;

import com.mallika.ecom.dto.CartItemsDto;
import com.mallika.ecom.dto.OrderDto;
import com.mallika.ecom.entity.CartItems;
import com.mallika.ecom.entity.Order;
import com.mallika.ecom.entity.Product;
import com.mallika.ecom.entity.User;
import com.mallika.ecom.enums.OrderStatus;
import com.mallika.ecom.repository.CartItemsRepository;
import com.mallika.ecom.repository.OrderRepository;
import com.mallika.ecom.repository.ProductRepository;
import com.mallika.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId
                (addProductInCartDto.getProductId(), activeOrder.getId(), addProductInCartDto.getUserId());

        if (optionalCartItems.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {
            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());

            if (optionalProduct.isPresent() && optionalUser.isPresent()) {
                CartItems cart = new CartItems();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                CartItems updatedCart = cartItemsRepository.save(cart);

                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
//                activeOrder.getCartItems().add(cart);

                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
    }

    public OrderDto getCartByUserId(Long userId) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
//        List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());
//        because comment private List<CartItems> cartItems; in Order Entity so use method below instead

//        method 1 get form CartItems but can be null
//        List<CartItemsDto> cartItemsDtoList = cartItemsRepository.findByOrderId(activeOrder.getId()).get().stream().map(CartItems::getCartDto).collect(Collectors.toList());

//        method 2 check null by .isPresent()
        Optional<List<CartItems>> cartItemsList = cartItemsRepository.findByOrderId(activeOrder.getId());
        List<CartItemsDto> cartItemsDtoList = new ArrayList<>();
        if (cartItemsList.isPresent()) {
            cartItemsDtoList = cartItemsList.get().stream().map(CartItems::getCartDto).toList();
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());
        orderDto.setCartItems(cartItemsDtoList);

        return orderDto;
    }
}
