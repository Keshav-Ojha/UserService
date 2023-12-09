package com.microservices.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.UserService.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
