package com.Abhishek.Repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Abhishek.Models.User;

public interface UserRepo  extends JpaRepository<User,Integer>{
    
    public Optional<User> findByEmail(String email);
}
