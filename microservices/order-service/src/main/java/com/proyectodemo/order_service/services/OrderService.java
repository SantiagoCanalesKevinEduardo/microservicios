package com.proyectodemo.order_service.services;

import com.proyectodemo.order_service.model.dto.*;
import com.proyectodemo.order_service.model.entities.Order;
import com.proyectodemo.order_service.model.entities.OrderItems;
import com.proyectodemo.order_service.repositories.OrderRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        // Check for inventory
        BaseResponse result= this.webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/api/inventory/in-stock")
                .bodyValue(orderRequest.getOrderItemsRequests())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();


        if(result!=null && !result.hasErrors()){
            Order order = new Order();
            order.setOrderNumber(UUID.class.toString());
            order.setOrderItems(orderRequest.getOrderItemsRequests().stream()
                    .map(orderItemRequest -> mapOrderItemsRequestToOrderItem(orderItemRequest,order))
                    .toList());
            this.orderRepository.save(order);
        }else {
            throw new RuntimeException("Out of stock");
        }


    }


    private OrderItems mapOrderItemsRequestToOrderItem(@NonNull OrderItemsRequest orderItemsRequest, Order order){
        return OrderItems.builder()
                .order(order)
                .id(orderItemsRequest.getId())
                .quantity(orderItemsRequest.getQuantity())
                .price(orderItemsRequest.getPrice())
                .sku(orderItemsRequest.getSku())
                .order(order)
                .build();
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = this.orderRepository.findAll();
        return orders.stream().map(this::mapToOrderResponse).toList();


    }

    private OrderResponse mapToOrderResponse(Order order) {
        return new OrderResponse(order.getId(), order.getOrderNumber(),
                order.getOrderItems().stream().map(this::mapToOrderItemResponse).toList());

    }

    private OrderItemsResponse mapToOrderItemResponse(OrderItems orderItems) {
        return new OrderItemsResponse(orderItems.getId(), orderItems.getSku(), orderItems.getPrice(), orderItems.getQuantity());
    }



}
