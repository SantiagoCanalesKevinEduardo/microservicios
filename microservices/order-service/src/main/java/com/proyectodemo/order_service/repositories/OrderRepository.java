package com.proyectodemo.order_service.repositories;

import com.proyectodemo.order_service.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
