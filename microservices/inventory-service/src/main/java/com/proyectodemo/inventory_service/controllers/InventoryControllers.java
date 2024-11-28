package com.proyectodemo.inventory_service.controllers;

import com.proyectodemo.inventory_service.model.dto.BaseResponse;
import com.proyectodemo.inventory_service.model.dto.OrderItemsRequest;
import com.proyectodemo.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryControllers {
    private final InventoryService inventoryService;


    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku") String sku) {
        return this.inventoryService.isInStock(sku);
    }

    @PostMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse areInStock(@RequestBody List<OrderItemsRequest> orderItems) {
        return this.inventoryService.areInStock(orderItems);
    }


}
