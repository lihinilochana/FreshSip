package com.freshsip.freshsip.service;


import com.freshsip.freshsip.dto.ItemDTO;
import com.freshsip.freshsip.entity.Items;
import com.freshsip.freshsip.repository.ItemRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ItemServices {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ItemDTO saveItem(ItemDTO itemDTO) {
        Items itemStock = modelMapper.map(itemDTO, Items.class);
        itemStock = itemRepo.save(itemStock);
        return modelMapper.map(itemStock, ItemDTO.class);
    }

    public List<ItemDTO> getAllItemStock() {
        List<Items> itemList = itemRepo.findAll();
        return modelMapper.map(itemList, new TypeToken<List<ItemDTO>>() {}.getType());
    }

    public ItemDTO updateItem(ItemDTO itemDTO) {
        Items existingItemStock = itemRepo.findById(itemDTO.getItem_id())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        existingItemStock.setItem_name(itemDTO.getItem_name());
        existingItemStock.setDescription(itemDTO.getDescription());
        existingItemStock.setItem_prize(itemDTO.getItem_prize());
        existingItemStock.setQuantity(itemDTO.getQuantity());

        if (itemDTO.getImage() != null) {
            existingItemStock.setImage(itemDTO.getImage());
        }

        itemRepo.save(existingItemStock);
        return modelMapper.map(existingItemStock, ItemDTO.class);
    }

    public boolean deleteItemById(Long item_id) {
        itemRepo.deleteById(item_id);
        return true;
    }

    public ItemDTO getItemById(Long item_id) {
        Items itemStock = itemRepo.findById(item_id).orElseThrow(() -> new RuntimeException("Item not found"));
        return modelMapper.map(itemStock, ItemDTO.class);
    }





}

