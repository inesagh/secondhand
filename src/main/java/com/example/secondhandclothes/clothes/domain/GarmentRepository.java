package com.example.secondhandclothes.clothes.domain;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface GarmentRepository extends JpaRepository<Garment, Long> {
    @Query("SELECT g FROM Garment g " +
            "WHERE (:type IS NULL OR g.type = :type) " +
            "AND (:description IS NULL OR LOWER(g.description) LIKE LOWER(CONCAT('%', :description, '%'))) " +
            "AND (:size IS NULL OR g.size = :size) " +
            "AND (:price IS NULL OR g.price = :price) " +
            "AND (:publisherId IS NULL OR g.publisher.id = :publisherId)")
    List<Garment> search(String type, String description, String size, Double price, Long publisherId);
}
