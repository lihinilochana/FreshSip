package com.freshsip.freshsip.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderDTOTest {

    @Test
    void testOrderDTOGettersAndSetters() {

        OrderDTO orderDTO = new OrderDTO();
        Long cartId = 1L;
        LocalDate createDate = LocalDate.of(2024, 11, 22);
        LocalTime createTime = LocalTime.of(12, 30);
        double fullTotal = 200.50;
        int status = 1;
        Long id = 100L;
        String uEmail = "test@example.com";


        orderDTO.setCart_id(cartId);
        orderDTO.setCreate_date(createDate);
        orderDTO.setCreate_time(createTime);
        orderDTO.setFull_total(fullTotal);
        orderDTO.setStatus(status);
        orderDTO.setUserId(id);
        orderDTO.setU_email(uEmail);


        assertEquals(cartId, orderDTO.getCart_id());
        assertEquals(createDate, orderDTO.getCreate_date());
        assertEquals(createTime, orderDTO.getCreate_time());
        assertEquals(fullTotal, orderDTO.getFull_total());
        assertEquals(status, orderDTO.getStatus());
        assertEquals(id, orderDTO.getUserId());
        assertEquals(uEmail, orderDTO.getU_email());
    }

    @Test
    void testOrderDTOAllArgsConstructor() {

        Long cartId = 1L;
        LocalDate createDate = LocalDate.of(2024, 11, 22);
        LocalTime createTime = LocalTime.of(12, 30);
        double fullTotal = 200.50;
        int status = 1;
        Long id = 100L;
        String uEmail = "test@example.com";


        OrderDTO orderDTO = new OrderDTO(cartId, createDate, createTime, fullTotal, status, id, uEmail);


        assertEquals(cartId, orderDTO.getCart_id());
        assertEquals(createDate, orderDTO.getCreate_date());
        assertEquals(createTime, orderDTO.getCreate_time());
        assertEquals(fullTotal, orderDTO.getFull_total());
        assertEquals(status, orderDTO.getStatus());
        assertEquals(id, orderDTO.getUserId());
        assertEquals(uEmail, orderDTO.getU_email());
    }

    @Test
    void testOrderDTONoArgsConstructor() {

        OrderDTO orderDTO = new OrderDTO();


        assertNotNull(orderDTO);
        assertNull(orderDTO.getCart_id());
        assertNull(orderDTO.getCreate_date());
        assertNull(orderDTO.getCreate_time());
        assertEquals(0.0, orderDTO.getFull_total());
        assertEquals(0, orderDTO.getStatus());
        assertNull(orderDTO.getUserId());
        assertNull(orderDTO.getU_email());
    }

    @Test
    void testOrderDTOToString() {

        OrderDTO orderDTO = new OrderDTO(1L, LocalDate.of(2024, 11, 22), LocalTime.of(12, 30), 200.50, 1, 100L, "test@example.com");


        String toString = orderDTO.toString();


        assertTrue(toString.contains("cart_id=1"));
        assertTrue(toString.contains("create_date=2024-11-22"));
        assertTrue(toString.contains("create_time=12:30"));
        assertTrue(toString.contains("full_total=200.5"));
        assertTrue(toString.contains("status=1"));
        assertTrue(toString.contains("id=100"));
        assertTrue(toString.contains("u_email=test@example.com"));
    }

    @Test
    void testOrderDTOEqualsAndHashCode() {

        OrderDTO orderDTO1 = new OrderDTO(1L, LocalDate.of(2024, 11, 22), LocalTime.of(12, 30), 200.50, 1, 100L, "test@example.com");
        OrderDTO orderDTO2 = new OrderDTO(1L, LocalDate.of(2024, 11, 22), LocalTime.of(12, 30), 200.50, 1, 100L, "test@example.com");
        OrderDTO orderDTO3 = new OrderDTO(2L, LocalDate.of(2024, 11, 23), LocalTime.of(14, 0), 300.00, 0, 101L, "example@test.com");


        assertEquals(orderDTO1, orderDTO2);
        assertNotEquals(orderDTO1, orderDTO3);
        assertEquals(orderDTO1.hashCode(), orderDTO2.hashCode());
        assertNotEquals(orderDTO1.hashCode(), orderDTO3.hashCode());
    }
}
