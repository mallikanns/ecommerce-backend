package com.mallika.ecom.services.admin.adminOrder;

import com.mallika.ecom.dto.AnalyticsResponse;
import com.mallika.ecom.dto.OrderDto;

import java.util.List;

public interface AdminOrderService {
    List<OrderDto> getAllPlacedOrders();

    OrderDto changeOrderStatus(Long orderId, String status);

    AnalyticsResponse calculateAnalytics();
}
