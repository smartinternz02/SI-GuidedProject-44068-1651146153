package com.externship.expensetracker.repository;

import com.externship.expensetracker.table.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
