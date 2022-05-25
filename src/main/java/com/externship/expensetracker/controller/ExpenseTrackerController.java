package com.externship.expensetracker.controller;

import com.externship.expensetracker.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExpenseTrackerController {

    @Autowired
    BalanceRepository balanceRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ExpensesRepository expensesRepository;
    @Autowired
    UserRepository userRepository;

    long userId;
    int netBalance;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @RequestMapping("/")
    public String displayIndexPage() {
        return "redirect:/user_login";
    }

    @RequestMapping("/user_register")
    public String displayRegistrationPage() {
        return "user_register";
    }

    @RequestMapping("/user_login")
    public String displayLogInPage() {
        return "user_login";
    }

    @RequestMapping("/expense_add")
    public String displayExpenseAddPage() {
        return "expense_add";
    }

    @RequestMapping("/expense_view")
    public String displayExpenseViewPage() {
        return "expense_view";
    }

    @RequestMapping("/balance_add")
    public String displayBalanceAddPage() {
        return "balance_add";
    }

    @RequestMapping("/balance_view")
    public String displayBalanceViewPage() {
        return "balance_view";
    }

    @RequestMapping("/analysis")
    public String displayAnalysisPage() {
        return "analysis";
    }

    @RequestMapping("/analysis_week")
    public String displayAnalysisWeekPage() {
        return "analysis_week";
    }

    @RequestMapping("/analysis_category")
    public String displayAnalysisCategoryPage() {
        return "analysis_category";
    }

    @RequestMapping("/analysis_month")
    public String displayAnalysisMonthPage() {
        return "analysis_month";
    }

    @RequestMapping("/analysis_year")
    public String displayAnalysisYearPage() {
        return "analysis_year";
    }
}
