package com.freshsip.freshsip.service;

import com.freshsip.freshsip.dto.OrderItemsDTO;
import com.freshsip.freshsip.entity.OrderItems;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface OrderItemsServiceImpl {
     void saveOrder(OrderItemsDTO orderItemsDTO);
     List<OrderItemsDTO> getAllOrderItems();
     void deleteOrderByOrderID( Long cart_id);
     List<OrderItemsDTO> getChannelingByChannelingDate(Long cart_id);
     List<OrderItems> getOrderByOrderDate(LocalTime create_time);
     List<Object[]> getOrderByOrders(LocalDate create_date);
     List<OrderItems> getOrderByOrder(Long cart_id);
     void updateOrder(Long cart_id, OrderItemsDTO orderItemsDTO);
}
