package com.freshsip.freshsip.repository;


import com.freshsip.freshsip.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface UserRepository  extends JpaRepository<User, Long> {
    @Query(value="SELECT * FROM user WHERE u_email=?1 ",nativeQuery = true)
    User getUserByEmail(String u_email);

    @Query(value="SELECT * FROM user WHERE u_email=?1 ",nativeQuery = true)
    User findByEmail(String u_email);
}
