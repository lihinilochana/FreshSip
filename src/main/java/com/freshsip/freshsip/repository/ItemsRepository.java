package com.freshsip.freshsip.repository;

import com.freshsip.freshsip.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository  extends JpaRepository<Items, Long> {
}
