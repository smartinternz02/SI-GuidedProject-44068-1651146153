package com.externship.expensetracker.controller;

import com.externship.expensetracker.repository.*;
import com.externship.expensetracker.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class ExpenseTrackerController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ExpensesRepository expensesRepository;
    @Autowired
    BalanceRepository balanceRepository;

    long userId;

    @RequestMapping("/index")
    public String indexPage() {
        return "index";
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
