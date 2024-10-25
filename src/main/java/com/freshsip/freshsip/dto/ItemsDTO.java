package com.freshsip.freshsip.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemsDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long item_id;
    private String item_name;
    private double item_price;
}
