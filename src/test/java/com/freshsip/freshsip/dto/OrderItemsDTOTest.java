package com.freshsip.freshsip.dto;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderItemsDTOTest {


    @Test
    void testOrderItemsDTOGettersAndSetters() {

        OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
        Long cartItemsId =1L;
        Long cartId = 2L;
        List<ProductDTO> selectedProducts= Arrays.asList(
                new ProductDTO(1L,200.0,5,"Product1",1000.0),
                new ProductDTO(2L,100.0,4,"Product2",400.0)
        );
        String itemName ="Test Item";
        double itemPrice = 100.0;


        orderItemsDTO.setCart_items(cartItemsId);
        orderItemsDTO.setCart_id(cartId);
        orderItemsDTO.setSelectedProducts(selectedProducts);
        orderItemsDTO.setItem_name(itemName);
        orderItemsDTO.setItem_price(itemPrice);



        assertEquals(cartItemsId,orderItemsDTO.getCart_items());
        assertEquals(cartId, orderItemsDTO.getCart_id());
        assertEquals(selectedProducts, orderItemsDTO.getSelectedProducts());
        assertEquals(itemName, orderItemsDTO.getItem_name());
        assertEquals(itemPrice, orderItemsDTO.getItem_price());

    }

    @Test
    void testOrderItemsDTOAllArgsConstructor() {

        Long cartItemsId =1L;
        Long cartId = 2L;
        List<ProductDTO> selectedProducts= Arrays.asList(
                new ProductDTO(1L,200.0,2,"Product1",400.0),
                new ProductDTO(2L,100.0,5,"Product2",500.0)
        );
        String itemName ="Test Item";
        double itemPrice = 100.0;




        OrderItemsDTO orderItemsDTO = new OrderItemsDTO(cartItemsId,cartId,selectedProducts,itemName,itemPrice);


        assertEquals(cartItemsId,orderItemsDTO.getCart_items());
        assertEquals(cartId, orderItemsDTO.getCart_id());
        assertEquals(selectedProducts, orderItemsDTO.getSelectedProducts());
        assertEquals(itemName, orderItemsDTO.getItem_name());
        assertEquals(itemPrice, orderItemsDTO.getItem_price());

    }

    @Test
    void testOrderItemsDTONoArgsConstructor() {

        OrderItemsDTO orderItemsDTO = new OrderItemsDTO();


        assertNotNull(orderItemsDTO);
        assertNull(orderItemsDTO.getCart_items());
        assertNull( orderItemsDTO.getCart_id());
        assertNull(orderItemsDTO.getSelectedProducts());
        assertNull(orderItemsDTO.getItem_name());
        assertEquals(0.0,orderItemsDTO.getItem_price());

    }

    @Test
    void testOrderItemsDTOToString() {

        OrderItemsDTO orderItemsDTO = new OrderItemsDTO(1L,
                2L,
                Arrays.asList(
                        new ProductDTO(1L,200.0,2,"Product1",400.0),
                        new ProductDTO(2L,100.0,1,"Product2",100.0)
                ),
                "Test Item",
        100.0   );


        String toString = orderItemsDTO.toString();


        assertTrue(toString.contains("cart_items=1"));
        assertTrue(toString.contains("cart_id=2"));
        assertTrue(toString.contains("selectedProducts"));
        assertTrue(toString.contains("item_name=Test Item"));
        assertTrue(toString.contains("item_price=100.0"));

    }

    @Test
    void testOrderItemsDTOEqualsAndHashCode() {

        List<ProductDTO> selectedProducts =Arrays.asList(
                new ProductDTO(1L,200.0,2,"Product1",400.0),
                new ProductDTO(2L,100.0,2,"Product2",200.0)
        );
        OrderItemsDTO orderItemsDTO1 = new OrderItemsDTO(1L, 2L, selectedProducts, "Test Item",100.0);
        OrderItemsDTO orderItemsDTO2 = new OrderItemsDTO(1L, 2L, selectedProducts, "Test Item",100.0);
        OrderItemsDTO orderItemsDTO3 = new OrderItemsDTO(3L, 4L, null, "Another",50.0);


        assertEquals(orderItemsDTO1, orderItemsDTO2);
        assertNotEquals(orderItemsDTO1, orderItemsDTO3);
        assertEquals(orderItemsDTO1.hashCode(), orderItemsDTO2.hashCode());
        assertNotEquals(orderItemsDTO1.hashCode(), orderItemsDTO3.hashCode());
    }
}
