package com.passmanager.password_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.passmanager.password_manager.domain.User;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PasswordManagerRepository extends JpaRepository<User, Integer> {
    List<User> findByNameContainingIgnoreCase(String keyword);
}