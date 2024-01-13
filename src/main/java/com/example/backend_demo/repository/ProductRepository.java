package com.example.backend_demo.repository;

import com.example.backend_demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query(value =
//            "SELECT * FROM product p " +
//                    "WHERE (:category IS NULL OR p.category_name = :category) " +
//                    "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discounted_price BETWEEN :minPrice AND :maxPrice)) " +
//                    "AND (:minDiscount IS NULL OR p.discount_present >= :minDiscount) " +
//                    "ORDER BY " +
//                    "CASE WHEN :sort = 'price_low' THEN p.discounted_price END ASC, " +
//                    "CASE WHEN :sort = 'price_high' THEN p.discounted_price END DESC",
//            nativeQuery = true)
    @Query("SELECT p from Product p WHERE (p.category.name = :category OR :category='') " +
            "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice))" +
            "AND (:minDiscount IS NULL OR p.discountPresent >= :minDiscount)" +
            "ORDER BY " +
            "CASE WHEN :sort = 'price_low' THEN p.discountedPrice END ASC, " +
            "CASE WHEN :sort = 'price_high' THEN p.discountedPrice END DESC")
    public List<Product> filterProducts(@Param("category") String category, @Param("minPrice") Integer minPrice,
                                        @Param("maxPrice") Integer maxPrice, @Param("minDiscount") Integer misDiscount,
                                        @Param("sort") String sort);
}
