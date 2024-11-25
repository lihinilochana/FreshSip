package com.freshsip.freshsip.service;

import com.freshsip.freshsip.dto.OrderDTO;
import com.freshsip.freshsip.entity.Order;
import com.freshsip.freshsip.entity.User;
import com.freshsip.freshsip.repository.OrderRepository;
import com.freshsip.freshsip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

@Override
public OrderDTO saveOrder(OrderDTO orderDTO) {

    User user = userRepository.findByEmails(orderDTO.getU_email());

    Order order = modelMapper.map(orderDTO, Order.class);

    order.setUser(user);

    orderRepository.save(order);

    return orderDTO;
}

    @Override
    public List<OrderDTO> getAllOrders(){
        List<Order>orderList=orderRepository.findAll();
        return modelMapper.map(orderList,new TypeToken<List<OrderDTO>>(){}.getType());

    }
    @Override
    public OrderDTO getLatestOrderByIndexNo(String u_email) {
        Long latestOrderId = orderRepository.findLatestOrderIdByIndexNo(u_email);
        if (latestOrderId != null) {
            Optional<Order> orderOptional = orderRepository.findById(latestOrderId);
            if (orderOptional.isPresent()) {
                return modelMapper.map(orderOptional.get(), OrderDTO.class);
            }
        }
        return null;
    }
    @Override
    public OrderDTO updateOrderByOrderID( Long cart_id , OrderDTO orderDTO){
        Order existingOrder = orderRepository.findById(cart_id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + cart_id));


        modelMapper.map(orderDTO, existingOrder);


        orderRepository.save(existingOrder);


        return modelMapper.map(existingOrder, OrderDTO.class);
    }

    @Override
    public OrderDTO getCartById(Long cart_id){
        Order order=orderRepository.getCartById(cart_id);
        return modelMapper.map(order,OrderDTO.class);
    }

}
