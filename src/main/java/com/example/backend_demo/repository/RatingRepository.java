package com.example.backend_demo.repository;

import com.example.backend_demo.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    public List<Rating> findAllByProductId(Long productId);

}
