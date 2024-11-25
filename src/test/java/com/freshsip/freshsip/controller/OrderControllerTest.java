package com.freshsip.freshsip.controller;

import com.freshsip.freshsip.dto.OrderDTO;
import com.freshsip.freshsip.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testGetAllOrders() throws Exception {

        List<OrderDTO> orderList = new ArrayList<>();
        orderList.add(new OrderDTO(1L, LocalDate.now(), LocalTime.now(), 100.50, 1, 123L, "user@example.com"));
        when(orderService.getAllOrders()).thenReturn(orderList);

        mockMvc.perform(get("/FreshSip/Order/Orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].cart_id").value(1));

        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    public void testSaveOrder() throws Exception {

        OrderDTO orderDTO = new OrderDTO(1L, LocalDate.now(), LocalTime.now(), 100.50, 1, 123L, "user@example.com");
        when(orderService.saveOrder(any(OrderDTO.class))).thenReturn(orderDTO);


        mockMvc.perform(post("/FreshSip/Order/adminUser/Order")
                        .contentType("application/json")
                        .content("{\"cart_id\":1,\"create_date\":\"2024-11-23\",\"create_time\":\"10:00:00\",\"full_total\":100.50,\"status\":1,\"id\":123,\"u_email\":\"user@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cart_id").value(1))
                .andExpect(jsonPath("$.u_email").value("user@example.com"));

        verify(orderService, times(1)).saveOrder(any(OrderDTO.class));
    }

    @Test
    public void testGetLatestOrderByUser() throws Exception {

        String userEmail = "user@example.com";
        OrderDTO orderDTO = new OrderDTO(1L, LocalDate.now(), LocalTime.now(), 100.50, 1, 123L, userEmail);
        when(orderService.getLatestOrderByIndexNo(userEmail)).thenReturn(orderDTO);


        mockMvc.perform(get("/FreshSip/Order/adminUser/{u_email}", userEmail))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cart_id").value(1))
                .andExpect(jsonPath("$.u_email").value(userEmail));

        verify(orderService, times(1)).getLatestOrderByIndexNo(userEmail);
    }

    @Test
    public void testGetLatestOrderByUserNotFound() throws Exception {

        String userEmail = "user@example.com";
        when(orderService.getLatestOrderByIndexNo(userEmail)).thenReturn(null);


        mockMvc.perform(get("/FreshSip/Order/adminUser/{u_email}", userEmail))
                .andExpect(status().isNotFound());

        verify(orderService, times(1)).getLatestOrderByIndexNo(userEmail);
    }




    @Test
    public void testGetCartById() throws Exception {

        Long cartId = 1L;
        OrderDTO orderDTO = new OrderDTO(cartId, LocalDate.now(), LocalTime.now(), 100.50, 1, 123L, "user@example.com");
        when(orderService.getCartById(cartId)).thenReturn(orderDTO);


        mockMvc.perform(get("/FreshSip/Order/adminUser/CartById/{cart_id}", cartId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cart_id").value(cartId))
                .andExpect(jsonPath("$.u_email").value("user@example.com"));

        verify(orderService, times(1)).getCartById(cartId);
    }
}
