package com.foodOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public Object findByEmail(String email);
}
