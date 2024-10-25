package com.freshsip.freshsip.service;

import com.freshsip.freshsip.dto.OrderItemsDTO;
import com.freshsip.freshsip.dto.ProductDTO;
import com.freshsip.freshsip.entity.Order;
import com.freshsip.freshsip.entity.OrderItems;
import com.freshsip.freshsip.repository.ItemsRepository;
import com.freshsip.freshsip.repository.OrderItemsRepository;
import com.freshsip.freshsip.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderItemsService implements OrderItemsServiceImpl{
    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public void saveOrder(OrderItemsDTO orderItemsDTO) {

    List<ProductDTO> selectedProducts = orderItemsDTO.getSelectedProducts();
    Long cartId = orderItemsDTO.getCart_id();


    Order order = orderRepository.findById(cartId)
            .orElseThrow(() -> new RuntimeException("Order not found"));

    for (ProductDTO product : selectedProducts) {

        com.freshsip.freshsip.entity.Items items = itemsRepository.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("Item not found"));


        OrderItems orderItem = new OrderItems();


        orderItem.setOrder(order);
        orderItem.setItems(items);


        orderItem.setPrice(product.getPrice());
        orderItem.setCart_quantity(product.getQuantity());
        orderItem.setItem_name(product.getItem_name());
        orderItem.setItem_price(product.getItem_price());


        orderItemsRepository.save(orderItem);
    }
}


    @Override
    public List<OrderItemsDTO> getAllOrderItems(){
        List<OrderItems>orderList=orderItemsRepository.findAll();
        return modelMapper.map(orderList,new TypeToken<List<OrderItemsDTO>>(){}.getType());

    }

    @Override
    @Transactional
    public void deleteOrderByOrderID( Long cart_id){
       orderItemsRepository.deleteByCartId(cart_id);

    }

    @Override
    public List<OrderItemsDTO> getChannelingByChannelingDate(Long cart_id){
        List<OrderItems> channelingList=orderItemsRepository.getChannelingByChannelingDate(cart_id);
        return modelMapper.map(channelingList, new TypeToken<List<OrderItemsDTO>>() {}.getType());
    }
    @Override
    public List<OrderItems> getOrderByOrderDate(LocalTime create_time) {
        return orderItemsRepository.getOrderByOrderTime(create_time);
    }
    @Override
    public List<Object[]> getOrderByOrders(LocalDate create_date) {
        return orderItemsRepository.getOrderByOrders(create_date);
    }
    @Override
    public List<OrderItems> getOrderByOrder(Long cart_id) {
        return orderItemsRepository.getOrderByOrder(cart_id);
    }
    @Override
    public void updateOrder(Long cart_id, OrderItemsDTO orderItemsDTO) {

        List<ProductDTO> selectedProducts = orderItemsDTO.getSelectedProducts();
        Long cartId = orderItemsDTO.getCart_id();


        Order order = orderRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        for (ProductDTO product : selectedProducts) {

            com.freshsip.freshsip.entity.Items items = itemsRepository.findById(product.getId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));


            OrderItems orderItem = new OrderItems();


            orderItem.setOrder(order);
            orderItem.setItems(items);


            orderItem.setPrice(product.getPrice());
            orderItem.setCart_quantity(product.getQuantity());
            orderItem.setItem_name(product.getItem_name());
            orderItem.setItem_price(product.getItem_price());


            orderItemsRepository.save(orderItem);
        }
    }

}
