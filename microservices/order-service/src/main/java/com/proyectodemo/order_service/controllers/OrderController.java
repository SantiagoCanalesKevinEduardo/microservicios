package com.proyectodemo.order_service.controllers;

import com.proyectodemo.order_service.model.dto.OrderRequest;
import com.proyectodemo.order_service.model.dto.OrderResponse;
import com.proyectodemo.order_service.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {


        this.orderService.placeOrder(orderRequest);
            return "Order placed successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders() {
        return this.orderService.getAllOrders();
    }
}
