package com.freshsip.freshsip.repository;

import com.freshsip.freshsip.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface OrderItemsRepository  extends JpaRepository<OrderItems, Long> {

    @Query(value="SELECT * FROM order_items WHERE cart_id=?1 ",nativeQuery = true)
    List<OrderItems> getChannelingByChannelingDate(Long cart_id);

    @Modifying
    @Query(value="DELETE FROM order_items WHERE cart_id=?1 ",nativeQuery = true)
     void deleteByCartId(Long cart_id);


    @Query(value = "SELECT oi.* FROM order_items oi " +
            "JOIN order_table o ON oi.cart_id = o.cart_id " +"JOIN items i ON oi.item_id = i.item_id " +
            "WHERE o.create_time = ?1", nativeQuery = true)
    List<OrderItems> getOrderByOrderTime(LocalTime create_time);

    @Query(value = "SELECT oi.cart_id, oi.cart_quantity, oi.item_name, oi.item_price, oi.price, oi.item_id, o.status, oi.cart_items " +
            "FROM order_items oi " +
            "JOIN order_table o ON oi.cart_id = o.cart_id " +
            "JOIN items i ON oi.item_id = i.item_id " +
            "WHERE o.create_date = ?1", nativeQuery = true)
    List<Object[]> getOrderByOrders(LocalDate create_date);


    @Query(value = "SELECT oi.*   FROM order_items oi " +
            "JOIN order_table o ON oi.cart_id = o.cart_id " +"JOIN items i ON oi.item_id = i.item_id " +
            "WHERE o.cart_id = ?1", nativeQuery = true)
    List<OrderItems> getOrderByOrder(Long cart_id);


}
