package com.Abhishek.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Abhishek.Models.Profile;

public interface ProfileRepo extends JpaRepository<Profile,Integer>{

    public Profile findByEmail(String email);
}