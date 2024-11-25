package com.freshsip.freshsip.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freshsip.freshsip.dto.OrderItemsDTO;
import com.freshsip.freshsip.service.OrderItemsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderItemsController.class)
class OrderItemsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderItemsService orderItemsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetOrderItems() throws Exception {
        OrderItemsDTO order = new OrderItemsDTO(1L, 2L, null, "Test Item", 150.0);
        Mockito.when(orderItemsService.getAllOrderItems()).thenReturn(List.of(order));

        mockMvc.perform(get("/FreshSip/Order/OrdersItems"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cart_items").value(1L))
                .andExpect(jsonPath("$[0].cart_id").value(2L))
                .andExpect(jsonPath("$[0].item_name").value("Test Item"))
                .andExpect(jsonPath("$[0].item_price").value(150.0));
    }

    @Test
    void testSubmitOrder() throws Exception {
        OrderItemsDTO order = new OrderItemsDTO(1L, 2L, null, "Test Item", 150.0);
        doNothing().when(orderItemsService).saveOrder(any(OrderItemsDTO.class));

        mockMvc.perform(post("/FreshSip/Order/adminUser/OrderItems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Order successfully submitted"));
    }

    @Test
    void testDeleteOrderByOrderID() throws Exception {
        doNothing().when(orderItemsService).deleteOrderByOrderID(1L);

        mockMvc.perform(delete("/FreshSip/Order/adminUser/OrderItemsByOrderItemsID/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetOrderLatest() throws Exception {
        OrderItemsDTO order = new OrderItemsDTO(1L, 2L, null, "Test Item", 150.0);
        Mockito.when(orderItemsService.getChannelingByChannelingDate(1L)).thenReturn(List.of(order));

        mockMvc.perform(get("/FreshSip/Order/OrderItemsLatest/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cart_items").value(1L))
                .andExpect(jsonPath("$[0].cart_id").value(2L))
                .andExpect(jsonPath("$[0].item_name").value("Test Item"))
                .andExpect(jsonPath("$[0].item_price").value(150.0));
    }


    @Test
    void testUpdateOrder() throws Exception {
        OrderItemsDTO updatedOrder = new OrderItemsDTO(1L, 2L, null, "Updated Item", 200.0);
        doNothing().when(orderItemsService).updateOrder(1L, updatedOrder);

        mockMvc.perform(put("/FreshSip/Order/adminUser/OrderItemsOrder/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedOrder)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Order successfully submitted"));
    }
}
