package com.externship.expensetracker;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Balance {

    @Id
    String email;
    int balance;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
