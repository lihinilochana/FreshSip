package com.freshsip.freshsip.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private double price;
    private int quantity;
    private String item_name;
    private double item_price;
}

