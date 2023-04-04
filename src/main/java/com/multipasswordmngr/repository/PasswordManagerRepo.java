package com.multipasswordmngr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multipasswordmngr.domain.User;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PasswordManagerRepo extends JpaRepository<User, Integer> {
    List<User> findByNameContainingIgnoreCase(String keyword);
}
