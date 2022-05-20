package com.externship.expensetracker.util;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Balance {

    @Id
    String email;
    int balance;
}
