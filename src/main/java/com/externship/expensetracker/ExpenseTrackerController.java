package com.externship.expensetracker;

import com.externship.expensetracker.repo.BalanceRepo;
import com.externship.expensetracker.repo.ExpensesRepo;
import com.externship.expensetracker.repo.UserRepo;
import com.externship.expensetracker.util.Expenses;
import com.externship.expensetracker.util.User;
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
    UserRepo userRepo;
    @Autowired
    ExpensesRepo expensesRepo;
    @Autowired
    BalanceRepo balanceRepo;

    String email;

    @RequestMapping("/index")
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/user_register")
    public String registerPage() {
        return "user_register";
    }

    @PostMapping("/process_user_register")
    public String processUserRegister(User user){
        userRepo.save(user);
        return "user_login";
    }

    @RequestMapping("/user_login")
    public String logInPage() {
        return "user_login";
    }

    @PostMapping("/process_user_login")
    public String processUserLogIn(@RequestParam String email, @RequestParam String password){
        User person = userRepo.findByEmail(email);
        if(person!=null && Objects.equals(password, person.getPassword())) {
            this.email = email;
            return "home";
        }
        else {
            System.out.println("Wrong Credentials");
            //Code to make alert in login page
            return "user_login";
        }
    }

    @RequestMapping("/expenses_add")
    public String addExpensesPage() {
        return "expenses_add";
    }

    @PostMapping("/process_expense_add")
    public void processExpensesAdd(Expenses expenses){
        expensesRepo.save(expenses);
    }

    @RequestMapping("/expenses_view")
    public String viewExpensesPage() {
        List<Expenses> expenses = expensesRepo.findAllByEmail(email);
        //Code for DOM
        return "expenses_view";
    }
}
