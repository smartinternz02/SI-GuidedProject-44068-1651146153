package com.externship.expensetracker.repository;

import com.externship.expensetracker.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepo extends JpaRepository<Balance, String> {
    Balance findByEmail(String email);
}

