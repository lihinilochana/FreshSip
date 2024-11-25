package com.freshsip.freshsip.service;

import com.freshsip.freshsip.dto.ItemDTO;
import com.freshsip.freshsip.entity.Items;
import com.freshsip.freshsip.repository.ItemRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ItemServicesTest {
    @InjectMocks
    private ItemServices itemServices;

    @Mock
    private ItemRepo itemRepo;

    @Mock
    private ModelMapper modelMapper;

//    @Test
//    public void testSaveItem() {
//        // Arrange
//        ItemDTO itemDTO = new ItemDTO(1L, "Apple Juice", null, "Fresh apple juice", 3.5,1);
//        Items itemStock = new Items(1L, "Apple Juice", null, "Fresh apple juice", 3.5,1);
//
//        Mockito.when(modelMapper.map(itemDTO, Items.class)).thenReturn(itemStock);
//        Mockito.when(itemRepo.save(itemStock)).thenReturn(itemStock);
//        Mockito.when(modelMapper.map(itemStock, ItemDTO.class)).thenReturn(itemDTO);
//
//        // Act
//        ItemDTO savedItem = itemServices.saveItem(itemDTO);
//
//        // Assert
//        Assertions.assertNotNull(savedItem);
//        Assertions.assertEquals("Apple Juice", savedItem.getItem_name());
//        Assertions.assertEquals("Fresh apple juice", savedItem.getDescription());
//        Assertions.assertEquals(3.5, savedItem.getItem_prize());
//    }

//    @Test
//    public void testGetAllItemStock() {
//        // Arrange
//        Items item1 = new Items(1L, "Apple Juice", null, "Fresh apple juice", 3.5,1);
//        Items item2 = new Items(2L, "Orange Juice", null, "Fresh orange juice", 4.0,1);
//        List<Items> itemList = Arrays.asList(item1, item2);
//
//        ItemDTO itemDTO1 = new ItemDTO(1L, "Apple Juice", null, "Fresh apple juice", 3.5,1);
//        ItemDTO itemDTO2 = new ItemDTO(2L, "Orange Juice", null, "Fresh orange juice", 4.0,1);
//        List<ItemDTO> itemDTOList = Arrays.asList(itemDTO1, itemDTO2);
//
//        Mockito.when(itemRepo.findAll()).thenReturn(itemList);
//        Mockito.when(modelMapper.map(itemList, new TypeToken<List<ItemDTO>>() {}.getType())).thenReturn(itemDTOList);
//
//        // Act
//        List<ItemDTO> fetchedItems = itemServices.getAllItemStock();
//
//        // Assert
//        Assertions.assertNotNull(fetchedItems);
//        Assertions.assertEquals(2, fetchedItems.size());
//        Assertions.assertEquals("Apple Juice", fetchedItems.get(0).getItem_name());
//        Assertions.assertEquals("Orange Juice", fetchedItems.get(1).getItem_name());
//    }

//    @Test
//    public void testUpdateItem() {
//        // Arrange
//        ItemDTO itemDTO = new ItemDTO(1L, "Updated Juice", null, "Updated description", 6.0,1);
//        Items existingItem = new Items(1L, "Apple Juice", null, "Fresh apple juice", 3.5,1);
//
//        Mockito.when(itemRepo.findById(1L)).thenReturn(Optional.of(existingItem));
//        Mockito.when(itemRepo.save(existingItem)).thenReturn(existingItem);
//        Mockito.when(modelMapper.map(existingItem, ItemDTO.class)).thenReturn(itemDTO);
//
//        // Act
//        ItemDTO updatedItem = itemServices.updateItem(itemDTO);
//
//        // Assert
//        Assertions.assertNotNull(updatedItem);
//        Assertions.assertEquals("Updated Juice", updatedItem.getItem_name());
//        Assertions.assertEquals("Updated description", updatedItem.getDescription());
//        Assertions.assertEquals(6.0, updatedItem.getItem_prize());
//    }

    @Test
    public void testDeleteItemById() {
        // Arrange
        Long item_id = 1L;

        Mockito.doNothing().when(itemRepo).deleteById(item_id);

        // Act
        boolean isDeleted = itemServices.deleteItemById(item_id);

        // Assert
        Assertions.assertTrue(isDeleted);
    }

//    @Test
//    public void testGetItemById() {
//        // Arrange
//        Long item_id = 1L;
//        Items itemStock = new Items(1L, "Apple Juice", null, "Fresh apple juice", 3.5,1);
//        ItemDTO itemDTO = new ItemDTO(1L, "Apple Juice", null, "Fresh apple juice", 3.5,1);
//
//        Mockito.when(itemRepo.findById(item_id)).thenReturn(Optional.of(itemStock));
//        Mockito.when(modelMapper.map(itemStock, ItemDTO.class)).thenReturn(itemDTO);
//
//        // Act
//        ItemDTO fetchedItem = itemServices.getItemById(item_id);
//
//        // Assert
//        Assertions.assertNotNull(fetchedItem);
//        Assertions.assertEquals("Apple Juice", fetchedItem.getItem_name());
//        Assertions.assertEquals("Fresh apple juice", fetchedItem.getDescription());
//        Assertions.assertEquals(3.5, fetchedItem.getItem_prize());
//    }
}

