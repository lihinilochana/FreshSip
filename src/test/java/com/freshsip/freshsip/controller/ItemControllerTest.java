package com.freshsip.freshsip.controller;

import com.freshsip.freshsip.dto.ItemDTO;
import com.freshsip.freshsip.service.ItemServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemServices itemServices;

    @Test
    public void testGetItem() throws Exception {
        // Mock data
        ItemDTO item1 = new ItemDTO(1L, "Apple Juice", null, "Fresh apple juice", 3.5,1);
        ItemDTO item2 = new ItemDTO(2L, "Orange Juice", null, "Fresh orange juice", 4.0,1);

        // Mock service behavior
        Mockito.when(itemServices.getAllItemStock()).thenReturn(Arrays.asList(item1, item2));

        // Test the endpoint
        mockMvc.perform(get("/FreshSip/admin/ItemStock/ITEM"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].item_name").value("Apple Juice"))
                .andExpect(jsonPath("$[1].item_name").value("Orange Juice"));
    }

    @Test
    public void testSaveItem() throws Exception {
        // Mock data
        ItemDTO newItem = new ItemDTO(1L, "Grape Juice", null, "Delicious grape juice", 5.0,1);

        // Mock service behavior
        Mockito.when(itemServices.saveItem(Mockito.any(ItemDTO.class))).thenReturn(newItem);

        // Test the endpoint
        mockMvc.perform(post("/FreshSip/admin/ItemStock/ItemS")
                        .param("item_name", "Grape Juice")
                        .param("description", "Delicious grape juice")
                        .param("item_prize", "5.0")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.item_name").value("Grape Juice"));
    }

    @Test
    public void testUpdateItem() throws Exception {
        // Mock data
        ItemDTO updatedItem = new ItemDTO(1L, "Updated Juice", null, "Updated description", 6.0,1);

        // Mock service behavior
        Mockito.when(itemServices.updateItem(Mockito.any(ItemDTO.class))).thenReturn(updatedItem);

        // Test the endpoint
        mockMvc.perform(put("/FreshSip/admin/ItemStock/item/1")
                        .param("item_name", "Updated Juice")
                        .param("description", "Updated description")
                        .param("item_prize", "6.0")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.item_name").value("Updated Juice"));
    }

    @Test
    public void testDeleteItem() throws Exception {
        // Mock service behavior
        Mockito.when(itemServices.deleteItemById(1L)).thenReturn(true);

        // Test the endpoint
        mockMvc.perform(delete("/FreshSip/admin/ItemStock/items/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetItemById() throws Exception {
        // Mock data
        ItemDTO item = new ItemDTO(1L, "Apple Juice", null, "Fresh apple juice", 3.5,1);

        // Mock service behavior
        Mockito.when(itemServices.getItemById(1L)).thenReturn(item);

        // Test the endpoint
        mockMvc.perform(get("/FreshSip/admin/ItemStock/ITEMItemById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.item_name").value("Apple Juice"));
    }
}
