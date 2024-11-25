package com.freshsip.freshsip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long item_id;

    @Column(name = "item_name")
    private String item_name;
    @Lob
    @Column(name = "image" , columnDefinition = "MEDIUMBLOB")
    private byte[] image;
    @Column(name = "description")
    private String description;
    @Column(name = "item_prize")
    private double item_prize;
    @Column(name="quantity")
    private int quantity;


    @OneToMany(mappedBy = "items", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems;

}
