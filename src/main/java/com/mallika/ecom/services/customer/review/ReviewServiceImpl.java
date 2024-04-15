package com.mallika.ecom.services.customer.review;

import com.mallika.ecom.dto.CartItemsDto;
import com.mallika.ecom.dto.OrderedProductsResponseDto;
import com.mallika.ecom.dto.ProductDto;
import com.mallika.ecom.dto.ReviewDto;
import com.mallika.ecom.entity.*;
import com.mallika.ecom.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final OrderRepository orderRepository;

    private final CartItemsRepository cartItemsRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    public OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        OrderedProductsResponseDto orderedProductsResponseDto = new OrderedProductsResponseDto();
        if (optionalOrder.isPresent()) {
            orderedProductsResponseDto.setOrderAmount(optionalOrder.get().getAmount());
//        because comment private List<CartItems> cartItems; in Order Entity so use method below instead
//            List<ProductDto> productDtoList = new ArrayList<>();
//            for (CartItems cartItems: optionalOrder.get().getCartItems()) {
//                ProductDto productDto = new ProductDto();
//
//                productDto.setId(cartItems.getProduct().getId());
//                productDto.setName(cartItems.getProduct().getName());
//                productDto.setPrice(cartItems.getPrice());
//                productDto.setQuantity(cartItems.getQuantity());
//                productDto.setByteImg(cartItems.getProduct().getImg());
//
//                productDtoList.add(productDto);
//            }

//          get form CartItems & check null by .isPresent()
            List<ProductDto> productDtoList = new ArrayList<>();
            Optional<List<CartItems>> cartItemsList = cartItemsRepository.findByOrderId(optionalOrder.get().getId());
            if (cartItemsList.isPresent()) {
                for (CartItems cartItems: cartItemsList.get()) {
                    ProductDto productDto = new ProductDto();

                    productDto.setId(cartItems.getProduct().getId());
                    productDto.setName(cartItems.getProduct().getName());
                    productDto.setPrice(cartItems.getPrice());
                    productDto.setQuantity(cartItems.getQuantity());
                    productDto.setByteImg(cartItems.getProduct().getImg());

                    productDtoList.add(productDto);
                }
            }

            orderedProductsResponseDto.setProductDtoList(productDtoList);
        }
        return orderedProductsResponseDto;
    }

    public ReviewDto giveReview(ReviewDto reviewDto) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(reviewDto.getProductId());
        Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());

        if (optionalProduct.isPresent() && optionalUser.isPresent()) {
            Review review = new Review();

            review.setRating(reviewDto.getRating());
            review.setDescription(reviewDto.getDescription());
            review.setUser(optionalUser.get());
            review.setProduct(optionalProduct.get());
            review.setImg(reviewDto.getImg().getBytes());

            return reviewRepository.save(review).getDto();
        }
        return null;
    }
}
