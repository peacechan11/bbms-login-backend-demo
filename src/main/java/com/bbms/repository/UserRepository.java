package com.bbms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbms.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailIgnoreCase(String email);
}