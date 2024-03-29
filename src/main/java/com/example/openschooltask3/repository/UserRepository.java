package com.example.openschooltask3.repository;

import com.example.openschooltask3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
