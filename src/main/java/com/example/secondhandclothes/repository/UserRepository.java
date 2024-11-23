package com.example.secondhandclothes.repository;

import com.example.secondhandclothes.entity.UserDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDAO, Long> {
    Optional<UserDAO> findByUsername(String username);
}
