package com.mursalin.springSecurity.repository;

import com.mursalin.springSecurity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
