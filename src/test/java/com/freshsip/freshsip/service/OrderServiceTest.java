package com.freshsip.freshsip.service;


import com.freshsip.freshsip.dto.OrderDTO;
import com.freshsip.freshsip.entity.Order;
import com.freshsip.freshsip.entity.User;
import com.freshsip.freshsip.repository.OrderRepository;
import com.freshsip.freshsip.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderService orderService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


//    @Test
//    public void testSaveOrder() {
//
//        String email = "test@example.com";
//        User mockUser = new User();
//        mockUser.setEmail(email);
//
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setU_email(email);
//
//        Order mockOrder = new Order();
//
//        when(userRepository.findByEmails(email)).thenReturn(mockUser);
//        when(modelMapper.map(orderDTO, Order.class)).thenReturn(mockOrder);
//
//
//        OrderDTO result = orderService.saveOrder(orderDTO);
//
//
//        verify(userRepository, times(1)).findByEmail(email);
//        verify(modelMapper, times(1)).map(orderDTO, Order.class);
//        verify(orderRepository, times(1)).save(mockOrder);
//
//        assertNotNull(result);
//        assertEquals(orderDTO, result);
//    }

    @Test
    public void testGetAllOrders() {

        Order order = new Order();
        List<Order> orderList = Arrays.asList(order);

        OrderDTO orderDTO = new OrderDTO();
        List<OrderDTO> orderDTOList = Arrays.asList(orderDTO);

        when(orderRepository.findAll()).thenReturn(orderList);
        when(modelMapper.map(orderList, new TypeToken<List<OrderDTO>>() {}.getType())).thenReturn(orderDTOList);


        List<OrderDTO> result = orderService.getAllOrders();


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(orderDTOList, result);

        verify(orderRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(orderList, new TypeToken<List<OrderDTO>>() {}.getType());
    }


    @Test
    public void testUpdateOrderByOrderID_OrderNotFound() {

        Long cartId = 1L;
        OrderDTO orderDTO = new OrderDTO();

        when(orderRepository.findById(cartId)).thenReturn(Optional.empty());


        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.updateOrderByOrderID(cartId, orderDTO);
        });

        assertEquals("Order not found with id: " + cartId, exception.getMessage());

        verify(orderRepository, times(1)).findById(cartId);
        verify(modelMapper, never()).map(any(), any());
        verify(orderRepository, never()).save(any());
    }

    @Test
    public void testGetCartById_Success() {

        Long cartId = 1L;
        Order order = new Order();
        order.setCart_id(cartId);
        order.setStatus(0);
        order.setFull_total(100.0);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(cartId);
        orderDTO.setStatus(0);
        orderDTO.setFull_total(100.0);

        when(orderRepository.getCartById(cartId)).thenReturn(order);
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);


        OrderDTO result = orderService.getCartById(cartId);


        assertNotNull(result);
        assertEquals(cartId, result.getUserId());
        assertEquals(0, result.getStatus());
        assertEquals(100.0, result.getFull_total());

        verify(orderRepository, times(1)).getCartById(cartId);
        verify(modelMapper, times(1)).map(order, OrderDTO.class);
    }

    @Test
    public void testGetCartById_CartNotFound() {

        Long cartId = 1L;

        when(orderRepository.getCartById(cartId)).thenReturn(null);


        OrderDTO result = orderService.getCartById(cartId);


        assertNull(result);

        verify(orderRepository, times(1)).getCartById(cartId);
        verify(modelMapper, never()).map(any(), any());
    }
}
