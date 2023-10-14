package com.codesmachine.springbootrestapi.repositories;

import com.codesmachine.springbootrestapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username,String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}