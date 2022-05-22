package com.externship.expensetracker.repository;

import com.externship.expensetracker.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, String> {
    Balance findByEmail(String email);
}

