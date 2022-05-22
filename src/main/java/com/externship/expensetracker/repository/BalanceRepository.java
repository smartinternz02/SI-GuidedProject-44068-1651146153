package com.externship.expensetracker.repository;

import com.externship.expensetracker.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BalanceRepository extends JpaRepository<Balance, String> {

    @Query("select sum(balance) from balance where user =:user")
    public int sumBalance(@Param("user") Long user);
}

