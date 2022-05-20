package com.externship.expensetracker.repo;

import com.externship.expensetracker.util.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpensesRepo extends JpaRepository<Expenses, String> {
    List<Expenses> findAllByEmail(String email);
}

