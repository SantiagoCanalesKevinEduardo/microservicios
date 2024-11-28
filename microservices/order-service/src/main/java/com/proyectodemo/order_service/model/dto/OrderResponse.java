package com.proyectodemo.order_service.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderResponse (Long id, String orderNumber, List<OrderItemsResponse> orderItemsResponse) {

}
