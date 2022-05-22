package com.externship.expensetracker.repository;

import com.externship.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<User, String> {
}
