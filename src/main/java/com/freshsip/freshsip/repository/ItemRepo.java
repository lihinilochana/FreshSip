package com.freshsip.freshsip.repository;

import com.freshsip.freshsip.entity.Items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ItemRepo extends JpaRepository<Items,Long> {

    @Query(value = "select * FROM item_stock WHERE item_id=?1" , nativeQuery = true)
    Items getItemById (Long item_id);

    @Query(value="SELECT * FROM item_stock WHERE  item_name=?1",nativeQuery = true)
    Items getProductByName(String item_name);

        void deleteById(Long item_id);

}
