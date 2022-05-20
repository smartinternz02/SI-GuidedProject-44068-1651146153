package com.externship.expensetracker;

import com.externship.expensetracker.repo.BalanceRepo;
import com.externship.expensetracker.repo.ExpensesRepo;
import com.externship.expensetracker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ExpenseTrackerController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    ExpensesRepo expensesRepo;
    @Autowired
    BalanceRepo balanceRepo;


}
