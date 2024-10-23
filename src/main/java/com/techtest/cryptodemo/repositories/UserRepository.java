package com.techtest.cryptodemo.repositories;

import com.techtest.cryptodemo.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
