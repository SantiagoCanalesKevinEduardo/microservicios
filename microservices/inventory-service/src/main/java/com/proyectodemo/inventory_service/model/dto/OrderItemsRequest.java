package com.proyectodemo.inventory_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsRequest {

    private Long id;
    private String sku;
    private Double price;
    private Long quantity;

}
