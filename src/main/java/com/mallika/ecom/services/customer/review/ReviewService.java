package com.mallika.ecom.services.customer.review;

import com.mallika.ecom.dto.OrderedProductsResponseDto;
import com.mallika.ecom.dto.ReviewDto;

import java.io.IOException;

public interface ReviewService {
    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);

    ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
