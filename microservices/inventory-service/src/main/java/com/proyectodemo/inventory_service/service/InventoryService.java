package com.proyectodemo.inventory_service.service;

import com.proyectodemo.inventory_service.model.dto.BaseResponse;
import com.proyectodemo.inventory_service.model.dto.OrderItemsRequest;
import com.proyectodemo.inventory_service.model.entities.Inventory;
import com.proyectodemo.inventory_service.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String sku) {
        var inventory = this.inventoryRepository.findBySku(sku);
        return inventory.filter(value -> value.getQuantity() > 0).isPresent();
    }

    public BaseResponse areInStock(List<OrderItemsRequest> orderItems) {
        var errorList = new ArrayList<String>();
        List<String> skus = orderItems.stream().map(OrderItemsRequest::getSku).toList();

        List<Inventory> invtoryList = this.inventoryRepository.findBySkuIn(skus);

        orderItems.forEach(orderItem -> {
            var inventory = invtoryList.stream().filter(value -> value.getSku().equals(orderItem.getSku())).findFirst();
            if (inventory.isEmpty()) {
                errorList.add("Product with sku " + orderItem.getSku() + " does not exist");

            } else if (inventory.get().getQuantity() < orderItem.getQuantity()) {
                errorList.add("Product with sku " + orderItem.getSku() + " does not have enough stock");
            }
        });

        return errorList.size()>0 ? new BaseResponse(errorList.toArray(new String[0])): new BaseResponse(null);

    }

}
