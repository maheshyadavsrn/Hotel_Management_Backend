package com.tech.hotel.rep;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.hotel.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);
	Optional<User>  findByEmail(String email);

}
