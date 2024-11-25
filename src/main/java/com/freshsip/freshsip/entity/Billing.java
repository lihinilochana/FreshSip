package com.freshsip.freshsip.entity;



import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bill_records")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    private LocalDate date;
    private LocalTime time;
    private Double cash;
    private Double balance;
    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "full_total")
    private Double fullTotal;

    public Long getBillId() {
        return billId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public Double getCash() {
        return cash;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }


    public Double getFullTotal() {
        return fullTotal;
    }

    public void setFullTotal(Double fullTotal) {
        this.fullTotal = fullTotal;
    }
}
