package com.proyectodemo.product_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // sirve para crear objetos de la clase sin necesidad de usar el constructor ejemplo: Product product = Product.builder().name("product").price(100).build();
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    private String name;
    private String description;
    private Double price;
    private Boolean status;

}
