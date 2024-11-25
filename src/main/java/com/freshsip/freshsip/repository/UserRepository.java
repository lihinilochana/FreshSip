package com.freshsip.freshsip.repository;

import com.freshsip.freshsip.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);

    @Modifying // Add this annotation
    @Transactional // Add this to ensure it's wrapped in a transaction
    @Query("DELETE FROM User u WHERE u.email = :email")
    void deleteByEmail(String email);

    @Query(value="SELECT * FROM user WHERE email=?1 ",nativeQuery = true)
    User findByEmails(String email);
}
