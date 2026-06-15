package com.microservices.cart_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_items")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer productId;

    private String productName;

    private Double price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")

    @JsonIgnore
    private Cart cart;
}