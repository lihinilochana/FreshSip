package com.freshsip.freshsip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemDTO {

    private Long item_id;
    private String item_name;
    private byte[] image;
    private String description;
    private double item_prize;
    private int quantity;

}

