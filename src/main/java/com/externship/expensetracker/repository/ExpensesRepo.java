package com.externship.expensetracker.repository;

import com.externship.expensetracker.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpensesRepo extends JpaRepository<Expenses, String> {
    List<Expenses> findAllByEmail(String email);
}

