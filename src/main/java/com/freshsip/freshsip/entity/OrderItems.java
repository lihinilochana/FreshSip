package com.freshsip.freshsip.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long cart_items;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id",nullable = false)
    private Order order;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "item_id",nullable = false)
    private Items items;

    private double price;
    private int cart_quantity;

    private String item_name;
    private double item_price;
}
