package com.example.secondhandclothes.repository;

import com.example.secondhandclothes.entity.GarmentDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarmentRepository extends CrudRepository<GarmentDAO, Long> {
    @Query("SELECT g FROM GarmentDAO g " +
            "WHERE (:type IS NULL OR g.type = :type) " +
            "AND (:description IS NULL OR LOWER(g.description) LIKE LOWER(CONCAT('%', :description, '%'))) " +
            "AND (:size IS NULL OR g.size = :size) " +
            "AND (:price IS NULL OR g.price = :price) " +
            "AND (:publisherId IS NULL OR g.publisher.id = :publisherId)")
    List<GarmentDAO> search(String type, String description, String size, Double price, Long publisherId);
}
