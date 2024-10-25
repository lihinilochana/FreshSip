package com.freshsip.freshsip.repository;

import com.freshsip.freshsip.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository  extends JpaRepository<Order, Long> {
    @Query(value = "SELECT cart_id FROM order_table WHERE u_email= ?1 ORDER BY create_time DESC LIMIT 1", nativeQuery = true)
    Long findLatestOrderIdByIndexNo(String u_email);

    @Query(value="SELECT * FROM order_table WHERE cart_id=?1",nativeQuery = true)
    Order updateOrderByOrderID(Long cart_id);

    @Query(value="SELECT * FROM order_table WHERE cart_id=?1 ",nativeQuery = true)
    Order getCartById(Long cart_id);

}