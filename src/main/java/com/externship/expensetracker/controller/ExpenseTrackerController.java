package com.externship.expensetracker.controller;

import com.externship.expensetracker.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExpenseTrackerController {

    long id;
    @Autowired
    BalanceRepository balanceRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ExpensesRepository expensesRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String indexPage() {
        return "redirect:/user_login";
    }

    @RequestMapping("/user_register")
    public String registerPage() {
        return "user_register";
    }

    @RequestMapping("/user_login")
    public String logInPage() {
        return "user_login";
    }

    @RequestMapping("/expenses_add")
    public String addExpensesPage() {
        return "expenses_add";
    }

    @RequestMapping("/expenses_view")
    public String viewExpensesPage() {
        return "expenses_view";
    }

    @RequestMapping("/balance_add")
    public String addBalancePage() {
        return "balance_add";
    }
}
