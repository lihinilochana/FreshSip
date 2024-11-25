package com.freshsip.freshsip.controller;

import com.freshsip.freshsip.dto.ItemDTO;
import com.freshsip.freshsip.service.ItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "FreshSip/ItemStock")
@CrossOrigin
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemServices itemServices;

    @GetMapping("admin/ITEM")
    public List<ItemDTO> getItem() {
        return itemServices.getAllItemStock();
    }

    @PostMapping("admin/ItemS")
    public ItemDTO saveItem(@RequestParam("item_name") String item_name,
                            @RequestParam("description") String description,
                            @RequestParam("item_prize") double item_prize,
                            @RequestParam("quantity") int quantity,
                            @RequestParam("image") MultipartFile image) throws IOException {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItem_name(item_name);
        itemDTO.setDescription(description);
        itemDTO.setItem_prize(item_prize);
        itemDTO.setQuantity(quantity);
        itemDTO.setImage(image.getBytes());
        return itemServices.saveItem(itemDTO);
    }

    @PutMapping("admin/item/{item_id}")
    public ItemDTO updateItem(@PathVariable("item_id")Long item_id,
                              @RequestParam("item_name") String item_name,
                              @RequestParam("description") String description,
                              @RequestParam("item_prize") double item_prize,
                              @RequestParam("quantity") int quantity,
                              @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItem_id(item_id);
        itemDTO.setItem_name(item_name);
        itemDTO.setDescription(description);
        itemDTO.setItem_prize(item_prize);
        itemDTO.setQuantity(quantity);
        if (image != null) {
            itemDTO.setImage(image.getBytes());
        } else {
            // Fetch existing image if not updated
            ItemDTO existingItem = itemServices.getItemById(item_id);
            itemDTO.setImage(existingItem.getImage());
        }
        return itemServices.updateItem(itemDTO);
    }


    @DeleteMapping("admin/items/{item_id}")
    public boolean deleteItem(@PathVariable("item_id") Long item_id) {
        return itemServices.deleteItemById(item_id);
    }

    @GetMapping("admin/ITEMItemById/{item_id}")
    public ItemDTO getItemById(@PathVariable  Long item_id){
        return itemServices.getItemById(item_id);
    }



}






