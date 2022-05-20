package com.externship.expensetracker.repo;

import com.externship.expensetracker.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    User findByEmail(String email);
}
