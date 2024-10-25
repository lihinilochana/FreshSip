package com.freshsip.freshsip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Items {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long item_id;
    private String item_name;
    private double item_prize;

}
