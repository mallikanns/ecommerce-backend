package com.mallika.ecom.services.admin.adminOrder;

import com.mallika.ecom.dto.OrderDto;

import java.util.List;

public interface AdminOrderService {
    List<OrderDto> getAllPlacedOrders();
}
