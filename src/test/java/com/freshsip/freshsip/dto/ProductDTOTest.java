package com.freshsip.freshsip.dto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductDTOTest {

    @Test
    void testProductDTOGettersAndSetters() {

        ProductDTO productDTO = new ProductDTO();

        Long id = 1L;
        double price = 200.50;
        int quantity = 1;
        String itemName = "test@example.com";
        double itemPrice = 100.00;


        productDTO.setId(id);
        productDTO.setPrice(price);
        productDTO.setQuantity(quantity);
        productDTO.setItem_name(itemName);
        productDTO.setItem_price(itemPrice);



        assertEquals(id,  productDTO.getId());
        assertEquals(price,  productDTO.getPrice());
        assertEquals(quantity,  productDTO.getQuantity());
        assertEquals(itemName,  productDTO.getItem_name());
        assertEquals(itemPrice,  productDTO.getItem_price());

    }

    @Test
    void testProductDTOAllArgsConstructor() {

        Long id = 1L;
        double price = 200.50;
        int quantity = 1;
        String itemName = "test@example.com";
        double itemPrice = 100.00;


        ProductDTO productDTO = new ProductDTO(id,price,quantity,itemName,itemPrice);


        assertEquals(id,  productDTO.getId());
        assertEquals(price,  productDTO.getPrice());
        assertEquals(quantity,  productDTO.getQuantity());
        assertEquals(itemName,  productDTO.getItem_name());
        assertEquals(itemPrice,  productDTO.getItem_price());

    }

    @Test
    void testProductDTONoArgsConstructor() {

        ProductDTO productDTO = new ProductDTO();


        assertNotNull( productDTO);
        assertNull( productDTO.getId());
        assertEquals( 0.0,productDTO.getPrice());
        assertEquals( 0,productDTO.getQuantity());
        assertNull(  productDTO.getItem_name());
        assertEquals(0,  productDTO.getItem_price());

    }

    @Test
    void testProductDTOToString() {

        ProductDTO productDTO = new ProductDTO(1L, 20.5, 3, "Test Product", 61.5);

        String toString = productDTO.toString();


        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("price=20.5"));
        assertTrue(toString.contains("quantity=3"));
        assertTrue(toString.contains("item_name=Test Product"));
        assertTrue(toString.contains("item_price=61.5"));
    }

    @Test
    void testProductDTOEqualsAndHashCode() {

        ProductDTO productDTO1 = new ProductDTO(1L,100.0,2,"item1",100.0);
        ProductDTO productDTO2 = new ProductDTO(1L,100.0,2,"item1",100.0);
        ProductDTO productDTO3 = new ProductDTO(2L,200.0,4,"item2",200.0);


        assertEquals(productDTO1, productDTO2);
        assertNotEquals(productDTO1,productDTO3);
        assertEquals(productDTO1.hashCode(),productDTO2.hashCode());
        assertNotEquals(productDTO1.hashCode(),productDTO3.hashCode());
    }
}
