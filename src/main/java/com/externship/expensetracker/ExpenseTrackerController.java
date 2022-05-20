package com.externship.expensetracker;

import com.externship.expensetracker.repo.BalanceRepo;
import com.externship.expensetracker.repo.ExpensesRepo;
import com.externship.expensetracker.repo.UserRepo;
import com.externship.expensetracker.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class ExpenseTrackerController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    ExpensesRepo expensesRepo;
    @Autowired
    BalanceRepo balanceRepo;

    @RequestMapping("/index")
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/login")
    public String logInPage() {
        return "login";
    }

    @RequestMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/user_register")
    public String processRegister(User user){
        userRepo.save(user);
        return "login";
    }

    @PostMapping("/user_login")
    public String processLogIn(@RequestParam String email, @RequestParam String password){
        User person = userRepo.findByEmail(email);
        if(person!=null && Objects.equals(password, person.getPassword())) return "home";
        else {
            System.out.println("Wrong Credentials");
            return "login";
        }
    }
}
