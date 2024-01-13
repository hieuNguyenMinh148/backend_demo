package com.example.backend_demo.repository;

import com.example.backend_demo.model.Cart;
import com.example.backend_demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE  c.user.id = :userId")
    public Cart findByUserId(@Param("userId") Long userId);

}
