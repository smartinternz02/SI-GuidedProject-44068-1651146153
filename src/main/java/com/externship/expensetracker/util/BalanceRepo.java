package com.externship.expensetracker.util;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepo extends JpaRepository<Balance, String> {
    Balance findByEmail(String email);
}

