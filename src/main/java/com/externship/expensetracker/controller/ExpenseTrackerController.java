package com.externship.expensetracker.controller;

import com.externship.expensetracker.model.Balance;
import com.externship.expensetracker.model.Expenses;
import com.externship.expensetracker.model.User;
import com.externship.expensetracker.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.TreeMap;

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

    @GetMapping(value = "/user_register")
    public ModelAndView displayRegistrationPage(ModelAndView modelAndView, User user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user_register");
        return modelAndView;
    }

    @PostMapping("/user_register")
    public String registerUser(ModelAndView modelAndView, User user) {
        String path = null;
        String email = user.getEmail();
        System.out.println(email);
        User existingUser = userRepository.findByEmailIgnoreCase(email);
        if (existingUser != null) {
            modelAndView.addObject("message", "This email already exists!");
            modelAndView.setViewName("ERROR#");
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            modelAndView.addObject("email", user.getEmail());
            path = "redirect:/user_login";
        }
        return path;
    }

    @GetMapping("/user_login")
    public ModelAndView displayLogInPage(ModelAndView modelAndView, User user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user_login");
        return modelAndView;
    }

    @PostMapping("/user_login")
    public ModelAndView logUserIn(ModelAndView modelAndView, User user) {
        String email = user.getEmail();
        User userFromDB = userRepository.findByEmailIgnoreCase(email);
        System.out.println(userFromDB);
        if (userFromDB != null) {
            if (bCryptPasswordEncoder.matches(user.getPassword(), userFromDB.getPassword())) {
                userId = userFromDB.getId();
                System.out.println("user id: " + userId);
                modelAndView.addObject("message", "You Have Successfully Logged into Expense tracker Application!");
                modelAndView.setViewName("user_home");
            } else {
                modelAndView.addObject("message", "Incorrect password. Try again!");
                modelAndView.setViewName("user_login");
            }
        } else {
            modelAndView.addObject("message", "The email provided does not exist!");
            modelAndView.setViewName("user_login");
        }
        return modelAndView;
    }

    @RequestMapping("/logout")
    public String logUserOut() {
        return "user_login";
    }

    @GetMapping("/expense_add")
    public ModelAndView displayExpenseAddPage(ModelAndView modelAndView, Expenses expenses) {
        modelAndView.addObject("expenses", expenses);
        modelAndView.setViewName("expense_add");
        return modelAndView;
    }

    @PostMapping(value = "/expense_add")
    public String addExpenses(ModelAndView modelAndView, Expenses expenses, Balance balance) {
        try {
            if ((expenses.getAmount()) > (balanceRepository.getNetBalanceOf(userId))) {
                modelAndView.addObject("message", "Your balance is low you can't spend money!!!");
                modelAndView.setViewName("expense_view");
            } else {
                expenses.setUser(userId);
                expensesRepository.save(expenses);
                balance.setBalance(-(expenses.getAmount()));
                balanceRepository.save(balance);
                modelAndView.addObject("message", "Expenses Added Successfully");
                modelAndView.setViewName("expense_view");
            }
        } catch (Exception e) {
            modelAndView.addObject("message", e.getMessage());
            modelAndView.setViewName("balance_add");
        }
        return "redirect:/expense_view";
    }

    @GetMapping("/expense_view")
    public ModelAndView displayExpenseViewPage(ModelAndView modelAndView) {
        try {
            System.out.println(userId);
            modelAndView.addObject("expenses", expensesRepository.getAllEntries(userId));
            modelAndView.setViewName("expense_view");
        } catch (Exception e) {
            modelAndView.addObject("message", e.getMessage());
            modelAndView.setViewName("user_home");
        }
        return modelAndView;
    }

    @GetMapping("/home")
    public String displayHomePage() {
        return  "home";
    }

    @GetMapping(value = "/balance_add")
    public ModelAndView addBalance(ModelAndView modelAndView, Balance balance) {
        try {
            modelAndView.addObject("balance", balance);
            modelAndView.setViewName("balance_add");
        } catch (Exception e) {
            modelAndView.addObject("message", e.getMessage());
            modelAndView.setViewName("user_home");
        }
        return modelAndView;
    }

    @PostMapping("/balance_add")
    public ModelAndView saveMoney(ModelAndView modelAndView, Balance balance) {
        try {
            balance.setUser(userId);
            balanceRepository.save(balance);
            modelAndView.addObject("message", "You have successfully added money to your wallet!!");
            modelAndView.setViewName("balance_add");
        } catch (Exception e) {
            modelAndView.addObject("message", e.getMessage());
            modelAndView.setViewName("AddMoney");
        }
        return modelAndView;
    }

    @GetMapping(value = "/balance_view")
    public ModelAndView displayBalanceViewPage(ModelAndView modelAndView) {
        try {
            netBalance = balanceRepository.getNetBalanceOf(userId);
            modelAndView.addObject("netBalance", netBalance);
            modelAndView.setViewName("balance_view");
        } catch (Exception e) {
            modelAndView.addObject("message", e.getMessage());
            modelAndView.setViewName("user_home");
        }
        return modelAndView;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteExpense(@PathVariable int id) {
        expensesRepository.deleteById(String.valueOf(id));
        return "redirect:/expense_view";
    }

    @RequestMapping("/analysis")
    public String displayAnalysisPage() {
        return "analysis";
    }

    @RequestMapping("/analysis_category")
    public String displayCategoryWiseAnalysis() {
        return "analysis_category";
    }

    @RequestMapping("/analysis_week")
    public String displayLastWeekExpense() {
        return "analysis_week";
    }

    @RequestMapping("/analysis_month")
    public String displayMonthlyAnalysis() {
        return "analysis_month";
    }

    @RequestMapping("/analysis_year")
    public String displayYearlyAnalysis() {
        return "analysis_year";
    }

    private TreeMap<String, Integer> getTable(int[] entries, String[] types) {
        TreeMap<String, Integer> table = new TreeMap<>();
        System.out.println(types.length);
        for (int i=0; i<types.length; i++) {
            table.put(types[i], entries[i]);
            System.out.println(table);
        }
        return table;
    }

    @GetMapping("/analysis_category/data")
    public ResponseEntity<TreeMap<String, Integer>> getPieChart(ModelAndView modelAndView) {
        int[] amountEntries = expensesRepository.getCategoryWiseExpenses(userId);
        String[] categories = expensesRepository.getAllCategories(userId);
        TreeMap<String, Integer> table = getTable(amountEntries, categories);
        modelAndView.addObject("chartData", table);
        modelAndView.addObject("size", expensesRepository.count());
        modelAndView.setViewName("analysis_graph");
        return new ResponseEntity<>(table, HttpStatus.OK);
    }

    @GetMapping("/analysis_month/data")
    public ResponseEntity<Map<String, Integer>> MonthWiseGraphData(ModelAndView modelAndView) {
        int[] amountEntries = expensesRepository.getMonthWiseExpenses(userId);
        String[] months = expensesRepository.getMonths(userId);
        TreeMap<String, Integer> table = getTable(amountEntries, months);
        modelAndView.addObject("chartData", table);
        modelAndView.addObject("size", expensesRepository.count());
        modelAndView.setViewName("analysis_graph");
        return new ResponseEntity<>(table, HttpStatus.OK);
    }

    @GetMapping("/analysis_year/data")
    public ResponseEntity<Map<String, Integer>> YearWiseGraphData(ModelAndView modelAndView) {
        int[] amountEntries = expensesRepository.getYearWiseExpenses(userId);
        String[] years = expensesRepository.getYears(userId);
        TreeMap<String, Integer> table = getTable(amountEntries, years);
        modelAndView.addObject("chartData", table);
        modelAndView.addObject("size", expensesRepository.count());
        modelAndView.setViewName("analysis_graph");
        return new ResponseEntity<>(table, HttpStatus.OK);
    }

    @GetMapping("/analysis_week/data")
    public ResponseEntity<Map<String, Integer>> Last7DaysGraphData(ModelAndView modelAndView) {
        int[] amountEntries = expensesRepository.getLastWeekExpenses(userId);
        String[] dates = expensesRepository.getLastWeekDates(userId);
        TreeMap<String, Integer> table = getTable(amountEntries, dates);
        modelAndView.addObject("chartData", table);
        modelAndView.addObject("size", expensesRepository.count());
        modelAndView.setViewName("analysis_graph");
        return new ResponseEntity<>(table, HttpStatus.OK);
    }
}
