package com.freshsip.freshsip.dto;
import com.freshsip.freshsip.dto.ItemDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemDTOTest {
    @Test
    public void testItemDTOGettersAndSetters() {
        // Mock data
        Long item_id = 1L;
        String item_name = "Apple Juice";
        byte[] image = new byte[]{1, 2, 3};
        String description = "Fresh apple juice";
        double item_prize = 3.5;

        // Create an instance using no-args constructor
        ItemDTO itemDTO = new ItemDTO();

        // Set values
        itemDTO.setItem_id(item_id);
        itemDTO.setItem_name(item_name);
        itemDTO.setImage(image);
        itemDTO.setDescription(description);
        itemDTO.setItem_prize(item_prize);

        // Assertions for getters
        assertEquals(item_id, itemDTO.getItem_id());
        assertEquals(item_name, itemDTO.getItem_name());
        assertArrayEquals(image, itemDTO.getImage());
        assertEquals(description, itemDTO.getDescription());
        assertEquals(item_prize, itemDTO.getItem_prize(), 0.001);
    }

    @Test
    public void testItemDTOAllArgsConstructor() {
        // Mock data
        Long item_id = 1L;
        String item_name = "Orange Juice";
        byte[] image = new byte[]{4, 5, 6};
        String description = "Fresh orange juice";
        double item_prize = 4.0;
        int quantity = 2;

        // Create an instance using all-args constructor
        ItemDTO itemDTO = new ItemDTO(item_id, item_name, image, description, item_prize,quantity);

        // Assertions for getters
        assertEquals(item_id, itemDTO.getItem_id());
        assertEquals(item_name, itemDTO.getItem_name());
        assertArrayEquals(image, itemDTO.getImage());
        assertEquals(description, itemDTO.getDescription());
        assertEquals(item_prize, itemDTO.getItem_prize(), 0.001);
        assertEquals(quantity, itemDTO.getQuantity(), 0);
    }

    @Test
    public void testItemDTONoArgsConstructor() {
        // Create an instance using no-args constructor
        ItemDTO itemDTO = new ItemDTO();

        // Assert that all fields are initially null or default values
        assertEquals(0, itemDTO.getItem_id());
        assertNull(itemDTO.getItem_name());
        assertNull(itemDTO.getImage());
        assertNull(itemDTO.getDescription());
        assertEquals(0.0, itemDTO.getItem_prize(), 0.001);
        assertEquals(0, itemDTO.getQuantity(), 0);
    }

    @Test
    public void testToString() {
        // Mock data
        Long item_id = 1L;
        String item_name = "Grape Juice";
        byte[] image = new byte[]{7, 8, 9};
        String description = "Delicious grape juice";
        double item_prize = 5.0;
        int quantity =2;

        // Create an instance
        ItemDTO itemDTO = new ItemDTO(item_id, item_name, image, description, item_prize,quantity);

        // Assert the toString output
        String expected = "ItemDTO(item_id=1, item_name=Grape Juice, image=[B@, description=Delicious grape juice, item_prize=5.0,quantity=2)";
        assertTrue(itemDTO.toString().contains("item_id=1"));
        assertTrue(itemDTO.toString().contains("item_name=Grape Juice"));
        assertTrue(itemDTO.toString().contains("description=Delicious grape juice"));
        assertTrue(itemDTO.toString().contains("item_prize=5.0"));
        assertTrue(itemDTO.toString().contains("quantity=2"));
    }
}

