package com.freshsip.freshsip.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long cart_id;
    private LocalDate create_date;
    private LocalTime create_time;
    private double full_total;
    private int status;
    private Long userId;
    private String u_email;

}
